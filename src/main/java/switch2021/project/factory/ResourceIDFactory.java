package switch2021.project.factory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import switch2021.project.factoryInterface.*;
import switch2021.project.model.Resource.ResourceIDReeng;
import switch2021.project.model.valueObject.ProjectID;
import switch2021.project.model.valueObject.SystemUserID;
import switch2021.project.model.valueObject.UsTitle;
import switch2021.project.model.valueObject.UserStoryID;

import java.time.LocalDate;

@Component
public class ResourceIDFactory implements IResouceIDFactory {

    /**
     * Attributes
     */
    @Autowired
    private IProjectIDFactory projectIDFactory;
    @Autowired
    private IUserIDFactory userIDFactory;

    private LocalDate startDate;


    public ResourceIDFactory(){}
    /**
     * Constructor
     */
    public ResourceIDFactory(IUserIDFactory userIDFactory, IProjectIDFactory projectIDFactory, LocalDate startDate) {
        this.projectIDFactory = projectIDFactory;
        this.userIDFactory = userIDFactory;
        this.startDate = startDate;
    }

    /**
     * Method
     */

    @Override
    public ResourceIDReeng create(String systemUserID, String projID, String startDat) {
        SystemUserID sysUserID = userIDFactory.createUserID(systemUserID);
        ProjectID projectID = projectIDFactory.create(projID);
        LocalDate startDate = LocalDate.parse(startDat);
        return new ResourceIDReeng(sysUserID, projectID, startDate);
    }
}
