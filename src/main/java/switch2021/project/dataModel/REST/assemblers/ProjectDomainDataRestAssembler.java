package switch2021.project.dataModel.REST.assemblers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;
import switch2021.project.dataModel.REST.ProjectRestDTO;
import switch2021.project.entities.aggregates.Project.Project;
import switch2021.project.entities.valueObjects.voFactories.voFactories.*;
import switch2021.project.entities.valueObjects.vos.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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


    public List<Project> toDomain(List<ProjectRestDTO> projectRestDTOS) {
        return projectRestDTOS.stream().map(this::toDomain).collect(Collectors.toList());
    }

    public Project toDomain(ProjectRestDTO projectRestDTO) {

        Description name = descriptionFactory.createDescription(projectRestDTO.getProjectName());
        Description description = descriptionFactory.createDescription(projectRestDTO.getProjectDescription());
        BusinessSector businessSector =
                businessSectorFactory.createBusinessSector(projectRestDTO.getProjectBusinessSector());
        NumberOfSprints numberOfSprints =
                numberOfSprintsFactory.create(Integer.parseInt(projectRestDTO.getProjectNumberOfPlannedSprints()));
        SprintDuration sprintDuration;
        try {

            sprintDuration = sprintDurationFactory.create(Integer.parseInt(projectRestDTO.getProjectSprintDuration()));
        } catch (NullPointerException sprintDurationException) {
            sprintDuration = sprintDurationFactory.create(15); //default spring number of days
        }
        Budget budget = budgetFactory.create(Double.parseDouble(projectRestDTO.getProjectBudget()));
        LocalDate startDate = LocalDate.parse(projectRestDTO.getStartDate(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));

        Project project = new Project(name, description, businessSector, startDate, numberOfSprints, sprintDuration,
                budget);
        project.setProjectCode(new ProjectID("Project_" + LocalDate.now().getYear() + "_" + projectRestDTO.getProjectCode()));
        return project;

    }

}
