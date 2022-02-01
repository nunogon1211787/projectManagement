package switch2021.project.stores;

import switch2021.project.model.TaskType;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TaskTypeStore {

    /**
     * Atributtes.
     */

    private List<TaskType> taskTypeList;

    /**
     * Constructor
     */

    public TaskTypeStore() {
        this.taskTypeList = new ArrayList<>();
    }

    /**
     * Method to populate the class with default objects.
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
     * Method to create TaskType.
     */

    public boolean createTaskType(String name){

        TaskType newType = new TaskType(name);

        return saveTaskType(newType);

    }


    /**
     * Methods to iterate with the list.
     */

    public List<String> getTaskTypesNames(){

        List<String> taskTypesNames = new ArrayList<>();

        for (TaskType taskType : this.taskTypeList) {

            taskTypesNames.add(taskType.getName());

        }

        return taskTypesNames;

    }

    public TaskType getTypeByName(String typeName) {

        TaskType type = null;

        for (TaskType taskType : this.taskTypeList) {

            if (taskType.hasName(typeName)) {
                type = taskType;
            }

        }

        return type;
    }

    /**
     * Method to save and validate task types in the list.
     */

    public boolean saveTaskType(TaskType type) {

        boolean result = false;

        if(type != null) {

            result = true;

            if(this.taskTypeList.size() != 0) {

                for (int i = 0; i < this.taskTypeList.size(); i++) {

                    if (validateNewTypeName(type)) {
                        type.setType_ID(id_TaskTypeGenerator());
                        this.taskTypeList.add(type);
                    }

                }

            } else {

                    type.setType_ID(id_TaskTypeGenerator());
                    this.taskTypeList.add(type);

            }

        }


        return result;
    }

    private boolean validateNewTypeName(TaskType type) {

        boolean result = true;

        for (TaskType taskType : this.taskTypeList) {

            if (taskType.getName().equals(type.getName())) {
                result = false;
                break;
            }

        }

        return result;
    }

    /**
     * ID Generator.
     **/
    public int id_TaskTypeGenerator() {
        int id = 1;
        if (this.taskTypeList.size() > 0) {
            id = this.taskTypeList.get(taskTypeList.size() - 1).getType_ID() + 1;
        }
        return id;
    }

    /**
     * Override methods.
     */


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TaskTypeStore that = (TaskTypeStore) o;
        return Objects.equals(taskTypeList, that.taskTypeList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(taskTypeList);
    }
}
