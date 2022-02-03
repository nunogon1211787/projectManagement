package switch2021.project.dto;

import java.time.LocalDate;

public class TaskEffortDTO {
    private TaskIdNameDTO taskIdNameDTO;
    private int effortHours;
    private int effortMinutes;
    private LocalDate effortDate;
    private String comment;
    private String attachment;

    public TaskEffortDTO(TaskIdNameDTO taskIdNameDTO, int effortHours, int effortMinutes, LocalDate effortDate, String comment, String attachment) {
        this.taskIdNameDTO = taskIdNameDTO;
        this.effortHours = effortHours;
        this.effortMinutes = effortMinutes;
        this.effortDate = effortDate;
        this.comment = comment;
        this.attachment = attachment;
    }

    public TaskIdNameDTO getTaskIdNameDTO() {
        return taskIdNameDTO;
    }

    public int getEffortHours() {
        return effortHours;
    }

    public int getEffortMinutes() {
        return effortMinutes;
    }

    public LocalDate getEffortDate() {
        return effortDate;
    }

    public String getComment() {
        return comment;
    }

    public String getAttachment() {
        return attachment;
    }

}
