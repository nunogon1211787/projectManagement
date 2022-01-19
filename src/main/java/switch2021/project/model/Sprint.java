package switch2021.project.model;

import switch2021.project.stores.TaskStore;

import java.time.LocalDate;
import java.util.List;

public class Sprint {

    /**
     * Atributos da classe Sprint
     **/
    private long id;
    private TaskStore taskstore;
    private SprintBacklog sprintBacklog;
    private LocalDate startDate;
    private LocalDate endDate;


    /**
     * Construtor de Sprint
     **/

    public Sprint(LocalDate startDate, LocalDate endDate) {

        ///this.number  - TODO incrementar números automaticamente -> Id generator (método de salvar/validação)
        this.startDate = startDate;
        this.endDate = endDate;
        this.sprintBacklog = new SprintBacklog();
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
     * Getter
     **/
    public SprintBacklog getSprintBacklog() {
        return sprintBacklog;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public long getNumber() {
        return id;
    }
}
