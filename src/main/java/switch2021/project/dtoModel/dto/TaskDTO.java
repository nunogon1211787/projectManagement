package switch2021.project.dtoModel.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class TaskDTO {
    /**
     * Attributes
     **/
    public String projectId;
    public String sprintName;
    public String usTitle;
    public String systemUserID;
    public String resourceStartDate;
    public String taskName;
    public String taskDescription;
    public double taskEffortEstimate;
    public String taskType;
    //public String responsible;
    //public String taskContainerID;
}
