package switch2021.project.controller;

import switch2021.project.model.Company;
import switch2021.project.model.ProductBacklog;
import switch2021.project.model.Project;
import switch2021.project.model.UserStory;
import switch2021.project.stores.ProjectStore;
import switch2021.project.utils.App;

import java.util.List;

public class ChangePriorityUSController {

    private Company company;
    private ProjectStore projectStore;
    private Project project;
    private ProductBacklog productBacklog;
    private UserStory userStory;
    List<Project> arrayProject;
    List<UserStory> userStoryList;

    public ChangePriorityUSController(){
        this.company = App.getInstance().getCompany();
    }

    public List<Project> getCurrentProjectListByUserEmail (String email){
        this.projectStore = this.company.getProjectStore();
        this.arrayProject = this.projectStore.getCurrentProjectListByUserEmail(email);
        return arrayProject;
    }

    public Project getProject(String code){
        this.project = this.company.getProjectStore().getProjectByCode(code);
        return this.project;
    }

    public List<UserStory> getUserStoryList(){
        this.productBacklog = this.project.getProductBacklog();
        this.userStoryList = this.productBacklog.getUserStoryList();
        return this.userStoryList;
    }

    public UserStory getUS(long idUS){
        this.userStory = this.productBacklog.getUserStoryById(idUS);
        return this.userStory;
    }

    public boolean setPriority(int x){
        return this.userStory.setPriority(x);
    }

}
