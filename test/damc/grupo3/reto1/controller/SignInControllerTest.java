/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package damc.grupo3.reto1.controller;

import damc.grupo3.reto1.Aplication;
import java.util.concurrent.TimeoutException;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.junit.Test;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import static org.testfx.api.FxAssert.verifyThat;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit.ApplicationTest;
import static org.testfx.matcher.base.NodeMatchers.isVisible;
import org.testfx.matcher.control.LabeledMatchers;
import org.testfx.matcher.control.TextInputControlMatchers;

/**
 *
 * @author Josu
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SignInControllerTest extends ApplicationTest {

    private TextField txtNombre;
    private TextField txtPasswd;
    private Label lblNombre;
    private Label lblPasswd;
    private Label lblError;
    private Button btnLogin;
    private Button btnSignUp;

    @BeforeClass
    public static void openWindow() throws TimeoutException {
        FxToolkit.registerPrimaryStage();
        FxToolkit.setupApplication(Aplication.class);
    }

    @Before
    public void getFields() {
        txtNombre = lookup("#txtNombre").query();
        txtPasswd = lookup("#txtPasswd").query();
        lblNombre = lookup("#lblNombre").query();
        lblPasswd = lookup("#lblPasswd").query();
        lblError = lookup("#lblError").query();
        btnLogin = lookup("#btnLogin").query();
        btnSignUp = lookup("#btnSignUp").query();

    }

    @Test
    public void test1_InicioVentana() {

        //this.getFields();
        verifyThat(txtNombre, isVisible());
        verifyThat(txtPasswd, isVisible());
        verifyThat(lblNombre, isVisible());
        verifyThat(lblPasswd, isVisible());
        verifyThat(btnLogin, isVisible());
        verifyThat(btnSignUp, isVisible());
        verifyThat(txtNombre, TextInputControlMatchers.hasText(""));
        verifyThat(txtPasswd, TextInputControlMatchers.hasText(""));

    }

    @Test
    public void test2_ComprobarCamposVacios() {

        clickOn(btnLogin);
        verifyThat(lblError, LabeledMatchers.hasText("CAMPOS NO INFORMADOS"));
    }

    @Test
    public void test3_ComprobarCampologinVacio() {

        clickOn(txtNombre);
        write("Javi");
        clickOn(btnLogin);
        verifyThat(lblError, LabeledMatchers.hasText("CAMPOS NO INFORMADOS"));

    }

    @Test
    public void test4_ComprobarCamporobarPasswordVacio() {

        clickOn(txtNombre);
        txtNombre.clear();
        clickOn(txtPasswd);
        write("qwer");
        clickOn(btnLogin);
        verifyThat(lblError, LabeledMatchers.hasText("CAMPOS NO INFORMADOS"));

    }

    @Test
    public void test5_ComprobarCarecteresNombre() {

        clickOn(txtPasswd);
        txtPasswd.clear();

        clickOn(txtNombre);
        write("qwertyuiopasdfgh");

        clickOn(txtPasswd);
        write("Man1");

        clickOn(btnLogin);
        verifyThat(lblError, LabeledMatchers.hasText("NUMERO CARACTERES \n INCORRECTOS"));

    }

    @Test
    public void test6_ComprobarCarecteresPasswd() {

        clickOn(txtPasswd);
        txtPasswd.clear();
        write("qwertyuiopasdfgh");

        clickOn(txtNombre);
        txtNombre.clear();
        write("david");

        clickOn(btnLogin);
        verifyThat(lblError, LabeledMatchers.hasText("NUMERO CARACTERES \n INCORRECTOS"));

    }

    @Test
    public void test7_ComprobarIncorrectPasswd() {

        clickOn(txtPasswd);
        txtPasswd.clear();
        write("manolo");

        clickOn(btnLogin);
        verifyThat(lblError, LabeledMatchers.hasText("Contraseña incorrecta"));

    }

    

    @Test
    public void test8_ComprobarTodoCorrecto() {

        clickOn(txtPasswd);
        txtPasswd.clear();
        write("Abcd*1234");
        
        clickOn(txtNombre);
        txtNombre.clear();
        write("david");
        
        clickOn(btnLogin);
        verifyThat("#idPane", isVisible());
        
        

    }
    


}
