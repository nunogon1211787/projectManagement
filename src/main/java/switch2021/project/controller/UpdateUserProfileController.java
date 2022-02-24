package switch2021.project.controller;

import switch2021.project.model.*;
import switch2021.project.stores.SystemUserStore;
import switch2021.project.stores.UserProfileStore;
import java.util.List;

public class UpdateUserProfileController {

    /**
     * Attributes
     **/
    private Company company;
    private UserProfileStore userProfileStore;
    private SystemUserStore systemUserStore;
    private SystemUser user;


    /**
     * Constructor to test (without SINGLETON)
     **/
    public UpdateUserProfileController(Company company) {
        this.company = company;
        this.userProfileStore = company.getUserProfileStore();
        this.systemUserStore = company.getSystemUserStore();
        this.user = null;
    }


    /**
     * Methods
     **/
    public SystemUser getUser(String email) {
        SystemUser user = systemUserStore.getUserByEmail(email);
        if (user == null) {
            throw new IllegalArgumentException("This email doesn't exist.");
        }
        return user;
    }

    public List<UserProfile> getUserProfileList() {
        return userProfileStore.getUserProfileList();
    }

    public boolean updateProfile(SystemUser user, UserProfile oldProfile, UserProfile newProfile) {
        this.user = user;
        this.user.updateProfile(oldProfile, newProfile);
        this.systemUserStore.saveSystemUser(user);

        return true;
    }

}
