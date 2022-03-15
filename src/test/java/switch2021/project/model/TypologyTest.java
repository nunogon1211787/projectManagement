package switch2021.project.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switch2021.project.Immutables.Description;
import switch2021.project.stores.TypologyStore;

import static org.junit.jupiter.api.Assertions.*;

public class TypologyTest {

    /** Constructor´s Tests **/

//    @DisplayName("Constructor Test fail - Description empty")
//    @Test
//    public void constructorTypologyTestNull() {
//        //Assert
//        assertThrows (IllegalArgumentException.class, () -> {
//            //Arrange and Act
//            new Typology(new Description("");
//        });
//    }
//    @DisplayName("Constructor Test fail - Description too small 2 characters")
//    @Test
//    public void constructorTypologyTest2char() {
//        //Assert
//        assertThrows (IllegalArgumentException.class, () -> {
//            //Arrange and Act
//            new Typology("df");
//        });
//    }

    @DisplayName("Constructor Test Success - Description 3 characters")
//    @Test
//    public void constructorTypologyTestNotNull() {
//        //Arrange and Act
//        Typology typo = new Typology("Fix");
//        String description = typo.getDescription();
//        //Assert
//        assertEquals("Fix",description);
//    }

//    @DisplayName("Get Typology test - id zero")
    @Test
    public void getId_TypologyZeroTest() {
        //Arrange
        Typology typo = new Typology(new Description("Teste"));
        int id = typo.getIdTypology();
        //Assert
        assertEquals(0, id);
    }

    @DisplayName("Set id Test Success")
    @Test
    public void setId_TypologyTest() {
        //Arrange
        Typology typo = new Typology(new Description("Teste"));
        //Act
        typo.setIdTypology(3);
        int id = typo.getIdTypology();
        //Assert
        assertEquals(3,id);
    }


    @Test
    public void equalsTestSuccess() {
        //Arrange
        Typology typo = new Typology(new Description("Fixed Cost"));
        Typology typo2 = new Typology(new Description("Fixed Cost"));
        //Assert
        assertEquals(typo, typo2);
    }

    @Test
    public void equalsTestFail() {
        //Arrange
        Typology typo = new Typology(new Description("Test"));
        Typology typo2 = new Typology(new Description("Fixed Cost"));
        //Assert
        assertNotEquals(typo, typo2);
    }
        @Test
    public void hashCodes() {
        //Arrange
        Typology typo = new Typology(new Description("Fixed Cost"));
        Typology typo2 = new Typology(new Description("Fixed Cost2"));
        Typology typo3 = new Typology(new Description("Fixed Cost2"));
        //Assert
        assertNotEquals(typo,typo2);
        assertEquals(typo2,typo3);
    }

    @Test
    public void typologyClass() {
        //Arrange
        Typology typo = new Typology(new Description("Fixed Cost"));
        Typology typo2 = new Typology(new Description("Fixed Cost2"));

        //Assert
        assertEquals(typo.getClass(),typo2.getClass());
    }

    @Test
    public void typologyNull() {
        //Arrange
        Typology typo = new Typology(new Description("Fixed Cost2"));
        Typology typo2 = null;
        //Assert
        assertNotEquals(typo,typo2);
    }
}
