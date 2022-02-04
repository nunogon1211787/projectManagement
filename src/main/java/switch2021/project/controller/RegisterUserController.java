package switch2021.project.controller;

import switch2021.project.model.*;
import switch2021.project.stores.SystemUserStore;
import switch2021.project.stores.UserProfileStore;
import switch2021.project.utils.App;

public class RegisterUserController {

    /**
     * Attributes
     **/

    private Company company;
    private SystemUser user;


    /**
     * Constructor to UI (with SINGLETON)
     **/

  //  public RegisterUserController() { this.company = App.getInstance().getCompany(); }

    /**
     * Constructor to test (without SINGLETON)
     **/

    public RegisterUserController(Company company){ this.company = company; }

    /**
     * Methods
     **/

    public boolean createSystemUser(String userName, String email, String function, String password, String passwordConfirmation, String photo) {
        UserProfileStore profileStore = company.getUserProfileStore();
        UserProfile visitorProfile = profileStore.getUserProfile("Visitor");

        SystemUserStore usersStore = company.getSystemUserStore();
        this.user = usersStore.createSystemUser(userName, email, function, password, passwordConfirmation, photo, visitorProfile);

        return usersStore.saveSystemUser(user);
    }

}
