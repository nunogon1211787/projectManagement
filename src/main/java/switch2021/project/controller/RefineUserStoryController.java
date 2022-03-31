package switch2021.project.controller;

import switch2021.project.model.*;
import switch2021.project.model.Project.ProductBacklog;
import switch2021.project.model.Project.Project;
import switch2021.project.model.UserStory.UserStory;
import switch2021.project.model.valueObject.UserStoryStatus;

public class RefineUserStoryController {

    /**
     * Attributes
     **/
    private final Company company;
    private Project project;
    private ProductBacklog productBacklog;
    private UserStory userStoryParent;


    /**
     * Constructor to test (without SINGLETON).
     **/
    public RefineUserStoryController(Company company){
        this.company = company;
    }


    /**
     * Methods
     **/
    public Project getProject(String projectCode){
        this.project = company.getProjectStore().getProjectByCode(projectCode);
        return this.project;
    }

    public ProductBacklog getProductBacklog(){
        this.productBacklog = this.project.getProductBacklog();
        return this.productBacklog;
    }

    public UserStory getUserStory(int idUserStory){
        this.userStoryParent = this.productBacklog.getUserStoryById(idUserStory);
        return userStoryParent;
    }

    public boolean updateRefinedUserStoryStatus(UserStory userStoryParent){
        return userStoryParent.setUserStoryStatusBoolean(company.getUserStoryStatusStore().getUserStoryStatusByDescription("Refined"));
    }

    public boolean createUserStory(String description, int priority, UserStoryStatus userStoryStatus){
        UserStory userStoryCreated = this.productBacklog.RefineUserStory(userStoryParent, userStoryStatus, priority, description);
        this.productBacklog.getUserStoryList().add(userStoryCreated);
        return true;
    }
}
