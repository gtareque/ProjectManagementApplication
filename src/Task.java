import java.time.LocalDate;
import java.util.ArrayList;

public class Task {
    private String description;
    private String name;
    private LocalDate dueDate;
    private ArrayList<Checklist> checklists = new ArrayList<>();

    public Task(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Task(String name, String description, LocalDate dueDate) {
        this.name = name;
        this.description = description;
        this.dueDate = dueDate;
    }

    public void addChecklist(String message) {
        checklists.add(new Checklist(message));
    }

    public void tickChecklist(int index) {
       checklists.get(index).setChecked();

    }
    public void removeChecklist(int index) {
        checklists.remove(index);
        checklists.trimToSize();

    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
