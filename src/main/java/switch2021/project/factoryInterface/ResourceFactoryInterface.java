package switch2021.project.factoryInterface;

import switch2021.project.model.Resource;
import switch2021.project.model.SystemUser;

import java.time.LocalDate;

public interface ResourceFactoryInterface {
    Resource createResource(SystemUser user, LocalDate startDate, LocalDate endDate, double costPerHour, double percentageOfAllocation);
}
