package switch2021.project.stores;

import lombok.Getter;
import lombok.Setter;
import switch2021.project.model.TaskStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

@Getter
@Setter
public class TaskStatusStore {

    private final List<String> taskList; // Review this atributte. The class need to have a list of Objects that are responsible.
    private List<TaskStatus> taskStatusList;

    public TaskStatusStore() {
        this.taskList = new ArrayList<>(); //Review
        this.taskStatusList = new ArrayList<>();
    }

    public TaskStatus createTaskStatus(String status) {
        return new TaskStatus(status);
    }

    public void populateDefault() {
        this.taskList.add("Planned"); //Review
        this.taskList.add("Running"); //Review
        this.taskList.add("Finished"); //Review
        this.taskList.add("Blocked"); //Review

        saveTaskStatus(new TaskStatus("Planned"));
        saveTaskStatus(new TaskStatus("Running"));
        saveTaskStatus(new TaskStatus("Finished"));
        saveTaskStatus(new TaskStatus("Blocked"));
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

    public TaskStatus getInitialStatus(){

        TaskStatus status = null;

        for (TaskStatus taskStatus : this.taskStatusList) {

            if (taskStatus.getDescription().equalsIgnoreCase("Planned")) {
                status = taskStatus;
            }

        }

        return status;
    }

    /**
     * Method to save and validate task types in the list.
     */

    public boolean saveTaskStatus(TaskStatus status) {

        boolean result = false;

        if(status != null) {

            result = true;

            for (int i = 0; i < this.taskStatusList.size(); i++) {

                if (validateNewStatusDescription(status)) {
                    status.setId_TaskStatus(id_TaskStatusGenerator());
                    this.taskStatusList.add(status);
                }

            }
        }


        return result;
    }

    private boolean validateNewStatusDescription(TaskStatus status) {

        boolean result = true;

        for (TaskStatus taskStatus : this.taskStatusList) {

            if (taskStatus.getDescription().equals(status.getDescription())) {
                result = false;
                break;
            }

        }

        return result;
    }

    /**
     * ID Generator.
     **/
    public int id_TaskStatusGenerator() {
        int id = 1;
        if (this.taskStatusList.size() > 0) {
            id = this.taskStatusList.get(taskStatusList.size() - 1).getId_TaskStatus() + 1;
        }
        return id;
    }



    /** Override **/
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TaskStatusStore)) return false;
        TaskStatusStore that = (TaskStatusStore) o;
        return Objects.equals(this.taskList, that.taskList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(taskList);
    }

}
