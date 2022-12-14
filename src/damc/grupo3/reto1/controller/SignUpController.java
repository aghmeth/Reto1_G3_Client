/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package damc.grupo3.reto1.controller;

import java.util.logging.Logger;
import java.awt.event.KeyEvent;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author Jessica y collaboradores Diego y Alejandro
 */
public class SignUpController {

    //Declaramos los campos que utilizaremos en esta ventana
    @FXML
    private TextField txtNombre2, txtNombreComp, txtEmail;

    @FXML
    private PasswordField txtPasswd2;

    //FORMATO CORRECTO PASSWORD
    @FXML
    private static final String PASSWORD_REGEX = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{3,15}$";

    @FXML
    private static final Pattern PASSWORD_PATTERN = Pattern.compile(PASSWORD_REGEX);

    @FXML
    private PasswordField txtConfirmPasswd;

    @FXML
    private Button btnSave, btnCancel;

    @FXML
    private Label lblError2, lblNombre2, lblNombreComp, lblEmail, lblPasswd2, lblConfirmPasswd;
    //FORMATO CORRECTO DEL EMAIL
    @FXML
    private static final String EMAIL_REGEX = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
    // private static final String  EMAIL_REGEX = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$.{10,30}";
    // private static final String EMAIL_REGEX = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";

    @FXML
    private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);

    @FXML
    protected static final Logger LOGGER = Logger.getLogger("SignUpController");

    @FXML
    public void initStage(Parent root) {
        LOGGER.info("Initializing Sign Up stage.");
        Scene scene = new Scene(root);

        //Los TextField nombre de usuario (txtNombre2), fullname  (txtNombreComp), email  (txtEmail),
        //password (txtPasswd2), ConfirmPassword (txtConfirmPasswd) est??n habilitados
        txtNombre2.setDisable(false);
        txtNombreComp.setDisable(false);
        txtEmail.setDisable(false);
        txtPasswd2.setDisable(false);
        txtConfirmPasswd.setDisable(false);

        //El bot??n save est?? habilitado.
        btnSave.setDisable(false);
        btnSave.setOnAction(this::handleButtonSaveAction);

        //El bot??n cancel est?? habilitado.
        btnCancel.setDisable(false);
        btnCancel.setOnAction(this::handleButtonCancel);

        //Los label de nombre de usuario, fullname, email,  password y confirmPasswd estan visibles.
        lblNombre2.setVisible(true);
        lblNombreComp.setVisible(true);
        lblEmail.setVisible(true);
        lblPasswd2.setVisible(true);
        lblConfirmPasswd.setVisible(true);

        //La ventana no es redimensionable.
        Stage stage = new Stage();
        stage.setResizable(false);

        //La ventana es una ventana modal.
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);

        //El foco inicialmente estar?? en el campo de nombre de usuario.
        txtNombre2.requestFocus();

        //El t??tulo de la ventana es ???Sign Up???.
        stage.setTitle("SIGN UP");

        stage.show();
    }

    @FXML
    //VALIDAR QUE TODO ESTE CORRECTO Y CUMPLA LOS REQUISITOS
    private void handleButtonSaveAction(ActionEvent event) {

        //Validar que los campos nombre de usuario, fullname, email, password y confirmPasswd est??n informados.
        try {
            //Si no est??n informados alguno de los campos saldr?? un label de error (lblError2).
            if (this.txtNombre2.getText().isEmpty() || this.txtNombreComp.getText().isEmpty()
                    || this.txtEmail.getText().isEmpty() || this.txtPasswd2.getText().isEmpty()
                    || this.txtConfirmPasswd.getText().isEmpty()) {
                throw new Exception("CAMPOS NO INFORMADOS");
            }

            //Validar que el m??ximo n??mero de caracteres en el campo de nombre de usuario, fullname, password y confirmPassword sea de 15.    
            if (this.txtNombre2.getText().length() > 15 || this.txtNombreComp.getText().length() > 15 || this.txtPasswd2.getText().length() > 15
                    || this.txtConfirmPasswd.getText().length() > 15) {
                throw new Exception("NUMERO CARACTERES \n INCORRECTOS");
            }

            //Validar que el email tenga formato espec??fico (xxxxx@gmail.com) y que no supere los 30 caracteres (ESPECIFICAMOS EL FORMATO ARRIBA).
            String email = this.txtEmail.getText();

            if (!(EMAIL_PATTERN.matcher(email).matches())) {
                throw new Exception("EMAIL \n INCORRECTOS");
            }

            //Validar que la password tenga formato especifico
            String password = this.txtPasswd2.getText();
            if (!(PASSWORD_PATTERN.matcher(password).matches())) {
                throw new Exception("CONTRASE??A NO VALIDA");
            }

            //Si los campos de password y confirmPassword no coinciden, saldr?? un label de error (lblError2) y limpia esos campos.
            if (txtPasswd2 == txtConfirmPasswd) {
                throw new Exception("LAS PASSWORD \n NO COINCIDEN");
            }

            //Seguido, saldr?? del m??todo del bot??n.
        } catch (Exception e) {
            lblError2.setVisible(true);
            lblError2.setText(e.getMessage());
        }

    }

    //Nos redirige a la ventana de login nuevamente y se cierra la ventana actual, sin guardar los datos.
    @FXML
    private void handleButtonCancel(ActionEvent event) {
        try {
            Stage stage = (Stage) this.btnCancel.getScene().getWindow();
            stage.close();
        } catch (Exception e) {
            new Exception("Error al cerrar la pesta??a " + e.getMessage());
        }

    }

}