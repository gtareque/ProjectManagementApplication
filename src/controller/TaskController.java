package controller;

import Response.ChangeColumn;
import Response.ReorderTask;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.*;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import model.Authenticator;
import model.Column;
import model.Task;
import model.UserProfile;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class TaskController {



    private Stage stage;
    UserProfile profile;
    private int projectIndex, columnIndex, taskIndex;
    private Task task;

    public TaskController(Stage stage, UserProfile profile, int projectIndex, int columnIndex, int taskIndex) {
        this.projectIndex = projectIndex;
        this.profile = profile;
        this. columnIndex = columnIndex;
        this.taskIndex = taskIndex;
        this.stage = stage;
        this.task = profile.getProject(projectIndex).getColumn(columnIndex).getTask(taskIndex);

    }

    @FXML
    private Label name;

    @FXML
    private Button edit;

    @FXML
    private Button delete;

    @FXML
    private Pane taskCard;

    @FXML
    public void initialize() {
        taskCard.setOnDragDetected((MouseEvent event) -> {
            System.out.println("setOnDragDetected");
            Dragboard db = taskCard.startDragAndDrop(TransferMode.MOVE);
            ClipboardContent content = new ClipboardContent();
            HashMap<String, Integer> data = new HashMap<>();
            data.put("projectIndex", projectIndex);
            data.put("columnIndex", columnIndex);
            data.put("taskIndex", taskIndex);

            File file = new File("temp.dat");
            FileOutputStream f;
            String string = "";
            ArrayList<File> files = new ArrayList<>();
            try {
                f = new FileOutputStream(file);
                ObjectOutputStream out = new ObjectOutputStream(f);
                out.writeObject(data);
                files.add(file);
            } catch (Exception e) {
                e.printStackTrace();
            }

            content.putFiles(files);

            db.setContent(content);
        });

        taskCard.setOnMouseDragged((MouseEvent event) -> {
            event.setDragDetect(true);
        });



        taskCard.setOnDragOver(new EventHandler<DragEvent>() {
            public void handle(DragEvent event) {
                if (event.getGestureSource() != taskCard && event.getDragboard().hasFiles()) {
                    event.acceptTransferModes(TransferMode.MOVE);

                }

                event.consume();
            }
        });

        taskCard.setOnDragDropped((DragEvent event) -> {
            Dragboard db = event.getDragboard();
            if (db.hasFiles()) {
                System.out.println("setOnDragDropped: TaskCard");
                try {


                    ObjectInputStream in = new ObjectInputStream(new FileInputStream(db.getFiles().get(0)));
                    HashMap<String, Integer> map = (HashMap<String, Integer>) in.readObject();
                    if(columnIndex == map.get("columnIndex")) {
                        new ReorderTask(map.get("projectIndex"), map.get("columnIndex"), map.get("taskIndex"), taskIndex).doAction(profile);
                        Authenticator.getInstance().save();
                        new Display().displayStage(new WorkspaceController(stage, profile));
                    } else {
                        new ChangeColumn(map.get("projectIndex"), map.get("columnIndex"), map.get("taskIndex"), columnIndex).doAction(profile);
                        Authenticator.getInstance().save();
                        new Display().displayStage(new WorkspaceController(stage, profile));
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
        name.setText(task.getName());
    };


}
