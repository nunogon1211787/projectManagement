package switch2021.project.dto;

import lombok.Getter;

@Getter

public class TaskIdNameDTO {

    /**
     * Attributes
     **/

    private int taskId;
    private String taskName;

    /**
     * Constructor to test (without SINGLETON)
     **/

    public TaskIdNameDTO(int taskId, String taskName) {
        this.taskId = taskId;
        this.taskName = taskName;
    }

}
