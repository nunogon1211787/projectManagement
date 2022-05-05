package switch2021.project.factoryInterface;

import switch2021.project.dto.NewSprintDTO;
import switch2021.project.model.Sprint.Sprint;

public interface ISprintFactory {
    Sprint createSprint(NewSprintDTO dto);
}
