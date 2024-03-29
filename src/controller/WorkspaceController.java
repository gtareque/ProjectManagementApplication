package controller;

import Response.DeleteProject;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Callback;
import model.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class WorkspaceController implements Controllable {
    private Stage stage;
    private UserProfile profile;
    private boolean newProject = false;

    public WorkspaceController(Stage stage, UserProfile profile, boolean newProject) {
        this.stage = stage;
        this.profile = profile;
        this.newProject = newProject;
    }
    public WorkspaceController(Stage stage, UserProfile profile) {
        this.stage = stage;
        this.profile = profile;

    }


    @FXML
    private MenuItem newProjectButton;

    @FXML
    private MenuItem addColumn;

    @FXML
    private MenuItem rename;

    @FXML
    private MenuItem defaultButton;

    @FXML
    private MenuItem unsetDefault;

    @FXML
    private MenuItem deleteProject;

    @FXML
    private Button logOut;

    @FXML
    private Button profileButton;

    @FXML
    private ImageView displayPhoto;

    @FXML
    private Label displayName;

    @FXML
    private TabPane projectTab;
    @FXML
    public void initialize() {
        setUpDisplayPhoto();
        setUpProjectTabs();

        if(newProject) {
            projectTab.getSelectionModel().selectLast();
        }
        if(profile.getDefaultProject() > 0){
            projectTab.getSelectionModel().select(profile.getDefaultProject());
        }
        defaultButton.setOnAction(event -> {
            profile.setDefaultProject(projectTab.getSelectionModel().getSelectedIndex());
            Authenticator.getInstance().save();
        });
        unsetDefault.setOnAction(event -> {
            profile.setDefaultProject(0);
            Authenticator.getInstance().save();
        });
        addColumn.setOnAction(event -> {
            int index = projectTab.getSelectionModel().getSelectedIndex();
            new Display().displayStage(new AddColumnController(stage, profile, index));

        });
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
            new Display().displayStage(new LoginController(stage));
        });

        rename.setOnAction(event -> {
            new Display().displayStage(new RenameProjectController(stage, profile, projectTab.getSelectionModel().getSelectedIndex()));
        });

        profileButton.setOnAction(event-> {
            new Display().displayStage(new EditProfileController(stage, profile));
        });



    }
    public void showStage(Pane root) {
        Scene scene = new Scene(root, 1000, 500);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Workspace");
        stage.show();
    }



    public void setUpDisplayPhoto() {
        displayName.setText(profile.getFirstName() +" " + profile.getLastName());
        if(profile.getPhoto() != null) {
            displayPhoto.setImage(profile.getPhoto());
        } else {
            try {
                BufferedImage bufferedImage = ImageIO.read(new File("C:/ProjectManagementApplication/src/resources/media/index.jpg"));
                WritableImage defaultPhoto = SwingFXUtils.toFXImage(bufferedImage, null);
                displayPhoto.setImage(defaultPhoto);
            } catch (Exception e) {
                System.out.println("File not found");
            }
        }

    }

    public void setUpProjectTabs() {
        for (int i = 0; i < profile.getProjects().size(); i++) {
            if(i == 0) {
                defaultButton.setDisable(false);
                unsetDefault.setDisable(false);
                addColumn.setDisable(false);
                deleteProject.setDisable(false);
                rename.setDisable(false);

            }
            String name = profile.getProject(i).getName();
            projectTab.getTabs().add(new Tab(name));
            /* CREATE THE GRID */
            Node node = loadColumns(i);
            projectTab.getTabs().get(i).setContent(node);

        }
    }



    public Node getColumn(String name, int columnIndex, int projectIndex) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../resources/view/ColumnHeader.fxml"));
        Callback<Class<?>, Object> controllerFactory = param -> {
            return new HeaderController(stage, name, columnIndex, projectIndex, profile);
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

    public ScrollPane loadColumns(int projectIndex) {
        ScrollPane scrollPane = new ScrollPane();
        Project project = profile.getProject(projectIndex);
        HBox grid = new HBox();
        for(int i = 0; i < project.columnSize(); i++) {
            Column c = project.getColumn(i);
            Node header = getColumn(c.getName(), i, projectIndex);
            grid.getChildren().add(header);
        }
        scrollPane.setContent(grid);
        return scrollPane;
    }



}
