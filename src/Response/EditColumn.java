package Response;

import model.Column;
import model.Project;
import model.UserProfile;

public class EditColumn extends EditProject  {
    private int columnIndex;

    public EditColumn(int projectIndex, int columnIndex, String name) {
        super(name, projectIndex);
        this.columnIndex = columnIndex;
    }


    @Override
    public void doAction(UserProfile profile) {

        Project project = profile.getProject(getProjectIndex());
        Column column = project.getColumn(columnIndex);
        column.setName(getName());

    }

    public int getColumnIndex() {
        return columnIndex;
    }
}
