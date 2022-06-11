package switch2021.project.entities.factories.factoryInterfaces;

import switch2021.project.dtoModel.dto.TaskDTO;

import switch2021.project.applicationServices.iRepositories.TaskContainerID;
import switch2021.project.entities.valueObjects.vos.ResourceID;
import switch2021.project.entities.aggregates.Task.Task;



public interface ITaskFactory {

    Task createTask(TaskDTO taskDTO, ResourceID resourceId, TaskContainerID taskContainerID);

}
