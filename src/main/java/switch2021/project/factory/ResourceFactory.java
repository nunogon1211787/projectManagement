package switch2021.project.factory;

import switch2021.project.factoryInterface.ResourceFactoryInterface;
import switch2021.project.valueObject.Resource.Resource;
import switch2021.project.model.SystemUser.SystemUser;
import java.time.LocalDate;

public class ResourceFactory implements ResourceFactoryInterface {

    public Resource createResource(SystemUser user, LocalDate startDate, LocalDate endDate, double costPerHour, double percentageOfAllocation){
            return new Resource(user, startDate,endDate,costPerHour,percentageOfAllocation);
    }
}
