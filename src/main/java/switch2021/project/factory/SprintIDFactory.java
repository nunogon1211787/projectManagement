package switch2021.project.factory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import switch2021.project.factoryInterface.IDescriptionFactory;
import switch2021.project.factoryInterface.IProjectIDFactory;
import switch2021.project.factoryInterface.VOFactoryInterface;
import switch2021.project.model.Sprint.SprintID;
import switch2021.project.model.valueObject.Description;
import switch2021.project.model.valueObject.ProjectID;

@Component
public class SprintIDFactory implements VOFactoryInterface<SprintID> {

    /**
     * Attributes
     */
    @Autowired
    private IProjectIDFactory projectIDFactory;
    @Autowired
    private IDescriptionFactory descriptionFactory;

    /**
     * Method
     */
    public SprintID create(Object p, Object d) {
        ProjectID projectID = projectIDFactory.create(p.toString());
        Description sprintName = descriptionFactory.createDescription(d.toString());
        return new SprintID(projectID, sprintName);
    }

}