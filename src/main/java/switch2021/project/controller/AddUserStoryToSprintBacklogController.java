package switch2021.project.controller;

import switch2021.project.model.*;
import switch2021.project.stores.SprintList;

public class AddUserStoryToSprintBacklogController {

    /**
     * Attributes
     **/

    private final Company company;
    private Project project;
    private SprintList sprintList;
    private ProductBacklog productBacklog;
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

    public SprintList getSprintStore() {
        sprintList = this.project.getSprintList();
        return sprintList;
    }

    public Sprint getSprint(int sprintId) {
        this.sprint = this.sprintList.getSprint(sprintId);
        return this.sprint;
    }

    public ProductBacklog getProductBacklog() {
        this.productBacklog = this.project.getProductBacklog();
        return this.productBacklog;
    }


    public boolean addUserStoryToSprintBacklog(int userStoryId) {
        UserStory userStory = this.productBacklog.getUserStoryById(userStoryId);
        this.sprint.getSprintBacklog().saveUserStoryToSprintBacklog(userStory);
        return true;
    }
}
