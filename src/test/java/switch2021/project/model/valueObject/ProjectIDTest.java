package switch2021.project.model.valueObject;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switch2021.project.model.Typology.Typology;

import static org.junit.jupiter.api.Assertions.*;

class ProjectIDTest {

    @Test
    public void shouldCreateAValidProjectCode() {
        //Arrange
        ProjectID code = new ProjectID(1);
        //Assert
        assertEquals("Project_2022_1", code.getCode());
    }

    @Test
    public void shouldCreateAValidDescriptionLengthLimit() {
        //Arrange
        assertThrows(IllegalArgumentException.class, () -> new ProjectID(0));
    }

    @Test
    @DisplayName("Test override conditions for coverage purposes")
    public void overrideTestNotEqual() {
        //Arrange
        ProjectID code = new ProjectID(1);
        ProjectID code2 = new ProjectID(2);
        //Assert
        assertNotEquals(code, code2);
    }

    @Test
    @DisplayName("Test override conditions for coverage purposes")
    public void overrideTestEqual() {
        //Arrange
        ProjectID code = new ProjectID(1);
        ProjectID code2 = new ProjectID(1);
        //Assert
        assertEquals(code, code2);
    }

    @Test
    @DisplayName("Test override conditions for coverage purposes")
    public void overrideTestENull() {
        //Arrange
        ProjectID code = new ProjectID(1);
        ProjectID code2 = null;
        //Assert
        assertNotEquals(code, code2);
    }

    @Test
    @DisplayName("Test override conditions for coverage purposes")
    public void overrideTestClass() {
        // Arrange
        ProjectID code = new ProjectID(1);
        Typology test = new Typology(new ID_Typology(new Description("Test")));
        //Assert
        assertNotEquals(code, test);
    }

    @Test
    public void hashCodeTestEqual() {
        // Arrange
        ProjectID code = new ProjectID(1);
        ProjectID code2 = new ProjectID(1);
        //Assert
        assertEquals(code.hashCode(), code2.hashCode());
    }

    @Test
    public void hashCodeTestNotEquals() {
        // Arrange
        ProjectID code = new ProjectID(1);
        ProjectID code2 = new ProjectID(2);
        //Assert
        assertNotEquals(code.hashCode(), code2.hashCode());
    }

}