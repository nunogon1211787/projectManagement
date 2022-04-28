package switch2021.project.dto;

import switch2021.project.model.Resource.Resource;
import switch2021.project.model.Task.TaskTypeEnum;

public class TaskDTO {

    /**
     * Attributes
     **/
    public String name;
    public String description;
    public double effortEstimate;
    public TaskTypeEnum type;
    public Resource responsible;

    /**
     * Constructor to test (without SINGLETON)
     **/
    public TaskDTO(){

    }
}
