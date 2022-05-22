package switch2021.project.datamodel;

import lombok.*;
import switch2021.project.model.valueObject.Request;
import switch2021.project.model.valueObject.UserProfileID;
import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity(name = "UserJpa")
@NoArgsConstructor
@AllArgsConstructor
@ToString
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
    @ElementCollection
    private List<Request> requestList;


}