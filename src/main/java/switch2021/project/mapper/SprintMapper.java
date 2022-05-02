package switch2021.project.mapper;

import org.springframework.stereotype.Component;
import switch2021.project.dto.SprintDTO;
import switch2021.project.model.Sprint.Sprint;

@Component
public class SprintMapper {

    /** Constructor */
    private SprintMapper() {
    }

    /** Method*/
    public SprintDTO toDTO(Sprint newSprint) {
        SprintDTO outPutSprintDTO = new SprintDTO();
        outPutSprintDTO.sprintID = newSprint.getSprintID().toString();
        outPutSprintDTO.projectID = newSprint.getSprintID().getProjectID().toString();
        outPutSprintDTO.name = newSprint.getSprintID().getSprintName().getText();
    return outPutSprintDTO;
    }
}
