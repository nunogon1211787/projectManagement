package switch2021.project.factoryInterface;

import switch2021.project.model.Project.Project;


public interface ProjectFactoryInterface {

    Project createProject(String name, String description, String startDate, int numberOfSprints, double budget);
}
