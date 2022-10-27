/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author Josu ,Jessica
 */
public class SignInController implements Initializable {

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtPasswd;

    @FXML
    private Label lblError;

    @FXML
    private Button btnLogin;

    @FXML
    private Button btnSignUp;

    @FXML
    //con esta sentencia en orden le estamos diciendo que tiene que tener minimo un numero una letra minuscula una mayuscula y que no puede tener espacios en blanco
    private static final String PASSWORD_REGEX = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{3,15}$";
  

    @FXML
    private static final Pattern PASSWORD_PATTERN = Pattern.compile(PASSWORD_REGEX);

    @FXML
    private void handleLoginAction(ActionEvent event) {

        try {
            // Validar que los TextField nombre de usuario y password estén informados.
            //Si no está informado alguno de los dos campos, saldrá un label de error (lblError). Seguido, saldrá del método del botón.
            if (this.txtNombre.getText().isEmpty() || this.txtPasswd.getText().isEmpty()) {
                lblError.setVisible(true);
                lblError.setText("CAMPOS NO INFORMADOS");
            } else {
                //Validar que el máximo número de caracteres en el campo de nombre de usuario y password sea de 15
                if (this.txtNombre.getText().length() > 15 || this.txtPasswd.getText().length() > 15) {
                    lblError.setVisible(true);
                    lblError.setText("NUMERO CARACTERES \n INCORRECTOS");
                } else {
                    String password = this.txtPasswd.getText();
                    if (PASSWORD_PATTERN.matcher(password).matches()) {
                        //cargar el fxml de la ventana de sign up utilizando un cargador no estatico 
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Principal.fxml"));

                        Parent root = loader.load();

                        PrincipalController principal = loader.getController();

                        Scene scene = new Scene(root);
                        Stage stage = new Stage();

                        stage.setScene(scene);
                        stage.show();

                        Stage myStage = (Stage) this.btnLogin.getScene().getWindow();

                    } else {

                        lblError.setVisible(true);
                        lblError.setText("CONTRASEÑA NO VALIDA");

                    }

                }
            }

        } catch (Exception e) {

            new Alert(Alert.AlertType.ERROR, "Los campos usuario y contraseña \n deben estar rellenos ", ButtonType.OK).showAndWait();
        }

    }

    @FXML
    private void handleSignUpAction(ActionEvent event) {

        try {
            //cargar el fxml de la ventana de sign up utilizando un cargador no estatico
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/SignUp.fxml"));

            Parent root = loader.load();

            PrincipalController principal = loader.getController();

            Scene scene = new Scene(root);
            Stage stage = new Stage();

            stage.setScene(scene);
            stage.show();

            Stage myStage = (Stage) this.btnLogin.getScene().getWindow();
        } catch (IOException ex) {
            Logger.getLogger(SignInController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
}
