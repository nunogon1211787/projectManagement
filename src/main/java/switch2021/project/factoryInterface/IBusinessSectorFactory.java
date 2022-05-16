package switch2021.project.factoryInterface;

import switch2021.project.model.valueObject.BusinessSector;

public interface IBusinessSectorFactory {
     BusinessSector createBusinessSector(String description);

}
