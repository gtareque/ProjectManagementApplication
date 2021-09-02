import java.util.ArrayList;

public class UserProfile {


    private String password;
    private String firstName;
    private String lastName ;
    private String photo = "default photo";
    private String userName;


    private ArrayList<Column> columns = new ArrayList<>();

    public UserProfile(String userName, String password, String firstName, String lastName, String photo) {
        this.userName= userName;
        this.password = password;
        this.firstName= firstName;
        this.lastName = lastName;
        this.photo = photo;

    }

    public UserProfile(String userName, String password, String firstName, String lastName) {
        this.userName= userName;
        this.password = password;
        this.firstName= firstName;
        this.lastName = lastName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
    public ArrayList<Column> getColumns() {
        return columns;
    }








}
