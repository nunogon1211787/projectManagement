package switch2021.project.entities.valueObjects.vos;

import lombok.Getter;

import java.util.Objects;

@Getter
public class UserStoryStatus {

    /**
     * Class UserStoryStatus Attributes
     **/
    private final Description description;
    private final boolean sprintAvailable;  //this attribute determines if the status is available as a sprint backlog status


    /**
     * Constructor
     **/
    public UserStoryStatus(String description, boolean sprintAvailable) {
        this.description = new Description(description);
        this.sprintAvailable = sprintAvailable;
    }


    /**
     * Methods to iterate with attributes
     */
    public boolean hasDescription(String description) {
        return this.description.getText().equals(description);
    }


    /**
     * Override
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserStoryStatus that = (UserStoryStatus) o;
        return sprintAvailable == that.sprintAvailable;
    }

    @Override
    public int hashCode() {
        return Objects.hash(description, sprintAvailable);
    }
}