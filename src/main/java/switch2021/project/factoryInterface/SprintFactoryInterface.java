package switch2021.project.factoryInterface;

import switch2021.project.model.Sprint.Sprint;


public interface SprintFactoryInterface {
    Sprint createSprint(String sprintID, String name);
}
