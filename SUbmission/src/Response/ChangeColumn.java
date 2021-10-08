package Response;

import SmartBoard.Project;
import SmartBoard.UserProfile;

public class ChangeColumn implements IResponse {
    private int projectIndex, columnIndex, taskIndex, toIndex;
    public ChangeColumn(int projectIndex, int columnIndex, int taskIndex, int toIndex) {
        this.projectIndex =projectIndex;
        this.columnIndex = columnIndex;
        this.taskIndex = taskIndex;
        this.toIndex = toIndex;
    }
    @Override
    public void doAction(UserProfile profile) {
        Project project = profile.getProject(projectIndex);
        project.getColumn(toIndex).addTask(project.getColumn(columnIndex).getTask(taskIndex));
        project.getColumn(columnIndex).removeTask(taskIndex);
    }
}
