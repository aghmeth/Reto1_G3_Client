/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package damc.grupo3.reto1.model;

import java.io.Serializable;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author 2dam
 */
public class SignInClient implements Serializable{
    
    /**
     * @param args the command line arguments
     */
    private static final String ip = "localhost";
    private static final int port = 5000;
    //private EnviarThread et;
   // private RecibirThread rt;
    Socket skCliente;
    private String mensaje;

    
    public static void main(String[] args) {
        //Instanciamos un nuevo cliente
        SignInClient cliente = new SignInClient();
    }

    public SignInClient() {
        try {
                //CREAMOS UN SOCKET NUEVO
                skCliente = new Socket(ip, port);
                System.out.println("Establecida conexion con el servidor");
                
              //CREAMOS LOS HILOS
               // et = new EnviarThread(skCliente);
               // rt = new RecibirThread(skCliente);
                
                //INICIALIZAMOS LOS HILOS
               // et.start();
                //rt.start();


        } catch (Exception ex) {
            Logger.getLogger(SignInClient.class.getName()).log(Level.SEVERE, null, ex);
            System.exit(0);
            
        }

    }
}
