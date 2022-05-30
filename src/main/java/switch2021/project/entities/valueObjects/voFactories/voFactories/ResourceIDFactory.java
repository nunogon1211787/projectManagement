package switch2021.project.entities.valueObjects.voFactories.voFactories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import switch2021.project.entities.valueObjects.voFactories.voInterfaces.IProjectIDFactory;
import switch2021.project.entities.valueObjects.voFactories.voInterfaces.IResouceIDFactory;
import switch2021.project.entities.valueObjects.voFactories.voInterfaces.IUserIDFactory;
import switch2021.project.entities.valueObjects.vos.ResourceIDReeng;
import switch2021.project.entities.valueObjects.vos.ProjectID;
import switch2021.project.entities.valueObjects.vos.UserID;

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
        UserID sysUserID = userIDFactory.createUserID(systemUserID);
        ProjectID projectID = projectIDFactory.create(projID);
        LocalDate startDate = LocalDate.parse(startDat);
        return new ResourceIDReeng(sysUserID, projectID, startDate);
    }
}
