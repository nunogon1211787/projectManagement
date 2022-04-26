package switch2021.project.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switch2021.project.model.*;
import switch2021.project.model.Project.Project;
import switch2021.project.model.UserStory.UserStory;
import switch2021.project.model.valueObject.BusinessSector;
import switch2021.project.model.valueObject.Customer;
import switch2021.project.model.Typology.Typology;

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
        Typology typo = company.getTypologyRepository().findTypologyByDescription("Fixed Cost");
        Customer customer = company.getCustomerStore().getCustomerByName("isep");
        BusinessSector sector = company.getBusinessSectorStore().getBusinessSectorByDescription("it");

        Project proj1 = company.getProjectStore().createAndSaveProject("prototype1", "proj1Prototype", customer,
                typo, sector, LocalDate.of(2022, 1, 1), 2, 3000);
        proj1.setEndDate(LocalDate.of(2022, 11, 30));


        //UserStory Creation and save on Product Backlog

        UserStory userStory1 = new UserStory("Project_" + LocalDate.now().getYear() + "_" + 1, "Project_2022_1_As a PO, i want to test this string","As a PO, i want to test this string", 4, "userstory1", 5);;
        proj1.getUserStoryStore().getUserStoryList().add(userStory1);
        UserStory userStory2 = new UserStory("Project_" + LocalDate.now().getYear() + "_" + 1,"Project_2022_1_As a PO, i want to test this string","As a sm, i want to test this string", 3, "userstory2", 5);
        proj1.getUserStoryStore().getUserStoryList().add(userStory2);
        UserStory userStory3 = new UserStory("Project_" + LocalDate.now().getYear() + "_" + 1,"Project_2022_1_As a PO, i want to test this string","As a user, i want to test this string", 1, "userstory3", 5);
        proj1.getUserStoryStore().getUserStoryList().add(userStory3);
        UserStory userStory4 = new UserStory("Project_" + LocalDate.now().getYear() + "_" + 1,"Project_2022_1_As a PO, i want to test this string","As a diretor, i want to test this string", 0, "userstory4", 5);
        proj1.getUserStoryStore().getUserStoryList().add(userStory4);

        //Act
        refineUserStoryController.getProject("Project_2022_1");
        refineUserStoryController.getUserStoryStore();
        //TODO CDC ver ID US refined
        refineUserStoryController.getUserStory(userStory2.getUserStoryID());
        refineUserStoryController.createUserStory("newUserStoryCreated", 3);
        refineUserStoryController.createUserStory("newUserStoryCreated2", 5);

        //Assert
        //check if project was saved on the list
        assertEquals(proj1, refineUserStoryController.getProject("Project_2022_1"));
    }

//    @Test
//    @DisplayName("Refine User Story Controller - Check Product Backlog")
//    public void RefineUserStoryController2() {
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
//        UserStoryStatus statusToDo = company.getUserStoryStatusStore().getUserStoryStatusByDescription("To do");
//        UserStory userStory1 = new UserStory("Project_2022_1_As a PO, i want to test this string","As a PO, i want to test this string", 4, "userstory1", 5);
//        proj1.getUserStoryStore().getUserStoryList().add(userStory1);
//        UserStory userStory2 = new UserStory("Project_2022_1_As a PO, i want to test this string","As a SO, i want to test this string", 3, "userstory2", 5);
//        proj1.getUserStoryStore().getUserStoryList().add(userStory2);
//        UserStory userStory3 = new UserStory("Project_2022_1_As a PO, i want to test this string","As a APO, i want to test this string", 1, "userstory3", 5);
//        proj1.getUserStoryStore().getUserStoryList().add(userStory3);
//        UserStory userStory4 = new UserStory("Project_2022_1_As a PO, i want to test this string","As a dPO, i want to test this string", 0, "userstory4", 5);
//        proj1.getUserStoryStore().getUserStoryList().add(userStory4);
//
//        //Act
//        refineUserStoryController.getProject("Project_2022_1");
//        refineUserStoryController.getUserStoryStore();
//        //TODO CDC ver id usrefined
////        refineUserStoryController.getUserStory(2);
//        boolean newUserStory = refineUserStoryController.createUserStory("newUserStoryCreated", 3);
//        boolean newUserStory2 = refineUserStoryController.createUserStory("newUserStoryCreated2", 5);
//
//        //Assert
//        //check if User Stories were added on product Backlog (4 created + 2 added after refine)
//        assertEquals(6, refineUserStoryController.getUserStoryStore().getUserStoryList().size());
//        //check if User Story2 is added and we can get
////        assertEquals(proj1.getUserStoryStore().getUserStoryList().get(2), refineUserStoryController.getUserStory(2));
//        //check New User Story Creation
//        assertTrue(newUserStory);
//        assertTrue(newUserStory2);
//    }

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
//        proj1.getUserStoryStore().getUserStoryList().add(userStory1);
//        UserStory userStory2 = new UserStory("As a SPO, i want to test this string", 3, "userstory2", 5);
//        proj1.getUserStoryStore().getUserStoryList().add(userStory2);
//        UserStory userStory3 = new UserStory("As a APO, i want to test this string", 1, "userstory3", 5);
//        proj1.getUserStoryStore().getUserStoryList().add(userStory3);
//        UserStory userStory4 = new UserStory("As a SPO, i want to test this string", 0, "userstory4", 5);
//        proj1.getUserStoryStore().getUserStoryList().add(userStory4);
//
//        //Act
//        refineUserStoryController.getProject("Project_2022_1");
//        refineUserStoryController.getUserStoryStore();
//        refineUserStoryController.getUserStory(2);
//        boolean result = refineUserStoryController.updateRefinedUserStoryStatus(userStory2);
//        String refinedUserStoryStatus = userStory2.getUserStoryStatus().getDescription().getText();
//
//        //Assert
//        assertEquals("Refined", refinedUserStoryStatus);
//        assertTrue(result);
//    }
}
