package switch2021.project.dtoModel.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@NoArgsConstructor
@AllArgsConstructor
@Relation(collectionRelation = "Tasks")
public class OutputTaskDTO extends RepresentationModel<OutputTaskDTO> {

    public String taskID;
    public String taskName;
    public String taskContainerID;
    public String projectID;
    public String resourceID;
    public String userID;
    public String resourceStartDate;
    public String description;
    public String type;
    public double effortEstimate;
}
