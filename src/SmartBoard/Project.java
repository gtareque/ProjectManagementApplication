package SmartBoard;

import java.util.ArrayList;

public class Project {
    private String name;



    private ArrayList<Column> columns = new ArrayList<>();
    public Project(String name) {
        this.name = name;
    }

    public void changeColumn(int task, int from, int to) {
        columns.get(from).removeTask(columns.get(from).getTask(task));
        columns.get(to).addTask(task);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addColumn(String name) {
        columns.add(new Column(name));
    }

    public void removeColumn(int index) {
        columns.remove(index);
        columns.trimToSize();
    }

    public Column getColumn(int index) {
        return columns.get(index);
    }

}
