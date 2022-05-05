package switch2021.project.factory;

import org.springframework.stereotype.Component;
import switch2021.project.factoryInterface.IDescriptionFactory;
import switch2021.project.model.valueObject.Description;


@Component
public class DescriptionFactory implements IDescriptionFactory {

        @Override
        public Description create (String description) {
            return new Description(description);
        }

}
