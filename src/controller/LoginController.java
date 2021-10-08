package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import model.Authenticator;
import model.UserProfile;
import model.Workspace;

import java.io.IOException;

public class LoginController implements Controllable {

    private Stage stage;
    public LoginController(Stage stage)  {
        this.stage = stage;

    }

    @FXML
    private TextField uName;

    @FXML
    private TextField password;


    @FXML
    private Button login;

    @FXML
    private Button register;



    @FXML
    public void initialize() {
        login.setOnAction(event -> {
            Authenticator authenticator = Authenticator.getInstance();
            UserProfile profile = authenticator.authenticate(uName.getText(), password.getText());

            new Display().displayStage(new WorkspaceController(stage, profile));
        });
        register.setOnAction(event -> {
            new Display().displayStage(new SignupController(stage));
        });

    }
    public void showStage(Pane root) {
        Scene scene = new Scene(root, 500, 300);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Welcome");
        stage.show();
    }
}