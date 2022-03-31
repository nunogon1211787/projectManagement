package switch2021.project.factory;

import switch2021.project.factoryInterface.BusinessSectorFactoryInterface;
import switch2021.project.model.valueObject.BusinessSector;

public class BusinessSectorFactory implements BusinessSectorFactoryInterface {
    @Override
    public BusinessSector createBusinessSector(String description) {
        return new BusinessSector(description);
    }
}
