package switch2021.project.model;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class UserStoryTest {

    private UserStory userStory;
    private UserStory userStory2;
    private Sprint sprint;

    @Test
    void setPriorityTest() {
        Sprint sprint = new Sprint("Super", LocalDate.of(2022,3,1));
        userStory = new UserStory("US001", 2, "Fazer tal",5);
        sprint.getSprintBacklog().saveUserStoryToSprintBacklog(userStory);
        userStory.setPriority(4);
        assertEquals(userStory.getPriority(),4);
    }

    @Test
    void setPriorityTestInvalid() {
        Sprint sprint = new Sprint("Super", LocalDate.of(2022,3,1));
        userStory = new UserStory("US001", 2, "Fazer tal",5);
        sprint.getSprintBacklog().saveUserStoryToSprintBacklog(userStory);
        userStory.setPriority(6);
        assertEquals(userStory.getPriority(),2);
    }

    @Test
    void setDescriptionTest() {
        Sprint sprint = new Sprint("Super", LocalDate.of(2022,3,1));
        userStory = new UserStory("US001", 2, "Fazer tal",5);
        sprint.getSprintBacklog().saveUserStoryToSprintBacklog(userStory);
        userStory.setDescription("Fazer coiso");
        assertEquals(userStory.getDescription(), "Fazer coiso");
    }

    @Test
    void setUserStoryStatusBooleanTest() {

        Sprint sprint = new Sprint("Super", LocalDate.of(2022,3,1));
        userStory = new UserStory("US001", 2, "Fazer tal",5);
        sprint.getSprintBacklog().saveUserStoryToSprintBacklog(userStory);
        UserStoryStatus userStoryStatus = new UserStoryStatus("Almost finished");
        userStory.setUserStoryStatusBoolean(userStoryStatus);

        assertEquals(userStory.getUserStoryStatus().getDescription(), "Almost finished");

    }

    @Test
    void validatePriorityTest() {
        Sprint sprint = new Sprint("Super", LocalDate.of(2022,3,1));
        userStory = new UserStory("US001", 2, "Fazer tal",5);
        sprint.getSprintBacklog().saveUserStoryToSprintBacklog(userStory);
        boolean expected = userStory.validatePriority(6);
        assertFalse(expected);
    }

    @Test
    void validatePriorityTest2() {
        Sprint sprint = new Sprint("Super", LocalDate.of(2022,3,1));
        userStory = new UserStory("US001", 2, "Fazer tal",5);
        sprint.getSprintBacklog().saveUserStoryToSprintBacklog(userStory);
        boolean expected = userStory.validatePriority(1);
        assertTrue(expected);
    }
}