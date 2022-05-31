package switch2021.project.entities.factories.factories;

import switch2021.project.entities.aggregates.User.User;
import switch2021.project.entities.valueObjects.vos.CostPerHour;
import switch2021.project.entities.valueObjects.vos.PercentageOfAllocation;
import switch2021.project.entities.aggregates.Resource.old.Resource;

import java.time.LocalDate;

public class ResourceFactory implements switch2021.project.entities.factories.factoryInterfaces.IResourceFactory {

    public Resource createResource(User user, LocalDate startDate, LocalDate endDate, double costPerHour, double percentageOfAllocation) {
        CostPerHour coPeHo = new CostPerHour(costPerHour);
        PercentageOfAllocation percentage = new PercentageOfAllocation(percentageOfAllocation);
        return new Resource(user, startDate, endDate, coPeHo, percentage);
    }
}
