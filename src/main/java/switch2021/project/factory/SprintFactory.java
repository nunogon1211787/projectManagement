package switch2021.project.factory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import switch2021.project.dto.NewSprintDTO;
import switch2021.project.factoryInterface.ISprintFactory;
import switch2021.project.factoryInterface.VOFactoryInterface;
import switch2021.project.model.Sprint.Sprint;
import switch2021.project.model.Sprint.SprintID;

@Component
public class SprintFactory implements ISprintFactory {

    /**
     * Attributes
     */
    @Autowired
    private VOFactoryInterface<SprintID> sprintIDFactory;

    /**
     * Constructor
     */
    public SprintFactory(VOFactoryInterface<SprintID> id) {
        this.sprintIDFactory = id;
    }

    /**
     * Method
     */
    @Override
    public Sprint createSprint(NewSprintDTO dto) {
        Sprint newSprint = new Sprint(sprintIDFactory.create(dto.projectID, dto.name));
        return newSprint;
    }
}
