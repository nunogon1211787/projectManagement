package switch2021.project.stores;

import lombok.Getter;
import switch2021.project.factory.TaskTypeFactoryInterface;
import switch2021.project.model.TaskType;
import java.util.ArrayList;
import java.util.List;

@Getter
public class TaskTypeStore {

    /**
     * Attributes
     */
    private List<TaskType> taskTypeList;
    private TaskTypeFactoryInterface taskTypeFactory;

    /**
     * Constructor
     */
    public TaskTypeStore(TaskTypeFactoryInterface taskTypeFactory) {
        this.taskTypeList = new ArrayList<>();
        this.taskTypeFactory = taskTypeFactory;
    }

    /**
     * Method to populate the class with default objects
     */
    public void populateDefault() {
        createAndAddTaskType("Meeting");
        createAndAddTaskType("Documentation");
        createAndAddTaskType("Design");
        createAndAddTaskType("Implementation");
        createAndAddTaskType("Testing");
        createAndAddTaskType("Deployment");
    }

    /**
     * Method to create TaskType
     */
    public boolean createAndAddTaskType(String description){

        if(getTypeByDescription(description) != null) {
            return false;
        } else {
            this.taskTypeList.add(taskTypeFactory.createTaskType(description));
            return true;
        }
    }

    /**
     * Methods to iterate with the list
     */
    public List<String> getTaskTypesDescription(){
        List<String> taskTypesDescription = new ArrayList<>();

        for (TaskType taskType : this.taskTypeList) {
            taskTypesDescription.add(taskType.getDescription().getText());
        }
        return taskTypesDescription;
    }

    public TaskType getTypeByDescription(String typeDescription) {
        TaskType type = null;

        for (TaskType taskType : this.taskTypeList) {
            if (taskType.hasDescription(typeDescription)) {
                type = taskType;
            }
        }
        return type;
    }
}
