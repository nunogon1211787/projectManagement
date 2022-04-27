package switch2021.project.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switch2021.project.model.*;
import switch2021.project.model.Project.Project;
import switch2021.project.model.Sprint.Sprint;
import switch2021.project.model.Sprint.SprintID;
import switch2021.project.model.UserStory.UserStory;
import switch2021.project.model.UserStory.UserStoryID;
import switch2021.project.model.UserStory.UserStoryStore;
import switch2021.project.model.valueObject.BusinessSector;
import switch2021.project.model.valueObject.Customer;
import switch2021.project.model.Typology.Typology;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class AddUserStoryToSprintBacklogControllerTest {

    @Test
    @DisplayName("Add Story to backlog")
    public void addStoryToBacklog() {
        //Arrange
        Company company = new Company();
        Typology typo = company.getTypologyRepository().findTypologyByDescription("Fixed Cost");
        Customer customer = company.getCustomerStore().getCustomerByName("Teste");
        BusinessSector sector = company.getBusinessSectorStore().getBusinessSectorByDescription("sector");
        Project project = company.getProjectStore().createAndSaveProject( "prototype", "test1234", customer,
                typo, sector, LocalDate.now(), 7, 5000);
        Sprint sprint = project.getSprintList().createAndSaveSprint("Project_2022_1", "Project_2022_1_Sprint 1", "Sprint Name", 2);
        sprint.setStartDate(LocalDate.now());
        SprintID sprintID = new SprintID("Project_2022_1_Sprint 1");
        UserStoryID userStoryId = new UserStoryID("Project_2022_1_As a PO, i want to test this string");
        String code = project.getProjectCode().getCode();

        UserStory us = new UserStory(code, userStoryId.toString(), "As a PO, i want to test this string", 1, "UserStory description", 5);
        UserStoryStore userStoryStore = new UserStoryStore();
        userStoryStore.save(us);
        //Act
        AddUserStoryToSprintBacklogController addStory = new AddUserStoryToSprintBacklogController(company);
        addStory.getProject(code);
        addStory.getSprintStore().findSprintById("Project_2022_1_Sprint 1");
        addStory.getUserStoryStore().findUserStoryById(userStoryId.toString());
        addStory.getUserStoryStore().save(us);
        //Assert
        assertEquals(1, addStory.getUserStoryStore().getUserStoryList().size());
    }
}


