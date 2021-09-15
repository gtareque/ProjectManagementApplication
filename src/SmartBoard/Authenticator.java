package SmartBoard;

import java.util.HashMap;
import java.util.Scanner;

public class Authenticator {
    private HashMap<String, UserProfile> users = new HashMap<>();

    public void signUp() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter User Name:");
        String userName = sc.nextLine();
        System.out.println("Enter password");
        String password = sc.nextLine();
        System.out.println("Enter first name");
        String firstName = sc.nextLine();
        System.out.println("Enter last name");
        String lastName = sc.nextLine();
        UserProfile profile = new UserProfile(userName, password, firstName, lastName);
        users.put(userName, profile);
    }

    public UserProfile authenticate() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter USer Name:");
        String userName = sc.nextLine();
        System.out.println("Enter password");
        String password = sc.nextLine();
        UserProfile profile = users.get(userName);
        return profile;
    }




}
