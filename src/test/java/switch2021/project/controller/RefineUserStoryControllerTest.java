package switch2021.project.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switch2021.project.model.*;
import switch2021.project.stores.ProjectStore;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RefineUserStoryControllerTest {
//    Company company;

    Company company = new Company();
    RefineUserStoryController refineUserStoryController = new RefineUserStoryController(company);


    @Test
    @DisplayName("Refine User Story Controller")
    public void RefineUserStoryController(){

        //Assert
        //Project Creation and save on the list
        Typology typo = company.getTypologyStore().getTypology("Fixed Cost");
        Customer customer = company.getCustomerStore().getCustomerByName("isep");
        BusinessSector sector = company.getBusinessSectorStore().getBusinessSectorByDescription("it");

        Project proj1 = company.getProjectStore().createProject( "prototype1", "proj1Prototype", customer,
                typo, sector, LocalDate.of(2022, 1, 1), 2, 3000);
        proj1.setEndDate(LocalDate.of(2022, 11, 30));

        ProjectStore projSt = company.getProjectStore();
        projSt.saveNewProject(proj1);
        ProductBacklog prodBack = proj1.getProductBacklog();

        //UserStory Creation
        UserStoryStatus StatusToDo = company.getUserStoryStatusStore().getUserStoryStatusByDescription("To Do");
        UserStory userStory1 = new UserStory(StatusToDo,4,"userstory1");
        prodBack.saveUserStory(userStory1);
        UserStory userStory2 = new UserStory(StatusToDo,3,"userstory2");
        prodBack.saveUserStory(userStory2);
        UserStory userStory3 = new UserStory(StatusToDo,1,"userstory3");
        prodBack.saveUserStory(userStory3);
        UserStory userStory4 = new UserStory(StatusToDo,0,"userstory4");
        prodBack.saveUserStory(userStory4);

        UserStory userStoryToRefine = proj1.getProductBacklog().getUserStoryById(2);
        refineUserStoryController.updateRefinedUserStoryStatus(userStoryToRefine);

        //check if project was saved on the list
        assertEquals(1,projSt.getProjectList().size());
        assertEquals(1,refineUserStoryController.getProjectList().size());
        //check if User Story were added on product Backlog
        assertEquals(4,proj1.getProductBacklog().getUserStoryList().size());
        assertEquals(4, refineUserStoryController.getProject("Project_2022_1").getProductBacklog().getUserStoryList().size());
        //check if method getProject is working
        assertEquals(proj1,refineUserStoryController.getProject("Project_2022_1"));
        //get User Story
        assertEquals(userStory2, userStoryToRefine);
        assertEquals(userStory2, refineUserStoryController.getProject("Project_2022_1").getProductBacklog().getUserStoryById(2));
    }

    @Test
    @DisplayName("Refine User Story Controller")
    public void RefineUserStoryController2(){

        //Assert
        //Project Creation and save on the list
        Typology typo = company.getTypologyStore().getTypology("Fixed Cost");
        Customer customer = company.getCustomerStore().getCustomerByName("isep");
        BusinessSector sector = company.getBusinessSectorStore().getBusinessSectorByDescription("it");

        Project proj1 = company.getProjectStore().createProject( "prototype1", "proj1Prototype", customer,
                typo, sector, LocalDate.of(2022, 1, 1), 2, 3000);
        proj1.setEndDate(LocalDate.of(2022, 11, 30));

        ProjectStore projSt = company.getProjectStore();
        projSt.saveNewProject(proj1);
        ProductBacklog prodBack = proj1.getProductBacklog();

        //UserStory Creation
        UserStoryStatus StatusToDo = company.getUserStoryStatusStore().getUserStoryStatusByDescription("To Do");
        UserStory userStory1 = new UserStory(StatusToDo,4,"userstory1");
        prodBack.saveUserStory(userStory1);
        UserStory userStory2 = new UserStory(StatusToDo,3,"userstory2");
        prodBack.saveUserStory(userStory2);
        UserStory userStory3 = new UserStory(StatusToDo,1,"userstory3");
        prodBack.saveUserStory(userStory3);
        UserStory userStory4 = new UserStory(StatusToDo,0,"userstory4");
        prodBack.saveUserStory(userStory4);

        UserStory userStoryToRefine = proj1.getProductBacklog().getUserStoryById(2);
        refineUserStoryController.updateRefinedUserStoryStatus(userStoryToRefine);
        UserStoryStatus statusRefined = company.getUserStoryStatusStore().getUserStoryStatusByDescription("Refined");
        assertEquals(statusRefined, userStoryToRefine.getUserStoryStatus());
//        assertEquals(userStory2, refineUserStoryController.getProject("proj1Code").getProductBacklog().getUserStoryById(2));
    }

    @Test
    @DisplayName("Refine User Story Controller")
    public void RefineUserStoryControllerCreateUS(){

        //Assert
        //Project Creation and save on the list
        Typology typo = company.getTypologyStore().getTypology("Fixed Cost");
        Customer customer = company.getCustomerStore().getCustomerByName("isep");
        BusinessSector sector = company.getBusinessSectorStore().getBusinessSectorByDescription("it");

        Project proj1 = company.getProjectStore().createProject( "prototype1", "proj1Prototype", customer,
                typo, sector, LocalDate.of(2022, 1, 1), 2, 3000);
        proj1.setEndDate(LocalDate.of(2022, 11, 30));

        ProjectStore projSt = company.getProjectStore();
        projSt.saveNewProject(proj1);
        ProductBacklog prodBack = proj1.getProductBacklog();

        //UserStory Creation
        UserStoryStatus StatusToDo = company.getUserStoryStatusStore().getUserStoryStatusByDescription("To Do");
        UserStory userStory1 = new UserStory(StatusToDo,4,"userstory1");
        prodBack.saveUserStory(userStory1);
        UserStory userStory2 = new UserStory(StatusToDo,3,"userstory2");
        prodBack.saveUserStory(userStory2);
        UserStory userStory3 = new UserStory(StatusToDo,1,"userstory3");
        prodBack.saveUserStory(userStory3);
        UserStory userStory4 = new UserStory(StatusToDo,0,"userstory4");
        prodBack.saveUserStory(userStory4);

        UserStory userStoryToRefine = proj1.getProductBacklog().getUserStoryById(2);
        refineUserStoryController.updateRefinedUserStoryStatus(userStoryToRefine);
        UserStoryStatus statusRefined = company.getUserStoryStatusStore().getUserStoryStatusByDescription("Refined");
        boolean newUserStory1 = refineUserStoryController.createUserStory("Project_2022_1", userStoryToRefine, "123testtest",3,StatusToDo);
        boolean newUserStory2 = refineUserStoryController.createUserStory("Project_2022_1", userStoryToRefine, "1234testtest",3,StatusToDo);


        assertTrue(newUserStory1);
        assertTrue(newUserStory2);
        assertEquals(6, proj1.getProductBacklog().getUserStoryList().size());
    }

}
