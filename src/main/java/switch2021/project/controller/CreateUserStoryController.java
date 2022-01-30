package switch2021.project.controller;

import switch2021.project.model.*;
import switch2021.project.stores.ProjectStore;
import switch2021.project.utils.App;

import java.util.List;


//https://www.devmedia.com.br/padrao-de-projeto-singleton-em-java/26392
//https://www.geeksforgeeks.org/singleton-class-java/



public class CreateUserStoryController {


    /**
     * Attributes
     **/

    private final Company company;
    private ProjectStore projectStore;
    private List<Project> arrayProject;
    private Project project;
    private ProductBacklog productBacklog;
    private UserStory userStory;


    /**
     * Constructor to UI (with SINGLETON)
     **/

 //   public CreateUserStoryController(){ this.company = App.getInstance().getCompany();}

    /**
     * Constructor to test (without SINGLETON)
     **/

    public CreateUserStoryController(Company company) {
        this.company = company;
    }

    /**
     * Methods
     **/

    public List<Project> getProjectListByUserEmail(String email) {
        this.projectStore = this.company.getProjectStore();
        this.arrayProject = this.projectStore.getProjectsByUserEmail(email);
        return arrayProject;
    }

    public Project getProjectByCode(String code) {
        this.project = this.company.getProjectStore().getProjectByCode(code);
        return this.project;
    }

    public boolean createUserStory(String name, int priority, String description, int  estimateEffort) {
        this.productBacklog = this.project.getProductBacklog();
        this.userStory = this.productBacklog.createUserStory(name, priority, description, estimateEffort);
        return this.productBacklog.saveUserStory(userStory);
    }
}
