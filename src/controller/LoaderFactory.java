package controller;

import javafx.fxml.FXMLLoader;

public class LoaderFactory {
    public FXMLLoader getLoader(Controllable controller) {
        if(controller instanceof CreateProjectController) {
            return new FXMLLoader(getClass().getResource("../resources/view/CreateProjectView.fxml"));
        } else if (controller instanceof LoginController) {
            return new FXMLLoader(getClass().getResource("../resources/view/LoginView.fxml"));
        } else if (controller instanceof SignupController) {
            return new FXMLLoader(getClass().getResource("../resources/view/SignupView.fxml"));
        } else if (controller instanceof WorkspaceController) {
            return new FXMLLoader(getClass().getResource("../resources/view/WorkspaceView.fxml"));
        }  else {
            return null;
        }
    }
}
