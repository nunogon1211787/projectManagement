package switch2021.project.model;

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
    private UserProfile assignedProfile;

    /**
     * Contructor
     **/
    public SystemUser(String userName, String email, String function, String password, String passwordConfirmation, String photo, UserProfile visitor) {
        checkUserNameRules(userName);
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
        this.assignedProfile = visitor;
    }
//ver este método (13/01/2022):
    /**
     * Copy Constructor. Para criar um novo objeto, igual ao parâmetro, mas sem levar adiante as referências do objeto original.
     */
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

    public String getPassword() {
        return this.password;
    }

    public boolean getActivateUserStatus() {
        return activateUser;
    }

    public UserProfile getAssignedProfile() {
        return assignedProfile;
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
        this.photo = photo;
    }

    public void setActivateUser() {
        this.activateUser = true;
    }

    public void setAssignedProfile(UserProfile assignedProfile) {
        this.assignedProfile = assignedProfile;
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
/*
    public void assignProfileToUser(UserProfile p) {
        this.assignedProfileList.addProfile(p);
    }


    public boolean updateProfile(UserProfile oldProfile, UserProfile newProfile) {

       // this.assignedProfileList.remove(oldProfile);
       // this.assignedProfileList.add(newProfile);
        return true;
    }
*/

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

    /**
     * Método para verificar se os parâmetros recebidos são do objeto.
     */


    ///
    // Rever este Método!!!!    *****************************
    ///

    /*public boolean hasThisData(String userName, String email, String function, int isActive, int[] profilesId) {

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

        if (isActive != -1) {
            if (isActive == 0) {
                if (this.activateUser) {
                    result = false;
                } else {
                    match++;
                }
            } else if (isActive == 1) {
                if (!this.activateUser) {
                    result = false;
                } else {
                    match++;
                }
            }
        }

        if (profilesId.length != 0) {

            if (this.assignedProfileList.size() == 0) {
                result = false;
            } else {

                int count = 0;

                for (int k : profilesId) {
                    for (UserProfile profile : this.assignedProfileList) {
                        if (profile.isValidId(k)) {
                            count++;
                            match++;
                            break;
                        }
                    }
                }

                if (count != profilesId.length) {
                    result = false;
                }

            }
        }

        if (match == 0) {
            result = false;
        }

        return result;
    }*/

    /**
     * Method to update old password with the new password
     */

    public boolean updatePassword(String oldpasswordUI, String newpassword) {


        if (validateOldPassword(oldpasswordUI)) {
            setPassword(newpassword);
            encryptPassword(newpassword);
        } else {
            return false;
        }
        return true;
    }

    private boolean validateOldPassword(String oldpasswordUI) {

        String oldpasswordSU = decryptPassword(this.password);

        if (oldpasswordUI.equals(oldpasswordSU)) {
            return true;
        }
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SystemUser that = (SystemUser) o;
        return (this.userName.equals(that.userName)) && (this.email.equals(that.email)) &&
                (this.photo.equals(that.photo)) && (this.password.equals(that.password)) &&
                (this.function.equals(that.function)) && (this.activateUser == that.activateUser)
                && (this.assignedProfile.equals(that.assignedProfile));
    }
    //Este override foi feito expecíficamente para os teste... uma vez que os IDs da classe
    // vão sempre seguir uma sequência! Aceito sugestões para melhorar isto...teste
}
