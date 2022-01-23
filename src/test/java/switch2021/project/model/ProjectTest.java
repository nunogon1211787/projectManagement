package switch2021.project.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switch2021.project.stores.SprintStore;

import java.time.LocalDate;

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

    /**
     * Testes de Criação de Project da classe Project (Paulo - US005)
     **/

    /*//Arrange
    static LocalDate date = LocalDate.of(2021, 12, 12);
    static Typology typology = new TypologyStore().getTypologyById(0);
    static Customer customer = new ProjectSettings().getCustomerById(0);
    static BusinessSector businessSector = new ProjectSettings().getBussinessSectorById(0);

    static Project newProject = new Project("123testcode", "prototype", "test", customer,
                                            typology, businessSector, date, 7, 5000);
    private UserProfile User= new UserProfile("ddd", "pro");


    @Test
    @DisplayName("Teste do construtor de Project")
    public void checkProject() {
        //Arrange
        LocalDate date = LocalDate.of(2021, 12, 12);
        ProjectSettings test= new ProjectSettings();
        TypologyStore typoS= new TypologyStore();
        Typology typology = typoS.getTypologyById(0);
        Customer customer = test.getCustomerById(0);
        BusinessSector businessSector = test.getBussinessSectorById(0);

        Project newProject = new Project("123testcode", "Test_Project", "test", customer,
                                            typology, businessSector, date, 7, 5000);

        // Expected and results
        assertEquals("123testcode", newProject.getCode());
        assertEquals("Test_Project", newProject.getProjectName());
        assertEquals("test", newProject.getDescription());
        assertEquals(test.getCustomerById(0), newProject.getCustomer());
        assertEquals(typoS.getTypologyById(0), newProject.getTypology());
        assertEquals(test.getBussinessSectorById(0), newProject.getBusinessSector());
        assertEquals(test.getProjectStatusById(0), newProject.getProjectStatus());
        assertEquals(date, newProject.getStartDate());
        assertEquals(7, newProject.getNumberOfSprints());
        assertEquals(5000, newProject.getBudget());

    }*/

   /* @Test
    @DisplayName("Teste de check Code")
    public void checkCode() {
        // Expected
        String code = newProject.getCode();
        String valueCode = "123testcode";

        // Results
        assertEquals(valueCode, code);
    }

    @Test
    @DisplayName("Teste de check Name")
    public void checkName() {
        //Expected
        String name = newProject.getProjectName();
        String valueName = "prototype";

        //Result
        assertEquals(valueName, name);
    }

    @Test
    @DisplayName("Teste de check description")
    public void checkDescription() {
        //Expected
        String description = newProject.getDescription();
        String valueDescription = "test";

        //Result
        assertEquals(valueDescription, description);
    }

    @Test
    @DisplayName("Teste de check Customer")
    public void checkCustomer() {
        //Expected
        Customer customerCheck = newProject.getCustomer();
        Customer valueCustomer = new Customer(0, "customer1@email.com", "name");

        //Result
        assertEquals(valueCustomer, customerCheck);
    }

    @Test
    @DisplayName("Teste de check Typology")
    public void checkTypology() {
        //Expected
        Typology typologyCheck = newProject.getTypology();
        Typology valueTypology = new Typology(0,"Fixed Cost");

        //Result
        assertEquals(valueTypology, typologyCheck);
    }

    @Test
    @DisplayName("Teste de check Start Date")
    public void checkStartDate() {
        //Expected
        LocalDate startDate = newProject.getStartDate();
        LocalDate valueStarDate = LocalDate.of(2021, 12, 12);

        //Result
        assertEquals(valueStarDate, startDate);
    }

    @Test
    @DisplayName("Teste de check End Date")
    public void checkEndDate() {
        //Expected
        newProject.setEndDate();
        LocalDate endDate = newProject.getEndDate();
        LocalDate valueEndDate = LocalDate.now();

        //Result
        assertEquals(valueEndDate, endDate);
    }

    @Test
    @DisplayName("Teste de check Sprint Duration")
    public void checkSprintDuration() {
        //Expected
        int numberOfSprints = newProject.getNumberOfSprints();
        int valueNumberOfSprints = 7;

        //Result
        assertEquals(valueNumberOfSprints, numberOfSprints);
    }

    @Test
    @DisplayName("Teste de check Budget")
    public void checkBudget() {
        //Expected
        double budget = newProject.getBudget();
        int valueBudget = 5000;

        //Result
        assertEquals(valueBudget, budget);
    }

    @Test
    @DisplayName("Teste de Project Status")
    public void checkProjectStatus() {
        //Expected
        ProjectStatus status = newProject.getProjectStatus();
        ProjectStatus valueStatus = new ProjectStatus("Planned");

        //Results
        assertEquals(valueStatus, status);
    }

    @Test
    @DisplayName("Teste de Business Sector")
    public void checkBusinessSector() {
        //Expected
        BusinessSector businessSectorCheck = newProject.getBusinessSector();
        BusinessSector valueBusinessSector = new BusinessSector("Business_0");

        //Results
        assertEquals(valueBusinessSector, businessSectorCheck);
    }

    @Test
    @DisplayName("Teste de busca de projecto a pelo indice")
    public void checkGetProjByIndex() {
        Company comp = new Company();
        comp.getProjectStore().addProject(comp.getProjectStore().createProject("123testcode", "prototype", "test", customer,
                typology, businessSector, date, 7, 5000));
        Project proj = new Project("123testcode_2", "prototype_2", "test_2", customer,
                typology, businessSector, date, 7, 6000);
        comp.getProjectStore().addProject(proj);

        //Result
        assertEquals(comp.getProjectStore().getProjByIndex(0), newProject);
        assertEquals(comp.getProjectStore().getProjByIndex(1), proj);
    }

    @Test
    @DisplayName("Teste de adição de projecto a company")
    public void checkAddProject() {
        Company comp = new Company();
        comp.getProjectStore().addProject(comp.getProjectStore().createProject("123testcode", "prototype", "test", customer,
                typology, businessSector, date, 7, 5000));

        //Result
        assertEquals(comp.getProjectStore().getProjByIndex(0), newProject);
    }

    @Test
    @DisplayName("Teste de validação de projecto")
    public void checkValidateProject() {
        Company comp = new Company();

        //Result
        assertTrue(comp.getProjectStore().validateProject(comp.getProjectStore().createProject("123testcode", "prototype", "test", customer,
                typology, businessSector, date, 7, 5000)));
        assertFalse(comp.getProjectStore().validateProject(comp.getProjectStore().createProject("123testcode", "prototype", "test", customer,
                typology, businessSector, date, 7, -1)));
        assertFalse(comp.getProjectStore().validateProject(comp.getProjectStore().createProject("123testcode", "prototype", "test", customer,
                typology, businessSector, date, -7, 5000)));
        assertFalse(comp.getProjectStore().validateProject(comp.getProjectStore().createProject("", "prototype", "test", customer,
                typology, businessSector, date, 7, 5000)));
        assertFalse(comp.getProjectStore().validateProject(comp.getProjectStore().createProject("123testcode", "", "test", customer,
                typology, businessSector, date, 7, 5000)));
    }


    @Test
    public void editProjectSetsTest() {
        //Arrange

        ProjectStatus status = new ProjectStatus("Planned");

        // Act
        newProject.setProjectName("XPTO9");
        newProject.setStartDate(LocalDate.of(2022, 2, 10));
        newProject.setEndDate(LocalDate.of(2022, 4, 20));
        newProject.setNumberOfSprints(5);
        newProject.setProjectStatus(status);
        newProject.setSprintDuration(2);


        //Assert
        assertEquals("XPTO9", newProject.getProjectName());
        assertEquals(LocalDate.of(2022, 2, 10), newProject.getStartDate());
        assertEquals(LocalDate.of(2022, 4, 20), newProject.getEndDate());
        assertEquals(5, newProject.getNumberOfSprints());
        assertEquals(status, newProject.getProjectStatus());
        assertEquals(2, newProject.getSprintDuration());

    }
*/

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
}