package switch2021.project.factory;

import org.springframework.stereotype.Component;
import switch2021.project.factoryInterface.ICostPerHourFactory;
import switch2021.project.factoryInterface.IPercOfAllocationFactory;
import switch2021.project.model.valueObject.CostPerHour;
import switch2021.project.model.valueObject.PercentageOfAllocation;

@Component
public class PercOfAllocationFactory implements IPercOfAllocationFactory {
    @Override
    public PercentageOfAllocation create (double percOfAllo){
        return new PercentageOfAllocation(percOfAllo);
    }
}
