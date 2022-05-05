package switch2021.project.factory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import switch2021.project.factoryInterface.IUsTitleFactory;
import switch2021.project.model.UserStory.UsTitle;
import static org.junit.jupiter.api.Assertions.assertEquals;


@ExtendWith(SpringExtension.class)
public class UsTitleFactoryTest {

    @Mock
    UsTitle usTitle;

    @Mock
    IUsTitleFactory iUsTitle;

    @InjectMocks
    UsTitleFactory usTitleFactory;

    @Test
    @DisplayName("Test to create user story title - with success")
    public void createUsTitleWithSuccess() {
        //Arrange
        String expected = "As a PO, i want to test this string";
//        when(usTitle.getTitleUs()).thenReturn(expected);
//        when(iUsTitle.create(expected)).thenReturn(usTitle);
//        when(usTitleFactory.create(expected)).thenReturn(usTitle);
        // Act
        usTitle = usTitleFactory.create(expected);
        //Assert
        assertEquals(expected, usTitle.getTitleUs());

    }
}
