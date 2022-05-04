package switch2021.project.factory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import switch2021.project.factoryInterface.VOFactoryInterface;
import switch2021.project.factoryInterface.IValueObjectsFactory;
import switch2021.project.model.UserStory.UsTitle;
import switch2021.project.model.UserStory.UserStoryID;
import switch2021.project.model.valueObject.ProjectID;

@Component
public class UserStoryIDFactory implements VOFactoryInterface<UserStoryID> {

    /** Attributes*/

    @Autowired
   private final IValueObjectsFactory<ProjectID> projectIDFactory;

    @Autowired
    private final IValueObjectsFactory<UsTitle> usTitleFactory;


    /** Constructor*/
    public UserStoryIDFactory(IValueObjectsFactory<ProjectID> projectIDFactory,
                              IValueObjectsFactory<UsTitle> usTitleFactory) {
        this.projectIDFactory = projectIDFactory;
        this.usTitleFactory = usTitleFactory;
    }

    /** Method */
    public UserStoryID create(Object p, Object d) {
        ProjectID projectID = projectIDFactory.create(p.toString());
        UsTitle usTitle = usTitleFactory.create(d.toString());
        return new UserStoryID(projectID, usTitle);
    }


}
