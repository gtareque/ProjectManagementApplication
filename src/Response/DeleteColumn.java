package Response;

import model.UserProfile;

public class DeleteColumn extends DeleteProject {
    int columnIndex;
    public DeleteColumn(int projectIndex, int columnIndex) {
        super(projectIndex);
        this.columnIndex = columnIndex;
    }

    @Override
    public void doAction(UserProfile profile) {
        profile.getProject(getProjectIndex()).removeColumn(columnIndex);
    }

    public int getColumnIndex() {
        return columnIndex;
    }
}
