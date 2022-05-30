package switch2021.project.datamodel.Task;

import lombok.*;
import switch2021.project.datamodel.ResourceIDJpa;
import switch2021.project.model.Task.TaskID;

import javax.persistence.*;
import java.util.List;

@Entity(name = "TaskJpa")
@Table(name = "tasks")
@Getter
@Setter
@NoArgsConstructor
//@AllArgsConstructor
//@ToString

public class TaskJpa {

    @EmbeddedId
    private TaskID id;
    private String description;
    private String type;
    private int effortEstimate;
    private String startDate2;
    private String endDate;

    @Embedded
    private ResourceIDJpa responsible;

    @OneToMany(mappedBy = "taskJpa", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<EffortJpa> taskEffortList;

    @ElementCollection
    private List<TaskID> precedenceList;

    public TaskJpa(TaskID id, String description, String type, int effortEstimate, String startDate, String endDate, ResourceIDJpa resourceIDJpa, List<TaskID> precedenceList) {
    }
}
