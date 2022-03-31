package switch2021.project.model.Task;

import lombok.Getter;
import lombok.Setter;
import switch2021.project.model.valueObject.*;
import switch2021.project.utils.App;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Setter
@Getter
public class Task {

    /**
     * Attributes.
     */
    private int idTask;
    private Name name;
    private Description description;
    private TaskType type;
    private double effortEstimate;
    private double effortRemaining; // Initially equal effortEstimate, but can be change by user to update the effort to the task.
    private LocalDate startDate;
    private LocalDate endDate;
    private TaskStatus status;
    private double hoursSpent; // Initially zero and will be updated by effort register.
    private double executionPercentage; // Calculated by divide hoursSpent to effortRemaining.
    private Resource responsible;
    private List<TaskEffort> taskEffortList;
    private List<String> precedenceList;


    /**
     * Constructors.
     */
    public Task(String description) {
        this.description = new Description(description);
        this.status = App.getInstance().getCompany().getTaskStatusStore().getTaskStatusByDescription("Planned");
    }

    public Task(String name, String description, double effortEstimate, TaskType type, Resource responsible) {

        checkEffortRules(effortEstimate);

        this.name = new Name(name);
        this.description = new Description(description);
        this.effortEstimate = effortEstimate;
        this.effortRemaining = effortEstimate;
        this.type = type;
        this.responsible = responsible;
        this.status = App.getInstance().getCompany().getTaskStatusStore().getInitialStatus();
        this.taskEffortList = new ArrayList<>();
    }

    public Task(String name, String description, double effortEstimate, TaskType type, Resource responsible, List<String> precedenceList) {
        new Task(name, description, effortEstimate, type, responsible);
        this.precedenceList = Collections.unmodifiableList(precedenceList);

    }

    /**
     * Methods to iterate with attributes,
     */

    public boolean hasName(String taskName) {
        return Objects.equals(this.name.getNameF(), taskName);
    }

    public boolean hasType(TaskType taskType) {
        return Objects.equals(this.type, taskType);
    }

    public boolean hasStatus(TaskStatus taskStatus) {
        return Objects.equals(this.status, taskStatus);
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
        if (effort <= 0) {
            throw new IllegalArgumentException("Effort can be bigger than zero.");
        }
    }

    public TaskEffort createTaskEffort(int effortHours, int effortMinutes, Date effortDate, String comment, String attachment) {
        TaskEffort taskEffort = new TaskEffort(effortHours, effortMinutes, effortDate, comment, attachment);
        if (taskEffort.getEffortHours().getEffortHours() == 0 && taskEffort.getEffortMinutes().getEffortMinutes() == 0)
            throw new IllegalArgumentException("Not work time values insert");
        return taskEffort;
    }

    public boolean validateTaskEffort(TaskEffort effort) {
        for (TaskEffort i : this.taskEffortList) {
            if (!effort.getEffortDate().getEffortDate().isAfter(i.getEffortDate().getEffortDate())) {
                throw new IllegalArgumentException("Effort for this day is already saved.");
            }
        }
        if (effort == null) {
            return false;
        }
        if (effort.getEffortDate().getEffortDate().isAfter(this.getResponsible().getEndDate()) || effort.getEffortDate().getEffortDate().isBefore(this.getResponsible().getStartDate())) {
            throw new IllegalArgumentException("work date not match with the resource allocation dates");
        }
        return !this.taskEffortList.contains(effort);
    }

    public boolean saveTaskEffort(TaskEffort effort) {
        boolean result = true;

        if (!validateTaskEffort(effort)) {
            result = false;
        } else {
            if (taskEffortList.isEmpty()) {
                setStartDate(effort.getEffortDate().getEffortDate());
                setStatus(App.getInstance().getCompany().getTaskStatusStore().getTaskStatusByDescription("Running"));
            }
            this.taskEffortList.add(effort);
            updateHoursSpent(effort);
            updateEffortRemaining(effort);
            updateExecutionPercentage();
            if (this.effortRemaining == 0) {
                setStatus(App.getInstance().getCompany().getTaskStatusStore().getTaskStatusByDescription("Finished"));
                setEndDate(effort.getEffortDate().getEffortDate());
            }
        }
        return result;
    }

    private double effortInHours(TaskEffort effort) {
        return (double) effort.getEffortHours().getEffortHours() + ((double) effort.getEffortMinutes().getEffortMinutes() / 60);
    }

    public double updateHoursSpent(TaskEffort effort) {
        return this.hoursSpent += effortInHours(effort);
    }

    public double updateEffortRemaining(TaskEffort effort) {
        double EFFORT_TO_COMPLETE = 0.0;

        if (this.effortRemaining <= effortInHours(effort)) {
            this.effortRemaining = EFFORT_TO_COMPLETE;
        } else {
            this.effortRemaining -= effortInHours(effort);
        }

        return this.effortRemaining;
    }

    private double updateExecutionPercentage() {
        double EFFORT_COMPLETED = 1.0;

        double workTotal = this.hoursSpent + this.effortRemaining;
        double workDone = this.hoursSpent;

        if (workDone >= workTotal) {
            this.executionPercentage = EFFORT_COMPLETED;
        } else {
            this.executionPercentage = workDone / workTotal;
        }
        return this.executionPercentage;
    }


    /**
     * Override methods.
     */

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return Double.compare(task.effortEstimate, effortEstimate) == 0 && Double.compare(task.effortRemaining, effortRemaining) == 0 && Double.compare(task.hoursSpent, hoursSpent) == 0 && Double.compare(task.executionPercentage, executionPercentage) == 0 && name.equals(task.name) && description.equals(task.description) && type.equals(task.type) && Objects.equals(endDate, task.endDate) && status.equals(task.status) && responsible.equals(task.responsible);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description, type, effortEstimate, effortRemaining, endDate, status, hoursSpent, executionPercentage, responsible);
    }
}