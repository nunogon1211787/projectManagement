package switch2021.project.controller;

import switch2021.project.model.*;
import switch2021.project.stores.ProjectStore;

import java.util.List;

/**
 * >>>>>> https://www.devmedia.com.br/padrao-de-projeto-singleton-em-java/26392 || https://www.geeksforgeeks.org/singleton-class-java/ <<<<<<
 **/


public class CreateUserStoryController {

    private Company company;
    private ProjectStore projectStore;
    List<Project> arrayProject;
    private Project project;


    public CreateUserStoryController(Company company) {
        this.company = company;
    }

    public List<Project> getAllProjectListByUserEmail(String email) {
        this.projectStore = this.company.getProjectStore();
        this.arrayProject = this.projectStore.getAllProjectListByUserEmail(email);
        return arrayProject;
    }

    public Project getProjectByCode(String code) {
        this.project = this.company.getProjectStore().getProjectByCode(code);
        return this.project;
    }



    public boolean createUserStory(UserStoryStatus userStoryStatus, int priority, String description) {
        ProductBacklog productBacklog = this.project.getProductBacklog();
        UserStory userStory = productBacklog.createUserStory(userStoryStatus, priority, description);
        return productBacklog.saveUserStory(userStory);
    }

}
