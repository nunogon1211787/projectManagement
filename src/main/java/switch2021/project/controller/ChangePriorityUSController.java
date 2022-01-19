package switch2021.project.controller;

import switch2021.project.model.Company;
import switch2021.project.model.ProductBacklog;
import switch2021.project.model.Project;
import switch2021.project.model.UserStory;
import switch2021.project.stores.ProjectStore;

import java.util.List;

public class ChangePriorityUSController {

    private Company company;
    private ProjectStore projectStore;
    private Project project;
    private ProductBacklog productBacklog;
    private UserStory userStory;
    List<Project> arrayProject;
    List<UserStory> userStoryList;

    public List<Project> getProjectListByMemberAssociated (String email){
        this.projectStore = this.company.getProjectStore();
        this.arrayProject = this.projectStore.getProjectListByMemberAssociated(email);
        return arrayProject;
    }

    public Project getProject(String code){
        this.project = this.company.getProjectStore().getProjectByCode(code);
        return this.project;
    }

    public List<UserStory> getUserStoryList(){
        this.productBacklog = this.project.getProductBacklog();
        this.userStoryList = this.project.getProductBacklog().getUserStoryList();
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
