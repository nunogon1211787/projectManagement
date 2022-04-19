package switch2021.project.model.valueObject;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class NumberOfSprintsTest {

    @Test
    @DisplayName("Validate that the number of sprints are less than 0")
    public void checkNumberOfSprintsNegative() {

        //Act
        assertThrows(IllegalArgumentException.class, () -> {
            //Arrange
            int nrSprints = -1;

            //Assert
            new NumberOfSprints(nrSprints);
        });
    }

    @Test
    @DisplayName("Validate that the number of sprints are equal to 0")
    public void checkNumberOfSprintsZero() {

        //Act
        assertThrows(IllegalArgumentException.class, () -> {
            //Arrange
            int nrSprints = 0;

            //Assert
            new NumberOfSprints(nrSprints);
        });

    }

    @Test
    @DisplayName("Validate that the number of sprints")
    public void numberSprintsSuccess() {
        //Arrange
        int nrSprints = 5;
        //Act
        NumberOfSprints sprintsSuccess = new NumberOfSprints(nrSprints);
        //Arrange
        assertEquals(nrSprints, sprintsSuccess.getNumberOfSprintsVO());
    }

}

