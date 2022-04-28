package switch2021.project.factoryInterface;

import switch2021.project.model.Resource.Resource;
import switch2021.project.model.Task.Task;
import switch2021.project.model.Task.TaskTypeEnum;

public interface TaskFactoryInterface {

    Task createTask(String name, String description, double effortEstimate, TaskTypeEnum type, Resource responsible);

}
