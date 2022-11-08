/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package damc.grupo3.reto1.controller;

import static damc.grupo3.reto1.controller.SignUpController.LOGGER;
import damc.grupo3.reto1.model.User;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
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
import javafx.stage.Stage;

/**
 *
 * @author 2dam
 */
public class PrincipalController {

    @FXML
    private Label lblTexto;
    @FXML
    private Button btnExit;

    @FXML
    public void initStage(Parent root) {

         LOGGER.info("Initializing Principal stage.");
         User user = new User();
         Scene scene = new Scene(root);
         //El campo de texto está deshabilitado
        lblTexto.setDisable(true);
        //El botón está habilitado
        btnExit.setDisable(false);
        lblTexto.setVisible(true);
        btnExit.setVisible(true);
        
        infoUser(user);
       
       
        Stage stage = new Stage();
        stage.setResizable(false);
        stage.setScene(scene);
        stage.setTitle("PRINCIPAL");

        stage.show();
      //  User user = new User();
       
    }

    @FXML
    private void handleExitButtonAction(ActionEvent event) {
        try {

            //El campo de texto está deshabilitado
            lblTexto.setDisable(true);

            //El botón está habilitado
            btnExit.setDisable(false);
            //Con esto vamos a crear una ventana de confirmación al pulsar el botón de salir
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
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage() + ButtonType.OK).showAndWait();
        }
    }
    public void infoUser(User user){
        lblTexto.setText(user.getLogin() + lblTexto.getText());
    }

}
