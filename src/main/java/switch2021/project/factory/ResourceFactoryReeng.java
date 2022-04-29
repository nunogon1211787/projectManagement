package switch2021.project.factory;

import switch2021.project.dto.ResourceDTOReeng;
import switch2021.project.factoryInterface.ResourceFactoryInterfaceReeng;
import switch2021.project.model.Resource.ResourceId;
import switch2021.project.model.Resource.ResourceReeng;
import switch2021.project.model.valueObject.*;

import java.time.LocalDate;

public class ResourceFactoryReeng implements ResourceFactoryInterfaceReeng {

    public ResourceReeng createResource(ResourceDTOReeng resourceDTO){

        SystemUserID sysUserId = new SystemUserID(new Email(resourceDTO.systemUserID));
        ProjectID projectID = new ProjectID(resourceDTO.projectId);
        LocalDate startDate = LocalDate.of(resourceDTO.yearStartDate, resourceDTO.monthStartDate, resourceDTO.dayStartDate);
        LocalDate endDate = LocalDate.of(resourceDTO.yearEndDate, resourceDTO.monthEndDate, resourceDTO.dayEndDate);
        ResourceId resourceId = new ResourceId(sysUserId,projectID, startDate);
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

//    public ResourceReeng createResource(ResourceDTOReeng resourceDTO) {
//
////        CostPerHour coPeHo = new CostPerHour(costPerHour);
////        PercentageOfAllocation percentage = new PercentageOfAllocation(percentageOfAllocation);
//        return new ResourceReeng(resourceId);
//    }
}
