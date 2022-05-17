package switch2021.project.factoryInterface;

import switch2021.project.model.SystemUser.User;
import switch2021.project.model.Resource.old.Resource;

import java.time.LocalDate;

public interface IResourceFactory {
    Resource createResource(User user, LocalDate startDate, LocalDate endDate, double costPerHour, double percentageOfAllocation);
}
