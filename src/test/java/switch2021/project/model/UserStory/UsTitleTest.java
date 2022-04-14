package switch2021.project.model.UserStory;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switch2021.project.model.UserStory.UsTitle;

import static org.junit.jupiter.api.Assertions.*;

public class UsTitleTest {

    @Test
    @DisplayName("Validate that tile begins with As")
    public void checkBeginsWithAsA() {
        //Arrange
        String usTitle = "As a PO, i want to test this string";
        String usTitle2 = "as a PO, i want to test this string";
        //Act
        UsTitle expected = new UsTitle(usTitle);
        UsTitle expected2 = new UsTitle(usTitle2);
        //Assert
        assertEquals(expected.getTitleUs(), usTitle);
        assertNotNull(expected);
        assertEquals(expected2.getTitleUs(), usTitle2);
        assertNotNull(expected2);
    }

    @Test
    @DisplayName("Fail create title - dont begin with word as")
    public void usTitleDontBeginWithAs() {
        //Arrange
        String usTitle = "PO, i want to test this string";
        //Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new UsTitle(usTitle));
        //Assert
        assertEquals("Title need to begin with - as", exception.getMessage());
    }

    @Test
    @DisplayName("Fail create title - dont begin with word want")
    public void usTitleDontContainsWantWord() {
        //Arrange
        String usTitle = "As a PO, test this string";
        //Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new UsTitle(usTitle));
        //Assert
        assertEquals("Title don't contain the word want", exception.getMessage());
    }

    @Test
    @DisplayName("Fail create title - blank")
    public void usTitleBlank() {
        //Arrange
        String usTitle = "";
        //Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new UsTitle(usTitle));
        //Assert
        assertEquals("Title cannot be blank", exception.getMessage());
    }

}
