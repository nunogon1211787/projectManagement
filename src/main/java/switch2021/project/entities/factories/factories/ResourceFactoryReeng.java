package switch2021.project.entities.factories.factories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import switch2021.project.dtoModel.dto.CreateResourceDTO;
import switch2021.project.dtoModel.dto.DefineRoleOfResourceDTO;
import switch2021.project.entities.valueObjects.voFactories.voInterfaces.ICostPerHourFactory;
import switch2021.project.entities.valueObjects.voFactories.voInterfaces.IPercOfAllocationFactory;
import switch2021.project.entities.valueObjects.voFactories.voInterfaces.IResourceIDFactory;
import switch2021.project.entities.valueObjects.vos.CostPerHour;
import switch2021.project.entities.valueObjects.vos.PercentageOfAllocation;
import switch2021.project.entities.valueObjects.vos.ResourceID;
import switch2021.project.entities.valueObjects.vos.enums.ProjectRoleReeng;
import switch2021.project.entities.factories.factoryInterfaces.*;
import switch2021.project.entities.aggregates.Resource.Resource;

import java.time.LocalDate;

@Component
public class ResourceFactoryReeng implements IResourceFactoryReeng {

    @Autowired
    private IResourceIDFactory resourceID;
    @Autowired
    private ICostPerHourFactory costPerHour;
    @Autowired
    private IPercOfAllocationFactory percentageOfAllocation;

    public Resource createResource(CreateResourceDTO resourceDTO) {
        ResourceID resourceId = resourceID.create(resourceDTO.systemUserID, resourceDTO.projectId,
                resourceDTO.startDate);
        ProjectRoleReeng projRole;

        if (resourceDTO.projectRole != null) {
            projRole = ProjectRoleReeng.valueOf(resourceDTO.projectRole);
        } else {
            projRole = ProjectRoleReeng.TeamMember;
        }

        CostPerHour coPeHo = costPerHour.create(resourceDTO.costPerHour);
        PercentageOfAllocation percOfAll = percentageOfAllocation.create(resourceDTO.percentageOfAllocation);

        return new Resource(resourceId, LocalDate.parse(resourceDTO.endDate), percOfAll, coPeHo, projRole);
    }

    @Override
    public Resource createResourceByAnotherResource(ResourceID id, DefineRoleOfResourceDTO dto) {
        ResourceID resourceId = resourceID.create(id.getUser().getEmail().getEmailText(), id.getProject().getCode(),
                dto.getStartDate());
        PercentageOfAllocation percOfAll = percentageOfAllocation.create(dto.getPercentageOfAllocation());
        CostPerHour coPeHo = costPerHour.create(dto.getCostPerHour());
        ProjectRoleReeng role = ProjectRoleReeng.valueOf(dto.getRole());

        return new Resource(resourceId, LocalDate.parse(dto.getEndDate()), percOfAll, coPeHo, role);
    }

}
