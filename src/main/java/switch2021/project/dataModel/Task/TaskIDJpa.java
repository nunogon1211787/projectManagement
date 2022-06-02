package switch2021.project.dataModel.Task;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import switch2021.project.entities.valueObjects.vos.Name;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import java.io.Serializable;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable


public class TaskIDJpa implements Serializable {

    private String taskContainerID;

    @Embedded
    private Name taskName;

}
