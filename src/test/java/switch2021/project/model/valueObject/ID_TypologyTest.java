package switch2021.project.model.valueObject;

import org.junit.jupiter.api.Test;
import switch2021.project.model.Typology.Typology;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ID_TypologyTest {

    @Test
    public void getDescription_Success() {
        //Arrange
        ID_Typology id = mock(ID_Typology.class);
        Description des = mock(Description.class);
        //Act
        when(des.getText()).thenReturn("Test");
        when(id.getDescription()).thenReturn(des);
        Description id_des = id.getDescription();
        String expected_des = "Test";
        //Assert
        assertEquals(expected_des, id_des.getText());
    }

    @Test
    //Verify if that test is correct <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    public void hasDescriptionSuccess() {
        //Arrange
        ID_Typology id = mock(ID_Typology.class);
        //Act
        when(id.hasDescription("Test")).thenReturn(true);
        //Assert
        assertTrue(id.hasDescription("Test"));
    }

    @Test
    //Verify if that test is correct <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    public void hasDescriptionFail() {
        //Arrange
        ID_Typology id = mock(ID_Typology.class);
        //Act
        when(id.hasDescription("Test")).thenReturn(false);
        //Assert
        assertFalse(id.hasDescription("Test"));
    }

    @Test
    public void equalsSuccess() {
        //Arrange
        ID_Typology id = new ID_Typology("Test");
        ID_Typology expected = new ID_Typology("Test");
        //Act and Assert
        assertEquals(expected, id);
    }

    @Test
    public void equalsFail() {
        //Arrange
        ID_Typology id = new ID_Typology("Test");
        ID_Typology expected = new ID_Typology("Test1");
        //Act and Assert
        assertNotEquals(expected, id);
    }

    @Test
    public void ClassFail() {
        //Arrange
        ID_Typology id = new ID_Typology("Test");
        Typology expected = new Typology("Test1");
        //Act and Assert
        assertNotEquals(expected, id);
    }

    @Test
    public void EqualsNullFail() {
        //Arrange
        ID_Typology id = new ID_Typology("Test");
        Typology expected = null;
        //Act and Assert
        assertNotEquals(expected, id);
    }

    @Test
    public void hashSuccess() {
        //Arrange
        ID_Typology id = new ID_Typology("Test");
        ID_Typology expected = new ID_Typology("Test");
        //Act
        int hash_id = id.hashCode();
        int hash_exp = expected.hashCode();
        // Assert
        assertEquals(hash_exp, hash_id);
    }

    @Test
    public void hashFail() {
        //Arrange
        ID_Typology id = new ID_Typology("Test");
        ID_Typology expected = new ID_Typology("Test1");
        //Act
        int hash_id = id.hashCode();
        int hash_exp = expected.hashCode();
        // Assert
        assertNotEquals(hash_exp, hash_id);
    }

    @Test
    public void sameValueAs_True() {
        //Arrange
        ID_Typology id = new ID_Typology("Test");
        ID_Typology expected = new ID_Typology("Test");
        Description des = mock(Description.class);
        //Act
        when(des.getText()).thenReturn("Test");
        //Assert
        assertTrue(id.sameValueAs(expected));
    }

    @Test
    public void sameValueAs_False() {
        //Arrange
        ID_Typology id = new ID_Typology("Test");
        ID_Typology expected = mock(ID_Typology.class);
        Description des_ex = mock(Description.class);
        //Act
        when(expected.getDescription()).thenReturn(des_ex);
        when(des_ex.getText()).thenReturn("Fail");
        //Assert
        assertFalse(id.sameValueAs(expected));
    }
}