package switch2021.project.dataModel.REST.assemblers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import switch2021.project.dataModel.REST.ProjectRestDTO;
import switch2021.project.entities.aggregates.Project.Project;
import switch2021.project.entities.aggregates.UserProfile.UserProfile;
import switch2021.project.entities.valueObjects.voFactories.voFactories.*;
import switch2021.project.entities.valueObjects.vos.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProjectDomainDataRestAssembler {


    @Autowired
    DescriptionFactory descriptionFactory;
    @Autowired
    BusinessSectorFactory businessSectorFactory;
    @Autowired
    NumberOfSprintsFactory numberOfSprintsFactory;
    @Autowired
    SprintDurationFactory sprintDurationFactory;
    @Autowired
    BudgetFactory budgetFactory;


    public List<Project> toDomain(List<ProjectRestDTO> projectRestDTOS){
        return projectRestDTOS.stream().map(this::toDomain).collect(Collectors.toList());
    }

    public Project toDomain(ProjectRestDTO projectRestDTO) {

        Description name = descriptionFactory.createDescription(projectRestDTO.projectName);
        Description description = descriptionFactory.createDescription(projectRestDTO.description);
        BusinessSector businessSector = businessSectorFactory.createBusinessSector(projectRestDTO.businessSector);
        NumberOfSprints numberOfSprints =
                numberOfSprintsFactory.create(Integer.parseInt(projectRestDTO.numberOfSprints));
        SprintDuration sprintDuration = sprintDurationFactory.create(Integer.parseInt(projectRestDTO.sprintDuration));
        Budget budget = budgetFactory.create(Double.parseDouble(projectRestDTO.budget));
        LocalDate startDate = LocalDate.parse(projectRestDTO.getStartDate());

        return new Project(name, description, businessSector, startDate, numberOfSprints, sprintDuration, budget);

    }

}
