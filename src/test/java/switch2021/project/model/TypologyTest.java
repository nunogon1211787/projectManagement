package switch2021.project.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import switch2021.project.stores.TypologyStore;

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
        //Assert
        assertThrows (IllegalArgumentException.class, () -> {
            //Arrange and Act
            new Typology("");
        });
    }

    @Test
    public void constructorTypologyTestNotNull() {
        //Arrange and Act
        Typology typo = new Typology("Fixed Cost");
        //Assert
        assertEquals(typo,typologyStore.getTypology(1));
    }

    @Test
    public void getId_TypologyZeroTest() {
        //Arrange
        Typology typo = new Typology("Teste");
        //Assert
        assertEquals(0,typo.getId_Typology());
    }

    @Test
    public void setId_TypologyTest() {
        //Arrange
        Typology typo = new Typology("Teste");
        //Act
        typo.setId_Typology(3);
        //Assert
        assertEquals(3,typo.getId_Typology());
    }

    @Test
    public void equalsTest() {
        //Arrange
        Typology typo = new Typology("Fixed Cost");
        boolean equals = typo.equals(typologyStore.getTypology("Fixed Cost"));
        //Assert
        assertTrue(equals);
    }
}
