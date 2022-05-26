package switch2021.project.controller.old;

import switch2021.project.model.*;
import switch2021.project.repositories.SprintRepository;
import switch2021.project.model.valueObject.UserStoryID;
import switch2021.project.repositories.UserStoryRepository;
import switch2021.project.model.Project.Project;
import switch2021.project.model.Sprint.Sprint;
import switch2021.project.model.UserStory.UserStory;

public class AddUserStoryToSprintBacklogController {

    /**
     * Attributes
     **/
    private final Company company;
    private Project project;
    private SprintRepository sprintList;
    private UserStoryRepository userStoryStore;
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
        this.project = company.getProjectStore().findById(code);
        return this.project;
    }

    public SprintRepository getSprintRepository() {
        sprintList = this.project.getSprintList();
        return sprintList;
    }

    public Sprint getSprint(String sprintId) {
        this.sprint = this.sprintList.findBySprintID(sprintId);
        return this.sprint;
    }

    public UserStoryRepository getUserStoryStore() {
        this.userStoryStore = this.project.getUserStoryStore();
        return this.userStoryStore;
    }

    public boolean addUserStoryToSprintBacklog(UserStoryID userStoryId) {
        UserStory userStory = this.userStoryStore.findByUserStoryId(userStoryId).get();
        this.sprint.saveUsInScrumBoard(userStory);
        return true;
    }
}
