package switch2021.project.controller;


import switch2021.project.model.Company;
import switch2021.project.model.UserProfile;
import switch2021.project.utils.App;


/**
 * >>>>>> https://www.devmedia.com.br/padrao-de-projeto-singleton-em-java/26392 || https://www.geeksforgeeks.org/singleton-class-java/ <<<<<<
 **/

public class CreateProfileController {

    private Company company;
    private UserProfile userProfile;

    public CreateProfileController() {
        this(App.getInstance().getCompany());
    }

    public CreateProfileController(Company company) {
        this.company = company;
        //this.profile = null;
    }


    public boolean createProfile(String name, String type) {
        this.userProfile = this.company.getUserProfileStore().createProfile(name, type);
        return this.company.getUserProfileStore().addProfile(this.userProfile);


    }
}


