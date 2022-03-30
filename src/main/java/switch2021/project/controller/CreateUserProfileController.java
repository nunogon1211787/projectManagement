package switch2021.project.controller;

import switch2021.project.model.Company;
import switch2021.project.stores.UserProfileStore;
import switch2021.project.valueObject.UserProfile;

//https://www.devmedia.com.br/padrao-de-projeto-singleton-em-java/26392
//https://www.geeksforgeeks.org/singleton-class-java/

public class CreateUserProfileController {

    /**
     * Attributes
     **/
    private final Company company;


    /**
     * Constructor to test (without SINGLETON)
     **/
    public CreateUserProfileController(Company company) {
        this.company = company;
    }


    /**
     * Method
     **/
    public void createUserProfile(String name) {
        UserProfileStore userProfileStore = this.company.getUserProfileStore();
        UserProfile userProfile = userProfileStore.createProfile(name);
        userProfileStore.saveUserProfile(userProfile);
    }
}


