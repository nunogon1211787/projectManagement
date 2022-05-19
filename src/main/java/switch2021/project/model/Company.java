package switch2021.project.model;

import lombok.Getter;
import switch2021.project.factory.*;
import switch2021.project.repositories.UserStoryRepository;
import switch2021.project.repositories.old.ProjectStore;
import switch2021.project.repositories.UserRepository;
//import switch2021.project.repositories.TypologyRepository;
//import switch2021.project.repositories.UserProfileRepository;
import switch2021.project.stores.*;

@Getter
public class Company {

    /**
     * Company Attributes
     * The company attributes are composed by all the store lists of a given project
     **/
    private final UserRepository systemUserStore;
    private final ProjectStore projectStore;
//    private final UserProfileRepository userProfileStore;
    private final ProjectRoleStore projectRoleStore;
//    private final TypologyRepository typologyRepository;
    private final CustomerStore customerStore;
    private final BusinessSectorStore businessSectorStore;
    private final UserStoryStatusStore userStoryStatusStore;
    //private final RequestStore requestStore;


    //private final ProjectRoleFactory projectRoleFactory;
    private final UserStoryRepository userStoryStore;
    //private final UserStoryFactory userStoryFactory;


    /**
     * Company Constructor
     * The company constructor initializes and populates all the store lists
     **/
    public Company() {
        this.systemUserStore = new UserRepository();
        this.projectStore = new ProjectStore();
//        this.userProfileStore = new UserProfileRepository();
        //this.projectRoleFactory = new ProjectRoleFactory();
        this.projectRoleStore = new ProjectRoleStore(new ProjectRoleFactory());
//        this.typologyRepository = new TypologyRepository();
        this.customerStore = new CustomerStore();
        this.businessSectorStore = new BusinessSectorStore(new BusinessSectorFactory());
        this.userStoryStatusStore = new UserStoryStatusStore();
        //this.requestStore = new RequestStore();
        //this.userStoryFactory = new UserStoryFactory();
        this.userStoryStore = new UserStoryRepository();

        //this.userProfileStore.populateDefault();
        this.projectRoleStore.populateDefault();
//        this.typologyRepository.populateDefault();
        this.userStoryStatusStore.populateDefault();
    }
}
