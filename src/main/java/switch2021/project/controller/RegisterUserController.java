package switch2021.project.controller;

import switch2021.project.model.*;

public class RegisterUserController {
    private Company company;
    private SystemUserStore usersSstore;
    private UserProfileStore profilesStore;
    private SystemUser user;

    public RegisterUserController() {
        this.company = new Company();
        this.usersSstore = null;
        this.profilesStore = null;
        this.user = null;
    }

    public boolean createSystemUser(String userName, String email, String function, String password, String passwordConfirmation, String photo) {
        UserProfileStore profileStore = company.getUserProfileStore();
        UserProfile visitorProfile = profileStore.getUserProfile("Visitor");

        SystemUserStore usersStore = company.getSystemUserStore();
        this.user=usersStore.createSystemUser(userName, email, function, password, passwordConfirmation, photo, visitorProfile);

        return usersStore.saveSystemUser(user);
    }
}
