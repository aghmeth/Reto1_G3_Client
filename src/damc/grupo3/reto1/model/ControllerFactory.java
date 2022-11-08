/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package damc.grupo3.reto1.model;

/**
 *
 * @author 2dam
 */
public class ControllerFactory {
    
              
    public SignInClient getSocket() {
        return new SignInClient();
    }

    public static void main(String[] arg) {
       new ControllerFactory(); 
    }
}