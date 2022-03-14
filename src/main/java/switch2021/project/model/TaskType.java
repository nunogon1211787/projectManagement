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
    private Description descriptionF;
    //public static final int MINIMUM_CHARACTER = 3;

    /**
     * Constructor
     */

    public TaskType(String description){
        Description description_1 = new Description(description);
        this.descriptionF = description_1;
    }

    /**
     * Methods to iterate with attributes
     */

    public boolean hasDescription(String typeDescription) {
        return this.descriptionF.getDescriptionF().equalsIgnoreCase(typeDescription);
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
        return typeID == taskType.typeID && Objects.equals(descriptionF, taskType.descriptionF);
    }

    @Override
    public int hashCode() {
        return Objects.hash(typeID, descriptionF);
    }
}

