package switch2021.project.dataModel.jpa.assembler;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import switch2021.project.dataModel.JPA.ProjectJpa;
import switch2021.project.dataModel.JPA.assembler.ProjectJpaAssembler;
import switch2021.project.entities.aggregates.Project.Project;
import switch2021.project.entities.factories.factories.ProjectFactory;
import switch2021.project.entities.valueObjects.vos.*;
import switch2021.project.entities.valueObjects.vos.enums.ProjectStatusEnum;

import java.time.LocalDate;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class ProjectJpaAssemblerTest {


    @Mock
    ProjectFactory projFac;


    @InjectMocks
    ProjectJpaAssembler projJpaAssembler;

    @Test
    void toJpaDataAllData() {
        //Arrange
        Project proj = mock(Project.class);
        ProjectID projID = mock(ProjectID.class);
        Description desc = mock(Description.class);
        BusinessSector busSec = mock(BusinessSector.class);
        NumberOfSprints numbSpr = mock(NumberOfSprints.class);
        SprintDuration sprDur = mock(SprintDuration.class);
        Budget bud = mock(Budget.class);
        TypologyID typoId = mock(TypologyID.class);
        Customer cust = mock(Customer.class);
        ProjectStatusEnum status = ProjectStatusEnum.PLANNED;

        String projName = "x";
        int numberOfSprints = 1;
        int sprintDuration = 7;
        double budget = 100;
        LocalDate endDate = LocalDate.now();

        when(proj.getProjectCode()).thenReturn(projID);
        when(proj.getProjectName()).thenReturn(desc);
        when(proj.getDescription()).thenReturn(desc);
        when(proj.getBusinessSector()).thenReturn(busSec);
        when(busSec.getDescription()).thenReturn(desc);
        when(proj.getTypologyId()).thenReturn(typoId);
        when(typoId.getDescription()).thenReturn(desc);
        when(proj.getCustomer()).thenReturn(cust);
        when(cust.getCustomerName()).thenReturn(desc);
        when(desc.getText()).thenReturn(projName);
        when(proj.getStartDate()).thenReturn(endDate);
        when(proj.getNumberOfSprints()).thenReturn(numbSpr);
        when(numbSpr.getNumberOfSprintsVO()).thenReturn(numberOfSprints);
        when(proj.getSprintDuration()).thenReturn(sprDur);
        when(sprDur.getSprintDurationDays()).thenReturn(sprintDuration);
        when(proj.getBudget()).thenReturn(bud);
        when(bud.getBudgetVO()).thenReturn(budget);
        when(proj.getEndDate()).thenReturn(endDate);
        when(proj.getProjectStatus()).thenReturn(status);

        //Act
        ProjectJpa result = projJpaAssembler.toJpaData(proj);
        ProjectJpa expected = new ProjectJpa(projID, projName, "x", "x", endDate.toString(),
                numberOfSprints, sprintDuration, budget, endDate.toString(), "x", "x", status.toString());

        //Assert
        assertEquals(result, expected);
    }

    @Test
    void toJpaDataTypoNull() {
        //Arrange
        Project proj = mock(Project.class);
        ProjectID projID = mock(ProjectID.class);
        Description desc = mock(Description.class);
        BusinessSector busSec = mock(BusinessSector.class);
        NumberOfSprints numbSpr = mock(NumberOfSprints.class);
        SprintDuration sprDur = mock(SprintDuration.class);
        Budget bud = mock(Budget.class);
        TypologyID typoId = mock(TypologyID.class);
        Customer cust = mock(Customer.class);
        ProjectStatusEnum status = ProjectStatusEnum.PLANNED;

        String projName = "x";
        int numberOfSprints = 1;
        int sprintDuration = 7;
        double budget = 100;
        LocalDate endDate = LocalDate.now();

        when(proj.getProjectCode()).thenReturn(projID);
        when(proj.getProjectName()).thenReturn(desc);
        when(proj.getDescription()).thenReturn(desc);
        when(proj.getBusinessSector()).thenReturn(busSec);
        when(busSec.getDescription()).thenReturn(desc);
        when(proj.getTypologyId()).thenReturn(null);
//        when(typoId.getDescription()).thenReturn(desc);
        when(proj.getCustomer()).thenReturn(cust);
        when(cust.getCustomerName()).thenReturn(desc);
        when(desc.getText()).thenReturn(projName);
        when(proj.getStartDate()).thenReturn(endDate);
        when(proj.getNumberOfSprints()).thenReturn(numbSpr);
        when(numbSpr.getNumberOfSprintsVO()).thenReturn(numberOfSprints);
        when(proj.getSprintDuration()).thenReturn(sprDur);
        when(sprDur.getSprintDurationDays()).thenReturn(sprintDuration);
        when(proj.getBudget()).thenReturn(bud);
        when(bud.getBudgetVO()).thenReturn(budget);
        when(proj.getEndDate()).thenReturn(endDate);
        when(proj.getProjectStatus()).thenReturn(status);

        //Act
        ProjectJpa result = projJpaAssembler.toJpaData(proj);
        ProjectJpa expected = new ProjectJpa(projID, projName, "x", "x", endDate.toString(),
                numberOfSprints, sprintDuration, budget, endDate.toString(), null, "x", status.toString());

        //Assert
        assertEquals(result, expected);
    }

    @Test
    void toJpaDataCustomerNull() {
        //Arrange
        Project proj = mock(Project.class);
        ProjectID projID = mock(ProjectID.class);
        Description desc = mock(Description.class);
        BusinessSector busSec = mock(BusinessSector.class);
        NumberOfSprints numbSpr = mock(NumberOfSprints.class);
        SprintDuration sprDur = mock(SprintDuration.class);
        Budget bud = mock(Budget.class);
        TypologyID typoId = mock(TypologyID.class);
        Customer cust = mock(Customer.class);
        ProjectStatusEnum status = ProjectStatusEnum.PLANNED;

        String projName = "x";
        int numberOfSprints = 1;
        int sprintDuration = 7;
        double budget = 100;
        LocalDate endDate = LocalDate.now();

        when(proj.getProjectCode()).thenReturn(projID);
        when(proj.getProjectName()).thenReturn(desc);
        when(proj.getDescription()).thenReturn(desc);
        when(proj.getBusinessSector()).thenReturn(busSec);
        when(busSec.getDescription()).thenReturn(desc);
        when(proj.getTypologyId()).thenReturn(typoId);
        when(typoId.getDescription()).thenReturn(desc);
        when(proj.getCustomer()).thenReturn(null);
//        when(cust.getCustomerName()).thenReturn(desc);
        when(desc.getText()).thenReturn(projName);
        when(proj.getStartDate()).thenReturn(endDate);
        when(proj.getNumberOfSprints()).thenReturn(numbSpr);
        when(numbSpr.getNumberOfSprintsVO()).thenReturn(numberOfSprints);
        when(proj.getSprintDuration()).thenReturn(sprDur);
        when(sprDur.getSprintDurationDays()).thenReturn(sprintDuration);
        when(proj.getBudget()).thenReturn(bud);
        when(bud.getBudgetVO()).thenReturn(budget);
        when(proj.getEndDate()).thenReturn(endDate);
        when(proj.getProjectStatus()).thenReturn(status);

        //Act
        ProjectJpa result = projJpaAssembler.toJpaData(proj);
        ProjectJpa expected = new ProjectJpa(projID, projName, "x", "x", endDate.toString(),
                numberOfSprints, sprintDuration, budget, endDate.toString(), "x", null, status.toString());

        //Assert
        assertEquals(result, expected);
    }

    @Test
    void toJpaDataEndDateNull() {
        //Arrange
        Project proj = mock(Project.class);
        ProjectID projID = mock(ProjectID.class);
        Description desc = mock(Description.class);
        BusinessSector busSec = mock(BusinessSector.class);
        NumberOfSprints numbSpr = mock(NumberOfSprints.class);
        SprintDuration sprDur = mock(SprintDuration.class);
        Budget bud = mock(Budget.class);
        TypologyID typoId = mock(TypologyID.class);
        Customer cust = mock(Customer.class);
        ProjectStatusEnum status = ProjectStatusEnum.PLANNED;

        String projName = "x";
        int numberOfSprints = 1;
        int sprintDuration = 7;
        double budget = 100;
        LocalDate endDate = LocalDate.now();

        when(proj.getProjectCode()).thenReturn(projID);
        when(proj.getProjectName()).thenReturn(desc);
        when(proj.getDescription()).thenReturn(desc);
        when(proj.getBusinessSector()).thenReturn(busSec);
        when(busSec.getDescription()).thenReturn(desc);
        when(proj.getTypologyId()).thenReturn(typoId);
        when(typoId.getDescription()).thenReturn(desc);
        when(proj.getCustomer()).thenReturn(cust);
        when(cust.getCustomerName()).thenReturn(desc);
        when(desc.getText()).thenReturn(projName);
        when(proj.getStartDate()).thenReturn(endDate);
        when(proj.getNumberOfSprints()).thenReturn(numbSpr);
        when(numbSpr.getNumberOfSprintsVO()).thenReturn(numberOfSprints);
        when(proj.getSprintDuration()).thenReturn(sprDur);
        when(sprDur.getSprintDurationDays()).thenReturn(sprintDuration);
        when(proj.getBudget()).thenReturn(bud);
        when(bud.getBudgetVO()).thenReturn(budget);
        when(proj.getEndDate()).thenReturn(null);
        when(proj.getProjectStatus()).thenReturn(status);

        //Act
        ProjectJpa result = projJpaAssembler.toJpaData(proj);
        ProjectJpa expected = new ProjectJpa(projID, projName, "x", "x", endDate.toString(),
                numberOfSprints, sprintDuration, budget, null, "x", "x", status.toString());

        //Assert
        assertEquals(result, expected);
    }

    @Test
    void toJpaDataStatusNull() {
        //Arrange
        Project proj = mock(Project.class);
        ProjectID projID = mock(ProjectID.class);
        Description desc = mock(Description.class);
        BusinessSector busSec = mock(BusinessSector.class);
        NumberOfSprints numbSpr = mock(NumberOfSprints.class);
        SprintDuration sprDur = mock(SprintDuration.class);
        Budget bud = mock(Budget.class);
        TypologyID typoId = mock(TypologyID.class);
        Customer cust = mock(Customer.class);
        ProjectStatusEnum status = null;

        String projName = "x";
        int numberOfSprints = 1;
        int sprintDuration = 7;
        double budget = 100;
        LocalDate endDate = LocalDate.now();

        when(proj.getProjectCode()).thenReturn(projID);
        when(proj.getProjectName()).thenReturn(desc);
        when(proj.getDescription()).thenReturn(desc);
        when(proj.getBusinessSector()).thenReturn(busSec);
        when(busSec.getDescription()).thenReturn(desc);
        when(proj.getTypologyId()).thenReturn(typoId);
        when(typoId.getDescription()).thenReturn(desc);
        when(proj.getCustomer()).thenReturn(cust);
        when(cust.getCustomerName()).thenReturn(desc);
        when(desc.getText()).thenReturn(projName);
        when(proj.getStartDate()).thenReturn(endDate);
        when(proj.getNumberOfSprints()).thenReturn(numbSpr);
        when(numbSpr.getNumberOfSprintsVO()).thenReturn(numberOfSprints);
        when(proj.getSprintDuration()).thenReturn(sprDur);
        when(sprDur.getSprintDurationDays()).thenReturn(sprintDuration);
        when(proj.getBudget()).thenReturn(bud);
        when(bud.getBudgetVO()).thenReturn(budget);
        when(proj.getEndDate()).thenReturn(endDate);
        when(proj.getProjectStatus()).thenReturn(null);

        //Act
        ProjectJpa result = projJpaAssembler.toJpaData(proj);
        ProjectJpa expected = new ProjectJpa(projID, projName, "x", "x", endDate.toString(),
                numberOfSprints, sprintDuration, budget, endDate.toString(), "x", "x", null);

        //Assert
        assertEquals(result, expected);
    }


    @Test
    void toDomain() {
        ProjectJpa projJpa = mock(ProjectJpa.class);
        ProjectID projId = mock(ProjectID.class);
        Description desc = mock(Description.class);
        TypologyID typoId = mock(TypologyID.class);
        ProjectStatusEnum status = ProjectStatusEnum.PLANNED;
        Customer cust = mock(Customer.class);
        BusinessSector busSec = mock(BusinessSector.class);
        NumberOfSprints numbSpr = mock(NumberOfSprints.class);
        SprintDuration sprDur = mock(SprintDuration.class);
        Budget bud = mock(Budget.class);
        LocalDate startDate = LocalDate.of(2022,1,1);
        LocalDate endDate = LocalDate.of(2022,12,12);

        String ex = "x";
        int numberOfSprints = 1;
        int sprintDuration = 7;
        double budget = 100;

        when(projJpa.getProjectCode()).thenReturn(projId);
        when(projJpa.getName()).thenReturn(ex);
        when(projJpa.getDescription()).thenReturn(ex);
        when(projJpa.getBusinessSector()).thenReturn(ex);
        when(projJpa.getStartDate()).thenReturn(startDate.toString());
        when(projJpa.getNumberOfSprints()).thenReturn(numberOfSprints);
        when(projJpa.getSprintDuration()).thenReturn(sprintDuration);
        when(projJpa.getBudget()).thenReturn(budget);
        when(projJpa.getEndDate()).thenReturn(endDate.toString());
        when(projJpa.getTypology()).thenReturn(ex);
        when(projJpa.getCustomer()).thenReturn(ex);
        when(projJpa.getStatus()).thenReturn(status.toString());

        //Act
        Project result = projJpaAssembler.toDomain(projJpa);
        Project expected = new Project(projId, desc, desc, typoId, status,
                cust, busSec, numbSpr, bud, sprDur,startDate, endDate);

        //Assert
        assertEquals(projId, result.getProjectCode());
        assertEquals( "x", result.getProjectName().getText());

    }
}