package switch2021.project.stores;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switch2021.project.model.valueObject.Description;
import switch2021.project.model.valueObject.UserStoryStatus;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class UserStoryStatusStoreTest {


    @Test
    @DisplayName("Test with mock if the description is returned")
    void getUserStoryStatusByDescription() {
        //Arrange
        UserStoryStatusStore userStoryStatusStore = new UserStoryStatusStore();
        userStoryStatusStore.populateDefault();
        UserStoryStatus userStoryStatus = mock(UserStoryStatus.class);
        Description description = mock(Description.class);
        when(userStoryStatus.getDescription()).thenReturn(description);
        when(description.getText()).thenReturn("In test");
        //Act
        UserStoryStatus teste = userStoryStatusStore.getUserStoryStatusByDescription("In test");
        //Assert
        assertEquals("In test", userStoryStatus.getDescription().getText());
        assertEquals("In test", teste.getDescription().getText());

    }

    @Test
    @DisplayName("Test with mock check if user story status exists")
    void checkUserStoryStatusExistsSuccess() {
        //Arrange
        UserStoryStatusStore userStoryStatusStore = new UserStoryStatusStore();
        userStoryStatusStore.populateDefault();
        Description desc = mock(Description.class);
        UserStoryStatus userStoryStatus = mock(UserStoryStatus.class);
        when(userStoryStatus.getDescription()).thenReturn(desc);
        when(desc.getText()).thenReturn("To do");
        when(userStoryStatus.isSprintAvailable()).thenReturn(true);
        userStoryStatusStore.saveNewUserStoryStatus(userStoryStatus);
        //Act
        boolean expected = userStoryStatusStore.checkUserStoryStatusExists(userStoryStatus);
        //Assert
        assertTrue(expected);

    }

    @Test
    @DisplayName("Test with mock check if user story status don't exist")
    void checkUserStoryStatusExistsFail() {
        //Arrange
        UserStoryStatusStore userStoryStatusStore = new UserStoryStatusStore();
        userStoryStatusStore.populateDefault();
        Description desc = mock(Description.class);
        UserStoryStatus userStoryStatus = mock(UserStoryStatus.class);
        when(userStoryStatus.getDescription()).thenReturn(desc);
        when(desc.getText()).thenReturn("To do");
        when(userStoryStatus.isSprintAvailable()).thenReturn(true);
        //Act
        boolean expected = userStoryStatusStore.checkUserStoryStatusExists(userStoryStatus);
        //Assert
        assertFalse(expected);
    }


    @Test
    @DisplayName("Test with mock save user story status success")
    void saveNewUserStoryStatusSuccess() {
        //Arrange
        UserStoryStatusStore userStoryStatusStore = new UserStoryStatusStore();
        userStoryStatusStore.populateDefault();
        UserStoryStatus userStoryStatus = mock(UserStoryStatus.class);
        //Act
        boolean expected = userStoryStatusStore.saveNewUserStoryStatus(userStoryStatus);
        //Assert
        assertTrue(expected);
    }

    @Test
    @DisplayName("Test with mock save user story status fail - already exist")
    void saveNewUserStoryStatusFail() {
        //Arrange
        UserStoryStatusStore userStoryStatusStore = new UserStoryStatusStore();
        userStoryStatusStore.populateDefault();
        UserStoryStatus userStoryStatus = mock(UserStoryStatus.class);
        //Act
        userStoryStatusStore.saveNewUserStoryStatus(userStoryStatus);
        //Assert
        assertFalse(userStoryStatusStore.saveNewUserStoryStatus(userStoryStatus));
    }

    @Test
    @DisplayName("Test with mock save user story status fail - null")
    void saveNewUserStoryStatusNull() {
        //Arrange
        UserStoryStatusStore userStoryStatusStore = new UserStoryStatusStore();
        userStoryStatusStore.populateDefault();
        //Act
        boolean expected = userStoryStatusStore.saveNewUserStoryStatus(null);
        //Assert
        assertTrue(expected);
    }
}
