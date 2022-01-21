package switch2021.project.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProjectCreationTest {

    /**
     * Project creation Test (US005)
     **/

    Company company = new Company();
    Project proj;

    @BeforeEach
    public void init() {
        //Arrange
        LocalDate date = LocalDate.of(2021, 12, 12);
        company.getBusinessSectorStore().addBusinessSector(company.getBusinessSectorStore().createBusinessSector("sector"));
        company.getCustomerStore().add(company.getCustomerStore().createCustomer("Teste","Teste"));

        Typology typo = company.getTypologyStore().getTypology("Fixed Cost");
        Customer customer = company.getCustomerStore().getCustomerByName("Teste");
        BusinessSector sector = company.getBusinessSectorStore().getBusinessSectorByDescription("sector");

        proj = company.getProjectStore().createProject("123testcode", "prototype", "test1234", customer,
                typo, sector, date, 7, 5000);
        company.getProjectStore().addProject(proj);
    }

    @Test
    @DisplayName("Project Creation Test")
    public void checkProjectCreation() {
        //Real and Expected
        String code = proj.getCode();
        String valuecode = "123testcode";

        String name = proj.getProjectName();
        String valueName = "prototype";

        String description = proj.getDescription();
        String valuedescription = "test1234";

        Customer customer = company.getProjectStore().getProjectByCode("123testcode").getCustomer();
        Customer valueCustomer = company.getCustomerStore().getCustomerByName("Teste");

        Typology typology = company.getProjectStore().getProjectByCode("123testcode").getTypology();
        Typology valuetypology = company.getTypologyStore().getTypology("Fixed Cost");

        BusinessSector sector = company.getProjectStore().getProjectByCode("123testcode").getBusinessSector();
        BusinessSector valueSector = company.getBusinessSectorStore().getBusinessSectorByDescription("sector");

        ProjectStatus status = company.getProjectStore().getProjectByCode("123testcode").getProjectStatus();
        ProjectStatus valuestatus = company.getProjectStatusStore().getProjectStatusByDescription("Planned");

        LocalDate date = company.getProjectStore().getProjectByCode("123testcode").getStartDate();
        LocalDate valueDate = LocalDate.of(2021, 12, 12);

        int numberOfSprints = company.getProjectStore().getProjectByCode("123testcode").getNumberOfSprints();
        int valueNrSprint = 7;

        double budget = company.getProjectStore().getProjectByCode("123testcode").getBudget();
        double valueBudget = 5000;

        //Result
        assertEquals(valuecode, code);
        assertEquals(valueName, name);
        assertEquals(valuedescription, description);
        assertEquals(valueCustomer,customer);
        assertEquals(valuetypology,typology);
        assertEquals(valueSector,sector);
        assertEquals(valuestatus,status);
        assertEquals(valueDate,date);
        assertEquals(valueNrSprint,numberOfSprints);
        assertEquals(valueBudget,budget);
    }

    @Test
    @DisplayName("Project addition to list test")
    public void projectAdditionTest() {
        List<Project> test = company.getProjectStore().getProjectList();
        String code = test.get(0).getCode();
        String expectedCode = "123testcode";
        assertEquals(expectedCode,code);
    }

    @Test
    @DisplayName("Project exceptions test")
    public void createProjectExceptionsTest_name() {
        //Arrange
        Typology typo = company.getTypologyStore().getTypology("Fixed Cost");
        Customer customer = company.getCustomerStore().getCustomerByName("Teste");
        BusinessSector sector = company.getBusinessSectorStore().getBusinessSectorByDescription("sector");
        LocalDate date = LocalDate.now();
        // Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            company.getProjectStore().createProject("", "", "", customer,
                    typo, sector, date, -1, -1);
        });
        //Assert
        assertTrue(exception.getMessage().contains("Project Name cannot be empty"));
    }

    @Test
    @DisplayName("Project exceptions test")
    public void createProjectExceptionsTest_nameLength() {
        //Arrange
        Typology typo = company.getTypologyStore().getTypology("Fixed Cost");
        Customer customer = company.getCustomerStore().getCustomerByName("Teste");
        BusinessSector sector = company.getBusinessSectorStore().getBusinessSectorByDescription("sector");
        LocalDate date = LocalDate.now();
        // Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            company.getProjectStore().createProject("", "as", "", customer,
                    typo, sector, date, -1, -1);
        });
        //Assert
        assertTrue(exception.getMessage().contains("Project Name must be at least 3 characters"));
    }

    @Test
    @DisplayName("Project exceptions test")
    public void createProjectExceptionsTest_description() {
        //Arrange
        Typology typo = company.getTypologyStore().getTypology("Fixed Cost");
        Customer customer = company.getCustomerStore().getCustomerByName("Teste");
        BusinessSector sector = company.getBusinessSectorStore().getBusinessSectorByDescription("sector");
        LocalDate date = LocalDate.now();
        // Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            company.getProjectStore().createProject("", "aetr", "", customer,
                    typo, sector, date, -1, -1);
        });
        //Assert
        assertTrue(exception.getMessage().contains("Description cannot be empty"));
    }

    @Test
    @DisplayName("Project exceptions test")
    public void createProjectExceptionsTest_descriptionLenght() {
        //Arrange
        Typology typo = company.getTypologyStore().getTypology("Fixed Cost");
        Customer customer = company.getCustomerStore().getCustomerByName("Teste");
        BusinessSector sector = company.getBusinessSectorStore().getBusinessSectorByDescription("sector");
        LocalDate date = LocalDate.now();
        // Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            company.getProjectStore().createProject("", "aetr", "asd", customer,
                    typo, sector, date, -1, -1);
        });
        //Assert
        assertTrue(exception.getMessage().contains("Description must be at least 5 characters"));
    }

    @Test
    @DisplayName("Project exceptions test")
    public void createProjectExceptionsTest_numberOfSprints() {
        //Arrange
        Typology typo = company.getTypologyStore().getTypology("Fixed Cost");
        Customer customer = company.getCustomerStore().getCustomerByName("Teste");
        BusinessSector sector = company.getBusinessSectorStore().getBusinessSectorByDescription("sector");
        LocalDate date = LocalDate.now();
        // Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            company.getProjectStore().createProject("", "aetr", "asdlkjv", customer,
                    typo, sector, date, -1, -1);
        });
        //Assert
        assertTrue(exception.getMessage().contains("Number of Sprints must be greater than 0"));
    }

    @Test
    @DisplayName("Project exceptions test")
    public void createProjectExceptionsTest_budget() {
        //Arrange
        Typology typo = company.getTypologyStore().getTypology("Fixed Cost");
        Customer customer = company.getCustomerStore().getCustomerByName("Teste");
        BusinessSector sector = company.getBusinessSectorStore().getBusinessSectorByDescription("sector");
        LocalDate date = LocalDate.now();
        // Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            company.getProjectStore().createProject("", "aetr", "asdlkjv", customer,
                    typo, sector, date, 7, -1);
        });
        //Assert
        assertTrue(exception.getMessage().contains("Budget must be greater than 0"));
    }

    @Test
    @DisplayName("Project Creation validation test")
    public void checkValidation() {
    boolean test = company.getProjectStore().saveNewProject(proj);
    assertFalse(test);
    }


}