package switch2021.project.factory;

import switch2021.project.model.Sprint.Sprint;


public class SprintFactory implements switch2021.project.factoryInterface.ISprintFactory {

    @Override
    public Sprint createSprint(String projectID, String sprintId, String name) {
        return new Sprint(projectID, sprintId, name);
    }
}
