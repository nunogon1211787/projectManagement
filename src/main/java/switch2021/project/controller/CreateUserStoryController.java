package switch2021.project.controller;

import switch2021.project.model.*;
import switch2021.project.stores.ProjectStore;
import switch2021.project.utils.App;

import java.util.Collections;
import java.util.List;


//https://www.devmedia.com.br/padrao-de-projeto-singleton-em-java/26392
//https://www.geeksforgeeks.org/singleton-class-java/



public class CreateUserStoryController {


    /**
     * Attributes
     **/

    private final Company company;
    private Project project;

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
        ProjectStore projectStore = this.company.getProjectStore();
        List<Project> arrayProject = projectStore.getProjectsByUserEmail(email);
        return Collections.unmodifiableList(arrayProject);
    }

    public Project getProjectByCode(String code) {
        this.project = this.company.getProjectStore().getProjectByCode(code);
        return this.project;
    }

    public boolean createUserStory(String name, int priority, String description, int  estimateEffort) {
        ProductBacklog productBacklog = this.project.getProductBacklog();
        UserStory userStory = productBacklog.createUserStory(name, priority, description, estimateEffort);
        return productBacklog.saveUserStory(userStory);
    }

}
