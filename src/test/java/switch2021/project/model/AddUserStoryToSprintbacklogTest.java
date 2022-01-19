package switch2021.project.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AddUserStoryToSprintbacklogTest {

    Company company = new Company();

    @Test
    @DisplayName("Creat Story in sprint")
    public void createStoryInSprint() {
        //Arrange

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
