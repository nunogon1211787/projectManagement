package switch2021.project.controller;

import org.junit.jupiter.api.Test;
import switch2021.project.model.*;
import switch2021.project.model.Project.*;
import switch2021.project.model.Sprint.Sprint;
import switch2021.project.model.UserStory.UserStory;
import switch2021.project.stores.UserStoryStatusStore;
import switch2021.project.model.valueObject.BusinessSector;
import switch2021.project.model.valueObject.Customer;
import switch2021.project.model.Typology.Typology;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UpdateStatusUsControllerTest {

    private Company company;
    private final UserStoryStatusStore userStoryStatusStore = new UserStoryStatusStore();

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
    void getUsAvailableStatusListSize() {
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
        assertEquals(expectedList.size(),actualList.size());
    }

    @Test
    void changeStatusOfUs() {
        //arrange
        company = new Company();
        UpdateStatusUsController update = new UpdateStatusUsController(company);
        this.userStoryStatusStore.populateDefault();
        LocalDate startDate2 = LocalDate.of(2022, 12, 31);
        Typology typo = company.getTypologyStore().getTypologyByDescription("Fixed Cost");
        Customer customer = company.getCustomerStore().getCustomerByName("ISEP");
        BusinessSector sector = company.getBusinessSectorStore().getBusinessSectorByDescription("Balloons");
        Project project = company.getProjectStore().createProject("prototype2", "test56", customer,
                typo, sector, startDate2, 7, 5000);
        company.getProjectStore().saveNewProject(project);
        UserStory userStory = new UserStory("As a PO, i want to test this string", 2, "Fazer tal", 5);
        UserStory userStory2 = new UserStory("As a PO, i want to test this string", 3, "Fazer tal e coiso", 5);
        Sprint sprint = project.getSprintList().createSprint("Sprintao", LocalDate.now(), 12);
        project.getSprintList().saveSprint(sprint);
        project.getCurrentSprint().saveUsInSprintBacklog(userStory);
        project.getCurrentSprint().saveUsInSprintBacklog(userStory2);

        //act

        userStory.setUserStoryStatus(userStoryStatusStore.getUserStoryStatusByDescription("Done"));
        update.changeStatusOfUs("Project_2022_1", 0, "In progress");

        //assert
        assertEquals(userStory.getUserStoryStatus(), userStoryStatusStore.getUserStoryStatusByDescription("In progress"));



    }
}