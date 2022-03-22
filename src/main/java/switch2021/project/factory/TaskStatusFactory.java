package switch2021.project.factory;

import switch2021.project.factoryInterface.TaskStatusFactoryInterface;
import switch2021.project.immutable.TaskStatus;

public class TaskStatusFactory implements TaskStatusFactoryInterface {

    /**
     *  Methods to create and add an object that this class are responsible
     */
    @Override
    public TaskStatus createTaskStatus(String status) {
       return new TaskStatus(status);
    }
}
