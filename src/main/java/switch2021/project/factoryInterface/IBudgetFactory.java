package switch2021.project.factoryInterface;

import switch2021.project.model.valueObject.Budget;

public interface IBudgetFactory {
    Budget create (double budget);
}
