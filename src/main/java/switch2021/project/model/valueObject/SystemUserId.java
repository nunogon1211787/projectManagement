package switch2021.project.model.valueObject;

import lombok.Getter;
import switch2021.project.utils.ValueObject;

@Getter
public class SystemUserId implements ValueObject<SystemUserId> {

    private final Email email;

    /**
     * Constructor.
     */
    public SystemUserId(String email) {
        this.email = new Email(email);
    }

    /**
     * Override Methods
     **/
    @Override
    public boolean sameValueAs(final SystemUserId other) {
        return other != null && this.email.equals(other.email);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final SystemUserId that = (SystemUserId) o;
        return sameValueAs(that);
    }

    @Override
    public int hashCode() {
        return email.hashCode();
    }
}