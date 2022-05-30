package switch2021.project.entities.valueObjects.voFactories.voFactories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import switch2021.project.entities.valueObjects.voFactories.voInterfaces.IDescriptionFactory;
import switch2021.project.entities.valueObjects.voFactories.voInterfaces.IProjectIDFactory;
import switch2021.project.entities.valueObjects.voFactories.voInterfaces.ISprintIDFactory;
import switch2021.project.entities.valueObjects.vos.SprintID;
import switch2021.project.entities.valueObjects.vos.Description;
import switch2021.project.entities.valueObjects.vos.ProjectID;

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
