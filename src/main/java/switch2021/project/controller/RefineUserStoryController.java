package switch2021.project.controller;

import switch2021.project.model.*;
import switch2021.project.stores.ProjectStore;
import switch2021.project.utils.App;

import java.util.List;

public class RefineUserStoryController {
    private final Company company;
    private Project project;
    ProjectStore projSt;
    List<Project> projectList;
    private ProductBacklog productBacklog;
    private UserStory userStory;

    public RefineUserStoryController(){
        this.company = App.getInstance().getCompany();
        this.projSt = null;
        this.projectList = null;
    }

    public RefineUserStoryController(Company company){
        this.company = company;
        this.projSt = null;
        this.projectList = null;
    }

    public List<Project> getProjectList(){
        this.projSt = this.company.getProjectStore();
        this.projectList = projSt.getProjectList();
        return this.projectList;
    }
    public Project getProject(String projectCode){
        return this.project = projSt.getProjectByCode(projectCode);
    }
    public ProductBacklog getProductBacklog(){
        this.productBacklog = project.getProductBacklog();
        return this.productBacklog;
    }
    public UserStory getUserStory(int idUserStory){
        this.userStory = this.productBacklog.getUserStoryById(idUserStory);
        return userStory;
    }

    public boolean updateRefinedUserStoryStatus(UserStory userStoryParent){
        userStoryParent.setUserStoryStatus(App.getInstance().getCompany().getUserStoryStatusStore().getUserStoryStatusByDescription("Refined"));
        return true;
    }

    public boolean createUserStory(UserStory userStoryParent, String description, int priority, UserStoryStatus userStoryStatus){
        UserStory userStoryCreated = this.productBacklog.createUserStoryRefine(userStoryParent, userStoryStatus, priority, description);
        this.productBacklog.saveUserStory(userStoryCreated);
        return true;
    }

    public Company getCompany() {
        return company;
    }
}
