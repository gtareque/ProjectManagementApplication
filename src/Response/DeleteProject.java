package Response;

import SmartBoard.UserProfile;

public class DeleteProject implements  IResponse {

    private int projectIndex;
    public DeleteProject(int projectIndex) {
        this.projectIndex = projectIndex;
    }

    @Override
    public void doAction(UserProfile profile) {
        profile.removeProject(projectIndex);
    }

    public int getProjectIndex() {
        return projectIndex;
    }
}
