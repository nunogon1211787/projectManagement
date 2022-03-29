package switch2021.project.model.SystemUser;

import lombok.Getter;
import lombok.Setter;
import switch2021.project.model.UserProfile.UserProfile;

import java.time.LocalDate;
import java.util.Objects;

@Getter
@Setter
public class Request {

    /**
     * Class attributes
     **/

    private int idRequest;
    private LocalDate requestDate;
    private SystemUser user;
    private UserProfile profileRequested;
    private boolean requestStatus;

    /**
     * Class constructor
     **/

    public Request(UserProfile profile, SystemUser user) {
        this.requestDate = LocalDate.now();
        this.user = user;
        this.profileRequested = profile;
    }

    /**
     * Method to change request status
     * */
    public void changeRequestStatus(){
        this.requestStatus = true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Request request = (Request) o;
        return Objects.equals(requestDate, request.requestDate)&& Objects.equals(user, request.user) && Objects.equals(profileRequested, request.profileRequested) && Objects.equals(idRequest, request.idRequest);
    }

    @Override
    public int hashCode() {
        return Objects.hash(requestDate,user, profileRequested);
    }
}





