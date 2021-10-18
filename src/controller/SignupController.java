package controller;

import javafx.embed.swing.SwingFXUtils;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import model.Authenticator;
import model.UserProfile;
import model.Workspace;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class SignupController implements Controllable {
    WritableImage img = null;
    @FXML
    private ImageView image;

    @FXML
    private Button signup;

    @FXML
    private TextField fName;

    @FXML
    private TextField lName;

    @FXML
    private TextField uName;

    @FXML
    private PasswordField password;
    private Stage stage;
    public SignupController(Stage stage) {
        this.stage = stage;

    }
    @FXML
    public void initialize() {
        try {
            BufferedImage bufferedImage = ImageIO.read(new File("C:/ProjectManagementApplication/src/resources/media/index.jpg"));
            WritableImage defaultPhoto = SwingFXUtils.toFXImage(bufferedImage, null);
            image.setImage(defaultPhoto);
        } catch (IOException e) {

        }
        signup.setOnAction(event -> {
            System.out.println(fName.getText());
            Authenticator authenticator = Authenticator.getInstance();
            if(img == null) {
                authenticator.signUp(uName.getText(), password.getText(), fName.getText(), lName.getText());
            } else {
                authenticator.signUp(uName.getText(), password.getText(), fName.getText(), lName.getText(), img);
            }
            UserProfile profile = authenticator.authenticate(uName.getText(), password.getText());
            Workspace workspace = new Workspace(profile);

            new Display().displayStage(new WorkspaceController(stage, profile));
        });

        image.onMouseClickedProperty().set((EventHandler<MouseEvent>) (MouseEvent t) -> {
            FileChooser fileChooser = new FileChooser();
            //Set extension filter
            FileChooser.ExtensionFilter extFilterJPG
                    = new FileChooser.ExtensionFilter("JPG files (*.JPG)", "*.JPG");
            FileChooser.ExtensionFilter extFilterjpg
                    = new FileChooser.ExtensionFilter("jpg files (*.jpg)", "*.jpg");
            FileChooser.ExtensionFilter extFilterPNG
                    = new FileChooser.ExtensionFilter("PNG files (*.PNG)", "*.PNG");
            FileChooser.ExtensionFilter extFilterpng
                    = new FileChooser.ExtensionFilter("png files (*.png)", "*.png");
            fileChooser.getExtensionFilters()
                    .addAll(extFilterJPG, extFilterjpg, extFilterPNG, extFilterpng);
            //Show open file dialog
            File file = fileChooser.showOpenDialog(null);
            try {
                BufferedImage bufferedImage = ImageIO.read(file);
                WritableImage img = SwingFXUtils.toFXImage(bufferedImage, null);
                image.setImage(img);
                this.img = img;



            } catch (IOException e) {

            }
        });

    }

    public void showStage(Pane root) {
        Scene scene = new Scene(root, 500, 600);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Welcome");
        stage.show();
    }
}
