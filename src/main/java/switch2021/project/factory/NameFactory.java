package switch2021.project.factory;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import switch2021.project.factoryInterface.IValueObjectsFactory;
import switch2021.project.model.valueObject.Name;

@Component
public class NameFactory implements IValueObjectsFactory<Name> {
    @Override
    public Name create(Object o) {
        return new Name(o.toString());
    }
}
