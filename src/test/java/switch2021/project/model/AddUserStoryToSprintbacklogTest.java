package switch2021.project.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AddUserStoryToSprintbacklogTest {

    Company company = new Company();
    @BeforeEach


    @Test
    @DisplayName("Create Story in sprint")
    public void createStoryInSprint() {
        //Arrange
        UserStoryStatus status = new UserStoryStatus("statusTet");
        UserStory userStory = new UserStory(status, 2,"teste");
        SprintBacklog testBacklog = new SprintBacklog();
        UserStoryOfSprint test = testBacklog.createUSerStoryOfSprint(userStory,8);
        String value = test.toString();

        assertEquals("",value);
    }

    @Test
    @DisplayName("Add Story to backlog")
    public void addStoryToBacklog() {
        UserStoryStatus status = new UserStoryStatus("statusTet");
        UserStory userStory = new UserStory(status, 2,"teste");
        SprintBacklog testBacklog = new SprintBacklog();
        UserStoryOfSprint test = testBacklog.createUSerStoryOfSprint(userStory,8);
        testBacklog.addUserStory(test);

        //Result
        assertEquals(test,testBacklog.getUserStoryOfSprintList().get(0));
    }
}
