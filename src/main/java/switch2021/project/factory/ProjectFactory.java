package switch2021.project.factory;

import switch2021.project.factoryInterface.ProjectFactoryInterface;
import switch2021.project.model.Project.ProjectReeng;

import java.time.LocalDate;

public class ProjectFactory implements ProjectFactoryInterface {

    @Override
    public ProjectReeng createProject(String name, String description, String startDate,
                                      String businessSector, int numberOfSprints,
                                      double budget) {

        return new ProjectReeng(name, description, businessSector, LocalDate.parse(startDate), numberOfSprints,
                                       budget);
    }

}
