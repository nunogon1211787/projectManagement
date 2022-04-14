package switch2021.project.stores;

import lombok.Getter;
import switch2021.project.interfaces.SystemUserRepositoryInterface;
import switch2021.project.model.SystemUser.SystemUser;
import switch2021.project.model.UserProfile.UserProfile;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
public class SystemUserStore implements SystemUserRepositoryInterface {

    /**
     * Class Attributes
     */
    private final List<SystemUser> systemUserList;

    private SystemUserRepositoryInterface systemUserRepositoryInterface;

    /**
     * Constructor
     */
    public SystemUserStore() {
        this.systemUserList = new ArrayList<>();
    }

    @Override
    public SystemUser findSystemUserByEmail(String email) {
        SystemUser user = null;

        for (SystemUser i : this.systemUserList) {
            if (i.isYourEmail(email)) {
                user = i;
                break;
            }
        }
        return user;
    }

    @Override
    public List<SystemUser> findAllSystemUsers() {
        return new ArrayList<>(this.systemUserList);
    }

    /**
     * Save Method
     */
    @Override
    public boolean saveSystemUser(SystemUser user) {
        boolean result = true;

        if (!validateSystemUser(user)) {
            result = false;
        } else {
            this.systemUserList.add(user);
        }
        return result;
    }

    @Override
    public boolean existsByEmail(String newUserEmail) {
        for (SystemUser newUser : systemUserList) {
            if (newUser.getSystemUserId().getEmail().getEmail().trim().equalsIgnoreCase(newUserEmail.trim())) {
                return true;
            }
        }
        return false;
    }

    ///// ----->>>>>>  Rever Método
    public List<SystemUser> searchUsers(String name, String email, String function, int state, List<UserProfile> profileChoosenList) {
        int listSize = this.systemUserList.size();
        List<SystemUser> foundUsersList = new ArrayList<>();

        if (listSize != 0) {
            for (SystemUser systemUser : this.systemUserList)
                if (systemUser.hasThisData(name, email, function, state, profileChoosenList)) {
                    foundUsersList.add(systemUser);
                }
        }
        return foundUsersList;
    }

    /**
     * Validation Methods
     */
    private boolean validateSystemUser(SystemUser user) {
        if (user == null || existsByEmail(user.getSystemUserId().getEmail().getEmail())) {
            return false;
        }
        return !this.systemUserList.contains(user);
    }

    /**
     * Override
     **/
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof SystemUserStore)) return false;
        SystemUserStore that = (SystemUserStore) obj;
        return (this.systemUserList.equals(that.systemUserList));
    }

    @Override
    public int hashCode() {
        return Objects.hash(systemUserList);
    }

/**
 * Create Method
 */
    /*public SystemUser createSystemUser(String userName, String email, String function, String password, String passwordConfirmation, String photo, UserProfileId visitorId) {

        SystemUserId systemUserId = new SystemUserId(email); //not sure (Nuno)

        return new SystemUser(systemUserId, userName, function, password, passwordConfirmation, photo, visitorId);
    }*/
}