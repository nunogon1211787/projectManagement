package switch2021.project.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor

public class UserStoryStatus {
    /** Classe UserStoryStatus Atributes **/
    private int id_UserStoryStatus;
    private String description;
    private boolean sprintAvailable = false;  //this attribute means it is available as a status in sprint backlog

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


}
