package switch2021.project.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

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

    //TODO repetir testes do Project testes, check todos os campos de criação;
}