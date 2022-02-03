package switch2021.project.model;

import lombok.Getter;
import lombok.Setter;
import switch2021.project.utils.App;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Setter
@Getter
public class Task {

    /**
     * Attributes.
     */
    private int idTask;
    private String name;
    private String description;
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


    /**
     * Constructors.
     */
    public Task (String description){
        this.description = description;
        this.status = new TaskStatus("Planned");
    }

    public Task(String name, String description, double effortEstimate, TaskType type, Resource responsible) {

        checkNameRules(name);
        checkDescriptionRules(description);
        checkEffortRules(effortEstimate);
        checkTypeNotNull(type);
        checkResponsibleNotNull(responsible);

        this.name = name;
        this.description = description;
        this.effortEstimate = effortEstimate;
        this.effortRemaining = effortEstimate;
        this.type = type;
        this.responsible = responsible;
        this.status = App.getInstance().getCompany().getTaskStatusStore().getInitialStatus();
        this.taskEffortList = new ArrayList<>();

    }

    /**
     * Methods to iterate with atributtes,
     */

    public boolean hasName(String taskName) {
        return Objects.equals(this.name, taskName);
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
    private void checkNameRules(String name) {
        if (name.trim().isEmpty())
            throw new IllegalArgumentException("Name cannot be empty.");
        if ((name.length() < 3))
            throw new IllegalArgumentException("Name must be at least 3 characters");
    }

    private void checkIdRules(int id) {
        if (id < 0)
            throw new IllegalArgumentException("Type ID cannot be negative.");
    }

    private void checkDescriptionRules(String description) {
        if (description.trim().isEmpty())
            throw new IllegalArgumentException("Description cannot be empty.");
        if ((description.length() < 20))
            throw new IllegalArgumentException("Description must be at least 20 characters");
    }

    private void checkEffortRules(double effort) {
        if (effort <= 0) {
            throw new IllegalArgumentException("Effort can be bigger than zero.");
        }
    }

    private void checkTypeNotNull(TaskType type) {
        if (type == null) {
            throw new IllegalArgumentException("Type can be a valid(not null) object.");
        }
    }

    private void checkResponsibleNotNull(Resource responsible) {
        if (responsible == null) {
            throw new IllegalArgumentException("Responsible can be a valid(not null) object.");
        }
    }

    public TaskEffort createTaskEffort(int effortHours, int effortMinutes, LocalDate effortDate, String comment, String attachment) {
        Resource effortResponsible = this.responsible;

        return new TaskEffort(effortHours, effortMinutes, effortDate, comment, attachment, effortResponsible);
    }

    public boolean validateTaskEffort(TaskEffort effort) {
        boolean result = false;

        for (TaskEffort i : this.taskEffortList) {
            if (!effort.getEffortDate().isAfter(i.getEffortDate())) {
                throw new IllegalArgumentException("Effort for this day is already saved.");
            }
        }
        if (effort == null) {
            return false;
        }
        return !this.taskEffortList.contains(effort);
    }

    public boolean saveTaskEffort(TaskEffort effort) {
        boolean result = true;

        if (!validateTaskEffort(effort)) {
            result = false;
        } else {
            if (taskEffortList.isEmpty()) {
                setStartDate(effort.getEffortDate());
                setStatus(App.getInstance().getCompany().getTaskStatusStore().getTaskStatusByDescription("Running"));
            }
            this.taskEffortList.add(effort);
            updateHoursSpent(effort);
            updateEffortRemaining(effort);
            updateExecutionPercentage();
            if (this.effortRemaining == 0) {
                setStatus(App.getInstance().getCompany().getTaskStatusStore().getTaskStatusByDescription("Finished"));
                setEndDate(effort.getEffortDate());
            }
        }
        return result;
    }

    private double effortInHours(TaskEffort effort) {
        return (double) effort.getEffortHours() + ((double) effort.getEffortMinutes() / 60);
    }

    public double updateHoursSpent(TaskEffort effort) {
        return this.hoursSpent = this.hoursSpent + effortInHours(effort);
    }

    public double updateEffortRemaining(TaskEffort effort) {
        if (this.effortRemaining - effortInHours(effort) < 0) {
            return this.effortRemaining = 0.0;
        }
        return this.effortRemaining = this.effortRemaining - effortInHours(effort);
    }

    public double updateExecutionPercentage() {
        double workTotal = this.hoursSpent + this.effortRemaining;
        double workDone = this.hoursSpent;
        if (workDone > workTotal || workDone == workTotal) {
            return this.executionPercentage = 1.0;
        } else {
            return this.executionPercentage = workDone / workTotal;
        }
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