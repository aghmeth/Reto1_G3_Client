/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exceptions;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *This exception is used to show a message if the passwords checked in the Sign Up view are incorrect
 * @author Alejandro
 */
public class SignUpIncorrectPassword {
    public SignUpIncorrectPassword() {
        try {
            throw new Exception ("The password is incorrect");
        } catch (Exception ex) {
            Logger.getLogger(SignUpIncorrectPassword.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
