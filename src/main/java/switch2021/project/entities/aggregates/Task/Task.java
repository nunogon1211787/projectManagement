package switch2021.project.entities.aggregates.Task;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import switch2021.project.utils.Entity;
import switch2021.project.entities.valueObjects.vos.ResourceID;
import switch2021.project.entities.valueObjects.vos.*;
import switch2021.project.entities.valueObjects.vos.enums.TaskTypeEnum;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Task implements Entity<Task> {

    /**
     * Attributes
     */
    private TaskID taskID;
    private Description description;
    private TaskTypeEnum type;
    private EffortEstimate effortEstimate;  //in hours
    private LocalDate startDate;
    private LocalDate endDate;
    private ResourceID responsible;
    private List<TaskEffort> taskEffortList;
    private List<TaskID> precedenceList;

    /**
     * Constructor
     */
    public Task(TaskID taskID, Description description,
                EffortEstimate effortEstimate, TaskTypeEnum type,
                ResourceID responsible) {
        this.taskID = taskID;
        this.description = description;
        this.effortEstimate = effortEstimate;
        this.type = type;
        this.responsible = responsible;
        this.taskEffortList = new ArrayList<>();
        this.precedenceList = new ArrayList<>();
    }
/*
    /**
     * Constructor with precedence list
     **/

//    public TaskReeng(Name name, Description description, EffortEstimate effortEstimate, TaskTypeEnum type, Resource
//    responsible, TaskContainerID taskContainerID, List<String> precedenceList) {
//        new TaskReeng(name, description, effortEstimate, type, responsible, taskContainerID);
//        this.precedenceList = Collections.unmodifiableList(precedenceList);
//    }

    /**
     * Methods to iterate with attributes
     */
    public boolean hasName(String taskName) {
        return Objects.equals(this.taskID.getTaskName().getText(), taskName);
    }

    public boolean hasTaskTypeEnum(String taskType) {
        return Objects.equals(this.type.toString(), taskType);
    }

    public boolean hasResponsible(ResourceID resp) {
        return Objects.equals(this.responsible, resp);
    }

    public boolean hasDescription(Description description) {
        return Objects.equals(this.description, description);
    }


    /**
     * Methods get
     */
    public String getStatus() {
        String status = "Blocked";
        double x = getExecutionPercentage();

        if (x == 0) {
            status = "Planned";
        }

        if (x == 1) {
            status = "Finished";
        }

        if (x > 0 && x < 1) {
            status = "Running";
        }

        return status;
    }

    public double getExecutionPercentage() {
        return getHoursSpent() / this.effortEstimate.getEffortHours();
    }

    public double getHoursSpent() {
        double x = 0;
        for (TaskEffort i : this.taskEffortList) {
            x = x + effortInHours(i);
        }
        return x;
    }

    private double effortInHours(TaskEffort effort) {
        return (double) effort.getEffortHours().getEffortHours()
                + ((double) effort.getEffortMinutes().getEffortMinutes() / 60);
    }


    /**
     * Methods Override
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return Objects.equals(taskID, task.taskID) && Objects.equals(description, task.description)
                && type == task.type && Objects.equals(effortEstimate, task.effortEstimate)
                && Objects.equals(startDate, task.startDate) && Objects.equals(endDate, task.endDate)
                && Objects.equals(responsible, task.responsible) && Objects.equals(taskEffortList, task.taskEffortList)
                && Objects.equals(precedenceList, task.precedenceList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(taskID, description, type, effortEstimate, startDate, endDate, responsible,
                taskEffortList, precedenceList);
    }

    @Override
    public boolean sameIdentityAs(Task other) {
        return false;
    }
}
