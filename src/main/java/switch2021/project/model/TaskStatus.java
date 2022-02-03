package switch2021.project.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Setter
@Getter
public class TaskStatus {

    /**
     * Attributes.
     **/
    private int idTaskStatus;
    private String description;

    /**
     * Constructor.
     **/
    public TaskStatus(String description) {

        checkDescriptionRules(description);
        this.description = description;
    }

    /**
     * Methods to iterate with attributes,
     */

    public boolean hasDescription(String description) {
        return Objects.equals(this.description, description);
    }

    public void setID_TaskStatus(int id){
        checkIdRules(id);
        this.idTaskStatus = id;
    }

    /**
     * Methods to validate attributes data.
     */
    private void checkDescriptionRules(String description) {
        if (description.trim().isEmpty())
            throw new IllegalArgumentException("Name cannot be empty.");
        if ((description.length() < 3))
            throw new IllegalArgumentException("Name must be at least 3 characters");
    }

    private void checkIdRules(int id) {
        if (id < 0)
            throw new IllegalArgumentException("Type ID cannot be negative.");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TaskStatus that = (TaskStatus) o;
        return idTaskStatus == that.idTaskStatus && description.equals(that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idTaskStatus, description);
    }
}
