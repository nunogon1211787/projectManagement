package switch2021.project.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switch2021.project.model.*;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AddUserStoryToSprintBacklogControllerTest {

    @Test
    @DisplayName("Add Story to backlog")
    public void addStoryToBacklog() {
        //Arrange
        Company company = new Company();

        Typology typo = company.getTypologyStore().getTypologyByDescription("Fixed Cost");
        Customer customer = company.getCustomerStore().getCustomerByName("Teste");
        BusinessSector sector = company.getBusinessSectorStore().getBusinessSectorByDescription("sector");
        Project project = company.getProjectStore().createProject( "prototype", "test1234", customer,
                typo, sector, LocalDate.now(), 7, 5000);

        Sprint sprint = project.getSprintList().createSprint("Sprint 1", LocalDate.now(), 2);
        project.getSprintList().saveSprint(sprint);

        company.getProjectStore().saveNewProject(project);
        UserStory userStory = company.getProjectStore().getProjectByCode("Project_2022_1").getProductBacklog().createUserStory( new UserStoryStatus("TODO"),
                1, "Fazer coisas cool");
        company.getProjectStore().getProjectByCode("Project_2022_1").getProductBacklog().saveUserStory(userStory);

        //Act
        AddUserStoryToSprintBacklogController addStory = new AddUserStoryToSprintBacklogController(company);
        addStory.getProject("Project_2022_1");
        addStory.getSprintStore();
        addStory.getSprint(1);
        addStory.getProductBacklog();
        addStory.getUserStory(1);

        addStory.addUserStoryToSprintBacklog(4,company.getUserStoryStatusStore().getUserStoryStatusByDescription("Planned"));

        UserStoryOfSprint value = project.getSprintList().getSprintList().get(0).getSprintBacklog().getUserStoryOfSprintList().get(0);

        //Assert
        assertEquals(userStory, value.getUserStoryOfSprint());
        assertEquals(4,project.getSprintList().getSprintList().get(0).getSprintBacklog().getUserStoryOfSprintList().get(0).getEstimateEffort());
    }
}


