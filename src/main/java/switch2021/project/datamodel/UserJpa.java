package switch2021.project.datamodel;

import lombok.*;
import switch2021.project.model.valueObject.Request;
import switch2021.project.model.valueObject.SystemUserID;
import switch2021.project.model.valueObject.UserProfileID;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity(name = "UserJpa")
@NoArgsConstructor
@AllArgsConstructor
//@ToString
@Table(name = "systemUsers")
public class UserJpa {

    @Id
    private String email;
    private String userName;
    private String function;
    private String photo;
    private String password;
    private String isActive;
    @ElementCollection
    private List<UserProfileID> assignedIDProfiles;
    @OneToMany(mappedBy = "userJpa", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<RequestJpa> requests;

    public UserJpa(SystemUserID email, String userName, String function, String photo, String password,
                   String isActive, List<UserProfileID> assignedIDProfiles) {
        this.email = email;
        this.userName = userName;
        this.function = function;
        this.photo = photo;
        this.password = password;
        this.isActive = isActive;
        this.assignedIDProfiles = assignedIDProfiles;
        this.requests = new ArrayList<>();
    }
}