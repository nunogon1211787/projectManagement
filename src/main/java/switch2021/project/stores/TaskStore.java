package switch2021.project.stores;

import lombok.Getter;
import switch2021.project.model.Customer;
import switch2021.project.model.Request;
import switch2021.project.model.Task;

import java.util.ArrayList;
import java.util.List;

@Getter
public class TaskStore {

    private List<Task> taskList;

    public TaskStore() {
        this.taskList = new ArrayList<>();
    }

    public Task createTask(String description){
        return new Task(description) ;
    }



    /**
     * Method to add a task to the list
     */

    public boolean addTaskToTheList(Task task) {
        this.taskList.add(task);
        return true;
    }

    /**
     * Method to remove an object
     */

    public boolean removeTaskFromTheList(Task task) {
        this.taskList.remove(task);

        return true;
    }

    /**
     * Method to validate if a task already exists
     */

    private boolean validateIfTaskAlreadyExists(Task task) {
        return this.taskList.contains(task);
    }

    /**
     * Method to save task
     */

    public boolean saveTask(Task newTask) {

        boolean result = false;

        if (!validateIfTaskAlreadyExists(newTask)) {
            addTaskToTheList(newTask);
            result = true;
        }
        return result;
    }




}
