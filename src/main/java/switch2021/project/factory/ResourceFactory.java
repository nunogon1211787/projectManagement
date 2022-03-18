package switch2021.project.factory;

import switch2021.project.model.Resource;
import switch2021.project.model.SystemUser;

import java.time.LocalDate;

public class ResourceFactory {
    private Resource res;
    public Resource createResource(SystemUser user, LocalDate startDate, LocalDate endDate, double costPerHour, double percentageOfAllocation){
            return this.res = new Resource(user, startDate,endDate,costPerHour,percentageOfAllocation);
    }
}
