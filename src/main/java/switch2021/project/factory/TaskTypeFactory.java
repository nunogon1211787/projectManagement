package switch2021.project.factory;

import switch2021.project.factoryInterface.TaskTypeFactoryInterface;
import switch2021.project.model.valueObject.TaskType;

public class TaskTypeFactory implements TaskTypeFactoryInterface {

    /**
     * Method to create and add a task type
     */
    public TaskType createTaskType(String task_type) {
        return new TaskType(task_type);
    }
}
