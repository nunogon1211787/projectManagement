package switch2021.project.entities.valueObjects.voFactories;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import switch2021.project.entities.valueObjects.voFactories.voFactories.UsTitleFactory;
import switch2021.project.entities.valueObjects.vos.UsTitle;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
public class UsTitleFactoryTest {

    @Autowired
    UsTitleFactory usTitleFactory;

    @Test
    @DisplayName("Test to create user story title - with success")
    public void createUsTitleWithSuccess() {
        //Arrange
        String expected = "As a PO, i want to test this string";
        // Act
        UsTitle usTitle = usTitleFactory.create(expected);
        //Assert
        assertEquals(expected, usTitle.getTitleUs());

    }
}
