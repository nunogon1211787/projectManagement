package switch2021.project.applicationServices.iRepositories;

import switch2021.project.entities.valueObjects.vos.TaskID;

public interface ITaskIDFactory {
    TaskID createTaskID(TaskContainerID taskContainerID, String name);
}
