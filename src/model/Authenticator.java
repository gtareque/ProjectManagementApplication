package model;
import javafx.scene.image.WritableImage;
import java.io.*;
import java.util.HashMap;
/**
 * The Authenticator class acts as mediator between the data base and model.
 * Has functions to sign up, authenticate and update database.
 * Follows the singleton design pattern and has global access
 */
public class Authenticator {

    public static Authenticator authenticator = null;       /* global instance */
    private HashMap<String, UserProfile> users = null;      /* users stored */


    /**
     * Class constructor
     */
    private Authenticator() {

    }

    /**
     * Returns the global instance to the database.
     * Check to see if any previous instances has been created
     * An instance is only created if no previous instances has been made
     * Otherwise the old instance is returned.
     * User the load() function to input the data from file
     *
     */
    public static Authenticator getInstance() {
        if(authenticator == null) {

            authenticator = new Authenticator();
            authenticator.load();
        }
        return  authenticator;
    }

    /**
     * Returns boolean based on the success of the process. Parameters are user information.
     * The function has two method signatures and it's use is dependent on the data user provides.
     *
     * @param  userName  the unique user name. has to be unique otherwise signup is not complete
     * @param  password the password of the user must be of given length or longer
     * @param firstName the first name of the user
     * @param lastName the last name of the user
     * @return      boolean value: true if success otherwise false
     *
     */
    public boolean signUp(String userName, String password, String firstName, String lastName) {
        /* check if username exists */
        if(users.keySet().contains(userName)) {
            return false;
        }
        UserProfile profile = new UserProfile(userName, password, firstName, lastName);
        /* put data in the data base */
        users.put(userName, profile);
        save();
        return true;

    }


    /**
     * Returns boolean based on the success of the process. Parameters are user information.
     * The function has two method signatures and it's use is dependent on the data user provides.
     *
     * @param  userName  the unique user name. has to be unique otherwise signup is not complete
     * @param  password the password of the user must be of given length or longer
     * @param firstName the first name of the user
     * @param lastName the last name of the user
     * @param photo is the profile picture
     * @return      boolean value: true if success otherwise false
     *
     */
    public boolean signUp(String userName, String password, String firstName, String lastName, WritableImage photo) {
        /* check if username exists */
        if(users.keySet().contains(userName)) {
            return false;
        }
        UserProfile profile = new UserProfile(userName, password, firstName, lastName, photo);
        users.put(userName, profile);
        save();
        return true;
    }

    /**
     * Returns the UserProfile with user data if authentication is successful.
     * Returns null if the password/username combo is not correct
     *
     * @param  userName  the unique user name. has to be unique otherwise signup is not complete
     * @param  password the password of the user must be of given length or longer
     * @return the user profile
     *
     */
    public UserProfile authenticate(String userName, String password) {

        UserProfile profile = users.get(userName);
        if(password.equals(profile.getPassword())) {
            return profile;
        }
        return null;
    }


    /**
     * Function reads data from users.dat file
     * If file does not exist it statrts a new hasmap on it's own
     *

     * @return      boolean value: true if success otherwise false
     *
     */
    public void load() {
        try {
            /* take data from users.dat */
            ObjectInputStream in = new ObjectInputStream(new FileInputStream("users.dat"));
            users = new HashMap<String, UserProfile>();
            users = (HashMap<String, UserProfile>) in.readObject();
            in.close();

        }
        /* exception handling */
        catch (FileNotFoundException e){
            /* first use of the app therefore, no data found */
            System.err.println(("File cannot be read "));
            users = new HashMap<>();
        } catch (IOException i) {
            i.printStackTrace();

        } catch (ClassNotFoundException c) {
            users = new HashMap<>();

        }
    }

    /**
     * Function saves to users.dat file which acts as the database
     *
     *
     */
    public void save() {
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("users.dat"));
            out.writeObject(users);
            out.close();

        } catch (IOException i) {
            i.printStackTrace();
        }
    }




}
