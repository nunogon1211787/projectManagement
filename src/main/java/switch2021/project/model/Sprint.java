package switch2021.project.model;

import lombok.Getter;
import switch2021.project.stores.TaskStore;

import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Getter
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


    /**
     * Constructor of Sprint
     **/

    public Sprint(long id, String name, LocalDate startDate) {
        checkSprintNameRules(name);
        this.id = id;
        this.name = name;
        this.startDate = startDate;
        this.sprintBacklog = new SprintBacklog();
    }

    public boolean addStoryToSprintBacklog(UserStory us, int effort) {
        this.sprintBacklog.addUserStory(sprintBacklog.createUSerStoryOfSprint(us, effort));
        return true;
    }

    /**
     * Method to change Sprint EndDate
     **/


    public LocalDate changeEndDate(int sprintDurationInWeeks) {

        return startDate.plusDays(sprintDurationInWeeks * 7);
    }


    /**
     * Validation Methods for the Constructor
     **/

    private void checkSprintNameRules(String name) {
        if (name.trim().isEmpty())
            throw new IllegalArgumentException("Sprint Name cannot be empty.");
        if ((name.length() < 2))
            throw new IllegalArgumentException("Sprint Name must have at least 2 characters");
    }



}
