package switch2021.project.controller;

import switch2021.project.model.*;
import switch2021.project.stores.ProjectStore;
import switch2021.project.utils.App;

public class RefineUserStoryController {

    /**
     * Attributes
     **/

    private final Company company;
    private Project project;
    private ProductBacklog productBacklog;
    private UserStory userStoryParent;

    /**
     * Constructor to UI (with SINGLETON)
     **/

    public RefineUserStoryController(){
        this.company = App.getInstance().getCompany();
    }

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
        return this.project = company.getProjectStore().getProjectByCode(projectCode);
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
        userStoryParent.setUserStoryStatus(company.getUserStoryStatusStore().getUserStoryStatusByDescription("Refined"));
        return true;
    }
    public boolean createUserStory(String description, int priority, UserStoryStatus userStoryStatus){
        UserStory userStoryCreated = this.productBacklog.createUserStoryRefine(userStoryParent, userStoryStatus, priority, description);
        this.productBacklog.saveUserStory(userStoryCreated);
        return true;
    }
}
