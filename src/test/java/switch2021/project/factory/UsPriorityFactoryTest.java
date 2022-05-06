package switch2021.project.factory;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import switch2021.project.model.valueObject.UsPriority;

import static org.junit.jupiter.api.Assertions.assertEquals;


@ExtendWith(SpringExtension.class)
public class UsPriorityFactoryTest {

    @Mock
    UsPriority usPriority;

    @InjectMocks
    UsPriorityFactory usPriorityFactory;

    @Test
    @DisplayName("Test to create user story priority - with success")
    public void createUsPrioritySuccess(){
        //Arrange
        int expected = 5;
        // Act
        usPriority = usPriorityFactory.create(expected);
        //Assert
        assertEquals(expected, usPriority.getPriorityUs());

    }
}
