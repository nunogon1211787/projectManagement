package switch2021.project.controller;

import switch2021.project.model.Company;
import switch2021.project.model.UserStory;
import switch2021.project.utils.App;

public class AddUserStoryToSprintBacklogController {
        private final Company company;
        private final UserStory userStory;
    private final long id;
        private final String projCode;
        private final int effort;

    public AddUserStoryToSprintBacklogController(long id, String projCode,int effort) {
        this.company =  App.getInstance().getCompany();
        this.userStory = company.getProjectStore().getProductBacklog(projCode).getUserStoryById(id);
        this.id = id;
        this.projCode = projCode;
        this.effort = effort;
    }

    public boolean addUserStoryToSprintBacklog() {
        company.getProjectStore().getProjectByCode(projCode).getSprintStore().getSprint(id).addStoryToSprintBacklog(userStory,effort);
        return true;
    }
}
