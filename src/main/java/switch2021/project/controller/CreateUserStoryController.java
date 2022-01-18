package switch2021.project.controller;

import switch2021.project.model.*;
import switch2021.project.utils.App;

import java.util.List;

/**
 * >>>>>> https://www.devmedia.com.br/padrao-de-projeto-singleton-em-java/26392 || https://www.geeksforgeeks.org/singleton-class-java/ <<<<<<
 **/


public class CreateUserStoryController {

    private Company company;

    public CreateUserStoryController() {
        this.company = App.getInstance().getCompany();
    }

    public CreateUserStoryController(Company company) {
        this.company = company;
    }

    public boolean createUserStory(String code, UserStoryStatus userStoryStatus, int priority, String description, int timeEstimate) {
        ProductBacklog productBacklog = this.company.getProductBacklog(code);
        UserStory userStory = productBacklog.createUserStory(userStoryStatus, priority, description, timeEstimate);
        return productBacklog.saveUserStory(userStory);
    }

    public List<Project> getProjectListWithPORight(String email) {
        List<Project> projectsAvailable = this.company.getProjectListWithPORight(email);
        return projectsAvailable;
    }


}
