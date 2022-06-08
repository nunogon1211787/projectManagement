package switch2021.project.entities.aggregates.Project;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import switch2021.project.entities.aggregates.Typology.Typology;
import switch2021.project.entities.valueObjects.vos.*;
import switch2021.project.entities.valueObjects.vos.enums.ProjectStatusEnum;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
class ProjectTest {

    @Mock
    ProjectID projectID;
    @Mock
    Description description;
    @Mock
    Description projectName;
    @Mock
    TypologyID typology;
    @Mock
    Customer customer;
    @Mock
    BusinessSector businessSector;
    @Mock
    Budget budget;
    @Mock
    NumberOfSprints numberOfSprints;
    @Mock
    SprintDuration sprintDuration;


//    @Test
//    void hasCodeTrue() {
//        //Arrange
//        ProjectID code = mock(ProjectID.class);
//        when(code.getCode()).thenReturn("Project_2022_1");
//        LocalDate startDate = LocalDate.now();
//        //Act
//        ProjectReeng projectReeng = new ProjectReeng(projectName, description, businessSector, startDate,
//                numberOfSprints, sprintDuration, budget);
////        projectReeng.setProjectCode(code);
//        //Assert
//        assertTrue(projectReeng.hasCode("Project_2022_1"));
//    }

//    @Test
//    void hasCodeFalse() {
//        //Arrange
//        ProjectID code = mock(ProjectID.class);
//        when(code.getCode()).thenReturn("test");
//        LocalDate startDate = LocalDate.now();
//        //Act
//        ProjectReeng projectReeng = new ProjectReeng(projectName, description, businessSector, startDate,
//                numberOfSprints, sprintDuration, budget);
////        projectReeng.setProjectCode(code);
//        //Assert
//        assertFalse(projectReeng.hasCode("testX"));
//    }


//    @Test
//    void testEquals() {
//        //Arrange
//        ProjectID code = mock(ProjectID.class);
//        when(code.getCode()).thenReturn("test");
//        LocalDate startDate = LocalDate.now();
//        Description name_2 = mock(Description.class);
//        ProjectID id_2 = mock(ProjectID.class);
//        //Act
//        ProjectReeng proj_1 = new ProjectReeng(projectName, description, businessSector, startDate, numberOfSprints
//                , sprintDuration, budget);
//
////        proj_1.setProjectCode(code);
//        ProjectReeng proj_2 = new ProjectReeng(name_2, description, businessSector, startDate, numberOfSprints
//                , sprintDuration, budget);
////        proj_2.setProjectCode(id_2);
//
//        ProjectReeng proj_3 = new ProjectReeng(projectName, description, businessSector, startDate, numberOfSprints
//                , sprintDuration, budget);
////        proj_3.setProjectCode(id_2);
//        //Assert
//        assertEquals(proj_1, proj_1);
//        assertNotEquals(proj_1, proj_2);
//        assertNotEquals(proj_3, proj_1);
//        assertNotEquals(customer, proj_2);
//    }

    @Test
    void canEqual() {
    }

//    @Test
//    void testHashCode() {
//        //Arrange
//        ProjectID id_2 = mock(ProjectID.class);
//        LocalDate startDate = LocalDate.now();
//        //Act
//        ProjectReeng proj_1 = new ProjectReeng(projectName, description, businessSector, startDate, numberOfSprints
//                , sprintDuration, budget);
////        proj_1.setProjectCode(projectID);
//
//        ProjectReeng proj_2 = new ProjectReeng(projectName, description, businessSector, startDate, numberOfSprints
//                , sprintDuration, budget);
////        proj_2.setProjectCode(id_2);
//        //Assert
//        assertEquals(proj_1.hashCode(), proj_1.hashCode());
//        assertNotEquals(proj_1.hashCode(), proj_2.hashCode());
//    }

//    @Test
//    void setAndGetProjectCode() {
//        //Arrange
//        LocalDate startDate = LocalDate.now();
//        //Act
//        ProjectReeng projectReeng = new ProjectReeng(projectName, description, businessSector, startDate,
//                numberOfSprints, sprintDuration, budget);
////        projectReeng.setProjectCode(projectID);
//        //Assert
//        assertEquals(projectID, projectReeng.getProjectCode());
//    }

    @Test
    void setAndGetProjectName() {
        //Arrange
        LocalDate startDate = LocalDate.now();
        //Act
        Project project = new Project(projectName, description, businessSector, startDate,
                                      numberOfSprints, sprintDuration, budget);
        project.setProjectName(projectName);
        //Assert
        assertEquals(projectName, project.getProjectName());
    }

