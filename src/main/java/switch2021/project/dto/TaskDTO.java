package switch2021.project.dto;

import lombok.Getter;


@Getter
public class TaskDTO {

    /*** Attributes **/
    public String name;
    public String description;
    public double effortEstimate;
    public String type;
    public String responsible;
    public String taskContainerID;




    public TaskDTO(){

    }
}
