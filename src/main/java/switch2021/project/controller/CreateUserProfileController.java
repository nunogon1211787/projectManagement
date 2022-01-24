package switch2021.project.controller;


import switch2021.project.model.Company;
import switch2021.project.model.UserProfile;
import switch2021.project.stores.UserProfileStore;
import switch2021.project.utils.App;


/**
 * >>>>>> https://www.devmedia.com.br/padrao-de-projeto-singleton-em-java/26392 || https://www.geeksforgeeks.org/singleton-class-java/ <<<<<<
 **/

public class CreateUserProfileController {

    private Company company;

    /**
     * Constructor to UI (with SINGLETON).
     */
    public CreateUserProfileController(){ this.company = App.getInstance().getCompany();}

    /**
     * Constructor to test (without SINGLETON).
     */
    public CreateUserProfileController(Company company) {
        this.company = company;
    }


    public boolean createProfile(String name) {
        UserProfileStore userProfileStore = this.company.getUserProfileStore();
        UserProfile userProfile = userProfileStore.createProfile(name);
        return userProfileStore.saveUserProfile(userProfile);
    }
}


