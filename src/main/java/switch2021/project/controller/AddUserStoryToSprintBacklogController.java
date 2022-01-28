package switch2021.project.controller;

import switch2021.project.model.*;
import switch2021.project.stores.SprintList;

public class AddUserStoryToSprintBacklogController {

    private final Company company;
    private Project project;
    private SprintList sprintList;
    private ProductBacklog productBacklog;
    private UserStory userStory;
    private Sprint sprint;

/*    public AddUserStoryToSprintBacklogController() {
        this.company = App.getInstance().getCompany();
    }*/

    /**
     * Constructor to test (without SINGLETON).
     */
    public AddUserStoryToSprintBacklogController(Company company) {
        this.company = company;
    }

    public Project getProject(String code) {
        return this.project = company.getProjectStore().getProjectByCode(code);
    }

    public SprintList getSprintStore() {
        return sprintList = this.project.getSprintList();
    }

    public Sprint getSprint(int sprintId) {
        return this.sprint = this.sprintList.getSprint(sprintId);
    }

    public ProductBacklog getProductBacklog() {
        return this.productBacklog = this.project.getProductBacklog();
    }

    public UserStory getUserStory(int userStoryId) {
        return this.userStory = this.productBacklog.getUserStoryById(userStoryId);
    }

    public boolean addUserStoryToSprintBacklog(int effort, UserStoryStatus status) {
        this.sprint.getSprintBacklog().saveUserStoryOfSprint(this.sprint.getSprintBacklog().createUSerStoryOfSprint(this.userStory,effort,status));
        return true;
    }
}
