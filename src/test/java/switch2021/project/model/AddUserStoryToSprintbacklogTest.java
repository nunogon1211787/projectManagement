package switch2021.project.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AddUserStoryToSprintbacklogTest {


    Company company = new Company();
    SprintBacklog sprintback = new SprintBacklog();
    @BeforeEach

    @Test
    @DisplayName("Create Story in sprint")
    public void createStoryInSprint() {
        //Arrange
        UserStoryStatus status = new UserStoryStatus("statusTest");
        UserStory userStory = new UserStory(status, 2,"teste");
        SprintBacklog testBacklog = new SprintBacklog();
        UserStoryOfSprint test = testBacklog.createUSerStoryOfSprint(userStory,8);

        //Expected vs real
        int value = test.getEstimateEffort();
        int priority_expected = 8;
        String description = test.getUserStoryOfSprint().getDescription();
        String description_expected = "teste";
        String status_value = test.getUserStoryOfSprint().getUserStoryStatus().getDescription();
        String expected_status = "statusTest";

        //Results
        assertEquals(priority_expected,value);
        assertEquals(description_expected,description);
        assertEquals(expected_status,status_value);

    }

    @Test
    @DisplayName("Add Story to backlog")
    public void addStoryToBacklog() {
        UserStoryStatus status = new UserStoryStatus("statusTest");
        UserStory userStory = new UserStory(status, 2,"teste");
        SprintBacklog testBacklog = new SprintBacklog();
        UserStoryOfSprint test = testBacklog.createUSerStoryOfSprint(userStory,8);
        testBacklog.addUserStory(test);
        String expected = test.toString();

        //Result
        assertEquals(expected,testBacklog.getUserStoryOfSprintList().get(0).toString());
    }

}
