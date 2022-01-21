package switch2021.project.model;

import lombok.Getter;
import lombok.Setter;
import switch2021.project.stores.TaskStore;

import java.time.LocalDate;
import java.util.Objects;

@Getter
@Setter
public class Sprint {

    /**
     * Atributos da classe Sprint
     **/
    private int id_Sprint;
    private String name;
    private final TaskStore taskstore;
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
        this.taskstore = new TaskStore();
    }


    /**
     * Add User Story to the Sprintbacklog
     **/
    public boolean addStoryToSprintBacklog(UserStory us, int effort) {
        this.sprintBacklog.saveUserStoryOfSprint(sprintBacklog.createUSerStoryOfSprint(us, effort));
        return true;
    }


    /**
     * Method to change Sprint EndDate
     **/
    public void changeEndDate(int sprintDurationInWeeks) {
        this.endDate = startDate.plusDays(sprintDurationInWeeks * 7L);
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
        return (this.startDate.isBefore(LocalDate.now()) && this.endDate.isAfter(LocalDate.now()));
    }


    /**
     * Override Methods
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Sprint)) return false;
        Sprint sprint = (Sprint) o;
        return id_Sprint == sprint.id_Sprint && Objects.equals(name, sprint.name) &&
                Objects.equals(taskstore, sprint.taskstore) && Objects.equals(sprintBacklog, sprint.sprintBacklog)
                && Objects.equals(startDate, sprint.startDate) && Objects.equals(endDate, sprint.endDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_Sprint, name, taskstore, sprintBacklog, startDate, endDate);
    }
}
