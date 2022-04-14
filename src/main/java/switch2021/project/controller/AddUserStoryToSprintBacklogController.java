package switch2021.project.controller;

import switch2021.project.model.*;
import switch2021.project.model.Sprint.SprintStore;
import switch2021.project.model.UserStory.UserStoryId;
import switch2021.project.model.UserStory.UserStoryStore;
import switch2021.project.model.Project.Project;
import switch2021.project.model.Sprint.Sprint;
import switch2021.project.model.UserStory.UserStory;

public class AddUserStoryToSprintBacklogController {

    /**
     * Attributes
     **/
    private final Company company;
    private Project project;
    private SprintStore sprintList;
    private UserStoryStore userStoryStore;
    private Sprint sprint;


    /**
     * Constructor to test (without SINGLETON)
     **/
    public AddUserStoryToSprintBacklogController(Company company) {
        this.company = company;
    }


    /**
     * Methods
     **/
    public Project getProject(String code) {
        this.project = company.getProjectStore().getProjectByCode(code);
        return this.project;
    }

    public SprintStore getSprintStore() {
        sprintList = this.project.getSprintList();
        return sprintList;
    }

    public Sprint getSprint(int sprintId) {
        this.sprint = this.sprintList.getSprintById(sprintId);
        return this.sprint;
    }

    public UserStoryStore getProductBacklog() {
        this.userStoryStore = this.project.getUserStoryStore();
        return this.userStoryStore;
    }

    public boolean addUserStoryToSprintBacklog(UserStoryId userStoryId) {
        UserStory userStory = this.userStoryStore.findUserStoryById(userStoryId);
        this.sprint.saveUsInSprintBacklog(userStory);
        return true;
    }
}
