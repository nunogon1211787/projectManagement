package switch2021.project.controller;

import switch2021.project.model.Company;
import switch2021.project.model.ProductBacklog;
import switch2021.project.model.Project;
import switch2021.project.model.UserStory;
import switch2021.project.stores.ProjectStore;

import java.util.List;

public class ProductBacklogController {


    private Company company;
    private ProjectStore projectStore;
    private Project project;
    private ProductBacklog productBacklog;
    private List<Project> arrayProject;
    private List<UserStory> userStoryList;

    public ProductBacklogController(Company company) {
        this.company = company;
    }

    public List<Project> getProjectListByUserEmail(String email){
        this.projectStore = this.company.getProjectStore();
        this.arrayProject = this.projectStore.getProjectListByUserEmail(email);
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
