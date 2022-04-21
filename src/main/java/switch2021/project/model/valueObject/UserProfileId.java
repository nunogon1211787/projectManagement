package switch2021.project.model.valueObject;

import lombok.Getter;
import switch2021.project.utils.ValueObject;

@Getter
public class UserProfileId implements ValueObject<UserProfileId> {

    private final Description userProfileName;

    /**
     * Constructor.
     */
    public UserProfileId(String userProfileName) {

        this.userProfileName = new Description(userProfileName);
    }

    /**
     * Override Methods
     **/
    @Override
    public boolean sameValueAs(final UserProfileId other) {
        return other != null && this.userProfileName.equals(other.userProfileName);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final UserProfileId that = (UserProfileId) o;
        return sameValueAs(that);
    }

    @Override
    public int hashCode() {
        return userProfileName.hashCode();
    }
}
