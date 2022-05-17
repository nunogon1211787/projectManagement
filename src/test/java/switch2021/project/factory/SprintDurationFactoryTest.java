package switch2021.project.factory;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import switch2021.project.model.valueObject.SprintDuration;
import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
public class SprintDurationFactoryTest {

    @Autowired
    SprintDurationFactory sprintDurationFactory;

    @Test
    @DisplayName("Test to create sprintDuration in days - with success")
    public void createSprintDurationSuccess() {
        //Arrange
        int expected = 8;
        //Act
        SprintDuration sprintDuration = sprintDurationFactory.create(expected);
        //Assert
        assertEquals(expected, sprintDuration.getSprintDurationDays());
    }
}