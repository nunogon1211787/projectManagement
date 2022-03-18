package switch2021.project.controller;

import org.junit.jupiter.api.Test;
import switch2021.project.mapper.ScrumBoardMapper;
import switch2021.project.model.*;

import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

public class GetScrumBoardControllerTest {

    @Test
    public void getScrumBoardControllerTest() {
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
        UserStory userStory = company.getProjectStore().getProjectByCode("Project_2022_1").getProductBacklog().createUserStory( "US001",
                1, "Fazer coisas cool",5);
        company.getProjectStore().getProjectByCode("Project_2022_1").getProductBacklog().saveUserStory(userStory);
        company.getProjectStore().getProjectByCode("Project_2022_1").getSprintList().getCurrentSprint().getSprintBacklog().saveUserStoryToSprintBacklog(userStory);

        //Act
        ScrumBoardMapper mapper = new ScrumBoardMapper();
        GetScrumBoardController controller = new GetScrumBoardController(company,mapper);
        controller.getProject("Project_2022_1");
        controller.getCurrentSprint();
        controller.getSprintBacklog();
        controller.getUserStoryList();


        //Assert
        assertEquals(controller.getScrumBoard().get(0).getUserStoryId(),mapper.toDTO(userStory).getUserStoryId());
        assertEquals(controller.getScrumBoard().get(0).getUserStoryPriority(),mapper.toDTO(userStory).getUserStoryPriority());
        assertEquals(controller.getScrumBoard().get(0).getUserStoryTimeEstimate(),mapper.toDTO(userStory).getUserStoryTimeEstimate());
        assertEquals(controller.getScrumBoard().get(0).getUserStoryStatus(),mapper.toDTO(userStory).getUserStoryStatus());
        assertEquals(controller.getScrumBoard().get(0).getUserStoryTitle(),mapper.toDTO(userStory).getUserStoryTitle());
        assertEquals(controller.getScrumBoard().get(0).getUserStoryDescription(),mapper.toDTO(userStory).getUserStoryDescription());
    }
}
