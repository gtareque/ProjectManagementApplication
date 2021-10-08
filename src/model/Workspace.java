package model;

import Response.IResponse;
import controller.WorkspaceController;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;

public class Workspace {
    private UserProfile profile;

    public Workspace(UserProfile profile) {
        this.profile =profile;
    }

    public void modifyWorkspace(IResponse response) {
        response.doAction(profile);
    }

    public void loadWorkspace(Stage stage) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../resources/view/WorkspaceView.fxml"));
        Callback<Class<?>, Object> controllerFactory = param -> {
            return new WorkspaceController(stage, profile);
        };
        loader.setControllerFactory(controllerFactory);
        VBox root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        controller.WorkspaceController workspaceController = loader.getController();
        workspaceController.showStage(root);
    }

    public void logout() {
        profile = null;
    }


}
