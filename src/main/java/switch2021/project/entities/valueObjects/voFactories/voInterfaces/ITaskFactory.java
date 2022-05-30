package switch2021.project.entities.valueObjects.voFactories.voInterfaces;

import switch2021.project.dtoModel.dto.TaskDTO;

import switch2021.project.applicationServices.iRepositories.TaskContainerID;
import switch2021.project.entities.valueObjects.vos.ResourceIDReeng;
import switch2021.project.entities.aggregates.Task.TaskReeng;



public interface ITaskFactory {

    TaskReeng createTask(TaskDTO taskDTO, ResourceIDReeng resourceId, TaskContainerID taskContainerID);

}
