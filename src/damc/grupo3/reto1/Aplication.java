

import damc.grupo3.reto1.controller.SignInController;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * This is the Application class for the client side.
 * @author 2dam
 */
public class Aplication extends Application{
    
   
    public void start(Stage stage) throws Exception {
        
        FXMLLoader loader= new FXMLLoader(getClass().getResource("view/SignIn.fxml")); 
        Parent root = (Parent)loader.load();
        SignInController signIn= ((SignInController)loader.getController());
        signIn.setStage(stage);
        signIn.initStage(root);
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
