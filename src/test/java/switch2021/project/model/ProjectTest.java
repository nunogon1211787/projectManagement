package switch2021.project.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class ProjectTest {

    /**
     * Testes de Criação de Project da classe Project (Paulo - US005)
     **/

    //Arrange
    static LocalDate date = LocalDate.of(2021, 12, 12);
    static Typology typology = new ProjectSettings().getTypologyById(0);
    static Customer customer = new ProjectSettings().getCustomerById(0);
    static BusinessSector businessSector = new ProjectSettings().getBussinessSectorById(0);

    static Project newProject = new Project("123testcode", "prototype", "test", customer,
            typology, businessSector, date, 7, 5000);
    private Profile User;

    @Test
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
        Customer valueCustomer = new Customer(0, "customer1@email.com");

        //Result
        assertEquals(valueCustomer, customerCheck);
    }

    @Test
    @DisplayName("Teste de check Typology")
    public void checkTypology() {
        //Expected
        Typology typologyCheck = newProject.getTypology();
        Typology valueTypology = new Typology("Fixed Cost");

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
        int budget = newProject.getBudget();
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
        comp.add(comp.createProject("123testcode", "prototype", "test", customer,
                typology, businessSector, date, 7, 5000));
        Project proj = new Project("123testcode_2", "prototype_2", "test_2", customer,
                typology, businessSector, date, 7, 6000);
        comp.add(proj);

        //Result
        assertEquals(comp.getProjByIndex(0), newProject);
        assertEquals(comp.getProjByIndex(1), proj);
    }

    @Test
    @DisplayName("Teste de adição de projecto a company")
    public void checkAddProject() {
        Company comp = new Company();
        comp.add(comp.createProject("123testcode", "prototype", "test", customer,
                typology, businessSector, date, 7, 5000));

        //Result
        assertEquals(comp.getProjByIndex(0), newProject);
    }

    @Test
    @DisplayName("Teste de validação de projecto")
    public void checkValidateProject() {
        Company comp = new Company();

        //Result
        assertTrue(comp.validateProject(comp.createProject("123testcode", "prototype", "test", customer,
                typology, businessSector, date, 7, 5000)));
        assertFalse(comp.validateProject(comp.createProject("123testcode", "prototype", "test", customer,
                typology, businessSector, date, 7, -1)));
        assertFalse(comp.validateProject(comp.createProject("123testcode", "prototype", "test", customer,
               typology, businessSector, date, -7, 5000)));
        assertFalse(comp.validateProject(comp.createProject("", "prototype", "test", customer,
                typology, businessSector, date, 7, 5000)));
        assertFalse(comp.validateProject(comp.createProject("123testcode", "", "test", customer,
                typology, businessSector, date, 7, 5000)));
    }

    /**
     * >>>>>> Tests from userStory <<<<<<
     **/

    // Test adding userStory to the project (Cris US009)

    @Test
    public void createUserStoryPriorityIsInvalid() {
        //Arrange
        Project project = new Project();
        String code = "123d";
        project.setCode(code);
        UserStoryStatus status = UserStoryStatus.TODO;
        int priority = -1;
        String description = "teste";
        int timeEstimate = 1;
        // Act
        boolean isAdded = project.createUserStory(status, priority, description, timeEstimate);
        //Assert
        assertFalse(isAdded);
    }

    @Test
    public void createUserStoryUserStoryAlreadyExist() {
        //Arrange
        Project project = new Project();
        String code = "123d";
        project.setCode(code);
        UserStoryStatus status = UserStoryStatus.TODO;
        int priority = 1;
        String description = "teste";
        int timeEstimate = 7;
        project.createUserStory(status, priority, description, timeEstimate);
        // Act
        boolean isAdded = project.createUserStory(UserStoryStatus.IN_TEST, 2, description, 8);
        //Assert
        assertFalse(isAdded);
    }

    @Test
    public void createUserStoryTimeEstimateInvalid() {
        //Arrange
        Project project = new Project();
        String code = "123d";
        project.setCode(code);
        UserStoryStatus status = UserStoryStatus.TODO;
        int priority = 1;
        String description = "teste";
        int timeEstimate = -1;
        // Act
        boolean isAdded = project.createUserStory(status, priority, description, timeEstimate);
        //Assert
        assertFalse(isAdded);
    }

    @Test
    public void createUserStoryDescriptionInvalid() {
        //Arrange
        Project project = new Project();
        String code = "123d";
        project.setCode(code);
        UserStoryStatus status = UserStoryStatus.TODO;
        int priority = 1;
        String description = null;
        int timeEstimate = 1;
        // Act
        boolean isAdded = project.createUserStory(status, priority, description, timeEstimate);
        //Assert
        assertFalse(isAdded);
    }

    @Test
    public void createUserStoryWithSuccess() {
        //Arrange
        Project project = new Project();
        String code = "123d";
        project.setCode(code);
        UserStoryStatus status = UserStoryStatus.TODO;
        int priority = 1;
        String description = "teste";
        int timeEstimate = 7;
        // Act
        boolean isAdded = project.createUserStory(status, priority, description, timeEstimate);
        //Assert
        assertTrue(isAdded);
    }


    @Test
    public void editProjectSetsTest() {
        //Arrange
        Project project = new Project();
        SystemUser scrumMaster = new SystemUser("Antonio","antonio@isep.ipp.pt", "Designer", "123", User);
        SystemUser productOwner = new SystemUser("Manuel","manuel@isep.ipp.pt", "Designer mini", "123", User);
        SystemUser user = new SystemUser("Antonio","antonio@isep.ipp.pt", "Designer", "123", User);
        SystemUser user2 = new SystemUser("Manuel","manuel@isep.ipp.pt", "Designer mini", "123", User);
        ProjectStatus status = new ProjectStatus("Planned");

        // Act
        project.setProjectName("XPTO9");
        project.setStartDate(LocalDate.of(2022, 2,10));
        project.setEndDate(LocalDate.of(2022,4,20));
        project.setNumberOfSprints(5);
        project.setProjectStatus(status);
        project.setSprintDuration(2);
        project.setScrumMaster(scrumMaster);
        project.setProductOwner(productOwner);

        //Assert
        assertEquals("XPTO9", project.getProjectName());
        assertEquals(LocalDate.of(2022,2,10), project.getStartDate());
        assertEquals(LocalDate.of(2022,4,20), project.getEndDate());
        assertEquals(5, project.getNumberOfSprints());
        assertEquals(status, project.getProjectStatus());
        assertEquals(2, project.getSprintDuration());
        assertEquals(user, project.getScrumMaster());
        assertEquals(user2, project.getProductOwner());

    }

    @Test
    @DisplayName("Teste add Resource")
    public void addResource(){
        //Arrange
        /** user **/
        Profile pro = new Profile("mku", "sss");
        SystemUser newUser = new SystemUser("xyz", "fase", "des", "gth", pro);
        LocalDate startDateAllocated = LocalDate.of(2021, 12, 12);
        LocalDate endDateAllocated = LocalDate.of(2021, 12, 24);

        Resource resAllo1 = new Resource(newUser, startDateAllocated, endDateAllocated, 100, .2);
        LocalDate startDateToAllocate = LocalDate.of(2021, 12, 13);
        LocalDate endDateToAllocate = LocalDate.of(2021, 12, 14);
        Resource resAllo2 = new Resource(newUser, startDateToAllocate, endDateToAllocate, 100, .2);


        /** project list **/

       Company comTest = new Company();
       LocalDate startProjectDate = LocalDate.of(2021, 02, 25);
       Customer cust = new Customer("ght@gmail.com");
       Typology typo = new Typology("typo1");
       BusinessSector busSector = new BusinessSector("busSec1");
       Project proj2 = comTest.createProject("2", "gfd", "ghj", cust,typo, busSector, startProjectDate, 30,4500);

       //Act
        boolean result = proj2.addResource(resAllo2);

        //Assert
        assertTrue(result);
    }

    @Test
    @DisplayName("Teste getTeamMemberByIndex")
    public void getTeamMemberByIndex(){
        //Arrange
        /** user **/
        Profile pro = new Profile("mku", "sss");
        SystemUser newUser = new SystemUser("xyz", "fase", "des", "gth", pro);
        SystemUser newUser2 = new SystemUser("xyz", "fase", "des", "gth", pro);
        LocalDate startDateAllocated = LocalDate.of(2021, 12, 12);
        LocalDate endDateAllocated = LocalDate.of(2021, 12, 24);
        Resource resAllo1 = new Resource(newUser, startDateAllocated, endDateAllocated, 100, .5);

        /** project **/
        Company comTest = new Company();
        LocalDate startProjectDate = LocalDate.of(2021, 02, 25);
        Customer cust = new Customer("ght@gmail.com");
        Typology typo = new Typology("typo1");
        BusinessSector busSector = new BusinessSector("busSec1");
        Project proj1 = comTest.createProject("1", "gfd", "ghj", cust, typo, busSector, startProjectDate, 30, 4500);
        Project proj3 = comTest.createProject("3", "gfd", "ghj", cust, typo, busSector, startProjectDate, 30, 4500);
        proj1.addResource(resAllo1);
        proj3.addResource(resAllo1);

        //Act
        Resource result = proj1.getTeamMemberByIndex(0);
        //Assert
        assertEquals(resAllo1, result);
    }

}