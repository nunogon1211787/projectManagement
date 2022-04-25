package switch2021.project.model.Sprint;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import switch2021.project.interfaces.TaskContainerID;
import switch2021.project.model.valueObject.Description;
import switch2021.project.model.valueObject.ProjectID;
import switch2021.project.utils.ValueObject;

@Getter
@EqualsAndHashCode
public class SprintID implements ValueObject<SprintID>, TaskContainerID {

    /** Attributes */
    private final ProjectID projectID;
    private final Description sprintName;


    /** Constructor */
    public SprintID(ProjectID projectID, Description sprintName){
        this.projectID = projectID;
        this.sprintName = sprintName;
    }

    public SprintID(String sprintIdString){
        String [] values = sprintIdString.split("_");
        this.projectID = new ProjectID(Integer.parseInt(values[2]));
        this.sprintName = new Description(values[3]);
    }


    /** Override Methods */

    @Override
    public String toString() {
        return projectID.getCode() + "_" +  sprintName.getText();
    }

    @Override
    public boolean sameValueAs(SprintID other) {
        return false;
    }
}
