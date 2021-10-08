package Response;

import SmartBoard.UserProfile;

public class DeleteColumn extends DeleteProject {
    int columnIndex;
    public DeleteColumn(int projectIndex, int columnIndex) {
        super(projectIndex);
        this.columnIndex = columnIndex;
    }

    @Override
    public void doAction(UserProfile profile) {
        profile.removeProject(getProjectIndex());
    }

    public int getColumnIndex() {
        return columnIndex;
    }
}
