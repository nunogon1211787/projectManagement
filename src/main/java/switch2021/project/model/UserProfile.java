package switch2021.project.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
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


