/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package damc.grupo3.reto1.controller;

import damc.grupo3.reto1.exception.IncorrectPasswordException;
import damc.grupo3.reto1.exception.ServerConnectionException;
import damc.grupo3.reto1.exception.UserNameErrorException;
import damc.grupo3.reto1.model.ControllerFactory;
import damc.grupo3.reto1.model.Sign;
import damc.grupo3.reto1.model.User;
import java.util.logging.Logger;
import java.awt.event.KeyEvent;
import java.net.URL;
import static java.time.Instant.now;
import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;
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
 * Controller for the 'sign up' window
 * @author Jessica y Josu
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

    //Declaramos la interfaz
    private Sign interf;
    private String IncorrectPasswordException;

    /**
     * Method to initialize the window
     * @param root the root of the window
     */
    @FXML
    public void initStage(Parent root) {
        LOGGER.info("Initializing Sign Up stage.");
        Scene scene = new Scene(root);

        //Los TextField nombre de usuario (txtNombre2), fullname  (txtNombreComp), email  (txtEmail), 
        //password (txtPasswd2), ConfirmPassword (txtConfirmPasswd) están habilitados
        txtNombre2.setDisable(false);
        txtNombreComp.setDisable(false);
        txtEmail.setDisable(false);
        txtPasswd2.setDisable(false);
        txtConfirmPasswd.setDisable(false);

        //El botón save está habilitado.
        btnSave.setDisable(false);

        //El botón cancel está habilitado. 
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

        //El foco inicialmente estará en el campo de nombre de usuario.
        txtNombre2.requestFocus();

        //El título de la ventana es “Sign Up”.
        stage.setTitle("SIGN UP");
        
        lblError2.setVisible(true);
        lblError2.setText("Bienvenido");

        stage.show();

        //Invocamos a la factoria
        ControllerFactory fact = new ControllerFactory();
        interf = fact.getSocket();

    }

    /**
     * 'save' button method with validations
     * @param event
     */
    
    @FXML
    //VALIDAR QUE TODO ESTE CORRECTO Y CUMPLA LOS REQUISITOS
    private void handleButtonSaveAction(ActionEvent event) throws IncorrectPasswordException, UserNameErrorException {

        //Validar que los campos nombre de usuario, fullname, email, password y confirmPasswd estén informados.
        try {
            //Si no están informados alguno de los campos saldrá un label de error (lblError2).
            if (this.txtNombre2.getText().isEmpty() || this.txtNombreComp.getText().isEmpty()
                    || this.txtEmail.getText().isEmpty() || this.txtPasswd2.getText().isEmpty()
                    || this.txtConfirmPasswd.getText().isEmpty()) {
                throw new Exception("CAMPOS NO INFORMADOS");
            }

            //Validar que el máximo número de caracteres en el campo de nombre de usuario, fullname, password y confirmPassword sea de 15.     
            if ((this.txtNombreComp.getText().length() > 15 || this.txtPasswd2.getText().length() > 15
                    || this.txtConfirmPasswd.getText().length() > 15) || (this.txtNombreComp.getText().length() < 3
                    || this.txtPasswd2.getText().length() < 3 || this.txtConfirmPasswd.getText().length() < 3)) {
                throw new Exception("NUMERO CARACTERES INCORRECTOS");
            }
            
            if (this.txtNombre2.getText().length() > 15 || this.txtNombre2.getText().length() < 3) {
                throw new UserNameErrorException("NOMBRE DE USUARIO\n INCORRECTO");
            }

            //Validar que el email tenga formato específico (xxxxx@gmail.com) y que no supere los 30 caracteres (ESPECIFICAMOS EL FORMATO ARRIBA).
            String email = this.txtEmail.getText();

            if (!(EMAIL_PATTERN.matcher(email).matches())) {
                throw new Exception("EMAIL \n INCORRECTOS");
            }

            //Validar que la password tenga formato especifico
            String password = this.txtPasswd2.getText();
            if (!(PASSWORD_PATTERN.matcher(password).matches())) {
                throw new Exception("CONTRASEÑA NO VALIDA");
            }

            //Si los campos de password y confirmPassword no coinciden, saldrá un label de error (lblError2) y limpia esos campos.
            if (!(txtPasswd2.getText().equals(txtConfirmPasswd.getText()))) {
                throw new IncorrectPasswordException("LAS CONTRASEÑAS\n NO COINCIDEN");
                
            
               
                //throw new Exception("usuario registrado");
            } else {

                User user = new User();
                user.setLogin(txtNombre2.getText());
                user.setFullname(txtNombreComp.getText());
                user.setEmail(txtEmail.getText());
                user.setPassword(txtPasswd2.getText());
                user.setLastPasswordChange(Date.from(now()));
                
                Alert ventanita = new Alert(Alert.AlertType.INFORMATION);
                ventanita.setHeaderText(null);
                ventanita.setTitle("INFORMACIÓN");
                ventanita.setContentText("EL USUARIO SE HA REGISTRADO CORRECTAMENTE");
                Optional<ButtonType> action = ventanita.showAndWait();
                
                cerrarVentana();
                interf.getExecuteSignUp(user);

            }

            //Seguido, saldrá del método del botón.
        } catch (Exception e) {

            lblError2.setVisible(true);
            lblError2.setText(e.getMessage());
        } 

    }

    /**
     *'cancel' button method. It redirects us to the login window again. The
     * current window is closed without saving the data if we have not pressed 'save'
     *@param event
     */
  
    @FXML
    private void handleButtonCancel(ActionEvent event) {
        try {
            Stage stage = (Stage) this.btnCancel.getScene().getWindow();
            stage.close();
        } catch (Exception e) {
            new Exception("Error al cerrar la pestaña " + e.getMessage());
        }

    }

    private void cerrarVentana() {
     Stage stage = (Stage) this.btnSave.getScene().getWindow();
     stage.close();
    }
}