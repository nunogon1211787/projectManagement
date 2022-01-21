package switch2021.project.model;

import switch2021.project.stores.*;

public class Company {

    /**
     * Company Atributes
     * The company atributes are composed by all the store lists of a given project
     **/

    private SystemUserStore systemUserStore;
    private ProjectStore projectStore;
    private UserProfileStore userProfileStore;
    private ProjectRoleStore projectRoleStore;
    private TypologyStore typologyStore;
    private CustomerStore customerStore;
    private BusinessSectorStore businessSectorStore;
    private ProjectStatusStore projectStatusStore;
    private UserStoryStatusStore userStoryStatusStore;
    private RequestStore requestStore;


    /**
     * Company Constructor
     * The company constructor initializes and populates all the store lists.
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

        this.userProfileStore.populateDefault();
        this.projectRoleStore.populateDefault();
        this.typologyStore.populateDefault();
        this.projectStatusStore.populateProjectStatusList();
        this.userStoryStatusStore.populateUserStoryStatusList();
    }

    /** Company getter's
     * Getting Methods that return all the stores to other classes **/

    //Project Store
    public ProjectStore getProjectStore() {return this.projectStore;}

    //SystemUser Store
    public SystemUserStore getSystemUserStore() {return this.systemUserStore;}

    //Profile Store
    public UserProfileStore getUserProfileStore() {return this.userProfileStore;}

    // ProjectRole Store
    public ProjectRoleStore getProjectRolesStore() { return this.projectRoleStore;}

    //Typology Store
    public TypologyStore getTypologyStore() {return this.typologyStore;}

    //ProjectStatus Store
    public ProjectStatusStore getProjectStatusStore() {return this.projectStatusStore;}

    //Customer Store
    public CustomerStore getCustomerStore() {return this.customerStore;}

    //BusinessSector Store
    public BusinessSectorStore getBusinessSectorStore() {return this.businessSectorStore;}

    //Request Store
    public RequestStore getRequestStore() {return requestStore;}

    //User Story Status Store
    public UserStoryStatusStore getUserStoryStatusStore() {
        return userStoryStatusStore;
    }

    // Project Roles Store
    public ProjectRoleStore getProjectRoleStore() {
        return projectRoleStore;
    }
}
