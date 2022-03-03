package switch2021.project.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switch2021.project.model.*;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

public class CreateNewProjectControllerTest {

    Company company = new Company();

    @Test
    @DisplayName("Check creation and addition to list")
    public void createNewProjectControllerTest() {
        //Arrange
        LocalDate date = LocalDate.of(2021, 12, 12);
        company.getBusinessSectorStore().addBusinessSector(company.getBusinessSectorStore().createBusinessSector("sector"));
        company.getCustomerStore().saveNewCustomer(company.getCustomerStore().createCustomer("Teste", "Teste"));

        company.getTypologyStore().getTypology("Fixed Cost");
        company.getCustomerStore().getCustomerByName("Teste");
        company.getBusinessSectorStore().getBusinessSectorByDescription("sector");

        CreateProjectController controller = new CreateProjectController(company);

        //Act and Assert
        assertTrue(controller.createProject("prototype", "test1234", "Teste",
                "Fixed Cost", "sector", date, 7, 5000));
        assertEquals(1, company.getProjectStore().getProjectList().size());
    }

}