package switch2021.project.mapper;

import org.springframework.stereotype.Component;
import switch2021.project.dto.OutPutSprintDTO;
import switch2021.project.model.Sprint.Sprint;

@Component
public class SprintMapper {


    /** Method*/
    public OutPutSprintDTO toDTO(Sprint newSprint) {
        String sprintID = newSprint.getSprintID().toString();
        String projectID = newSprint.getSprintID().getProjectID().getCode();
        String name = newSprint.getSprintID().getSprintName().getText();
        return new OutPutSprintDTO(projectID, sprintID, name);
    }
}
