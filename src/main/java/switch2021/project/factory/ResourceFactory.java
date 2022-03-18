package switch2021.project.factory;

import switch2021.project.model.Resource;
import switch2021.project.model.SystemUser;

import java.time.LocalDate;

public class ResourceFactory {

    public Resource createResource(SystemUser user, LocalDate startDate, LocalDate endDate, double costPerHour, double percentageOfAllocation){
            return new Resource(user, startDate,endDate,costPerHour,percentageOfAllocation);
    }
}
