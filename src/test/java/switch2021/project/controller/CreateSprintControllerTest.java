package switch2021.project.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switch2021.project.model.*;
import switch2021.project.stores.ProjectStore;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CreateSprintControllerTest {


    @Test
    @DisplayName("To search the list of projects that the PM is current in")
    public void getCurrentProjectListByUserEmailSuccess() {
        //Arrange
        Company company = new Company();
        CreateSprintController controller = new CreateSprintController(company);

        ProjectStore projectStore = company.getProjectStore();
        Typology typo = company.getTypologyStore().getTypologyByDescription("Fixed Cost");
        Customer customer = company.getCustomerStore().getCustomerByName("isep");
        BusinessSector sector = company.getBusinessSectorStore().getBusinessSectorByDescription("it");
        Project currentProject = company.getProjectStore().createProject("prototype4", "proj4Prototype", customer,
                typo, sector, LocalDate.now().minusDays(7), 2, 4000);
        currentProject.setEndDate(LocalDate.now().plusDays(7));
        projectStore.saveNewProject(currentProject);

        UserProfile profile = company.getUserProfileStore().getUserProfile("Visitor");
        SystemUser user2 = new SystemUser("joana", "joana@beaver.com", "tester", "ghi", "ghi", "photo", profile);
        LocalDate startDateMm = LocalDate.now().minusDays(7);
        LocalDate endDateMm = LocalDate.now().plusDays(7);
        Resource joana = new Resource(user2, startDateMm, endDateMm, 100, 1);
        currentProject.getProjectTeam().addResourceToTeam(joana);

        // Act
        List<Project> projectListActual = controller.getCurrentProjectListByUserEmail("joana@beaver.com");
        int sizeExpected = projectListActual.size();
        // Assert
        assertEquals(1, sizeExpected);
    }


    @Test
    @DisplayName("To search a project")
    public void getProject() {
        //Arrange
        Company company = new Company();
        CreateSprintController controller = new CreateSprintController(company);

        ProjectStore projectStore = company.getProjectStore();
        Typology typo = company.getTypologyStore().getTypologyByDescription("Fixed Cost");
        Customer customer = company.getCustomerStore().getCustomerByName("isep");
        BusinessSector sector = company.getBusinessSectorStore().getBusinessSectorByDescription("it");
        Project currentProject = company.getProjectStore().createProject("prototype4", "proj4Prototype", customer,
                typo, sector, LocalDate.now().minusDays(7), 2, 4000);
        currentProject.setEndDate(LocalDate.now().plusDays(7));
        projectStore.saveNewProject(currentProject);

        Project proj1 = company.getProjectStore().createProject("prototype1", "proj1Prototype", customer,
                typo, sector, LocalDate.now().minusDays(7), 2, 3000);
        proj1.setEndDate(LocalDate.now().plusDays(7));
        projectStore.saveNewProject(proj1);

        UserProfile profile = company.getUserProfileStore().getUserProfile("Visitor");
        SystemUser user2 = new SystemUser("joana", "joana@beaver.com", "tester", "ghi", "ghi", "photo", profile);
        LocalDate startDateMm = LocalDate.now().minusDays(7);
        LocalDate endDateMm = LocalDate.now().plusDays(7);
        Resource joana = new Resource(user2, startDateMm, endDateMm, 100, 1);
        currentProject.getProjectTeam().addResourceToTeam(joana);
        proj1.getProjectTeam().addResourceToTeam(joana);

        controller.getCurrentProjectListByUserEmail("joana@beaver.com");
        //Act
        Project projectActual = controller.getProject("Project_2022_1");
        //Assert
        assertEquals(currentProject, projectActual);
    }

//    @Test
//    @DisplayName("Test to create and save a sprint")
//    public void createAndSaveSprint() {
//
//        //Arrange
//        Company company = new Company();
//        CreateSprintController controllerTest = new CreateSprintController(company);
//
//        ProjectStore projectStore = company.getProjectStore();
//        Typology typo = company.getTypologyStore().getTypologyByDescription("Fixed Cost");
//        Customer customer = company.getCustomerStore().getCustomerByName("isep");
//        BusinessSector sector = company.getBusinessSectorStore().getBusinessSectorByDescription("it");
//
//        Project currentProject = company.getProjectStore().createProject("prototype4", "proj4Prototype", customer,
//                typo, sector, LocalDate.now().minusDays(7), 2, 4000);
//        currentProject.setEndDate(LocalDate.now().plusDays(7));
//        currentProject.setSprintDuration(2);
//        projectStore.saveNewProject(currentProject);
//
////        Project proj1 = company.getProjectStore().createProject("prototype1", "proj1Prototype", customer,
////                typo, sector, LocalDate.now().minusDays(7), 2, 3000);
////        proj1.setEndDate(LocalDate.now().plusDays(7));
////        projectStore.saveNewProject(proj1);
//
//        UserProfile profile = company.getUserProfileStore().getUserProfile("Visitor");
//        SystemUser user2 = new SystemUser("joana", "joana@beaver.com", "tester", "ghi", "ghi", "photo", profile);
//        LocalDate startDateMm = LocalDate.now().minusDays(7);
//        LocalDate endDateMm = LocalDate.now().plusDays(7);
//        Resource joana = new Resource(user2, startDateMm, endDateMm, 100, 1);
//        currentProject.getProjectTeam().addResourceToTeam(joana);
//
//        controllerTest.getProject("Project_2022_1");
//
//        Sprint sprint = controllerTest.createSprint("Sprint_1", LocalDate.of(2022, 1, 1));
//        controllerTest.saveSprint();
//
//        //Assert
//        assertEquals(sprint, currentProject.getSprintStore().getSprint(1));
//    }

}
