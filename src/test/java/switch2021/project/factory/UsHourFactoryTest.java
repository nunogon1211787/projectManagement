package switch2021.project.factory;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import switch2021.project.model.valueObject.UsHour;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class UsHourFactoryTest {

    @Autowired
    UsHourFactory usHourFactory;

    @Test
    @DisplayName("Test to create time estimate - with success")
    public void createTimeEstimateUsSuccess() {
        //Arrange
        double expected = 1.0;
        // Act
        UsHour usHour = usHourFactory.create(expected);
        //Assert
        assertEquals(expected, usHour.getUsHours());

    }
}
