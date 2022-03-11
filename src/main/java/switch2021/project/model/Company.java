package switch2021.project.model;

import lombok.Getter;
import switch2021.project.stores.*;


@Getter
public class Company {

    /**
     * Company Attributes
     * The company attributes are composed by all the store lists of a given project
     **/
    private final SystemUserStore systemUserStore;
    private final ProjectStore projectStore;
    private final UserProfileStore userProfileStore;
    private final ProjectRoleStore projectRoleStore;
    private final TypologyStore typologyStore;
    private final CustomerStore customerStore;
    private final BusinessSectorStore businessSectorStore;
    private final ProjectStatusStore projectStatusStore;
    private final UserStoryStatusStore userStoryStatusStore;
    private final RequestStore requestStore;
    private final TaskStatusStore taskStatusStore;
    private final TaskTypeStore taskTypeStore;


    /**
     * Company Constructor
     * The company constructor initializes and populates all the store lists
     **/
    public Company() {
        this.systemUserStore = new SystemUserStore();
        this.projectStore = new ProjectStore();
        this.userProfileStore = new UserProfileStore();
        this.projectRoleStore = new ProjectRoleStore();
        this.typologyStore = new TypologyStore();
        this.customerStore = new CustomerStore();
        this.businessSectorStore = new BusinessSectorStore();
        this.projectStatusStore = new ProjectStatusStore();
        this.userStoryStatusStore = new UserStoryStatusStore();
        this.requestStore = new RequestStore();
        this.taskStatusStore = new TaskStatusStore();
        this.taskTypeStore = new TaskTypeStore();

        this.userProfileStore.populateDefault();
        this.projectRoleStore.populateDefault();
        this.typologyStore.populateDefault();
        this.projectStatusStore.populateDefault();
        this.userStoryStatusStore.populateDefault();
        this.taskStatusStore.populateDefault();
        this.taskTypeStore.populateDefault();
    }
}
