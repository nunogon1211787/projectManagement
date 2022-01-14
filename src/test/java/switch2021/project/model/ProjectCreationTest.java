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

        Typology typo = company.getTypologyStore().getTypologyByDescription("Fixed Cost");
        Customer customer = company.getCustomerStore().getCustomerByName("Teste");
        BusinessSector sector = company.getBusinessSectorStore().getBusinessSectorByDescription("sector");
        proj = company.getProjectStore().createProject("123testcode", "prototype", "test", customer,
                typo, sector, date, 7, 5000);
    }

    @Test
    @DisplayName("Teste de check Name")
    public void checkName() {
        //Real and Expected
        String name = proj.getProjectName();
        String valueName = "prototype";

        company.getProjectStatusStore().getProjectStatusByDescription("Planned");

        //Result
        assertEquals(valueName, name);
    }

    //TODO repetir testes do Project testes, check todos os campos de criação;
}