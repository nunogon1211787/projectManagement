package switch2021.project.model;

import lombok.Getter;
import lombok.Setter;
import switch2021.project.stores.TaskList;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
public class Sprint {

    /**
     * Atributos da classe Sprint
     **/
    private int idSprint;
    private String name;
    private final TaskList taskList;
    private final SprintBacklog sprintBacklog;
    private LocalDate startDate;
    private LocalDate endDate;


    /**
     * Constructor of Sprint
     **/
    public Sprint(String name, LocalDate startDate) {
        checkSprintNameRules(name);
        this.name = name;
        this.startDate = startDate;
        this.sprintBacklog = new SprintBacklog();
        this.taskList = new TaskList();
    }

    /**
     * Method to change Sprint EndDate
     **/
    public void changeEndDate(int sprintDurationInWeeks) {
        this.endDate = startDate.plusDays((sprintDurationInWeeks * 7L) - 1);
    }


    /**
     * Validation Method for the Constructor
     **/
    private void checkSprintNameRules(String name) {
        if (name.trim().isEmpty())
            throw new IllegalArgumentException("Sprint Name cannot be empty.");
        if ((name.length() < 2))
            throw new IllegalArgumentException("Sprint Name must have at least 2 characters");
    }

    /**
     * Check if this Sprint is the current Sprint
     */
    public boolean isCurrentSprint() {
        if(this.endDate == null) {
            throw new NullPointerException();
        }
        return ((this.startDate.isBefore(LocalDate.now()) || this.startDate.equals(LocalDate.now())) && (this.endDate.isAfter(LocalDate.now()) || this.endDate.equals(LocalDate.now())));
    }

    /**
     * Method to get list of tasks within a sprint
     */
    public List<Task> getListOfTasksOfASprint(){
        List<Task> taskList = new ArrayList<>();

        taskList.addAll(this.sprintBacklog.getUserStoryOfSprintTasks());
        taskList.addAll(this.taskList.getTaskList());
        return taskList;
    }

    /**
     * Override Methods
     */

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sprint sprint = (Sprint) o;
        return idSprint == sprint.idSprint && Objects.equals(name, sprint.name) && Objects.equals(taskList, sprint.taskList) && Objects.equals(sprintBacklog, sprint.sprintBacklog) && Objects.equals(startDate, sprint.startDate) && Objects.equals(endDate, sprint.endDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idSprint, name, taskList, sprintBacklog, startDate, endDate);
    }

    public boolean hasSprintID(int id) {
        return this.idSprint == id;
    }
}
