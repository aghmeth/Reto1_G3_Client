/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package damc.grupo3.reto1.model;

import com.sun.deploy.config.Config;
import damc.grupo3.reto1.exception.PasswordErrorException;
import damc.grupo3.reto1.exception.ServerErrorException;
import damc.grupo3.reto1.exception.UserAlreadyExitsException;
import damc.grupo3.reto1.exception.UserNotFoundException;

import static damc.grupo3.reto1.model.MessageType.ERROR_RESPONSE;
import static damc.grupo3.reto1.model.MessageType.OK_RESPONSE;
import static damc.grupo3.reto1.model.MessageType.PASSWORD_ERROR_RESPONSE;
import static damc.grupo3.reto1.model.MessageType.SIGNIN_REQUEST;
import static damc.grupo3.reto1.model.MessageType.USER_ALREADY_EXISTS_RESPONSE;
import static damc.grupo3.reto1.model.MessageType.USER_NOT_FOUND_RESPONSE;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import static jdk.nashorn.internal.objects.NativeMath.log;

/**
 * This class is the Client Socket. Depending on the method sent by the controller, it executes 'getExecuteSignIn' or 'getExecuteSignUp'.
 * @author Alejandro, Jessica y Diego
 */
public class SignInClient implements Sign {

    private static final ResourceBundle RETO1 = ResourceBundle.getBundle("damc.grupo3.reto1.model.Config");
    private static final int PUERTO = Integer.parseInt(RETO1.getString("PORT"));
    private static final String HOST = ResourceBundle.getBundle("damc.grupo3.reto1.model.Config").getString("Ip");
    MessageType mt;
    private Encapsulator encap = null;

    /**
     * Connect with the 'SignerServer', recieves the information from the 'signup' 
     * window and enters it into the database
     * @param user object of type User
     * @throws UserAlreadyExitsException this exception is thrown when the user is not found in our database
     * @throws ServerErrorException this exception is thrown when there is a error in the server
     * @return user
     */
    
    @Override
    public User getExecuteSignUp(User user) throws UserAlreadyExitsException, ServerErrorException {

        MessageType mst;
        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;

        try {
            //Enviamos el objecto encapsulado al servidor
            Socket skCliente = new Socket(HOST, PUERTO);
            oos = new ObjectOutputStream(skCliente.getOutputStream());
            encap = new Encapsulator();
            encap.setUser(user);
            encap.setMessage(MessageType.SIGNUP_REQUEST);
            oos.writeObject(encap);

            //Recivimos el objeto encapsulado del servidor
            ois = new ObjectInputStream(skCliente.getInputStream());
            encap = (Encapsulator) ois.readObject();
            user = encap.getUser();
            //Declaramos una variable int, pues las enumeraciones devuelven valores int
            int decision = encap.getMessage().ordinal();
            oos.close();
            ois.close();
            skCliente.close();
            //Dependiendo de el mensaje que reciva lanza o escribe un mensaje nuevo
            switch (encap.getMessage()) {
                case OK_RESPONSE:
                    return user;
                case USER_ALREADY_EXISTS_RESPONSE:
                    throw new UserAlreadyExitsException("The user already exists");
                case ERROR_RESPONSE:
                    throw new ServerErrorException("An error in the server has ocurred");
            }

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SignInClient.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(SignInClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        return user;
    }

    
    /**
     * Connects with the 'signerServer', recieves the information from the 'signIn' 
     * window and checks that the user is in the database
     * @param user object of type User
     * @throws PasswordErrorException this exception is thrown when there is a error in the password
     * @throws UserNotFoundException this exception is thrown when the user is not found
     * @throws ServerErrorException this exception is thrown when there is an error in the server
     * @return user
     */
    
    @Override
    public User getExecuteSignIn(User user) throws PasswordErrorException, UserNotFoundException, ServerErrorException {

        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;

        try {
            //Enviamos el objecto encapsulado al servidor
            Socket skCliente = new Socket(HOST, PUERTO);
            oos = new ObjectOutputStream(skCliente.getOutputStream());
            encap = new Encapsulator();
            encap.setUser(user);
            encap.setMessage(MessageType.SIGNIN_REQUEST);
            oos.writeObject(encap);

            //Recivimos el objeto encapsulado del servidor
            ois = new ObjectInputStream(skCliente.getInputStream());
            encap = (Encapsulator) ois.readObject();
            user = encap.getUser();
            int decision = encap.getMessage().ordinal();
            oos.close();
            ois.close();
            skCliente.close();
            //Dependiendo de el mensaje que reciva lanza o escribe un mensaje nuevo
            switch (encap.getMessage()) {
                case OK_RESPONSE:
                    return user;
                case USER_NOT_FOUND_RESPONSE:
                    throw new UserNotFoundException("The user was not found");
                case ERROR_RESPONSE:
                    throw new ServerErrorException("An error in the server has ocurred");
                case PASSWORD_ERROR_RESPONSE:
                    throw new PasswordErrorException("The password is incorrect");
            }

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SignInClient.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(SignInClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        return user;
    }
}
