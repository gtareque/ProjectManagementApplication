import java.time.LocalDate;
import java.util.ArrayList;

public class Column {
    private String name;

    ArrayList<Task> tasks = new ArrayList<>();

    public void addTask(String name, String description) {
        tasks.add(new Task(name, description));
    }
}
