package switch2021.project.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switch2021.project.stores.SprintStore;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProjectTest {

    private Project proj1;
    private Project proj2;
    private Project proj3;
    private Project currentProject;

    @BeforeEach
    public void init() {
        Company company = new Company();
        Typology typo = company.getTypologyStore().getTypology("Fixed Cost");
        Customer customer = company.getCustomerStore().getCustomerByName("isep");
        BusinessSector sector = company.getBusinessSectorStore().getBusinessSectorByDescription("it");

        proj1 = company.getProjectStore().createProject("proj1Code", "prototype1", "proj1Prototype", customer,
                typo, sector, LocalDate.of(2021, 11, 1), 2, 3000);
        proj1.setEndDate(LocalDate.of(2021, 11, 30));

        proj2 = company.getProjectStore().createProject("proj2Code", "prototype2", "proj2Prototype", customer,
                typo, sector, LocalDate.of(2021, 11, 1), 2, 2000);
        proj2.setEndDate(LocalDate.of(2021, 11, 30));

        proj3 = company.getProjectStore().createProject("proj3Code", "prototype3", "proj3Prototype", customer,
                typo, sector, LocalDate.of(2021, 11, 1), 2, 2000);
        proj3.setEndDate(LocalDate.of(2021, 11, 30));

        currentProject = company.getProjectStore().createProject("proj4Code", "prototype4", "proj4Prototype", customer,
                typo, sector, LocalDate.now().minusDays(7), 2, 4000);
        currentProject.setEndDate(LocalDate.now().plusDays(7));

        UserProfile profile = company.getUserProfileStore().getUserProfile("Visitor");
        SystemUser user1 = new SystemUser("manuelbras", "manuelbras@beaver.com", "tester", "ghi", "ghi", "photo", profile);
        LocalDate startDateMb = LocalDate.of(2021, 11, 1);
        LocalDate endDateMb = LocalDate.of(2021, 11, 15);
        Resource manuelbras = new Resource(user1, startDateMb, endDateMb, 100, .5);

        SystemUser user2 = new SystemUser("manuelmartins", "manuelmartins@beaver.com", "tester", "ghi", "ghi", "photo", profile);
        LocalDate startDateMm = LocalDate.now().minusDays(7);
        LocalDate endDateMm = LocalDate.now().plusDays(7);
        Resource manuelmartins = new Resource(user2, startDateMm, endDateMm, 100, 1);

        SystemUser user3 = new SystemUser("manueljose", "manueljose@beaver.com", "tester", "ghi", "ghi", "photo", profile);
        LocalDate startDateMj = LocalDate.of(2021, 11, 1);
        LocalDate endDateMj = LocalDate.of(2021, 11, 15);
        Resource manueljose = new Resource(user3, startDateMj, endDateMj, 100, .5);

        SystemUser user4 = new SystemUser("manueloliveira", "manueloliveira@beaver.com", "tester", "ghi", "ghi", "photo", profile);
        LocalDate startDateMo = LocalDate.of(2021, 11, 1);
        LocalDate endDateMo = LocalDate.of(2021, 11, 15);
        Resource manueloliveira = new Resource(user4, startDateMo, endDateMo, 100, .3333);

        SystemUser user5 = new SystemUser("manuelfernandes", "manuelfernandes@beaver.com", "tester", "ghi", "ghi", "photo", profile);
        LocalDate startDateMf = LocalDate.of(2021, 11, 16);
        LocalDate endDateMf = LocalDate.of(2021, 11, 30);
        Resource manuelfernandes = new Resource(user5, startDateMf, endDateMf, 100, 1);

        SystemUser user6 = new SystemUser("manuelgoncalves", "manuelgoncalves@beaver.com", "tester", "ghi", "ghi", "photo", profile);
        LocalDate startDateMg = LocalDate.of(2021, 11, 16);
        LocalDate endDateMg = LocalDate.of(2021, 11, 30);
        Resource manuelgoncalves = new Resource(user6, startDateMg, endDateMg, 100, 1);

        proj1.getProjectTeam().addResourceToTeam(manuelbras);
        proj1.getProjectTeam().addResourceToTeam(manueljose);
        proj1.getProjectTeam().addResourceToTeam(manueloliveira);
        proj1.getProjectTeam().addResourceToTeam(manuelfernandes);
        proj2.getProjectTeam().addResourceToTeam(manuelbras);
        proj2.getProjectTeam().addResourceToTeam(manueloliveira);
        proj2.getProjectTeam().addResourceToTeam(manuelgoncalves);
        proj3.getProjectTeam().addResourceToTeam(manueljose);
        proj3.getProjectTeam().addResourceToTeam(manueloliveira);
        currentProject.getProjectTeam().addResourceToTeam(manuelmartins);
    }

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

        //company.getProjectStore().removeProject(project2);
    }

    @Test
    public void hasCurrentProjectTeamMemberEmailSuccess() {
        assertTrue(this.currentProject.getProjectTeam().hasCurrentResource(("manuelmartins@beaver.com")));
    }

    @Test
    public void hasCurrentProjectTeamMemberEmailFailResourceNotPresent() {
        assertFalse(this.currentProject.getProjectTeam().hasCurrentResource(("manueloliveira@beaver.com")));
    }

    @Test
    public void hasCurrentProjectTeamMemberEmailFailResourceNotCurrent() {
        assertFalse(this.proj3.getProjectTeam().hasCurrentResource(("manueloliveira@beaver.com")));
    }


    @Test
    @DisplayName("Validate the getter of sprint store")
    void getSprintStoreTest() {
        Sprint sprint1 = new Sprint("Effort View", LocalDate.now());
        SprintStore sprintStore1 = new SprintStore();
        sprintStore1.saveSprint(sprint1);
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