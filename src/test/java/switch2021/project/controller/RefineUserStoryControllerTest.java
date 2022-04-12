package switch2021.project.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switch2021.project.model.*;
import switch2021.project.model.Project.Project;
import switch2021.project.model.UserStory.UserStory;
import switch2021.project.model.valueObject.BusinessSector;
import switch2021.project.model.valueObject.Customer;
import switch2021.project.model.Typology.Typology;
import switch2021.project.model.valueObject.UserStoryStatus;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RefineUserStoryControllerTest {

    @Test
    @DisplayName("Refine User Story Controller - Check if project was saved on the list")
    public void RefineUserStoryController() {
        Company company = new Company();
        RefineUserStoryController refineUserStoryController = new RefineUserStoryController(company);

        //Arrange
        //Project Creation and save on the list
        Typology typo = company.getTypologyStore().getTypologyByDescription("Fixed Cost");
        Customer customer = company.getCustomerStore().getCustomerByName("isep");
        BusinessSector sector = company.getBusinessSectorStore().getBusinessSectorByDescription("it");

        Project proj1 = company.getProjectStore().createProject("prototype1", "proj1Prototype", customer,
                typo, sector, LocalDate.of(2022, 1, 1), 2, 3000);
        proj1.setEndDate(LocalDate.of(2022, 11, 30));

        company.getProjectStore().saveNewProject(proj1);

        //UserStory Creation and save on Product Backlog
        UserStoryStatus statusToDo = company.getUserStoryStatusStore().getUserStoryStatusByDescription("To do");
        UserStory userStory1 = new UserStory("As a PO, i want to test this string", 4, "userstory1", 5);
        userStory1.setIdUserStory(0);
        proj1.getProductBacklog().getUserStoryList().add(userStory1);
        UserStory userStory2 = new UserStory("As a sm, i want to test this string", 3, "userstory2", 5);
        userStory2.setIdUserStory(1);
        proj1.getProductBacklog().getUserStoryList().add(userStory2);
        UserStory userStory3 = new UserStory("As a user, i want to test this string", 1, "userstory3", 5);
        userStory3.setIdUserStory(2);
        proj1.getProductBacklog().getUserStoryList().add(userStory3);
        UserStory userStory4 = new UserStory("As a diretor, i want to test this string", 0, "userstory4", 5);
        userStory4.setIdUserStory(3);
        proj1.getProductBacklog().getUserStoryList().add(userStory4);

        //Act
        refineUserStoryController.getProject("Project_2022_1");
        refineUserStoryController.getProductBacklog();
        refineUserStoryController.getUserStory(2);
        refineUserStoryController.createUserStory("newUserStoryCreated", 3);
        refineUserStoryController.createUserStory("newUserStoryCreated2", 5);

        //Assert
        //check if project was saved on the list
        assertEquals(proj1, refineUserStoryController.getProject("Project_2022_1"));
    }

    @Test
    @DisplayName("Refine User Story Controller - Check Product Backlog")
    public void RefineUserStoryController2() {
        Company company = new Company();
        RefineUserStoryController refineUserStoryController = new RefineUserStoryController(company);

        //Arrange
        //Project Creation and save on the list
        Typology typo = company.getTypologyStore().getTypologyByDescription("Fixed Cost");
        Customer customer = company.getCustomerStore().getCustomerByName("isep");
        BusinessSector sector = company.getBusinessSectorStore().getBusinessSectorByDescription("it");

        Project proj1 = company.getProjectStore().createProject("prototype1", "proj1Prototype", customer,
                typo, sector, LocalDate.of(2022, 1, 1), 2, 3000);
        proj1.setEndDate(LocalDate.of(2022, 11, 30));

        company.getProjectStore().saveNewProject(proj1);

        //UserStory Creation and save on Product Backlog
        UserStoryStatus statusToDo = company.getUserStoryStatusStore().getUserStoryStatusByDescription("To do");
        UserStory userStory1 = new UserStory("As a PO, i want to test this string", 4, "userstory1", 5);
        userStory1.setIdUserStory(0);
        proj1.getProductBacklog().getUserStoryList().add(userStory1);
        UserStory userStory2 = new UserStory("As a SO, i want to test this string", 3, "userstory2", 5);
        userStory2.setIdUserStory(1);
        proj1.getProductBacklog().getUserStoryList().add(userStory2);
        UserStory userStory3 = new UserStory("As a APO, i want to test this string", 1, "userstory3", 5);
        userStory3.setIdUserStory(2);
        proj1.getProductBacklog().getUserStoryList().add(userStory3);
        UserStory userStory4 = new UserStory("As a dPO, i want to test this string", 0, "userstory4", 5);
        userStory4.setIdUserStory(3);
        proj1.getProductBacklog().getUserStoryList().add(userStory4);

        //Act
        refineUserStoryController.getProject("Project_2022_1");
        refineUserStoryController.getProductBacklog();
        refineUserStoryController.getUserStory(2);
        boolean newUserStory = refineUserStoryController.createUserStory("newUserStoryCreated", 3);
        boolean newUserStory2 = refineUserStoryController.createUserStory("newUserStoryCreated2", 5);

        //Assert
        //check if User Stories were added on product Backlog (4 created + 2 added after refine)
        assertEquals(6, refineUserStoryController.getProductBacklog().getUserStoryList().size());
        //check if User Story2 is added and we can get
        assertEquals(proj1.getProductBacklog().getUserStoryList().get(2), refineUserStoryController.getUserStory(2));
        //check New User Story Creation
        assertTrue(newUserStory);
        assertTrue(newUserStory2);
    }

//TODO CDC validar se teste faz sentido tendo em conta que apenas testa status

//    @Test
//    @DisplayName("Refine User Story Controller - Check if status of user story refined changed")
//    public void RefineUserStoryController3() {
//        Company company = new Company();
//        RefineUserStoryController refineUserStoryController = new RefineUserStoryController(company);
//
//        //Arrange
//        //Project Creation and save on the list
//        Typology typo = company.getTypologyStore().getTypologyByDescription("Fixed Cost");
//        Customer customer = company.getCustomerStore().getCustomerByName("isep");
//        BusinessSector sector = company.getBusinessSectorStore().getBusinessSectorByDescription("it");
//
//        Project proj1 = company.getProjectStore().createProject("prototype1", "proj1Prototype", customer,
//                typo, sector, LocalDate.of(2022, 1, 1), 2, 3000);
//        proj1.setEndDate(LocalDate.of(2022, 11, 30));
//
//        company.getProjectStore().saveNewProject(proj1);
//
//        //UserStory Creation and save on Product Backlog
//        UserStory userStory1 = new UserStory("As a PO, i want to test this string", 4, "userstory1", 5);
//        proj1.getProductBacklog().getUserStoryList().add(userStory1);
//        UserStory userStory2 = new UserStory("As a SPO, i want to test this string", 3, "userstory2", 5);
//        proj1.getProductBacklog().getUserStoryList().add(userStory2);
//        UserStory userStory3 = new UserStory("As a APO, i want to test this string", 1, "userstory3", 5);
//        proj1.getProductBacklog().getUserStoryList().add(userStory3);
//        UserStory userStory4 = new UserStory("As a SPO, i want to test this string", 0, "userstory4", 5);
//        proj1.getProductBacklog().getUserStoryList().add(userStory4);
//
//        //Act
//        refineUserStoryController.getProject("Project_2022_1");
//        refineUserStoryController.getProductBacklog();
//        refineUserStoryController.getUserStory(2);
//        boolean result = refineUserStoryController.updateRefinedUserStoryStatus(userStory2);
//        String refinedUserStoryStatus = userStory2.getUserStoryStatus().getDescription().getText();
//
//        //Assert
//        assertEquals("Refined", refinedUserStoryStatus);
//        assertTrue(result);
//    }
}
