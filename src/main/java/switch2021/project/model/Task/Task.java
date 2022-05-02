package switch2021.project.model.Task;

import lombok.Getter;
import lombok.Setter;
import switch2021.project.interfaces.TaskContainerID;
import switch2021.project.model.Resource.old.Resource;
import switch2021.project.model.valueObject.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Setter
@Getter
@Deprecated
public class Task {

    /**
     * Attributes.
     */
    private int idTask;
    private Name name;
    private Description description;
    private TaskTypeEnum type;
    private double effortEstimate;  // em horas
    private LocalDate startDate;
    private LocalDate endDate;
    private Resource responsible;
    private List<TaskEffort> taskEffortList;
    private List<String> precedenceList;
    private TaskContainerID taskContainerID;


    /**
     * Constructors.
     */
    public Task(String description) {
        this.description = new Description(description);
    }

    public Task(String name, String description, double effortEstimate, TaskTypeEnum type, Resource responsible) {

        checkEffortRules(effortEstimate);

        this.name = new Name(name);
        this.description = new Description(description);
        this.effortEstimate = effortEstimate;
        this.type = type;
        this.responsible = responsible;
        this.taskEffortList = new ArrayList<>();

    }

    public Task(String name, String description, double effortEstimate, TaskTypeEnum type, Resource responsible, TaskContainerID taskContainerID) {

        checkEffortRules(effortEstimate);

        this.name = new Name(name);
        this.description = new Description(description);
        this.effortEstimate = effortEstimate;
        this.type = type;
        this.responsible = responsible;
        this.taskEffortList = new ArrayList<>();
        this.taskContainerID = taskContainerID;
    }

    public Task(String name, String description, double effortEstimate, TaskTypeEnum type, Resource responsible, List<String> precedenceList) {
        new Task(name, description, effortEstimate, type, responsible);
        this.precedenceList = Collections.unmodifiableList(precedenceList);

    }

    /**
     * Methods to iterate with attributes,
     */

    public boolean hasName(String taskName) {
        return Objects.equals(this.name.getText(), taskName);
    }

    public boolean hasTaskTypeEnum(String taskType) {
        return Objects.equals(this.type.toString(), taskType);
    }

    public boolean hasResponsible(Resource resp) {
        return Objects.equals(this.responsible, resp);
    }

    public boolean hasId(int id) {
        return Objects.equals(this.idTask, id);
    }

    public void setIdTask(int id) {
        checkIdRules(id);
        this.idTask = id;
    }

    /**
     * Methods to validate attributes data.
     */

    private void checkIdRules(int id) {
        if (id < 1)
            throw new IllegalArgumentException("Type ID cannot be negative.");
    }

    private void checkEffortRules(double effort) {
        if (effort <= 0.00) {
            throw new IllegalArgumentException("Effort can be bigger than zero.");
        }
    }

    public void createAndSaveTaskEffort(int effortHours, int effortMinutes, Date effortDate, String comment, String attachment) {
        TaskEffort taskEffort = new TaskEffort(effortHours, effortMinutes, effortDate, comment, attachment);
        if (taskEffort.getEffortHours().getEffortHours() == 0 && taskEffort.getEffortMinutes().getEffortMinutes() == 0) {
            throw new IllegalArgumentException("Not work time values insert");
        }

            if (taskEffortList.isEmpty()) {
                setStartDate(taskEffort.getEffortDate().getEffortDate());

            }

            //validateEffortPercentage(effortHours, effortMinutes);

        this.taskEffortList.add(taskEffort);
        if(getHoursSpent() == this.effortEstimate)
            setEndDate(effortDate.getEffortDate());
    }


    private double effortInHours(TaskEffort effort) {
        return (double) effort.getEffortHours().getEffortHours() + ((double) effort.getEffortMinutes().getEffortMinutes() / 60);
    }



    public String getStatus() {
        String status = "Blocked";
        double x = getExecutionPercentage();


        if(x == 0){
            status = "Planned";
        }

        if(x == 1){
            status = "Finished";
        }

        if(x > 0 && x < 1){
            status = "Running";
        }

        return status;
    }

    public double getHoursSpent() {
        double x = 0;
        for (TaskEffort i : this.taskEffortList) {
            x = x + effortInHours(i);
        }
        return x;
    }

    public double getExecutionPercentage() {
        double x = getHoursSpent();
        return x / this.effortEstimate;
    }

/*    public void validateEffortPercentage(int hours, int minutes) {
        double x = getHoursSpent();
        double y = getEffortEstimate();
        double z = hours + (minutes / 60);

        if(x + z > y){
            throw new IllegalArgumentException("Hours spent is higher then effort estimated, please update effort estimate");

        }
    }

 */

    /**
     * Override methods.
     */

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return Double.compare(task.effortEstimate, effortEstimate) == 0 && name.equals(task.name) && description.equals(task.description) && type.equals(task.type) && Objects.equals(endDate, task.endDate) && responsible.equals(task.responsible);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description, type, effortEstimate, endDate, responsible);
    }
}