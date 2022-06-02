package switch2021.project.entities.valueObjects.vos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import switch2021.project.applicationServices.iRepositories.TaskContainerID;
import switch2021.project.utils.ValueObject;

import javax.persistence.Embedded;
import java.util.Objects;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TaskID implements ValueObject<TaskID> {

    /** Attributes */

    private TaskContainerID taskContainerID;
    @Embedded
    private Name taskName;

//    public TaskID (TaskContainerID taskContainerID, Name name){
//
//        this.taskName = name;
//        this.taskContainerID = taskContainerID;
//
//    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TaskID taskID = (TaskID) o;
        return Objects.equals(taskContainerID, taskID.taskContainerID) && Objects.equals(taskName, taskID.taskName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(taskContainerID, taskName);
    }

    @Override
    public boolean sameValueAs(TaskID other) {
        return false;
    }
}
