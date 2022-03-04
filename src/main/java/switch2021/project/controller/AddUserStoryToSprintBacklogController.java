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
        return this.project = company.getProjectStore().getProjectByCode(code);
    }

    public SprintList getSprintStore() {
        return sprintList = this.project.getSprints();
    }

    public Sprint getSprint(int sprintId) {
        return this.sprint = this.sprintList.getSprint(sprintId);
    }

    public ProductBacklog getProductBacklog() {
        return this.productBacklog = this.project.getProductBacklog();
    }


    public boolean addUserStoryToSprintBacklog(int userStoryId) {
        UserStory userStory = this.productBacklog.getUserStoryById(userStoryId);
        this.sprint.getSprintBacklog().saveUserStoryToSprintBacklog(userStory);
        return true;
    }
}
