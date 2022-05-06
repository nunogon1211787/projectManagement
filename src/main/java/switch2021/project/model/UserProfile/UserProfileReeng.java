package switch2021.project.model.UserProfile;

import lombok.Getter;
import lombok.Setter;
import switch2021.project.model.valueObject.UserProfileId;
import switch2021.project.utils.Entity;

@Getter
@Setter
public class UserProfileReeng implements Entity<UserProfileReeng> {

    /**
     * Class Profile Attributes
     **/
    private final UserProfileId userProfileId;

    /**
     * Profile Constructors
     */
    public UserProfileReeng(UserProfileId userProfileId) {

        this.userProfileId = userProfileId;
    }


    /**
     * Override Methods
     **/
    @Override
    public boolean sameIdentityAs(UserProfileReeng other) {
        return other != null && userProfileId.sameValueAs(other.userProfileId);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserProfileReeng that = (UserProfileReeng) o;
        return sameIdentityAs(that);
    }

    @Override
    public int hashCode() {
        return userProfileId.hashCode();
    }

}
