package Response;

import SmartBoard.UserProfile;

public class EditProfile implements IResponse {
    private String firstName;
    private String lastName;
    private String photo;

    @Override
    public void doAction(UserProfile profile) {
        profile.setFirstName(firstName);
        profile.setLastName(lastName);
        profile.setPhoto(photo);
    }
}
