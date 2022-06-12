package switch2021.project.entities.factories.factories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import switch2021.project.dtoModel.dto.TaskDTO;
import switch2021.project.entities.valueObjects.voFactories.voInterfaces.IDescriptionFactory;
import switch2021.project.entities.valueObjects.voFactories.voInterfaces.INameFactory;
import switch2021.project.applicationServices.iRepositories.IEffortEstimateFactory;
import switch2021.project.entities.valueObjects.voFactories.voInterfaces.ITaskIDFactory;
import switch2021.project.applicationServices.iRepositories.TaskContainerID;
import switch2021.project.entities.factories.factoryInterfaces.ITaskFactory;
import switch2021.project.entities.valueObjects.vos.ResourceID;
import switch2021.project.entities.aggregates.Task.Task;
import switch2021.project.entities.valueObjects.vos.enums.TaskTypeEnum;

@Component
public class TaskFactory implements ITaskFactory {

    @Autowired
    private INameFactory name;
    @Autowired
    private IDescriptionFactory description;
    @Autowired
    private IEffortEstimateFactory effortEstimate;
    @Autowired
    private ITaskIDFactory taskID;


    /**
     * Create Method
     */
    @Override
    public Task createTask(TaskDTO taskDTO, ResourceID responsible2, TaskContainerID taskContainerID) {

        return new Task(taskID.createTaskID(taskContainerID, taskDTO.name),
                        description.createDescription(taskDTO.description),
                        effortEstimate.create(taskDTO.effortEstimate), TaskTypeEnum.valueOf(taskDTO.type),
                        responsible2);


    }
}
