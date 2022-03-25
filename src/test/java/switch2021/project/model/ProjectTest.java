package switch2021.project.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switch2021.project.factory.SprintFactory;
import switch2021.project.immutable.Description;
import switch2021.project.stores.ProjectStore;
import switch2021.project.stores.SprintList;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProjectTest {


    /**
     * Project creation Test (US005)
     **/

    Company company = new Company();
    Project proj;

    @BeforeEach
    public void init() {
        //Arrange
        LocalDate date = LocalDate.of(2021, 12, 12);
        company.getBusinessSectorStore().createAndAddBusinessSector("sector");
        company.getCustomerStore().saveNewCustomer(company.getCustomerStore().createCustomer("Teste", "Teste@teste.com", 123456789));

        Typology typo = company.getTypologyStore().getTypologyByDescription("Fixed Cost");
        Customer customer = company.getCustomerStore().getCustomerByName("Teste");
        BusinessSector sector = company.getBusinessSectorStore().getBusinessSectorByDescription("sector");

        proj = company.getProjectStore().createProject("prototype", "test1", customer,
                typo, sector, date, 7, 5000);
        company.getProjectStore().saveNewProject(proj);
        company.getProjectStore().getProjectByCode(proj.getCode()).setEndDate(LocalDate.now());
    }

    @Test
    @DisplayName("Project Creation Test")
    public void checkProjectCreation() {
        //Real and Expected
        String code = proj.getCode();
        String valueCode = "Project_2022_1";

        String name = proj.getProjectName();
        String valueName = "prototype";

        Description description = proj.getDescription();
        Description valueDescription = new Description("test1");

        Customer customer = company.getProjectStore().getProjectByCode("Project_2022_1").getCustomer();
        Customer valueCustomer = company.getCustomerStore().getCustomerByName("Teste");

        Typology typology = company.getProjectStore().getProjectByCode("Project_2022_1").getTypology();
        Typology valueTypology = company.getTypologyStore().getTypologyByDescription("Fixed Cost");

        BusinessSector sector = company.getProjectStore().getProjectByCode("Project_2022_1").getBusinessSector();
        BusinessSector valueSector = company.getBusinessSectorStore().getBusinessSectorByDescription("sector");

        ProjectStatus status = company.getProjectStore().getProjectByCode("Project_2022_1").getProjectStatus();
        ProjectStatus valueStatus = company.getProjectStatusStore().getProjectStatusByDescription("Planned");

        LocalDate date = company.getProjectStore().getProjectByCode("Project_2022_1").getStartDate();
        LocalDate valueDate = LocalDate.of(2021, 12, 12);

        LocalDate endDate = company.getProjectStore().getProjectByCode("Project_2022_1").getEndDate();
        LocalDate valueEndDate = LocalDate.now();

        int numberOfSprints = company.getProjectStore().getProjectByCode("Project_2022_1").getNumberOfSprints();
        int valueNrSprint = 7;

        double budget = company.getProjectStore().getProjectByCode("Project_2022_1").getBudget();
        double valueBudget = 5000;


        //Result
        assertEquals(valueCode, code);
        assertEquals(valueName, name);
        assertEquals(valueDescription, description);
        assertEquals(valueCustomer, customer);
        assertEquals(valueTypology, typology);
        assertEquals(valueSector, sector);
        assertEquals(valueStatus.getDescription(), status.getDescription());
        assertEquals(valueDate, date);
        assertEquals(valueEndDate, endDate);
        assertEquals(valueNrSprint, numberOfSprints);
        assertEquals(valueBudget, budget);
    }

    @Test
    @DisplayName("Project addition to list test")
    public void saveProjectTest() {
        List<Project> test = company.getProjectStore().getProjects();
        String code = test.get(0).getCode();
        String expectedCode = "Project_2022_1";
        assertEquals(expectedCode, code);
    }

    @Test
    @DisplayName("Project exceptions test")
    public void createProjectExceptionsTest_name() {
        //Arrange
        Typology typo = company.getTypologyStore().getTypologyByDescription("Fixed Cost");
        Customer customer = company.getCustomerStore().getCustomerByName("Teste");
        BusinessSector sector = company.getBusinessSectorStore().getBusinessSectorByDescription("sector");
        LocalDate date = LocalDate.now();
        // Act
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                company.getProjectStore().createProject("", "test1234", customer,
                        typo, sector, date, 7, 5000));
        //Assert
        assertTrue(exception.getMessage().contains("Project Name cannot be empty"));
    }

    @Test
    @DisplayName("Project exceptions test")
    public void createProjectExceptionsTest_nameLength() {
        //Arrange
        Typology typo = company.getTypologyStore().getTypologyByDescription("Fixed Cost");
        Customer customer = company.getCustomerStore().getCustomerByName("Teste");
        BusinessSector sector = company.getBusinessSectorStore().getBusinessSectorByDescription("sector");
        LocalDate date = LocalDate.now();
        // Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            company.getProjectStore().createProject("pr", "test1234", customer,
                    typo, sector, date, 7, 5000);
        });
        //Assert
        assertTrue(exception.getMessage().contains("Project Name must be at least 3 characters"));
    }

    @Test
    @DisplayName("Project exceptions test")
    public void createProjectExceptionsTest_descriptionLenght2() {
        //Arrange
        Typology typo = company.getTypologyStore().getTypologyByDescription("Fixed Cost");
        Customer customer = company.getCustomerStore().getCustomerByName("Teste");
        BusinessSector sector = company.getBusinessSectorStore().getBusinessSectorByDescription("sector");
        LocalDate date = LocalDate.now();
        // Act
        assertThrows(IllegalArgumentException.class, () -> {
            company.getProjectStore().createProject("prototype", "", customer,
                    typo, sector, date, 7, 5000);
        });
    }

    @Test
    @DisplayName("Project exceptions test")
    public void createProjectExceptionsTest_numberOfSprints() {
        //Arrange
        Typology typo = company.getTypologyStore().getTypologyByDescription("Fixed Cost");
        Customer customer = company.getCustomerStore().getCustomerByName("Teste");
        BusinessSector sector = company.getBusinessSectorStore().getBusinessSectorByDescription("sector");
        LocalDate date = LocalDate.now();
        // Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            company.getProjectStore().createProject("prototype", "test1234", customer,
                    typo, sector, date, 0, 5000);
        });
        //Assert
        assertTrue(exception.getMessage().contains("Number of Sprints must be greater than 0"));
    }

    @Test
    @DisplayName("Project exceptions test")
    public void createProjectExceptionsTest_budget() {
        //Arrange
        Typology typo = company.getTypologyStore().getTypologyByDescription("Fixed Cost");
        Customer customer = company.getCustomerStore().getCustomerByName("Teste");
        BusinessSector sector = company.getBusinessSectorStore().getBusinessSectorByDescription("sector");
        LocalDate date = LocalDate.now();
        // Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            company.getProjectStore().createProject("prototype", "test1234", customer,
                    typo, sector, date, 7, 0);
        });
        //Assert
        assertTrue(exception.getMessage().contains("Budget must be greater than 0"));
    }

    @Test
    @DisplayName("Project Creation validation test")
    public void checkValidation() {
        boolean test = company.getProjectStore().saveNewProject(proj);
        assertFalse(test);
    }

    private Project proj1;
    private Project proj2;
    private Project proj3;
    private Project currentProject;

    Typology typo = company.getTypologyStore().getTypologyByDescription("Fixed Cost");
    Customer customer = company.getCustomerStore().getCustomerByName("Teste");
    BusinessSector sector = company.getBusinessSectorStore().getBusinessSectorByDescription("sector");
    ProjectStatus projectStatus = new ProjectStatus("ToStart");
    Project project2 = new Project("prototype", "test56", customer,
            typo, sector, LocalDate.now(), projectStatus, 7, 5000);
    ProductBacklog productBacklog = project2.getProductBacklog();


    @BeforeEach
    public void init_2() {
        Company company = new Company();
        Typology typo = company.getTypologyStore().getTypologyByDescription("Fixed Cost");
        Customer customer = company.getCustomerStore().getCustomerByName("isep");
        BusinessSector sector = company.getBusinessSectorStore().getBusinessSectorByDescription("it");

        proj1 = company.getProjectStore().createProject("prototype1", "proj1Prototype", customer,
                typo, sector, LocalDate.of(2021, 11, 1), 2, 3000);
        proj1.setEndDate(LocalDate.of(2021, 11, 30));

        proj2 = company.getProjectStore().createProject("prototype2", "proj2Prototype", customer,
                typo, sector, LocalDate.of(2021, 11, 1), 2, 2000);
        proj2.setEndDate(LocalDate.of(2021, 11, 30));

        proj3 = company.getProjectStore().createProject("prototype3", "proj3Prototype", customer,
                typo, sector, LocalDate.of(2021, 11, 1), 2, 2000);
        proj3.setEndDate(LocalDate.of(2021, 11, 30));

        currentProject = company.getProjectStore().createProject("prototype4", "proj4Prototype", customer,
                typo, sector, LocalDate.now().minusDays(7), 2, 4000);
        currentProject.setEndDate(LocalDate.now().plusDays(7));

        UserProfile profile = company.getUserProfileStore().getUserProfile("Visitor");
        SystemUser user1 = new SystemUser("manuelbras", "manuelbras@beaver.com", "tester", "Qwerty_1", "Qwerty_1", "photo", profile);
        LocalDate startDateMb = LocalDate.of(2021, 11, 1);
        LocalDate endDateMb = LocalDate.of(2021, 11, 15);
        Resource manuelbras = new Resource(user1, startDateMb, endDateMb, 100, .5);

        SystemUser user2 = new SystemUser("manuelmartins", "manuelmartins@beaver.com", "tester", "Qwerty_1", "Qwerty_1", "photo", profile);
        LocalDate startDateMm = LocalDate.now().minusDays(7);
        LocalDate endDateMm = LocalDate.now().plusDays(7);
        Resource manuelmartins = new Resource(user2, startDateMm, endDateMm, 100, 1);

        SystemUser user3 = new SystemUser("manueljose", "manueljose@beaver.com", "tester", "Qwerty_1", "Qwerty_1", "photo", profile);
        LocalDate startDateMj = LocalDate.of(2021, 11, 1);
        LocalDate endDateMj = LocalDate.of(2021, 11, 15);
        Resource manueljose = new Resource(user3, startDateMj, endDateMj, 100, .5);

        SystemUser user4 = new SystemUser("manueloliveira", "manueloliveira@beaver.com", "tester", "Qwerty_1", "Qwerty_1", "photo", profile);
        LocalDate startDateMo = LocalDate.of(2021, 11, 1);
        LocalDate endDateMo = LocalDate.of(2021, 11, 15);
        Resource manueloliveira = new Resource(user4, startDateMo, endDateMo, 100, .3333);

        SystemUser user5 = new SystemUser("manuelfernandes", "manuelfernandes@beaver.com", "tester", "Qwerty_1", "Qwerty_1", "photo", profile);
        LocalDate startDateMf = LocalDate.of(2021, 11, 16);
        LocalDate endDateMf = LocalDate.of(2021, 11, 30);
        Resource manuelfernandes = new Resource(user5, startDateMf, endDateMf, 100, 1);

        SystemUser user6 = new SystemUser("manuelgoncalves", "manuelgoncalves@beaver.com", "tester", "Qwerty_1", "Qwerty_1", "photo", profile);
        LocalDate startDateMg = LocalDate.of(2021, 11, 16);
        LocalDate endDateMg = LocalDate.of(2021, 11, 30);
        Resource manuelgoncalves = new Resource(user6, startDateMg, endDateMg, 100, 1);

        proj1.getProjectTeam().saveResource(manuelbras);
        proj1.getProjectTeam().saveResource(manueljose);
        proj1.getProjectTeam().saveResource(manueloliveira);
        proj1.getProjectTeam().saveResource(manuelfernandes);
        proj2.getProjectTeam().saveResource(manuelbras);
        proj2.getProjectTeam().saveResource(manueloliveira);
        proj2.getProjectTeam().saveResource(manuelgoncalves);
        proj3.getProjectTeam().saveResource(manueljose);
        proj3.getProjectTeam().saveResource(manueloliveira);
        currentProject.getProjectTeam().saveResource(manuelmartins);
    }

    @Test
    @DisplayName("validate that list have results (not null) and check list size are correct")
    public void getProductBacklogWithResults() {
        // Arrange


        //company.getProjectStore().saveNewProject(proj1);
        proj1.getProductBacklog().createAndSaveUserStory("US001", 1, "making test", 5);
        proj1.getProductBacklog().createAndSaveUserStory("US002", 1, "making other test", 5);
        proj1.getProductBacklog().createAndSaveUserStory("US003", 1, "making test 4", 5);
        // Act
        proj1.getProductBacklog().getUserStoryList();
        //Assert
        assertNotNull(proj1.getProductBacklog());
        assertEquals(3, proj1.getProductBacklog().getUserStoryList().size());

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
        SprintList sprintList1 = new SprintList(new SprintFactory());
        sprintList1.saveSprint(sprint1);
        SprintList projectSprintList = this.project2.getSprintList();
        projectSprintList.saveSprint(sprint1);
        assertEquals(sprintList1, projectSprintList);
    }

    @Test
    @DisplayName("Validate the getter of sprint store")
    void getSprintStoreTestFail() {
        Sprint sprint1 = new Sprint("Effort View", LocalDate.now());
        SprintList sprintList1 = new SprintList(new SprintFactory());
        sprintList1.saveSprint(sprint1);
        Sprint sprint2 = new Sprint("Effort View 1", LocalDate.now());
        SprintList projectSprintList = this.project2.getSprintList();
        projectSprintList.saveSprint(sprint1);
        projectSprintList.saveSprint(sprint2);
        assertNotEquals(sprintList1, projectSprintList);
    }

    @Test
    @DisplayName("Create task")
    void getActivitiesOfAProject() {
        //Arrange
        Sprint sprint1 = new Sprint("Effort View", LocalDate.now());

        UserProfile profile = company.getUserProfileStore().getUserProfile("Visitor");
        SystemUser user = company.getSystemUserStore().createSystemUser("manuelbras", "manuelbras@beaver.com", "tester", "Qwerty_1", "Qwerty_1", "photo", profile);
        LocalDate startDateMb = LocalDate.of(2022, 1, 1);
        LocalDate endDateMb = LocalDate.of(2022, 1, 31);
        Resource resource = new Resource(user, startDateMb, endDateMb, 100, .5);
        String taskDescription = "must be at least 20 characters";
        TaskType taskType = company.getTaskTypeStore().getTypeByDescription("Testing");

        Task taskTest = sprint1.getTaskList().createTask("test", taskDescription, 8.0, taskType, resource);
        sprint1.getTaskList().saveTask(taskTest);
        List<Task> taskList = new ArrayList<>();
        //Act
        project2.getSprintList().saveSprint(sprint1);
        taskList.add(taskTest);
        //Assert
        assertEquals(taskList, project2.getSprintList().getSprintById(1).getListOfTasksOfASprint());
    }

    @Test
    @DisplayName("Validate Save Project")
    void saveNewProject() {
        //Arrange
        ProjectStore store = new ProjectStore();
        Project proj1 = company.getProjectStore().createProject("prototype1", "proj1Prototype", customer,
                typo, sector, LocalDate.of(2021, 11, 1), 2, 3000);
        proj1.setEndDate(LocalDate.of(2021, 11, 30));

        store.saveNewProject(proj1);
        store.saveNewProject(proj1);

        assertEquals(1, store.getProjects().size());
    }

    @Test
    @DisplayName("Validate Save Project")
    void saveNewProject_2() {
        //Arrange
        ProjectStore store = new ProjectStore();
        Project proj1 = company.getProjectStore().createProject("prototype1", "proj1Prototype", customer,
                typo, sector, LocalDate.of(2021, 11, 1), 2, 3000);
        proj1.setEndDate(LocalDate.of(2021, 11, 30));

        Project proj2 = company.getProjectStore().createProject("prototype2", "proj1Prototype", customer,
                typo, sector, LocalDate.of(2021, 11, 1), 2, 3000);
        proj1.setEndDate(LocalDate.of(2021, 11, 30));

        store.saveNewProject(proj1);
        store.saveNewProject(proj2);

        assertEquals(2, store.getProjects().size());
    }

    @Test
    @DisplayName("Validate resource - add fail")
    void addResource() {
        //Arrange
        UserProfile profile = company.getUserProfileStore().getUserProfile("Visitor");
        SystemUser user4 = new SystemUser("manueloliveira", "manueloliveira@beaver.com", "tester", "Querty_1", "Querty_1", "photo", profile);
        LocalDate startDateMo = LocalDate.of(2021, 11, 1);
        LocalDate endDateMo = LocalDate.of(2021, 11, 15);
        Resource manueloliveira = new Resource(user4, startDateMo, endDateMo, 100, .3333);
        //Act
        boolean addResource = proj3.addResource(manueloliveira);
        //Assert
        assertFalse(addResource);
    }

    @Test
    @DisplayName("Validate resource")
    void addResource2() {
        //Arrange
        UserProfile profile = company.getUserProfileStore().getUserProfile("Visitor");
        SystemUser user4 = new SystemUser("manueloliveira", "manueloliveira2@beaver.com", "tester", "Qwerty_1", "Qwerty_1", "photo", profile);
        LocalDate startDateMo = LocalDate.of(2021, 11, 1);
        LocalDate endDateMo = LocalDate.of(2021, 11, 15);
        Resource manueloliveira = new Resource(user4, startDateMo, endDateMo, 100, .3333);
        //Act
        boolean addResource = proj3.addResource(manueloliveira);
        //Assert
        assertTrue(addResource);
    }

    @Test
    public void overrideAndHashCodeTest() {
        //Arrange
        ProjectStore list1 = new ProjectStore();

        Typology typo = company.getTypologyStore().getTypologyByDescription("Fixed Cost");
        Customer customer = company.getCustomerStore().getCustomerByName("Teste");
        BusinessSector sector = company.getBusinessSectorStore().getBusinessSectorByDescription("sector");
        Project project = company.getProjectStore().createProject("prototype", "test1234", customer,
                typo, sector, LocalDate.now(), 7, 5000);
        company.getProjectStore().saveNewProject(project);

        list1.saveNewProject(project);
        ProjectStore list2 = new ProjectStore();
        list2.saveNewProject(project);
        ProjectStore list3 = new ProjectStore();
        list3.saveNewProject(list3.createProject("prototype4", "test123456", customer,
                typo, sector, LocalDate.now(), 10, 6000));


        //Assert
        assertNotSame(list1, list2);
        assertEquals(list1, list2);
        assertEquals(list1.hashCode(), list2.hashCode());
        assertNotEquals(list1, list3);
        assertNotEquals(list1.hashCode(), list3.hashCode());
        assertEquals(7, project.getNumberOfSprints());
        assertEquals(project.getCode(), list1.getProjectByCode(project.getCode()).getCode());
        assertEquals("prototype", project.getProjectName());
        assertEquals("test1234", project.getDescription().getText());
        assertEquals(sector, project.getBusinessSector());

    }

    @Test
    @DisplayName("Test Override")
    public void overrideTest() {
        //Arrange
        ProductBacklog backlog = new ProductBacklog();
        LocalDate date = LocalDate.of(2024, 12, 12);
        Typology typo = company.getTypologyStore().getTypologyByDescription("Fixed Cost");
        Customer customer = company.getCustomerStore().getCustomerByName("Teste");
        BusinessSector sector = company.getBusinessSectorStore().getBusinessSectorByDescription("sector");
        Project project = company.getProjectStore().createProject("prototype", "test1234", customer,
                typo, sector, LocalDate.now(), 7, 5000);
        project.setCode("1");
        project.setProductBacklog(backlog);
        project.setEndDate(date);
        Project project2 = company.getProjectStore().createProject("prototype", "test1234", customer,
                typo, sector, LocalDate.now(), 7, 5000);
        project2.setCode("1");
        project2.setEndDate(date);
        project2.setProductBacklog(backlog);

        assertEquals(project.toString(), project2.toString());
        assertEquals(project, project2);

        project2.setProjectStatus(new ProjectStatus("test"));

        assertNotEquals(project, project2);

        project.setProjectStatus(new ProjectStatus("test"));
        project2.setDescription(new Description("test"));

        assertNotEquals(project, project2);

        project.setDescription(new Description("test"));
        project2.setProjectName("erro");

        assertNotEquals(project, project2);
    }

    @Test
    public void overrideTest2() {
        //Arrange
        ProductBacklog backlog = new ProductBacklog();
        LocalDate date = LocalDate.of(2024, 12, 12);
        Typology typo = company.getTypologyStore().getTypologyByDescription("Fixed Cost");
        Customer customer = company.getCustomerStore().getCustomerByName("Teste");
        BusinessSector sector = company.getBusinessSectorStore().getBusinessSectorByDescription("sector");
        Project project = company.getProjectStore().createProject("prototype", "test1234", customer,
                typo, sector, LocalDate.now(), 7, 5000);
        project.setCode("1");
        project.setProductBacklog(backlog);
        project.setEndDate(date);
        Project project2 = company.getProjectStore().createProject("prototype", "test1234", customer,
                typo, sector, LocalDate.now(), 7, 5000);
        project2.setCode("1");
        project2.setEndDate(date);
        project2.setProductBacklog(backlog);

        assertEquals(project.toString(), project2.toString());
        assertEquals(project, project2);

        project2.setProjectStatus(new ProjectStatus("test"));

        assertNotEquals(project, project2);

        project.setProjectStatus(new ProjectStatus("test"));
        project2.setDescription(new Description("test"));

        assertNotEquals(project, project2);

        project.setDescription(new Description("test"));
        project2.setProjectName("erro");

        assertNotEquals(project, project2);

        assertEquals(project.getBusinessSector(), sector);
        assertEquals(project.getCustomer(), customer);
        assertEquals(project.getTypology(), typo);
        assertEquals(project.getEndDate(), date);
    }
}