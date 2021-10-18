package Response;

import model.Column;
import model.Task;
import model.UserProfile;

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
        System.out.println(to);
        Column temp = profile.getProject(project).getColumn(column);
        temp.swapTask(task, to);

    }
}
