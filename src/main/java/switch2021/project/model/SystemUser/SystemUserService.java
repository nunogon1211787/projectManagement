package switch2021.project.model.SystemUser;

import switch2021.project.model.valueObject.UserProfileId;
import switch2021.project.stores.SystemUserStore;
import switch2021.project.stores.UserProfileStore;

public class SystemUserService {

    private final SystemUserStore systemUserStore;
    private final UserProfileStore userProfileStore;

    /**
     * Constructor.
     */
    public SystemUserService(SystemUserStore systemUserStore, UserProfileStore userProfileStore) {
        this.systemUserStore = systemUserStore;
        this.userProfileStore = userProfileStore;
    }

    //TODO implement SystemUserDTO instead of boolean
    public boolean createAndSaveSystemUser(String userName, String email, String function, String password, String passwordConfirmation, String photo) {
        if (this.systemUserStore.existsByEmail(email))
            return false;

        //SystemUserId systemUserId = new SystemUserId(email);
        UserProfileId visitorId = this.userProfileStore.getUserProfile("Visitor").getUserProfileId();
        SystemUser newUser = new SystemUser(userName, email, function, password, passwordConfirmation, photo,visitorId);

        //newUser.getAssignedProfiles().add(visitorId);

        return this.systemUserStore.saveSystemUser(newUser);
    }
}
