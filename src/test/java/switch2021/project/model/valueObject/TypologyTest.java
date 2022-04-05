package switch2021.project.model.valueObject;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switch2021.project.model.Typology.Typology;

import static org.junit.jupiter.api.Assertions.*;

public class TypologyTest {

    @DisplayName("Get Typology test")
    @Test
    public void get_TypologyTest() {
        //Arrange
        Typology typo = new Typology("Teste");
        String desc = typo.getDescription().getText();
        //Assert
        assertEquals("Teste", desc);
    }

    @Test
    public void equalsTestSuccess() {
        //Arrange
        Typology typo = new Typology("Teste");
        Typology typo2 = new Typology("Teste");
        //Assert
        assertEquals(typo, typo2);
    }

    @Test
    public void equalsTestFail() {
        //Arrange
        Typology typo = new Typology("Teste");
        Typology typo2 = new Typology("Fixed Cost");
        //Assert
        assertNotEquals(typo, typo2);
    }
        @Test
    public void hashCodes() {
        //Arrange
        Typology typo = new Typology("Fixed Cost");
        Typology typo2 = new Typology("Fixed Cost2");
        Typology typo3 = new Typology("Fixed Cost2");
        //Assert
        assertNotEquals(typo,typo2);
        assertEquals(typo2,typo3);
    }

    @Test
    public void typologyClass() {
        //Arrange
        Typology typo = new Typology("Fixed Cost");
        Typology typo2 = new Typology("Fixed Cost2");

        //Assert
        assertEquals(typo.getClass(),typo2.getClass());
    }

    @Test
    public void typologyNull() {
        //Arrange
        Typology typo = new Typology("Fixed Cost2");
        Typology typo2 = null;
        //Assert
        assertNotEquals(typo,typo2);
    }
}
