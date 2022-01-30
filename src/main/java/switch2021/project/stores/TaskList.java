package switch2021.project.stores;

import lombok.Getter;
import switch2021.project.mapper.TaskMapper;
import switch2021.project.dto.CreateTaskDTO;
import switch2021.project.model.Project;
import switch2021.project.model.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
public class TaskList {

    /**
     * Atributtes.
     */

    private List<Task> taskList;

    /**
     * Constructor.
     */

    public TaskList() {
        this.taskList = new ArrayList<>();
    }


    /**
     * Methods to create Task.
     */

    public Task createTask(String description){
        return new Task(description);
    }

    public boolean createSprintTask(CreateTaskDTO dto, TaskMapper mapper, Project proj){

        Task newTask = mapper.toModel(dto, proj);

        return saveTask(newTask);
    }

    /**
     * Getter methods.
     */

    public List<String> getTasksNames(){

        List<String> tasksNames = new ArrayList<>();

        for (Task task : this.taskList) {

            tasksNames.add(task.getDescription());

        }

        return tasksNames;
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

    /**
     * Override methods.
     */

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
