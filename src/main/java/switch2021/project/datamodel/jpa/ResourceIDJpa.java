package switch2021.project.dataModel.jpa;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import switch2021.project.entities.valueObjects.vos.ProjectID;
import switch2021.project.entities.valueObjects.vos.UserID;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import java.io.Serializable;

@Getter
@Setter
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class ResourceIDJpa implements Serializable {

    @Embedded
    private UserID user;
    @Embedded
    private ProjectID project;
    private String startDate;
}


