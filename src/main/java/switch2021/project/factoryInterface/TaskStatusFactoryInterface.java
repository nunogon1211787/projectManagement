package switch2021.project.factoryInterface;

import switch2021.project.model.valueObject.TaskStatus;

public interface TaskStatusFactoryInterface {
    TaskStatus createTaskStatus(String status);
}
