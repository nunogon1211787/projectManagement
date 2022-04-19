package switch2021.project.controller;

import org.junit.jupiter.api.Test;
import switch2021.project.model.*;
import switch2021.project.model.Project.*;
import switch2021.project.model.Resource.Resource;
import switch2021.project.model.Sprint.Sprint;
import switch2021.project.model.SystemUser.SystemUser;
import switch2021.project.model.Task.Task;
import switch2021.project.model.Task.TaskTypeEnum;
import switch2021.project.model.Typology.Typology;
import switch2021.project.model.UserProfile.UserProfile;
import switch2021.project.model.valueObject.*;
import switch2021.project.repositories.ProjectStore;
import switch2021.project.stores.SystemUserStore;

import java.time.LocalDate;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class ViewStatusOfActivitiesInAProjectControllerTest {

    @Test
    void getListOfProjectActivities() {
        Company company = new Company();
        Typology typo = company.getTypologyRepository().findTypologyByDescription("Fixed Cost");
        Customer customer = company.getCustomerStore().getCustomerByName("Teste");
        BusinessSector sector = company.getBusinessSectorStore().getBusinessSectorByDescription("sector");

        Project project2 = new Project("Project_test", "prototype", customer,
                typo, sector, LocalDate.now(), 7, 5000);

        company.getProjectStore().saveNewProject(project2);

        Sprint sprint1 = project2.getSprintList().createAndSaveSprint("Project_2022_1_Sprint 1", "Sprint Name", 2);

        UserProfile profile = company.getUserProfileStore().getUserProfile("Visitor");
        SystemUser user = new SystemUser("manuelbras", "manuelbras@beaver.com", "tester", "Qwerty_1", "Qwerty_1", "photo.png", profile.getUserProfileId());
        LocalDate startDateMb = LocalDate.of(2022, 1, 1);
        LocalDate endDateMb = LocalDate.of(2022, 1, 31);
        Resource resource = new Resource(user, startDateMb, endDateMb, new CostPerHour(100), new PercentageOfAllocation(.5));
        String taskDescription = "must be at least 20 characters";
        TaskTypeEnum taskType = TaskTypeEnum.Design;

        Task taskTest = sprint1.getTaskStore().createTask("test", taskDescription, 8.0, taskType, resource);
        sprint1.getTaskStore().saveTask(taskTest);

        List<Task> listTest;

        ViewStatusOfActivitiesInAProjectController viewStatusTester = new ViewStatusOfActivitiesInAProjectController(company);

        viewStatusTester.getProjectByCode("Project_2022_1");
        listTest = viewStatusTester.getListOfProjectActivities();

        assertEquals(listTest, project2.getSprintList().getListOfAllAActivitiesOfAProject());
        assertNull(viewStatusTester.getProjectByCode(null));
        assertNotNull(viewStatusTester.getProjectByCode("Project_2022_1"));
    }

    @Test
    void getProjectListByUserSuccess() {
        //Arrange
        Company company = new Company();
        ViewStatusOfActivitiesInAProjectController controller = new ViewStatusOfActivitiesInAProjectController(company);
        ProjectStore projectStore = company.getProjectStore();
        SystemUserStore systemUserStore = company.getSystemUserStore();
        //Project1
        Typology typo = company.getTypologyRepository().findTypologyByDescription("Fixed Cost");
        Customer customer = company.getCustomerStore().getCustomerByName("isep");
        BusinessSector sector = company.getBusinessSectorStore().getBusinessSectorByDescription("it");
        Project project1 = projectStore.createProject("prototype4", "proj4Prototype", customer,
                typo, sector, LocalDate.of(2021, 12, 1), 2, 4000);
        project1.setEndDate(LocalDate.of(2021, 12, 31));
        projectStore.saveNewProject(project1);
        //Project2
        Project project2 = projectStore.createProject("prototype3", "proj3Prototype", customer,
                typo, sector, LocalDate.of(2022, 1, 1), 2, 4000);
        project1.setEndDate(LocalDate.of(2022, 1, 31));
        projectStore.saveNewProject(project2);
        //Project3
        Project project3 = projectStore.createProject("prototype2", "proj2Prototype", customer,
                typo, sector, LocalDate.of(2021, 12, 1), 2, 4000);
        project1.setEndDate(LocalDate.of(2021, 12, 31));
        projectStore.saveNewProject(project3);
        //Resource1
        UserProfile profile = company.getUserProfileStore().getUserProfile("Visitor");
        SystemUser user = new SystemUser("manuelmartins", "manuelmartins@beaver.com", "tester", "Qwerty_1", "Qwerty_1", "photo.png", profile.getUserProfileId());
        systemUserStore.saveSystemUser(user);
        LocalDate startDateMm = LocalDate.of(2021, 12, 15);
        LocalDate endDateMm = LocalDate.of(2021, 12, 31);
        Resource resource1 = project1.getProjectTeam().createResource(user, startDateMm, endDateMm, 100, .5);
        //Resource2
       SystemUser user2 = new SystemUser("manuel", "manuel@beaver.com", "tester", "Qwerty_1", "Qwerty_1", "photo.png", profile.getUserProfileId());
        systemUserStore.saveSystemUser(user2);
        LocalDate startDateM = LocalDate.of(2022, 1, 1);
        LocalDate endDateM = LocalDate.of(2022, 1, 31);
        Resource resource2 = project2.getProjectTeam().createResource(systemUserStore.findSystemUserByEmail("manuel@beaver.com"), startDateM, endDateM, 100, .5);
        //assign them to the projects
        project1.getProjectTeam().saveResource(resource1);
        project2.getProjectTeam().saveResource(resource2);
        project3.getProjectTeam().saveResource(resource1);
        // Act
        List<Project> userList = controller.getProjectListByUser("manuelmartins@beaver.com");
        List<Project> user2List = controller.getProjectListByUser("manuel@beaver.com");
        // Assert
        assertEquals(2, userList.size());
        assertEquals(1, user2List.size());
        assertTrue(userList.contains(project1));
        assertTrue(user2List.contains(project2));
        assertTrue(userList.contains(project3));
    }
}