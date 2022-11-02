/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package damc.grupo3.reto1.exception;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Password mal introducida
 * @author 2dam
 */
public class IncorrectPasswordException extends Exception {

         private static final long serialVersionUID = 1L;
    
    public IncorrectPasswordException (String message){
        super(message);
           
    } 
           
}
