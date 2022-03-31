package switch2021.project.model.valueObject;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SprintDurationTest {

    @Test
    void getSprintDurationDays() {
        SprintDuration duration = new SprintDuration(14);

        assertEquals(14, duration.getSprintDurationDays());
    }

    @Test
    void getMIN() {
        SprintDuration duration = new SprintDuration(14);

        assertEquals(1, duration.getMIN());
    }

    @Test
    void getMAX() {
        SprintDuration duration = new SprintDuration(14);

        assertEquals(31, duration.getMAX());
    }

    @Test
    void shouldTrowNullException() {
        assertThrows(NullPointerException.class, () -> {

        new SprintDuration(35);

        });

    }

    @Test
    void overrideTestDifferentClass() {
        // Arrange
        SprintDuration duration = new SprintDuration(15);
        Typology test = new Typology("test");
        //Assert
        assertNotEquals(duration, test);

    }

    @Test
    void overrideTestEqualsObject() {
        // Arrange
        SprintDuration duration = new SprintDuration(15);
        SprintDuration duration2 = new SprintDuration(15);
        //Assert
        assertEquals(duration, duration2);

    }

    @Test
    void overrideTestSameObject() {
        // Arrange
        SprintDuration duration = new SprintDuration(15);
        //Assert
        assertEquals(duration, duration);

    }

    @Test
    void overrideTestNotEqualsObject() {
        // Arrange
        SprintDuration duration = new SprintDuration(15);
        SprintDuration duration2 = new SprintDuration(16);
        //Assert
        assertNotEquals(duration, duration2);

    }

    @Test
    void overrideTestNullObject() {
        // Arrange
        SprintDuration duration = new SprintDuration(15);
        SprintDuration duration2 = null;
        //Assert
        assertNotEquals(duration, duration2);

    }

    @Test
    public void hashCodeTestSuccess() {
        // Arrange
        SprintDuration duration = new SprintDuration(15);
        SprintDuration duration2 = new SprintDuration(15);
        //Assert
        assertEquals(duration.hashCode(), duration2.hashCode());
    }

    @Test
    public void hashCodeTestFail() {
        // Arrange
        // Arrange
        SprintDuration duration = new SprintDuration(15);
        SprintDuration duration2 = new SprintDuration(16);
        //Assert
        assertNotEquals(duration.hashCode(), duration2.hashCode());
    }
}