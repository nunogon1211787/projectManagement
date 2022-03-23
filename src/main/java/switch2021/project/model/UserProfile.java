package switch2021.project.model;

import lombok.Getter;
import switch2021.project.immutable.Description;
import java.util.Objects;

@Getter
public class UserProfile {

    /**
     * Class Profile Attributes
     **/
    private final Description userProfileName;

    /**
     * Profile Constructors
     */
    public UserProfile(String userProfileName) {
        this.userProfileName = new Description(userProfileName);
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


