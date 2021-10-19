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
        } else if(controller instanceof AddColumnController) {
            return  new FXMLLoader(getClass().getResource("../resources/view/AddColumnView.fxml"));
        } else if(controller instanceof AddTaskController) {
            return new FXMLLoader(getClass().getResource("../resources/view/AddTaskView.fxml"));
        } else if(controller instanceof RenameProjectController) {
            return new FXMLLoader(getClass().getResource("../resources/view/RenameProject.fxml"));
        } else if(controller instanceof  EditProfileController) {
            return new FXMLLoader(getClass().getResource("../resources/view/EditProfileView.fxml"));
        }else {
        }

         {
            return null;
        }
    }
}
