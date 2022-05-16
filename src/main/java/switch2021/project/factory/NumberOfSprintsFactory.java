package switch2021.project.factory;

import org.springframework.stereotype.Component;
import switch2021.project.factoryInterface.INumberOfSprintsFactory;
import switch2021.project.model.valueObject.NumberOfSprints;


@Component
public class NumberOfSprintsFactory implements INumberOfSprintsFactory {

        @Override
        public NumberOfSprints create(int numberOfSprints){
            return new NumberOfSprints(numberOfSprints);
        }

    }
