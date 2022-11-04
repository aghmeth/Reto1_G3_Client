/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package damc.grupo3.reto1.exception;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *This exception is used to show a message if the passwords checked in the Sign Up view are incorrect
 * @author Alejandro
 */
public class SignUpIncorrectPassword extends Exception{
    public SignUpIncorrectPassword(String msg) {
        super(msg);
    }
}
