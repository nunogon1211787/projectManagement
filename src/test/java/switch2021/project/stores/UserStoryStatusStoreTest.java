package switch2021.project.stores;

import org.junit.jupiter.api.Test;
import switch2021.project.Immutables.Description;
import switch2021.project.model.UserStoryStatus;

import static org.junit.jupiter.api.Assertions.*;

class UserStoryStatusStoreTest {

    private final UserStoryStatusStore userStoryStatusStore = new UserStoryStatusStore();
    private final UserStoryStatus userStoryStatus = new UserStoryStatus("Forte");


    @Test
    void checkUserStoryStatusExists() {
        this.userStoryStatusStore.populateDefault();
        this.userStoryStatusStore.saveNewUserStoryStatus(userStoryStatus);
        this.userStoryStatus.setSprintAvailable(true);
        boolean expected = this.userStoryStatusStore.checkUserStoryStatusExists(userStoryStatus);
        assertTrue(expected);
    }

    @Test
    void checkUserStoryStatusExists2() {
        this.userStoryStatusStore.populateDefault();
        this.userStoryStatus.setSprintAvailable(true);
        boolean expected = this.userStoryStatusStore.checkUserStoryStatusExists(userStoryStatus);
        assertFalse(expected);
    }

    @Test
    void saveNewUserStoryStatus() {
        this.userStoryStatusStore.populateDefault();
        this.userStoryStatusStore.saveNewUserStoryStatus(userStoryStatus);
        this.userStoryStatus.setSprintAvailable(true);
        assertEquals(userStoryStatusStore.getUserStoryStatusByDescription("Forte"), userStoryStatus);
    }

    @Test
    void saveNewUserStoryStatus2() {
        this.userStoryStatusStore.populateDefault();
        this.userStoryStatusStore.saveNewUserStoryStatus(userStoryStatus);
        boolean expected = this.userStoryStatusStore.saveNewUserStoryStatus(userStoryStatus);
        this.userStoryStatus.setSprintAvailable(true);
        assertFalse(expected);
    }

    @Test
    void saveNewUserStoryStatus3() {
        this.userStoryStatusStore.populateDefault();
        boolean expected = this.userStoryStatusStore.saveNewUserStoryStatus(userStoryStatus);
        this.userStoryStatus.setSprintAvailable(true);
        assertTrue(expected);
    }
    @Test
    void setUserStoryDescription() {
        this.userStoryStatusStore.populateDefault();
        this.userStoryStatusStore.saveNewUserStoryStatus(userStoryStatus);
        this.userStoryStatus.setSprintAvailable(true);
        userStoryStatus.setDescription(new Description("Outro"));
        boolean expected = this.userStoryStatusStore.checkUserStoryStatusExists(userStoryStatus);
        assertTrue(expected);
    }

    @Test
    void saveUserStoryStatusNull() {
        this.userStoryStatusStore.populateDefault();
        boolean saveNewUserStoryStatus = this.userStoryStatusStore.saveNewUserStoryStatus(null);
        assertTrue(saveNewUserStoryStatus);
    }


}