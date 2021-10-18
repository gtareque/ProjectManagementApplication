package model;

import java.io.Serializable;
import java.util.ArrayList;

public class Project implements Serializable {
    private String name;



    private ArrayList<Column> columns = new ArrayList<>();
    public Project(String name) {
        this.name = name;
    }



    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
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

    public int columnSize() {
        return columns.size();
    }

}
