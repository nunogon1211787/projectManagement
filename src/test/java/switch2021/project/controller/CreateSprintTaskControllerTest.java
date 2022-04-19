package switch2021.project.controller;

import org.junit.jupiter.api.Test;
import switch2021.project.dto.CreateTaskDTO;
import switch2021.project.mapper.TaskMapper;
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
        List<String> test = TaskTypeEnum.getTaskTypesDescriptionEnums();
        //Assert
        assertEquals(6, result.size());
        assertEquals(result, test);
    }

    @Test
    void getSprintTasksTestSuccess() {
        //Arrange
        Company company = new Company();
        TaskMapper mapper = new TaskMapper();
        CreateSprintTaskController controller = new CreateSprintTaskController(company, mapper);

        //Create a project
        Typology typo = company.getTypologyRepository().findTypologyByDescription("Fixed Cost");
        Customer customer = company.getCustomerStore().getCustomerByName("Teste");
        BusinessSector sector = company.getBusinessSectorStore().getBusinessSectorByDescription("sector");
        Project project = company.getProjectStore().createProject("prototype", "test1234", customer,
                typo, sector, LocalDate.now(), 7, 5000);
        company.getProjectStore().saveNewProject(project);

        //Create a sprint
        Sprint sprint = project.getSprintList().createAndSaveSprint(1, "Project_2022_1_Sprint 1", "Sprint Name", 2);

        //Create tasks
        UserProfile profile = company.getUserProfileStore().getUserProfile("Visitor");
        SystemUser user = new SystemUser("user test", "test@test.pt", "test", "Qwerty_1", "Qwerty_1", "photo.png", profile.getUserProfileId());
        Resource resource = new Resource(user, LocalDate.of(2022, 2, 1), LocalDate.of(2023, 2, 1), new CostPerHour(100), new PercentageOfAllocation(1));
        TaskTypeEnum type = TaskTypeEnum.Design;
        Task newTask = new Task("test", "test test test tests", 10, type, resource);
        Task newTask2 = new Task("testdois", "test2 test2 test2 test2", 10, type, resource);
        sprint.getTaskStore().saveTask(newTask);
        sprint.getTaskStore().saveTask(newTask2);

        //Get sprint task list
        List<String> tasksNames = controller.getSprintTasks(project.getProjectCode().getCode(), sprint.getSprintID());

        //Asserts
        assertEquals(2, tasksNames.size());
        assertEquals("test", tasksNames.get(0));
        assertEquals("testdois", tasksNames.get(1));
    }

    @Test
    void getSprintTasksEmptyTest() {
        //Arrange
        Company company = new Company();
        TaskMapper mapper = new TaskMapper();
        CreateSprintTaskController controller = new CreateSprintTaskController(company, mapper);

        //Create a project
        Typology typo = company.getTypologyRepository().findTypologyByDescription("Fixed Cost");
        Customer customer = company.getCustomerStore().getCustomerByName("Teste");
        BusinessSector sector = company.getBusinessSectorStore().getBusinessSectorByDescription("sector");
        Project project = company.getProjectStore().createProject("prototype", "test1234", customer,
                typo, sector, LocalDate.now(), 7, 5000);
        company.getProjectStore().saveNewProject(project);

        //Create a sprint
        Sprint sprint = project.getSprintList().createAndSaveSprint(1, "Project_2022_1_Sprint 1", "Sprint Name", 2);

        //Get sprint task list
        List<String> tasksNames = controller.getSprintTasks(project.getProjectCode().getCode(), sprint.getSprintID());

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
        Typology typo = company.getTypologyRepository().findTypologyByDescription("Fixed Cost");
        Customer customer = company.getCustomerStore().getCustomerByName("Teste");
        BusinessSector sector = company.getBusinessSectorStore().getBusinessSectorByDescription("sector");
        Project project = company.getProjectStore().createProject("prototype", "test1234", customer,
                typo, sector, LocalDate.now(), 7, 5000);
        company.getProjectStore().saveNewProject(project);

        //Create a sprint
        Sprint sprint = project.getSprintList().createAndSaveSprint(1, "Project_2022_1_Sprint 1", "Sprint Name", 2);

        //Create project team
        UserProfile profile = company.getUserProfileStore().getUserProfile("Visitor");
        SystemUser user1 = new SystemUser("user test", "test@test.pt", "test", "Qwerty_1", "Qwerty_1", "photo.png", profile.getUserProfileId());
        Resource res1 = new Resource(user1, LocalDate.of(2022, 2, 1), LocalDate.of(2023, 2, 1), new CostPerHour(100), new PercentageOfAllocation(1));
        SystemUser user2 = new SystemUser("user test dois", "test2@test.pt", "test", "Qwerty_1", "Qwerty_1", "photo.png", profile.getUserProfileId());
        Resource res2 = new Resource(user2, LocalDate.of(2022, 2, 1), LocalDate.of(2023, 2, 1), new CostPerHour(100), new PercentageOfAllocation(1));
        SystemUser user3 = new SystemUser("user test tres", "test@test.pt", "test", "Qwerty_1", "Qwerty_1", "photo.png", profile.getUserProfileId());
        Resource res3 = new Resource(user3, LocalDate.of(2022, 2, 1), LocalDate.of(2023, 2, 1), new CostPerHour(100), new PercentageOfAllocation(1));
        project.getProjectTeam().saveResource(res1);
        project.getProjectTeam().saveResource(res2);
        project.getProjectTeam().saveResource(res3);

        //Get project team names
        controller.getSprintTasks(project.getProjectCode().getCode(), sprint.getSprintID());
        List<String> test = controller.getCurrentProjectTeam();

        //Asserts
        assertEquals(3, test.size());
        assertEquals("user test", test.get(0));
        assertEquals("user test dois", test.get(1));
        assertEquals("user test tres", test.get(2));
    }

    @Test
    void getCurrentProjectTeamEmptyTest() {
        //Arrange
        Company company = new Company();
        TaskMapper mapper = new TaskMapper();
        CreateSprintTaskController controller = new CreateSprintTaskController(company, mapper);

        //Create a project
        Typology typo = company.getTypologyRepository().findTypologyByDescription("Fixed Cost");
        Customer customer = company.getCustomerStore().getCustomerByName("Teste");
        BusinessSector sector = company.getBusinessSectorStore().getBusinessSectorByDescription("sector");
        Project project = company.getProjectStore().createProject("prototype", "test1234", customer,
                typo, sector, LocalDate.now(), 7, 5000);
        company.getProjectStore().saveNewProject(project);

        //Create a sprint
        Sprint sprint = project.getSprintList().createAndSaveSprint(1, "Project_2022_1_Sprint 1", "Sprint Name", 2);

        //Get project team names
        controller.getSprintTasks(project.getProjectCode().getCode(), sprint.getSprintID());
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
        Typology typo = company.getTypologyRepository().findTypologyByDescription("Fixed Cost");
        Customer customer = company.getCustomerStore().getCustomerByName("Teste");
        BusinessSector sector = company.getBusinessSectorStore().getBusinessSectorByDescription("sector");
        Project project = company.getProjectStore().createProject("prototype", "test1234", customer,
                typo, sector, LocalDate.now(), 7, 5000);
        company.getProjectStore().saveNewProject(project);

        //Create a sprint
        Sprint sprint = project.getSprintList().createAndSaveSprint(1, "Project_2022_1_Sprint 1", "Sprint Name", 2);

        //Create project team
        UserProfile profile = company.getUserProfileStore().getUserProfile("Visitor");
        SystemUser user1 = new SystemUser("user test", "test@test.pt", "test", "Qwerty_1", "Qwerty_1", "photo.png", profile.getUserProfileId());
        Resource res1 = new Resource(user1, LocalDate.of(2022, 2, 1), LocalDate.of(2023, 2, 1), new CostPerHour(100), new PercentageOfAllocation(1));
        SystemUser user2 = new SystemUser("user test dois", "test2@test.pt", "test", "Qwerty_1", "Qwerty_1", "photo.png", profile.getUserProfileId());
        Resource res2 = new Resource(user2, LocalDate.of(2022, 2, 1), LocalDate.of(2023, 2, 1), new CostPerHour(100), new PercentageOfAllocation(1));
        SystemUser user3 = new SystemUser("user test tres", "test@test.pt", "test", "Qwerty_1", "Qwerty_1", "photo.png", profile.getUserProfileId());
        Resource res3 = new Resource(user3, LocalDate.of(2022, 2, 1), LocalDate.of(2023, 2, 1), new CostPerHour(100), new PercentageOfAllocation(1));
        project.getProjectTeam().saveResource(res1);
        project.getProjectTeam().saveResource(res2);
        project.getProjectTeam().saveResource(res3);

        //Create a new Sprint Task
        controller.getSprintTasks(project.getProjectCode().getCode(), sprint.getSprintID());
        CreateTaskDTO dto = new CreateTaskDTO("newTask", "newTask to a controller test", 10, "Design", "user test tres");

        //Asserts
        assertTrue(controller.createSprintTask(dto));
        assertEquals(1, sprint.getTaskStore().getTasksNames().size());
        assertEquals("newTask", sprint.getTaskStore().getTasksNames().get(0));
    }

    @Test
    void createSprintTaskTestSuccessOneMore() {
        //Arrange
        Company company = new Company();
        TaskMapper mapper = new TaskMapper();
        CreateSprintTaskController controller = new CreateSprintTaskController(company, mapper);

        //Create a project
        Typology typo = company.getTypologyRepository().findTypologyByDescription("Fixed Cost");
        Customer customer = company.getCustomerStore().getCustomerByName("Teste");
        BusinessSector sector = company.getBusinessSectorStore().getBusinessSectorByDescription("sector");
        Project project = company.getProjectStore().createProject("prototype", "test1234", customer,
                typo, sector, LocalDate.now(), 7, 5000);
        company.getProjectStore().saveNewProject(project);

        //Create a sprint
        Sprint sprint = project.getSprintList().createAndSaveSprint(1, "Project_2022_1_Sprint 1", "Sprint Name", 2);

        //Create project team
        UserProfile profile = company.getUserProfileStore().getUserProfile("Visitor");
        SystemUser user1 = new SystemUser("user test", "test@test.pt", "test", "Qwerty_1", "Qwerty_1", "photo.png", profile.getUserProfileId());
        Resource res1 = new Resource(user1, LocalDate.of(2022, 2, 1), LocalDate.of(2023, 2, 1), new CostPerHour(100), new PercentageOfAllocation(1));
        SystemUser user2 = new SystemUser("user test dois", "test2@test.pt", "test", "Qwerty_1", "Qwerty_1", "photo.png", profile.getUserProfileId());
        Resource res2 = new Resource(user2, LocalDate.of(2022, 2, 1), LocalDate.of(2023, 2, 1), new CostPerHour(100), new PercentageOfAllocation(1));
        SystemUser user3 = new SystemUser("user test tres", "test@test.pt", "test", "Qwerty_1", "Qwerty_1", "photo.png", profile.getUserProfileId());
        Resource res3 = new Resource(user3, LocalDate.of(2022, 2, 1), LocalDate.of(2023, 2, 1), new CostPerHour(100), new PercentageOfAllocation(1));
        project.getProjectTeam().saveResource(res1);
        project.getProjectTeam().saveResource(res2);
        project.getProjectTeam().saveResource(res3);

        //Create tasks
        TaskTypeEnum type = TaskTypeEnum.Design;
        Task newTask = new Task("test", "test test test tests", 10, type, res1);
        Task newTask2 = new Task("testdois", "test2 test2 test2 test2", 10, type, res2);
        sprint.getTaskStore().saveTask(newTask);
        sprint.getTaskStore().saveTask(newTask2);

        //Create a new Sprint Task
        controller.getSprintTasks(project.getProjectCode().getCode(), sprint.getSprintID());
        CreateTaskDTO dto = new CreateTaskDTO("newTask", "newTask to a controller test", 10, "Design", "user test tres");

        //Asserts
        assertTrue(controller.createSprintTask(dto));
        assertEquals(3, sprint.getTaskStore().getTasksNames().size());
        assertEquals("newTask", sprint.getTaskStore().getTasksNames().get(2));
    }

    @Test
    void createSprintTaskTestSuccessWithPrecendence() {
        //Arrange
        Company company = new Company();
        TaskMapper mapper = new TaskMapper();
        CreateSprintTaskController controller = new CreateSprintTaskController(company, mapper);

        //Create a project
        Typology typo = company.getTypologyRepository().findTypologyByDescription("Fixed Cost");
        Customer customer = company.getCustomerStore().getCustomerByName("Teste");
        BusinessSector sector = company.getBusinessSectorStore().getBusinessSectorByDescription("sector");
        Project project = company.getProjectStore().createProject("prototype", "test1234", customer,
                typo, sector, LocalDate.now(), 7, 5000);
        company.getProjectStore().saveNewProject(project);

        //Create a sprint
        Sprint sprint = project.getSprintList().createAndSaveSprint(1, "Project_2022_1_Sprint 1", "Sprint Name", 2);

        //Create project team
        UserProfile profile = company.getUserProfileStore().getUserProfile("Visitor");
        SystemUser user1 = new SystemUser("user test", "test@test.pt", "test", "Qwerty_1", "Qwerty_1", "photo.png", profile.getUserProfileId());
        Resource res1 = new Resource(user1, LocalDate.of(2022, 2, 1), LocalDate.of(2023, 2, 1), new CostPerHour(100), new PercentageOfAllocation(1));
        SystemUser user2 = new SystemUser("user test dois", "test2@test.pt", "test", "Qwerty_1", "Qwerty_1", "photo.png", profile.getUserProfileId());
        Resource res2 = new Resource(user2, LocalDate.of(2022, 2, 1), LocalDate.of(2023, 2, 1), new CostPerHour(100), new PercentageOfAllocation(1));
        SystemUser user3 = new SystemUser("user test tres", "test@test.pt", "test", "Qwerty_1", "Qwerty_1", "photo.png", profile.getUserProfileId());
        Resource res3 = new Resource(user3, LocalDate.of(2022, 2, 1), LocalDate.of(2023, 2, 1), new CostPerHour(100), new PercentageOfAllocation(1));
        project.getProjectTeam().saveResource(res1);
        project.getProjectTeam().saveResource(res2);
        project.getProjectTeam().saveResource(res3);

        //Create tasks
        TaskTypeEnum type = TaskTypeEnum.Design;
        Task newTask = new Task("test", "test test test tests", 10, type, res1);
        Task newTask2 = new Task("testdois", "test2 test2 test2 test2", 10, type, res2);
        sprint.getTaskStore().saveTask(newTask);
        sprint.getTaskStore().saveTask(newTask2);
        List<String> precedenceList = new ArrayList<>();
        precedenceList.add(newTask.getName().getNameF());
        precedenceList.add(newTask2.getName().getNameF());

        //Create a new Sprint Task
        controller.getSprintTasks(project.getProjectCode().getCode(), sprint.getSprintID());
        CreateTaskDTO dto = new CreateTaskDTO("newTask", "newTask to a controller test",
                10, "Design", "user test tres", precedenceList);

        //Asserts
        assertTrue(controller.createSprintTask(dto));
        assertEquals(2, sprint.getTaskStore().getTaskById(3).getPrecedenceList().size());
        assertEquals("test", sprint.getTaskStore().getTaskById(3).getPrecedenceList().get(0));
    }
}