package switch2021.project.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

class ProjectTest {

    /**
     * Testes de Criação de Project da classe Project (Paulo - US005)
     **/

    //Arrange
    static LocalDate date = LocalDate.of(2021, 12, 12);
    static Typology typology = new ProjectSettings().getTypologyById(0);
    static Customer customer = new ProjectSettings().getCustomerById(0);
    static BusinessSector businessSector = new ProjectSettings().getBussinessSectorById(0);

    static Project newProject = new Project("123testcode", "prototype", "test", customer,
            typology, businessSector, date, 7, 5000);

    @Test
    @DisplayName("Teste de check Code")
    public void checkCode() {
        // Expected
        String code = newProject.getCode();
        String valueCode = "123testcode";

        // Results
        assertEquals(valueCode, code);
    }

    @Test
    @DisplayName("Teste de check Name")
    public void checkName() {
        //Expected
        String name = newProject.getProjectName();
        String valueName = "prototype";

        //Result
        assertEquals(valueName, name);
    }

    @Test
    @DisplayName("Teste de check description")
    public void checkDescription() {
        //Expected
        String description = newProject.getDescription();
        String valueDescription = "test";

        //Result
        assertEquals(valueDescription, description);
    }

    @Test
    @DisplayName("Teste de check Customer")
    public void checkCustomer() {
        //Expected
        Customer customerCheck = newProject.getCustomer();
        Customer valueCustomer = new Customer(0, "customer1@email.com");

        //Result
        assertEquals(valueCustomer, customerCheck);
    }

    @Test
    @DisplayName("Teste de check Typology")
    public void checkTypology() {
        //Expected
        Typology typologyCheck = newProject.getTypology();
        Typology valueTypology = new Typology("Fixed Cost");

        //Result
        assertEquals(valueTypology, typologyCheck);
    }

    @Test
    @DisplayName("Teste de check Start Date")
    public void checkStartDate() {
        //Expected
        LocalDate startDate = newProject.getStartDate();
        LocalDate valueStarDate = LocalDate.of(2021, 12, 12);

        //Result
        assertEquals(valueStarDate, startDate);
    }

    @Test
    @DisplayName("Teste de check End Date")
    public void checkEndDate() {
        //Expected
        newProject.setEndDate();
        LocalDate endDate = newProject.getEndDate();
        LocalDate valueEndDate = LocalDate.now();

        //Result
        assertEquals(valueEndDate, endDate);
    }

    @Test
    @DisplayName("Teste de check Sprint Duration")
    public void checkSprintDuration() {
        //Expected
        int numberOfSprints = newProject.getNumberOfSprints();
        int valueNumberOfSprints = 7;

        //Result
        assertEquals(valueNumberOfSprints, numberOfSprints);
    }

    @Test
    @DisplayName("Teste de check Budget")
    public void checkBudget() {
        //Expected
        int budget = newProject.getBudget();
        int valueBudget = 5000;

        //Result
        assertEquals(valueBudget, budget);
    }

    @Test
    @DisplayName("Teste de Project Status")
    public void checkProjectStatus() {
        //Expected
        ProjectStatus status = newProject.getProjectStatus();
        ProjectStatus valueStatus = new ProjectStatus("Planned");

        //Results
        assertEquals(valueStatus, status);
    }

    @Test
    @DisplayName("Teste de Business Sector")
    public void checkBusinessSector() {
        //Expected
        BusinessSector businessSectorCheck = newProject.getBusinessSector();
        BusinessSector valueBusinessSector = new BusinessSector("Business_0");

        //Results
        assertEquals(valueBusinessSector, businessSectorCheck);
    }

    @Test
    @DisplayName("Teste de adição de projecto a company")
    public void checkAddProject() {
        Company comp = new Company();
        comp.add(comp.createProject("123testcode", "prototype", "test", customer,
                typology, businessSector, date, 7, 5000));

        //Result
        assertEquals(comp.getProj(0),newProject);
    }

    @Test
    @DisplayName("Teste de validação de projecto")
    public void checkValidateProject() {
        Company comp = new Company();

        //Result
        assertTrue(comp.validateProject(comp.createProject("123testcode", "prototype", "test", customer,
                typology, businessSector, date, 7, 5000)));
        assertFalse(comp.validateProject(comp.createProject("123testcode", "prototype", "test", customer,
                typology, businessSector, date, 7, -1)));
        assertFalse(comp.validateProject(comp.createProject("123testcode", "prototype", "test", customer,
                typology, businessSector, date, -7, 5000)));
        assertFalse(comp.validateProject(comp.createProject("", "prototype", "test", customer,
                typology, businessSector, date, -7, 5000)));
        assertFalse(comp.validateProject(comp.createProject("123testcode", "", "test", customer,
                typology, businessSector, date, -7, 5000)));
    }
}