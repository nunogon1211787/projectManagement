package switch2021.project.controller;

import switch2021.project.model.*;
import switch2021.project.stores.*;
import switch2021.project.utils.App;

import java.util.ArrayList;
import java.util.List;

public class UserStoryEffortController {

    // construtor com get instance e com company
    private Company company;
    private ProjectStore projStore;
    private List<Project> projectList; // nome da lista que contem o projectliST
    private Project proj;
    private SprintList sprintList;
    private List<Sprint> sprintsList;
    private Sprint sprint;
    private SprintBacklog sprintBacklog;
    private UserStoryOfSprint userStoryOfSprint;

    /**
     * Constructor to UI (with SINGLETON).
     */
    public UserStoryEffortController() { //tem como função o dominio para os proximos passos
        this.company = App.getInstance().getCompany();
    }

    /**
     * Constructor to test (without SINGLETON).
     */
    public UserStoryEffortController(Company company) { //tem como função o dominio para os proximos passos
        this.company = company;
    }


    public List<Project> getCurrentProjectListByUserEmail(String email) {
        this.projectList = new ArrayList<>();
        this.projStore = this.company.getProjectStore();
        this.projectList = this.projStore.getCurrentProjectsByUserEmail(email);
        return this.projectList;
    }

    public Project getProjectByCode(String code) {
        this.proj = this.company.getProjectStore().getProjectByCode(code);
        return this.proj;
    }

    public List<Sprint> getSprintsList() {
        this.sprintsList = new ArrayList<>();
        this.sprintList = this.proj.getSprintList();
        this.sprintsList = this.sprintList.getSprintList();
        return this.sprintsList;
    }

    public Sprint getSprint(int id) {
        this.sprintList = this.proj.getSprintList();
        this.sprint = this.sprintList.getSprint(id);
        return this.sprint;
    }

    public SprintBacklog getSprintBacklog() {
        this.sprintBacklog=this.sprint.getSprintBacklog();
        return this.sprintBacklog;
    }

    public UserStoryOfSprint getUserStory(int id_UserStory){
        this.userStoryOfSprint= this.sprintBacklog.getUserStory(id_UserStory);
        return this.userStoryOfSprint;
    }

    public boolean setEffort(int effort) {
        return this.userStoryOfSprint.setEstimateEffort(effort);
    }

}

