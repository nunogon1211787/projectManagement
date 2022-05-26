package switch2021.project.datamodel.Task;


import lombok.*;

import switch2021.project.model.valueObject.*;

import javax.persistence.Embeddable;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Embeddable


public class EffortJpa {

    private Hours effortHours;
    private Minutes effortMinutes;
    private Date effortDate;
    private Description comment;
    private Attachment attachment;

}
