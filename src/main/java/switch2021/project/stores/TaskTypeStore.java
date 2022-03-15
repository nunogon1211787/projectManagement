package switch2021.project.stores;

import lombok.Getter;
import switch2021.project.model.TaskType;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
public class TaskTypeStore {

    /**
     * Attributes
     */
    private List<TaskType> taskTypeList;

    /**
     * Constructor
     */
    public TaskTypeStore() {
        this.taskTypeList = new ArrayList<>();
    }

    /**
     * Method to populate the class with default objects
     */
    public void populateDefault() {
        saveTaskType(new TaskType("Meeting"));
        saveTaskType(new TaskType("Documentation"));
        saveTaskType(new TaskType("Design"));
        saveTaskType(new TaskType("Implementation"));
        saveTaskType(new TaskType("Testing"));
        saveTaskType(new TaskType("Deployment"));
    }

    /**
     * Method to create TaskType
     */
    public boolean createTaskType(String description){

        TaskType newType = new TaskType(description);

        return saveTaskType(newType);

    }

    /**
     * Methods to iterate with the list
     */
    public List<String> getTaskTypesDescription(){
        List<String> taskTypesDescription = new ArrayList<>();

        for (TaskType taskType : this.taskTypeList) {
            taskTypesDescription.add(taskType.getDescription().getDescriptionF());
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

    /**
     * Method to save and validate task types in the list
     */
    public boolean saveTaskType(TaskType type) {
        boolean result = false;

        if(type != null) {
            result = true;
            if(this.taskTypeList.size() != 0) {
                for (int i = 0; i < this.taskTypeList.size(); i++) {
                    if (validateNewTypeDescription(type)) {
                        type.setTypeID(idTaskTypeGenerator());
                        this.taskTypeList.add(type);
                    }
                }
            } else {
                    type.setTypeID(idTaskTypeGenerator());
                    this.taskTypeList.add(type);
            }
        }
        return result;
    }

    private boolean validateNewTypeDescription(TaskType type) {
        boolean result = true;

        for (TaskType taskType : this.taskTypeList) {
            if (taskType.getDescription().equals(type.getDescription())) {
                result = false;
                break;
            }
        }
        return result;
    }

    /**
     * ID Generator
     **/
    public int idTaskTypeGenerator() {
        int id = 1;
        if (this.taskTypeList.size() > 0) {
            id = this.taskTypeList.get(taskTypeList.size() - 1).getTypeID() + 1;
        }
        return id;
    }

    /**
     * Override
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TaskTypeStore that = (TaskTypeStore) o;
        return this.taskTypeList.equals(that.taskTypeList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(taskTypeList);
    }

}
