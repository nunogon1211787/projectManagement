package switch2021.project.factory;

import org.springframework.stereotype.Component;
import switch2021.project.dto.ProjectDTO;
import switch2021.project.factoryInterface.ProjectFactoryInterface;
import switch2021.project.model.Project.ProjectReeng;
import switch2021.project.model.Project.ProjectStatusEnum;
import switch2021.project.model.valueObject.*;

import java.time.LocalDate;

@Component
public class ProjectFactory implements ProjectFactoryInterface {

    @Override
    public ProjectReeng createProject(ProjectDTO projectDTO, int nextId) {

        ProjectID projectID = new ProjectID(nextId);
        Description name = new Description(projectDTO.projectName);
        Description description = new Description(projectDTO.description);
        BusinessSector businessSector = new BusinessSector(projectDTO.businessSector);
        LocalDate date = LocalDate.parse(projectDTO.startDate);
        NumberOfSprints numberOfSprints = new NumberOfSprints(Integer.parseInt(projectDTO.numberOfSprints));
        SprintDuration sprintDuration = new SprintDuration(Integer.parseInt(projectDTO.sprintDuration));
        Budget budget = new Budget(Integer.parseInt(projectDTO.budget));

        if (date.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("Project can´t start before today's date");
        }

        ProjectReeng newProject = new ProjectReeng(name,description,businessSector,date,numberOfSprints,
                                                   sprintDuration,budget);

        newProject.setProjectCode(projectID);
        newProject.setProjectStatus(ProjectStatusEnum.PLANNED);

        return newProject;
    }

}
