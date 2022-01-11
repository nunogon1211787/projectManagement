package switch2021.project.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

public class TypologyTest {

    private Company company;
    private TypologyStore typologyStore;



    @BeforeEach
    public void initialize() {
        //Arrange
        company = new Company();
        typologyStore = company.getTypologyStore();
    }


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
        Typology typo2 = new Typology("TestTypology");
        //Act
        typologyStore.add(typo2);
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
        Typology typo2 = new Typology("TestTypology2");
        Typology typo3 = new Typology("TestTypology3");
        Typology typo4 = new Typology("TestTypology4");
        Typology typo5 = new Typology("TestTypology5");
        Typology typo6 = new Typology("TestTypology6");
        //Act
        typologyStore.add(typo2);
        typologyStore.add(typo3);
        typologyStore.add(typo4);
        typologyStore.add(typo5);
        typologyStore.add(typo6);
        int id = typo5.getId_Typology();
        int value_id = 5;
        String description = typo3.getDescription();
        String value_description = "TestTypology3";
        //Assert
        assertEquals(id, value_id);
        assertEquals(description, value_description);
        assertEquals(7,typologyStore.getTypologyList().size());

    }

    @Test
    public void getId_Typology(){

        //Arrange
        Typology typo8 = new Typology("TestTypology8");
        //Act
        typologyStore.saveTypology(typo8);
        int id = typo8.getId_Typology();
        //Assert
        assertEquals(2, id);
    }

    @Test
    public void getDescriptionTest(){
        //Arrange
        //Act
        String descriptionTest = typologyStore.getTypologyList().get(1).getDescription();
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
        Typology typo8 = new Typology("Test2");
        //Act
        typo8.updateDescription("");
        typologyStore.saveTypology(typo8);
        //Assert
        assertFalse(typologyStore.saveTypology(typo8));
    }

    @Test
    public void setDescriptionTestDescriptionEmpty2() {
        //Arrange
        Typology typo8 = new Typology("");
        //Act
        typologyStore.saveTypology(typo8);
        int id = typo8.getId_Typology();
        //Assert
        assertEquals(2, id);
        assertFalse(typologyStore.saveTypology(typo8));
    }

    @Test
    public void equalsTest() {
        //Arrange
        Typology typo = new Typology("Fixed Cost");
        boolean equals = typo.equals(typologyStore.getTypologyById(0));
        //Assert
        assertTrue(equals);
    }
}
