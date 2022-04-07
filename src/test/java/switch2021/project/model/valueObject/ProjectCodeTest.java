package switch2021.project.model.valueObject;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switch2021.project.model.Typology.Typology;

import static org.junit.jupiter.api.Assertions.*;

class ProjectCodeTest {

    @Test
    public void shouldCreateAValidProjectCode() {
        //Arrange
        ProjectCode code = new ProjectCode(1);
        //Assert
        assertEquals("Project_2022_1", code.getCode());
    }

    @Test
    public void shouldCreateAValidDescriptionLengthLimit() {
        //Arrange
        assertThrows(IllegalArgumentException.class, () -> new ProjectCode(0));
    }

    @Test
    @DisplayName("Test override conditions for coverage purposes")
    public void overrideTestNotEqual() {
        //Arrange
        ProjectCode code = new ProjectCode(1);
        ProjectCode code2 = new ProjectCode(2);
        //Assert
        assertNotEquals(code, code2);
    }

    @Test
    @DisplayName("Test override conditions for coverage purposes")
    public void overrideTestEqual() {
        //Arrange
        ProjectCode code = new ProjectCode(1);
        ProjectCode code2 = new ProjectCode(1);
        //Assert
        assertEquals(code, code2);
    }

    @Test
    @DisplayName("Test override conditions for coverage purposes")
    public void overrideTestENull() {
        //Arrange
        ProjectCode code = new ProjectCode(1);
        ProjectCode code2 = null;
        //Assert
        assertNotEquals(code, code2);
    }

    @Test
    @DisplayName("Test override conditions for coverage purposes")
    public void overrideTestClass() {
        // Arrange
        ProjectCode code = new ProjectCode(1);
        Typology test = new Typology("test");
        //Assert
        assertNotEquals(code, test);
    }

    @Test
    public void hashCodeTestEqual() {
        // Arrange
        ProjectCode code = new ProjectCode(1);
        ProjectCode code2 = new ProjectCode(1);
        //Assert
        assertEquals(code.hashCode(), code2.hashCode());
    }

    @Test
    public void hashCodeTestNotEquals() {
        // Arrange
        ProjectCode code = new ProjectCode(1);
        ProjectCode code2 = new ProjectCode(2);
        //Assert
        assertNotEquals(code.hashCode(), code2.hashCode());
    }

}