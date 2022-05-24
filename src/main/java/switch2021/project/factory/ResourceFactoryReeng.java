package switch2021.project.factory;

import org.springframework.stereotype.Component;
import switch2021.project.dto.CreateResourceDTO;
import switch2021.project.factoryInterface.ResourceFactoryInterfaceReeng;
import switch2021.project.model.Resource.ResourceIDReeng;
import switch2021.project.model.Resource.ResourceReeng;
import switch2021.project.model.valueObject.*;

import java.time.LocalDate;

@Component
public class ResourceFactoryReeng implements ResourceFactoryInterfaceReeng {

    public ResourceReeng createResource(CreateResourceDTO resourceDTO){

        SystemUserID sysUserId = new SystemUserID(new Email(resourceDTO.systemUserID));
        ProjectID projectID = new ProjectID(resourceDTO.projectId);
        ResourceIDReeng resourceId = new ResourceIDReeng(sysUserId,projectID, LocalDate.parse(resourceDTO.startDate));
        ProjectRoleReeng projRole = ProjectRoleReeng.valueOf(resourceDTO.projectRole);
        CostPerHour coPeHo = new CostPerHour(resourceDTO.costPerHour);
        PercentageOfAllocation percOfAll = new PercentageOfAllocation(resourceDTO.percentageOfAllocation);

        return new ResourceReeng(resourceId, percOfAll, coPeHo, projRole, LocalDate.parse(resourceDTO.endDate));
    }


}
