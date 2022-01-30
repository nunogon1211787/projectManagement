package switch2021.project.dto;

import lombok.Getter;
import switch2021.project.model.TaskType;

@Getter
public class CreateTaskDTO {

    private String name;
    private String description;
    private int effortEstimate;
    private String typeName;
    private String responsible;

}
