package switch2021.project.model;


import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter


public class UserStoryStatus {
    /** Classe UserStoryStatus Atributes **/
    private int idUserStoryStatus;
    private String description;
    private boolean sprintAvailable;  //this attribute means it is available as a status in sprint backlog

    /** Constructor **/
    public UserStoryStatus(String description) {
        this.description = description;
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
        return idUserStoryStatus == that.idUserStoryStatus && sprintAvailable == that.sprintAvailable && Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idUserStoryStatus, description, sprintAvailable);
    }
}
