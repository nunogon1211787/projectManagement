package switch2021.project.factory;

import org.springframework.stereotype.Component;
import switch2021.project.dto.TaskDTO;
import switch2021.project.factoryInterface.TaskFactoryInterface;
import switch2021.project.interfaces.TaskContainerID;
import switch2021.project.model.Resource.ResourceIDReeng;
import switch2021.project.model.Task.TaskID;
import switch2021.project.model.Task.TaskReeng;
import switch2021.project.model.Task.TaskTypeEnum;
import switch2021.project.model.valueObject.Description;
import switch2021.project.model.valueObject.EffortEstimate;
import switch2021.project.model.valueObject.Name;
@Component
public class TaskFactory implements TaskFactoryInterface {


    @Override
    public TaskReeng createTask(TaskDTO taskDTO, ResourceIDReeng responsible2, TaskContainerID taskContainerID) {

        Name newName = new Name(taskDTO.name);
        Description description1 = new Description(taskDTO.description);
        EffortEstimate effortEstimate1 = new EffortEstimate(taskDTO.effortEstimate);
        TaskTypeEnum taskTypeEnum = TaskTypeEnum.valueOf(taskDTO.type);
        TaskID newTaskID = new TaskID(taskContainerID, newName);

        TaskReeng newTask = new TaskReeng(newTaskID);

        newTask.setName(newName);
        newTask.setDescription(description1);
        newTask.setEffortEstimate(effortEstimate1);
        newTask.setType(taskTypeEnum);
        newTask.setResponsible(responsible2);
        newTask.setTaskContainerID(taskContainerID);

        return newTask;
    }
}
