
import java.util.HashMap;
import java.util.Scanner;

public class Authenticator {
    private HashMap<String, UserProfile> users = new HashMap<>();


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
