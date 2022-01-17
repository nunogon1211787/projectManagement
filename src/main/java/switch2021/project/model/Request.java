package switch2021.project.model;

import java.time.LocalDate;
import java.util.Objects;

public class Request {

    /**
     * atributos da classe "Request"
     **/

    private int idRequest;
    private LocalDate requestDate;
    private SystemUser user;
    private UserProfile profileRequested;
    private boolean statusRequest;

    /**
     * construtor da classe "Request"
     **/

    public Request(UserProfile profile, SystemUser user) {

        this.idRequest = idRequest;
        this.requestDate = LocalDate.now();
        this.user = user;
        this.profileRequested = profile;
        this.statusRequest = false;
    }

    /**
     * getters da classe "Request"
     **/

    public LocalDate getRequestDate() {return requestDate;}

    public SystemUser getUser() {return user;}

    public UserProfile getProfile() {return this.profileRequested;}

    public Integer getIdRequest() {return this.idRequest;}

    public boolean isStatusRequest() {return statusRequest;}

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

    public void setIdRequest(int idGenerator) {

        this.idRequest = idGenerator;

    }
}





