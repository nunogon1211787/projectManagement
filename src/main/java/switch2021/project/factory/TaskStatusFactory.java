package switch2021.project.factory;

import switch2021.project.immutable.TaskStatus;

public class TaskStatusFactory {
    /**
     * Attribute
     */
    private TaskStatus taskStatus;


    /**
     *  Methods to create and add an object that this class are responsible
     */
    public TaskStatus createAndAddTaskStatus(String status) {
       return this.taskStatus = new TaskStatus(status);
    }
}
