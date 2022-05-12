package switch2021.project.model.Project;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import switch2021.project.model.Typology.Typology;
import switch2021.project.model.valueObject.*;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

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
    LocalDate startDate;
    @Mock
    SprintDuration sprintDuration;
    @Mock
    LocalDate endDate;


    @Test
    void hasCode() {
        ProjectID code = mock(ProjectID.class);
        when(code.getCode()).thenReturn("test");

        ProjectReeng projectReeng = new ProjectReeng(projectName,description,businessSector,startDate,numberOfSprints
                ,sprintDuration,budget);
        projectReeng.setProjectCode(code);

        assertTrue(projectReeng.hasCode("test"));
    }

    @Test
    void testEquals() {
        Description name_2 = mock(Description.class);
        ProjectID id_2 = mock(ProjectID.class);

        ProjectReeng proj_1 = new ProjectReeng(projectName,description,businessSector,startDate,numberOfSprints
                ,sprintDuration,budget);
        proj_1.setProjectCode(projectID);

        ProjectReeng proj_2 = new ProjectReeng(name_2,description,businessSector,startDate,numberOfSprints
                ,sprintDuration,budget);
        proj_2.setProjectCode(id_2);

        ProjectReeng proj_3 = new ProjectReeng(projectName,description,businessSector,startDate,numberOfSprints
                ,sprintDuration,budget);
        proj_3.setProjectCode(id_2);

        assertEquals(proj_1,proj_1);
        assertNotEquals(proj_1,proj_2);
        assertNotEquals(proj_3,proj_1);
        assertNotEquals(customer,proj_2);
    }

    @Test
    void canEqual() {
    }

    @Test
    void testHashCode() {
        ProjectID id_2 = mock(ProjectID.class);

        ProjectReeng proj_1 = new ProjectReeng(projectName,description,businessSector,startDate,numberOfSprints
                ,sprintDuration,budget);
        proj_1.setProjectCode(projectID);

        ProjectReeng proj_2 = new ProjectReeng(projectName,description,businessSector,startDate,numberOfSprints
                ,sprintDuration,budget);
        proj_2.setProjectCode(id_2);

        assertEquals(proj_1.hashCode(),proj_1.hashCode());
        assertNotEquals(proj_1.hashCode(),proj_2.hashCode());
    }

    @Test
    void setAndGetProjectCode() {
        ProjectReeng projectReeng = new ProjectReeng(projectName,description,businessSector,startDate,numberOfSprints
                ,sprintDuration,budget);

        assertEquals(projectID,projectReeng.getProjectCode());
    }

    @Test
    void setAndGetProjectName() {
        ProjectReeng projectReeng = new ProjectReeng(projectName,description,businessSector,startDate,numberOfSprints
                ,sprintDuration,budget);
        projectReeng.setProjectName(projectName);

        assertEquals(projectName, projectReeng.getProjectName());
    }

    @Test
    void setAndGetDescription() {
        ProjectReeng projectReeng = new ProjectReeng(projectName,description,businessSector,startDate,numberOfSprints
                ,sprintDuration,budget);
        projectReeng.setDescription(description);

        assertEquals(description, projectReeng.getProjectName());
    }

    @Test
    void setAndGetTypology() {
        ProjectReeng projectReeng = new ProjectReeng(projectName,description,businessSector,startDate,numberOfSprints
                ,sprintDuration,budget);
        projectReeng.setTypology(typology);

        assertEquals(typology, projectReeng.getTypology());
    }

    @Test
    void setAndGetProjectStatus() {
        ProjectReeng projectReeng = new ProjectReeng(projectName,description,businessSector,startDate,numberOfSprints
                ,sprintDuration,budget);
        projectReeng.setProjectStatus(ProjectStatusEnum.PLANNED);

        assertEquals(ProjectStatusEnum.PLANNED, projectReeng.getProjectStatus());
    }

    @Test
    void setAndGetCustomer() {
        ProjectReeng projectReeng = new ProjectReeng(projectName,description,businessSector,startDate,numberOfSprints
                ,sprintDuration,budget);
        projectReeng.setCustomer(customer);

        assertEquals(customer, projectReeng.getCustomer());
    }

    @Test
    void setAndGetBusinessSector() {
        ProjectReeng projectReeng = new ProjectReeng(projectName,description,businessSector,startDate,numberOfSprints
                ,sprintDuration,budget);
        projectReeng.setBusinessSector(businessSector);

        assertEquals(businessSector, projectReeng.getBusinessSector());
    }

    @Test
    void setAndGetNumberOfSprints() {
        ProjectReeng projectReeng = new ProjectReeng(projectName,description,businessSector,startDate,numberOfSprints
                ,sprintDuration,budget);
        projectReeng.setNumberOfSprints(numberOfSprints);

        assertEquals(numberOfSprints, projectReeng.getNumberOfSprints());
    }

    @Test
    void setAndGetBudget() {
        ProjectReeng projectReeng = new ProjectReeng(projectName,description,businessSector,startDate,numberOfSprints
                ,sprintDuration,budget);
        projectReeng.setBudget(budget);

        assertEquals(budget, projectReeng.getBudget());
    }

    @Test
    void setAndGetSprintDuration() {
        ProjectReeng projectReeng = new ProjectReeng(projectName,description,businessSector,startDate,numberOfSprints
                ,sprintDuration,budget);
        projectReeng.setSprintDuration(sprintDuration);

        assertEquals(sprintDuration, projectReeng.getSprintDuration());
    }

    @Test
    void setAndGetStartDate() {
        ProjectReeng projectReeng = new ProjectReeng(projectName,description,businessSector,startDate,numberOfSprints
                ,sprintDuration,budget);
        projectReeng.setStartDate(startDate);

        assertEquals(startDate, projectReeng.getStartDate());
    }

    @Test
    void setAndGetEndDate() {
        ProjectReeng projectReeng = new ProjectReeng(projectName,description,businessSector,startDate,numberOfSprints
                ,sprintDuration,budget);
        projectReeng.setEndDate(endDate);

        assertEquals(endDate, projectReeng.getEndDate());
    }

}