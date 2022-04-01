package switch2021.project.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switch2021.project.model.*;
import switch2021.project.model.Project.Project;
import switch2021.project.model.Sprint.Sprint;
import switch2021.project.model.valueObject.BusinessSector;
import switch2021.project.model.valueObject.Customer;
import switch2021.project.model.valueObject.Typology;

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

        company.getProjectStore().getProjectByCode("Project_2022_1").getProductBacklog().createAndSaveUserStory( "As a PO, i want to test this string",
                1, "Fazer coisas cool",5);

        //Act
        AddUserStoryToSprintBacklogController addStory = new AddUserStoryToSprintBacklogController(company);
        addStory.getProject("Project_2022_1");
        addStory.getSprintStore();
        addStory.getSprint(1);
        addStory.getProductBacklog();

        addStory.addUserStoryToSprintBacklog(1);

        //Assert
        assertEquals(company.getProjectStore().getProjectByCode("Project_2022_1").getProductBacklog().getUserStoryList().get(0), company.getProjectStore().getProjectByCode("Project_2022_1").getCurrentSprint().getListOfUsFromSprintBacklog().get(0));
    }
}


