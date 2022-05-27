package switch2021.project.datamodel.assembler;

import org.springframework.stereotype.Component;
import switch2021.project.datamodel.ResourceIDJpa;
import switch2021.project.datamodel.ResourceJpa;
import switch2021.project.model.Resource.ResourceIDReeng;
import switch2021.project.model.Resource.ResourceReeng;
import switch2021.project.model.valueObject.*;

import java.time.LocalDate;

@Component
public class ResourceJpaAssembler {

    public ResourceJpa toData(ResourceReeng resource) {


        SystemUserID userId = resource.getId().getUser();
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

    public ResourceReeng toDomain(ResourceJpa resourceJpaSaved) {

        ResourceIDReeng id = new ResourceIDReeng(resourceJpaSaved.getId().getUser(), resourceJpaSaved.getId().getProject(),
                LocalDate.parse(resourceJpaSaved.getId().getStartDate()));
        LocalDate endDate = LocalDate.parse(resourceJpaSaved.getEndDate());
        PercentageOfAllocation percentageOfAllocation = new PercentageOfAllocation(resourceJpaSaved.getAllocation());
        CostPerHour costPerHour = new CostPerHour(resourceJpaSaved.getCost());
        ProjectRoleReeng projectRole = ProjectRoleReeng.valueOf(resourceJpaSaved.getRole());

        ResourceReeng resourceSaved = new ResourceReeng(id, endDate, percentageOfAllocation, costPerHour, projectRole);

        return resourceSaved;
    }

}
