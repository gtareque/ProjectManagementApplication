package model;

import javafx.scene.image.WritableImage;

import java.io.Serializable;
import java.util.ArrayList;

public class UserProfile implements Serializable {


    private String password;
    private String firstName;
    private String lastName ;
    private WritableImage photo;
    private String userName;

    ArrayList<Project> projects = new ArrayList<>();

    public Project getCurrentProjectOnDisplay() {
        return currentProjectOnDisplay;
    }

    Project currentProjectOnDisplay;



    public UserProfile(String userName, String password, String firstName, String lastName, WritableImage img ) {
        this.userName= userName;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.photo = img;

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

    public WritableImage getPhoto() {
        return photo;
    }

    public void setPhoto(WritableImage photo) {
        this.photo = photo;
    }


    public void addProject(String name){
        projects.add(new Project(name));
        currentProjectOnDisplay= projects.get(0);
   }

    public void removeProject(int projectIndex) {
        projects.remove(projectIndex);
        projects.trimToSize();
    }


    public Project getProject(int index) {
        return projects.get(index);
    }

    public ArrayList<Project> getProjects() {
        return  projects;
    }

}
