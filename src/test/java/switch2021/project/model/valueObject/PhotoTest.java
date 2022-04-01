package switch2021.project.model.valueObject;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PhotoTest {

    @Test
    @DisplayName("Validate that photo extension fail")
    public void photoWithExtensionNotAllowed() {
        //Assert
        assertThrows(IllegalArgumentException.class, () -> {
            //Arrange
            String photo = "photo.xxx";
            //Act
            new Photo(photo);
        });
    }

    @Test
    @DisplayName("Validate that photo has a correct extension")
    public void photoWithCorrectExtension_JPG() {
        //Arrange
        String photo = "photo.jpg";
        //Act
        Photo x = new Photo(photo);
        //Assert
        assertEquals(photo, x.getExtension());
    }

    @Test
    @DisplayName("Validate that photo has a correct extension")
    public void photoWithCorrectExtension_PNG() {
        //Arrange
        String photo = "photo.png";
        //Act
        Photo y = new Photo(photo);
        //Assert
        assertEquals(photo, y.getExtension());
    }

    @Test
    @DisplayName("Validate that attachment extension is correct")
    public void photoWithNullExtension() {
        //Arrange
        String photo = "photo.jpg";
        //Act
        Photo x = new Photo(photo);
        //Assert
        assertNotNull(x);
    }

}
