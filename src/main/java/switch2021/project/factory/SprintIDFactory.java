package switch2021.project.factory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import switch2021.project.factoryInterface.IDescriptionFactory;
import switch2021.project.factoryInterface.IProjectIDFactory;
import switch2021.project.factoryInterface.ISprintIDFactory;
import switch2021.project.model.valueObject.SprintID;
import switch2021.project.model.valueObject.Description;
import switch2021.project.model.valueObject.ProjectID;

@Component
public class SprintIDFactory implements ISprintIDFactory{

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
    public SprintID create(String p, String d) {
        ProjectID projectID = projectIDFactory.create(p);
        Description sprintName = descriptionFactory.createDescription(d);
        return new SprintID(projectID, sprintName);
    }

}
