package switch2021.project.stores;

import switch2021.project.model.SystemUser;
import switch2021.project.model.UserProfile;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SystemUserStore {

    //Atributes
    private List<SystemUser> systemUserList;

    //Constructor
    public SystemUserStore() {

        this.systemUserList = new ArrayList<>();
    }

    public SystemUser createSystemUser(String userName, String email, String function, String password, String passwordConfirmation, String photo, UserProfile visitor) {
        SystemUser user;
        //try {
        user = new SystemUser(userName, email, function, password, passwordConfirmation, photo, visitor);
        // } catch (IllegalArgumentException e) {
        //e.getMessage();
        // user = null;
        // }
        return user;
    }

    /**
     * Add Method
     **/

    public boolean addSystemUser(SystemUser syUser) {
        this.systemUserList.add(syUser);
        return true;
    }

    /**
     * Getter Methods
     */
    public List<SystemUser> getSystemUserList() {
        List<SystemUser> copyList = new ArrayList<>(this.systemUserList);

        return copyList;
    }

    public SystemUser getUserByEmail(String email) {
        SystemUser user = null;

        for (SystemUser i : this.systemUserList) {
            if (i.isYourEmail(email)) {
                user = i;
                break;
            }
        }
        return user;
    }

    ///// Rever Método
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

    public boolean validateSystemUser(SystemUser user) {
        if (user == null || hasEmail(user.getEmail())) {
            return false;
        }
        return !this.systemUserList.contains(user);
    }

    boolean hasEmail(String newUserEmail) {
        for (SystemUser newUser : systemUserList) {
            if (newUser.getEmail().trim().equalsIgnoreCase(newUserEmail.trim())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Save Method
     */

    public boolean saveSystemUser(SystemUser user) {
        boolean result = true;

        if (!validateSystemUser(user)) {
            result = false;
        } else {
            this.systemUserList.add(user);
        }
        return result;
    }

    /** Override Methods **/
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof UserProfileStore)) return false;
        SystemUserStore that = (SystemUserStore) obj;
        return (this.systemUserList.equals(that.systemUserList));
    }

    @Override
    public int hashCode() {
        return Objects.hash(systemUserList);
    }
}
