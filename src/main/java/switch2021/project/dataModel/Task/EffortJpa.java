package switch2021.project.dataModel.Task;

import lombok.*;
import switch2021.project.entities.valueObjects.vos.Description;


import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
//@ToString
@Embeddable

public class EffortJpa implements Serializable {

    private int effortHours;
    private int effortMinutes;
    private String effortDate;

    @Embedded
    private Description comment;

    private String attachment;


}
