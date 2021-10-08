package controller;

import Response.DeleteProject;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import model.Authenticator;
import model.UserProfile;

import java.io.IOException;

public class WorkspaceController implements Controllable {
    private Stage stage;
    private UserProfile profile;


    public WorkspaceController(Stage stage, UserProfile profile) {
        this.stage = stage;
        this.profile = profile;

    }
    @FXML
    private Button logOut;

    @FXML
    private Button buttonProfile;

    @FXML
    private Label displayName;

    @FXML
    private ImageView displayPhoto;


    @FXML
    private MenuItem newProjectButton;

    @FXML
    private TabPane projectTab;


    @FXML
    private MenuItem deleteProject;

    @FXML
    public void initialize() {
        displayName.setText(profile.getFirstName() +" " + profile.getLastName());
        if(profile.getPhoto() != null) {
            displayPhoto.setImage(profile.getPhoto());
        }
        for (int i = 0; i < profile.getProjects().size(); i++) {
            String name = profile.getProject(i).getName();
            projectTab.getTabs().add(new Tab(name));
        }
        newProjectButton.setOnAction(event -> {
            new Display().displayStage(new CreateProjectController(stage, profile));
            Authenticator authenticator = Authenticator.getInstance();
            authenticator.save();

        });

        deleteProject.setOnAction(event -> {
            int projectIndex = projectTab.getSelectionModel().getSelectedIndex();
            new DeleteProject(projectIndex).doAction(profile);
            new Display().displayStage(new WorkspaceController(stage, profile));
            Authenticator authenticator = Authenticator.getInstance();
            authenticator.save();
        });


        logOut.setOnAction(event -> {
            new Display().displayStage(new SignupController(stage));
        });

    }
    public void showStage(Pane root) {
        Scene scene = new Scene(root, 1000, 600);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Welcome");
        stage.show();
    }

}
