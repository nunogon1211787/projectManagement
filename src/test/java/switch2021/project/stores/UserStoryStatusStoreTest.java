package switch2021.project.stores;

import org.junit.jupiter.api.Test;
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
}