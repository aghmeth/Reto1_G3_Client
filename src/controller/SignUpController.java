/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.awt.event.KeyEvent;
import java.net.URL;
import java.util.ResourceBundle;
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
 * @author 2dam
 */
public class SignUpController implements Initializable {

    //Declaramos los campos que utilizaremos en esta ventana
    @FXML
    private TextField txtNombre2, txtNombreComp, txtEmail;

    @FXML
    private PasswordField txtPasswd2;

    @FXML
    private PasswordField txtConfirmPasswd;

    @FXML
    private Button btnSave, btnCancel;

    @FXML
    private Label lblError2;

    
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
       
        //Validar que los campos nombre de usuario, fullname, 
        //email, password y confirmPasswd estén informados.
        try {
            if (this.txtNombre2.getText().isEmpty() || this.txtNombreComp.getText().isEmpty()
                    || this.txtEmail.getText().isEmpty() || this.txtPasswd2.getText().isEmpty()
                    || this.txtConfirmPasswd.getText().isEmpty()) {

            } else {
                //Si no están informados alguno de los campos saldrá un label de error (lblError2).
                lblError2.setVisible(true);
                lblError2.setText("");

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
