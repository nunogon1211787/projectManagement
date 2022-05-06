package switch2021.project.factory;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import switch2021.project.factoryInterface.IValueObjectsFactory;
import switch2021.project.model.valueObject.Function;

@Component
public class FunctionFactory implements IValueObjectsFactory<Function> {
    @Override
    public Function create(Object o) {
        return new Function(o.toString());
    }
}
