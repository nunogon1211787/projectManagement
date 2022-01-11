package switch2021.project.model;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
/**Se der erro, é pq, para fins de teste com o ID automático,
 não aceita fazer todos os testes ao mesmo tempo... deve ser revisado futuramente**/
public class TypologyTest {

    /**
     * Constructor´s Tests
     */

    @Test
    public void constructorTypologyTestNull() {
        //Arrange
        Typology typo = new Typology("");
        //Act
        int id = typo.getId_Typology();
        int value_id = 0;
        String description = typo.getDescription();
        String value_description = "";
        //Assert
        assertEquals(id, value_id);
        assertEquals(description, value_description);
    }

    @Test
    public void constructorTypologyTestNotNull() {
        //Arrange
        ProjectSettings proj = new ProjectSettings();
        Typology typo2 = new Typology("TestTypology");
        //Act
        proj.add(typo2);
        int value_id = 2;
        String description = typo2.getDescription();
        String value_description = "TestTypology";
        //Assert
        assertEquals(typo2.getId_Typology(), value_id);
        assertEquals(description, value_description);
    }

    @Test
    public void constructorTypologyTestNotNull_ID() {
        //Arrange
        ProjectSettings test = new ProjectSettings();
        Typology typo2 = new Typology("TestTypology2");
        Typology typo3 = new Typology("TestTypology3");
        Typology typo4 = new Typology("TestTypology4");
        Typology typo5 = new Typology("TestTypology5");
        Typology typo6 = new Typology("TestTypology6");
        //Act
        test.add(typo2);
        test.add(typo3);
        test.add(typo4);
        test.add(typo5);
        test.add(typo6);
        int id = typo5.getId_Typology();
        int value_id = 5;
        String description = typo3.getDescription();
        String value_description = "TestTypology3";
        //Assert
        assertEquals(id, value_id);
        assertEquals(description, value_description);
        assertEquals(7,test.getArrayTypology().size());

    }

    @Test
    public void getId_Typology(){

        //Arrange
        ProjectSettings proj = new ProjectSettings();
        Typology typo8 = new Typology("TestTypology8");
        //Act
        proj.saveTypology(typo8);
        int id = typo8.getId_Typology();
        //Assert
        assertEquals(2, id);
    }

    @Test
    public void getDescriptionTest(){
        //Arrange
        ProjectSettings proj = new ProjectSettings();
        //Act
        String descriptionTest = proj.getArrayTypology().get(1).getDescription();
        //Assert
        assertEquals(descriptionTest, "Time and Materials");

    }

    //Teste para mudar a descrição da Typology.
    @Test
    public void updateDescriptionTest() {
        //Arrange
        Typology typo8 = new Typology("TestTypology8");
        ArrayList<Typology> testList = new ArrayList<>();
        testList.add(typo8);
        typo8.updateDescription("Update Description Test");
        //Act
        int size = testList.size();
        Typology expected = new Typology("Update Description Test");
        //Assert
        assertEquals(size, 1);
        assertEquals(typo8.getDescription(),expected.getDescription());
        assertNotEquals(typo8.getId_Typology(),expected.getId_Typology());
    }

    @Test
    public void setDescriptionTestDescriptionEmpty() {
        //Arrange
        ProjectSettings proj = new ProjectSettings();
        Typology typo8 = new Typology("Test2");
        //Act
        typo8.updateDescription("");
        proj.saveTypology(typo8);
        //Assert
        assertFalse(proj.saveTypology(typo8));
    }

    @Test
    public void setDescriptionTestDescriptionEmpty2() {
        //Arrange
        ProjectSettings proj = new ProjectSettings();
        Typology typo8 = new Typology("");
        //Act
        proj.saveTypology(typo8);
        int id = typo8.getId_Typology();
        //Assert
        assertEquals(2, id);
        assertFalse(proj.saveTypology(typo8));
    }

    @Test
    public void equalsTest() {
        //Arrange
        ProjectSettings proj = new ProjectSettings();
        Typology typo = new Typology("Fixed Cost");
        boolean equals = typo.equals(proj.getTypologyById(0));
        //Assert
        assertTrue(equals);
    }
}
