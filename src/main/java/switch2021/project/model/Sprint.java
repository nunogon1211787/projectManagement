package switch2021.project.model;

import switch2021.project.stores.TaskStore;

import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Sprint {

    /**
     * Atributos da classe Sprint
     **/
    private long id;
    private String name;
    private TaskStore taskstore;
    private SprintBacklog sprintBacklog;
    private LocalDate startDate;
    private LocalDate endDate;
    private int sprintDuration;


    /**
     * Constructor of Sprint
     **/

    public Sprint(long id, String name, LocalDate startDate, int sprintDuration) {
        checkSprintNameRules(name);
        //checkSprintStartDateRules(startDate);
        //checkSprintSprintDurationRules(sprintDuration);
        this.id = ID_GENERATOR.getAndIncrement();
        this.name = name;
        this.startDate = startDate;
        this.sprintBacklog = new SprintBacklog();
        this.sprintDuration = sprintDuration;
    }

    public boolean addStoryToSprintBacklog(UserStory us, int effort) {
        this.sprintBacklog.addUserStory(sprintBacklog.createUSerStoryOfSprint(us, effort));
        return true;
    }

    /**
     * Change Sprint End Date
     **/

    public void changeSprintEndDate(LocalDate end) {
        this.endDate = end;
    }

    /**
     * Getters and Setters
     **/

    public long getID() {
        return id;
    }

    public String getname() {
        return name;
    }

    public String setname() {
        return name;
    }

    public TaskStore getTaskStore() {
        return taskstore;
    }

    public TaskStore setTaskStore() {
        return taskstore;
    }

    public SprintBacklog getSprintBacklog() {
        return sprintBacklog;
    }

    public SprintBacklog setSprintBacklog() {
        return sprintBacklog;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate setStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public LocalDate setEndDate() {
        return endDate;
    }

    public int getSprintDuration() {
        return sprintDuration;
    }

    public int setSprintDuration() {
        return sprintDuration;
    }

    //Create ID Automatically
    private static AtomicInteger ID_GENERATOR = new AtomicInteger(1);

    /**
     * Validation Methods for the Constructor
     **/

    private void checkSprintNameRules(String name) {
        if (name.trim().isEmpty())
            throw new IllegalArgumentException("Sprint Name cannot be empty.");
        if ((name.length() < 2))
            throw new IllegalArgumentException("Sprint Name must have at least 2 characters");
    }

    //private void checkSprintStartDateRules(LocalDate startDate) {
    //    if (startDate <= endDate)
    //        throw new IllegalArgumentException("StartDate cannot be empty.");
    //}

    //private void checkSprintSprintDurationRules(Project sprintDuration) {
    //    if ((sprintDuration < 0))
    //        throw new IllegalArgumentException("Username must be at least 2 characters");
    //}

}
