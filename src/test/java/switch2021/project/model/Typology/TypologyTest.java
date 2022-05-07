package switch2021.project.model.Typology;

import org.junit.jupiter.api.Test;
import switch2021.project.model.valueObject.Description;
import switch2021.project.model.valueObject.TypologyID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class TypologyTest {

    @Test
    public void get_TypologyTest() {
        //Arrange
        Description des = mock(Description.class);
        TypologyID id = mock(TypologyID.class);
        Typology typo = new Typology(id);
        //Act
        when(id.getDescription()).thenReturn(des);
        when(des.getText()).thenReturn("Test");
        //Assert
        assertEquals("Test", typo.getId_description().getDescription().getText());
    }

    @Test
    public void hasID_DescriptionSuccess() {
        //Arrange
        TypologyID id = mock(TypologyID.class);
        Typology typo = new Typology(id);
        //Act
        when(id.hasDescription("Test")).thenReturn(true);
        //Assert
        assertTrue(typo.hasID_Description("Test"));
    }

    @Test
    public void hasID_DescriptionFail() {
        //Arrange
        TypologyID id = mock(TypologyID.class);
        Typology typo = new Typology(id);
        //Act
        when(id.hasDescription("Test")).thenReturn(false);
        //Assert
        assertFalse(typo.hasID_Description("Fail"));
    }

    @Test
    public void equalsTestSuccess() {
        //Arrange
        Description des = mock(Description.class);
        TypologyID id = mock(TypologyID.class);
        Typology typo = new Typology(id);
        Typology typo2 = new Typology(id);
        //Act
        when(id.getDescription()).thenReturn(des);
        when(des.getText()).thenReturn("Test");
        //Assert
        assertEquals(typo.toString(), typo2.toString());
    }

    @Test
    public void equalsTestFail() {
        //Arrange
        TypologyID id = mock(TypologyID.class);
        TypologyID id2 = mock(TypologyID.class);
        Typology typo = new Typology(id);
        Typology typo2 = new Typology(id2);
        //Assert
        assertNotEquals(typo, typo2);
    }

    @Test
    public void typologyClass() {
        //Arrange
        TypologyID id = mock(TypologyID.class);
        TypologyID id2 = mock(TypologyID.class);
        Typology typo = new Typology(id);
        Typology typo2 = new Typology(id2);
        //Assert
        assertEquals(typo.getClass(), typo2.getClass());
    }

    @Test
    public void typologyNull() {
        //Arrange
        TypologyID id = mock(TypologyID.class);
        Typology typo = new Typology(id);
        Typology typo2 = null;
        //Assert
        assertNotEquals(typo, typo2);
    }

    @Test
    public void hashCodeSuccess() {
        //Arrange
        TypologyID id = mock(TypologyID.class);
        Typology typo1 = new Typology(id);
        Typology typo2 = new Typology(id);
        //Act
        int t1 = typo1.hashCode();
        int t2 = typo2.hashCode();
        //Assert
        assertEquals(t1, t2);
    }

    @Test
    public void hashCodeFail() {
        //Arrange
        TypologyID id = mock(TypologyID.class);
        TypologyID id2 = mock(TypologyID.class);
        Typology typo1 = new Typology(id);
        Typology typo2 = new Typology(id2);
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
        TypologyID id = mock(TypologyID.class);
        TypologyID id2 = mock(TypologyID.class);
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
        TypologyID id = mock(TypologyID.class);
        TypologyID id2 = mock(TypologyID.class);
        //Act
        when(typo1.getId_description()).thenReturn(id);
        when(typo2.getId_description()).thenReturn(id2);
        when(id.sameValueAs(id2)).thenReturn(false);
        //Assert
        assertFalse(typo1.sameIdentityAs(typo2));
    }
}
