package switch2021.project.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import switch2021.project.model.*;
import switch2021.project.stores.SprintList;
import switch2021.project.stores.SystemUserStore;
import switch2021.project.stores.UserProfileStore;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserStoryEffortControllerTest {

    private Company company;
    private SystemUser user;
    private UserStoryEffortController userStoryEffortController;
    private Resource input;

    private Project project1, project2;
    private Sprint sprint1, sprint2;
    private UserStory story;


    @Test
    void catchCurrentProjectListByUserEmail() {
        // Arrange
        Company company = new Company(); //iniciar aqui
        UserStoryEffortController controller = new UserStoryEffortController(company); //chamei o controller
        SystemUserStore systemUserStore = company.getSystemUserStore(); //chamo a systemUserStore
        UserProfile userProfile = company.getUserProfileStore().getUserProfile("Visitor"); //Preciso de obter o profile para introduzir no user
        SystemUser user = new SystemUser("Test", "xxxx@isep.ipp.pt",
                "tester", "123456", "123456", "IMG_123", userProfile); //Tenho de atribuir um user profile
        systemUserStore.saveSystemUser(user); //salvo o user
        Typology typo = company.getTypologyStore().getTypologyByDescription("Fixed Cost");
        Customer customer = company.getCustomerStore().getCustomerByName("Teste");
        BusinessSector sector = company.getBusinessSectorStore().getBusinessSectorByDescription("sector");
        Project project1 = company.getProjectStore().createProject("prototype", "test56", customer,
                typo, sector, LocalDate.now(), 7, 5000); //quando inicio um project preciso de customer, typo, sector
        LocalDate startDate = LocalDate.of(2021, 12, 31);
        LocalDate endDate = LocalDate.of(2022, 3, 5);
        Resource person1 = new Resource(user, startDate, endDate, 100, .5);
        Resource person2 = new Resource(user, startDate, endDate, 100, .5);
        project1.getProjectTeam().addResourceToTeam(person1);
        project1.getProjectTeam().addResourceToTeam(person2);
        company.getProjectStore().saveNewProject(project1);
        //UserProfileStore userProfileStore = company.getUserProfileStore();
        /*Sprint sprint1 = new Sprint("Effort View", LocalDate.now());
        sprint1.setId_Sprint(1);
        Sprint sprint2 = new Sprint("Effort View 1", LocalDate.now());
        sprint2.setId_Sprint(2);
        UserStoryStatus status = company.getUserStoryStatusStore().getUserStoryStatusByDescription("To do");
        int priority = 5;
        String description = "Validate";
        UserStory story = new UserStory(status, priority, description);*/

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
        Company company = new Company(); //iniciar aqui
        UserStoryEffortController controller = new UserStoryEffortController(company); //chamei o controller
        SystemUserStore systemUserStore = company.getSystemUserStore(); //chamo a systemUserStore
        UserProfile userProfile = company.getUserProfileStore().getUserProfile("Visitor"); //Preciso de obter o profile para introduzir no user
        SystemUser user = new SystemUser("Test", "xxxx@isep.ipp.pt",
                "tester", "123456", "123456", "IMG_123", userProfile); //Tenho de atribuir um user profile
        systemUserStore.saveSystemUser(user); //salvo o user
        Typology typo = company.getTypologyStore().getTypologyByDescription("Fixed Cost");
        Customer customer = company.getCustomerStore().getCustomerByName("Teste");
        BusinessSector sector = company.getBusinessSectorStore().getBusinessSectorByDescription("sector");
        Project project1 = company.getProjectStore().createProject("prototype", "test56", customer,
                typo, sector, LocalDate.now(), 7, 5000); //quando inicio um project preciso de customer, typo, sector
        LocalDate startDate = LocalDate.of(2021, 12, 31);
        LocalDate endDate = LocalDate.of(2022, 3, 5);
        Resource person1 = new Resource(user, startDate, endDate, 100, .5);
        Resource person2 = new Resource(user, startDate, endDate, 100, .5);
        project1.getProjectTeam().addResourceToTeam(person1);
        project1.getProjectTeam().addResourceToTeam(person2);
        company.getProjectStore().saveNewProject(project1);
        Sprint sprint1 = new Sprint("Hello1", LocalDate.now());

        //Act
        controller.getProjectByCode("Project_2022_1");
        SprintList sprintList = project1.getSprintList(); //objeto
        sprintList.saveSprint(sprint1);
        List<Sprint> sprint = sprintList.getSprintList(); // passar de objeto para lista
        List<Sprint> sprint2 = controller.getSprintsList();

        //Assert
        assertEquals(sprint, sprint2);

    }

    @Test
    void getSprint() {
        project1.getSprintList().saveSprint(sprint1);
        project1.getSprintList().saveSprint(sprint2);
        company.getProjectStore().saveNewProject(project1);

        Project companyProject = userStoryEffortController.getProjectByCode("Project_2022_1");

        assertEquals(sprint1, userStoryEffortController.getSprint(1));
    }

   /* @Test
    void getSprintBacklog() {
        project1.getSprintList().saveSprint(sprint1);
        UserStoryOfSprint userStoryOfSprint = sprint1.getSprintBacklog().createUSerStoryOfSprint(story, 5, company.getUserStoryStatusStore().getUserStoryStatusByDescription("Planned"));
        sprint1.getSprintBacklog().saveUserStoryOfSprint(userStoryOfSprint);
        project1.getSprintList().saveSprint(sprint2);
        company.getProjectStore().saveNewProject(project1);

        Project companyProject = userStoryEffortController.getProjectByCode("Project_2022_1");
        Sprint userSprint = userStoryEffortController.getSprint(1);

        assertEquals(sprint1.getSprintBacklog(), userStoryEffortController.getSprintBacklog());
    }

    @Test
    void getUserStory() {
        project1.getSprintList().saveSprint(sprint1);
        UserStoryOfSprint userStoryOfSprint = sprint1.getSprintBacklog().createUSerStoryOfSprint(story, 5, company.getUserStoryStatusStore().getUserStoryStatusByDescription("Planned"));
        userStoryOfSprint.setId_UserStoryOfSprint(1);
        sprint1.getSprintBacklog().saveUserStoryOfSprint(userStoryOfSprint);
        project1.getSprintList().saveSprint(sprint2);
        company.getProjectStore().saveNewProject(project1);

        Project companyProject = userStoryEffortController.getProjectByCode("Project_2022_1");
        Sprint userSprint = userStoryEffortController.getSprint(1);

        SprintBacklog sprintBacklog = userStoryEffortController.getSprintBacklog();

        assertEquals(userStoryOfSprint, userStoryEffortController.getUserStory(1));
    }

    @Test
    void setEffort() {
        project1.getSprintList().saveSprint(sprint1);
        UserStoryOfSprint userStoryOfSprint = sprint1.getSprintBacklog().createUSerStoryOfSprint(story, 5, company.getUserStoryStatusStore().getUserStoryStatusByDescription("Planned"));
        userStoryOfSprint.setId_UserStoryOfSprint(1);
        sprint1.getSprintBacklog().saveUserStoryOfSprint(userStoryOfSprint);
        project1.getSprintList().saveSprint(sprint2);
        company.getProjectStore().saveNewProject(project1);


        userStoryEffortController.getProjectByCode(project1.getCode());
        userStoryEffortController.getSprint(sprint1.getId_Sprint());
        userStoryEffortController.getSprintBacklog();
        userStoryEffortController.getUserStory(1);
        userStoryEffortController.setEffort(21);
        assertEquals(21, userStoryEffortController.getUserStory(1).getEstimateEffort());
    }

    */
}