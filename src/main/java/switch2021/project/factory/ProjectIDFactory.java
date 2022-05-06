package switch2021.project.factory;

import org.springframework.stereotype.Component;
import switch2021.project.factoryInterface.IProjectIDFactory;
import switch2021.project.model.valueObject.ProjectID;

@Component
public class ProjectIDFactory implements IProjectIDFactory {
    @Override
    public ProjectID create(String projectID) {
        return new ProjectID(projectID);
    }
}
