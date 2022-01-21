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
        Project project = company.getProjectStore().createProject("testCode", "prototype", "test1234", customer,
                typo, sector, LocalDate.now(), 7, 5000);
        Sprint sprint = project.getSprintStore().createSprint("Sprint 1", LocalDate.now(), 2);
        project.getSprintStore().addSprint(sprint);

        company.getProjectStore().addProject(project);
        UserStory userStory = company.getProjectStore().getProductBacklog("testCode").createUserStory(1, new UserStoryStatus("TODO"), 1, "Fazer coisas cool");
        company.getProjectStore().getProductBacklog("testCode").addUserStory(userStory);

        //Act
        AddUserStoryToSprintBacklogController addStory = new AddUserStoryToSprintBacklogController(company, 1, "testCode", 5);
        addStory.addUserStoryToSprintBacklog();
        UserStoryOfSprint value = project.getSprintStore().getSprintList().get(0).getSprintBacklog().getUserStoryOfSprintList().get(0);

//    @Test
//    @DisplayName("Add Story to backlog")
//    public void addStoryToBacklog() {
//        //Assert
//        assertEquals(userStory, value.getUserStoryOfSprint());
//    }
    }

}
