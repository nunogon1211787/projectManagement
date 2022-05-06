package switch2021.project.factory;

import org.springframework.stereotype.Component;
import switch2021.project.factoryInterface.INameFactory;
import switch2021.project.model.valueObject.Name;

@Component
public class NameFactory implements INameFactory {
    @Override
    public Name createName(String name) {
        return new Name(name);
    }
}
