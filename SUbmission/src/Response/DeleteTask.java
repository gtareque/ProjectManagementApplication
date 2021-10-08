package Response;

import SmartBoard.UserProfile;

public class DeleteTask extends DeleteColumn {
    private int taskIndex;
    public DeleteTask(int projectIndex, int columnIndex, int taskIndex) {
        super(projectIndex, columnIndex);
    }
    @Override
    public void doAction(UserProfile profile) {
        profile.getProject(getProjectIndex()).getColumn(getColumnIndex()).removeTask(taskIndex);
    }
}
