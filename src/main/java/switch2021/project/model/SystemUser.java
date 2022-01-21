package switch2021.project.model;

import switch2021.project.stores.UserProfileStore;

import java.util.ArrayList;
import java.util.List;

public class SystemUser {


    /**
     * Attributes of systemUser´s class
     **/
    private String userName;
    private String email;
    private String photo;
    private String password;
    private String function;
    private boolean activateUser;
    private List<UserProfile> assignedProfileList;

    /**
     * Constructor
     **/
    public SystemUser(String userName, String email, String function, String password, String passwordConfirmation, String photo, UserProfile profile) {
        checkUserNameRules(userName); // o construtor faz validações
        checkEmailRules(email);
        checkFunctionRules(function);
        checkPasswordRules(password);
        this.userName = userName;
        this.email = email;
        this.photo = photo;
        this.function = function;
        if (password.equals(passwordConfirmation)) {
            this.password = encryptPassword(password);
        } else {
            throw new IllegalArgumentException("passwords not match");
        }
        this.activateUser = false;
        this.assignedProfileList = new ArrayList<>();
        this.assignedProfileList.add(profile);
    }

//ver este método (13/01/2022):
    // Copy Constructor. Para criar um novo objeto, igual ao parâmetro, mas sem levar adiante as referências do objeto original. */
    /*public SystemUser(SystemUser originalUser) {
        this.userName = originalUser.userName;
        this.email = originalUser.email;
        this.photo = originalUser.photo;
        this.function = originalUser.function;
        this.password = originalUser.password;
        this.activateUser = originalUser.activateUser;
        this.assignedProfileList = deepCopyListProfile(originalUser.assignedProfileList);
    }

    private UserProfileStore deepCopyListProfile(UserProfileStore originalList) {
        UserProfileStore deepCopyList = this.assignedProfileList;
        return deepCopyList;
    }*/


    /**
     * Getting Methods
     **/
    public String getUserName() {
        return this.userName;
    }

    public String getEmail() {
        return this.email;
    }

    public String getPhoto() {
        return this.photo;
    }

    public String getFunction() {
        return this.function;
    }

    // o getPassword não deveria ser privado??
    public String getPassword() {
        return this.password;
    }

    public boolean getActivateUserStatus() {
        return activateUser;
    }

    //está de acordo com o encapsulamento??
    public List<UserProfile> getAssignedProfileList() {
        return assignedProfileList;
    }


    /**
     * Setting Methods
     **/
    public void setUserName(String userName) {
        if (!userName.trim().isEmpty() || !(userName.length() < 2)) {
            this.userName = userName;
        }
    }

    public void setEmail(String email) {
        if (!email.trim().isEmpty() || !(email.length() < 2)) {
            this.email = email;
        }
    }

    public void setFunction(String function) {
        if (!function.trim().isEmpty() || !(function.length() < 2)) {
            this.function = function;
        }
    }

    public void setPassword(String password) {
        if (!password.trim().isEmpty() || !(password.length() < 2)) {
            this.password = password;
        }
    }

    public void setPhoto(String photo) {

        if (!photo.trim().isEmpty()) {
            this.photo = photo;
        }
    }

    public SystemUser setAllData(String userName, String function, String photo, SystemUser user) {

        if (checkAllData(userName, function, photo)) {
            user.setUserName(userName);
            user.setFunction(function);
            user.setPhoto(photo);
            return user;
        }
        return null;
    }

    public boolean setActivateUser() {
        return this.activateUser = true;
    }

    //é suposto ter esse método? não quebra a regra de encapsulamento?
    public void setAssignedProfileList(List<UserProfile> assignedProfileList) {
        this.assignedProfileList = assignedProfileList;
    }


    /**
     * Validation Methods
     **/
    //// Verificar se se pode implementar apenas um metodo de validação ****
    private void checkUserNameRules(String userName) {
        if (userName.trim().isEmpty())
            throw new IllegalArgumentException("Username cannot be empty.");
        if ((userName.length() < 2))
            throw new IllegalArgumentException("Username must be at least 2 characters");
    }

    private void checkEmailRules(String email) {
        if (email.trim().isEmpty())
            throw new IllegalArgumentException("Email cannot be empty.");
        if ((email.length() < 2))
            throw new IllegalArgumentException("Email must be at least 2 characters");
    }

    private void checkFunctionRules(String function) {
        if (function.trim().isEmpty())
            throw new IllegalArgumentException("Function cannot be empty.");
        if ((function.length() < 2))
            throw new IllegalArgumentException("Function must be at least 2 characters");
    }

    private void checkPasswordRules(String password) {
        if (password.trim().isEmpty())
            throw new IllegalArgumentException("Password cannot be empty.");
        if ((password.length() < 2))
            throw new IllegalArgumentException("Password must be at least 2 characters");
    }

    public boolean checkAllData(String userName, String function, String photo) {
        if (photo.trim().isEmpty())
            throw new IllegalArgumentException("Photo cannot be empty.");
        if (function.trim().isEmpty() || function.length() < 2)
            throw new IllegalArgumentException("Function cannot be empty or less then 2 characters.");
        if (userName.trim().isEmpty() || userName.length() < 2)
            throw new IllegalArgumentException("Username cannot be empty or less then 2 characters.");
        return true;
    }

