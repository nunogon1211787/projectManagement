package switch2021.project.dtoModel.mapper;

import org.springframework.stereotype.Service;
import switch2021.project.dtoModel.dto.OutputSprintDTO;
import switch2021.project.entities.aggregates.Sprint.Sprint;

@Service
public class SprintMapper {

    public OutputSprintDTO toDTO(Sprint newSprint) {
        String sprintID = newSprint.getSprintID().toString();
        String projectID = newSprint.getSprintID().getProjectID().getCode();
        String name = newSprint.getSprintID().getSprintName().getText();
        return new OutputSprintDTO(projectID, sprintID, name);
    }
}
