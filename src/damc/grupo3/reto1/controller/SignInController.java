/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package damc.grupo3.reto1.controller;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 *
 * @author Josu ,Jessica
 */
public class SignInController {
    
    @FXML
    private Stage stage;
    
    @FXML
    private TextField txtNombre;
    
    @FXML
    private TextField txtPasswd;
    
    @FXML
    private Label lblError;
    
    @FXML
    private Label lblNombre;
    
    @FXML
    private Label lblPasswd;
    
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
    protected static final Logger LOGGER = Logger.getLogger("/controller/SignInController");
    
    public void initStage(Parent root) {
        
        LOGGER.info("Initializing SignIn stage");
        Scene scene = new Scene(root);
        stage.setScene(scene);
        //Los TextField nombre de usuario (txtNombre) y password (txtPasswd) están habilitados.
        txtNombre.setDisable(false);//false si quiero que este habilitado
        txtPasswd.setDisable(false);

        //El botón login (btnLogin) está habilitado.
        btnLogin.setDisable(false);

        //El botón SignUp (btnSignUp) está habilitado. 
        btnSignUp.setDisable(false);

        //Los label de nombre de usuario (lblNombre) y password (lblPasswd) son visibles.
        lblNombre.setDisable(false);
        lblPasswd.setDisable(false);

        //El foco estará puesto en el campo de nombre de usuario (txtNombre).
        txtNombre.requestFocus();

        //El título de la ventana es “Sign In”.
        stage.setTitle("SIGN IN");

        //La ventana no es redimensionable
        stage.setResizable(false);
        
        stage.show();
    }
    
    public void setStage(Stage stage) {
        this.stage = stage;
    }
    
    @FXML
    private void handleLoginAction(ActionEvent event) throws Exception {
        
        try {
            // Validar que los TextField nombre de usuario y password estén informados.
            //Si no está informado alguno de los dos campos, saldrá un label de error (lblError). Seguido, saldrá del método del botón.
            if (this.txtNombre.getText().isEmpty() || this.txtPasswd.getText().isEmpty()) {
                
                throw new Exception("CANPOS NO INFORMADOS ");
            }
            //Validar que el máximo número de caracteres en el campo de nombre de usuario y password sea de 15
            if (this.txtNombre.getText().length() > 15 || this.txtPasswd.getText().length() > 15) {
                
                throw new Exception("NUMERO CARACTERES \n INCORRECTOS");
            }
            String password = this.txtPasswd.getText();
            if (!(PASSWORD_PATTERN.matcher(password).matches())) {

                // Una vez que los campos de nombre de usuario y password
                //son válidos. Se llama al método getExecuteSignIn de la interfaz 
                //(Sign) pasándole un objeto (User), con los valores del nombre de usuario 
                //y la password: 
                // User user = Sign
                //cargar el fxml de la ventana de sign up utilizando un cargador no estatico 
//                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Principal.fxml"));
//
//                Parent root = loader.load();
//
//                PrincipalController principal = loader.getController();
//
//                principal.setUser(User);
//                principal.initialize(location, resources);
//                throw new Exception("CONTRASEÑA NO VALIDA");
//           
                throw new Exception("Contraseña incorrecta");
            }
            
        } catch (Exception e) {
            
            lblError.setVisible(true);
            lblError.setText(e.getMessage());
            
        }
        
    }
    
    @FXML
    private void handleSignUpAction(ActionEvent event) {
        
        try {
            Stage SignUpStage = new Stage();
            //cargar el fxml de la ventana de sign up utilizando un cargador no estatico
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/damc/grupo3/reto1/view/SignUp.fxml"));
            
            Parent root = (Parent) loader.load();
            
            SignUpController signUpController = ((SignUpController) loader.getController());
            
            signUpController.initStage(root);
        } catch (IOException ex) {
            Logger.getLogger(SignInController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
