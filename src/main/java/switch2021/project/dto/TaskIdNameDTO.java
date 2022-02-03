package switch2021.project.dto;

public class TaskIdNameDTO {
    private int taskId;
    private String taskName;

    public TaskIdNameDTO(int taskId, String taskName) {
        this.taskId = taskId;
        this.taskName = taskName;
    }

    public int getTaskId() {
        return taskId;
    }

    public String getTaskName() {
        return taskName;
    }
}
