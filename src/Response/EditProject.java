package Response;

import SmartBoard.UserProfile;

public class EditProject implements IResponse {

    private String name;
    private int projectIndex;


    public EditProject(String name, int projectIndex) {
        this.name = name;
        this.projectIndex = projectIndex;
    }



    @Override
    public void doAction(UserProfile profile) {
        profile.getProject(projectIndex).setName(name);
    }


    public int getProjectIndex() {
        return projectIndex;
    }


    public String getName() {
        return name;
    }
}
