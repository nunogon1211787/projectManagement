package switch2021.project.controller;

import org.junit.jupiter.api.Test;
import switch2021.project.model.*;
import switch2021.project.stores.UserStoryStatusStore;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UpdateStatusUsControllerTest {

    private Company company;
    private Project project;
    private UserStory userStory;
    private UserStory userStory2;
    private Typology typo;
    private Customer customer;
    private BusinessSector sector;
    private Sprint sprint;
    private UserStoryStatusStore userStoryStatusStore = new UserStoryStatusStore();




    @Test
    void getUsAvailableStatusList() {
        //arrange
        company = new Company();
        UpdateStatusUsController update = new UpdateStatusUsController(company);

        //act
        String todo = "To do";
        String progress = "In progress";
        String done = "Done";
        List <String> expectedList = update.getUsAvailableStatusList();
        List <String> actualList = Arrays.asList(todo, progress, done);

        //assert
        assertEquals(expectedList,actualList);
    }

    @Test
    void changeStatusOfUs() {
        //arrange
        company = new Company();
        UpdateStatusUsController update = new UpdateStatusUsController(company);
        this.userStoryStatusStore.populateDefault();
        LocalDate startDate2 = LocalDate.of(2022, 12, 31);
        typo = company.getTypologyStore().getTypologyByDescription("Fixed Cost");
        customer = company.getCustomerStore().getCustomerByName("ISEP");
        sector = company.getBusinessSectorStore().getBusinessSectorByDescription("Balloons");
        project = company.getProjectStore().createProject("prototype2", "test56", customer,
                typo, sector, startDate2, 7, 5000);
        company.getProjectStore().saveNewProject(project);
        userStory = new UserStory("US001", 2, "Fazer tal",5);
        userStory2 = new UserStory("US001", 3, "Fazer tal e coiso",5);
        sprint = project.getSprints().createSprint("Sprintao", LocalDate.now(),12);
        project.getSprints().saveSprint(sprint);
        project.getCurrentSprint().getSprintBacklog().saveUserStoryToSprintBacklog(userStory);
        project.getCurrentSprint().getSprintBacklog().saveUserStoryToSprintBacklog(userStory2);

        //act

        userStory.setUserStoryStatus(userStoryStatusStore.getUserStoryStatusByDescription("Done"));
        update.changeStatusOfUs("Project_2022_1", 0, userStoryStatusStore.getUserStoryStatusByDescription("In progress"));

        //assert
        assertEquals(userStory.getUserStoryStatus(), userStoryStatusStore.getUserStoryStatusByDescription("In progress"));



    }
}