package switch2021.project.factory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import switch2021.project.factoryInterface.VOFactoryInterface;
import switch2021.project.model.Sprint.SprintID;
import switch2021.project.model.valueObject.Description;
import switch2021.project.model.valueObject.ProjectID;

@Component
public class SprintIDFactory implements VOFactoryInterface<SprintID> {

    /** Attributes*/
    @Autowired
    private final ProjectIDFactory projectIDFactory;
    @Autowired
    private final DescriptionFactory descriptionFactory;


    /** Constructor*/

    public SprintIDFactory(ProjectIDFactory projectIDFactory, DescriptionFactory descriptionFactory) {
        this.projectIDFactory = projectIDFactory;
        this.descriptionFactory = descriptionFactory;
    }

    /** Method */
    public SprintID create(Object p, Object d) {
        ProjectID projectID = projectIDFactory.create(p.toString());
        Description sprintName = descriptionFactory.create(d.toString());
        return new SprintID(projectID, sprintName);
    }

}
