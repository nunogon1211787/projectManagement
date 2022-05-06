package switch2021.project.factory;

import org.springframework.stereotype.Service;
import switch2021.project.factoryInterface.IDescriptionFactory;
import switch2021.project.model.valueObject.Description;

@Service
public class DescriptionFactory implements IDescriptionFactory {
    @Override
    public Description createDescription(String description) {
        return new Description(description);
    }
}
