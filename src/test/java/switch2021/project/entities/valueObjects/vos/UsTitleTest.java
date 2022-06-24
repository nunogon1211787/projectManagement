package switch2021.project.entities.valueObjects.vos;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switch2021.project.entities.aggregates.Typology.Typology;
import switch2021.project.entities.valueObjects.vos.Description;
import switch2021.project.entities.valueObjects.vos.TypologyID;
import switch2021.project.entities.valueObjects.vos.UsTitle;

import static org.junit.jupiter.api.Assertions.*;

public class UsTitleTest {

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
    @DisplayName("Fail create title - don't begin with word as")
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
    @DisplayName("Test hasCode conditions for coverage purposes")
    public void hasCodeSuccess() {
        //Arrange
        UsTitle usTitle = new UsTitle("As a PO, i want to test this string");
        UsTitle usTitle2 = new UsTitle("As a PO, i want to test this string");
        //Act and Assert
        assertEquals(usTitle.hashCode(), usTitle2.hashCode());
    }

    @Test
    @DisplayName("Test hashcode conditions for coverage purposes")
    public void hashCodeFail() {
        //Arrange
        UsTitle usTitle = new UsTitle("As a PO, i want to test this string");
        UsTitle usTitle2 = new UsTitle("As a PO, i want to test");
        //Act and Assert
        assertNotEquals(usTitle.hashCode(), usTitle2.hashCode());
    }

    @Test
    @DisplayName("Test override conditions for coverage purposes")
    public void overrideTestNull() {
        //Arrange
        UsTitle usTitle = null;
        //Act and Assert
        assertNull(usTitle);
    }

    @Test
    @DisplayName("Test override conditions for coverage purposes")
    public void overrideTestFail() {
        //Arrange
        UsTitle usTitle = new UsTitle("As a PO, i want to test this string");
        Typology usTitle1 = new Typology(new TypologyID(new Description("Test")));
        //Act and Assert
        assertNotEquals(usTitle, usTitle1);
    }

    @Test
    @DisplayName("Test override conditions for coverage purposes")
    public void overrideTestFail_1() {
        //Arrange
        UsTitle usTitle = new UsTitle("As a PO, i want to test this string");
        UsTitle usTitle2 = new UsTitle("As a PO, i want to test this");
        //Act and Assert
        assertNotEquals(usTitle, usTitle2);
    }

    @Test
    @DisplayName("Test override conditions for coverage purposes")
    public void overrideTestSuccess() {
        //Arrange
        UsTitle usTitle = new UsTitle("As a PO, i want to test this string");
        UsTitle usTitle2 = new UsTitle("As a PO, i want to test this string");
        //Act and Assert
        assertEquals(usTitle, usTitle2);
    }

    @Test
    @DisplayName("Test override conditions for coverage purposes")
    public void overrideTest() {
        //Arrange
        UsTitle usTitle = new UsTitle("As a PO, i want to test this string");
        //Act and Assert
        assertEquals(usTitle, usTitle);
    }

    @Test
    @DisplayName("Test override conditions for coverage purposes")
    public void sameValueOf() {
        //Arrange
        UsTitle usTitle = new UsTitle("As a PO, i want to test this string");
        UsTitle usTitle2 = new UsTitle("As a PO, i want to test");
        //Act and Assert
        assertFalse(usTitle.sameValueAs(usTitle2));
    }
}
