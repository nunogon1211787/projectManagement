package switch2021.project.controller.old;

import org.junit.jupiter.api.Test;
import switch2021.project.controller.old.CreateNewTypologyController;
import switch2021.project.model.Company;
import static org.junit.jupiter.api.Assertions.*;

public class CreateNewTypologyControllerTest {

    @Test
    public void createNewTypologyControllerTest() {
        //Arrange
        Company company = new Company();
        CreateNewTypologyController controller = new CreateNewTypologyController
                (company);
        //Act
        controller.createTypology("TypoTest");
        //Assert
        assertEquals(1, company.getTypologyRepository().getTypologyList().size());
    }

    @Test
    public void createNewTypologyControllerNullDescriptionTest() {
        //Arrange
        Company company = new Company();
        assertThrows(IllegalArgumentException.class, () -> {
            //Arrange
            CreateNewTypologyController controller = new CreateNewTypologyController(company);
            //Act and Assert
            controller.createTypology("");
        });
    }

    @Test
    public void createTypologyTest(){
        //Arrange
        Company company = new Company();
        assertThrows(IllegalArgumentException.class, () -> {
            //Arrange
            CreateNewTypologyController cont = new CreateNewTypologyController(company);
            //Act
            cont.createTypology("      ");
        });
    }
}
