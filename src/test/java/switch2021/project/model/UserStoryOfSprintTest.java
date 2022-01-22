package switch2021.project.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switch2021.project.stores.SprintStore;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class UserStoryOfSprintTest {
    Company company= new Company();
    Sprint sprint = new Sprint("Effort View", LocalDate.now());
    Sprint sprint1 = new Sprint("Effort View 1", LocalDate.now());
    UserStoryStatus status = company.getUserStoryStatusStore().getUserStoryStatusByDescription("To do");
    SprintBacklog sprintBacklog = new SprintBacklog();
    int priority = 5;
    String description = "Validate";
    UserStory story = new UserStory(status, priority, description);

//    @Test
//    void hasCode() {
//        UserStoryOfSprint userStoryOfSprint = new UserStoryOfSprint(story, 21);
//        sprintBacklog.addUserStory(userStoryOfSprint);
//        int id = 1;
//        assertTrue(userStoryOfSprint.hasCode(id));
//    }

    @Test
    void getSprintIDSuccess() {
        SprintStore sprintStore = new SprintStore();
        sprintStore.saveSprint(sprint);
        Sprint sprint1 = sprintStore.getSprint(1);
        assertEquals(sprint, sprint1);
    }

    @Test
    void getSprintIDNotSuccess() {
        SprintStore sprintStore = new SprintStore();
        sprintStore.saveSprint(sprint);
        Sprint sprint2 = sprintStore.getSprint(1);
        sprintStore.saveSprint(sprint1);
        Sprint sprint3 = sprintStore.getSprint(5);
        assertNotEquals(sprint2, sprint3);
    }

    @Test
    @DisplayName("Validate the Effort of an User Story of Sprint")
    void validateEffortTestSuccess() {
        UserStoryOfSprint userStoryOfSprint = new UserStoryOfSprint(story, 5, 1);
        assertTrue(userStoryOfSprint.setEstimateEffort(5));
        assertEquals(userStoryOfSprint.getEstimateEffort(), 5);
    }

    @Test
    @DisplayName("Validate the Effort of an User Story of Sprint")
    void validateEffortTestNotSuccess() {
        UserStoryOfSprint userStoryOfSprint = new UserStoryOfSprint(story, 5, 1);
        assertFalse(userStoryOfSprint.setEstimateEffort(4));
        assertNotEquals(userStoryOfSprint.getEstimateEffort(), 4);
    }

}