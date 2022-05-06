package switch2021.project.factoryInterface;

import switch2021.project.dto.TaskDTO;

import switch2021.project.interfaces.TaskContainerID;
import switch2021.project.model.Resource.ResourceIDReeng;
import switch2021.project.model.Task.TaskReeng;



public interface TaskFactoryInterface {

    TaskReeng createTask(TaskDTO taskDTO, ResourceIDReeng resourceId, TaskContainerID taskContainerID);

}
