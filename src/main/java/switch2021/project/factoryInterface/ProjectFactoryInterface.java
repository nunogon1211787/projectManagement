package switch2021.project.factoryInterface;

import org.springframework.stereotype.Component;
import switch2021.project.model.Project.ProjectReeng;

@Component
public interface ProjectFactoryInterface {

    ProjectReeng createProject(String name, String description,String businessSector, String startDate,
                               int numberOfSprints, double budget);
}
