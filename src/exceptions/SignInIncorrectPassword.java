/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exceptions;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *This exception shows a message wich says the password wich corresponds to the user in the Sign In view is incorrect
 * @author Alejandro
 */
public class SignInIncorrectPassword {
    public SignInIncorrectPassword() {
        try {
            throw new Exception ("The password is incorrect");
        } catch (Exception ex) {
            Logger.getLogger(SignInIncorrectPassword.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
