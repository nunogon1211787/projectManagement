package switch2021.project.controller;

import org.junit.jupiter.api.Test;
import switch2021.project.mapper.ScrumBoardMapper;
import switch2021.project.model.*;
import switch2021.project.model.Project.Project;
import switch2021.project.model.Sprint.Sprint;
import switch2021.project.model.valueObject.BusinessSector;
import switch2021.project.model.valueObject.Customer;
import switch2021.project.model.valueObject.Typology;

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
        Project project = company.getProjectStore().createProject("prototype", "test1234", customer,
                typo, sector, LocalDate.now(), 7, 5000);
        project.getProductBacklog().createAndSaveUserStory("As a PO, i want to test this string", 3, "Description", 12);
        Sprint sprint = project.getSprintList().createSprint("Sprint 1", LocalDate.now(), 2);
        project.getSprintList().saveSprint(sprint);

        company.getProjectStore().saveNewProject(project);
        company.getProjectStore().getProjectByCode("Project_2022_1").getSprintList().getCurrentSprint().getSprintBacklog().saveUserStoryToSprintBacklog(project.getProductBacklog().getUserStoryList().get(0));

        //Act
        ScrumBoardMapper mapper = new ScrumBoardMapper();
        GetScrumBoardController controller = new GetScrumBoardController(company, mapper);
        controller.getProject("Project_2022_1");
        controller.getCurrentSprint();
        controller.getSprintBacklog();
        controller.getUserStoryList();


        //Assert
        assertEquals(controller.getScrumBoard().get(0).getUserStoryId(), mapper.toDTO(project.getProductBacklog().getUserStoryList().get(0)).getUserStoryId());
        assertEquals(controller.getScrumBoard().get(0).getUserStoryPriority(), mapper.toDTO(project.getProductBacklog().getUserStoryList().get(0)).getUserStoryPriority());
        assertEquals(controller.getScrumBoard().get(0).getUserStoryTimeEstimate(), mapper.toDTO(project.getProductBacklog().getUserStoryList().get(0)).getUserStoryTimeEstimate());
        assertEquals(controller.getScrumBoard().get(0).getUserStoryStatus(), mapper.toDTO(project.getProductBacklog().getUserStoryList().get(0)).getUserStoryStatus());
        assertEquals(controller.getScrumBoard().get(0).getUserStoryTitle(), mapper.toDTO(project.getProductBacklog().getUserStoryList().get(0)).getUserStoryTitle());
        assertEquals(controller.getScrumBoard().get(0).getUserStoryDescription(), mapper.toDTO(project.getProductBacklog().getUserStoryList().get(0)).getUserStoryDescription());
    }
}
