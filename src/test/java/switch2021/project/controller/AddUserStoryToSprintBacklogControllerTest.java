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

        Typology typo = company.getTypologyStore().getTypology("Fixed Cost");
        Customer customer = company.getCustomerStore().getCustomerByName("Teste");
        BusinessSector sector = company.getBusinessSectorStore().getBusinessSectorByDescription("sector");
        Project project = company.getProjectStore().createProject( "prototype", "test1234", customer,
                typo, sector, LocalDate.now(), 7, 5000);

        Sprint sprint = project.getSprints().createSprint("Sprint 1", LocalDate.now(), 2);
        project.getSprints().saveSprint(sprint);

        company.getProjectStore().saveNewProject(project);
        UserStory userStory = company.getProjectStore().getProjectByCode("Project_2022_1").getProductBacklog().createUserStory( "US001",
                1, "Fazer coisas cool",5);
        company.getProjectStore().getProjectByCode("Project_2022_1").getProductBacklog().saveUserStory(userStory);

        //Act
        AddUserStoryToSprintBacklogController addStory = new AddUserStoryToSprintBacklogController(company);
        addStory.getProject("Project_2022_1");
        addStory.getSprintStore();
        addStory.getSprint(1);
        addStory.getProductBacklog();
        addStory.getUserStory(1);

        addStory.addUserStoryToSprintBacklog();

        //Assert
        assertEquals(userStory, company.getProjectStore().getProjectByCode("Project_2022_1").getCurrentSprint().getSprintBacklog().getUserStoryList().get(0));
    }
}


