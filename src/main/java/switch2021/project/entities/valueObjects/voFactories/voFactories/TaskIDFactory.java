package switch2021.project.entities.valueObjects.voFactories.voFactories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import switch2021.project.entities.valueObjects.voFactories.voInterfaces.INameFactory;
import switch2021.project.entities.valueObjects.voFactories.voInterfaces.ITaskIDFactory;
import switch2021.project.applicationServices.iRepositories.TaskContainerID;
import switch2021.project.entities.valueObjects.vos.TaskID;
import switch2021.project.entities.valueObjects.vos.Name;

@Component
public class TaskIDFactory implements ITaskIDFactory {

    /**
     * Attributes
     */
    private TaskContainerID taskContainerID;
    @Autowired
    private INameFactory taskName;


    /**
     * Method Create
     */
    @Override
    public TaskID createTaskID(TaskContainerID taskContainerID, String name) {
        Name name2 = taskName.createName(name);
        return new TaskID(taskContainerID, name2);
    }
}
