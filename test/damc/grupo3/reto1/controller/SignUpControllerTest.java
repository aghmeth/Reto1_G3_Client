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
import javafx.scene.control.PasswordField;
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
import org.testfx.matcher.control.LabeledMatchers;
import org.testfx.matcher.control.TextInputControlMatchers;

/**
 *
 * @author Josu
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SignUpControllerTest extends ApplicationTest {

    private TextField txtNombre2;
    private TextField txtNombreComp;
    private TextField txtEmail;
    private PasswordField txtPasswd2;
    private PasswordField txtConfirmPasswd;
    private Button btnSave;
    private Button btnSignUp;
    private Button btnCancel;
    private Label lblError2;
    private Label lblNombre2;
    private Label lblNombreComp;
    private Label lblEmail;
    private Label lblPasswd2;
    private Label lblConfirmPasswd;

    @BeforeClass
    public static void openWindow() throws TimeoutException {
        FxToolkit.registerPrimaryStage();
        FxToolkit.setupApplication(Aplication.class);

    }

    @Before
    public void getFields() {

        txtNombre2 = lookup("#txtNombre2").query();
        txtNombreComp = lookup("#txtNombreComp").query();
        txtEmail = lookup("#txtEmail").query();
        txtPasswd2 = lookup("#txtPasswd2").query();
        txtConfirmPasswd = lookup("#txtConfirmPasswd").query();
        btnSave = lookup("#btnSave").query();

        btnCancel = lookup("#btnCancel").query();
        lblError2 = lookup("#lblError2").query();
        lblNombre2 = lookup("#lblNombre2").query();
        lblNombreComp = lookup("#lblNombreComp").query();
        lblEmail = lookup("#lblEmail").query();
        lblPasswd2 = lookup("#lblPasswd2").query();
        lblConfirmPasswd = lookup("#lblConfirmPasswd").query();

        btnSignUp = lookup("#btnSignUp").query();
    }

    @Test
    public void test1_InicioVentana() {
        clickOn(btnSignUp);
    }

    @Test
    public void test2_CamposVacios() {

        clickOn(btnSave);
        verifyThat(lblError2, LabeledMatchers.hasText("CAMPOS NO INFORMADOS"));

    }

    @Test
    public void test3_CaracteresIncorrectos() {

        clickOn(txtNombreComp);
        write("QWERTYUIOPASDFGH");

        clickOn(txtNombre2);
        write("juan");
        clickOn(txtPasswd2);
        write("Man1");
        clickOn(txtConfirmPasswd);
        write("Man1");
        clickOn(txtEmail);
        write("juan@gmail.com");
        clickOn(txtConfirmPasswd);

        clickOn(btnSave);
        verifyThat(lblError2, LabeledMatchers.hasText("NUMERO CARACTERES \n INCORRECTOS"));

    }

    @Test
    public void test4_EmailIncorrectos() {

        clickOn(txtNombreComp);
        eraseText(16);
        write("jessica");

        clickOn(txtEmail);
        eraseText(14);
        write("juangmail.com");

        clickOn(btnSave);
        verifyThat(lblError2, LabeledMatchers.hasText("EMAIL \n INCORRECTOS"));

    }

    @Test
    public void test5_ConrtraseñaIncorrecta() {

        clickOn(txtEmail);
        eraseText(14);
        write("juan@gmail.com");

        clickOn(txtPasswd2);
        eraseText(4);
        write("abcd");

        clickOn(txtConfirmPasswd);
        eraseText(4);
        write("abcd");

        clickOn(btnSave);
        verifyThat(lblError2, LabeledMatchers.hasText("CONTRASEÑA NO VALIDA"));

    }

    /*@Test
    public void test6_ConrtraseñaNoCoincide() {

        clickOn(txtPasswd2);
        eraseText(4);
        write("Man1");

        clickOn(txtConfirmPasswd);
        eraseText(4);
        write("Mann1");

        clickOn(btnSave);
        verifyThat(lblError2, LabeledMatchers.hasText("LAS PASSWORD \n NO COINCIDEN"));

    }*/

    @Test
    public void test7_TodoCorrecto() {

        clickOn(txtConfirmPasswd);
        eraseText(4);
        write("Man1");

        clickOn(btnSave);

    }
}