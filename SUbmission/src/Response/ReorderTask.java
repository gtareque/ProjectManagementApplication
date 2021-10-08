package Response;

import SmartBoard.Column;
import SmartBoard.Task;
import SmartBoard.UserProfile;

public class ReorderTask implements IResponse{
    int project;
    int column;
    int task;
    int to;
    public ReorderTask(int project, int column, int task, int to) {
        this.project =project;
        this.column = column;
        this.task = task;
        this.to = to;
    }
    @Override
    public void doAction(UserProfile profile) {
        Column temp = profile.getProject(project).getColumn(column);
        Task tempTask = temp.getTask(task);
        temp.removeTask(task);
        temp.addTask(task, temp.getTask(to));
        temp.removeTask(to);
        temp.addTask(to, tempTask);

    }
}
