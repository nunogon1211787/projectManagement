package switch2021.project.controller.old;

import switch2021.project.model.*;
import switch2021.project.model.Project.Project;
import switch2021.project.model.Sprint.Sprint;
import switch2021.project.model.Sprint.SprintStore;
import switch2021.project.repositories.ProjectStore;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UserStoryEffortController {

    /**
     * Attributes
     **/
    private final Company company;
    private Project proj;
    private SprintStore sprintList;
    private List<Sprint> sprintsList;
    private Sprint sprint;


    /**
     * Constructor to test (without SINGLETON)
     **/
    public UserStoryEffortController(Company company) { //tem como função o dominio para os proximos passos
        this.company = company;
    }


    /**
     * Methods
     **/
    public List<Project> getCurrentProjectListByUserEmail(String email) {
        ProjectStore projStore = this.company.getProjectStore();
        List<Project> projectList = projStore.getCurrentProjectsByUserEmail(email);
        return Collections.unmodifiableList(projectList);
    }

    public Project getProjectByCode(String code) {
        this.proj = this.company.getProjectStore().findById(code);
        return this.proj;
    }

    public List<Sprint> getSprintsList() {
        this.sprintsList = new ArrayList<>();
        this.sprintList = this.proj.getSprintList();
        this.sprintsList = this.sprintList.findSprints();
        return Collections.unmodifiableList(sprintsList);
    }

    public Sprint getSprint(String sprintID) {
        this.sprintList = this.proj.getSprintList();
        this.sprint = this.sprintList.findSprintById(sprintID);
        return this.sprint;
    }

//    public SprintBacklog getSprintBacklog() {
//        this.sprintBacklog = this.sprint.getSprintBacklog();
//        return this.sprintBacklog;
//    }
}

