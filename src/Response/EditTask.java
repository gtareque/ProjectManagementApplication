package Response;

import model.Column;
import model.Project;
import model.Task;
import model.UserProfile;

public class EditTask extends EditColumn {
    private int taskIndex;
    private String description;
    public EditTask(int projectIndex, int columnIndex, int taskIndex, String name, String description) {
        super(projectIndex, columnIndex, name);
        this.taskIndex = taskIndex;
        this.description = description;
    }

    @Override
    public void doAction(UserProfile profile) {
        Project project = profile.getProject(getProjectIndex());
        Column column = project.getColumn(getColumnIndex());
        Task task = column.getTask(taskIndex);
        task.setName(getName());
        task.setDescription(description);

    }

}
