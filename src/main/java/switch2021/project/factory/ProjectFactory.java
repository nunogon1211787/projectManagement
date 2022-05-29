package switch2021.project.factory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import switch2021.project.dto.ProjectDTO;
import switch2021.project.factoryInterface.*;
import switch2021.project.model.Project.Project;
import switch2021.project.model.Typology.Typology;
import switch2021.project.model.valueObject.*;

import java.time.LocalDate;

@Component
public class ProjectFactory implements switch2021.project.factoryInterface.IProjectFactory {

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

        if (date.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("Project can´t start before today's date");
        }

        Project project = new Project(name, description, businessSector, date, numberOfSprints,
                                      sprintDuration, budget);

        if (projectDTO.getCustomer() != null) {
            Customer cust = new Customer();
            cust.setCustomerName(new Description(projectDTO.getCustomer())); //TODO Setter em VO (Customer) Remover!!
            project.setCustomer(cust);
        }

        if (projectDTO.getTypology() != null)
            project.setTypology(new Typology(new TypologyID(new Description(projectDTO.getTypology()))));
        if (projectDTO.getEndDate() != null)
            project.setEndDate(LocalDate.parse(projectDTO.getEndDate()));

        return project;
    }

}
