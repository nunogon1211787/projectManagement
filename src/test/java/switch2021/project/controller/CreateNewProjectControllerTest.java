package switch2021.project.controller;

import org.junit.jupiter.api.Test;
import switch2021.project.model.*;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CreateNewProjectControllerTest {

    Company company = new Company();

    @Test
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
        assertTrue(controller.createProject("123testcode", "prototype", "test1234", "Teste",
                "Fixed Cost", "sector", date, 7, 5000));
    }
}