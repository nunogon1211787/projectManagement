package switch2021.project.entities.factories.factoryInterfaces;

import switch2021.project.dtoModel.dto.TaskDTO;

import switch2021.project.applicationServices.iRepositories.TaskContainerID;
import switch2021.project.entities.valueObjects.vos.ResourceIDReeng;
import switch2021.project.entities.aggregates.Task.Task;



public interface ITaskFactory {

    Task createTask(TaskDTO taskDTO, ResourceIDReeng resourceId, TaskContainerID taskContainerID);

}
