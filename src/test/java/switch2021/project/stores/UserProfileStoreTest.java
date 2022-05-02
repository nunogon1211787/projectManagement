package switch2021.project.stores;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switch2021.project.model.UserProfile.UserProfile;
import switch2021.project.repositories.UserProfileRepository;

import static org.junit.jupiter.api.Assertions.*;


public class UserProfileStoreTest {

    /**
     * UserProfile's Tests
     **/

    // Test add UserProfile in company (Cris US013)
    @Test
    @DisplayName("Return exception message, no name for profile inserted")
    public void createNewUserProfileWithFailNameEmpty() {
        assertThrows(IllegalArgumentException.class, () -> {
            //Arrange
            new UserProfile("");
        });
    }

    @Test
    @DisplayName("Create new profile with success")
    public void createNewUserProfileWithSuccess() {
        //Arrange
        UserProfileRepository userProfileStore = new UserProfileRepository();
        String name = "Cris";
        // Act
        UserProfile up = userProfileStore.createProfile(name);
        //Assert
        assertEquals("Cris", up.getUserProfileId().getUserProfileName().getText());
    }

/*    @Test
    @DisplayName("Return exception message, profile already exist")
    public void createNewUserProfileAlreadyExist() {
        //Arrange
        UserProfileRepository userProfileStore = new UserProfileRepository();
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
        UserProfileRepository userProfileStore = new UserProfileRepository();
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

 */

    @Test
    @DisplayName("Create new profile with success")
    public void saveNewUserProfileWithSuccess() {
        //Arrange
        UserProfileRepository userProfileStore = new UserProfileRepository();
        String name = "Cris";
        UserProfile up = userProfileStore.createProfile(name);
        userProfileStore.saveUserProfile(up);
        // Act
        int inicialSize = userProfileStore.findAllUserProfiles().size();
        UserProfile up1 = userProfileStore.createProfile("Cris_Dani");
        userProfileStore.saveUserProfile(up1);
        //Assert
        assertEquals(userProfileStore.findAllUserProfiles().size(), inicialSize + 1);
    }


    @Test
    @DisplayName("Create new profile with success")
    public void saveNewUserProfileWithSuccess2() {
        //Arrange
        UserProfileRepository userProfileStore = new UserProfileRepository();
        String name = "Cris";
        UserProfile up = userProfileStore.createProfile(name);
        userProfileStore.saveUserProfile(up);
        // Act
        int inicialSize = userProfileStore.findAllUserProfiles().size();
        UserProfile up1 = userProfileStore.createProfile("Cris_Dani");
        UserProfile up2 = userProfileStore.createProfile("Cris_Dani2");
        userProfileStore.saveUserProfile(up1);
        userProfileStore.saveUserProfile(up2);
        //Assert
        assertEquals(userProfileStore.findAllUserProfiles().size(), inicialSize + 2);
    }

/*    @Test
    public void addNewUserProfileAlreadyExist() {
        //Arrange
        UserProfileRepository userProfileStore = new UserProfileRepository();
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

 */

    @Test
    public void addNewUserProfileWithSuccess() {
        //Arrange
        UserProfileRepository userProfileStore = new UserProfileRepository();
        String name = "Cris";
        UserProfile up = userProfileStore.createProfile(name);
        userProfileStore.saveUserProfile(up);
        // Act
        int inicialSize = userProfileStore.findAllUserProfiles().size();
        UserProfile up1 = userProfileStore.createProfile("Cris_Dani");
        userProfileStore.saveUserProfile(up1);
        //Assert
        assertEquals(userProfileStore.findAllUserProfiles().size(), inicialSize + 1);
    }


    @Test
    public void addNewUserProfileWithSuccess2() {
        //Arrange
        UserProfileRepository userProfileStore = new UserProfileRepository();
        String name = "Cris";
        UserProfile up = userProfileStore.createProfile(name);
        userProfileStore.saveUserProfile(up);
        // Act
        int initialSize = userProfileStore.findAllUserProfiles().size();
        UserProfile up1 = userProfileStore.createProfile("Cris_Dani");
        UserProfile up2 = userProfileStore.createProfile("Cris_Dani2");
        userProfileStore.saveUserProfile(up1);
        userProfileStore.saveUserProfile(up2);
        //Assert
        assertEquals(userProfileStore.findAllUserProfiles().size(), initialSize + 2);
    }

    @Test
    public void overrideAndHashCodeTest() {
        //Arrange
        UserProfileRepository list1 = new UserProfileRepository();
        list1.saveUserProfile(list1.createProfile("new"));
        UserProfileRepository list2 = new UserProfileRepository();
        list2.saveUserProfile(list1.createProfile("new"));
        UserProfileRepository list3 = new UserProfileRepository();
        list3.saveUserProfile(list3.createProfile("not new"));
        //Assert
        assertNotSame(list1, list2);
        assertEquals(list1, list2);
        assertEquals(list1.hashCode(), list2.hashCode());
        assertNotEquals(list1, list3);
        assertNotEquals(list1.hashCode(), list3.hashCode());
    }

}

