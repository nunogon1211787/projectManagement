package switch2021.project.factory;

import org.springframework.stereotype.Component;
import switch2021.project.factoryInterface.IValueObjectsFactory;
import switch2021.project.model.valueObject.Description;


@Component
public class DescriptionFactory implements IValueObjectsFactory<Description> {

    @Override
    public Description create(Object o) { return new Description(o.toString()); }

}

