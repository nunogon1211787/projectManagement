package switch2021.project.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switch2021.project.stores.TypologyStore;

import static org.junit.jupiter.api.Assertions.*;

public class TypologyTest {

    /**
     * Constructor´s Tests
     */

    @DisplayName("Constructor Test fail - Description empty")
    @Test
    public void constructorTypologyTestNull() {
        //Assert
        assertThrows (IllegalArgumentException.class, () -> {
            //Arrange and Act
            new Typology("");
        });
    }
    @DisplayName("Constructor Test fail - Description too small 2 characters")
    @Test
    public void constructorTypologyTest2char() {
        //Assert
        assertThrows (IllegalArgumentException.class, () -> {
            //Arrange and Act
            new Typology("df");
        });
    }

    @DisplayName("Constructor Test Success - Description 3 characters")
    @Test
    public void constructorTypologyTestNotNull() {
        //Arrange and Act
        Typology typo = new Typology("Fix");
        String description = typo.getDescription();
        //Assert
        assertEquals("Fix",description);
    }

    @DisplayName("Get Typology test - id zero")
    @Test
    public void getId_TypologyZeroTest() {
        //Arrange
        Typology typo = new Typology("Teste");
        //Assert
        assertEquals(0,typo.getIdTypology());
    }

    @DisplayName("Set id Test Success")
    @Test
    public void setId_TypologyTest() {
        //Arrange
        Typology typo = new Typology("Teste");
        //Act
        typo.setIdTypology(3);
        //Assert
        assertEquals(3,typo.getIdTypology());
    }


    @Test
    public void equalsTestSuccess() {
        //Arrange
        Company company = new Company();
        TypologyStore typologyStore = company.getTypologyStore();
        Typology typo = new Typology("Fixed Cost");
        Typology typo2 = typologyStore.getTypology("Fixed Cost");
        //Assert
        assertEquals(typo, typo2);
    }

    @Test
    public void equalsTestFail() {
        //Arrange
        Company company = new Company();
        TypologyStore typologyStore = company.getTypologyStore();
        Typology typo = new Typology("Test");
        Typology typo2 = typologyStore.getTypology("Fixed Cost");

        //Assert
        assertNotEquals(typo, typo2);
    }

    @Test
    public void typologyClass() {
        //Arrange
        Typology typo = new Typology("Fixed Cost");
        Typology typo2 = new Typology("Fixed Cost2");
        assertEquals(typo.getClass(),typo2.getClass());
    }

    @Test
    public void typologyNull() {
        //Arrange
        Typology typo = new Typology("Fixed Cost");
        Typology typo2 = null;
        assertNotEquals(typo,typo2);
    }
}
