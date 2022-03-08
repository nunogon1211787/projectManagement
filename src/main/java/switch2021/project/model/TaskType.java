package switch2021.project.model;

import lombok.Getter;

import java.util.Objects;

@Getter
public class TaskType {

    /**
     * Attributes.
     */
    private int typeID;
    private String name;

    /**
     * Constructor.
     */

    public TaskType(String name){
        checkNameRules(name);
        this.name = name;
    }

    /**
     * Methods to iterate with attributes,
     */

    public boolean hasName(String typeName) {
        return this.name.equalsIgnoreCase(typeName);
    }

    public void setTypeID(int id){
        checkIdRules(id);
        this.typeID = id;
    }

    /**
     * Methods to validate attributes data.
     */


    private void checkNameRules(String name) {
        if (name.trim().isEmpty())
            throw new IllegalArgumentException("Name cannot be empty.");
        if ((name.length() < 3))
            throw new IllegalArgumentException("Name must be at least 3 characters");
    }

    private void checkIdRules(int id) {
        if (id < 1)
            throw new IllegalArgumentException("Type ID cannot be negative.");
    }

    /**
     * Override methods.
     */

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TaskType taskType = (TaskType) o;
        return typeID == taskType.typeID && name.equals(taskType.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(typeID, name);
    }
}
