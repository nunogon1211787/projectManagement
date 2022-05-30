package switch2021.project.entities.aggregates.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import switch2021.project.entities.valueObjects.vos.*;
import switch2021.project.utils.Entity;

import java.util.ArrayList;
import java.util.List;


@Getter
@AllArgsConstructor
public class User implements Entity<User> {

    /**
     * Attributes of systemUser´s class
     */
    private final UserID userId;
    private Name userName;
    private Photo photo;
    private Description encryptedPassword;
    private Function function;
    private boolean isActive;
    private List<UserProfileID> assignedIdProfiles;
    private List<Request> requestedProfiles;


    /**
     * Constructor
     */
    public User(UserID userId, Name userName, Photo photo, Password password,
                Password passwordConfirmation,
                Function function, UserProfileID visitorID) {
        this.userId = userId;
        this.userName = userName;
        this.photo = photo;
        this.function = function;
        this.isActive = false;
        this.assignedIdProfiles = new ArrayList<>();
        this.requestedProfiles = new ArrayList<>();
        assignValidatedPassword(password, passwordConfirmation);
        assignProfileId(visitorID);
    }

    public User(UserID userId, Name userName, Photo photo, Description encryptedPassword,
                Function function, boolean isActive, List<UserProfileID> assignedIdProfiles) {
        this.userId = userId;
        this.userName = userName;
        this.photo = photo;
        this.encryptedPassword = encryptedPassword;
        this.function = function;
        this.isActive = isActive;
        this.assignedIdProfiles = assignedIdProfiles;
        this.requestedProfiles = new ArrayList<>();
    }


    /**
     * Assigns
     */
    private void assignName(Name userName) {
        this.userName = userName;
    }

    private void assignPhoto(Photo photo) {
        this.photo = photo;
    }

    private void assignFunction(Function function) {
        this.function = function;
    }


    private void assignProfileId(UserProfileID profileId) {
        if (profileId.getUserProfileName().getText().trim().equalsIgnoreCase("visitor")) {
            this.assignedIdProfiles.add(profileId);
        } else {
            throw new IllegalArgumentException("at registration visitor profile must be assigned!");
        }
    }


    /**
     * Password validate if passwords are equal and encrypt and assign it to the System User
     *
     * @param newPassword,newPasswordConfirmation [Password v.o.'s]
     * @see #encryptPassword(Password)
     */
    private void assignValidatedPassword(Password newPassword, Password newPasswordConfirmation) {
        if (newPassword.equals(newPasswordConfirmation)) {
            this.encryptedPassword = encryptPassword(newPassword);
        } else {
            throw new IllegalArgumentException("passwords not match");
        }
    }


    /**
     * Password encryption
     *
     * @param password [v.o.]
     * @return v.o. Description(encrypted string)
     */
    private Description encryptPassword(Password password) {
        int codigoASCII;
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < password.getPwd().length(); i++) {
            codigoASCII = password.getPwd().charAt(i) + 99;
            stringBuilder.append((char) codigoASCII);
        }
        return new Description(stringBuilder.toString());
    }

    private Description decryptPassword() {
        int codigoASCII;
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < this.encryptedPassword.getText().length(); i++) {
            codigoASCII = this.encryptedPassword.getText().charAt(i) - 99;
            stringBuilder.append((char) codigoASCII);
        }
        return new Description(stringBuilder.toString());
    }


    /**
     * Method to update old password with the new password
     */
    public void updatePassword(String oldPassword, String newPassword) {
        if (validateOldPassword(oldPassword)) {
            this.encryptedPassword = encryptPassword(new Password(newPassword));
        } else {
         throw new IllegalArgumentException("The password is incorrect!");
        }
    }


    /**
     * Method to validate the old password from the UI with thew old password from the System User
     */
    private boolean validateOldPassword(String oldPassword) {
        Description password = new Description(oldPassword );
        return decryptPassword().equals(password);
    }


    /**
     * Request Creation Method
     */
    public boolean createProfileRequest(UserProfileID profileId) {
        Request newRequest = new Request(profileId);
        return this.requestedProfiles.add(newRequest);
    }


    /**
     * Method to Edit Personal Data
     */
//    public boolean editPersonalData(String userName, String function, String photo) {
//        boolean msg = false;
//        if(userName.trim().equalsIgnoreCase(userName) && function.trim().equalsIgnoreCase(function) &&
//                photo.trim().equalsIgnoreCase(photo)) {
//            assignName(new Name(userName));
//            assignFunction(new Function(function));
//            assignPhoto(new Photo(photo));
//            msg = true;
//        }
//        return msg;
//    }
    public void editPersonalData(String userName, String function, String photo) {
        if (userName != null) {
            assignName(new Name(userName));
        }
        if(function != null) {
            assignFunction(new Function(function));
        }
        if (photo != null) {
            assignPhoto(new Photo(photo));
        }
    }

    /**
     * Activation Status Methods
     */
    public boolean activateStatus() {
        //Can't be simplified because the status is a business rule
        if (this.isActive == false) {
            isActive = true;
            return true;
        } else {
            return false;
        }
    }

    public boolean inactivateStatus() {
        //Can't be simplified because the status is a business rule
        if (this.isActive == true) {
            isActive = false;
            return true;
        } else {
            return false;
        }
    }


    /**
     * Override Methods
     *
     * @param o to compare
     * @return True if they have the same identity
     * @see #sameIdentityAs(User)
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User that = (User) o;
        return sameIdentityAs(that);
    }

    @Override
    public boolean sameIdentityAs(User other) {
        return other != null && userId.sameValueAs(other.userId);
    }

    /**
     * @return Hash code of systemUser id.
     */
    @Override
    public int hashCode() {
        return userId.hashCode();
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////
    //OLD METHODS

    /**
     * getting Methods (outside of lombock)
     */
    public List<UserProfileID> getAssignedProfiles() {
        return new ArrayList<>(assignedIdProfiles);
    }

    public List<Request> getRequestedProfiles() {
        return new ArrayList<>(requestedProfiles);
    }

    /*
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

/*
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
 */

    public boolean isYourEmail(String email) {

        boolean result = false;
        int idxString = this.getUserId().getEmail().getEmailText().indexOf(email.toLowerCase());

        if (idxString != -1) {
            result = true;
        }
        return result;
    }

    public boolean hasName(String name) {
        return this.userName.getText().equals(name);
    }

    public boolean hasFunction(String function) {
        return this.function.getText().equals(function);
    }

    private boolean hasProfileId(UserProfileID profileId) {
        boolean profileStatus = false;

        for (UserProfileID profileIdCheck : assignedIdProfiles) {
            if (profileId.equals(profileIdCheck)) {
                profileStatus = true;
                break;
            }
        }
        return profileStatus;
    }
}
