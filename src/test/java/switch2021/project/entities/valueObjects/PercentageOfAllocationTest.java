package switch2021.project.entities.valueObjects;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switch2021.project.entities.valueObjects.vos.PercentageOfAllocation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PercentageOfAllocationTest {

    @Test
    @DisplayName("Validate that percentage is correct - limit bound")
    public void percentageSuccessLimit1() {
        //Arrange
        double percentage = 0.0;
        //Act
        PercentageOfAllocation percOfAllo = new PercentageOfAllocation(percentage);
        //Assert
        assertEquals(percentage, percOfAllo.getPercentage());
    }

    @Test
    @DisplayName("Validate that percentage is correct - limit bound")
    public void percentageSuccessLimit2() {
        //Arrange
        double percentage = 1.0;
        //Act
        PercentageOfAllocation percOfAllo = new PercentageOfAllocation(percentage);
        //Assert
        assertEquals(percentage, percOfAllo.getPercentage());
    }

    @Test
    @DisplayName("Validate that percentage is correct - other values")
    public void percentageSuccessInBound() {
        //Arrange
        double percentage = 0.3;
        //Act
        PercentageOfAllocation percOfAllo = new PercentageOfAllocation(percentage);
        //Assert
        assertEquals(percentage, percOfAllo.getPercentage());
    }

    @Test
    @DisplayName("Validate that percentage is correct - fail")
    public void percentageFailOutBoundNegative() {
        //Assert
        assertThrows(IllegalArgumentException.class, () -> {
            //Arrange
            double percentage = -1;
            //Act
            new PercentageOfAllocation(percentage);
        });
    }

    @Test
    @DisplayName("Validate that percentage is correct - fail")
    public void percentageFailOutBoundMoreThanOne() {
        //Assert
        assertThrows(IllegalArgumentException.class, () -> {
            //Arrange
            double percentage = 1.1;
            //Act
            new PercentageOfAllocation(percentage);
        });
    }

}
