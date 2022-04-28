package switch2021.project.controller.old;

import switch2021.project.model.*;
import switch2021.project.model.UserStory.UserStoryID;
import switch2021.project.model.UserStory.RepoUserStory;
import switch2021.project.model.Project.Project;
import switch2021.project.model.UserStory.UserStory;

public class RefineUserStoryController {

    /**
     * Attributes
     **/
    private final Company company;
    private Project project;
    private RepoUserStory userStoryStore;
    private UserStory userStoryParent;


    /**
     * Constructor to test (without SINGLETON).
     **/
    public RefineUserStoryController(Company company){
        this.company = company;
    }


    /**
     * Methods
     **/
    public Project getProject(String projectCode){
        this.project = company.getProjectStore().findById(projectCode);
        return this.project;
    }

    public RepoUserStory getUserStoryStore(){
        this.userStoryStore = this.project.getUserStoryStore();
        return this.userStoryStore;
    }

    public UserStory getUserStory(UserStoryID idUserStory){
        this.userStoryParent = this.userStoryStore.findUserStoryById(idUserStory.toString());
        return userStoryParent;
    }
//TODO CDC ver como podemos fazer update status us refined

//    public boolean updateRefinedUserStoryStatus(UserStory userStoryParent){
//        return userStoryParent.setUserStoryStatusBoolean(company.getUserStoryStatusStore().getUserStoryStatusByDescription("Refined"));
//    }

    public boolean createUserStory(String description, int priority){
        UserStory userStoryCreated = this.userStoryStore.refineUserStory(userStoryParent, priority, description);
        this.userStoryStore.getUserStoryList().add(userStoryCreated);
        return true;
    }
}
