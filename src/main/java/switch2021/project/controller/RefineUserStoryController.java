package switch2021.project.controller;

import switch2021.project.model.*;
import switch2021.project.stores.ProjectStore;
import switch2021.project.utils.App;

import java.util.List;

public class RefineUserStoryController {
    private final Company company;
    private Project project;
    private ProductBacklog productBacklog;
    private UserStory userStoryParent;

    /**
     * Constructor to UI (with SINGLETON).
     */
    public RefineUserStoryController(){
        this.company = App.getInstance().getCompany();
    }

    /**
     * Constructor to test (without SINGLETON).
     */
    public RefineUserStoryController(Company company){ this.company = company;}


    public List<Project> getProjectList(String email){
        ProjectStore projSt = this.company.getProjectStore();
        List<Project> projectList;
        return projectList = projSt.getCurrentProjectListByUserEmail(email);
    }
    public Project getProject(String projectCode){
        return this.project = this.company.getProjectStore().getProjectByCode(projectCode);
    }
    public ProductBacklog getProductBacklog(){
        this.productBacklog = project.getProductBacklog();
        return this.productBacklog;
    }
    public UserStory getUserStory(int idUserStory){
        this.userStoryParent = productBacklog.getUserStoryById(idUserStory);
        return userStoryParent;
    }

    public boolean updateRefinedUserStoryStatus(){
        this.userStoryParent.setUserStoryStatus(App.getInstance().getCompany().getUserStoryStatusStore().getUserStoryStatusByDescription("Refined"));
        return true;
    }

    public boolean createUserStory(String description, int priority, UserStoryStatus userStoryStatus){
        UserStory userStoryCreated = this.productBacklog.createUserStoryRefine(userStoryParent, userStoryStatus, priority, description);
        this.productBacklog.saveUserStory(userStoryCreated);
        return true;
    }
}
