package switch2021.project.factory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import switch2021.project.dto.CreateResourceDTO;
import switch2021.project.factoryInterface.*;
import switch2021.project.model.Resource.ResourceIDReeng;
import switch2021.project.model.Resource.ResourceReeng;
import switch2021.project.model.valueObject.*;

import java.time.LocalDate;

@Component
public class ResourceFactoryReeng implements ResourceFactoryInterfaceReeng {

    @Autowired
    private IResouceIDFactory resourceID;
    @Autowired
    private ICostPerHourFactory costPerHour;
    @Autowired
    private IPercOfAllocationFactory percentageOfAllocation;

    public ResourceReeng createResource(CreateResourceDTO resourceDTO){

        ResourceIDReeng resourceId = resourceID.create(resourceDTO.systemUserID, resourceDTO.projectId, resourceDTO.startDate);
        ProjectRoleReeng projRole = ProjectRoleReeng.valueOf(resourceDTO.projectRole);
        CostPerHour coPeHo = costPerHour.create(resourceDTO.costPerHour);
        PercentageOfAllocation percOfAll = percentageOfAllocation.create(resourceDTO.percentageOfAllocation);

        return new ResourceReeng(resourceId, percOfAll, coPeHo, projRole, LocalDate.parse(resourceDTO.endDate));
    }


}
