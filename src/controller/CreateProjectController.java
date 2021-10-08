package controller;

import Response.CreateProject;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.Authenticator;
import model.UserProfile;
import model.Workspace;

public class CreateProjectController implements Controllable {

    private Stage stage;
    private UserProfile profile;
    public CreateProjectController(Stage stage,  UserProfile profile) {
        this.stage = stage;
        this.profile = profile;
    }

    @FXML
    private TextField projectName;

    @FXML
    private Button ok;

    @FXML
    private Button cancel;

    @FXML
    public void initialize() {
        ok.setOnAction(event -> {
            new CreateProject(projectName.getText()).doAction(profile);
            Authenticator authenticator = Authenticator.getInstance();
            authenticator.save();
            Workspace workspace = new Workspace(profile);
            new Display().displayStage(new WorkspaceController(stage, profile));
        });

        cancel.setOnAction(event -> {
            Workspace workspace = new Workspace(profile);
            new Display().displayStage(new WorkspaceController(stage, profile));
        });
    }

    public void showStage(Pane root) {
        Scene scene = new Scene(root, 500, 300);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Create project");
        stage.show();
    }


}