package switch2021.project.controller;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import switch2021.project.model.*;
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

    @BeforeEach
    void init() {
        company = new Company(); // sempre a mesma instancia
        SystemUserStore systemUserStore = company.getSystemUserStore();
        UserProfileStore userProfileStore = company.getUserProfileStore();
        user = new SystemUser("Ivan Aguiar", "xxxx@isep.ipp.pt",
                "tester", "123456", "123456", "IMG_123", userProfileStore.getUserProfile(0));
        systemUserStore.saveSystemUser(user);
        userStoryEffortController = new UserStoryEffortController(company);
        Typology typo = company.getTypologyStore().getTypology("Fixed Cost");
        Customer customer = company.getCustomerStore().getCustomerByName("Teste");
        BusinessSector sector = company.getBusinessSectorStore().getBusinessSectorByDescription("sector");
        project1 = company.getProjectStore().createProject("123testcode", "prototype", "test56", customer,
                typo, sector, LocalDate.now(), 7, 5000);
        LocalDate startDate = LocalDate.of(2021, 12, 31);
        LocalDate endDate = LocalDate.of(2022, 3, 5);
        input = new Resource(user, startDate, endDate, 100, .5);
        //Resource input2 = new Resource(user, startDate, endDate, 100, .5);
        project2 = company.getProjectStore().createProject("123testcode2", "prototype2", "test562", customer,
                typo, sector, LocalDate.now(), 7, 5000);
        sprint1 = new Sprint("Effort View", LocalDate.now());
        sprint1.setId_Sprint(1);
        sprint2 = new Sprint("Effort View 1", LocalDate.now());
        sprint2.setId_Sprint(2);
        UserStoryStatus status = company.getUserStoryStatusStore().getUserStoryStatusByDescription("To do");
        int priority = 5;
        String description = "Validate";
        story = new UserStory(status, priority, description);
    }

    @Test
    void getCurrentProjectListByUserEmail() {
        company.getProjectStore().addProject(project1);
        project1.addResource(input);
        List<Project> projectList = new ArrayList<>();
        projectList.add(project1);

        List<Project> companyProjectList = userStoryEffortController.getCurrentProjectListByUserEmail(user.getEmail());
        assertEquals(companyProjectList, projectList);
    }

    @Test
    void getProjectByCode() {
        company.getProjectStore().addProject(project1);
        project1.addResource(input);
        List<Project> projectList = new ArrayList<>();
        projectList.add(project1);

        List<Project> companyProjectList = userStoryEffortController.getCurrentProjectListByUserEmail(user.getEmail());

        assertEquals(project1, userStoryEffortController.getProjectByCode(companyProjectList.get(0).getCode()));
    }

    @Test
    void getSprintList() {
        company.getProjectStore().addProject(project1);
        project1.addResource(input);
        project1.getSprintStore().saveSprint(sprint1);
        List<Project> projectList = new ArrayList<>();
        projectList.add(project1);

        Project companyProject = userStoryEffortController.getProjectByCode("123testcode");

        assertEquals(project1.getSprintStore().getSprintList(), userStoryEffortController.getSprintList());
    }

    @Test
    void getSprint() {
        project1.getSprintStore().saveSprint(sprint1);
        project1.getSprintStore().saveSprint(sprint2);
        company.getProjectStore().addProject(project1);

        Project companyProject = userStoryEffortController.getProjectByCode("123testcode");

        assertEquals(sprint1, userStoryEffortController.getSprint(1));
    }

    @Test
    void getSprintBacklog() {
        project1.getSprintStore().saveSprint(sprint1);
        UserStoryOfSprint userStoryOfSprint = sprint1.getSprintBacklog().createUSerStoryOfSprint(story,5);
        sprint1.getSprintBacklog().addUserStory(userStoryOfSprint);
        project1.getSprintStore().saveSprint(sprint2);
        company.getProjectStore().addProject(project1);

        Project companyProject = userStoryEffortController.getProjectByCode("123testcode");
        Sprint userSprint = userStoryEffortController.getSprint(1);

        assertEquals(sprint1.getSprintBacklog(), userStoryEffortController.getSprintBacklog());
    }

    @Test
    void getUserStory() {
        project1.getSprintStore().saveSprint(sprint1);
        UserStoryOfSprint userStoryOfSprint = sprint1.getSprintBacklog().createUSerStoryOfSprint(story,5);
        userStoryOfSprint.setId_UserStoryOfSprint(1);
        sprint1.getSprintBacklog().addUserStory(userStoryOfSprint);
        project1.getSprintStore().saveSprint(sprint2);
        company.getProjectStore().addProject(project1);

        Project companyProject = userStoryEffortController.getProjectByCode("123testcode");
        Sprint userSprint = userStoryEffortController.getSprint(1);

        SprintBacklog sprintBacklog = userStoryEffortController.getSprintBacklog();

        assertEquals(userStoryOfSprint, userStoryEffortController.getUserStory(1));
    }

    @Test
    void setEffort() {
        project1.getSprintStore().saveSprint(sprint1);
        UserStoryOfSprint userStoryOfSprint = sprint1.getSprintBacklog().createUSerStoryOfSprint(story,5);
        userStoryOfSprint.setId_UserStoryOfSprint(1);
        sprint1.getSprintBacklog().addUserStory(userStoryOfSprint);
        project1.getSprintStore().saveSprint(sprint2);
        company.getProjectStore().addProject(project1);

        Project companyProject = userStoryEffortController.getProjectByCode("123testcode");
        Sprint userSprint = userStoryEffortController.getSprint(1);

        SprintBacklog sprintBacklog = userStoryEffortController.getSprintBacklog();

        userStoryEffortController.getUserStory(1);
        userStoryEffortController.setEffort(21);
        assertEquals(21, userStoryEffortController.getUserStory(1).getEstimateEffort());
    }
}