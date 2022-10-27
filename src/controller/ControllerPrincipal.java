/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;

/**
 *
 * @author Diego
 */
public class ControllerPrincipal implements Initializable {
    
    private String nombre = "pablo";
    @FXML
    private Label lblTexto;
    @FXML
    private Button btnExit;
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
       try{
       Alert ventanita = new Alert(Alert.AlertType.CONFIRMATION);
            ventanita.setHeaderText(null);
            ventanita.setTitle("Advertencia");
            ventanita.setContentText("¿Deseas Salir?");
            //Con este Optional<ButtonType> creamos botones de Ok y cancelar
            Optional<ButtonType> action = ventanita.showAndWait();
            //Si le da a OK el programa cesará de existir, se cierra por completo
            if (action.get() == ButtonType.OK) {
                Platform.exit();
            } else {
                //Si le da a cancelar la ventana emergente se cerrará pero la ventana principal se mantiene
                ventanita.close();
            }      
       }catch(Exception e){
           new Alert(Alert.AlertType.ERROR, e.getMessage() + ButtonType.OK).showAndWait();
       }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
           
           lblTexto.setText(nombre + "\n" + lblTexto.getText());
            //El campo de texto estará deshabilitado
           lblTexto.setDisable(true);
           //El botón está habilitado
           btnExit.setDisable(false);
           
    }    
    
}
