package switch2021.project.model;

import lombok.Getter;

@Getter
public class TaskStatus {

    /**
     * Classe UserStoryStatus Atributes
     **/
    private String description;

    /**
     * Constructor
     **/
    public TaskStatus(String description) {
        this.description = description;
    }

}
