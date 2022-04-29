package switch2021.project.model.Project;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class ProjectReengTest {

    @Test
    void hasCode() {
    }

    @Test
    void testEquals() {
    }

    @Test
    void canEqual() {
    }

    @Test
    void testHashCode() {
    }

    @Test
    void getProjectCode() {
    }

    @Test
    void getProjectName() {

        ProjectReeng projectReeng = new ProjectReeng("name", "description", "businessSector",
                                                     LocalDate.of(2032,12,12),15,
                                                     5000);

        ProjectReeng projectReeng_2 = new ProjectReeng("other_name", "other_description", "other_businessSector",
                                                       LocalDate.of(2032,12,12),15,
                                                       5000);

        assertEquals(projectReeng.hashCode(),projectReeng.hashCode());
        assertNotEquals(projectReeng.hashCode(),projectReeng_2.hashCode());
    }

    @Test
    void getDescription() {
    }

    @Test
    void getTypology() {
    }

    @Test
    void getProjectStatus() {
    }

    @Test
    void getCustomer() {
    }

    @Test
    void getBusinessSector() {
    }

    @Test
    void getNumberOfSprints() {
    }

    @Test
    void getBudget() {
    }

    @Test
    void getSprintDuration() {
    }

    @Test
    void getStartDate() {
    }

    @Test
    void getEndDate() {
    }

    @Test
    void setProjectCode() {
    }

    @Test
    void setProjectName() {
    }

    @Test
    void setDescription() {
    }

    @Test
    void setTypology() {
    }

    @Test
    void setProjectStatus() {
    }

    @Test
    void setCustomer() {
    }

    @Test
    void setBusinessSector() {
    }

    @Test
    void setNumberOfSprints() {
    }

    @Test
    void setBudget() {
    }

    @Test
    void setSprintDuration() {
    }

    @Test
    void setStartDate() {
    }

    @Test
    void setEndDate() {
    }
}