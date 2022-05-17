package switch2021.project.datamodel.assembler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import switch2021.project.datamodel.ProjectJpa;
import switch2021.project.factory.ProjectFactory;
import switch2021.project.model.Project.ProjectReeng;
import switch2021.project.model.valueObject.*;
import java.time.LocalDate;

@Service
public class ProjectJpaAssembler {

    @Autowired
    ProjectFactory factory;

    public ProjectJpa toJpaData(ProjectReeng projectReeng) {

        return new ProjectJpa(projectReeng.getProjectCode(),
                              projectReeng.getProjectName(),
                              projectReeng.getDescription(),
                              projectReeng.getBusinessSector(),
                              projectReeng.getStartDate(),
                              projectReeng.getSprintDuration(),
                              projectReeng.getNumberOfSprints(),
                              projectReeng.getBudget());
    }

    public ProjectReeng toDomain(ProjectJpa projectJpa) {
        LocalDate date = LocalDate.parse(projectJpa.getStartDate());

        ProjectReeng projects = new ProjectReeng(new Description(projectJpa.getName()),
                                new Description(projectJpa.getDescription()),
                                new BusinessSector(projectJpa.getBusinessSector()),
                                date,
                                new NumberOfSprints(projectJpa.getNumberOfSprints()),
                                new SprintDuration(projectJpa.getSprintDuration()),
                                new Budget(projectJpa.getBudget()));

        projects.setProjectCode(new ProjectID(projectJpa.getName()));

        return projects;
    }

}
