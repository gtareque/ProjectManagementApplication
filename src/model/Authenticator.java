package model;

import javafx.scene.image.WritableImage;

import java.io.*;
import java.util.HashMap;

public class Authenticator {
    public static Authenticator authenticator = null;
    private HashMap<String, UserProfile> users = null;

    private Authenticator() {

    }

    public static Authenticator getInstance() {
        if(authenticator == null) {

            authenticator = new Authenticator();
            authenticator.load();
        }
        return  authenticator;
    }


    public void signUp(String userName, String password, String firstName, String lastName) {

        UserProfile profile = new UserProfile(userName, password, firstName, lastName);
        users.put(userName, profile);
        save();

    }

    public void signUp(String userName, String password, String firstName, String lastName, WritableImage photo) {

        UserProfile profile = new UserProfile(userName, password, firstName, lastName, photo);
        users.put(userName, profile);
        save();

    }

    public UserProfile authenticate(String userName, String password) {

        UserProfile profile = users.get(userName);
        if(password.equals(profile.getPassword())) {
            return profile;
        }
        return null;
    }

    public void load() {
        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream("users.dat"));
            users = new HashMap<String, UserProfile>();
            users = (HashMap<String, UserProfile>) in.readObject();
            in.close();

        } catch (FileNotFoundException e){
            users = new HashMap<>();
        } catch (IOException i) {
            i.printStackTrace();

        } catch (ClassNotFoundException c) {
            users = new HashMap<>();

        }
        System.out.println(users);

    }
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
