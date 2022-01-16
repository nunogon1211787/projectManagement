package switch2021.project.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import switch2021.project.model.Company;
import switch2021.project.model.TypologyStore;
import switch2021.project.utils.App;
import static org.junit.jupiter.api.Assertions.*;


public class CreateNewTypologyControllerTest {

    private  Company company;
    private TypologyStore typologyStore;

    @BeforeEach
    public void init(){

        company = App.getInstance().getCompany(); // sempre a mesma instancia
        typologyStore = company.getTypologyStore();
    }

    @Test
    public void createNewTypologyControllerTest() {
        //Arrange
        CreateNewTypologyController controller = new CreateNewTypologyController
                (typologyStore);
        //Act and Assert
        assertTrue(controller.createTypology("TypoTest"));
    }



    @Test
    public void createNewTypologyControllerNullDescriptionTest() {
        //Assert
        assertThrows(IllegalArgumentException.class, () -> {
            //Arrange
            CreateNewTypologyController controller = new CreateNewTypologyController(typologyStore);
            //Act and Assert
            controller.createTypology("");
        });
    }

    @Test
    public void createTypologyTest(){
        //Assert
        assertThrows(IllegalArgumentException.class, () -> {
            //Arrange
            CreateNewTypologyController cont = new CreateNewTypologyController(typologyStore);
            //Act
            cont.createTypology("      ");
        });
    }
}
