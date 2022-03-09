package switch2021.project.controller;

import switch2021.project.model.*;
import switch2021.project.stores.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UserStoryEffortController {

    /**
     * Attributes
     **/

    private final Company company;
    private Project proj;
    private SprintList sprintList;
    private List<Sprint> sprintsList;
    private Sprint sprint;
    private SprintBacklog sprintBacklog;

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
        List<Project> projectList = new ArrayList<>();
        ProjectStore projStore = this.company.getProjectStore();
        projectList = projStore.getCurrentProjectsByUserEmail(email);
        return Collections.unmodifiableList(projectList);
    }

    public Project getProjectByCode(String code) {
        this.proj = this.company.getProjectStore().getProjectByCode(code);
        return this.proj;
    }

    public List<Sprint> getSprintsList() {
        this.sprintsList = new ArrayList<>();
        this.sprintList = this.proj.getSprints();
        this.sprintsList = this.sprintList.getSprintList();
        return Collections.unmodifiableList(sprintsList);
    }

    public Sprint getSprint(int id) {
        this.sprintList = this.proj.getSprints();
        this.sprint = this.sprintList.getSprint(id);
        return this.sprint;
    }

    public SprintBacklog getSprintBacklog() {
        this.sprintBacklog = this.sprint.getSprintBacklog();
        return this.sprintBacklog;
    }

}

