package switch2021.project.factory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import switch2021.project.dto.NewSprintDTO;
import switch2021.project.factoryInterface.ISprintFactory;
import switch2021.project.factoryInterface.ISprintIDFactory;
import switch2021.project.model.Sprint.Sprint;

@Component
public class SprintFactory implements ISprintFactory {

    /**
     * Attributes
     */
    @Autowired
    private ISprintIDFactory sprintIDFactory;

    /**
     * Method
     */
    @Override
    public Sprint createSprint(NewSprintDTO dto) {
        return new Sprint(sprintIDFactory.create(dto.projectID, dto.name));
    }
}
