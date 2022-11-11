/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package damc.grupo3.reto1.controller;

import damc.grupo3.reto1.Aplication;
import java.util.concurrent.TimeoutException;
import javafx.application.Application;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import static org.testfx.api.FxAssert.verifyThat;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit.ApplicationTest;
import static org.testfx.matcher.base.NodeMatchers.isVisible;

/**
 *
 * @author Diego y Josu
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PrincipalControllerTest extends ApplicationTest {
    //Invocamos los campos de las ventanas que queremos probar
    private Label lblTexto;
    private TextField txtNombre, txtPasswd;
    private Button btnExit, btnLogin;

    //Abrimos la ventana
    @BeforeClass
    public static void openWindow() throws TimeoutException {
        FxToolkit.registerPrimaryStage();
        FxToolkit.setupApplication(Aplication.class);
    }

    //Instanciamos los campos de las ventanas
    @Before
    public void getFields() {
        lblTexto = lookup("#lblTexto").query();
        btnExit = lookup("#btnExit").query();
        btnLogin = lookup("#btnLogin").query();
        txtNombre = lookup("#txtNombre").query();
        txtPasswd = lookup("#txtPasswd").query();
    }
    
    //Este test comprueba que la ventana principal se abre desde la ventana de Sign In
    @Test
    public void test1_inicioVentana() {
        //Introducimos el nombre de usuario y una contraseña de prueba
        //una vez metidos pasamos a la ventana de principal
        clickOn(txtNombre);
        write("Ale");
        clickOn(txtPasswd);
        write("Abcd*1234");
        clickOn(btnLogin);
        //Nos aseguramos que el botón de salir y el texto están visibles
        verifyThat(lblTexto, isVisible());
        verifyThat(btnExit, isVisible());
    }

    //Este test comprueba que al pulsar el boton de salida se pulsa el boton de "Cancelar"
    //de la ventana emergente que se genera
    @Test
    public void test2_compruebaBotonExitCancelar() {
        clickOn(btnExit);
        clickOn("Cancelar");
    }
    //Este test comprueba que al pulsar el boton de salida se pulsa el boton de "Aceptar"
    //de la ventana emergente que se genera
    @Test
    public void test2_compruebaBotonExitEjecuAceptar() {
        clickOn(btnExit);
        clickOn("Aceptar");

    }

}

