package switch2021.project.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TaskStatus {

    /**
     * Class UserStoryStatus Atributes
     **/
    private int task_Id_TaskStatus;
    private String description;

    /**
     * Constructor
     **/
    public TaskStatus(String description) {
        this.description = description;
    }

}
