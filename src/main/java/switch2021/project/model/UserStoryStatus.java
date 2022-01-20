package switch2021.project.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor

public class UserStoryStatus {
    /** Classe UserStoryStatus Atributes **/
    private int id_UserStoryStatus;
    private String description;

    /** Constructor **/
    public UserStoryStatus(String description) {
        this.description = description;
    }



}
