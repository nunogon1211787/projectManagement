package switch2021.project.datamodel.assembler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import switch2021.project.datamodel.ProjectJpa;
import switch2021.project.factory.ProjectFactory;
import switch2021.project.model.Project.ProjectReeng;
import switch2021.project.model.Project.ProjectStatusEnum;
import switch2021.project.model.Typology.Typology;
import switch2021.project.model.valueObject.*;

import java.time.LocalDate;

@Service
public class ProjectJpaAssembler {

    @Autowired
    ProjectFactory factory;

    public ProjectJpa toJpaData(ProjectReeng projectReeng) {

        String projectCode = projectReeng.getProjectCode();
        String projectName = projectReeng.getProjectName().getText();
        String description = projectReeng.getDescription().getText();
        String businessSector = projectReeng.getBusinessSector().getDescription().getText();
        String startDate = projectReeng.getStartDate().toString();
        int numberOfSprints = projectReeng.getNumberOfSprints().getNumberOfSprintsVO();
        int sprintDuration = projectReeng.getSprintDuration().getSprintDurationDays();
        double budget = projectReeng.getBudget().getBudgetVO();

        String endDate = null;
        String typology = null;
        String customer = null;
        String status = null;

        if (!(projectReeng.getEndDate() == null)) {
            endDate = projectReeng.getEndDate().toString();
        }
        if (!(projectReeng.getTypology() == null)) {
            typology = projectReeng.getTypology().getId_description().getDescription().getText();
        }
        if (!(projectReeng.getCustomer() == null)) {
            customer = projectReeng.getCustomer().getCustomerName().getText();
        }
        if (!(projectReeng.getProjectStatus() == null)) {
            status = projectReeng.getProjectStatus().toString();
        }

        ProjectJpa projectJpa = new ProjectJpa(projectCode, projectName, description, businessSector, startDate,
                numberOfSprints, sprintDuration, budget, endDate, typology, customer, status);

        return projectJpa;

    }

    public ProjectReeng toDomain(ProjectJpa projectJpa) {
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


        ProjectReeng projectReeng = new ProjectReeng(projectCode, projectName, description, typology, status,
                customer, businessSector, numberOfSprints, budget, sprintDuration, startDate, endDate);

        return projectReeng;
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
