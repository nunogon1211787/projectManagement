package switch2021.project.stores;

import lombok.Getter;
import switch2021.project.factory.TaskStatusFactory;
import switch2021.project.immutable.TaskStatus;
import java.util.ArrayList;
import java.util.List;

@Getter
public class TaskStatusStore {

    /**
     * Attributes
     */
    private final List<TaskStatus> taskStatusList;
    private TaskStatusFactory taskStatusFactory;


    /**
     * Constructor
     */
    public TaskStatusStore(TaskStatusFactory tsf) {
        this.taskStatusList = new ArrayList<>();
        this.taskStatusFactory = tsf;
    }


    /**
     * Methods to create and add an object that this class are responsible
     */
    public boolean createAndAddTaskStatus(String status) {

        if(getTaskStatusByDescription(status) != null) {
            return false;
        } else {
            this.taskStatusList.add(taskStatusFactory.createAndAddTaskStatus(status));
            return true;
        }
    }


    /**
     * Methods to populate the Store
     */
    public void populateDefault() {
        if(this.taskStatusList.size() != 0) {
            throw new IllegalArgumentException ("Task Status Store is not empty!");
        }
        createAndAddTaskStatus("Planned");
        createAndAddTaskStatus("Running");
        createAndAddTaskStatus("Finished");
        createAndAddTaskStatus("Blocked");
    }


    /**
     * Methods to iterate with the list
     */
    public TaskStatus getTaskStatusByDescription(String description){
        TaskStatus result = null;

        for (TaskStatus status : this.taskStatusList) {
            if (status.hasDescription(description)) {
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
}
