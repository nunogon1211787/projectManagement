package switch2021.project.dataModel.assembler;

import org.springframework.stereotype.Component;
import switch2021.project.dataModel.jpa.ResourceIDJpa;
import switch2021.project.dataModel.jpa.ResourceJpa;
import switch2021.project.entities.valueObjects.vos.ResourceIDReeng;
import switch2021.project.entities.aggregates.Resource.Resource;
import switch2021.project.entities.valueObjects.vos.*;
import switch2021.project.entities.valueObjects.vos.enums.ProjectRoleReeng;

import java.time.LocalDate;

@Component
public class ResourceJpaAssembler {

    public ResourceJpa toData(Resource resource) {


        UserID userId = resource.getId().getUser();
        ProjectID projId = resource.getId().getProject();
        String startDate = resource.getId().getStartDate().toString();

        ResourceIDJpa id = new ResourceIDJpa(userId, projId, startDate);

        double percentageOfAllocation = resource.getAllocation().getPercentage();
        double costPerHour = resource.getCost().getCost();
        String projectRole = resource.getRole().toString();
        String endDate = resource.getEndDate().toString();

        ResourceJpa resourceJpa = new ResourceJpa(id, endDate, percentageOfAllocation, costPerHour, projectRole);

        return resourceJpa;
    }

    public Resource toDomain(ResourceJpa resourceJpaSaved) {

        ResourceIDReeng id = new ResourceIDReeng(resourceJpaSaved.getId().getUser(), resourceJpaSaved.getId().getProject(),
                LocalDate.parse(resourceJpaSaved.getId().getStartDate()));
        LocalDate endDate = LocalDate.parse(resourceJpaSaved.getEndDate());
        PercentageOfAllocation percentageOfAllocation = new PercentageOfAllocation(resourceJpaSaved.getAllocation());
        CostPerHour costPerHour = new CostPerHour(resourceJpaSaved.getCost());
        ProjectRoleReeng projectRole = ProjectRoleReeng.valueOf(resourceJpaSaved.getRole());

        Resource resourceSaved = new Resource(id, endDate, percentageOfAllocation, costPerHour, projectRole);

        return resourceSaved;
    }

}
