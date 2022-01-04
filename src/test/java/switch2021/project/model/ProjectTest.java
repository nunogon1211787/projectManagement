package switch2021.project.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProjectTest {


    /**
     * O teste Está imcompleto ainda
     **/

    @Test
    @DisplayName("Teste de criação de projecto")
    public void createProject() {
        //Arrange
        ArrayList<String> businessSector = new ArrayList<String>();
        businessSector.add("business_1");
        businessSector.add("business_2");
        LocalDate date = LocalDate.of(2021, 12, 12);

        //
        Project newProject = new Project("123testcode", "prototype", "test", "customer_0",
                "typology_0", businessSector, date, 7, 5000);
        newProject.setEndDate();

        // Expected
        String code = newProject.getCode();
        String valueCode = "123testcode";

        String name = newProject.getName();
        String valueName = "prototype";

        String description = newProject.getDescription();
        String valueDescription = "test";

        String customer = newProject.getCustomer();
        String valueCustomer = "customer_0";

        String typology = newProject.getTypology();
        String valueTypology = "typology_0";

        List<String> businessSectorCheck = newProject.getBusinessSector();

        LocalDate startDate = newProject.getStartDate();
        LocalDate valueStarDate = LocalDate.of(2021, 12, 12);

        LocalDate endDate = newProject.getEndDate();
        LocalDate valueEndDate = LocalDate.now();

        int numberOfSprints = newProject.getNumberOfSprints();
        int valueNumberOfSprints = 7;

        int budget = newProject.getBudget();
        int valueBudget = 5000;

        String status = newProject.getProjectStatus();
        String valueStatus = "Status_0";

        // Result
        assertEquals(valueCode, code);
        assertEquals(valueName, name);
        assertEquals(valueDescription, description);
        assertEquals(valueCustomer, customer);
        assertEquals(valueTypology, typology);
        assertEquals(valueStarDate, startDate);
        assertEquals(valueEndDate, endDate);
        assertEquals(valueNumberOfSprints, numberOfSprints);
        assertEquals(valueBudget, budget);
        assertEquals(valueStatus, status);
        assertTrue(businessSector.equals(businessSectorCheck));

    }

}