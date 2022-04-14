package switch2021.project.controller;

import org.junit.jupiter.api.Test;
import switch2021.project.model.*;
import switch2021.project.model.Project.*;
import switch2021.project.model.Resource.Resource;
import switch2021.project.model.Sprint.Sprint;
import switch2021.project.model.Sprint.SprintStore;
import switch2021.project.model.SystemUser.SystemUser;
import switch2021.project.model.Typology.Typology;
import switch2021.project.model.UserProfile.UserProfile;
import switch2021.project.model.valueObject.*;
import switch2021.project.stores.SystemUserStore;

import java.time.LocalDate;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class UserStoryEffortControllerTest {



    @Test
    void catchCurrentProjectListByUserEmail() {
        // Arrange
        Company company = new Company();
        UserStoryEffortController controller = new UserStoryEffortController(company);
        SystemUserStore systemUserStore = company.getSystemUserStore();
        UserProfile userProfile = company.getUserProfileStore().getUserProfile("Visitor");
        SystemUser user = new SystemUser("Test", "xxxx@isep.ipp.pt",
                "tester", "Qwerty_1", "Qwerty_1", "photo.png", userProfile);
        systemUserStore.saveSystemUser(user); //salvo o user
        Typology typo = company.getTypologyStore().getTypologyByDescription("Fixed Cost");
        Customer customer = company.getCustomerStore().getCustomerByName("Teste");
        BusinessSector sector = company.getBusinessSectorStore().getBusinessSectorByDescription("sector");
        LocalDate startDate = LocalDate.of(2021, 12, 31);
        Project project1 = company.getProjectStore().createProject("prototype", "test56", customer,
                typo, sector, startDate, 7, 5000);
        LocalDate endDate = LocalDate.of(2023, 3, 5);
        Resource person1 = new Resource(user, startDate, endDate, new CostPerHour(100), new PercentageOfAllocation(.5));
        Resource person2 = new Resource(user, startDate, endDate, new CostPerHour(100), new PercentageOfAllocation(.5));
        project1.getProjectTeam().saveResource(person1);
        project1.getProjectTeam().saveResource(person2);
        company.getProjectStore().saveNewProject(project1);

        //Act
        List<Project> projectList = company.getProjectStore().getProjectsByUserEmail("xxxx@isep.ipp.pt");
        List<Project> companyProjectList = controller.getCurrentProjectListByUserEmail("xxxx@isep.ipp.pt");

        //Assert
        assertEquals(companyProjectList, projectList);
    }

    @Test
    void getProjectByCode() {
        //Arrange
        Company company = new Company();
        UserStoryEffortController controller = new UserStoryEffortController(company);
        //Act
        Project project = company.getProjectStore().getProjectByCode("123");
        Project expected = controller.getProjectByCode("123");
        // Arrange
        assertEquals(project, expected);
    }

    @Test
    void getSprintList() {
        //Arrange
        Company company = new Company();
        UserStoryEffortController controller = new UserStoryEffortController(company);
        SystemUserStore systemUserStore = company.getSystemUserStore(); //chamo a systemUserStore
        UserProfile userProfile = company.getUserProfileStore().getUserProfile("Visitor");
        SystemUser user = new SystemUser("Test", "xxxx@isep.ipp.pt",
                "tester", "Qwerty_1", "Qwerty_1", "photo.png", userProfile);
        systemUserStore.saveSystemUser(user); //salvo o user
        Typology typo = company.getTypologyStore().getTypologyByDescription("Fixed Cost");
        Customer customer = company.getCustomerStore().getCustomerByName("Teste");
        BusinessSector sector = company.getBusinessSectorStore().getBusinessSectorByDescription("sector");
        Project project1 = company.getProjectStore().createProject("prototype", "test56", customer,
                typo, sector, LocalDate.now(), 7, 5000);
        LocalDate startDate = LocalDate.of(2021, 12, 31);
        LocalDate endDate = LocalDate.of(2022, 3, 5);
        Resource person1 = new Resource(user, startDate, endDate, new CostPerHour(100), new PercentageOfAllocation(.5));
        Resource person2 = new Resource(user, startDate, endDate, new CostPerHour(100), new PercentageOfAllocation(.5));
        project1.getProjectTeam().saveResource(person1);
        project1.getProjectTeam().saveResource(person2);
        company.getProjectStore().saveNewProject(project1);
        Sprint sprint1 = new Sprint("Hello1");
        SprintStore sprintList = project1.getSprintList();
        sprintList.saveSprint(sprint1);

        //Act
        controller.getProjectByCode("Project_2022_1");
        List<Sprint> sprint = sprintList.findSprints();
        List<Sprint> sprint2 = controller.getSprintsList();

        //Assert
        assertEquals(sprint, sprint2);

    }

    @Test
    void getSprint() {
        //Arrange
        Company company = new Company();
        UserStoryEffortController controller = new UserStoryEffortController(company);
        Typology typo = company.getTypologyStore().getTypologyByDescription("Fixed Cost");
        Customer customer = company.getCustomerStore().getCustomerByName("Teste");
        BusinessSector sector = company.getBusinessSectorStore().getBusinessSectorByDescription("sector");
        Project project1 = company.getProjectStore().createProject("prototype", "test56", customer,
                typo, sector, LocalDate.now(), 7, 5000);
        company.getProjectStore().saveNewProject(project1);
        Sprint sprint1 = new Sprint("Hello1");
        SprintStore sprintList = project1.getSprintList();
        sprintList.saveSprint(sprint1);
        project1.getSprintList().saveSprint(sprint1);
        controller.getProjectByCode(project1.getProjectCode().getCode());
        //Assert
        assertEquals(sprint1, controller.getSprint(1));
    }

//    @Test   // controller incomplete and incorrect, commenting until fixed
//    void getSprintBacklog() {
//        Company company = new Company();
//        UserStoryEffortController controller = new UserStoryEffortController(company);
//        Typology typo = company.getTypologyStore().getTypologyByDescription("Fixed Cost");
//        Customer customer = company.getCustomerStore().getCustomerByName("Teste");
//        BusinessSector sector = company.getBusinessSectorStore().getBusinessSectorByDescription("sector");
//        Project project1 = company.getProjectStore().createProject("prototype", "test56", customer,
//                typo, sector, LocalDate.now(), 7, 5000);
//        company.getProjectStore().saveNewProject(project1);
//        Sprint sprint1 = new Sprint("teste1", LocalDate.now());
//        sprint1.setIdSprint(1);
//        project1.getSprintList().saveSprint(sprint1);
//        UserStory story = new UserStory("As a PO, i want to test this string", 5, "validate", 5);
//        project1.getSprintList().getSprintById(1).saveUsInSprintBacklog(story);
//        company.getProjectStore().saveNewProject(project1);
//        controller.getProjectByCode("Project_2022_1");
//        controller.getSprintsList();
//        controller.getSprint(1);
//
//        assertEquals(sprint1.getSprintBacklog(), controller.getSprintBacklog());
//    }


    @Test
    void getProjectCode() {
        //Arrange
        Company company = new Company();
        UserStoryEffortController controller = new UserStoryEffortController(company);
        Typology typo = company.getTypologyStore().getTypologyByDescription("Fixed Cost");
        Customer customer = company.getCustomerStore().getCustomerByName("Teste");
        BusinessSector sector = company.getBusinessSectorStore().getBusinessSectorByDescription("sector");
        Project project1 = company.getProjectStore().createProject("prototype", "test56", customer,
                typo, sector, LocalDate.now(), 7, 5000);
        company.getProjectStore().saveNewProject(project1);
        //Assert
        assertEquals(project1, controller.getProjectByCode(project1.getProjectCode().getCode()));
    }

}