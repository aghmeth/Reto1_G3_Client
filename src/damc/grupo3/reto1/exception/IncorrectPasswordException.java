/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package damc.grupo3.reto1.exception;

/**
 *
 * @author 2dam
 */
public class IncorrectPasswordException extends Exception{
    
     private static final long serialVersionUID = 1L;
     
      public IncorrectPasswordException (String message){
        super(message);
    }
}
