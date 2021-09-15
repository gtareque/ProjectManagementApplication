package SmartBoard;

import java.time.LocalDate;
import java.util.ArrayList;

public class Column extends Project {


    ArrayList<Task> tasks = new ArrayList<>();

    public Column(String name) {
        super(name);
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
}
