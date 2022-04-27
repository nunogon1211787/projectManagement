package switch2021.project.model.SystemUser;

import lombok.Getter;
import lombok.Setter;
import switch2021.project.model.UserProfile.UserProfile;
import switch2021.project.model.valueObject.*;
import switch2021.project.utils.Entity;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class SystemUser implements Entity<SystemUser> {

    /**
     * Attributes of systemUser´s class
     **/
    private SystemUserId systemUserId;
    private Name userName;
    private Photo photo;
    private Password password;
    private Function function;
    private boolean isActive;
    private final List<UserProfileId> assignedIdProfiles;
    private List<Request> requestedProfiles;
    //private final Email email;

    /**
     * Constructor
     **/
    public SystemUser(String userName, String email, String function, String password, String passwordConfirmation,
                      String photo, UserProfileId profileId) {
        checkProfileRules(profileId);
        this.systemUserId = new SystemUserId(email);
        this.userName = new Name(userName);
        this.photo = new Photo(photo);
        this.function = new Function(function);
        if (password.equals(passwordConfirmation)) {
            this.password = new Password(password);
        } else {
            throw new IllegalArgumentException("passwords not match");
        }
        this.isActive = false;
        this.assignedIdProfiles = new ArrayList<>();
        this.assignedIdProfiles.add(profileId);
        this.requestedProfiles = new ArrayList<>();
    }

    /**
     * getting Methods (outside of lombock)
     **/
    public List<UserProfileId> getAssignedProfiles() {
        return new ArrayList<>(assignedIdProfiles);
    }

    public List<Request> getRequestedProfiles() {
        return new ArrayList<>(requestedProfiles);
    }

    /**
     * Setting Methods (outside of lombock)
     **/
    public void setUserName(String userName) {
        this.userName = new Name(userName);
    }

    public void setFunction(String function) {
        this.function = new Function(function);
        }

    private void setPassword(String password) {
        this.password = new Password(password);
    }

    public void setPhoto(String photo) {
        this.photo = new Photo(photo);
    }

//    public boolean setAllData(String userName, String function, String photo) {
//        this.setUserName(userName);
//    }

    public boolean setActive(Boolean x) {
        this.isActive = x;
        return true;
    }

    /**
     * AssignProfileList´s methods
     */
    /*public void assignProfileToUser(UserProfile profile) { NÃO É PRECISO (NUNO)
        this.assignedProfiles.add(profile);
    }*/
    public boolean updateProfile(UserProfile oldProfile, UserProfile newProfile) {

        if (!checkAssignedProfileList(newProfile)) {
            throw new IllegalArgumentException("Repeated user profile inserted.");
        } else {
            this.assignedIdProfiles.remove(oldProfile.getUserProfileId());
            this.assignedIdProfiles.add(newProfile.getUserProfileId());
        }
        return true;
    }

    /**
     * Validation Methods
     **/

    private void checkProfileRules(UserProfileId profileId) {
        if (!profileId.getUserProfileName().getText().equals("Visitor"))
            throw new IllegalArgumentException("at registration visitor profile must be associated");
    }

    //?????????????????????????? (Nuno)
    private boolean checkAssignedProfileList(UserProfile profile) {
        boolean msg = true;
        for (UserProfileId i : assignedIdProfiles) {
            if (i.getUserProfileName().getText().equals(profile.getUserProfileId().getUserProfileName().getText())) {
                msg = false;
                break;
            }
        }
        return msg;
    }

    /**
     * Método para validar se o email (ou parte dele) é deste objeto.
     */
    public boolean isYourEmail(String email) {

        boolean result = false;
        int idxString = this.getSystemUserId().getEmail().getEmail().indexOf(email.toLowerCase());

        if (idxString != -1) {
            result = true;
        }
        return result;
    }

    public boolean hasName(String name) {
        return this.userName.getNameF().equals(name);
    }


    /**
     * Method to validate if user already has the profile requested
     */
    private boolean hasProfileId(UserProfileId profileId) {
        boolean profileStatus = false;

        for (UserProfileId profileIdCheck : assignedIdProfiles) {
            if (profileId.equals(profileIdCheck)) {
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
    private int hasPartiallyName(String name) {
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
    private int hasPartiallyEmail(String email) {
        int result = 0;

        if (!email.isEmpty()) {
            int idxString = this.getSystemUserId().getEmail().getEmail().toLowerCase().indexOf(email.toLowerCase());
            if (idxString != -1) {
                result = 1;
            } else {
                result = -1;
            }
        }
        return result;
    }

    //REVIEW
    private int hasPartiallyFunction(String function) {
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
    private int hasState(int state) {
        int result = 0;
        int check = this.isActive ? 1 : 0;

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
    private int hasAllProfilesInTheList(List<UserProfile> profiles) {
        int result = 0;

        if (!profiles.isEmpty()) {
            //Review, the assignedProfileList isn't null because is created in the constructor...
//            if (this.assignedProfileList != null) {
            int count = 0;

            for (UserProfile k : profiles) {
                if (this.assignedIdProfiles.contains(k.getUserProfileId())) {
                    count++;
                }
            }
            if (count == profiles.size()) {
                result = 1;
            } else {
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

        if (res1 != -1 && res2 != -1 && res3 != -1 && res4 != -1 && res5 != -1) {
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
     * Method to validate the old password from the UI with thew old password from the System User
     **/
    private boolean validateOldPassword(String oldpasswordUI) {

        Password pwd = new Password(oldpasswordUI);

        return pwd.equals(this.password);

    }
////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * Request Creator Method
     **/
    public boolean createAndSaveProfileRequest(UserProfileId profileId) {
        Request newRequest = new Request(profileId);

        if (hasProfileId(profileId) || this.requestedProfiles.contains(newRequest)) {
            return false;
        }
        return this.requestedProfiles.add(newRequest);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * Override Methods
     **/
    @Override
    public boolean sameIdentityAs(SystemUser other) {
        return other != null && systemUserId.sameValueAs(other.systemUserId);
    }

    /**
     * @param o to compare
     * @return True if they have the same identity
     * @see #sameIdentityAs(SystemUser)
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SystemUser that = (SystemUser) o;
        return sameIdentityAs(that);
    }

    /**
     * @return Hash code of systemUser id.
     */
    @Override
    public int hashCode() {
        return systemUserId.hashCode();
    }
}
