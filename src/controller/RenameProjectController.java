package controller;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.Authenticator;
import model.UserProfile;

public class RenameProjectController implements Controllable {
    private Stage stage;
    private UserProfile profile;
    private int projectIndex;


    @FXML
    private TextField projectName;

    @FXML
    private Label errorMessage;

    @FXML
    private Button ok;

    @FXML
    private Button cancel;

    public RenameProjectController(Stage stage,  UserProfile profile, int projectIndex) {
        this.stage = stage;
        this.profile = profile;
        this.projectIndex = projectIndex;
    }

    @FXML
    public void initialize() {
        projectName.setText(profile.getProjects().get(projectIndex).getName());
        ok.setOnAction(event -> {
            if(projectName.getText().length() == 0) {
                errorMessage.setText("Project name cannot be empty");
            }else {
                Authenticator authenticator = Authenticator.getInstance();
                profile.getProjects().get(projectIndex).setName(projectName.getText());
                authenticator.save();
                new Display().displayStage(new WorkspaceController(stage, profile));
            }


        });

        cancel.setOnAction(event -> {

            new Display().displayStage(new WorkspaceController(stage, profile));
        });

    }

    public void showStage(Pane root) {
        Scene scene = new Scene(root, 500, 300);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Rename project");
        stage.show();
    }
}
