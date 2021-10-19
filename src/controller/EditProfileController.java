package controller;

import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.Authenticator;
import model.UserProfile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EditProfileController implements Controllable {
    private Stage stage;
    private UserProfile profile = null;
    WritableImage img = null;
    public EditProfileController(Stage stage, UserProfile profile) {
        this.stage = stage;
        this.profile = profile;

    }

    @FXML
    private ImageView image;

    @FXML
    private TextField fName;

    @FXML
    private TextField lName;

    @FXML
    private PasswordField password;

    @FXML
    private Label errorMessage;

    @FXML
    private Button update;

    @FXML
    private Button back;

    @FXML
    public void initialize() {
        loadData();
        if(profile.getPhoto() != null) {
            image.setImage(profile.getPhoto());
        }
        update.setOnAction(updateHandler);
        back.setOnAction(actionEvent -> {
            new Display().displayStage(new WorkspaceController(stage, profile));
        });
        image.onMouseClickedProperty().set(uploadPhotoHandler);

    }

    @Override
    public void showStage(Pane root) {
        Scene scene = new Scene(root, 500, 600);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Create a new user");
        stage.show();
    }

    public void loadData() {
        fName.setText(profile.getFirstName());
        lName.setText(profile.getLastName());
        password.setText(profile.getPassword());
    }
    /**
     * Function used for validation. Function uses regex to check pattern
     * names can only be alphabetical
     * user name must can be alphenumerical at MOST
     * password needs a length of at least 6 characters
     * @return boolean returns true if inputs are ok
     */

    public boolean validateInput() {
        /* create patterns */
        String nameRegex = "[a-zA-Z]+";
        String userNameRegex = "[a-zA-Z0-9]+";
        Pattern pattern = Pattern.compile(nameRegex);
        Pattern uNamePattern = Pattern.compile((userNameRegex));
        Matcher firstNameMatcher = pattern.matcher(fName.getText());
        Matcher lastNameMatcher = pattern.matcher(lName.getText());


        /* check if empty or short */
        if(fName.getText().length() == 0) {
            errorMessage.setText("Please enter your first name");
            return false;
        }

        if(lName.getText().length() == 0) {
            errorMessage.setText("Please enter your last name");
            return false;
        }



        if(password.getText().length() < 6) {
            errorMessage.setText("Password must be at least 6 characters");
            return false;
        }

        /* match with pattern */
        if(!firstNameMatcher.matches()) {

            errorMessage.setText("First name can only have alphabets");
            return false;
        }
        if(!lastNameMatcher.matches()) {

            errorMessage.setText("Last name can only have alphabets");
            return false;
        }

        return true;


    }

    /* EVENT HANDLERS */
    /****************************************************************************************************/
    /**
     * FXML event handler for when create user button is clicked
     *
     * @param  event    The mouse event
     * @return      void
     */
    @FXML
    EventHandler<ActionEvent> updateHandler = (event) -> {

        Authenticator authenticator = Authenticator.getInstance();

        if(validateInput()) {       // validate input

            profile.setFirstName(fName.getText());
            profile.setLastName(lName.getText());
            profile.setPassword(password.getText());
            if(img != null) {
                profile.setPhoto(img);
            }

            authenticator.save();
            new Display().displayStage(new WorkspaceController(stage, profile));
        }

    };


    /**
     * FXML event handler for uploading the photo. Makes sure the photos are either jpg or png.
     *
     * @param  t    The mouse event
     * @return      void
     */
    @FXML
    EventHandler<MouseEvent> uploadPhotoHandler = (MouseEvent t) -> {
        FileChooser fileChooser = new FileChooser();
        /* Set the extensions */
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
        /* File dialog box */
        File file = fileChooser.showOpenDialog(null);

        /* read the file */
        try {
            BufferedImage bufferedImage = ImageIO.read(file);
            WritableImage img = SwingFXUtils.toFXImage(bufferedImage, null);
            image.setImage(img);
            this.img = img;
        } catch (IOException e) {
            errorMessage.setText("Upload failed");
        } catch (IllegalArgumentException e) {
            errorMessage.setText("No photo found");
        }
    };
}
