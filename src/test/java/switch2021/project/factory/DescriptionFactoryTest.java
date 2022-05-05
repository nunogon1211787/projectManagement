package switch2021.project.factory;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import switch2021.project.model.valueObject.Description;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
public class DescriptionFactoryTest {

    @InjectMocks
    DescriptionFactory descriptionFactory;

    @Mock
    Description description;

    @Test
    @DisplayName("Test to create a description - success")
    public void createDescription_Success() {
        //Arrange
        String x = "Description";
        // Act
        description = descriptionFactory.create(x);
        //Assert
        assertEquals(x, description.getText());
    }

}
