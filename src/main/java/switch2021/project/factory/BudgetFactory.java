package switch2021.project.factory;

import org.springframework.stereotype.Component;
import switch2021.project.factoryInterface.IBudgetFactory;
import switch2021.project.model.valueObject.Budget;

@Component
public class BudgetFactory implements IBudgetFactory {
    @Override
    public Budget create (double budget){
        return new Budget(budget);
    }
}
