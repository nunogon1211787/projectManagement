package switch2021.project.dtoModel.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TaskDTO {

    /**
     * Attributes
     **/
    public String name;
    public String description;
    public double effortEstimate;
    public String type;
    public String responsible;
    public String taskContainerID;
}
