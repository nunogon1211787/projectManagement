package switch2021.project.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import switch2021.project.stores.SprintStore;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SprintBacklogTest {


    @Test
    void getUserStoryOfSprint() {
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
        UserStoryOfSprint story = new UserStoryOfSprint(userstory, 21);
        UserStoryOfSprint story2 = new UserStoryOfSprint(userstory, 13);
        UserStoryOfSprint story3 = new UserStoryOfSprint(userstory, 2);

        
        sprintBacklog.addUserStory(story);
        sprintBacklog.addUserStory(story2);
        sprintBacklog.addUserStory(story3);

        //List<Sprint> sprintList = project2.getSprintStore().getSprintList();
        UserStoryOfSprint userStoryOfSprint2 = sprintBacklog.getUserStory(0);

        assertEquals(story2, userStoryOfSprint2);
    }
}