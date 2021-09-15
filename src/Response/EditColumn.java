package Response;

import SmartBoard.Column;
import SmartBoard.Project;
import SmartBoard.UserProfile;

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
