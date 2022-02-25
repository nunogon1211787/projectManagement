package switch2021.project.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switch2021.project.depracated.UserStoryOfSprint;
import switch2021.project.stores.SprintList;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class UserStoryOfSprintTest {
    Company company= new Company();
    Sprint sprint = new Sprint("Effort View", LocalDate.now());
    Sprint sprint1 = new Sprint("Effort View 1", LocalDate.now());
    SprintBacklog sprintBacklog = new SprintBacklog();
    int priority = 5;
    String description = "Validate";
    UserStory story = new UserStory("US001", priority, description,5);

    @Test
    void getSprintIDSuccess() {
        SprintList sprintList = new SprintList();
        sprintList.saveSprint(sprint);
        Sprint sprint1 = sprintList.getSprint(1);
        assertEquals(sprint, sprint1);
    }

    @Test
    void getSprintIDNotSuccess() {
        SprintList sprintList = new SprintList();
        sprintList.saveSprint(sprint);
        Sprint sprint2 = sprintList.getSprint(1);
        sprintList.saveSprint(sprint1);
        Sprint sprint3 = sprintList.getSprint(5);
        assertNotEquals(sprint2, sprint3);
    }

    @Test
    @DisplayName("Validate the Effort of an User Story of Sprint")
    void validateEffortTestSuccess() {
        //Act
        UserStoryOfSprint userStoryOfSprint = new UserStoryOfSprint(story, 5, 1);
        //Assert
        assertTrue(userStoryOfSprint.setEstimateEffort(5));
        assertEquals(5, userStoryOfSprint.getEstimateEffort());
    }

    @Test
    @DisplayName("Validate the Effort of an User Story of Sprint")
    void validateEffortTestNotSuccess() {
        UserStoryOfSprint userStoryOfSprint = new UserStoryOfSprint(story, 5, 1);
        assertFalse(userStoryOfSprint.setEstimateEffort(4));
        assertNotEquals(4, userStoryOfSprint.getEstimateEffort() );
    }

    @Test
    @DisplayName("Test hashCode Method")
    void HashCodeTest() {
        UserStory userStory1 = new UserStory("CCC", 2, "Fazer tal", 5);
        UserStory userStory2 = new UserStory("AAA", 2, "Fazer tal e coiso", 5);

        UserStoryOfSprint usSrpint = new UserStoryOfSprint(userStory1,5, new UserStoryStatus("teste"));
        UserStoryOfSprint usSrpint2 = new UserStoryOfSprint(userStory2,5, new UserStoryStatus("teste"));

        assertNotEquals(usSrpint.hashCode(),usSrpint2.hashCode());
        assertNotEquals(usSrpint,usSrpint2);
    }

    @Test
    @DisplayName("Test get Status")
    void userStpryOfSprintGetStatus() {
        UserStory userStory1 = new UserStory("CCC", 2, "Fazer tal", 5);

        UserStoryOfSprint usSrpint = new UserStoryOfSprint(userStory1,5, new UserStoryStatus("teste"));

        assertNotEquals("Fazer tal",usSrpint.getStatus().getDescription());
        assertEquals(0,usSrpint.getUserStoryOfSprintTasks().size());
    }

    @Test
    @DisplayName("Test get TaskList")
    void userStoryOfSprintGettask() {
        //Arrange
        UserProfile profile = company.getUserProfileStore().getUserProfile("Visitor");
        SystemUser user = new SystemUser("manuelbras", "manuelbras@beaver.com", "tester",
                "ghi", "ghi", "photo", profile);
        LocalDate startDateMb = LocalDate.of(2022, 1, 1);
        LocalDate endDateMb = LocalDate.of(2022, 1, 31);
        String taskDescription = "must be at least 20 characters";
        TaskType taskType = company.getTaskTypeStore().getTypeByName("Testing");
        Resource resource = new Resource(user, startDateMb, endDateMb, 100, .5);
        Task task = new Task("test", taskDescription, 16.00, taskType, resource);
        Task task2 = new Task("test2", taskDescription, 16.00, taskType, resource);
        UserStory userStory1 = new UserStory("CCC", 2, "Fazer tal", 5);
        UserStoryOfSprint usSrpint = new UserStoryOfSprint(userStory1,5, new UserStoryStatus("teste"));
        UserStoryOfSprint usSrpint2 = new UserStoryOfSprint(userStory1,5, new UserStoryStatus("teste"));
        UserStoryOfSprint usSrpint3 = new UserStoryOfSprint(userStory1,5, new UserStoryStatus("teste"));
        usSrpint.getUserStoryOfSprintTasks().add(task);
        usSrpint2.getUserStoryOfSprintTasks().add(task2);
        usSrpint3.getUserStoryOfSprintTasks().add(task2);

        assertNotEquals(usSrpint,usSrpint2);
        assertEquals(usSrpint2,usSrpint3);
    }
}