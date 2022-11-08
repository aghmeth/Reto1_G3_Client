/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package damc.grupo3.reto1.model;
                               
import damc.grupo3.reto1.exception.PasswordErrorException;
import damc.grupo3.reto1.exception.ServerErrorException;
import damc.grupo3.reto1.exception.UserAlreadyExitsException;
import damc.grupo3.reto1.exception.UserNotFoundException;
import damc.grupo3.reto1.model.MessageType;
import damc.grupo3.reto1.model.Message;
import static damc.grupo3.reto1.model.MessageType.ERROR_RESPONSE;
import static damc.grupo3.reto1.model.MessageType.OK_RESPONSE;
import static damc.grupo3.reto1.model.MessageType.PASSWORD_ERROR_RESPONSE;
import static damc.grupo3.reto1.model.MessageType.SIGNIN_REQUEST;
import static damc.grupo3.reto1.model.MessageType.SIGNUP_REQUEST;
import static damc.grupo3.reto1.model.MessageType.USER_ALREADY_EXISTS_RESPONSE;
import static damc.grupo3.reto1.model.MessageType.USER_NOT_FOUND_RESPONSE;
import damc.grupo3.reto1.model.Sign;
import damc.grupo3.reto1.model.User;
import java.awt.TrayIcon;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import static jdk.nashorn.internal.objects.NativeMath.log;

/**
 *
 * @author Ale y Diego
 */
public class SignInClient implements Sign{
    static final String HOST = "localhost";
    static final int PUERTO = 5000;    
    MessageType mt;
    public SignInClient() {
        
    }

    @Override
    public User getExecuteSignUp(User user) {
       Message m = new Message();
       ObjectOutputStream oos = null;
       ObjectInputStream ois = null;
        try {
            mt = SIGNUP_REQUEST;
            Socket skCliente = new Socket(HOST, PUERTO);
            oos = new ObjectOutputStream(skCliente.getOutputStream());
           
            m.getMessageType();
            m.getUser();
            System.out.println("Enviando mensaje");
            oos.writeObject(m);
            
            ois = new ObjectInputStream(skCliente.getInputStream());
            Message m2 = (Message) ois.readObject();
            System.out.println("El tipo de mensaje es: " + m2.getMessageType());
            if(null != mt) switch (mt) {
               case OK_RESPONSE:
                   log(Level.SEVERE, "The user has been logged correctly");
                   break;
               case USER_ALREADY_EXISTS_RESPONSE:
                   throw new UserAlreadyExitsException("The user already exists");
               case ERROR_RESPONSE:
                   throw new ServerErrorException("An error in the server has ocurred");
                   
               case MAX_USER:
                   System.out.println("Max users");
               default:
                   break;
           }
            
        } catch (IOException ex) {
            Logger.getLogger(SignInClient.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SignInClient.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UserAlreadyExitsException ex) {
            Logger.getLogger(SignInClient.class.getName()).log(Level.SEVERE, null, ex);
        }catch (ServerErrorException ex) {
            Logger.getLogger(SignInClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        return user;
    }

    @Override
    public User getExecuteSignIn(User user) {
        Message m = new Message();
        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;
        try {
            mt = SIGNIN_REQUEST;
            Socket skCliente = new Socket(HOST, PUERTO);
            oos = new ObjectOutputStream(skCliente.getOutputStream());           
            m.getMessageType();
            m.getUser();
            oos.writeObject(m);

            ois = new ObjectInputStream(skCliente.getInputStream());
            Message m2 = (Message) ois.readObject();
            
            if(mt == OK_RESPONSE) {
                log(Level.SEVERE, "El usuario se ha logeado correctamente");
            }else if(mt == USER_NOT_FOUND_RESPONSE) {
               throw new UserNotFoundException("The user was not found");
            }else if(mt == ERROR_RESPONSE) {
                throw new ServerErrorException("An error in the server has ocurred");
            }else if(mt == PASSWORD_ERROR_RESPONSE) {
                throw new PasswordErrorException("The password is incorrect");
            }
        } catch (IOException ex) {
            Logger.getLogger(SignInClient.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UserNotFoundException ex) {
            Logger.getLogger(SignInClient.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ServerErrorException ex) {
            Logger.getLogger(SignInClient.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SignInClient.class.getName()).log(Level.SEVERE, null, ex);
        } catch (PasswordErrorException ex) {
            Logger.getLogger(SignInClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        return user;   
    }
    
    
}