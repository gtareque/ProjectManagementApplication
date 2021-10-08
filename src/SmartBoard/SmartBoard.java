package SmartBoard;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.layout.VBox;
import javafx.util.Callback;


import controller.LoginController;
import java.util.Scanner;

public class SmartBoard extends Application {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

//        Authenticator authenticator = new Authenticator();
//        authenticator.signUp();
//        UserProfile profile = authenticator.authenticate();
//        Workspace currentSpace = new Workspace(profile);
        Application.launch(args);


    }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../resources/view/LoginView.fxml"));
        Callback<Class<?>, Object> controllerFactory = param -> {
            return new LoginController(stage);
        };
        loader.setControllerFactory(controllerFactory);
        VBox root = loader.load();
        controller.LoginController loginController = loader.getController();
        loginController.showStage(root);



    }
}
