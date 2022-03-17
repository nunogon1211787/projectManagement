package switch2021.project.stores;

import lombok.Getter;
import switch2021.project.immutable.TaskStatus;
import java.util.ArrayList;
import java.util.List;

@Getter
public class TaskStatusStore {

    /**
     * Attributes
     */
    private List<TaskStatus> taskStatusList;


    /**
     * Constructor
     */
    public TaskStatusStore() {
        this.taskStatusList = new ArrayList<>();
    }


    /**
     * Methods to create and add an object that this class are responsible
     */
    public boolean createAndSaveTaskStatus(String status) {
       TaskStatus ts = new TaskStatus(status);

        if(getTaskStatusByDescription(status) != null) {
            throw new IllegalArgumentException("This Task Status already exist!");
        } else {
            this.taskStatusList.add(ts);
            return true;
        }
    }


    /**
     * Methods to populate the Store
     */
    public void populateDefault() {
        createAndSaveTaskStatus("Planned");
        createAndSaveTaskStatus("Running");
        createAndSaveTaskStatus("Finished");
        createAndSaveTaskStatus("Blocked");
    }


    /**
     * Methods to iterate with the list
     */
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
            if (taskStatus.hasDescription("Planned")) {
                status = taskStatus;
            }
        }
        return status;
    }

    public List<String> getTaskStatusDescriptions(){
        List<String> taskStatusDescriptions = new ArrayList<>();

        for (TaskStatus taskStatus : this.taskStatusList) {
            taskStatusDescriptions.add(taskStatus.getDescription().getText());
        }
        return taskStatusDescriptions;
    }


//    /**
//     * Override
//     **/
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
