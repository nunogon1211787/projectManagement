package switch2021.project.controller;

import switch2021.project.model.Company;
import switch2021.project.model.ProductBacklog;
import switch2021.project.model.Project;
import switch2021.project.model.UserStory;

import java.util.List;

public class ProductBacklogController {


    private Company company;
    private switch2021.project.stores.ProjectStore projectStore;
    private Project project;
    private ProductBacklog productBacklog;
    List<Project> arrayProject;
    List<UserStory> userStoryList;

    public ProductBacklogController(Company company) {
        this.company = company;
    }

    public List<Project> getAllProjectListByUserEmail (String email){
        this.projectStore = this.company.getProjectStore();
        this.arrayProject = this.projectStore.getAllProjectListByUserEmail(email);
        return arrayProject;
    }

    public Project getProject(String code){
        this.project = this.company.getProjectStore().getProjectByCode(code);
        return this.project;
    }

    public List<UserStory> getUsSortedByPriority(){
        this.productBacklog = this.project.getProductBacklog();
        this.userStoryList = this.project.getProductBacklog().getUsSortedByPriority();
        return this.userStoryList;
    }







}
