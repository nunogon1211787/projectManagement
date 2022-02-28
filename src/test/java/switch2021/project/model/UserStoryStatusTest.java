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

    @Test
    void overrideTest() {
    UserStoryStatus status1 = new UserStoryStatus("teste");
    UserStoryStatus status2= new UserStoryStatus("teste2");

        assertNotEquals(status1,status2);
        assertNotSame(status1,status2);
        assertEquals(status1.getClass(),status2.getClass());
        assertNotEquals( null,status1);
    }
}