    public boolean checkAssignedProfileList(UserProfile Profile) {
        boolean msg = true;
        for (UserProfile i : assignedProfileList) {
            if (i.equals(Profile)) {
                msg = false;
                break;
            }
        }
        return msg;
    }


    /**
     * Encryption/Decryption Methods
     **/
    public String encryptPassword(String password) {
        int codigoASCII;
        String result = "";

        for (int i = 0; i < password.length(); i++) {
            codigoASCII = password.charAt(i) + 99;
            result += (char) codigoASCII;
        }
        return result;
    }

    public String decryptPassword(String password) {
        int codigoASCII;
        String result = "";

        for (int i = 0; i < password.length(); i++) {
            codigoASCII = password.charAt(i) - 99;
            result += (char) codigoASCII;
        }
        return result;
    }


    /**
     * AssignProfileList´s methods
     */
    public void assignProfileToUser(UserProfile profile) {
        this.assignedProfileList.add(profile);
    }

    public boolean updateProfile(UserProfile oldProfile, UserProfile newProfile) {

        if (!checkAssignedProfileList(newProfile)) {
            throw new IllegalArgumentException("Repeated user profile inserted.");
        } else {
            this.assignedProfileList.remove(oldProfile);
            this.assignedProfileList.add(newProfile);
        }
        return true;
    }


    /**
     * Método para validar se o email (ou parte dele) é deste objeto.
     */
    public boolean isYourEmail(String email) {

        boolean result = false;
        int idxString = this.email.indexOf(email.toLowerCase());

        if (idxString != -1) {
            result = true;
        }
        return result;
    }

    public boolean isYourName(String name) {
        return this.userName.equals(name);
    }

    /**
     * Method to validate if user as already has the profile requested
     */

    public boolean hasProfile(UserProfile profile) {

        boolean profileStatus = false;

        for (UserProfile profileCheck : assignedProfileList) {

            if (profile.equals(profileCheck)) {

                profileStatus = true;
                break;
            }
        }

        return profileStatus;
    }


    /*
     * Método para verificar se os parâmetros recebidos são do objeto.
     */

    // Método para verificar se os parâmetros recebidos são do objeto. */
    ///
    // Rever este Método!!!!    *****************************
    ///

    public boolean hasThisData(String userName, String email, String function, int state, List<UserProfile> profileChoosenList) {

        boolean result = true;
        int match = 0;

        if (!userName.isEmpty()) {
            int idxString = this.userName.toLowerCase().indexOf(userName.toLowerCase());
            if (idxString == -1) {
                result = false;
            } else {
                match++;
            }
        }

        if (!email.isEmpty()) {
            int idxString = this.email.toLowerCase().indexOf(email.toLowerCase());
            if (idxString == -1) {
                result = false;
            } else {
                match++;
            }
        }

        if (!function.isEmpty()) {
            int idxString = this.function.toLowerCase().indexOf(function.toLowerCase());
            if (idxString == -1) {
                result = false;
            } else {
                match++;
            }
        }

        if (state != -1) {
            if (state == 0) {
                if (this.activateUser) {
                    result = false;
                } else {
                    match++;
                }
            } else if (state == 1) {
                if (!this.activateUser) {
                    result = false;
                } else {
                    match++;
                }
            }
        }

        if (profileChoosenList.size() != 0) {

            if (this.assignedProfileList == null) {
                result = false;
            } else {

                int count = 0;

                for (UserProfile k : profileChoosenList) {
                    if (this.assignedProfileList.contains(k)) {
                        count++;
                        match++;
                        break;
                    }
                }

                if (count != profileChoosenList.size()) {
                    result = false;
                }

            }
        }

        if (match == 0) {
            result = false;
        }

        return result;
    }

    /*
     * Method to update old password with the new password
     */

    /*
     * Method to update old password with the new password
     */
    public boolean updatePassword(String oldpasswordUI, String newpassword) {

        if (validateOldPassword(oldpasswordUI)) {
            setPassword(newpassword);
            checkPasswordRules(newpassword);
            encryptPassword(newpassword);
        } else {
            return false;
        }
        return true;
    }

    private boolean validateOldPassword(String oldpasswordUI) {

        String oldpasswordSU = decryptPassword(this.password);

        return oldpasswordUI.equals(oldpasswordSU);
    }

    /**
     * Override Methods
     **/
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SystemUser that = (SystemUser) o;
        return (this.userName.equals(that.userName)) && (this.email.equals(that.email)) &&
                (this.photo.equals(that.photo)) && (this.password.equals(that.password)) &&
                (this.function.equals(that.function)) && (this.activateUser == that.activateUser)
                && (this.assignedProfileList.equals(that.assignedProfileList));
    }
    //Este override foi feito expecíficamente para os teste... uma vez que os IDs da classe
    // vão sempre seguir uma sequência! Aceito sugestões para melhorar isto...teste
}
