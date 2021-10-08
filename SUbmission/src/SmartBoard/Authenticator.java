package SmartBoard;

import java.util.HashMap;
import java.util.Scanner;

public class Authenticator {
    public static Authenticator authenticator = null;

    private Authenticator() {

    }

    public static Authenticator getInstance() {
        if(authenticator == null) {
            authenticator = new Authenticator();
        }
        return  authenticator;
    }
    private HashMap<String, UserProfile> users = new HashMap<>();

    public void signUp(String userName, String password, String firstName, String lastName) {

        UserProfile profile = new UserProfile(userName, password, firstName, lastName);
        users.put(userName, profile);
    }

    public UserProfile authenticate(String userName, String password) {

        UserProfile profile = users.get(userName);
        if(password.equals(profile.getPassword())) {
            return profile;
        }
        return null;
    }




}
