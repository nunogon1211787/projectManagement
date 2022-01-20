package switch2021.project.controller;

import switch2021.project.model.Company;
import switch2021.project.model.UserStory;
import switch2021.project.utils.App;

public class AddUserStoryToSprintBacklogController {

    public AddUserStoryToSprintBacklogController(long userStoryId,long sprintId, String projCode,int effort) {
        Company company = App.getInstance().getCompany();
        UserStory userStory = company.getProjectStore().getProductBacklog(projCode).getUserStoryById(userStoryId);
        company.getProjectStore().getProjectByCode(projCode).getSprintStore().getSprint(sprintId).getSprintBacklog().validateUserStoryOfSprintAddition(userStoryId);
        company.getProjectStore().getProjectByCode(projCode).getSprintStore().getSprint(sprintId).addStoryToSprintBacklog(userStory,effort);
    }

}
