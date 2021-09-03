import java.util.ArrayList;

public class Project {
    private String name;

    public ArrayList<Column> getColumns() {
        return columns;
    }

    private ArrayList<Column> columns = new ArrayList<>();
    public Project(String name) {
        this.name = name;
    }

}
