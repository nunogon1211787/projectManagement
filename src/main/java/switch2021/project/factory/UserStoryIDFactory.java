package switch2021.project.factory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import switch2021.project.factoryInterface.IProjectIDFactory;
import switch2021.project.factoryInterface.IUsTitleFactory;
import switch2021.project.factoryInterface.IUserStoryIDFactory;
import switch2021.project.model.UserStory.UsTitle;
import switch2021.project.model.UserStory.UserStoryID;
import switch2021.project.model.valueObject.ProjectID;

@Component
public class UserStoryIDFactory implements IUserStoryIDFactory {

    /**
     * Attributes
     */
    @Autowired
    private IProjectIDFactory projectIDFactory;
    @Autowired
    private IUsTitleFactory usTitleFactory;


    public UserStoryIDFactory(){}
    /**
     * Constructor
     */
    public UserStoryIDFactory(IProjectIDFactory projectIDFactory, IUsTitleFactory usTitleFactory) {
        this.projectIDFactory = projectIDFactory;
        this.usTitleFactory = usTitleFactory;
    }

    /**
     * Method
     */
    @Override
    public UserStoryID create(String pID, String usT) {
        ProjectID projectID = projectIDFactory.create(pID);
        UsTitle usTitle = usTitleFactory.create(usT);
        return new UserStoryID(projectID, usTitle);
    }


}
