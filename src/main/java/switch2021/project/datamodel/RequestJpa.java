package switch2021.project.datamodel;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import switch2021.project.model.valueObject.UserProfileID;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Entity(name = "RequestJpa")
@NoArgsConstructor
//@ToString
@Table(name = "requests")
public class RequestJpa implements Serializable {
    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "email", insertable = false, updatable = false)
    private UserJpa userJpa;
    @Embedded
    private UserProfileID profileIdRequested;
    private String requestDate;

    public RequestJpa(String requestDate, UserProfileID profileIdRequested, UserJpa userJpa) {
        this.requestDate = requestDate;
        this.profileIdRequested = profileIdRequested;
        this.userJpa = userJpa;
    }
}
