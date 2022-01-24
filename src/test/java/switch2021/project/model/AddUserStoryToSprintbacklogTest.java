package switch2021.project.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class AddUserStoryToSprintbacklogTest {

    SprintBacklog sprintBacklog = new SprintBacklog();
    UserStoryOfSprint userStoryOfSprint;

    @BeforeEach
    public void init() {
        UserStoryStatus status = new UserStoryStatus("statusTest");
        UserStory userStory = new UserStory(status, 2, "teste");
        userStoryOfSprint = sprintBacklog.createUSerStoryOfSprint(userStory, 8);
        sprintBacklog.addUserStory(userStoryOfSprint);
    }

    @Test
    @DisplayName("Create Story in sprint")
    public void createStoryInSprint() {
        //Act
        int value = userStoryOfSprint.getEstimateEffort();
        int priority_expected = 8;

        String description = userStoryOfSprint.getUserStoryOfSprint().getDescription();
        String description_expected = "teste";

        String status_value = userStoryOfSprint.getUserStoryOfSprint().getUserStoryStatus().getDescription();
        String expected_status = "statusTest";

        //Assert
        assertEquals(priority_expected, value);
        assertEquals(description_expected, description);
        assertEquals(expected_status, status_value);
    }

    @Test
    @DisplayName("Create Story in sprint fail case - effort below 1")
    public void createStoryInSprintFailEffort() {
        UserStoryStatus status = new UserStoryStatus("statusTest");
        UserStory userStory = new UserStory(status, 2, "teste");
        assertThrows(IllegalArgumentException.class, () -> sprintBacklog.createUSerStoryOfSprint(userStory, -1));
    }

    @Test
    @DisplayName("Create Story in sprint fail case - User Story null")
    public void createStoryInSprintFailUserStory() {
        UserStory userStory = null;
        assertThrows(IllegalArgumentException.class, () -> sprintBacklog.createUSerStoryOfSprint(userStory, 5));
    }


    @Test
    @DisplayName("Create Story in sprint fail case - UserStory selected has Done Status")
    public void createStoryInSprintStatusDone() {
        UserStoryStatus status = new UserStoryStatus("Done");
        UserStory userStory = new UserStory(status, 2, "teste");
        assertThrows(IllegalArgumentException.class, () -> sprintBacklog.createUSerStoryOfSprint(userStory, 5));

    }

    @Test
    @DisplayName("Create Story in sprint fail case - UserStory already exists in sprintbacklog")
    public void UserStoryInSprintFail_AlreadyExists() {
        UserStoryStatus status = new UserStoryStatus("statusTest");
        UserStory userStory = new UserStory(status, 2, "teste");
        userStory.setId_UserStory(1);
        UserStoryOfSprint userStoryOfSprint= sprintBacklog.createUSerStoryOfSprint(userStory, 5);
        sprintBacklog.addUserStory(userStoryOfSprint);
        assertTrue(sprintBacklog.validateId_UserStoryOfSprint(userStoryOfSprint));

    }

    @Test
    @DisplayName("Add Story to backlog")
    public void addStoryToBacklog() {
        //Act
        UserStoryStatus status = new UserStoryStatus("statusTest");
        UserStory userStory = new UserStory(status, 2, "teste");

        SprintBacklog testBacklog = new SprintBacklog();
        UserStoryOfSprint test = testBacklog.createUSerStoryOfSprint(userStory, 8);
        testBacklog.addUserStory(test);
        test.getUserStoryOfSprint().setId_UserStory(sprintBacklog.getUserStoryOfSprintList().get(0).getUserStoryOfSprint().getId_UserStory());

        String expected = test.toString();

        //Result
        assertEquals(expected, sprintBacklog.getUserStoryOfSprintList().get(0).toString());
    }

}
