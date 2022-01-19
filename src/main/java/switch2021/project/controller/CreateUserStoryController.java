package switch2021.project.controller;

import switch2021.project.model.*;
import switch2021.project.stores.ProjectStore;
import switch2021.project.utils.App;

import java.util.List;

/**
 * >>>>>> https://www.devmedia.com.br/padrao-de-projeto-singleton-em-java/26392 || https://www.geeksforgeeks.org/singleton-class-java/ <<<<<<
 **/


public class CreateUserStoryController {

    private Company company;
    private ProjectStore projectStore;
    List<Project> arrayProject;
    private Project project;

    public CreateUserStoryController() {
        this.company = App.getInstance().getCompany();
    }

    public CreateUserStoryController(Company company) {
        this.company = company;
    }

    public CreateUserStoryController(Project project) {
        this.project = project;
    }


  //TODO - apagar o PO right?

  //  public List<Project> getProjectListWithPORight(String email) {
 //       List<Project> projectsAvailable = this.company.getProjectStore().getProjectListWithPORight(email); //CONFIRMAR ALTERAÇÃO
   //     return projectsAvailable;
      //  }

    public List<Project> getProjectListByMemberAssociated (String email){
        this.projectStore = this.company.getProjectStore();
        this.arrayProject = this.projectStore.getProjectListByUserEmail(email);
        return arrayProject;
    }

    public Project getProjectByCode(String code){
        this.project = this.company.getProjectStore().getProjectByCode(code);
        return this.project;
    }

    //TODO - criar novo metodo a ir ao sitio certo e eliminar este

  //  public boolean createUserStoryNEW(String code, UserStoryStatus userStoryStatus, int priority, String description, int timeEstimate) {
 //       ProductBacklog productBacklog = this.company.getProjectStore().getProductBacklog(code);
//        UserStory userStory = productBacklog.createUserStory(userStoryStatus, priority, description, timeEstimate);
  //      return productBacklog.saveUserStory(userStory);
//    }



    public boolean createUserStory(UserStoryStatus userStoryStatus, int priority, String description) {
      ProductBacklog productBacklog = this.project.getProductBacklog();
        UserStory userStory = productBacklog.createUserStory(userStoryStatus, priority, description);
        return productBacklog.saveUserStory(userStory);
    }

}
