package switch2021.project.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switch2021.project.model.*;
import switch2021.project.stores.ProjectStore;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RefineUserStoryControllerTest {
//    Company company;

    Company company = new Company();
    RefineUserStoryController refineUserStoryController = new RefineUserStoryController(company);


    @Test
    @DisplayName("Refine User Story Controller")
    public void RefineUserStoryController(){

        Company company = new Company();
        RefineUserStoryController refineUserStoryController = new RefineUserStoryController(company);

        //Arrange
        //Project Creation and save on the list
        Typology typo = company.getTypologyStore().getTypologyByDescription("Fixed Cost");
        Customer customer = company.getCustomerStore().getCustomerByName("isep");
        BusinessSector sector = company.getBusinessSectorStore().getBusinessSectorByDescription("it");

        Project proj1 = company.getProjectStore().createProject( "prototype1", "proj1Prototype", customer,
                typo, sector, LocalDate.of(2022, 1, 1), 2, 3000);
        proj1.setEndDate(LocalDate.of(2022, 11, 30));

        company.getProjectStore().saveNewProject(proj1);

        //UserStory Creation and save on Product Backlog
        UserStoryStatus statusToDo = company.getUserStoryStatusStore().getUserStoryStatusByDescription("To Do");
        UserStory userStory1 = new UserStory(statusToDo,4,"userstory1");
        proj1.getProductBacklog().saveUserStory(userStory1);
        UserStory userStory2 = new UserStory(statusToDo,3,"userstory2");
        proj1.getProductBacklog().saveUserStory(userStory2);
        UserStory userStory3 = new UserStory(statusToDo,1,"userstory3");
        proj1.getProductBacklog().saveUserStory(userStory3);
        UserStory userStory4 = new UserStory(statusToDo,0,"userstory4");
        proj1.getProductBacklog().saveUserStory(userStory4);

        //Act
        refineUserStoryController.getProject("Project_2022_1");
        refineUserStoryController.getProductBacklog();
        refineUserStoryController.updateRefinedUserStoryStatus(userStory2);
        boolean newUserStory = refineUserStoryController.createUserStory("newUserStoryCreated", 3, statusToDo);
        boolean newUserStory2 = refineUserStoryController.createUserStory("newUserStoryCreated2", 5, statusToDo);

        //Assert
        //check if project was saved on the list
        assertEquals(proj1,refineUserStoryController.getProject("Project_2022_1"));
        //check if User Stories were added on product Backlog (4 created + 2 added after refine)
        assertEquals(6, refineUserStoryController.getProductBacklog().getUserStoryList().size());
        //check if User Story2 is added and we can get
        assertEquals(userStory2, refineUserStoryController.getUserStory(2));
        //check New User Story Creation
        assertTrue(newUserStory);
        assertTrue(newUserStory2);
    }
}
