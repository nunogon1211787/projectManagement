package switch2021.project.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AddUserStoryToSprintbacklogTest {

    Company company = new Company();

    @Test
    @DisplayName("Creat Story in sprint")
    public void createStoryInSprint() {
        UserStoryStatus status = new UserStoryStatus("statusTet");
        UserStory userStory = new UserStory("test",status, 2,"teste", 5);
        SprintBacklog testBacklog = new SprintBacklog();
        UserStoryOfSprint test = testBacklog.createUSerStoryOfSprint(userStory,8);

        assertEquals(test,testBacklog.getUserStoryOfSprintList().get(0));
    }

    @Test
    @DisplayName("Add Story to backlog")
    public void addStoryToBacklog() {

    }
}
