package switch2021.project.dto;

import java.time.LocalDate;

public class TaskEffortDTO {
    private TaskIdDTO taskIdDTO;
    private int workHours;
    private int workMinutes;
    private LocalDate workDate;
    private String comment;
    private String attachment;
    private String email;

    public TaskEffortDTO(TaskIdDTO taskIdDTO, int workHours, int workMinutes, LocalDate workDate, String comment, String attachment, String email) {
        this.taskIdDTO = taskIdDTO;
        this.workHours = workHours;
        this.workMinutes = workMinutes;
        this.workDate = workDate;
        this.comment = comment;
        this.attachment = attachment;
        this.email = email;
    }

    public TaskIdDTO getTaskIdDTO() {
        return taskIdDTO;
    }

    public int getWorkHours() {
        return workHours;
    }

    public int getWorkMinutes() {
        return workMinutes;
    }

    public LocalDate getWorkDate() {
        return workDate;
    }

    public String getComment() {
        return comment;
    }

    public String getAttachment() {
        return attachment;
    }

    public String getEmail() {
        return email;
    }
}
