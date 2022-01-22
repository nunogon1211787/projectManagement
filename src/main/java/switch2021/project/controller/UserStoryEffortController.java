package switch2021.project.controller;

import switch2021.project.model.*;
import switch2021.project.stores.*;
import switch2021.project.utils.App;

import java.util.ArrayList;
import java.util.List;

public class UserStoryEffortController {

    private Company company;
    private ProjectStore projStore;
    private List<Project> projectList; // nome da lista que contem o projectliST
    private Project proj;
    private SprintStore sprintStore;
    private List<Sprint> sprintList;
    private Sprint sprint;
    private SprintBacklog sprintBacklog;
    private UserStoryOfSprint userStoryOfSprint;

    public UserStoryEffortController() { //tem como função o dominio para os proximos passos
        this.company = App.getInstance().getCompany();
    }

    public List<Project> getCurrentProjectListByUserEmail(String email) {
        this.projectList = new ArrayList<>();
        this.projStore = this.company.getProjectStore();
        this.projectList = this.projStore.getCurrentProjectListByUserEmail(email);
        return this.projectList;
    }

    public Project getProjectByCode(String code) {
        this.proj = this.projStore.getProjectByCode(code);
        return this.proj;
    }

    public List<Sprint> getSprintList () {
        this.sprintList = new ArrayList<>();
        this.sprintStore = this.proj.getSprintStore();
        this.sprintList = this.sprintStore.getSprintList();
        return this.sprintList;
    }

    public Sprint getSprint(int id) {
        this.sprintStore = this.proj.getSprintStore();
        this.sprint = this.sprintStore.getSprint(id);
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

