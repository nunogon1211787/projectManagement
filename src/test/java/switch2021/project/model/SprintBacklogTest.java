package switch2021.project.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switch2021.project.depracated.UserStoryOfSprint;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class SprintBacklogTest {

    Company company = new Company();

    @Test
    void compareUserStoryIDOfSprint() {
        Company company = new Company();
        ProjectTeamTest projectTeamTest = new ProjectTeamTest();
        Typology typo = company.getTypologyStore().getTypology("Fixed Cost");
        Customer customer = company.getCustomerStore().getCustomerByName("Teste");
        BusinessSector sector = company.getBusinessSectorStore().getBusinessSectorByDescription("sector");
        ProjectStatus projectStatus = new ProjectStatus("ToStart");
        Project project2 = new Project( "prototype", "test56", customer,
                typo, sector, LocalDate.now(), projectStatus, 7, 5000);
        UserStoryStatus status = company.getUserStoryStatusStore().getUserStoryStatusByDescription("To do");
        SprintBacklog sprintBacklog = new SprintBacklog();
        int priority = 5;
        String description = "Validate";
        UserStory userstory = new UserStory("US001", priority, description,5);
        UserStoryOfSprint story = new UserStoryOfSprint(userstory, 21, 1);
        UserStoryOfSprint story2 = new UserStoryOfSprint(userstory, 13, 2);
        UserStoryOfSprint story3 = new UserStoryOfSprint(userstory, 2, 3);

        
        sprintBacklog.saveUserStoryOfSprint(story);
        sprintBacklog.saveUserStoryOfSprint(story2);
        sprintBacklog.saveUserStoryOfSprint(story3);

        UserStoryOfSprint userStoryOfSprint1 = sprintBacklog.getUserStoryDeprecated(1);
        UserStoryOfSprint userStoryOfSprint2 = sprintBacklog.getUserStoryDeprecated(2);
        UserStoryOfSprint userStoryOfSprint3 = sprintBacklog.getUserStoryDeprecated(3);

        assertEquals(story, userStoryOfSprint1);
        assertEquals(story2, userStoryOfSprint2);
        assertNotEquals(story3, userStoryOfSprint2);
    }


    SprintBacklog sprintBacklog = new SprintBacklog();
    UserStoryOfSprint userStoryOfSprint;

    @BeforeEach
    public void init() {
        UserStoryStatus status = new UserStoryStatus("statusTest");
        UserStory userStory = new UserStory("US001", 2, "teste",5);
        userStoryOfSprint = sprintBacklog.createUSerStoryOfSprint(userStory, 8,
                company.getUserStoryStatusStore().getUserStoryStatusByDescription("Planned"));
        sprintBacklog.saveUserStoryOfSprint(userStoryOfSprint);
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
        String expected_status = "To do";

        //Assert
        assertEquals(priority_expected, value);
        assertEquals(description_expected, description);
        assertEquals(expected_status, status_value);
    }

    @Test
    @DisplayName("Create Story in sprint fail case - effort below 1")
    public void createStoryInSprintFailEffort() {
        UserStoryStatus status = new UserStoryStatus("statusTest");
        UserStory userStory = new UserStory("US001", 2, "teste",5);
        assertThrows(IllegalArgumentException.class, () -> sprintBacklog.createUSerStoryOfSprint(userStory, -1,
                company.getUserStoryStatusStore().getUserStoryStatusByDescription("Planned")));
    }

    @Test
    @DisplayName("Create Story in sprint fail case - User Story null")
    public void createStoryInSprintFailUserStory() {
        UserStory userStory = null;
        assertThrows(IllegalArgumentException.class, () -> sprintBacklog.createUSerStoryOfSprint(userStory, 5,
                company.getUserStoryStatusStore().getUserStoryStatusByDescription("Planned")));
    }


    @Test
    @DisplayName("Create Story in sprint fail case - UserStory selected has Done Status")
    public void createStoryInSprintStatusDone() {
        UserStory userStory = new UserStory("US001", 2, "teste",5);
        userStory.setUserStoryStatus(company.getUserStoryStatusStore().getUserStoryStatusByDescription("Done"));
        assertThrows(IllegalArgumentException.class, () -> sprintBacklog.createUSerStoryOfSprint(userStory, 5,
                company.getUserStoryStatusStore().getUserStoryStatusByDescription("Planned")));

    }

    @Test
    @DisplayName("Create Story in sprint fail case - UserStory already exists in sprintbacklog")
    public void UserStoryInSprintFail_AlreadyExists() {
        UserStoryStatus status = new UserStoryStatus("statusTest");
        UserStory userStory = new UserStory("US001", 2, "teste",5);
        userStory.setId_UserStory(1);
        UserStoryOfSprint userStoryOfSprint= sprintBacklog.createUSerStoryOfSprint(userStory, 5,
                company.getUserStoryStatusStore().getUserStoryStatusByDescription("Planned"));
        sprintBacklog.saveUserStoryOfSprint(userStoryOfSprint);
        assertTrue(sprintBacklog.validateUserStoryOfSprint(userStoryOfSprint));

    }

    @Test
    @DisplayName("Add Story to backlog")
    public void addStoryToBacklog() {
        //Act
        UserStoryStatus status = new UserStoryStatus("statusTest");
        UserStory userStory = new UserStory("US001", 2, "teste",5);

        SprintBacklog testBacklog = new SprintBacklog();
        UserStoryOfSprint test = testBacklog.createUSerStoryOfSprint(userStory, 8,
                company.getUserStoryStatusStore().getUserStoryStatusByDescription("Planned"));
        testBacklog.saveUserStoryOfSprint(test);
        test.getUserStoryOfSprint().setId_UserStory(sprintBacklog.getUserStoryOfSprintList().get(0).getUserStoryOfSprint().getId_UserStory());

        String expected = test.toString();

        //Result
        assertEquals(expected, sprintBacklog.getUserStoryOfSprintList().get(0).toString());
    }

}