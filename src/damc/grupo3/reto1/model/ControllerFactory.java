/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package damc.grupo3.reto1.model;

/**
 * Instantiate the interface methods that run on 'SignInClient' to the Controller
 * @author Diego
 */
public class ControllerFactory {

    /**
     * Con este método creamos el método getSocket, el cual llama al
     * SignInClient y recoge los métodos que hay en este (dependiendo de cual
     * sea llamado en las ventanas correspondientes
     * @return sign
     */
    public Sign getSocket() {
        Sign sign;
        sign = new SignInClient();
        return sign;
    }
}
