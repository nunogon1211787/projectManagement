package switch2021.project.entities.factories.factories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import switch2021.project.dtoModel.dto.CreateResourceDTO;
import switch2021.project.entities.valueObjects.voFactories.voInterfaces.ICostPerHourFactory;
import switch2021.project.entities.valueObjects.voFactories.voInterfaces.IPercOfAllocationFactory;
import switch2021.project.entities.valueObjects.voFactories.voInterfaces.IResouceIDFactory;
import switch2021.project.entities.valueObjects.vos.CostPerHour;
import switch2021.project.entities.valueObjects.vos.PercentageOfAllocation;
import switch2021.project.entities.valueObjects.vos.enums.ProjectRoleReeng;
import switch2021.project.entities.factories.factoryInterfaces.*;
import switch2021.project.entities.valueObjects.vos.ResourceIDReeng;
import switch2021.project.entities.aggregates.Resource.ResourceReeng;

import java.time.LocalDate;

@Component
public class ResourceFactoryReeng implements IResourceFactoryReeng {

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

        return new ResourceReeng(resourceId, LocalDate.parse(resourceDTO.endDate), percOfAll, coPeHo, projRole);
    }


}
