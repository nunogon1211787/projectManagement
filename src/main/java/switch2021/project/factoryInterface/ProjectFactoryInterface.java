package switch2021.project.factoryInterface;

import switch2021.project.model.Project.Project;

import java.time.LocalDate;

public interface ProjectFactoryInterface {

    Project createProject(String name, String description, LocalDate startDate, int numberOfSprints, int budget);
}
