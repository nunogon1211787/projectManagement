package switch2021.project.entities.factories.factoryInterfaces;

import switch2021.project.entities.aggregates.User.User;
import switch2021.project.entities.aggregates.Resource.old.Resource;

import java.time.LocalDate;

public interface IResourceFactory {
    Resource createResource(User user, LocalDate startDate, LocalDate endDate, double costPerHour, double percentageOfAllocation);
}
