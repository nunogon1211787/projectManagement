package switch2021.project.dto;

import switch2021.project.interfaces.TaskContainerID;
import switch2021.project.model.Resource.old.Resource;
import switch2021.project.model.Task.TaskTypeEnum;
import switch2021.project.model.valueObject.ProjectID;

import java.time.LocalDate;

public class TaskDTO {

    /*** Attributes **/
    public String name;
    public String description;
    public double effortEstimate;
    public String type;
    public String responsible;
    public String taskContainerID;
    public String projectID;
    public String resDate;


    public TaskDTO(){

    }
}
