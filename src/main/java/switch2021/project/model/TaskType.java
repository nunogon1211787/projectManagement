package switch2021.project.model;

import lombok.Getter;
import switch2021.project.Immutables.Description;

import java.util.Objects;

@Getter
public class TaskType {

    /**
     * Attributes
     */

    private int typeID;
    private Description description;

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
        return this.description.getDescriptionF().equalsIgnoreCase(typeDescription);
    }

    public void setTypeID(int id){
        checkIdRules(id);
        this.typeID = id;
    }

    /**
     * Methods to validate attributes data
     */

    private void checkIdRules(int id) {
        if (id < 1)
            throw new IllegalArgumentException("Type ID cannot be negative.");
    }

    /**
     * Override methods
     */

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TaskType)) return false;
        TaskType taskType = (TaskType) o;
        return typeID == taskType.typeID && Objects.equals(description, taskType.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(typeID, description);
    }
}

