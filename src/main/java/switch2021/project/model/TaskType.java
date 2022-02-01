package switch2021.project.model;

import lombok.Getter;

import java.util.Objects;

@Getter
public class TaskType {

    /**
     * Attributes.
     */
    private int type_ID;
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
        return Objects.equals(this.name, typeName);
    }

    public void setType_ID(int id){
        checkIdRules(id);
        this.type_ID = id;
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
        if (id < 0)
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
        return type_ID == taskType.type_ID && name.equals(taskType.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type_ID, name);
    }
}
