package switch2021.project.factoryInterface;

import org.springframework.stereotype.Component;
import switch2021.project.model.Sprint.Sprint;

@Component
public interface ISprintFactory {
    Sprint createSprint(String projectId, String sprintID, String name);
}
