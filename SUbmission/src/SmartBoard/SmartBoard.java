package SmartBoard;

import java.util.Scanner;

public class SmartBoard {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Authenticator authenticator = new Authenticator();
        authenticator.signUp();
        UserProfile profile = authenticator.authenticate();
        Workspace currentSpace = new Workspace(profile);



    }
}
