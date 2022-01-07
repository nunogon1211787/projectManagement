package switch2021.project.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switch2021.project.controller.US008Controller;

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
    public void addUserStoryEmptyOrNullCode() {
        //Arrange
        Project project = new Project();
        String code = "123d";
        project.setCode(code);
        UserStoryStatus status = UserStoryStatus.TODO;
        int priority = 1;
        String description = "teste";
        int timeEstimate = 1;
        UserStory userStory = new UserStory("", status, priority, description, timeEstimate);
        UserStory userStory2 = new UserStory("  ", status, priority, description, timeEstimate);
        UserStory userStory3 = new UserStory(null, status, priority, description, timeEstimate);
        // Act
        boolean isAdded = project.addUserStory(userStory);
        boolean isAdded2 = project.addUserStory(userStory2);
        boolean isAdded3 = project.addUserStory(userStory3);
        //Assert
        assertFalse(isAdded);
        assertFalse(isAdded2);
        assertFalse(isAdded3);
    }

    @Test
    public void addUserStoryPriorityIsInvalid() {
        //Arrange
        Project project = new Project();
        String code = "123d";
        project.setCode(code);
        UserStoryStatus status = UserStoryStatus.TODO;
        int priority = -1;
        String description = "teste";
        int timeEstimate = 1;
        UserStory userStory = new UserStory(code, status, priority, description, timeEstimate);
        // Act
        boolean isAdded = project.addUserStory(userStory);
        //Assert
        assertFalse(isAdded);
    }

    @Test
    public void addUserStoryUserStoryAlreadyExist() {
        //Arrange
        Project project = new Project();
        String code = "123d";
        project.setCode(code);
        UserStoryStatus status = UserStoryStatus.TODO;
        int priority = 1;
        String description = "teste";
        int timeEstimate = 7;
        UserStory userStory = new UserStory(code, status, priority, description, timeEstimate);
        project.addUserStory(userStory);
        // Act
        UserStory userStory2 = new UserStory(code, UserStoryStatus.IN_TEST, 2, description, 8);
        boolean isAdded = project.addUserStory(userStory2);
        //Assert
        assertFalse(isAdded);
    }

    @Test
    public void addUserStoryTimeEstimateInvalid() {
        //Arrange
        Project project = new Project();
        String code = "123d";
        project.setCode(code);
        UserStoryStatus status = UserStoryStatus.TODO;
        int priority = 1;
        String description = "teste";
        int timeEstimate = -1;
        UserStory userStory = new UserStory(code, status, priority, description, timeEstimate);
        // Act
        boolean isAdded = project.addUserStory(userStory);
        //Assert
        assertFalse(isAdded);
    }

    @Test
    public void addUserStoryWithSuccess() {
        //Arrange
        Project project = new Project();
        String code = "123d";
        project.setCode(code);
        UserStoryStatus status = UserStoryStatus.TODO;
        int priority = 1;
        String description = "teste";
        int timeEstimate = 7;
        UserStory userStory = new UserStory(code, status, priority, description, timeEstimate);
        // Act
        boolean isAdded = project.addUserStory(userStory);
        //Assert
        assertTrue(isAdded);
    }

    @Test
    public void createNewUserStoryWithSuccess() {
        //Arrange
        Project project = new Project();
        String code = "123d";
        project.setCode(code);
        UserStoryStatus status = UserStoryStatus.TODO;
        int priority = 1;
        String description = "teste";
        int timeEstimate = 7;
        // Act
        UserStory userStory = project.createUserStory(status, priority, description, timeEstimate);
        //Assert
        assertEquals(code, userStory.getProjectCode());
        assertEquals(status, userStory.getUserStoryStatus());
        assertEquals(priority, userStory.getPriority());
        assertEquals(description, userStory.getDescription());
        assertEquals(timeEstimate, userStory.getTimeEstimate());
    }


    @Test
    public void editProjectSetsTest() {
        //Arrange
        Project project = new Project();
        SystemUser scrumMaster = new SystemUser("Antonio","antonio@isep.ipp.pt", "Designer", "", User);
        SystemUser productOwner = new SystemUser("Manuel","manuel@isep.ipp.pt", "Designer mini", "", User);
        SystemUser user = new SystemUser("Antonio","antonio@isep.ipp.pt", "Designer", "", User);
        SystemUser user2 = new SystemUser("Manuel","manuel@isep.ipp.pt", "Designer mini", "", User);
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

}