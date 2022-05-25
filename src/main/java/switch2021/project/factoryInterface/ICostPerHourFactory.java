package switch2021.project.factoryInterface;

import switch2021.project.model.valueObject.CostPerHour;

public interface ICostPerHourFactory {
    CostPerHour create (double costPerHour);
}
