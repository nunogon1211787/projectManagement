package switch2021.project.factoryInterface;

import switch2021.project.model.TaskType.TaskType;

public interface TaskTypeFactoryInterface {

    TaskType createTaskType(String description);

}
