package switch2021.project.model;

import java.util.Objects;

public class UserProfile {

    /**
     * Class Profile Attributes
     **/
    private int idProfile;
    private String userProfileName;


    /**
     * Profile Constructors
     */
    public UserProfile(String userProfileName) {
        checkAttributeRules(userProfileName);
        this.userProfileName = userProfileName;
    }


    /**
     * Copy Constructor
     */
    public UserProfile(UserProfile originalProfile) {
        this.idProfile = originalProfile.idProfile;
        this.userProfileName = originalProfile.userProfileName;
    }


    /**
     * Getters e Setters
     **/
    public int getIdUserProfile() {
        return idProfile;
    }

    public String getUserProfileName() {
        return this.userProfileName;
    }

    public void setUserProfileName(String newName) {
        this.userProfileName = newName;
    }

    public void setIdUserProfile(int id) {
        this.idProfile = id;
    }


    /**
     * Method to validate entered data (name) by Administrator
     */
    private void checkAttributeRules(String name) {
        //Check empty field on name
        if ((name == null || name.trim().isEmpty())) throw new IllegalArgumentException("Name cannot be blank.");
    }


    /**
     * Override Methods
     **/
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserProfile that = (UserProfile) o;
        return (this.userProfileName.equals(that.userProfileName));
    }

    @Override
    public int hashCode() {
        return Objects.hash(userProfileName);
    }
}


