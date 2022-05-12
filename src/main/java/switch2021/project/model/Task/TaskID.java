package switch2021.project.model.Task;

import lombok.Getter;
import switch2021.project.interfaces.TaskContainerID;
import switch2021.project.model.valueObject.Name;
import switch2021.project.utils.ValueObject;

import java.util.Objects;

@Getter
public class TaskID implements ValueObject<TaskID> {

    /** Attributes */
    private final TaskContainerID taskContainerID;
    private final Name taskName;

    public TaskID (TaskContainerID taskContainerID, Name name){

        this.taskName = name;
        this.taskContainerID = taskContainerID;

    }

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
