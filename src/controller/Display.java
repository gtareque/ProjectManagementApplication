package controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.VBox;
import javafx.util.Callback;

import java.io.IOException;

public class Display {


    public void displayStage(Controllable controller) {
        FXMLLoader loader = new LoaderFactory().getLoader(controller);
        Callback<Class<?>, Object> controllerFactory = param -> {
            return controller;
        };
        loader.setControllerFactory(controllerFactory);
        VBox root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Controllable control = loader.getController();
        control.showStage(root);
    }
}
