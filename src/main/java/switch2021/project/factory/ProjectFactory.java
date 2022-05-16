package switch2021.project.factory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import switch2021.project.dto.ProjectDTO;
import switch2021.project.factoryInterface.*;
import switch2021.project.model.Project.ProjectReeng;
import switch2021.project.model.Project.ProjectStatusEnum;
import switch2021.project.model.valueObject.*;

import java.time.LocalDate;

@Component
public class ProjectFactory implements switch2021.project.factoryInterface.IProjectFactory {

    @Autowired
    private IDescriptionFactory iDescription;
    @Autowired
    private IDescriptionFactory iName;
    @Autowired
    private IBusinessSectorFactory iBusinessSector;
    @Autowired
    private INumberOfSprintsFactory iNumberOfSprints;
    @Autowired
    private ISprintDurationFactory iSprintDuration;
    @Autowired
    private IBudgetFactory iBudget;


    @Override
    public ProjectReeng createProject(ProjectDTO projectDTO, int nextId) {

        ProjectID projectID = new ProjectID(nextId); //TODO ver questão de projectID não receber como parametro nextID

        Description name = iName.createDescription(projectDTO.projectName);
        Description description = iDescription.createDescription(projectDTO.description);
        BusinessSector sector = iBusinessSector.createBusinessSector(projectDTO.businessSector);
        LocalDate date = LocalDate.parse(projectDTO.startDate);
        NumberOfSprints numberOfSprints= iNumberOfSprints.create(Integer.parseInt(projectDTO.numberOfSprints));
        SprintDuration sprintDuration = iSprintDuration.create(Integer.parseInt(projectDTO.sprintDuration));
        Budget budget = iBudget.create(Integer.parseInt(projectDTO.budget));
        if (date.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("Project can´t start before today's date");
        }
        ProjectReeng newProject = new ProjectReeng(name, description, sector, date, numberOfSprints,
                sprintDuration, budget);
        newProject.setProjectCode(projectID);
        newProject.setProjectStatus(ProjectStatusEnum.PLANNED);
        return newProject;


    }

}
