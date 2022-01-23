package switch2021.project.controller;

import switch2021.project.model.*;
import switch2021.project.stores.SystemUserStore;
import switch2021.project.stores.UserProfileStore;
import switch2021.project.utils.App;

public class RegisterUserController {
    private Company company;
    private SystemUserStore usersSstore;
    private UserProfileStore profilesStore;
    private SystemUser user;


    public RegisterUserController() {
        this.company = (App.getInstance().getCompany());
        this.usersSstore = null;
        this.profilesStore = null;
        this.user = null;
    }

    public boolean createSystemUser(String userName, String email, String function, String password, String passwordConfirmation, String photo) {
        UserProfileStore profileStore = company.getUserProfileStore();
        UserProfile visitorProfile = profileStore.getUserProfile("Visitor");

        SystemUserStore usersStore = company.getSystemUserStore();
        this.user = usersStore.createSystemUser(userName, email, function, password, passwordConfirmation, photo, visitorProfile);

        return usersStore.saveSystemUser(user);
    }

    public Company getCompany() {
        return company;
    }
}
