/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package damc.grupo3.reto1.controller;

import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author Ale
 */
public class ControlDePractica {
    
    @FXML
    private Stage stage;
    
    @FXML
    private TextField txtNombre;
    
    @FXML
    private TextField txtPasswd1;
    
    @FXML
    private TextField txtPasswd2;
    
    @FXML
    private Label lblNombre;
    
    @FXML
    private Label lblPasswd1;
    
    @FXML
    private Label lblPasswd2;
        
    @FXML
    private Button buttonControl;
    
    @FXML
    private static final String PASSWORD_REGEX = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{3,15}$";
    
    @FXML
    protected static final Logger LOGGER = Logger.getLogger("/controller/ControlDePractica");
    
    public void initStage(Parent root) {
        LOGGER.info("Iniciando la ventana...");
        Scene scene = new Scene(root);
        stage.setScene(scene);
        
        txtNombre.setDisable(false);
        txtPasswd1.setDisable(false);
        txtPasswd2.setDisable(false);
        buttonControl.setDisable(false);
        
        lblNombre.setDisable(false);
        lblPasswd1.setDisable(false);
        lblPasswd2.setDisable(false);
        
        txtNombre.requestFocus();
        
        stage.setTitle("Ventana de prueba");
        
        stage.setResizable(true);
        
        stage.show();
        
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
    
    private void handleControlAction (ActionEvent event) throws Exception{
        
    }
}
