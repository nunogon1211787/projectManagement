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
        company.getProjectStore().getProjectByCode("Project_2022_1").getSprints().getCurrentSprint().getSprintBacklog().saveUserStoryToSprintBacklog(userStory);

        //
        ScrumBoardMapper mapper = new ScrumBoardMapper();
        GetScrumBoardController controller = new GetScrumBoardController(company,mapper);

        assertEquals(controller.getScrumBoard("Project_2022_1").get(0),mapper.toDTO(userStory));
    }
}
