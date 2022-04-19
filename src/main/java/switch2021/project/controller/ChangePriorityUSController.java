package switch2021.project.controller;

import switch2021.project.model.Company;
import switch2021.project.model.UserStory.UserStoryId;
import switch2021.project.model.UserStory.UserStoryStore;
import switch2021.project.model.Project.Project;
import switch2021.project.model.UserStory.UserStory;
import switch2021.project.repositories.ProjectStore;

import java.util.List;

public class ChangePriorityUSController {

    /**
     * Attributes
     **/
    private final Company company;
    private Project project;
    private UserStoryStore userStoryStore;
    private UserStory userStory;
    List<Project> arrayProject;
    List<UserStory> userStoryList;


    /**
     * Constructor to test (without SINGLETON)
     **/
    public ChangePriorityUSController(Company company) {
        this.company = company;
    }

    /**
     * Methods
     **/
    public ProjectStore getProjectStore() {
        return company.getProjectStore();
    }

    public Project getProject(String code) {
        this.project = company.getProjectStore().getProjectByCode(code);
        return this.project;
    }

    public UserStoryStore getUserStoryStore() {
        this.userStoryStore = this.project.getUserStoryStore();
        return this.userStoryStore;
    }

    public UserStory getUserStory(UserStoryId userStoryId) {
        this.userStory = this.userStoryStore.findUserStoryById(userStoryId.toString());
        return this.userStory;
    }

    public List<Project> getCurrentProjectListByUserEmail(String email) {
        this.arrayProject = this.company.getProjectStore().getCurrentProjectsByUserEmail(email);
        return arrayProject;
    }

    public Project getProjectByCode(String code) {
        this.project = this.company.getProjectStore().getProjectByCode(code);
        return this.project;
    }

    public List<UserStory> getUserStoryList() {
        this.userStoryStore = this.project.getUserStoryStore();
        this.userStoryList = this.userStoryStore.findActiveUserStoryList();
        return this.userStoryList;
    }

    public UserStory getUS(UserStoryId userStoryId) {
        this.userStory = this.userStoryStore.findUserStoryById(userStoryId.toString());
        return this.userStory;
    }

    public boolean setPriority(int x) {
        return this.userStory.setPriority(x);
    }
}
