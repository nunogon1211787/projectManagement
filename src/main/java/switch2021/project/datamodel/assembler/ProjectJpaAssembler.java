package switch2021.project.dataModel.assembler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import switch2021.project.dataModel.jpa.ProjectJpa;
import switch2021.project.entities.valueObjects.vos.*;
import switch2021.project.entities.factories.factories.ProjectFactory;
import switch2021.project.entities.aggregates.Project.Project;
import switch2021.project.entities.valueObjects.vos.enums.ProjectStatusEnum;
import switch2021.project.entities.aggregates.Typology.Typology;

import java.time.LocalDate;

@Service
public class ProjectJpaAssembler {

    @Autowired
    ProjectFactory factory;

    public ProjectJpa toJpaData(Project project) {

        String projectCode = project.getProjectCode();
        String projectName = project.getProjectName().getText();
        String description = project.getDescription().getText();
        String businessSector = project.getBusinessSector().getDescription().getText();
        String startDate = project.getStartDate().toString();
        int numberOfSprints = project.getNumberOfSprints().getNumberOfSprintsVO();
        int sprintDuration = project.getSprintDuration().getSprintDurationDays();
        double budget = project.getBudget().getBudgetVO();

        String endDate = null;
        String typology = null;
        String customer = null;
        String status = null;

        if (!(project.getEndDate() == null)) {
            endDate = project.getEndDate().toString();
        }
        if (!(project.getTypology() == null)) {
            typology = project.getTypology().getId_description().getDescription().getText();
        }
        if (!(project.getCustomer() == null)) {
            customer = project.getCustomer().getCustomerName().getText();
        }
        if (!(project.getProjectStatus() == null)) {
            status = project.getProjectStatus().toString();
        }

        ProjectJpa projectJpa = new ProjectJpa(projectCode, projectName, description, businessSector, startDate,
                numberOfSprints, sprintDuration, budget, endDate, typology, customer, status);

        return projectJpa;

    }

    public Project toDomain(ProjectJpa projectJpa) {
        String projectCode = projectJpa.getProjectCode();
        Description projectName = new Description(projectJpa.getName());
        Description description = new Description(projectJpa.getDescription());
        BusinessSector businessSector = new BusinessSector(projectJpa.getBusinessSector());
        LocalDate startDate = LocalDate.parse(projectJpa.getStartDate());
        NumberOfSprints numberOfSprints = new NumberOfSprints(projectJpa.getNumberOfSprints());
        SprintDuration sprintDuration = new SprintDuration(projectJpa.getSprintDuration());
        Budget budget = new Budget(projectJpa.getBudget());

        LocalDate endDate = null;
        Typology typology = null;
        Customer customer = null;
        ProjectStatusEnum status = null;

        if (!(projectJpa.getEndDate() == null)) {
            endDate = LocalDate.parse(projectJpa.getEndDate());
        }
        if (!(projectJpa.getTypology() == null)) {
            typology = new Typology(new TypologyID(new Description(projectJpa.getTypology())));
        }
        if (!(projectJpa.getCustomer() == null)) {
            customer = new Customer(projectJpa.getCustomer(), "email@email.pt", 123456789);//TODO Change here
        }
        if (!(projectJpa.getStatus() == null)) {
            status = ProjectStatusEnum.valueOf(projectJpa.getStatus());
        }


        Project project = new Project(projectCode, projectName, description, typology, status,
                                      customer, businessSector, numberOfSprints, budget, sprintDuration, startDate, endDate);

        return project;
    }

//    public ProjectReeng toDomain(ProjectJpa projectJpa) {
//        LocalDate startDate = LocalDate.parse(projectJpa.getStartDate());
//        LocalDate endDate = LocalDate.parse(projectJpa.getEndDate());
//
//        ProjectReeng projectReeng = new ProjectReeng(
//                new Description(projectJpa.getName()),
//                new Description(projectJpa.getDescription()),
//                new BusinessSector(projectJpa.getBusinessSector()),
//                startDate,
//                new NumberOfSprints(projectJpa.getNumberOfSprints()),
//                new SprintDuration(projectJpa.getSprintDuration()),
//                new Budget(projectJpa.getBudget()));
//        projectReeng.setProjectCode(projectJpa.getProjectCode());
//        return projectReeng;
//    }


}
