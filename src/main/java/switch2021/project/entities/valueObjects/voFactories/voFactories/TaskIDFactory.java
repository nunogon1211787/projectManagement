package switch2021.project.entities.valueObjects.voFactories.voFactories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import switch2021.project.entities.valueObjects.voFactories.voInterfaces.INameFactory;
import switch2021.project.applicationServices.iRepositories.ITaskIDFactory;
import switch2021.project.applicationServices.iRepositories.TaskContainerID;
import switch2021.project.entities.valueObjects.vos.TaskID;
import switch2021.project.entities.valueObjects.vos.Name;

@Component
public class TaskIDFactory implements ITaskIDFactory {


    private TaskContainerID taskContainerID;

    @Autowired
    private INameFactory taskName;



    public TaskIDFactory() {}

    public TaskIDFactory(TaskContainerID taskContainerID, INameFactory nameFactory) {
        this.taskContainerID = taskContainerID;
        this.taskName = nameFactory;
    }


    @Override
    public TaskID createTaskID(TaskContainerID taskContainerID, String name) {
        Name name2 = taskName.createName(name);
        return new TaskID(taskContainerID, name2);
    }
}
