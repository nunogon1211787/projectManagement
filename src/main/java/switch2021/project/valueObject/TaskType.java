package switch2021.project.valueObject;

import lombok.Getter;
import java.util.Objects;

@Getter
public class TaskType {

    /**
     * Attributes
     */

    private final Description description;

    /**
     * Constructor
     */

    public TaskType(String description){
        this.description = new Description(description);
    }

    /**
     * Methods to iterate with attributes
     */

    public boolean hasDescription(String typeDescription) {
        return this.description.getText().equalsIgnoreCase(typeDescription);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TaskType)) return false;
        TaskType taskType = (TaskType) o;
        return Objects.equals(description, taskType.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(description);
    }
}

