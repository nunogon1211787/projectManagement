package switch2021.project.controller;

import org.junit.jupiter.api.Test;
import switch2021.project.dto.CreateTaskDTO;
import switch2021.project.mapper.TaskMapper;
import switch2021.project.model.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CreateSprintTaskControllerTest {

    @Test
    void getTaskTypesTestSuccess() {
        //Arrange
        Company company = new Company();
        TaskMapper mapper = new TaskMapper();
        CreateSprintTaskController controller = new CreateSprintTaskController(company, mapper);
        List<String> result = controller.getTaskTypes();
        List<String> test = new ArrayList<>(company.getTaskTypeStore().getTaskTypesNames());
        //Assert
        assertEquals(6,result.size());
        assertEquals(result, test);
    }

    @Test
    void getSprintTasksTestSuccess() {
        //Arrange
        Company company = new Company();
        TaskMapper mapper = new TaskMapper();
        CreateSprintTaskController controller = new CreateSprintTaskController(company, mapper);

            //Create a project
        Typology typo = company.getTypologyStore().getTypology("Fixed Cost");
        Customer customer = company.getCustomerStore().getCustomerByName("Teste");
        BusinessSector sector = company.getBusinessSectorStore().getBusinessSectorByDescription("sector");
        Project project = company.getProjectStore().createProject( "prototype", "test1234", customer,
                typo, sector, LocalDate.now(), 7, 5000);
        company.getProjectStore().saveNewProject(project);

            //Create a sprint
        Sprint sprint = project.getSprints().createSprint("Sprint 1", LocalDate.of(2022, 2, 1), 2);
        project.getSprints().saveSprint(sprint);

            //Create tasks
        UserProfile profile = company.getUserProfileStore().getUserProfile("Visitor");
        SystemUser user = new SystemUser("user test", "test@test.pt", "test", "encript", "encript", "photo", profile);
        Resource resource = new Resource(user, LocalDate.of(2022, 2, 1), LocalDate.of(2023, 2, 1), 100, 1);
        TaskType type = new TaskType("type");
        Task newTask = new Task("test", "test test test tests", 10, type, resource);
        Task newTask2 = new Task("test2", "test2 test2 test2 test2", 10, type, resource);
        sprint.getTaskList().saveTask(newTask);
        sprint.getTaskList().saveTask(newTask2);

            //Get sprint task list
        List<String> tasksNames = controller.getSprintTasks(project.getCode(), sprint.getIdSprint());

        //Asserts
        assertEquals(2, tasksNames.size());
        assertEquals("test", tasksNames.get(0));
        assertEquals("test2", tasksNames.get(1));
    }

    @Test
    void getSprintTasksEmptyTest() {
        //Arrange
        Company company = new Company();
        TaskMapper mapper = new TaskMapper();
        CreateSprintTaskController controller = new CreateSprintTaskController(company, mapper);

        //Create a project
        Typology typo = company.getTypologyStore().getTypology("Fixed Cost");
        Customer customer = company.getCustomerStore().getCustomerByName("Teste");
        BusinessSector sector = company.getBusinessSectorStore().getBusinessSectorByDescription("sector");
        Project project = company.getProjectStore().createProject( "prototype", "test1234", customer,
                typo, sector, LocalDate.now(), 7, 5000);
        company.getProjectStore().saveNewProject(project);

        //Create a sprint
        Sprint sprint = project.getSprints().createSprint("Sprint 1", LocalDate.of(2022, 2, 1), 2);
        project.getSprints().saveSprint(sprint);

        //Get sprint task list
        List<String> tasksNames = controller.getSprintTasks(project.getCode(), sprint.getIdSprint());

        //Asserts
        assertEquals(0, tasksNames.size());
    }

    @Test
    void getCurrentProjectTeamTestSuccess() {
        //Arrange
        Company company = new Company();
        TaskMapper mapper = new TaskMapper();
        CreateSprintTaskController controller = new CreateSprintTaskController(company, mapper);

        //Create a project
        Typology typo = company.getTypologyStore().getTypology("Fixed Cost");
        Customer customer = company.getCustomerStore().getCustomerByName("Teste");
        BusinessSector sector = company.getBusinessSectorStore().getBusinessSectorByDescription("sector");
        Project project = company.getProjectStore().createProject( "prototype", "test1234", customer,
                typo, sector, LocalDate.now(), 7, 5000);
        company.getProjectStore().saveNewProject(project);

        //Create a sprint
        Sprint sprint = project.getSprints().createSprint("Sprint 1", LocalDate.of(2022, 2, 1), 2);
        project.getSprints().saveSprint(sprint);

        //Create project team
        UserProfile profile = company.getUserProfileStore().getUserProfile("Visitor");
        SystemUser user1 = new SystemUser("user test", "test@test.pt", "test", "encript", "encript", "photo", profile);
        Resource res1 = new Resource(user1, LocalDate.of(2022, 2, 1), LocalDate.of(2023, 2, 1), 100, 1);
        SystemUser user2 = new SystemUser("user test2", "test2@test.pt", "test", "encript", "encript", "photo", profile);
        Resource res2 = new Resource(user2, LocalDate.of(2022, 2, 1), LocalDate.of(2023, 2, 1), 100, 1);
        SystemUser user3 = new SystemUser("user test3", "test@test.pt", "test", "encript", "encript", "photo", profile);
        Resource res3 = new Resource(user3, LocalDate.of(2022, 2, 1), LocalDate.of(2023, 2, 1), 100, 1);
        project.getProjectTeam().saveResource(res1);
        project.getProjectTeam().saveResource(res2);
        project.getProjectTeam().saveResource(res3);

            //Get project team names
        controller.getSprintTasks(project.getCode(), sprint.getIdSprint());
        List<String> test = controller.getCurrentProjectTeam();

        //Asserts
        assertEquals(3, test.size());
        assertEquals("user test", test.get(0));
        assertEquals("user test2", test.get(1));
        assertEquals("user test3", test.get(2));
    }

    @Test
    void getCurrentProjectTeamEmptyTest() {
        //Arrange
        Company company = new Company();
        TaskMapper mapper = new TaskMapper();
        CreateSprintTaskController controller = new CreateSprintTaskController(company, mapper);

        //Create a project
        Typology typo = company.getTypologyStore().getTypology("Fixed Cost");
        Customer customer = company.getCustomerStore().getCustomerByName("Teste");
        BusinessSector sector = company.getBusinessSectorStore().getBusinessSectorByDescription("sector");
        Project project = company.getProjectStore().createProject( "prototype", "test1234", customer,
                typo, sector, LocalDate.now(), 7, 5000);
        company.getProjectStore().saveNewProject(project);

        //Create a sprint
        Sprint sprint = project.getSprints().createSprint("Sprint 1", LocalDate.of(2022, 2, 1), 2);
        project.getSprints().saveSprint(sprint);

        //Get project team names
        controller.getSprintTasks(project.getCode(), sprint.getIdSprint());
        List<String> test = controller.getCurrentProjectTeam();

        //Asserts
        assertEquals(0, test.size());
    }

    @Test
    void createSprintTaskTestSuccessFirstOne() {
        //Arrange
        Company company = new Company();
        TaskMapper mapper = new TaskMapper();
        CreateSprintTaskController controller = new CreateSprintTaskController(company, mapper);

        //Create a project
        Typology typo = company.getTypologyStore().getTypology("Fixed Cost");
        Customer customer = company.getCustomerStore().getCustomerByName("Teste");
        BusinessSector sector = company.getBusinessSectorStore().getBusinessSectorByDescription("sector");
        Project project = company.getProjectStore().createProject( "prototype", "test1234", customer,
                typo, sector, LocalDate.now(), 7, 5000);
        company.getProjectStore().saveNewProject(project);

        //Create a sprint
        Sprint sprint = project.getSprints().createSprint("Sprint 1", LocalDate.of(2022, 2, 1), 2);
        project.getSprints().saveSprint(sprint);

        //Create project team
        UserProfile profile = company.getUserProfileStore().getUserProfile("Visitor");
        SystemUser user1 = new SystemUser("user test", "test@test.pt", "test", "encript", "encript", "photo", profile);
        Resource res1 = new Resource(user1, LocalDate.of(2022, 2, 1), LocalDate.of(2023, 2, 1), 100, 1);
        SystemUser user2 = new SystemUser("user test2", "test2@test.pt", "test", "encript", "encript", "photo", profile);
        Resource res2 = new Resource(user2, LocalDate.of(2022, 2, 1), LocalDate.of(2023, 2, 1), 100, 1);
        SystemUser user3 = new SystemUser("user test3", "test@test.pt", "test", "encript", "encript", "photo", profile);
        Resource res3 = new Resource(user3, LocalDate.of(2022, 2, 1), LocalDate.of(2023, 2, 1), 100, 1);
        project.getProjectTeam().saveResource(res1);
        project.getProjectTeam().saveResource(res2);
        project.getProjectTeam().saveResource(res3);

            //Create a new Sprint Task
        controller.getSprintTasks(project.getCode(), sprint.getIdSprint());
        CreateTaskDTO dto = new CreateTaskDTO("newTask", "newTask to a controller test", 10, "deployment", "user test3");

        //Asserts
        assertTrue(controller.createSprintTask(dto));
        assertEquals(1, sprint.getTaskList().getTasksNames().size());
        assertEquals("newTask", sprint.getTaskList().getTasksNames().get(0));
    }

    @Test
    void createSprintTaskTestSuccessOneMore() {
        //Arrange
        Company company = new Company();
        TaskMapper mapper = new TaskMapper();
        CreateSprintTaskController controller = new CreateSprintTaskController(company, mapper);

        //Create a project
        Typology typo = company.getTypologyStore().getTypology("Fixed Cost");
        Customer customer = company.getCustomerStore().getCustomerByName("Teste");
        BusinessSector sector = company.getBusinessSectorStore().getBusinessSectorByDescription("sector");
        Project project = company.getProjectStore().createProject( "prototype", "test1234", customer,
                typo, sector, LocalDate.now(), 7, 5000);
        company.getProjectStore().saveNewProject(project);

        //Create a sprint
        Sprint sprint = project.getSprints().createSprint("Sprint 1", LocalDate.of(2022, 2, 1), 2);
        project.getSprints().saveSprint(sprint);

        //Create project team
        UserProfile profile = company.getUserProfileStore().getUserProfile("Visitor");
        SystemUser user1 = new SystemUser("user test", "test@test.pt", "test", "encript", "encript", "photo", profile);
        Resource res1 = new Resource(user1, LocalDate.of(2022, 2, 1), LocalDate.of(2023, 2, 1), 100, 1);
        SystemUser user2 = new SystemUser("user test2", "test2@test.pt", "test", "encript", "encript", "photo", profile);
        Resource res2 = new Resource(user2, LocalDate.of(2022, 2, 1), LocalDate.of(2023, 2, 1), 100, 1);
        SystemUser user3 = new SystemUser("user test3", "test@test.pt", "test", "encript", "encript", "photo", profile);
        Resource res3 = new Resource(user3, LocalDate.of(2022, 2, 1), LocalDate.of(2023, 2, 1), 100, 1);
        project.getProjectTeam().saveResource(res1);
        project.getProjectTeam().saveResource(res2);
        project.getProjectTeam().saveResource(res3);

        //Create tasks
        TaskType type = new TaskType("type");
        Task newTask = new Task("test", "test test test tests", 10, type, res1);
        Task newTask2 = new Task("test2", "test2 test2 test2 test2", 10, type, res2);
        sprint.getTaskList().saveTask(newTask);
        sprint.getTaskList().saveTask(newTask2);

        //Create a new Sprint Task
        controller.getSprintTasks(project.getCode(), sprint.getIdSprint());
        CreateTaskDTO dto = new CreateTaskDTO("newTask", "newTask to a controller test", 10, "deployment", "user test3");

        //Asserts
        assertTrue(controller.createSprintTask(dto));
        assertEquals(3, sprint.getTaskList().getTasksNames().size());
        assertEquals("newTask", sprint.getTaskList().getTasksNames().get(2));
    }

    @Test
    void createSprintTaskTestSuccessWithPrecendence() {
        //Arrange
        Company company = new Company();
        TaskMapper mapper = new TaskMapper();
        CreateSprintTaskController controller = new CreateSprintTaskController(company, mapper);

        //Create a project
        Typology typo = company.getTypologyStore().getTypology("Fixed Cost");
        Customer customer = company.getCustomerStore().getCustomerByName("Teste");
        BusinessSector sector = company.getBusinessSectorStore().getBusinessSectorByDescription("sector");
        Project project = company.getProjectStore().createProject( "prototype", "test1234", customer,
                typo, sector, LocalDate.now(), 7, 5000);
        company.getProjectStore().saveNewProject(project);

        //Create a sprint
        Sprint sprint = project.getSprints().createSprint("Sprint 1", LocalDate.of(2022, 2, 1), 2);
        project.getSprints().saveSprint(sprint);

        //Create project team
        UserProfile profile = company.getUserProfileStore().getUserProfile("Visitor");
        SystemUser user1 = new SystemUser("user test", "test@test.pt", "test", "encript", "encript", "photo", profile);
        Resource res1 = new Resource(user1, LocalDate.of(2022, 2, 1), LocalDate.of(2023, 2, 1), 100, 1);
        SystemUser user2 = new SystemUser("user test2", "test2@test.pt", "test", "encript", "encript", "photo", profile);
        Resource res2 = new Resource(user2, LocalDate.of(2022, 2, 1), LocalDate.of(2023, 2, 1), 100, 1);
        SystemUser user3 = new SystemUser("user test3", "test@test.pt", "test", "encript", "encript", "photo", profile);
        Resource res3 = new Resource(user3, LocalDate.of(2022, 2, 1), LocalDate.of(2023, 2, 1), 100, 1);
        project.getProjectTeam().saveResource(res1);
        project.getProjectTeam().saveResource(res2);
        project.getProjectTeam().saveResource(res3);

        //Create tasks
        TaskType type = new TaskType("type");
        Task newTask = new Task("test", "test test test tests", 10, type, res1);
        Task newTask2 = new Task("test2", "test2 test2 test2 test2", 10, type, res2);
        sprint.getTaskList().saveTask(newTask);
        sprint.getTaskList().saveTask(newTask2);
        List <String> precedenceList = new ArrayList<>();
        precedenceList.add(newTask.getName());
        precedenceList.add(newTask2.getName());

        //Create a new Sprint Task
        controller.getSprintTasks(project.getCode(), sprint.getIdSprint());
        CreateTaskDTO dto = new CreateTaskDTO("newTask", "newTask to a controller test", 10, "deployment", "user test3", precedenceList);

        //Asserts
        assertTrue(controller.createSprintTask(dto));
        assertEquals(2, sprint.getTaskList().getTaskById(3).getPrecedenceList().size());
        assertEquals("test", sprint.getTaskList().getTaskById(3).getPrecedenceList().get(0));
    }
}