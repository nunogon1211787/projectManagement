package switch2021.project.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import switch2021.project.model.ProjectSettings;
import switch2021.project.model.Typology;
import switch2021.project.utils.App;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class CreateNewTypologyControllerTest {

    private ProjectSettings projectSettings;
    private Typology typology;

    @BeforeEach
    public void init(){
        projectSettings = App.getInstance().getProjectSettings(); // sempre a mesma instancia
        typology = projectSettings.createTypology("TypoTest");
        projectSettings.getArrayTypology().add(typology);
    }

    @Test
    public void createNewTypologyControllerTest() {
        //Arrange
        CreateNewTypologyController controller = new CreateNewTypologyController
                (projectSettings);
        //Act and Assert
        assertTrue(controller.createTypology("TypoTest"));
    }

    @Test
    public void createTypologyTest(){
        //Arrange
        CreateNewTypologyController cont = new CreateNewTypologyController(projectSettings);
        //Act and Assert
        assertFalse(cont.createTypology(""));
    }
}
