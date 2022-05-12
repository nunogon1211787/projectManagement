package switch2021.project.factory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import switch2021.project.factoryInterface.INameFactory;
import switch2021.project.interfaces.ITaskIDFactory;
import switch2021.project.interfaces.TaskContainerID;
import switch2021.project.model.Task.TaskID;
import switch2021.project.model.valueObject.Name;

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
