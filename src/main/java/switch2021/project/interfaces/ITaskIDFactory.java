package switch2021.project.interfaces;

import switch2021.project.model.Task.TaskID;

public interface ITaskIDFactory {
    TaskID createTaskID(TaskContainerID taskContainerID, String name);
}
