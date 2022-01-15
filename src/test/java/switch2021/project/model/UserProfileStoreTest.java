package switch2021.project.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class UserProfileStoreTest {

    /**
     * >>>>>> Testes de UserProfile <<<<<<
     **/

    // Test add UserProfile in company (Cris US013)
    @Test
    public void createNewUserProfileWithFailNameEmpty() {
        //Arrange
        UserProfileStore userProfileStore = new UserProfileStore();
        String name = "";
        // Act
        try {
            UserProfile up = userProfileStore.createProfile(name);
        } catch (IllegalArgumentException exception) {
            //Assert
            assertEquals("Name cannot be blank.", exception.getMessage());
        }

    }

    @Test
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
    public void createNewUserProfileAlreadyExist() {
        //Arrange
        UserProfileStore userProfileStore = new UserProfileStore();
        String name = "Cris";
        // Act
        try {
            UserProfile up = userProfileStore.createProfile(name);
            UserProfile up1 = userProfileStore.createProfile(name);
        } catch (IllegalArgumentException exception) {
            //Assert
            assertEquals("Repeated user profile name inserted.", exception.getMessage());
        }
    }

    @Test
    public void addNewUserProfileAlreadyExist() {
        //Arrange
        UserProfileStore userProfileStore = new UserProfileStore();
        String name = "Cris";
        UserProfile up = userProfileStore.createProfile(name);
        UserProfile up1 = userProfileStore.createProfile(name);
        // Act
        try {
            userProfileStore.saveUserProfile(up);
            userProfileStore.saveUserProfile(up1);
        } catch (IllegalArgumentException exception) {
            //Assert
            assertEquals("Repeated user profile name inserted.", exception.getMessage());
        }
    }

    @Test
    public void addNewUserProfileWithSuccess() {
        //Arrange
        UserProfileStore userProfileStore = new UserProfileStore();
        String name = "Cris";
        UserProfile up = userProfileStore.createProfile(name);
        userProfileStore.addUserProfile(up);
        // Act
        int inicialSize = userProfileStore.getUserProfileList().size();
        UserProfile up1 = userProfileStore.createProfile("Cris_Dani");
        userProfileStore.addUserProfile(up1);
        //Assert
        assertEquals(userProfileStore.getUserProfileList().size(), inicialSize + 1);
    }


    @Test
    public void addNewUserProfileWithSuccess2() {
        //Arrange
        UserProfileStore userProfileStore = new UserProfileStore();
        String name = "Cris";
        UserProfile up = userProfileStore.createProfile(name);
        userProfileStore.addUserProfile(up);
        // Act
        int inicialSize = userProfileStore.getUserProfileList().size();
        UserProfile up1 = userProfileStore.createProfile("Cris_Dani");
        UserProfile up2 = userProfileStore.createProfile("Cris_Dani2");
        userProfileStore.addUserProfile(up1);
        userProfileStore.addUserProfile(up2);
        //Assert
        assertEquals(userProfileStore.getUserProfileList().size(), inicialSize + 2);
    }

    @Test
    public void addNewUserProfileWithFailNameEmpty() {
        //Arrange
        UserProfileStore userProfileStore = new UserProfileStore();
        String name = "";

        // Act
        try {
            UserProfile up = userProfileStore.createProfile(name);
            userProfileStore.addUserProfile(up);
        } catch (IllegalArgumentException exception) {
            //Assert
            assertEquals("Name cannot be blank.", exception.getMessage());
        }

    }

    @Test
    public void saveNewUserProfileAlreadyExist() {
        //Arrange
        UserProfileStore userProfileStore = new UserProfileStore();
        String name = "Cris";
        UserProfile up = userProfileStore.createProfile(name);
        UserProfile up1 = userProfileStore.createProfile(name);
        // Act
        try {
            userProfileStore.saveUserProfile(up);
            userProfileStore.saveUserProfile(up1);
        } catch (IllegalArgumentException exception) {
            //Assert
            assertEquals("Repeated user profile name inserted.", exception.getMessage());
        }
    }

    @Test
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
    public void saveNewUserProfileWithSuccess2() {
        //Arrange
        UserProfileStore userProfileStore = new UserProfileStore();
        String name = "Cris";
        UserProfile up = userProfileStore.createProfile(name);
        userProfileStore.addUserProfile(up);
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
    public void saveNewUserProfileWithFailNameEmpty() {
        //Arrange
        UserProfileStore userProfileStore = new UserProfileStore();
        String name = "    ";

        // Act
        try {
            UserProfile up = userProfileStore.createProfile(name);
            userProfileStore.saveUserProfile(up);
        } catch (IllegalArgumentException exception) {
            //Assert
            assertEquals("Name cannot be blank.", exception.getMessage());
        }
    }


}
