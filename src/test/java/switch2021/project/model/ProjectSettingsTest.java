package switch2021.project.model;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ProjectSettingsTest {

    /**
     *       >>>>>TYPOLOGY´S TEST<<<<<
     **/

    /**
     * Este método faz teste do construtorr de Typology, do método getArrayTypology(),
     * do método createTypology(), do método saveTypology() e do método ValidateTypology()
     **/
    @Test
    public void constructorProjectSettingsTest() {
        //Arrange
        //Sec1
        ProjectSettings Lists = new ProjectSettings();
        Typology TypoTest = Lists.createTypology("Test");
        //Sec2
        ProjectSettings Test = new ProjectSettings();
        Typology newTypo = Lists.createTypology("Test");
        //Act
        Lists.saveTypology(TypoTest);
        Test.saveTypology(newTypo);
        //Assert
        assertEquals(Test.getArrayTypology().size(), Lists.getArrayTypology().size());
        assertEquals(Test.getArrayTypology().get(2), Lists.getArrayTypology().get(2));
        assertEquals(Test.getArrayProjectStatus().size(), Lists.getArrayProjectStatus().size());
        assertEquals(Test.getArrayCustomer().size(), Lists.getArrayCustomer().size());
        assertEquals(Test.getArrayBusinessSector().size(), Lists.getArrayBusinessSector().size());
    }

    @Test
    public void createTypologyTestNull() {
        //Arrange
        ProjectSettings Test = new ProjectSettings();
        //Act
        Typology typo = Test.createTypology("");
        //Assert
        assertFalse(Test.saveTypology(typo));
    }

    @Test
    public void createTypologyTest() {
        //Arrange
        ProjectSettings Test = new ProjectSettings();
        Typology tes = Test.createTypology("Test");
        //Act
        Typology typo = Test.createTypology("TestXPTO");
        //Assert
        assertNotEquals(typo, tes);
    }

    @Test
    public void getArrayTypologyTest() {
        //Arrange
        ProjectSettings Test = new ProjectSettings();
        List<Typology> arrayTest = Test.getArrayTypology();
        //Act
        Typology typo = Test.createTypology("Test");
        Test.saveTypology(typo);
        arrayTest.add(typo);
        //Assert
        assertEquals(Test.getArrayTypology(), arrayTest);
    }

    @Test
    public void getTypologyByIdTest() {
        //Arrange
        ProjectSettings proj = new ProjectSettings();
        //Act
        Typology typo = proj.getTypologyById(0);
        //Assert
        assertEquals(typo, proj.getTypologyById(0));

    }

    @Test
    public void validateTypology(){
        //Arrange
        ProjectSettings proj = new ProjectSettings();
        Typology typo = new Typology("");
        Typology typo1 = new Typology("Tes");
        Typology typo2 = new Typology("");
        //Act
        proj.add(typo);
        proj.add(typo1);
        proj.add(typo2);
        //Assert
        assertFalse(proj.validateTypology(typo));
        assertTrue(proj.validateTypology(typo1));
        assertFalse(proj.validateTypology(typo2));
        assertEquals(typo1,proj.getTypologyById(2));

    }

}
