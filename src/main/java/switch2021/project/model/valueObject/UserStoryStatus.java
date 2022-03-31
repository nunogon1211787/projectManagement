package switch2021.project.model.valueObject;

import lombok.Getter;
import java.util.Objects;

@Getter
public class UserStoryStatus {

    /** Class UserStoryStatus Attributes **/
    private Description description;
    private boolean sprintAvailable;  //this attribute determines if the status is available as a sprint backlog status

    /** Constructor **/
    public UserStoryStatus(String description) {
        this.description = new Description(description);
    }
    public boolean isSprintAvailable() {
        return sprintAvailable;
    }

    public void setSprintAvailable(boolean sprintAvailable) {
        this.sprintAvailable = sprintAvailable;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserStoryStatus that = (UserStoryStatus) o;
        return sprintAvailable == that.sprintAvailable && Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(description, sprintAvailable);
    }
}