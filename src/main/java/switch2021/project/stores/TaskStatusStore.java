package switch2021.project.stores;

import lombok.Getter;
import switch2021.project.model.TaskStatus;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

@Getter
public class TaskStatusStore {

    /**
     * Attributes
     */
//    private final List<String> taskList; // Review this atributte. The class need to have a list of Objects that are responsible.
    private List<TaskStatus> taskStatusList;


    /**
     * Constructor
     */
    public TaskStatusStore() {
//        this.taskList = new ArrayList<>(); //Review
        this.taskStatusList = new ArrayList<>();
    }

    /**
     * Methods to create an object that this class are responsible
     */
    public boolean createTaskStatus(String status) {
        TaskStatus newStatus = new TaskStatus(status);

        return saveTaskStatus(newStatus);
    }

    public void populateDefault() {
//        this.taskList.add("Planned"); //Review
//        this.taskList.add("Running"); //Review
//        this.taskList.add("Finished"); //Review
//        this.taskList.add("Blocked"); //Review

        saveTaskStatus(new TaskStatus("Planned"));
        saveTaskStatus(new TaskStatus("Running"));
        saveTaskStatus(new TaskStatus("Finished"));
        saveTaskStatus(new TaskStatus("Blocked"));
    }

    /**
     * Methods to iterate with the list
     */
//    public String getTaskStatusDescription(String description) {
//        String result = "Status not found";
//        for(String i : taskList) {
//            if(i.toLowerCase(Locale.ROOT).equals(description.trim().toLowerCase(Locale.ROOT))) {
//                result = i;
//            }
//        }
//        return result;
//    }

    public TaskStatus getTaskStatusByDescription(String descript){
        TaskStatus result = null;

        for (TaskStatus status : this.taskStatusList) {
            if (status.hasDescription(descript)) {
                result = status;
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

    public List<String> getTaskStatusNames(){
        List<String> taskStatusNames = new ArrayList<>();

        for (TaskStatus taskStatus : this.taskStatusList) {
            taskStatusNames.add(taskStatus.getDescription());
        }
        return taskStatusNames;
    }

    /**
     * Method to save and validate task status in the list
     */
    public boolean saveTaskStatus(TaskStatus status) {
        boolean result = false;

        if(status != null) {
            result = true;
            if(this.taskStatusList.size() != 0) {
                for (int i = 0; i < this.taskStatusList.size(); i++) {
                    if (validateNewStatusDescription(status)) {
                        status.setIDTaskStatus(idTaskStatusGenerator());
                        this.taskStatusList.add(status);
                    }
                }
            } else {
                status.setIDTaskStatus(idTaskStatusGenerator());
                this.taskStatusList.add(status);
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
     * ID Generator
     **/
    public int idTaskStatusGenerator() {
        int id = 1;
        if (this.taskStatusList.size() > 0) {
            id = this.taskStatusList.get(taskStatusList.size() - 1).getIdTaskStatus() + 1;
        }
        return id;
    }

//    /**
//     * Override
//     **/
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (!(o instanceof TaskStatusStore)) return false;
//        TaskStatusStore that = (TaskStatusStore) o;
//        return Objects.equals(this.taskList, that.taskList);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(taskList);
//    }
}
