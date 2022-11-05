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
       public SignInClient getSocket(){
           SignInClient skCliente;
           skCliente = new SignInClient();
           return skCliente;
    }
       /*
       public SignInController getControllerSignIn(){
           SignInController signIn;
           signIn = new SignInController();
           return signIn;
       }
       public SignUpController getControllerSignIn(){
           SignUpController signUp;
           signUp= new SignInController();
           return signUp;
       }*/
       
}
