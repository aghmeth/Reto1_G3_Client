/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.awt.event.KeyEvent;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;


/**
 *
 * @author Jessica
 */
public class SignUpController implements Initializable {

    //Declaramos los campos que utilizaremos en esta ventana
    @FXML
    private TextField txtNombre2, txtNombreComp, txtEmail;

    @FXML
    private PasswordField txtPasswd2;

    //FORMATO CORRECTO PASSWORD
    @FXML
    private static final String PASSWORD_REGEX ="^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{3,15}$";

    @FXML
    private static final Pattern PASSWORD_PATTERN = Pattern.compile(PASSWORD_REGEX);

    @FXML
    private PasswordField txtConfirmPasswd;

    @FXML
    private Button btnSave, btnCancel;

    @FXML
    private Label lblError2;

    //FORMATO CORRECTO DEL EMAIL
    @FXML
   // private static final String email = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$.{10,30}";
    private static final String EMAIL_REGEX = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";
    

    @FXML
    private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);

    @FXML
    //VALIDAR QUE TODO ESTE CORRECTO Y CUMPLA LOS REQUISITOS
    private void handleButtonAction(ActionEvent event) {

        //Validar que los campos nombre de usuario, fullname, email, password y confirmPasswd estén informados.
        try {
            if (this.txtNombre2.getText().isEmpty() || this.txtNombreComp.getText().isEmpty()
                    || this.txtEmail.getText().isEmpty() || this.txtPasswd2.getText().isEmpty()
                    || this.txtConfirmPasswd.getText().isEmpty()) {
                //Si no están informados alguno de los campos saldrá un label de error (lblError2).
                lblError2.setVisible(true);
                lblError2.setText("CAMPOS NO INFORMADOS");

            } else {

                //Validar que el máximo número de caracteres en el campo de nombre de usuario, fullname, password y confirmPassword sea de 15.     
                if (this.txtNombre2.getText().length() > 15 || this.txtNombreComp.getText().length() > 15 || this.txtPasswd2.getText().length() > 15
                        || this.txtConfirmPasswd.getText().length() > 15) {
                    lblError2.setVisible(true);
                    lblError2.setText("NUMERO CARACTERES \n INCORRECTOS");

                } else {
                    //Validad que el email tenga formato específico (xxxxx@gmail.com) y que no supere los 30 caracteres (ESPECIFICAMOS EL FORMATO ARRIBA).
                    String recoger = this.txtEmail.toString();
                    if (!(EMAIL_PATTERN.matcher(recoger).matches())) {
                        lblError2.setVisible(true);
                        lblError2.setText("EMAIL \n INCORRECTOS");

                    } else {
                        //Validar que la password tenga formato especifico
                        String password = this.txtPasswd2.getText();
                        if (!(PASSWORD_PATTERN.matcher(password).matches())) {
                            lblError2.setVisible(true);
                            lblError2.setText("CONTRASEÑA NO VALIDA");

                        } else {
                            //Si los campos de password y confirmPassword no coinciden, saldrá un label de error (lblError2) y limpia esos campos.
                            if (txtPasswd2 != txtConfirmPasswd) {
                                lblError2.setVisible(true);
                                lblError2.setText("PASSWORD CORRECTAS");
                            }else{
                             lblError2.setVisible(true);
                                lblError2.setText("LAS PASSWORD \n NO COINCIDEN");   
                         }
                       
                    }

                }

                //Seguido, saldrá del método del botón.
            }
        } catch (Exception e) {

        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
   

}
