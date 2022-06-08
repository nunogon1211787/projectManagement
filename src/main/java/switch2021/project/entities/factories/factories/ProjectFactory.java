package switch2021.project.entities.factories.factories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import switch2021.project.dtoModel.dto.ProjectDTO;
import switch2021.project.entities.valueObjects.voFactories.voInterfaces.*;
import switch2021.project.entities.valueObjects.vos.*;
import switch2021.project.entities.aggregates.Project.Project;
import switch2021.project.entities.aggregates.Typology.Typology;

import java.time.LocalDate;

@Component
public class ProjectFactory implements switch2021.project.entities.factories.factoryInterfaces.IProjectFactory {

    @Autowired
    private IDescriptionFactory nameF;

    @Autowired
    private IDescriptionFactory descF;

    @Autowired
    private IBusinessSectorFactory busSecF;

    @Autowired
    private INumberOfSprintsFactory numberSprintsF;

    @Autowired
    private ISprintDurationFactory sprintDurationF;

    @Autowired
    private IBudgetFactory budgetF;


    @Override
    public Project createProject(ProjectDTO projectDTO) {

        Description name = nameF.createDescription(projectDTO.projectName);
        Description description = descF.createDescription(projectDTO.description);
        BusinessSector businessSector = busSecF.createBusinessSector(projectDTO.businessSector);
        LocalDate date = LocalDate.parse(projectDTO.startDate);
        NumberOfSprints numberOfSprints = numberSprintsF.create(Integer.parseInt(projectDTO.numberOfSprints));
        SprintDuration sprintDuration = sprintDurationF.create(Integer.parseInt(projectDTO.sprintDuration));
        Budget budget = budgetF.create(Integer.parseInt(projectDTO.budget));

        Project project = new Project(name, description, businessSector, date, numberOfSprints,
                                      sprintDuration, budget);

        project.setTypologyId((new TypologyID(new Description(projectDTO.getTypology()))));

        project.setCustomer((new Customer(projectDTO.getCustomer())));

        if (projectDTO.getEndDate() != null)
            project.setEndDate(LocalDate.parse(projectDTO.getEndDate()));

        return project;
    }

}
