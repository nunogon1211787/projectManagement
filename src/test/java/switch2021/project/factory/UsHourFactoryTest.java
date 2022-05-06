package switch2021.project.factory;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import switch2021.project.model.valueObject.UsHour;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
public class UsHourFactoryTest {

    @InjectMocks
    UsHourFactory usHourFactory;

    @Mock
    UsHour usHour;

    @Test
    @DisplayName("Test to create time estimate - with success")
    public void createTimeEstimateUsSuccess() {
        //Arrange
        double expected = 1.0;
        // Act
        usHour = usHourFactory.create(expected);
        //Assert
        assertEquals(expected, usHour.getUsHours());

    }
}
