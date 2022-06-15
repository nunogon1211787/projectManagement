package switch2021.project.dataModel.JPA;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import switch2021.project.entities.valueObjects.vos.UserProfileID;

import javax.persistence.*;

@Getter
@Setter
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class RequestJpa{

    @Embedded
    private UserProfileID profileIdRequested;
    @Column(name = "requestDate")
    private String requestDate;
}
