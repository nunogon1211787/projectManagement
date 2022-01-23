package switch2021.project.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switch2021.project.stores.SprintStore;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProjectTest {

    Company company = new Company();
    ProjectTeamTest projectTeamTest = new ProjectTeamTest();
    Typology typo = company.getTypologyStore().getTypology("Fixed Cost");
    Customer customer = company.getCustomerStore().getCustomerByName("Teste");
    BusinessSector sector = company.getBusinessSectorStore().getBusinessSectorByDescription("sector");
//    Project project = company.getProjectStore().createProject("123testcode", "prototype", "test56", customer,
//            typo, sector, LocalDate.now(), 7, 5000);
    ProjectStatus projectStatus = new ProjectStatus("ToStart");
    Project project2 = new Project("1234testcode", "prototype", "test56", customer,
            typo, sector, LocalDate.now(), projectStatus, 7, 5000);
//    String code = "1234testcode";

    ProductBacklog productBacklog = project2.getProductBacklog();

    UserStory userStory = productBacklog.createUserStory(new UserStoryStatus("In progress"), 1, "making test");
    UserStory userStory2 = productBacklog.createUserStory(new UserStoryStatus("Done"), 1, "making other test");
    UserStory userStory3 = productBacklog.createUserStory(new UserStoryStatus("To do"), 1, "making test 4");


    @Test
    public void getProductBacklogWithResults() {
        // Arrange
        company.getProjectStore().addProject(project2);
        productBacklog.addUserStory(userStory);
        productBacklog.addUserStory(userStory2);
        productBacklog.addUserStory(userStory3);
//        company.getProjectStore().getProductBacklog(code).addUserStory(userStory);
//        company.getProjectStore().getProductBacklog(code).addUserStory(userStory2);
//        company.getProjectStore().getProductBacklog(code).addUserStory(userStory3);
        // Act
        ProductBacklog productBacklog = company.getProjectStore().getProductBacklog("1234testcode");
//        ProductBacklog productbacklog = company.getProjectStore().getProjectByCode(code).getProductBacklog();
        //Assert
        assertNotNull(productBacklog);
    }

    @Test
    public void hasCurrentProjectTeamMemberEmailSuccess() {
        ProjectTeamTest teamTest = new ProjectTeamTest();
        assertTrue(teamTest.getCurrentProject().getProjectTeam().hasCurrentResource(("manuelmartins@beaver.com")));
    }

    @Test
    public void hasCurrentProjectTeamMemberEmailFailResourceNotPresent() {
        ProjectTeamTest teamTest = new ProjectTeamTest();
        assertFalse(teamTest.getCurrentProject().getProjectTeam().hasCurrentResource(("manueloliveira@beaver.com")));
    }

    @Test
    public void hasCurrentProjectTeamMemberEmailFailResourceNotCurrent() {
        ProjectTeamTest teamTest = new ProjectTeamTest();
        assertFalse(teamTest.getProj3().getProjectTeam().hasCurrentResource(("manueloliveira@beaver.com")));
    }


    @Test
    @DisplayName("Validate the getter of sprint store")
    void getSprintStoreTest() {
        Sprint sprint1 = new Sprint("Effort View", LocalDate.now());
        SprintStore sprintStore1 = new SprintStore();
        sprintStore1.saveSprint(sprint1);
        Sprint sprint2 = new Sprint("Effort View 1", LocalDate.now());
        SprintStore projectSprintStore = this.project2.getSprintStore();
        projectSprintStore.saveSprint(sprint1);
        assertTrue(sprintStore1.equals(projectSprintStore));
    }

    @Test
    @DisplayName("Validate the getter of sprint store")
    void getSprintStoreTestFail() {
        Sprint sprint1 = new Sprint("Effort View", LocalDate.now());
        SprintStore sprintStore1 = new SprintStore();
        sprintStore1.saveSprint(sprint1);
        Sprint sprint2 = new Sprint("Effort View 1", LocalDate.now());
        SprintStore projectSprintStore = this.project2.getSprintStore();
        projectSprintStore.saveSprint(sprint1);
        projectSprintStore.saveSprint(sprint2);
        assertFalse(sprintStore1.equals(projectSprintStore));
    }

    @Test
    @DisplayName("create task")
    void getActivitiesOfAProject(){
        Sprint sprint1 = new Sprint("Effort View", LocalDate.now());
        Task taskTest = sprint1.getTaskstore().createTask("test");
        sprint1.getTaskstore().addTaskToTheList(taskTest);
        List<Task> taskList = new ArrayList<>();

        project2.getSprintStore().saveSprint(sprint1);
        taskList.add(taskTest);


        assertEquals(taskList,project2.getSprintStore().getSprint(1).getListOfTasksOfASprint());

    }

}