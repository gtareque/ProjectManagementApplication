package controller;

import Response.ChangeColumn;
import Response.CreateTask;
import Response.DeleteColumn;
import Response.ReorderTask;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import model.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.HashMap;

public class HeaderController {
    private Stage stage;
    private String nameGiven;
    private UserProfile profile;
    private int index;
    private int projectIndex;
    public HeaderController(Stage stage, String name, int columnIndex, int projectIndex, UserProfile profile) {
        this.nameGiven = name;
        this.stage = stage;
        this.profile = profile;
        this.index = columnIndex;
        this.projectIndex = projectIndex;
    }
    @FXML
    private MenuButton addTask;

    @FXML
    private Label name;
    @FXML
    private MenuItem newTask;

    @FXML
    private MenuItem deleteColumn;
    @FXML
    private VBox columnBox;


    @FXML
    public void initialize() {
        name.setText(nameGiven);
        columnBox.setSpacing(10);
        for(int i = 0; i < profile.getProject(projectIndex).getColumn(index).tasksSize(); i++) {
            Node node = getTaskCard(i);
            columnBox.getChildren().add(node);
        }
        newTask.setOnAction(event -> {

            new Display().displayStage(new AddTaskController(stage, profile, projectIndex, index));
        });

        deleteColumn.setOnAction(event -> {
            new DeleteColumn(projectIndex, index).doAction(profile);
            Authenticator.getInstance().save();
            new Display().displayStage(new WorkspaceController(stage, profile));
        });

        columnBox.setOnDragOver(new EventHandler<DragEvent>() {
            public void handle(DragEvent event) {

                if ( event.getGestureSource() != columnBox &&
                        event.getDragboard().hasFiles()) {

                    event.acceptTransferModes(TransferMode.MOVE);
                }

                event.consume();
            }
        });

        columnBox.setOnDragDropped((DragEvent event) -> {
            Dragboard db = event.getDragboard();
            if (db.hasFiles()) {

                try {
                    ObjectInputStream in = new ObjectInputStream(new FileInputStream(db.getFiles().get(0)));
                    HashMap<String, Integer> map = (HashMap<String, Integer>) in.readObject();

                    if(index != map.get("columnIndex")) {

                        new ChangeColumn(map.get("projectIndex"), map.get("columnIndex"), map.get("taskIndex"), index).doAction(profile);
                        Authenticator.getInstance().save();
                        new Display().displayStage(new WorkspaceController(stage, profile));
                    } else {

                    }


                } catch (Exception e) {
                    e.printStackTrace();
                }
                event.setDropCompleted(true);
            } else {
                event.setDropCompleted(false);
            }
            event.consume();

        });

    }


    public Node getTaskCard(int taskIndex) {
        Task task = profile.getProject(projectIndex).getColumn(index).getTask(taskIndex);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../resources/view/TaskView.fxml"));
        Callback<Class<?>, Object> controllerFactory = param -> {
            return new TaskController(stage, profile, projectIndex, index, taskIndex);
        };
        loader.setControllerFactory(controllerFactory);
        Node node = null;
        try {
            node = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return node;
    }




}
