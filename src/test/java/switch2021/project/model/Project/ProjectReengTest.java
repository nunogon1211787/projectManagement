package switch2021.project.model.Project;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import switch2021.project.model.Typology.Typology;
import switch2021.project.model.valueObject.*;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class ProjectReengTest {

    @Mock
    ProjectID projectID;
    @Mock
    Description description;
    @Mock
    Description projectName;
    @Mock
    Typology typology;
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
        ProjectReeng projectReeng = new ProjectReeng(projectName, description, businessSector, startDate,
                numberOfSprints, sprintDuration, budget);
        projectReeng.setProjectName(projectName);
        //Assert
        assertEquals(projectName, projectReeng.getProjectName());
    }

    @Test
    void setAndGetDescription() {
        //Arrange
        LocalDate startDate = LocalDate.now();
        //Act
        ProjectReeng projectReeng = new ProjectReeng(projectName, description, businessSector, startDate,
                numberOfSprints, sprintDuration, budget);
        projectReeng.setDescription(description);
        //Assert
        assertEquals(description, projectReeng.getDescription());
    }

    @Test
    void setAndGetTypology() {
        //Arrange
        LocalDate startDate = LocalDate.now();
        //Act
        ProjectReeng projectReeng = new ProjectReeng(projectName, description, businessSector, startDate,
                numberOfSprints, sprintDuration, budget);
        projectReeng.setTypology(typology);
        //Assert
        assertEquals(typology, projectReeng.getTypology());
    }

    @Test
    void setAndGetProjectStatus() {
        //Arrange
        LocalDate startDate = LocalDate.now();
        //Act
        ProjectReeng projectReeng = new ProjectReeng(projectName, description, businessSector, startDate,
                numberOfSprints
                , sprintDuration, budget);
        projectReeng.setProjectStatus(ProjectStatusEnum.PLANNED);
        //Assert
        assertEquals(ProjectStatusEnum.PLANNED, projectReeng.getProjectStatus());
    }

    @Test
    void setAndGetCustomer() {
        //Arrange
        LocalDate startDate = LocalDate.now();
        //Act
        ProjectReeng projectReeng = new ProjectReeng(projectName, description, businessSector, startDate,
                numberOfSprints
                , sprintDuration, budget);
        projectReeng.setCustomer(customer);
        //Assert
        assertEquals(customer, projectReeng.getCustomer());
    }

    @Test
    void setAndGetBusinessSector() {
        //Arrange
        LocalDate startDate = LocalDate.now();
        //Act
        ProjectReeng projectReeng = new ProjectReeng(projectName, description, businessSector, startDate,
                numberOfSprints
                , sprintDuration, budget);
        projectReeng.setBusinessSector(businessSector);
        //Assert
        assertEquals(businessSector, projectReeng.getBusinessSector());
    }

    @Test
    void setAndGetNumberOfSprints() {
        //Arrange
        LocalDate startDate = LocalDate.now();
        //Act
        ProjectReeng projectReeng = new ProjectReeng(projectName, description, businessSector, startDate,
                numberOfSprints
                , sprintDuration, budget);
        projectReeng.setNumberOfSprints(numberOfSprints);
        //Assert
        assertEquals(numberOfSprints, projectReeng.getNumberOfSprints());
    }

    @Test
    void setAndGetBudget() {
        //Arrange
        LocalDate startDate = LocalDate.now();
        //Act
        ProjectReeng projectReeng = new ProjectReeng(projectName, description, businessSector, startDate,
                numberOfSprints
                , sprintDuration, budget);
        projectReeng.setBudget(budget);
        //Assert
        assertEquals(budget, projectReeng.getBudget());
    }

    @Test
    void setAndGetSprintDuration() {
        //Arrange
        LocalDate startDate = LocalDate.now();
        //Act
        ProjectReeng projectReeng = new ProjectReeng(projectName, description, businessSector, startDate,
                numberOfSprints
                , sprintDuration, budget);
        projectReeng.setSprintDuration(sprintDuration);
        //Assert
        assertEquals(sprintDuration, projectReeng.getSprintDuration());
    }

    @Test
    void setAndGetStartDate() {
        //Arrange
        LocalDate startDate = LocalDate.now();
        //Act
        ProjectReeng projectReeng = new ProjectReeng(projectName, description, businessSector, startDate,
                numberOfSprints
                , sprintDuration, budget);
        projectReeng.setStartDate(startDate);
        //Assert
        assertEquals(startDate, projectReeng.getStartDate());
    }

    @Test
    void setAndGetEndDate() {
        //Arrange
        LocalDate startDate = LocalDate.now();
        LocalDate endDate = LocalDate.now();
        //Act
        ProjectReeng projectReeng = new ProjectReeng(projectName, description, businessSector, startDate,
                numberOfSprints
                , sprintDuration, budget);
        projectReeng.setEndDate(endDate);
        //Assert
        assertEquals(endDate, projectReeng.getEndDate());
    }

    @Test
    void isActiveInThisDateFalse() {
        //Arrange
        LocalDate startDate = LocalDate.now();
        ProjectReeng projectReeng = new ProjectReeng(projectName, description, businessSector, startDate,
                numberOfSprints
                , sprintDuration, budget);
        //Act
        boolean expected = projectReeng.isActiveInThisDate(LocalDate.now().minusDays(1));
        //Assert
        assertFalse(expected);
    }

    @Test
    void isActiveInThisDateTrue() {
        //Arrange
        LocalDate startDate = LocalDate.now();
        ProjectReeng projectReeng = new ProjectReeng(projectName, description, businessSector, startDate,
                numberOfSprints
                , sprintDuration, budget);
        //Act
        projectReeng.setEndDate(LocalDate.now().plusDays(10));
        boolean expected = projectReeng.isActiveInThisDate(LocalDate.now().plusDays(10));
        //Assert
        assertTrue(expected);
    }


}