package switch2021.project.model.valueObject;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.apache.commons.lang3.builder.EqualsBuilder;
import switch2021.project.interfaces.TaskContainerID;
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
        this.projectID = new ProjectID(values[2]);
        this.sprintName = new Description(values[3]);
    }

    /** Override Methods */

    @Override
    public String toString() {
        return projectID.getCode() + "_" +  sprintName.getText();
    }

    @Override
    public boolean sameValueAs(final SprintID other) {
        return other != null && new EqualsBuilder().
                append(this.projectID, other.projectID).
                append(this.sprintName, other.sprintName).
                isEquals();
    }
}
