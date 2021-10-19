package controller;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.Authenticator;
import model.UserProfile;


public class LoginController implements Controllable {

    private Stage stage;
    public LoginController(Stage stage)  {
        this.stage = stage;

    }

    @FXML
    private TextField uName;

    @FXML
    private PasswordField password;


    @FXML
    private Button login;

    @FXML
    private Button register;

    @FXML
    private Label errorMessage;

    @FXML
    public void initialize() {
        login.setOnAction(event -> {
            Authenticator authenticator = Authenticator.getInstance();
            UserProfile profile = authenticator.authenticate(uName.getText(), password.getText());
            if(profile != null) {
                new Display().displayStage(new WorkspaceController(stage, profile));
            } else {
                errorMessage.setText("Incorrect username/password");
            }
        });
        register.setOnAction(event -> {
            new Display().displayStage(new SignupController(stage));
        });

    }
    public void showStage(Pane root) {
        Scene scene = new Scene(root, 500, 300);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Login");
        stage.show();
    }
}