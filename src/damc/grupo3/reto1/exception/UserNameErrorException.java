/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package damc.grupo3.reto1.exception;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Datos introducidos en el campo de usuario son erroneos
 * @author 2dam
 * 
 */
public class UserNameErrorException extends Exception{
 
        private static final long serialVersionUID = 1L;
    
    public UserNameErrorException (String message){
        super(message);
           
    } 
}
