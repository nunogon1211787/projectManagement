package switch2021.project.model;

import lombok.Getter;
import lombok.Setter;
import switch2021.project.immutable.Description;
import switch2021.project.immutable.Name;
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
    private Description sprintName;
    private final TaskList taskList;
    private final SprintBacklog sprintBacklog;
    private LocalDate startDate;
    private LocalDate endDate;


    /**
     * Constructor of Sprint
     **/
    public Sprint(String name, LocalDate startDate) {
        this.sprintName = new Description(name);
        this.startDate = startDate;
        this.sprintBacklog = new SprintBacklog();
        this.taskList = new TaskList();
    }

    /**
     * Method to change Sprint EndDate
     **/
    long SEM = 7;
    int DIA = 1;

    public void changeEndDate(int sprintDurationInWeeks) {
        this.endDate = startDate.plusDays((sprintDurationInWeeks * SEM) - DIA);
    }

    public boolean hasSprintID(int id) {
        return this.idSprint == id;
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
        List<Task> taskList2 = new ArrayList<>();

        taskList2.addAll(this.taskList.getTaskList());
        return taskList2;
    }

    /**
     * Override Methods
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sprint sprint = (Sprint) o;
        return idSprint == sprint.idSprint && Objects.equals(sprintName, sprint.sprintName) &&
                Objects.equals(taskList, sprint.taskList) && Objects.equals(sprintBacklog, sprint.sprintBacklog) &&
                Objects.equals(startDate, sprint.startDate) && Objects.equals(endDate, sprint.endDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idSprint, sprintName, taskList, sprintBacklog, startDate, endDate);
    }
}
