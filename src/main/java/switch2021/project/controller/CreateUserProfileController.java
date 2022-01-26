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
    private UserProfileStore userProfileStore;
    private UserProfile userProfile;

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


    public boolean createUserProfile(String name) {
        this.userProfileStore = this.company.getUserProfileStore();
        this.userProfile = this.userProfileStore.createProfile(name);
        return this.userProfileStore.saveUserProfile(this.userProfile);
    }
}


