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
        List<BusinessSector> businessSector = new ProjectSettings().getArrayBusinessSector();

        LocalDate date = LocalDate.of(2021, 12, 12);
        Typology typology = new ProjectSettings().getTypologyById(0);
        Customer customer = new ProjectSettings().getCustomerById(0);

        Project newProject = new Project("123testcode", "prototype", "test", customer,
                typology, businessSector, date, 7, 5000);

        newProject.setEndDate();

        // Expected
        String code = newProject.getCode();
        String valueCode = "123testcode";

        String name = newProject.getProjectName();
        String valueName = "prototype";

        String description = newProject.getDescription();
        String valueDescription = "test";

        Customer customerCheck = newProject.getCustomer();
        Customer valueCustomer = new Customer(1, "customer1@email.com");

        Typology typologyCheck = newProject.getTypology();
        Typology valueTypology = new Typology("Fixed Cost");

        List<BusinessSector> businessSectorCheck = newProject.getBusinessSector();
        List<BusinessSector> valueBusinessSector = new ProjectSettings().getArrayBusinessSector();

        LocalDate startDate = newProject.getStartDate();
        LocalDate valueStarDate = LocalDate.of(2021, 12, 12);

        LocalDate endDate = newProject.getEndDate();
        LocalDate valueEndDate = LocalDate.now();

        int numberOfSprints = newProject.getNumberOfSprints();
        int valueNumberOfSprints = 7;

        int budget = newProject.getBudget();
        int valueBudget = 5000;

        ProjectStatus status = newProject.getProjectStatus();
        ProjectStatus valueStatus = new ProjectStatus("Planned");

        // Result
        assertEquals(valueCode, code);
        assertEquals(valueName, name);
        assertEquals(valueDescription, description);
        assertEquals(valueCustomer, customerCheck);
        assertEquals(valueTypology, typologyCheck);
        assertEquals(valueStarDate, startDate);
        assertEquals(valueEndDate, endDate);
        assertEquals(valueNumberOfSprints, numberOfSprints);
        assertEquals(valueBudget, budget);
        assertEquals(valueStatus, status);
        assertEquals(valueBusinessSector, businessSectorCheck);

    }


}