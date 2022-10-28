/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exceptions;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *This exception show a message wich says an error ocurred trying to connect with the server
 * @author Alejandro
 */
public class ServerConnectionException extends Exception{
    public ServerConnectionException() {
        try {
            throw new Exception ("Error trying to connect with the server");
        } catch (Exception ex) {
            Logger.getLogger(ServerConnectionException.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
