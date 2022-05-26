package switch2021.project.factory;

import org.springframework.stereotype.Component;
import switch2021.project.factoryInterface.IBudgetFactory;
import switch2021.project.factoryInterface.ICostPerHourFactory;
import switch2021.project.model.valueObject.Budget;
import switch2021.project.model.valueObject.CostPerHour;

@Component
public class CostPerHourFactory implements ICostPerHourFactory {

    @Override
    public CostPerHour create (double costPerHour){
        return new CostPerHour(costPerHour);
    }
}
