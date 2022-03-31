package switch2021.project.model.valueObject;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switch2021.project.stores.UserStoryStatusStore;

import static org.junit.jupiter.api.Assertions.*;

class UserStoryStatusTest {

    private final UserStoryStatusStore userStoryStatusStore = new UserStoryStatusStore();
    private final UserStoryStatus userStoryStatus = new UserStoryStatus("Forte");

    @Test
    void isSprintAvailable() {
        //Arrange
        this.userStoryStatusStore.populateDefault();
        this.userStoryStatusStore.saveNewUserStoryStatus(userStoryStatus);
        this.userStoryStatus.setSprintAvailable(true);
        //Act
        assertTrue(this.userStoryStatus.isSprintAvailable());
    }

    @Test
    void setSprintAvailable() {
        //Arrange
        this.userStoryStatusStore.populateDefault();
        this.userStoryStatusStore.saveNewUserStoryStatus(userStoryStatus);
        this.userStoryStatus.setSprintAvailable(false);
        //Act
        assertFalse(this.userStoryStatus.isSprintAvailable());

    }

    @Test
    @DisplayName("Test override conditions for coverage purposes")
    public void overrideTests() {
        // Arrange
        UserStoryStatus description = new UserStoryStatus("Teste");
        UserStoryStatus description2 = new UserStoryStatus("Teste");
        UserStoryStatus description3 = null;
        Typology test = new Typology("test");
        // Act
        assertEquals(description,description2);
        assertNotEquals(description, description3);
        assertNotEquals(description, test);
    }
}