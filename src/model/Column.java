package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

public class Column implements Serializable {

    private String name;
    ArrayList<Task> tasks = new ArrayList<>();

    public Column(String name) {
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addTask(Task task) {
        tasks.add(task);
    }


    public Task getTask(int index) {
        return tasks.get(index);
    }

    public void addTask(int index, Task task) {
        tasks.add(index, task);
    }
    public void removeTask(int task) {
        tasks.remove(task);
        tasks.trimToSize();
    }
    public int tasksSize() {
        return tasks.size();
    }

    public void swapTask(int from, int to) {
        Collections.swap(tasks,from, to);
    }
    public String getName() {
        return name;
    }


}
