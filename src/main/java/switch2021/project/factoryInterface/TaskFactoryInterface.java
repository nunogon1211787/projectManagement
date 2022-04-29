package switch2021.project.factoryInterface;

import org.springframework.stereotype.Component;
import switch2021.project.dto.TaskDTO;

import switch2021.project.interfaces.TaskContainerID;
import switch2021.project.model.Resource.ResourceId;
import switch2021.project.model.Task.TaskReeng;


@Component
public interface TaskFactoryInterface {

    TaskReeng createTask(TaskDTO taskDTO, ResourceId resourceId, TaskContainerID taskContainerID);

}
