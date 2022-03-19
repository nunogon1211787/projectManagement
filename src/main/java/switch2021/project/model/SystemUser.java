package switch2021.project.model;

import lombok.Getter;
import lombok.Setter;
import switch2021.project.immutable.Function;
import switch2021.project.immutable.Name;
import switch2021.project.immutable.Password;
import switch2021.project.stores.UserProfileStore;
import switch2021.project.utils.App;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
public class SystemUser {


    /**
     * Attributes of systemUser´s class
     **/
    private Name userName;
    private final String email;
    private String photo;
    private Password password;
    private Function function;
    private boolean activateUser;
    private final List<UserProfile> assignedProfileList;

    /**
     * Constructor
     **/
    public SystemUser (String userName, String email, String function, String password, String passwordConfirmation,
                       String photo, UserProfile profile) {
        checkUserNameRules(userName);
        checkEmailRules(email);
//        checkPasswordRules(password);
        checkProfileRules(profile);
        this.userName = new Name(userName);
        this.email = email;
        this.photo = photo;
        this.function = new Function(function);
        if (password.equals(passwordConfirmation)) {
            this.password = new Password(password);
        } else {
            throw new IllegalArgumentException("passwords not match");
        }
        this.activateUser = false;
        this.assignedProfileList = new ArrayList<>();
        this.assignedProfileList.add(profile);
    }

    public boolean getActivateUserStatus() {
        return activateUser;
    }

    public List<UserProfile> getAssignedProfileList() {
        return new ArrayList<>(assignedProfileList);
    }

    /**
     * Setting Methods (outside of lombock)
     **/
    public void setUserName(String userName) {
        if (!userName.trim().isEmpty() && (userName.length() >= 1)) {
            this.userName = new Name(userName);
        }
    }

    public void setFunction(String function) {
        if (!function.trim().isEmpty() && (function.length() > 2)) {
            this.function = new Function(function);
        }
    }

    private void setPassword(String password) {
//        checkPasswordRules(password);
        this.password = new Password(password);
    }

    public void setPhoto(String photo) {
        if (!photo.trim().isEmpty()) {
            this.photo = photo;
        }
    }

    public boolean setAllData(String userName, String function, String photo) {

        boolean msg = false;

        if (checkAllData(userName, function, photo)) {
            setUserName(userName);
            setFunction(function);
            setPhoto(photo);
            msg = true;
        }
        return msg;
    }

    public boolean setActivateUser(Boolean x){
        this.activateUser = x;
        return true;
    }



    /**
     * Validation Methods
     **/

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


//    private void checkPasswordRules(String password) {
//        if (password.trim().isEmpty())
//            throw new IllegalArgumentException("Password cannot be empty.");
//        if ((password.length() < 2))
//            throw new IllegalArgumentException("Password must be at least 2 characters");
//    }

    private void checkProfileRules(UserProfile profile) {
        Company company = App.getInstance().getCompany();
        UserProfileStore profileStore = company.getUserProfileStore();
        UserProfile visitorProfile = profileStore.getUserProfile("Visitor");

        if (!profile.equals(visitorProfile))
            throw new IllegalArgumentException("at registration visitor profile must be associated");
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

    public boolean checkAssignedProfileList(UserProfile profile) {
        boolean msg = true;
        for (UserProfile i : assignedProfileList) {
            if (i.equals(profile)) {
                msg = false;
                break;
            }
        }
        return msg;
    }


    /**
     * Encryption/Decryption Methods
     **/
//    public String encryptPassword(String password) {
//        int codigoASCII;
//        StringBuilder stringBuilder = new StringBuilder();
//
//        for (int i = 0; i < password.length(); i++) {
//            codigoASCII = password.charAt(i) + 99;
//            stringBuilder.append((char) codigoASCII);
//        }
//        return stringBuilder.toString();
//    }
//
    public String decryptPassword(String password) {
        int codigoASCII;
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < password.length(); i++) {
            codigoASCII = password.charAt(i) - 99;
            stringBuilder.append((char) codigoASCII);
        }
        return stringBuilder.toString();
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

    public boolean hasName(String name) {
        return this.userName.getNameF().equals(name);
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


    /**
     * Method to verify if the object has the received parameters.
     */
    //REVIEW
    private int hasPartiallyName (String name){
        int result = 0;

        if (!name.isEmpty()) {
            int idxString = this.userName.getNameF().toLowerCase().indexOf(name.toLowerCase());
            if (idxString != -1) {
                result = 1;
            } else {
                result = -1;
            }
        }
        return result;
    }
    //REVIEW
    private int hasPartiallyEmail (String email){
        int result = 0;

        if (!email.isEmpty()) {
            int idxString = this.email.toLowerCase().indexOf(email.toLowerCase());
            if (idxString != -1) {
                result = 1;
            } else {
                result = -1;
            }
        }
        return result;
    }
    //REVIEW
    private int hasPartiallyFunction (String function){
        int result = 0;

        if (!function.isEmpty()) {
            int idxString = this.function.getText().toLowerCase().indexOf(function.toLowerCase());
            if (idxString != -1) {
                result = 1;
            } else {
                result = -1;
            }
        }
        return result;
    }
    //REVIEW
    private int hasState (int state) {
        int result = 0;
        int check = this.activateUser ? 1 : 0;

        if (state != -1) {
            if (state == check) {
                result = 1;
            } else {
                result = -1;
            }
        }
        return result;
    }
    //REVIEW
    private int hasAllProfilesInTheList (List<UserProfile> profiles){
        int result = 0;

        if (!profiles.isEmpty()) {
            //Review, the assignedProfileList isn't null because is created in the constructor...
//            if (this.assignedProfileList != null) {
                int count = 0;

                for (UserProfile k : profiles) {
                    if (this.assignedProfileList.contains(k)) {
                        count++;
                    }
                }
                if (count == profiles.size()) {
                    result = 1;
                } else{
                    result = -1;
                }
            }
//        }
        return result;
    }
    //REVIEW
    public boolean hasThisData(String userName, String email, String function, int state, List<UserProfile> profileChoosenList) {
        boolean result = false;

        // Check if the object has the userName parameter.
        int res1 = hasPartiallyName(userName);

        // Check if the object has the email parameter.
        int res2 = hasPartiallyEmail(email);

        // Check if the object has the function parameter.
        int res3 = hasPartiallyFunction(function);

        // Check if the object has the state parameter.
        int res4 = hasState(state);

        // Check if the object has the list profiles parameter.
        int res5 = hasAllProfilesInTheList(profileChoosenList);

        if(res1 != -1 && res2 != -1 && res3 != -1 && res4 != -1 && res5 != -1) {
            int match = res1 + res2 + res3 + res4 + res5;

            if (match > 0) {
                result = true;
            }
        }
        return result;
    }

    /**
     * Method to update old password with the new password
     **/
    public boolean updatePassword(String oldPasswordUI, String newPassword, String newPasswordConfirmation) {
        boolean msg = false;

        if (validateOldPassword(oldPasswordUI) && newPassword.equals(newPasswordConfirmation)) {
            setPassword(newPassword);
            msg = true;
        }
        return msg;
    }

    /**
     * Method to validate the oldpassword from the UI with thew old password from the System User
     **/

    private boolean validateOldPassword(String oldpasswordUI) {

        String oldpasswordSU = decryptPassword(this.password.getPwd());

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

    @Override
    public int hashCode() {
        return Objects.hash(userName, email, photo, password, function, activateUser, assignedProfileList);
    }


}
