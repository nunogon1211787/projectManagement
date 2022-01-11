package switch2021.project.controller;

import switch2021.project.model.Company;
import switch2021.project.model.Project;
import switch2021.project.model.UserStory;
import switch2021.project.model.UserStoryStatus;
import switch2021.project.utils.App;

import java.util.List;

/**
 * >>>>>> https://www.devmedia.com.br/padrao-de-projeto-singleton-em-java/26392 || https://www.geeksforgeeks.org/singleton-class-java/ <<<<<<
 **/



public class CreateUserStoryController {

    private Company company;
    private Project project;
    private UserStory userStory;

    public CreateUserStoryController() {
        this.company = App.getInstance().getCompany();
    }

    public List<Project> getProjectListWithPORight(String email){
        List<Project> projectsAvailable = this.company.getProjectListWithPORight(email);
        return projectsAvailable;
    }

    public boolean createUserStory(String code, String userStoryStatus, int priority, String description, int timeEstimate) {
        this.project = this.company.getProject(code);
        try {
            return this.project.createUserStory(UserStoryStatus.valueOf(userStoryStatus.toUpperCase()), priority, description, timeEstimate);
        } catch (IllegalArgumentException exception) {
            return false;
        }
    }
}
