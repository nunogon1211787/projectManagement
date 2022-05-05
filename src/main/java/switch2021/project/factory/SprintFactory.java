package switch2021.project.factory;

import org.springframework.stereotype.Component;
import switch2021.project.dto.SprintDTO;
import switch2021.project.model.Sprint.Sprint;
import switch2021.project.model.Sprint.SprintID;
import switch2021.project.model.valueObject.Description;
import switch2021.project.model.valueObject.Email;
import switch2021.project.model.valueObject.ProjectID;
import switch2021.project.model.valueObject.SystemUserID;

@Component
public class SprintFactory implements switch2021.project.factoryInterface.ISprintFactory {

    @Override
    public Sprint createSprint(SprintDTO dto) {
        ProjectID projectID = new ProjectID(dto.projectID);
        Description name = new Description(dto.name);
        SprintID sprintID = new SprintID(projectID, name);
        return new Sprint(sprintID);
    }
}