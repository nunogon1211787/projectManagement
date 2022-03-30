package switch2021.project.valueObject;

import lombok.Getter;
import java.util.Objects;

@Getter
public class TaskStatus {

    /**
     * Attributes
     **/
    private Description description;


    /**
     * Constructor
     **/
    public TaskStatus(String description) {
        this.description = new Description(description);
    }


    /**
     * Methods to iterate with attributes
     */
    public boolean hasDescription(String description) {
        return this.description.getText().equals(description);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TaskStatus that = (TaskStatus) o;
        return description.equals(that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(description);
    }
}
