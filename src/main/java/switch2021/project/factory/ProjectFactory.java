package switch2021.project.factory;

import switch2021.project.factoryInterface.ProjectFactoryInterface;
import switch2021.project.model.Project.Project;

import java.time.LocalDate;

public class ProjectFactory implements ProjectFactoryInterface {

    @Override
    public Project createProject(String name, String description, String startDate, int numberOfSprints, double budget) {

        return null;  //new Project(name, description, startDate , numberOfSprints, budget)  //TODO not implemented yet!!
    }

}
