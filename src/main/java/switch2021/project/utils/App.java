package switch2021.project.utils;

import switch2021.project.model.Company;
import switch2021.project.stores.*;

/**
 * >>>>>> https://www.devmedia.com.br/padrao-de-projeto-singleton-em-java/26392 || https://www.geeksforgeeks.org/singleton-class-java/ <<<<<<
 **/

public class App {

    private static App instance;
    private static Company company;
//    private static TypologyStore typologyStore;
//    private static SystemUserStore systemUserStore;
//    private static ProjectStore projectStore;
//    private static UserProfileStore userProfileStore;
//    private static ProjectRoleStore projectRoleStore;
//    private static CustomerStore customerStore;
//    private static BusinessSectorStore businessSectorStore;
//    private static ProjectStatusStore projectStatusStore;
//    private static UserStoryStatusStore userStoryStatusStore;
//    private static RequestStore requestStore;


    private App() {
        company = new Company();
//        typologyStore = new TypologyStore();
//        systemUserStore = new SystemUserStore();
//        projectStore = new ProjectStore();
//        userProfileStore = new UserProfileStore();
//        projectRoleStore = new ProjectRoleStore();
//        customerStore = new CustomerStore();
//        businessSectorStore = new BusinessSectorStore();
//        projectStatusStore = new ProjectStatusStore();
//        userStoryStatusStore = new UserStoryStatusStore();
//        requestStore = new RequestStore();
    }

    public static App getInstance() {

        if (instance == null) {
            instance = new App();
        }
        return instance;
    }

    public Company getCompany() {
        return company;
    }

//    public TypologyStore getTypologyStore() { return typologyStore; }

//    public SystemUserStore getSystemUserStore() {
//        return systemUserStore;
//    }
//
//    public ProjectStore getProjectStore() {
//        return projectStore;
//    }
//
//    public UserProfileStore getUserProfileStore() {
//        return userProfileStore;
//    }
//
//    public ProjectRoleStore getProjectRoleStore() {
//        return projectRoleStore;
//    }
//
//    public CustomerStore getCustomerStore() {
//        return customerStore;
//    }
//
//    public BusinessSectorStore getBusinessSectorStore() {
//        return businessSectorStore;
//    }
//
//    public ProjectStatusStore getProjectStatusStore() {
//        return projectStatusStore;
//    }
//
//    public UserStoryStatusStore getUserStoryStatusStore() {
//        return userStoryStatusStore;
//    }
//
//    public RequestStore getRequestStore() {
//        return requestStore;
//    }

}