    @Test
    void setAndGetDescription() {
        //Arrange
        LocalDate startDate = LocalDate.now();
        //Act
        Project project = new Project(projectName, description, businessSector, startDate,
                                      numberOfSprints, sprintDuration, budget);
        project.setDescription(description);
        //Assert
        assertEquals(description, project.getDescription());
    }

    @Test
    void setAndGetTypology() {
        //Arrange
        LocalDate startDate = LocalDate.now();
        //Act
        Project project = new Project(projectName, description, businessSector, startDate,
                                      numberOfSprints, sprintDuration, budget);
        project.setTypologyId(typology);
        //Assert
        assertEquals(typology, project.getTypologyId());
    }

    @Test
    void setAndGetProjectStatus() {
        //Arrange
        LocalDate startDate = LocalDate.now();
        //Act
        Project project = new Project(projectName, description, businessSector, startDate,
                                      numberOfSprints
                , sprintDuration, budget);
        project.setProjectStatus(ProjectStatusEnum.PLANNED);
        //Assert
        assertEquals(ProjectStatusEnum.PLANNED, project.getProjectStatus());
    }

    @Test
    void setAndGetCustomer() {
        //Arrange
        LocalDate startDate = LocalDate.now();
        //Act
        Project project = new Project(projectName, description, businessSector, startDate,
                                      numberOfSprints
                , sprintDuration, budget);
        project.setCustomer(customer);
        //Assert
        assertEquals(customer, project.getCustomer());
    }

    @Test
    void setAndGetBusinessSector() {
        //Arrange
        LocalDate startDate = LocalDate.now();
        //Act
        Project project = new Project(projectName, description, businessSector, startDate,
                                      numberOfSprints
                , sprintDuration, budget);
        project.setBusinessSector(businessSector);
        //Assert
        assertEquals(businessSector, project.getBusinessSector());
    }

    @Test
    void setAndGetNumberOfSprints() {
        //Arrange
        LocalDate startDate = LocalDate.now();
        //Act
        Project project = new Project(projectName, description, businessSector, startDate,
                                      numberOfSprints
                , sprintDuration, budget);
        project.setNumberOfSprints(numberOfSprints);
        //Assert
        assertEquals(numberOfSprints, project.getNumberOfSprints());
    }

    @Test
    void setAndGetBudget() {
        //Arrange
        LocalDate startDate = LocalDate.now();
        //Act
        Project project = new Project(projectName, description, businessSector, startDate,
                                      numberOfSprints
                , sprintDuration, budget);
        project.setBudget(budget);
        //Assert
        assertEquals(budget, project.getBudget());
    }

    @Test
    void setAndGetSprintDuration() {
        //Arrange
        LocalDate startDate = LocalDate.now();
        //Act
        Project project = new Project(projectName, description, businessSector, startDate,
                                      numberOfSprints
                , sprintDuration, budget);
        project.setSprintDuration(sprintDuration);
        //Assert
        assertEquals(sprintDuration, project.getSprintDuration());
    }

    @Test
    void setAndGetStartDate() {
        //Arrange
        LocalDate startDate = LocalDate.now();
        //Act
        Project project = new Project(projectName, description, businessSector, startDate,
                                      numberOfSprints
                , sprintDuration, budget);
        project.setStartDate(startDate);
        //Assert
        assertEquals(startDate, project.getStartDate());
    }

    @Test
    void setAndGetEndDate() {
        //Arrange
        LocalDate startDate = LocalDate.now();
        LocalDate endDate = LocalDate.now();
        //Act
        Project project = new Project(projectName, description, businessSector, startDate,
                                      numberOfSprints
                , sprintDuration, budget);
        project.setEndDate(endDate);
        //Assert
        assertEquals(endDate, project.getEndDate());
    }

    @Test
    void isActiveInThisDateFalse() {
        //Arrange
        LocalDate startDate = LocalDate.now();
        Project project = new Project(projectName, description, businessSector, startDate,
                                      numberOfSprints
                , sprintDuration, budget);
        //Act
        boolean expected = project.isActiveInThisDate(LocalDate.now().minusDays(1));
        //Assert
        assertFalse(expected);
    }

    @Test
    void isActiveInThisDateTrue() {
        //Arrange
        LocalDate startDate = LocalDate.now();
        Project project = new Project(projectName, description, businessSector, startDate,
                                      numberOfSprints
                , sprintDuration, budget);
        //Act
        project.setEndDate(LocalDate.now().plusDays(10));
        boolean expected = project.isActiveInThisDate(LocalDate.now().plusDays(10));
        //Assert
        assertTrue(expected);
    }


}