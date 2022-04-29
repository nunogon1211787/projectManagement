package switch2021.project.mapper;

import org.springframework.stereotype.Component;
import switch2021.project.dto.OutputSprintDTO;
import switch2021.project.model.Sprint.Sprint;

@Component
public class SprintMapper {

    /** Constructor */
    private SprintMapper() {
    }

    /** Method*/
    public OutputSprintDTO toDTO(Sprint newSprint) {
        OutputSprintDTO outputSprintDTO = new OutputSprintDTO();
        outputSprintDTO.sprintID = newSprint.getSprintID().toString();
        return outputSprintDTO;
    }
}
