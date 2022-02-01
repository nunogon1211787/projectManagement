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
     * Attributes.
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

    // Create task with DTO and Mapper. US032 - Sprint 3
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

            tasksNames.add(task.getName());

        }

        return tasksNames;
    }

    public Task getTaskById(int id){

        Task result = null;

        for (Task task : this.taskList) {

            if (task.hasId(id)) {
                result = task;
            }

        }

        return result;

    }

    public Task getTaskByName(String name){

        Task result = null;

        for (Task task : this.taskList) {

            if (task.hasName(name)) {
                result = task;
            }

        }

        return result;

    }


    /**
     * Method to add a task to the list.
     */

    private boolean addTaskToTheList(Task task) {
        this.taskList.add(task);
        return true;
    }

    /**
     * Method to remove an object.
     */

    public boolean removeTaskFromTheList(Task task) {
        this.taskList.remove(task);

        return true;
    }

    /**
     * Method to save task
     */

    public boolean saveTask(Task newTask) {

        boolean result = false;

        if(newTask != null) {

            result = true;

            if (!validateIfTaskAlreadyExists(newTask)) {
                newTask.setID_Task(id_TaskGenerator());
                addTaskToTheList(newTask);
            }

        }
        return result;
    }

    /**
     * Method to validate if a task already exists.
     */

    private boolean validateIfTaskAlreadyExists(Task task) {
        return this.taskList.contains(task);
    }

    /**
     * ID Generator.
     **/
    public int id_TaskGenerator() {
        int id = 1;
        if (this.taskList.size() > 0) {
            id = this.taskList.get(taskList.size() - 1).getID_Task() + 1;
        }
        return id;
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
