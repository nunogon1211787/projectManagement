package switch2021.project.factory;

import switch2021.project.factoryInterface.SprintFactoryInterface;
import switch2021.project.model.Sprint.Sprint;


public class SprintFactory implements SprintFactoryInterface {

    @Override
    public Sprint createSprint(String sprintId, String name) {
        return new Sprint(sprintId, name);
    }
}
