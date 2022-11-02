/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package damc.grupo3.reto1.exception;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 2dam
 * NO establece conexion con el servidor
 */
public class ServerConnectionException extends Exception{
    
   private static final long serialVersionUID = 1L;
    
    public ServerConnectionException (String message){
        super(message);
           
    } 
}
