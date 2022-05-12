package switch2021.project.factory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import switch2021.project.dto.TaskDTO;
import switch2021.project.factoryInterface.IDescriptionFactory;
import switch2021.project.factoryInterface.INameFactory;
import switch2021.project.factoryInterface.TaskFactoryInterface;
import switch2021.project.interfaces.IEffortEstimateFactory;
import switch2021.project.interfaces.ITaskIDFactory;
import switch2021.project.interfaces.TaskContainerID;
import switch2021.project.model.Resource.ResourceIDReeng;
import switch2021.project.model.Task.TaskReeng;
import switch2021.project.model.Task.TaskTypeEnum;

@Component
public class TaskFactory implements TaskFactoryInterface {

    @Autowired
    private INameFactory name;

    @Autowired
    private IDescriptionFactory description;

    @Autowired
    private IEffortEstimateFactory effortEstimate;

    @Autowired
    private ITaskIDFactory taskID;



    @Override
    public TaskReeng createTask(TaskDTO taskDTO, ResourceIDReeng responsible2, TaskContainerID taskContainerID) {

        return new TaskReeng(taskID.createTaskID(taskContainerID, taskDTO.name),
                description.createDescription(taskDTO.description),
                effortEstimate.create(taskDTO.effortEstimate), TaskTypeEnum.valueOf(taskDTO.type),
                responsible2);


    }
}
