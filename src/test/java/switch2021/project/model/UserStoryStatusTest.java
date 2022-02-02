package switch2021.project.model;

import org.junit.jupiter.api.Test;
import switch2021.project.stores.UserStoryStatusStore;

import static org.junit.jupiter.api.Assertions.*;

class UserStoryStatusTest {

    private final UserStoryStatusStore userStoryStatusStore = new UserStoryStatusStore();
    private final UserStoryStatus userStoryStatus = new UserStoryStatus("Forte");

    @Test
    void isSprintAvailable() {
        this.userStoryStatusStore.populateDefault();
        this.userStoryStatusStore.saveNewUserStoryStatus(userStoryStatus);
        this.userStoryStatus.setSprintAvailable(true);
        boolean actual = this.userStoryStatus.isSprintAvailable();
        assertTrue(actual);
    }

    @Test
    void setSprintAvailable() {
        this.userStoryStatusStore.populateDefault();
        this.userStoryStatusStore.saveNewUserStoryStatus(userStoryStatus);
        this.userStoryStatus.setSprintAvailable(false);
        boolean actual = this.userStoryStatus.isSprintAvailable();
        assertFalse(actual);

    }
}