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
    UserStory userStory;

    //Rever teste
/*    @Test
    @DisplayName("Add Story to backlog")
    public void addStoryToBacklog() {
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

        //Assert
        AddUserStoryToSprintBacklogController addStory = new AddUserStoryToSprintBacklogController(1,1, "testCode", 5);

        UserStoryOfSprint value = proj.getSprintStore().getSprintList().get(0).getSprintBacklog().getUserStoryOfSprintList().get(0);

        UserStory userStory = new UserStory(status, 2,"teste");
        UserStoryOfSprint expectedUS = new UserStoryOfSprint(userStory,5);

        //Result
        assertEquals(expectedUS.toString(),value.toString());
    }*/

    @Test
    @DisplayName("Add Story to backlog fail - User Story already exists")
    public void addStoryToBacklogFail() {
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



        //Assert
        AddUserStoryToSprintBacklogController addStory =
                new AddUserStoryToSprintBacklogController(1,1, "testCode", 5);

        assertThrows(IllegalArgumentException.class, () -> {
        AddUserStoryToSprintBacklogController coppiedUserStory =
                new AddUserStoryToSprintBacklogController(1,1, "testCode", 5);});
    }
}
