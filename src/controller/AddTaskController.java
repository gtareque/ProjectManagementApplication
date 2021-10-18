package controller;

import Response.ChangeColumn;
import Response.CreateTask;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.Authenticator;
import model.UserProfile;

public class AddTaskController implements Controllable {

    private Stage stage;
    private UserProfile profile;
    private int projectIndex;
    private int columnIndex;
    @FXML
    private TextField name;

    @FXML
    private TextField description;

    @FXML
    private Button addTask;
    public AddTaskController(Stage stage, UserProfile profile, int projectIndex, int columnIndex) {
        this.stage = stage;
        this. profile = profile;
        this. projectIndex = projectIndex;
        this.columnIndex = columnIndex;
    }
    @FXML
    public void initialize() {
        addTask.setOnAction(event -> {
            new CreateTask(name.getText(), description.getText(), projectIndex, columnIndex ).doAction(profile);
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
