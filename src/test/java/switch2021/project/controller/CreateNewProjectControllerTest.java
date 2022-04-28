package switch2021.project.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switch2021.project.controller.old.CreateProjectController;
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
        company.getBusinessSectorStore().createAndAddBusinessSector("sector");
        company.getCustomerStore().createAndAddCustomer("Teste", "Teste@teste.com", 123456789);

        //Act
        CreateProjectController controller = new CreateProjectController(company);

        controller.getTypology("Fixed Cost");
        controller.getCustomer("Teste");
        controller.getBusinessSector("sector");

        //Assert
        assertTrue(controller.createProject("prototype", "test1234", date,
                 7, 5000));
    }

    @Test
    @DisplayName("Check addition to list")
    public void SaveNewProjectControllerTest() {
        //Arrange
        LocalDate date = LocalDate.of(2021, 12, 12);
        company.getBusinessSectorStore().createAndAddBusinessSector("sector");
        company.getCustomerStore().createAndAddCustomer("Teste", "teste@teste.com", 123456789);

        //Act
        CreateProjectController controller = new CreateProjectController(company);

        controller.getTypology("Fixed Cost");
        controller.getCustomer("Teste");
        controller.getBusinessSector("sector");
        controller.createProject("prototype", "test1234", date,
                7, 5000);

        //Assert
        assertEquals(1, company.getProjectStore().getProjectList().size());
    }
}