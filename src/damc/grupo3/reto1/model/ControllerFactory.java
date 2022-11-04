/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package damc.grupo3.reto1.model;



/**
 *
 * @author Diego
 */
public class ControllerFactory{
    //Con este método creamos el método getSocket, el cual llama al SignInClient
       private SignInClient getSocket(){
           SignInClient skCliente;
           skCliente = new SignInClient();
           return skCliente;
    }
       /*
       private SignInController getControllerSignIn(){
           SignInController signIn;
           signIn = new SignInController();
           return signIn;
       }
       private SignUpController getControllerSignIn(){
           SignUpController signUp;
           signUp= new SignInController();
           return signUp;
       }*/
       
}
