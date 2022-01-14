package switch2021.project.controller;


import switch2021.project.model.Company;
import switch2021.project.model.UserProfile;
import switch2021.project.model.UserProfileStore;
import switch2021.project.utils.App;


/**
 * >>>>>> https://www.devmedia.com.br/padrao-de-projeto-singleton-em-java/26392 || https://www.geeksforgeeks.org/singleton-class-java/ <<<<<<
 **/

public class CreateUserProfileController {

    private Company company;


    public CreateUserProfileController() {
        this(App.getInstance().getCompany());
    }

    public CreateUserProfileController(Company company) {
        this.company = company;
    }


    public String createProfile(String name) {
        try {
            UserProfileStore userProfileStore = this.company.getUserProfileStore();
            UserProfile userProfile = userProfileStore.createProfile(name);
            userProfileStore.addUserProfile(userProfile);
        }catch (IllegalArgumentException exception){
            return exception.getMessage();
        }
        return "Profile created.";

    }
}


