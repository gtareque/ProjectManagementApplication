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


import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import model.Authenticator;
import model.UserProfile;


/**
 * This is the controller to the sign up view.
 * All the handler function has been stored as lambda expression.
 * Implements the Controllable interface that all the primary controllers have implemented.
 *
 */

public class SignupController implements Controllable {

    WritableImage img = null;       /* the profile picture as writeable image */

    /* FXML fxids */
    /**************************************************************************************/
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
    @FXML
    private Label errorMessage;
    @FXML
    private Button back;
    /*******************************************************************************/
    private Stage stage;        /* the view stage */

    /*
     * Stage assigning controller
     */
    public SignupController(Stage stage) {
        this.stage = stage;

    }

    /* The function initialize() is used by JAVAFX to connect all interactions with the controller.
     * @return void
     */
    @FXML
    public void initialize() {

        /* load the default photo */
        try {
            BufferedImage bufferedImage = ImageIO.read(new File("C:/ProjectManagementApplication/src/resources/media/index.jpg"));
            WritableImage defaultPhoto = SwingFXUtils.toFXImage(bufferedImage, null);
            image.setImage(defaultPhoto);
        } catch (IOException e) {
            e.printStackTrace();
        }

        /* when Create user is clicked */
        signup.setOnAction(signUpHandler);

        /* when the default image is clicked for upload */
        image.onMouseClickedProperty().set(uploadPhotoHandler);

        /* when the back button is pressed */
        back.setOnAction(t -> {
            new Display().displayStage(new LoginController(stage));
        });

    }



    /**
     * Function implemented by Controllable.
     * Used to display the stage
     *
     * @return void
     * @see stage
     */
    @Override
    public void showStage(Pane root) {
        Scene scene = new Scene(root, 500, 600);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Create a new user");
        stage.show();
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
        Matcher userNameMatcher = uNamePattern.matcher(uName.getText());

        /* check if empty or short */
        if(fName.getText().length() == 0) {
            errorMessage.setText("Please enter your first name");
            return false;
        }

        if(lName.getText().length() == 0) {
            errorMessage.setText("Please enter your last name");
            return false;
        }

        if(uName.getText().length() == 0) {
            errorMessage.setText("User name cannot be empty");
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
        if(!userNameMatcher.matches()) {

            errorMessage.setText("Username can only be alphanumeric");
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
    EventHandler<ActionEvent> signUpHandler = (event) -> {
        System.out.println(fName.getText());
        Authenticator authenticator = Authenticator.getInstance();
        boolean signUpSuccess = true;
        if(validateInput()) {       // validate input
            if (img == null) {      // no image uploaded
                if (!authenticator.signUp(uName.getText().toLowerCase(), password.getText(), fName.getText(), lName.getText())) {
                    errorMessage.setText("Username already exists");        // user name already exists
                    signUpSuccess = false;
                }
            } else {        // image uploaded
                if (!authenticator.signUp(uName.getText().toLowerCase(), password.getText(), fName.getText(), lName.getText(), img)) {
                    errorMessage.setText("Username already exists");
                    signUpSuccess = false;
                }
            }
            if(signUpSuccess) {
                /* take user to workspace */
                UserProfile profile = authenticator.authenticate(uName.getText(), password.getText());
                new Display().displayStage(new WorkspaceController(stage, profile));
            }
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
