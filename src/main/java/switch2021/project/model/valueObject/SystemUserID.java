package switch2021.project.model.valueObject;

import lombok.Getter;
import switch2021.project.utils.ValueObject;

@Getter
public class SystemUserID implements ValueObject<SystemUserID> {

    private final Email email;

    /**
     * Constructor.
     */
    public SystemUserID(Email email) {
        this.email = email;
    }

    /**
     * Override Methods
     **/
    @Override
    public boolean sameValueAs(final SystemUserID other) {
        return other != null && this.email.equals(other.email);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final SystemUserID that = (SystemUserID) o;
        return sameValueAs(that);
    }

    @Override
    public int hashCode() {
        return email.hashCode();
    }
}