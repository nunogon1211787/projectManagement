package switch2021.project.factory;

import org.springframework.stereotype.Component;
import switch2021.project.model.valueObject.BusinessSector;

@Component
public class BusinessSectorFactory implements switch2021.project.factoryInterface.IBusinessSectorFactory {

    @Override
    public BusinessSector createBusinessSector(String description) {

        return new BusinessSector(description);
    }
}
