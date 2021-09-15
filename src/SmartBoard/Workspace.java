package SmartBoard;

import Response.IResponse;
import SmartBoard.UserProfile;

public class Workspace {
    private UserProfile profile;

    public Workspace(UserProfile profile) {
        this.profile =profile;
    }

    public void modifyWorkspace(IResponse response) {
        response.doAction(profile);
    }

    public void displayWorkspace() {
        
    }


}
