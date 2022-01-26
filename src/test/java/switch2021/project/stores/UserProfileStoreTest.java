package switch2021.project.stores;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switch2021.project.model.UserProfile;
import switch2021.project.stores.UserProfileStore;

import static org.junit.jupiter.api.Assertions.*;


public class UserProfileStoreTest {

    /**
     * >>>>>> Testes de UserProfile <<<<<<
     **/

    // Test add UserProfile in company (Cris US013)
    @Test
    @DisplayName("Return exception message, no name for profile inserted")
    public void createNewUserProfileWithFailNameEmpty() {
        //Arrange
        UserProfileStore userProfileStore = new UserProfileStore();
        String name = "";

        // Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            UserProfile up = userProfileStore.createProfile(name);
            userProfileStore.saveUserProfile(up);
        });
        //Assert
        assertTrue(exception.getMessage().contains("Name cannot be blank."));
    }

    @Test
    @DisplayName("Create new profile with success")
    public void createNewUserProfileWithSuccess() {
        //Arrange
        UserProfileStore userProfileStore = new UserProfileStore();
        String name = "Cris";
        // Act
        UserProfile up = userProfileStore.createProfile(name);
        //Assert
        assertEquals("Cris", up.getUserProfileName());
    }

    @Test
    @DisplayName("Return exception message, profile already exist")
    public void createNewUserProfileAlreadyExist() {
        //Arrange
        UserProfileStore userProfileStore = new UserProfileStore();
        String name = "Cris";
        UserProfile up = userProfileStore.createProfile(name);
        UserProfile up1 = userProfileStore.createProfile(name);
        // Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            userProfileStore.saveUserProfile(up);
            userProfileStore.saveUserProfile(up1);
        });
        //Assert
        assertTrue(exception.getMessage().contains("Repeated user profile name inserted."));
    }


    @Test
    @DisplayName("Return exception message, profile already exist")
    public void saveNewUserProfileAlreadyExist() {
        //Arrange
        UserProfileStore userProfileStore = new UserProfileStore();
        String name = "Cris";
        UserProfile up = userProfileStore.createProfile(name);
        UserProfile up1 = userProfileStore.createProfile(name);
        // Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            userProfileStore.saveUserProfile(up);
            userProfileStore.saveUserProfile(up1);
        });
        //Assert
        assertTrue(exception.getMessage().contains("Repeated user profile name inserted."));
    }

    @Test
    @DisplayName("Create new profile with success")
    public void saveNewUserProfileWithSuccess() {
        //Arrange
        UserProfileStore userProfileStore = new UserProfileStore();
        String name = "Cris";
        UserProfile up = userProfileStore.createProfile(name);
        userProfileStore.saveUserProfile(up);
        // Act
        int inicialSize = userProfileStore.getUserProfileList().size();
        UserProfile up1 = userProfileStore.createProfile("Cris_Dani");
        userProfileStore.saveUserProfile(up1);
        //Assert
        assertEquals(userProfileStore.getUserProfileList().size(), inicialSize + 1);
    }


    @Test
    @DisplayName("Create new profile with success")
    public void saveNewUserProfileWithSuccess2() {
        //Arrange
        UserProfileStore userProfileStore = new UserProfileStore();
        String name = "Cris";
        UserProfile up = userProfileStore.createProfile(name);
        userProfileStore.saveUserProfile(up);
        // Act
        int inicialSize = userProfileStore.getUserProfileList().size();
        UserProfile up1 = userProfileStore.createProfile("Cris_Dani");
        UserProfile up2 = userProfileStore.createProfile("Cris_Dani2");
        userProfileStore.saveUserProfile(up1);
        userProfileStore.saveUserProfile(up2);
        //Assert
        assertEquals(userProfileStore.getUserProfileList().size(), inicialSize + 2);
    }

    @Test
    @DisplayName("Return exception message, no name for profile inserted")
    public void saveNewUserProfileWithFailNameEmpty() {
        //Arrange
        UserProfileStore userProfileStore = new UserProfileStore();
        String name = "    ";

        // Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            UserProfile up = userProfileStore.createProfile(name);
            userProfileStore.saveUserProfile(up);
        });
        //Assert
        assertTrue(exception.getMessage().contains("Name cannot be blank."));
    }


    @Test
    public void addNewUserProfileAlreadyExist() {
        //Arrange
        UserProfileStore userProfileStore = new UserProfileStore();
        String name = "Cris";
        UserProfile up = userProfileStore.createProfile(name);
        UserProfile up1 = userProfileStore.createProfile(name);
        // Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            userProfileStore.saveUserProfile(up);
            userProfileStore.saveUserProfile(up1);
        });
        // Assert
        assertTrue(exception.getMessage().contains("Repeated user profile name inserted."));

    }

    @Test
    public void addNewUserProfileWithSuccess() {
        //Arrange
        UserProfileStore userProfileStore = new UserProfileStore();
        String name = "Cris";
        UserProfile up = userProfileStore.createProfile(name);
        userProfileStore.saveUserProfile(up);
        // Act
        int inicialSize = userProfileStore.getUserProfileList().size();
        UserProfile up1 = userProfileStore.createProfile("Cris_Dani");
        userProfileStore.saveUserProfile(up1);
        //Assert
        assertEquals(userProfileStore.getUserProfileList().size(), inicialSize + 1);
    }


    @Test
    public void addNewUserProfileWithSuccess2() {
        //Arrange
        UserProfileStore userProfileStore = new UserProfileStore();
        String name = "Cris";
        UserProfile up = userProfileStore.createProfile(name);
        userProfileStore.saveUserProfile(up);
        // Act
        int initialSize = userProfileStore.getUserProfileList().size();
        UserProfile up1 = userProfileStore.createProfile("Cris_Dani");
        UserProfile up2 = userProfileStore.createProfile("Cris_Dani2");
        userProfileStore.saveUserProfile(up1);
        userProfileStore.saveUserProfile(up2);
        //Assert
        assertEquals(userProfileStore.getUserProfileList().size(), initialSize + 2);
    }

    @Test
    public void addNewUserProfileWithFailNameEmpty() {
        //Arrange
        UserProfileStore userProfileStore = new UserProfileStore();
        String name = "";
        // Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            UserProfile up = userProfileStore.createProfile(name);
            userProfileStore.saveUserProfile(up);
        });
        //Assert
        assertTrue(exception.getMessage().contains("Name cannot be blank."));

    }


}

