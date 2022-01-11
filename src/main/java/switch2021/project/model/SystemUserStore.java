package switch2021.project.model;

import java.util.ArrayList;
import java.util.List;

public class SystemUserStore {

    /**
     * Atributos
     */

    List<SystemUser> SystemUserList;


    // System user

    /**
     * Create Methods
     **/

    public SystemUser createSystemUser(String userName, String email, String function, String password, String passwordConfirmation, String photo, UserProfile visitor ) {

        SystemUser newUser = new SystemUser(userName, email, function, password, passwordConfirmation, photo, visitor);

        return newUser;

    }
    ///// Apenas manter um construtor !!!

    /**
     * Add Method
     **/

    public boolean addSystemUser(SystemUser syUser) {
        this.SystemUserList.add(syUser);
        return true;
    }

    /**
     * Getter Methods
     */
    public List<SystemUser> getSystemUserList() {
        return this.SystemUserList;
    }

    public SystemUser getUserByEmail(String email) {

        SystemUser user = null;

        for (int i = 0; i < this.SystemUserList.size(); i++) {
            if (this.SystemUserList.get(i).isYourEmail(email)) {
                user = this.SystemUserList.get(i);
                break;
            }
        }

        return user;
    }

    public List<SystemUser> searchUsers(String name, String email, String function, int isActive, int[] profileList) {

        int listSize = this.SystemUserList.size();
        List<SystemUser> foundUsersList = new ArrayList<>();

        if (listSize != 0) {

            for (SystemUser systemUser : this.SystemUserList)
                if (systemUser.hasThisData(name, email, function, isActive, profileList)) {
                    foundUsersList.add(new SystemUser(systemUser));
                }

        }

        return foundUsersList;
    }

    /**
     * Validation Methods
     */

    public boolean validateSystemUser(SystemUser user) {
        if (user == null) {
            return false;
        }
        if (hasEmail(user.getEmail())) {
            return false;
        }
        if (hasUserName(user.getUserName())) {
            return false;
        }
        return !this.SystemUserList.contains(user);
    }

    boolean hasEmail(String newUserEmail) {
        for (SystemUser newUser : SystemUserList) {
            if (newUser.getEmail().trim().equalsIgnoreCase(newUserEmail.trim())) {
                return true;
            }
        }
        return false;
    }

    //// Dois utilizadores podem existir com o mesmo nome, podem existir dois Nunos!!
    boolean hasUserName(String newUserName) {
        for (SystemUser newUser : SystemUserList) {
            if (newUser.getUserName().trim().equalsIgnoreCase(newUserName.trim())) {
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
            this.SystemUserList.add(user);
        }
        return result;
    }




}
