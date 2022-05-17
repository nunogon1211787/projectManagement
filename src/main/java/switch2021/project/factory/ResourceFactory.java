package switch2021.project.factory;

import switch2021.project.model.SystemUser.User;
import switch2021.project.model.valueObject.CostPerHour;
import switch2021.project.model.valueObject.PercentageOfAllocation;
import switch2021.project.model.Resource.old.Resource;

import java.time.LocalDate;

public class ResourceFactory implements switch2021.project.factoryInterface.IResourceFactory {

    public Resource createResource(User user, LocalDate startDate, LocalDate endDate, double costPerHour, double percentageOfAllocation) {
        CostPerHour coPeHo = new CostPerHour(costPerHour);
        PercentageOfAllocation percentage = new PercentageOfAllocation(percentageOfAllocation);
        return new Resource(user, startDate, endDate, coPeHo, percentage);
    }
}
