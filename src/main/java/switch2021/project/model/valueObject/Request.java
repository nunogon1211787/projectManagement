package switch2021.project.model.valueObject;

import lombok.Getter;
import switch2021.project.utils.ValueObject;

import java.time.LocalDate;
import java.util.Objects;

@Getter
public class Request implements ValueObject<Request> {

    /**
     * Class attributes
     **/
    private LocalDate requestDate;
    private UserProfileID profileIdRequested;
    //private int idRequest;
    //private SystemUser user;
    //private UserProfile profileRequested;
    //private boolean requestStatus;

    /**
     * Constructor.
     */
    public Request(UserProfileID profileId) {
        this.requestDate = LocalDate.now();
        //this.user = user;
        //this.profileRequested = profile;
        this.profileIdRequested = profileId;
    }

    /** //Não é preciso (Nuno) Method to change request status */
    /*public void changeRequestStatus() {
        this.requestStatus = true;
    }*/

    /**
     * Override Methods
     */
    @Override
    public boolean sameValueAs(final Request other) {
        return other != null && this.requestDate.equals(other.requestDate)
                && this.profileIdRequested.equals(other.profileIdRequested);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final Request that = (Request) o;
        return sameValueAs(that);
    }

    @Override
    public int hashCode() {
        return Objects.hash(requestDate, profileIdRequested);
    }
}





