package switch2021.project.mapper;

import org.springframework.stereotype.Component;
import switch2021.project.dto.NewSprintDTO;
import switch2021.project.dto.OutPutSprintDTO;
import switch2021.project.model.Sprint.Sprint;

@Component
public class SprintMapper {

    /** Constructor */
    public SprintMapper() {
    }

    /** Method*/
    public OutPutSprintDTO toDTO(Sprint newSprint) {
        OutPutSprintDTO outPutSprintDTO = new OutPutSprintDTO();
        outPutSprintDTO.sprintID = newSprint.getSprintID().toString();
        outPutSprintDTO.projectID = newSprint.getSprintID().getProjectID().getCode();
        outPutSprintDTO.name = newSprint.getSprintID().getSprintName().getText();
    return outPutSprintDTO;
    }
}
