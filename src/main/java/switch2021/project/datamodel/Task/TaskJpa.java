package switch2021.project.datamodel.Task;


import lombok.*;
import switch2021.project.model.Resource.ResourceIDReeng;
import switch2021.project.model.Task.TaskID;

import javax.persistence.*;
import java.util.List;

@Entity(name = "TaskJpa")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TaskJpa {

    @EmbeddedId
    private TaskID id;
    private String description;
    private String type;
    private int effortEstimate;
    private String startDate;
    private String endDate;

    @Embedded
    private ResourceIDReeng responsible;

    @ElementCollection
    private List<EffortJpa> taskEffortList;

    @ElementCollection
    private List<TaskID> precedenceList;



}
