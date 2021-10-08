package Response;

import model.UserProfile;

public class CreateColumn extends CreateProject {
    private int projectIndex;
    public CreateColumn(String name, int projectIndex) {
        super(name);
        this.projectIndex =projectIndex;
    }
    public void doAction(UserProfile profile) {
        profile.getProject(projectIndex).addColumn(getName());
    }

    public int getProjectIndex() {
        return projectIndex;
    }
}
