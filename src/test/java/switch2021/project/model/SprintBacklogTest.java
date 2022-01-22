package switch2021.project.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import switch2021.project.stores.SprintStore;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SprintBacklogTest {


    @Test
    void compareUserStoryIDOfSprint() {
        Company company = new Company();
        ProjectTeamTest projectTeamTest = new ProjectTeamTest();
        Typology typo = company.getTypologyStore().getTypology("Fixed Cost");
        Customer customer = company.getCustomerStore().getCustomerByName("Teste");
        BusinessSector sector = company.getBusinessSectorStore().getBusinessSectorByDescription("sector");
        ProjectStatus projectStatus = new ProjectStatus("ToStart");
        Project project2 = new Project("1234testcode", "prototype", "test56", customer,
                typo, sector, LocalDate.now(), projectStatus, 7, 5000);
        UserStoryStatus status = company.getUserStoryStatusStore().getUserStoryStatusByDescription("To do");
        SprintBacklog sprintBacklog = new SprintBacklog();
        int priority = 5;
        String description = "Validate";
        UserStory userstory = new UserStory(status, priority, description);
        UserStoryOfSprint story = new UserStoryOfSprint(userstory, 21, 1);
        UserStoryOfSprint story2 = new UserStoryOfSprint(userstory, 13, 2);
        UserStoryOfSprint story3 = new UserStoryOfSprint(userstory, 2, 3);

        
        sprintBacklog.addUserStory(story);
        sprintBacklog.addUserStory(story2);
        sprintBacklog.addUserStory(story3);

        UserStoryOfSprint userStoryOfSprint1 = sprintBacklog.getUserStory(1);
        UserStoryOfSprint userStoryOfSprint2 = sprintBacklog.getUserStory(2);
        UserStoryOfSprint userStoryOfSprint3 = sprintBacklog.getUserStory(3);

        assertEquals(story, userStoryOfSprint1);
        assertEquals(story2, userStoryOfSprint2);
        assertNotEquals(story3, userStoryOfSprint2);
    }
}