package switch2021.project.factory;

import switch2021.project.immutable.Description;
import switch2021.project.model.BusinessSector;
import switch2021.project.model.ProjectTeam;

public interface BusinessSectorFactoryInterface {

     public BusinessSector createBusinessSector(String description);

}
