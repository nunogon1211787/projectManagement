package switch2021.project.controller;

import switch2021.project.model.Company;
import switch2021.project.model.SystemUser.SystemUser;
import switch2021.project.model.SystemUser.SystemUserService;
import switch2021.project.model.UserProfile.UserProfile;
import switch2021.project.stores.SystemUserStore;
import switch2021.project.stores.UserProfileStore;

public class RegisterUserController {

    /**
     * Attributes
     **/
    private final Company company;
    private final SystemUserService systemUserService;


    /**
     * Constructor to test (without SINGLETON)
     **/
    public RegisterUserController(Company company, SystemUserService systemUserService) {
        this.company = company;
        this.systemUserService = systemUserService;
    }

    /**
     * Methods
     **/
    public boolean registerUser(String userName, String email, String function, String password, String passwordConfirmation, String photo) {
        return this.systemUserService.createAndSaveSystemUser(userName, email, function, password, passwordConfirmation, photo);
    }
/*
    public boolean createSystemUser(String userName, String email, String function, String password, String passwordConfirmation, String photo) {
        UserProfileStore profileStore = company.getUserProfileStore();
        UserProfile visitorProfile = profileStore.getUserProfile("Visitor");

        SystemUserStore usersStore = company.getSystemUserStore();

        SystemUser user = usersStore.createSystemUser(userName, email, function, password, passwordConfirmation, photo, visitorProfile);

        return usersStore.saveSystemUser(user);
    }*/
}
