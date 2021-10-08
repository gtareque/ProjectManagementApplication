package Response;

import model.Task;
import model.UserProfile;

public class CreateTask extends CreateColumn {
    private String description;
    private int columnIndex;
    public CreateTask(String name, String description, int projectIndex, int columnIndex) {
        super(name, projectIndex);
        this.description = description;
        this.columnIndex = columnIndex;
    }

    @Override
    public void doAction(UserProfile profile) {
        Task task = new Task(getName(), description);
        profile.getProject(getProjectIndex()).getColumn(columnIndex).addTask(task);
    }
}
