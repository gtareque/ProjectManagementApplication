package controller;

import Response.CreateColumn;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.Authenticator;
import model.Project;
import model.UserProfile;

public class AddColumnController implements Controllable {
    @FXML
    private TextField columnName;

    @FXML
    private Button addColumn;

    Stage stage;
    UserProfile profile;
    int projectIndex;

    public AddColumnController(Stage stage, UserProfile profile, int projectIndex) {
        this.stage = stage;
        this.projectIndex = projectIndex;
        this.profile = profile;
    }

    @FXML
    public void initialize() {
        addColumn.setOnAction(event -> {
            new CreateColumn(columnName.getText(), projectIndex).doAction(profile);
            Authenticator.getInstance().save();
            new Display().displayStage(new WorkspaceController(stage, profile));
        });
    }

    @Override
    public void showStage(Pane root) {
        Scene scene = new Scene(root, 500, 300);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Add Column");
        stage.show();
    }
}
