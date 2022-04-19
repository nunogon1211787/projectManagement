package switch2021.project.model.Typology;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switch2021.project.model.valueObject.Description;
import switch2021.project.model.valueObject.ID_Typology;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class TypologyTest {

    @DisplayName("Get Typology test")
    @Test
    public void get_TypologyTest() {
        //Arrange
        Typology typo = new Typology("Test");
        ID_Typology id = mock(ID_Typology.class);
        Description des = mock(Description.class);
        //Act
        when(id.getDescription()).thenReturn(des);
        when(des.getText()).thenReturn("Test");
        //Assert
        assertEquals("Test", typo.getId_description().getDescription().getText());
    }

    @Test
    public void hasID_DescriptionSuccess() {
        //Arrange
        Typology typo = new Typology("Test");
        ID_Typology id = mock(ID_Typology.class);
        //Act
        when(id.hasDescription("Test")).thenReturn(true);
        //Assert
        assertTrue(typo.hasID_Description("Test"));
    }

    @Test
    public void hasID_DescriptionFail() {
        //Arrange
        Typology typo = new Typology("Test");
        ID_Typology id = mock(ID_Typology.class);
        //Act
        when(id.hasDescription("Test")).thenReturn(false);
        //Assert
        assertFalse(typo.hasID_Description("Fail"));
    }

    @Test
    public void equalsTestSuccess() {
        //Arrange
        Typology typo = new Typology("Test");
        Typology typo2 = new Typology("Test");
        //Assert
        assertTrue(typo.sameIdentityAs(typo2));
    }

    @Test
    public void equalsTestFail() {
        //Arrange
        Typology typo = new Typology("Test");
        Typology typo2 = new Typology("Fixed Cost");
        //Assert
        assertNotEquals(typo, typo2);
    }

    @Test
    public void typologyClass() {
        //Arrange
        Typology typo = new Typology("Fixed Cost");
        Typology typo2 = new Typology("Fixed Cost2");

        //Assert
        assertEquals(typo.getClass(), typo2.getClass());
    }

    @Test
    public void typologyNull() {
        //Arrange
        Typology typo = new Typology("Fixed Cost2");
        Typology typo2 = null;
        //Assert
        assertNotEquals(typo, typo2);
    }

    @Test
    public void hashCodeSuccess() {
        //Arrange
        Typology typo1 = new Typology("Fixed Cost");
        Typology typo2 = new Typology("Fixed Cost");
        //Act
        int t1 = typo1.hashCode();
        int t2 = typo2.hashCode();
        //Assert
        assertEquals(t1, t2);
    }

    @Test
    public void hashCodeFail() {
        //Arrange
        Typology typo1 = new Typology("Fixed Cost");
        Typology typo2 = new Typology("Fixed Cost2");
        //Act
        int t1 = typo1.hashCode();
        int t2 = typo2.hashCode();
        //Assert
        assertNotEquals(t1, t2);
    }

    @Test
    public void sameIdentityAs_true() {
        //Arrange
        Typology typo1 = mock(Typology.class);
        Typology typo2 = mock(Typology.class);
        ID_Typology id = mock(ID_Typology.class);
        ID_Typology id2 = mock(ID_Typology.class);
        //Act
        when(typo1.getId_description()).thenReturn(id);
        when(typo2.getId_description()).thenReturn(id2);
        when(id.sameValueAs(id2)).thenReturn(true);
        //Assert
        assertFalse(typo1.sameIdentityAs(typo2));
    }

    @Test
    public void sameIdentityAs_false() {
        //Arrange
        Typology typo1 = mock(Typology.class);
        Typology typo2 = mock(Typology.class);
        ID_Typology id = mock(ID_Typology.class);
        ID_Typology id2 = mock(ID_Typology.class);
        //Act
        when(typo1.getId_description()).thenReturn(id);
        when(typo2.getId_description()).thenReturn(id2);
        when(id.sameValueAs(id2)).thenReturn(false);
        //Assert
        assertFalse(typo1.sameIdentityAs(typo2));
    }
}
