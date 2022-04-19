package switch2021.project.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switch2021.project.model.*;
import switch2021.project.model.Project.Project;
import switch2021.project.model.Sprint.Sprint;
import switch2021.project.model.Sprint.SprintID;
import switch2021.project.model.UserStory.UserStory;
import switch2021.project.model.UserStory.UserStoryId;
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
        Typology typo = company.getTypologyStore().getTypologyByDescription("Fixed Cost");
        Customer customer = company.getCustomerStore().getCustomerByName("Teste");
        BusinessSector sector = company.getBusinessSectorStore().getBusinessSectorByDescription("sector");
        Project project = company.getProjectStore().createProject( "prototype", "test1234", customer,
                typo, sector, LocalDate.now(), 7, 5000);
        Sprint sprint = project.getSprintList().createAndSaveSprint(1, "Project_2022_1_Sprint 1", "Sprint Name", 2);
        sprint.setStartDate(LocalDate.now());
        SprintID sprintID = new SprintID("Project_2022_1_Sprint 1");
        company.getProjectStore().saveNewProject(project);
        UserStoryId userStoryId = new UserStoryId("Project_2022_1_As a PO, i want to test this string");
        String code = project.getProjectCode().getCode();
        project.getUserStoryStore().createAndSaveUserStory(userStoryId.toString(), "As a PO, i want to test this string", 1, "Fazer coisas cool",5);
        UserStory us = new UserStory (userStoryId.toString(), "As a PO, i want to test this string", 1, "UserStory description", 5);
        //Act
        AddUserStoryToSprintBacklogController addStory = new AddUserStoryToSprintBacklogController(company);
        addStory.getProject(code);
        addStory.getSprintStore().findSprintById(sprintID);
        addStory.getUserStoryStore().findUserStoryById(userStoryId);
        //Assert
        assertEquals(1, addStory.getUserStoryStore().getUserStoryList().size());
    }
}


