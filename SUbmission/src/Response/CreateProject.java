package Response;

import SmartBoard.UserProfile;

public class CreateProject implements IResponse {
    private String name;

    public CreateProject(String name) {
        this.name = name;
    }
    @Override
    public void doAction(UserProfile profile) {
        profile.addProject(name);
    }

    public String getName() {
        return name;
    }
}
