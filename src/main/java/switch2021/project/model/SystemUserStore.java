package switch2021.project.model;

import java.util.List;

public class SystemUserStore {

    /**
     * Atributos
     */

    List<SystemUser> systemUserList;


    // System user

    /**
     * Create Methods
     **/

    public SystemUser createSystemUser(String userName, String email, String function, String password, String passwordConfirmation, String photo, UserProfile visitor) {
        SystemUser newUser;
        try {
            newUser = new SystemUser(userName, email, function, password, passwordConfirmation, photo, visitor);
        } catch (IllegalArgumentException e) {
            newUser = null;
        }
        return newUser;

    }
    ///// Apenas manter um construtor !!!

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
        return this.systemUserList;
    }

    public SystemUser getUserByEmail(String email) {

        SystemUser user = null;

        for (int i = 0; i < this.systemUserList.size(); i++) {
            if (this.systemUserList.get(i).isYourEmail(email)) {
                user = this.systemUserList.get(i);
                break;
            }
        }

        return user;
    }

    ///// Rever Método
    /*public List<SystemUser> searchUsers(String name, String email, String function, int isActive, int[] profileList) {

        int listSize = this.SystemUserList.size();
        List<SystemUser> foundUsersList = new ArrayList<>();

        if (listSize != 0) {

            for (SystemUser systemUser : this.SystemUserList)
                if (systemUser.hasThisData(name, email, function, isActive, profileList)) {
                    foundUsersList.add(new SystemUser(systemUser));
                }

        }

        return foundUsersList;
    }*/

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

    //// Dois utilizadores podem existir com o mesmo nome, podem existir dois Nunos!!
    boolean hasUserName(String newUserName) {
        for (SystemUser newUser : systemUserList) {
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
            this.systemUserList.add(user);
        }
        return result;
    }


}
