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

    /**
     * Attributes
     **/
    private TaskContainerID taskContainerID;
    @Embedded
    private Description taskName;

    @Override
    public boolean sameValueAs(TaskID other) {
        return other != null && this.taskContainerID.equals(other.taskContainerID)
                && this.taskName.equals(other.taskName);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final TaskID that = (TaskID) o;
        return sameValueAs(that);
    }

    @Override
    public int hashCode() {
        return Objects.hash(taskContainerID, taskName);
    }

    @Override
    public String toString() {
        return taskContainerID.toString() + "&" + taskName.getText();
    }
}
