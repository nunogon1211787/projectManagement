package switch2021.project.controller;

import switch2021.project.model.Company;
import switch2021.project.model.UserStory;
import switch2021.project.utils.App;

public class AddUserStoryToSprintBacklogController {

    private Company company;
    private UserStory userStory;
    private long id;
    private String projCode;
    private int effort;


    public AddUserStoryToSprintBacklogController(long id, String projCode, int effort) {
        this(App.getInstance().getCompany(), id, projCode, effort);
    }

    public AddUserStoryToSprintBacklogController(Company company, long id, String projCode, int effort) {
        this.company = company;
        this.userStory = company.getProjectStore().getProductBacklog(projCode).getUserStoryById(id);
        this.id = id;
        this.projCode = projCode;
        this.effort = effort;
    }

    public boolean addUserStoryToSprintBacklog() {
        company.getProjectStore().getProjectByCode(projCode).getSprintStore().getSprint(id).addStoryToSprintBacklog(userStory, effort);
        return true;
    }
}
