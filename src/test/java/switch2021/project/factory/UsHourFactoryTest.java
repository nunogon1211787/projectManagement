package switch2021.project.factory;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import switch2021.project.factoryInterface.IValueObjectsFactory;
import switch2021.project.model.UserStory.UsHour;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class UsHourFactoryTest {

    @InjectMocks
    UsHourFactory usHourFactory;



    @Test
    @DisplayName("Test to create time estimate - with success")
    public void createTimeEstimateUsSuccess() {

        UsHour usHour = usHourFactory.create(Double.valueOf(1.0));
        //Assert
        assertEquals(usHour.getUsHours(), 1.0);

    }
}
