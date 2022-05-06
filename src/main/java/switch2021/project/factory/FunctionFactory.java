package switch2021.project.factory;

import org.springframework.stereotype.Service;
import switch2021.project.factoryInterface.IFunctionFactory;
import switch2021.project.model.valueObject.Function;

@Service
public class FunctionFactory implements IFunctionFactory {
    @Override
    public Function createFunction(String function) {
        return new Function(function);
    }
}
