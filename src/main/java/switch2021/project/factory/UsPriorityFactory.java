package switch2021.project.factory;

import org.springframework.stereotype.Component;
import switch2021.project.factoryInterface.IUsPriorityFactory;
import switch2021.project.model.valueObject.UsPriority;

@Component
public class UsPriorityFactory implements IUsPriorityFactory {
    @Override
    public UsPriority create(int priority){
        return new UsPriority(priority);
    }

}
