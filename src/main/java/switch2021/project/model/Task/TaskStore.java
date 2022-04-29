package switch2021.project.model.Task;

import lombok.Getter;
import switch2021.project.mapper.old.TaskMapper;
import switch2021.project.dto.old.CreateTaskDTO;
import switch2021.project.model.Project.Project;
import switch2021.project.model.Resource.old.Resource;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
public class TaskStore {

    /**
     * Attributes
     */
    private final List<Task> taskList;

    /**
     * Constructor
     */
    public TaskStore() {
        this.taskList = new ArrayList<>();
    }

    /**
     * Methods to create Task
     */
    public Task createTask(String name, String description, double effortEstimate, TaskTypeEnum type, Resource responsible){
        return new Task(name, description, effortEstimate, type, responsible);
    }

    // Create task with DTO and Mapper. US032 - Sprint 3
    public boolean createSprintTask(CreateTaskDTO dto, TaskMapper mapper, Project proj){
        Task newTask = mapper.toModel(dto, proj);

        return saveTask(newTask);
    }

    public boolean createUsTask(CreateTaskDTO dto, TaskMapper mapper, Project proj){
        Task newTask = mapper.toModel(dto, proj);

        return saveTask(newTask);
    }

    /**
     * Getter methods
     */
    public List<String> getTasksNames(){
        List<String> tasksNames = new ArrayList<>();

        for (Task task : this.taskList) {
            tasksNames.add(task.getName().getNameF());
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
     * Method to add a task to the list
     */
    private boolean addTaskToTheList(Task task) {
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
     * Method to save task
     */
    public boolean saveTask(Task newTask) {

        boolean result = false;

        if(newTask != null) {

            result = true;

            if (!validateIfTaskAlreadyExists(newTask)) {
                newTask.setIdTask(idTaskGenerator());
                addTaskToTheList(newTask);
            }

        }
        return result;
    }

    /**
     * Method to validate if a task already exists
     */
    private boolean validateIfTaskAlreadyExists(Task task) {
        return this.taskList.contains(task);
    }


    /**
     * Method to get list of tasks within a sprint
     */
//It hasn't tests
//    public List<Task> getListOfTasksOfASprint(){
//        List<Task> taskList2 = new ArrayList<>();
//        taskList2.addAll(this.taskStore.getTaskList());
//        return taskList2;
//    }
    

    /**
     * ID Generator
     **/
    public int idTaskGenerator() {
        int id = 1;
        if (this.taskList.size() > 0) {
            id = this.taskList.get(taskList.size() - 1).getIdTask() + 1;
        }
        return id;
    }

    /**
     * Override
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TaskStore taskStore = (TaskStore) o;
        return Objects.equals(this.taskList, taskStore.taskList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(taskList);
    }
}
