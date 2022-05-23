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

        return new ProjectJpa(projectReeng.getProjectName(),
                projectReeng.getDescription(),
                projectReeng.getBusinessSector(),
                projectReeng.getStartDate(),
                projectReeng.getNumberOfSprints(),
                projectReeng.getSprintDuration(),
                projectReeng.getBudget()
//                projectReeng.getEndDate()
//                projectReeng.getTypology(),
//                projectReeng.getCustomer()
        );
    }

    public ProjectReeng toDomain(ProjectJpa projectJpa) {
        LocalDate startDate = LocalDate.parse(projectJpa.getStartDate());
//        LocalDate endDate = LocalDate.parse(projectJpa.getEndDate());

        ProjectReeng projectReeng = new ProjectReeng(
                new Description(projectJpa.getName()),
                new Description(projectJpa.getDescription()),
                new BusinessSector(projectJpa.getBusinessSector()),
                startDate,
                new NumberOfSprints(projectJpa.getNumberOfSprints()),
                new SprintDuration(projectJpa.getSprintDuration()),
                new Budget(projectJpa.getBudget()));
//                endDate);
//                new Typology(projectJpa.getTypology()),
//                new Customer(projectJpa.getCustomer());
                projectReeng.setProjectCode(projectJpa.getProjectCode());
        return projectReeng;
    }

}
