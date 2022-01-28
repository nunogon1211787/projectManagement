package switch2021.project.stores;

import lombok.Getter;
import switch2021.project.model.TaskStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Getter
public class TaskStatusStore {

    private final List<String> taskList;

    public TaskStatusStore() {
        this.taskList = new ArrayList<>();
    }

    public TaskStatus createTaskStatus(String status) {
        return new TaskStatus(status);
    }

    public void populateDefault() {
        this.taskList.add("Planned");
        this.taskList.add("Running");
        this.taskList.add("Finished");
        this.taskList.add("Blocked");
    }

    public String getTaskStatusByDescription(String description) {
        String result = "Status not found";
        for(String i : taskList) {
            if(i.toLowerCase(Locale.ROOT).equals(description.trim().toLowerCase(Locale.ROOT))) {
                result = i;
            }
        }
        return result;
    }
}
