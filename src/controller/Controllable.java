package controller;

import javafx.fxml.FXML;
import javafx.scene.layout.Pane;


public interface Controllable {
    @FXML
    public void initialize();

    public void showStage(Pane root);
}
