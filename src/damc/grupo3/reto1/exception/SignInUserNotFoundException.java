/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package damc.grupo3.reto1.exception;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *This is a exception wich shows a message to advertise us that user Introduced in the SignIn view was not found
 * @author Alejandro
 */
public class SignInUserNotFoundException extends Exception{
    public SignInUserNotFoundException(String msg) {
        super(msg);
    }
}
