package Response;

import model.UserProfile;

public class EditProfile implements IResponse {
    private String firstName;
    private String lastName;


    @Override
    public void doAction(UserProfile profile) {
        profile.setFirstName(firstName);
        profile.setLastName(lastName);

    }
}
