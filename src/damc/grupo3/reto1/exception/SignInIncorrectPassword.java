/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package damc.grupo3.reto1.exception;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *This exception shows a message wich says the password wich corresponds to the user in the Sign In view is incorrect
 * @author Alejandro
 */
public class SignInIncorrectPassword extends Exception{
    public SignInIncorrectPassword(String msg) {
        super(msg);
    }
}