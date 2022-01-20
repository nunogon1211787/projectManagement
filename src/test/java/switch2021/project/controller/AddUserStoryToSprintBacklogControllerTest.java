package switch2021.project.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switch2021.project.model.*;
import switch2021.project.utils.App;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AddUserStoryToSprintBacklogControllerTest {

    Company company = App.getInstance().getCompany();
    Project proj;
    SprintBacklog sprintBacklog = new SprintBacklog();
    UserStory userStory;

    @BeforeEach
    public void init() {
        //Arrange
        LocalDate date = LocalDate.of(2021, 12, 12);
        company.getBusinessSectorStore().addBusinessSector(company.getBusinessSectorStore().createBusinessSector("sector"));
        company.getCustomerStore().add(company.getCustomerStore().createCustomer("Teste", "Teste"));

        //Project
        Typology typo = company.getTypologyStore().getTypology("Fixed Cost");
        Customer customer = company.getCustomerStore().getCustomerByName("Teste");
        BusinessSector sector = company.getBusinessSectorStore().getBusinessSectorByDescription("sector");

        proj = company.getProjectStore().createProject("testCode", "prototype", "test1234", customer,
                typo, sector, date, 7, 5000);
        company.getProjectStore().addProject(proj);

        //Sprint
        proj.getSprintStore().addSprint(proj.getSprintStore().createSprint("nameTest",LocalDate.now(),5));

        //UserStory
        UserStoryStatus status = new UserStoryStatus("statusTest");
        userStory = new UserStory(status, 2,"teste");
        proj.getProductBacklog().addUserStory(userStory);

    }


    //Rever teste
    @Test
    @DisplayName("Add Story to backlog")
    public void addStoryToBacklog() {
        //Assert
        AddUserStoryToSprintBacklogController addStory = new AddUserStoryToSprintBacklogController(1,1, "testCode", 5);

        //Result
        assertEquals(1,proj.getSprintStore().getSprint(1).getSprintBacklog().getUserStoryOfSprintList().size());
    }

    @Test
    @DisplayName("Add Story to backlog fail - User Story already exists")
    public void addStoryToBacklogFail() {
        //Assert
        AddUserStoryToSprintBacklogController addStory =
                new AddUserStoryToSprintBacklogController(1,1, "testCode", 5);

        assertThrows(IllegalArgumentException.class, () -> {
        AddUserStoryToSprintBacklogController coppiedUserStory =
                new AddUserStoryToSprintBacklogController(1,1, "testCode", 5);});

    }
}
