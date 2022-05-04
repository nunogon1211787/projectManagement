package switch2021.project.factory;

import org.springframework.stereotype.Component;
import switch2021.project.dto.ResourceDTOReeng;
import switch2021.project.factoryInterface.ResourceFactoryInterfaceReeng;
import switch2021.project.model.Resource.ResourceIDReeng;
import switch2021.project.model.Resource.ResourceReeng;
import switch2021.project.model.valueObject.*;

import java.time.LocalDate;

@Component
public class ResourceFactoryReeng implements ResourceFactoryInterfaceReeng {

    public ResourceReeng createResource(ResourceDTOReeng resourceDTO){

        SystemUserID sysUserId = new SystemUserID(new Email(resourceDTO.systemUserID));
        ProjectID projectID = new ProjectID(resourceDTO.projectId);
        LocalDate startDate = LocalDate.of(resourceDTO.yearStartDate, resourceDTO.monthStartDate, resourceDTO.dayStartDate);
        LocalDate endDate = LocalDate.of(resourceDTO.yearEndDate, resourceDTO.monthEndDate, resourceDTO.dayEndDate);
        ResourceIDReeng resourceId = new ResourceIDReeng(sysUserId,projectID, startDate);
        ProjectRoleReeng projRole = ProjectRoleReeng.valueOf(resourceDTO.projectRole);
        CostPerHour coPeHo = new CostPerHour(resourceDTO.costPerHour);
        PercentageOfAllocation percOfAll = new PercentageOfAllocation(resourceDTO.percentageOfAllocation);

        ResourceReeng newResource = new ResourceReeng(resourceId);
        newResource.setEndDate(endDate);
        newResource.setRole(projRole);
        newResource.setCost(coPeHo);
        newResource.setAllocation(percOfAll);

        return newResource;
    }


}
