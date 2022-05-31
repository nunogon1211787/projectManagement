package switch2021.project.entities.valueObjects;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switch2021.project.entities.valueObjects.vos.Attachment;

import static org.junit.jupiter.api.Assertions.*;

public class AttachmentTest {


    @Test
    @DisplayName("Validate that attachment extension fail - not allowed")
    public void attachmentWithExtensionNotAllowed() {
        //Assert
        assertThrows(IllegalArgumentException.class, () -> {
            //Arrange
            String filename = "test.xxx";
            //Act
            new Attachment(filename);
        });
    }

    @Test
    @DisplayName("Validate that attachment extension is correct")
    public void attachmentWithCorrectExtension() {
        //Arrange
        String filename = "test.pdf";
        //Act
        Attachment attachment = new Attachment(filename);
        //Assert
        assertEquals(filename, attachment.getExtension());
    }

    @Test
    @DisplayName("Validate that attachment extension is correct")
    public void attachmentWithCorrectExtensionAnotherExtension() {
        //Arrange
        String filename = "test.txt";
        //Act
        Attachment attachment = new Attachment(filename);
        //Assert
        assertEquals(filename, attachment.getExtension());
        assertNotNull(attachment);
    }

}

