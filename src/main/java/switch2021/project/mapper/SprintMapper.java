package switch2021.project.mapper;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import switch2021.project.dto.OutputSprintDTO;
import switch2021.project.model.Sprint.Sprint;

@Service
public class SprintMapper {

    public OutputSprintDTO toDTO(Sprint newSprint) {
        String sprintID = newSprint.getSprintID().toString();
        String projectID = newSprint.getSprintID().getProjectID().getCode();
        String name = newSprint.getSprintID().getSprintName().getText();
        return new OutputSprintDTO(projectID, sprintID, name);
    }
}
