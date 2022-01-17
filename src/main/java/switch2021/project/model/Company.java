package switch2021.project.model;

import switch2021.project.stores.*;

import java.util.List;
import java.util.Objects;

public class Company {

    /**
     * Company Atributes
     * The company atributes are composed by all the store lists of a given project
     **/

    private SystemUserStore systemUserStore;
    private ProjectStore projectStore;
    private UserProfileStore userProfileStore;
    private ProjectStatusStore projectStatusStore;
    private UserStoryStatusStore userStoryStatusStore;
    private CustomerStore customerStore;
    private BusinessSectorStore businessSectorStore;
    private TypologyStore typologyStore;
    private RequestStore requestStore;


    /**
     * Company Constructor
     * The company constructor initializes and populates all the store lists.
     **/

    public Company() {
        this.systemUserStore = new SystemUserStore();
        this.projectStore = new ProjectStore();
        this.userProfileStore = new UserProfileStore();

        this.typologyStore = new TypologyStore();
        this.customerStore = new CustomerStore();
        this.businessSectorStore = new BusinessSectorStore();
        this.projectStatusStore = new ProjectStatusStore();
        this.userStoryStatusStore = new UserStoryStatusStore();
        this.requestStore = new RequestStore();

        this.userProfileStore.populateDefault();
        this.typologyStore.populateTypologyList();
        this.projectStatusStore.populateProjectStatusList();
        this.userStoryStatusStore.populateUserStoryStatusList();
    }

    /**
     * Company getter's
     * Getting Methods that return all the stores to other classes
     */

    //Project
    public ProjectStore getProjectStore() {
        return this.projectStore;
    }

    public ProductBacklog getProductBacklog(String code) {
        return this.projectStore.getProductBacklog(code);
    }

    public List<Project> getProjectListWithPORight(String email) {
        return projectStore.getProjectListWithPORight(email);
    }

    //SystemUser
    public SystemUserStore getSystemUserStore() {
        return this.systemUserStore;
    }

    //Profile
    public UserProfileStore getUserProfileStore() {
        return this.userProfileStore;
    }

    // SpecialProfile or ProjectRole (acho melhor a segunda opção)

    //Typology
    public TypologyStore getTypologyStore() {
        return this.typologyStore;
    }

    //ProjectStatus
    public ProjectStatusStore getProjectStatusStore() {
        return this.projectStatusStore;
    }

    //Customer
    public CustomerStore getCustomerStore() {
        return this.customerStore;
    }

    //BusinessSector
    public BusinessSectorStore getBusinessSectorStore() {
        return this.businessSectorStore;
    }

    public RequestStore getRequestStore() {return requestStore;}

    /**
     * Create Method
     **/

    public UserProfile createProfile(String name) {
        return new UserProfile(name);
    }

    /**
     * Getter Methods
     **/

    /*public List<UserProfile> getUserProfileList() {
        return this.getUserProfileList();
    }*/

    /* public List<UserProfile> getUserProfileListWithType(String type) {

        List<UserProfile> foundList = new ArrayList<>();

        for (int i = 0; i < this.userProfileStore.userProfileList.size(); i++) {

            if (this.userProfileStore.userProfileList.get(i).hasType(type)) {
                foundList.add(this.userProfileStore.userProfileList.get(i));
            }

        }

        return foundList;
    }*/

    ////Talvez mudar para não buscar por index
    public UserProfile getUserProfile(int index) {
        return new UserProfile(userProfileStore.getUserProfileList().get(index));
    }

    public UserProfile getUserProfile(String name) {
        UserProfile pro = null;
        for (int i = 0; i < userProfileStore.getUserProfileList().size(); i++) {
            if (Objects.equals(getUserProfile(i).getUserProfileName(), name)) {
                pro = getUserProfile(i);
                break;
            }
        }
        return pro;
    }




}