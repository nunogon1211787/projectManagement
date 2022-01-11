package switch2021.project.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Company {

    /**
     * Atributos da Classe
     **/

    List<SystemUser> arraySyUser;
    List<Request> arrayRequest;


    //Project
    public ProjectStore getProjectStore() {
        return new ProjectStore();
    }

    //Profile
    public UserProfileStore getUserProfileStore() {
        return new UserProfileStore();
    }

    // System user

    /**
     * Create Methods
     **/

    public SystemUser createSystemUser(String userName, String email, String function, String password) {
        return new SystemUser(userName, email, function, password, arrayProfile.get(0));
    }
    ///// Apenas manter um construtor !!!

    public SystemUser createSystemUser(String userName, String email, String function, String photo, String password) {
        return new SystemUser(userName, email, function, photo, password, arrayProfile.get(0));
    }

    /**
     * Add Method
     **/

    public boolean addSystemUser(SystemUser syUser) {
        this.arraySyUser.add(syUser);
        return true;
    }

    /**
     * Getter Methods
     */
    public List<SystemUser> getArraySyUser() {
        return this.arraySyUser;
    }

    public SystemUser getUserByEmail(String email) {

        SystemUser user = null;

        for (int i = 0; i < this.arraySyUser.size(); i++) {
            if (this.arraySyUser.get(i).isYourEmail(email)) {
                user = this.arraySyUser.get(i);
                break;
            }
        }

        return user;
    }

    public List<SystemUser> searchUsers(String name, String email, String function, int isActive, int[] profileList) {

        int listSize = this.arraySyUser.size();
        List<SystemUser> foundUsersList = new ArrayList<>();

        if (listSize != 0) {

            for (SystemUser systemUser : this.arraySyUser)
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
        return !this.arraySyUser.contains(user);
    }

    boolean hasEmail(String newUserEmail) {
        for (SystemUser newUser : arraySyUser) {
            if (newUser.getEmail().trim().equalsIgnoreCase(newUserEmail.trim())) {
                return true;
            }
        }
        return false;
    }

    //// Dois utilizadores podem existir com o mesmo nome, podem existir dois Nunos!!
    boolean hasUserName(String newUserName) {
        for (SystemUser newUser : arraySyUser) {
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
            this.arraySyUser.add(user);
        }
        return result;
    }



    //Request

    /**
     * Add Method
     */

    public boolean addRequest(Request request) {
        this.arrayRequest.add(request);
        return true;

    }

    /**
     * Validation Method
     */

    private boolean validateRequest(Request newRequest) {

        //Check if request already exist
        for (Request up : arrayRequest) {
            if (up.equals(newRequest)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Save Method
     */

    public boolean saveRequest(Request newRequest) {

        boolean result = false;

        if (validateRequest(newRequest)) {
            addRequest(newRequest);
            result = true;
        }

        return result;

    }


}