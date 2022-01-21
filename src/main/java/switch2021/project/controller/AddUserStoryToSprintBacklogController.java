package switch2021.project.controller;

import switch2021.project.model.Company;
import switch2021.project.model.UserStory;
import switch2021.project.utils.App;

public class AddUserStoryToSprintBacklogController {

    private Company company;
    private UserStory userStory;
    private int sprintId;
    private String projCode;
    private int effort;


/*    public AddUserStoryToSprintBacklogController(int id, String projCode, int effort) {
        this(App.getInstance().getCompany(), id, projCode, effort);
    }*/

    public AddUserStoryToSprintBacklogController(Company company, int userStoryId,int sprintId, String projCode, int effort) {
        this.company = company;
        this.userStory = company.getProjectStore().getProductBacklog(projCode).getUserStoryById(userStoryId);
        this.sprintId = sprintId;
        this.projCode = projCode;
        this.effort = effort;
    }

    public boolean addUserStoryToSprintBacklog() {
        company.getProjectStore().getProjectByCode(projCode).getSprintStore().getSprint(sprintId).addStoryToSprintBacklog(userStory, effort);
        return true;
    }
}
