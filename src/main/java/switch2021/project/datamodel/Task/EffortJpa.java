package switch2021.project.datamodel.Task;

import lombok.*;
import switch2021.project.model.Task.TaskID;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
//@AllArgsConstructor
//@ToString
//@Embeddable
@Entity(name = "EffortJpa")
@Table(name = "effortList")

public class EffortJpa implements Serializable {

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "ID", insertable = false, updatable = false)
    @JoinColumn(name= "task", insertable = false, updatable = false)
    private TaskJpa taskID;
    private int effortHours;
    private int effortMinutes;
    private String effortDate;
    private String comment;
    private String attachment;

    public EffortJpa(TaskJpa taskID, int effortHours, int effortMinutes, String effortDate, String comment, String attachment){
        this.taskID = taskID;
        this.effortHours = effortHours;
        this.effortMinutes = effortMinutes;
        this.effortDate = effortDate;
        this.comment = comment;
        this.attachment = attachment;
    }

}
