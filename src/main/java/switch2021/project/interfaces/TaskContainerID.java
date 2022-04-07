package switch2021.project.interfaces;

import switch2021.project.model.Resource.Resource;
import switch2021.project.model.Task.Task;
import switch2021.project.model.Task.TaskType;

public interface TaskContainerID {
    Task createTask(String name, String description, double effortEstimate, TaskType type, Resource responsible);

}
