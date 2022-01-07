package switch2021.project.model;

import java.time.LocalDate;
import java.util.Objects;

public class Request {

    /**
     * atributos da classe "Request"
     **/

    private LocalDate requestDate;
    private LocalDate requestTime;
    private SystemUser user;
    private Profile profileRequested;

    /**
     * construtor da classe "Request"
     **/

    public Request(LocalDate requestDate, LocalDate requestTime, Profile profile, SystemUser user) {
        this.requestDate = requestDate;
        this.requestTime = requestTime;
        this.user = user;
        this.profileRequested = profile;
    }

    /**
     * getters da classe "Request"
     **/

    public LocalDate getRequestDate() {
        return requestDate;
    }

    public LocalDate getRequestTime() {
        return requestTime;
    }

    public SystemUser getUser() {
        return user;
    }

    public Profile getProfile() {
        return this.profileRequested;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Request request = (Request) o;
        return Objects.equals(requestDate, request.requestDate) && Objects.equals(requestTime, request.requestTime) && Objects.equals(user, request.user) && Objects.equals(profileRequested, request.profileRequested);
    }

    @Override
    public int hashCode() {
        return Objects.hash(requestDate, requestTime, user, profileRequested);
    }
}





