package switch2021.project.model.Task;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import switch2021.project.model.Resource.ResourceIDReeng;
import switch2021.project.model.valueObject.Description;
import switch2021.project.model.valueObject.EffortEstimate;
import switch2021.project.utils.Entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TaskReeng implements Entity<TaskReeng> {

    /*** Attributes*/
    private TaskID idTask;
    private Description description;
    private TaskTypeEnum type;
    private EffortEstimate effortEstimate;  // em horas
    private LocalDate startDate;
    private LocalDate endDate;
    private ResourceIDReeng responsible;
    private List<TaskEffort> taskEffortList;
    private List<TaskID> precedenceList;



    /*** Constructor */

    public TaskReeng(TaskID taskID) {
        this.idTask = taskID;
        this.taskEffortList = new ArrayList<>();

    }

        public TaskReeng(TaskID taskID, Description description,
                         EffortEstimate effortEstimate, TaskTypeEnum type,
                         ResourceIDReeng responsible) {
        this.idTask = taskID;
        this.description = description;
        this.effortEstimate = effortEstimate;
        this.type = type;
        this.responsible = responsible;


    }

    /*** Constructor with precedence list*/

//    public TaskReeng(Name name, Description description, EffortEstimate effortEstimate, TaskTypeEnum type, Resource responsible, TaskContainerID taskContainerID, List<String> precedenceList) {
//        new TaskReeng(name, description, effortEstimate, type, responsible, taskContainerID);
//        this.precedenceList = Collections.unmodifiableList(precedenceList);
//    }

    /*** Methods to iterate with attributes */

    public boolean hasName(String taskName) {
        return Objects.equals(this.idTask.getTaskName().getText(), taskName);
    }

    public boolean hasTaskTypeEnum(String taskType) {
        return Objects.equals(this.type.toString(), taskType);
    }

    public boolean hasResponsible(ResourceIDReeng resp) {
        return Objects.equals(this.responsible, resp);
    }

    public boolean hasDescription(Description description) {
        return Objects.equals(this.description, description);
    }


    /*** Methods get */

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

    public double getExecutionPercentage() {
        double x = getHoursSpent();
        double result = (x / this.effortEstimate.getEffortHours());

        return result;
    }

    public double getHoursSpent() {
        double x = 0;
        for (TaskEffort i : this.taskEffortList) {
            x = x + effortInHours(i);
        }
        return x;
    }

    private double effortInHours(TaskEffort effort) {
        return (double) effort.getEffortHours().getEffortHours() + ((double) effort.getEffortMinutes().getEffortMinutes() / 60);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TaskReeng taskReeng = (TaskReeng) o;
        return Objects.equals(idTask, taskReeng.idTask) && Objects.equals(description, taskReeng.description) && type == taskReeng.type && Objects.equals(effortEstimate, taskReeng.effortEstimate) && Objects.equals(startDate, taskReeng.startDate) && Objects.equals(endDate, taskReeng.endDate) && Objects.equals(responsible, taskReeng.responsible) && Objects.equals(taskEffortList, taskReeng.taskEffortList) && Objects.equals(precedenceList, taskReeng.precedenceList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idTask, description, type, effortEstimate, startDate, endDate, responsible, taskEffortList, precedenceList);
    }

    @Override
    public boolean sameIdentityAs(TaskReeng other) {
        return false;
    }
}
