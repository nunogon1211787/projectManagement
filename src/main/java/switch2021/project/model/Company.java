package switch2021.project.model;

import java.util.List;

public class Company {

    /**
     * Company Atributes
     * The company atributes are composed by all the store lists of a given project
     **/

    private SystemUserStore systemUserStore;
    private ProjectStore projectStore;

    private UserProfileStore userProfileStore;

    private ProjectStatusStore projectStatusStore;
    private CustomerStore customerStore;
    private BusinessSectorStore businessSectorStore;
    private TypologyStore typologyStore;

    // falta colocar a chamada para o request quando se criar a página;
    List<Request> requestList;

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

        this.userProfileStore.populateDefault();
        this.typologyStore.populateTypologyList();
        this.projectStatusStore.populateProjectStatusList();
    }

    /**
     * Company getter's
     * Getting Methods that return all the stores to other classes
     */

    //Project
    public ProjectStore getProjectStore() {
        return this.projectStore;
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


    //Request

    /**
     * Add Method
     */

    public boolean addRequest(Request request) {
        this.requestList.add(request);
        return true;

    }

    /**
     * Validation Method
     */

    private boolean validateRequest(Request newRequest) {

        //Check if request already exist
        for (Request up : requestList) {
            if (up.equals(newRequest)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Save Method
     */

    public boolean saveRequest(Request newRequest) {

        boolean result = false;

        if (validateRequest(newRequest)) {
            addRequest(newRequest);
            result = true;
        }
        return result;
    }
}