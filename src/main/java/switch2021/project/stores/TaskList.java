package switch2021.project.stores;

import lombok.Getter;
import switch2021.project.model.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
public class TaskList {

    private List<Task> taskList;

    public TaskList() {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TaskList taskList = (TaskList) o;
        return Objects.equals(this.taskList, taskList.taskList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(taskList);
    }
}
