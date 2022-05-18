package switch2021.project.model.SystemUser;

import lombok.Getter;
import switch2021.project.dto.UpdateDataDTO;
import switch2021.project.model.valueObject.*;
import switch2021.project.utils.Entity;

import java.util.ArrayList;
import java.util.List;


@Getter
public class User implements Entity<User> {

    /**
     * Attributes of systemUser´s class
     */
    private final SystemUserID systemUserId;
    private Name userName;
    private Photo photo;
    private Description encryptedPassword;
    private Function function;
    private final boolean isActive;
    private final List<UserProfileID> assignedIdProfiles;
    private final List<Request> requestedProfiles;

    /**
     * Constructor
     */
    public User(SystemUserID systemUserId, Name userName, Photo photo, Password password,
                Password passwordConfirmation,
                Function function, UserProfileID visitorID) {
        this.systemUserId = systemUserId;
        this.userName = userName;
        this.photo = photo;
        this.function = function;
        this.isActive = false;
        this.assignedIdProfiles = new ArrayList<>();
        this.requestedProfiles = new ArrayList<>();
        assignValidatedPassword(password, passwordConfirmation);
        assignProfileId(visitorID);
    }

    /**
     * Assigns
     */

    public void assignName(Name userName) {
        this.userName = userName;
    }

    public void assignPhoto(Photo photo) {
        this.photo = photo;
    }

    public void assignFunction(Function function) {
        this.function = function;
    }


    private void assignProfileId(UserProfileID profileId) {
        if (profileId.getUserProfileName().getText().trim().equalsIgnoreCase("visitor")){
            this.assignedIdProfiles.add(profileId);
        } else {
            throw new IllegalArgumentException("at registration visitor profile must be assigned!");
        }
    }

    /**
     * Password validate if passwords are equal and encrypt and assign it to the System User
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
  public boolean updatePassword(String oldPassword, String newPassword){
        boolean msg = false;
        if (validateOldPassword(oldPassword)) {
            encryptPassword(new Password(newPassword));
            msg = true;
        }
        return msg;
    }

    /**
     * Method to validate the old password from the UI with thew old password from the System User
     */
    private boolean validateOldPassword(String oldPassword) {
        Password pwd = new Password(oldPassword);
        return pwd.toString().equals(oldPassword);
    }

    /**
     * Request Create and Save Method
     */
    public boolean createAndSaveProfileRequest(UserProfileID profileId) {
        Request newRequest = new Request(profileId);

        if (hasProfileId(profileId) || this.requestedProfiles.contains(newRequest)) {
            return false;
        }
        return this.requestedProfiles.add(newRequest);
    }


    /**
     * Method to Edit Personal Data
     */

    public boolean editPersonalData(String userName, String function, String photo) {
        boolean msg = false;
        if(userName.trim().equalsIgnoreCase(userName) && function.trim().equalsIgnoreCase(function) &&
                photo.trim().equalsIgnoreCase(photo)) {
            assignName(new Name(userName));
            assignFunction(new Function(function));
            assignPhoto(new Photo(photo));
            msg = true;
        }
        return msg;
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
        return other != null && systemUserId.sameValueAs(other.systemUserId);
    }

    /**
     * @return Hash code of systemUser id.
     */
    @Override
    public int hashCode() {
        return systemUserId.hashCode();
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
    /*

    /**
     * Método para validar se o email (ou parte dele) é deste objeto.
     */
    public boolean isYourEmail(String email) {

        boolean result = false;
        int idxString = this.getSystemUserId().getEmail().getEmailText().indexOf(email.toLowerCase());

        if (idxString != -1) {
            result = true;
        }
        return result;
    }
/*
    public boolean hasName(String name) {
        return this.userName.getText().equals(name);
    }

    /**
     * Method to validate if user already has the profile requested
     */
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
/*
    /**
     * Method to verify if the object has the received parameters.
     */
    //REVIEW
/*    private int hasPartiallyName(String name) {
        int result = 0;

        if (!name.isEmpty()) {
            int idxString = this.userName.getText().toLowerCase().indexOf(name.toLowerCase());
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
            int idxString = this.getSystemUserId().getEmail().getEmailText().toLowerCase().indexOf(email.toLowerCase());
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

 */
}
