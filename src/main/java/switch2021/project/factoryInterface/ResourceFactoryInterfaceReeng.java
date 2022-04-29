package switch2021.project.factoryInterface;

import org.springframework.stereotype.Component;
import switch2021.project.dto.ResourceDTOReeng;
import switch2021.project.model.Resource.ResourceReeng;
import switch2021.project.model.Resource.old.Resource;
import switch2021.project.model.SystemUser.SystemUser;
import switch2021.project.model.valueObject.CostPerHour;
import switch2021.project.model.valueObject.PercentageOfAllocation;
import switch2021.project.model.valueObject.ProjectID;
import switch2021.project.model.valueObject.SystemUserId;

import java.time.LocalDate;

@Component
public interface ResourceFactoryInterfaceReeng {

    ResourceReeng createResource(ResourceDTOReeng resourceDTO);

}
