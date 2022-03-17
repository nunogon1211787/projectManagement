package switch2021.project.stores;

import org.junit.jupiter.api.Test;
import switch2021.project.Immutables.Description;
import switch2021.project.dto.CreateTaskDTO;
import switch2021.project.mapper.TaskMapper;
import switch2021.project.model.*;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class TaskListTest {


    @Test
    void testIfCreatedListStartsEmpty() {

        //Arrange
        TaskList test = new TaskList();
        //Assert
        int listSizeResult = test.getTaskList().size();
        assertEquals( 0, listSizeResult);
        assertTrue(test.getTaskList().isEmpty());
    }

    @Test
    void addTaskToTheList() {

        //Arrange
        TaskList test = new TaskList();
        Task newTask = new Task("teste");
        test.saveTask(newTask);
        //Assert
        assertEquals(1,test.getTaskList().size());
        assertTrue(test.getTaskList().contains(newTask));
    }

    @Test
    void removeTaskFromTheList() {

        //Arrange
        Company comp = new Company();
        TaskList test = new TaskList();
        UserProfile profile = comp.getUserProfileStore().getUserProfile("Visitor");
        SystemUser user = new SystemUser("user test", "test@test.pt", "test", "encript", "encript", "photo", profile);
        Resource resource = new Resource(user, LocalDate.of(2022, 2, 1), LocalDate.of(2023, 2, 1), 100, 1);
        TaskType type = new TaskType("type");
        Task newTask = new Task("test", "test test test tests", 10, type, resource);
        Task newTask2 = new Task("test2", "test2 test2 test2 test2", 10, type, resource);
        //Assert
        test.saveTask(newTask);
        test.saveTask(newTask2);
        assertTrue(test.removeTaskFromTheList(newTask));
        assertEquals(1,test.getTaskList().size());
    }

    @Test
    void validateIfTaskDontExistOnList() {

        //Arrange
        Company comp = new Company();
        TaskList test = new TaskList();
        UserProfile profile = comp.getUserProfileStore().getUserProfile("Visitor");
        SystemUser user = new SystemUser("user test", "test@test.pt", "test", "encript", "encript", "photo", profile);
        Resource resource = new Resource(user, LocalDate.of(2022, 2, 1), LocalDate.of(2023, 2, 1), 100, 1);
        TaskType type = new TaskType("type");
        Task newTask = new Task("test", "test test test tests", 10, type, resource);
        //Assert
        assertTrue(test.saveTask(newTask));
        assertEquals(1, test.getTasksNames().size());
    }

    @Test
    void createTaskStatus() {
        //Arrange
        TaskStatus status = new TaskStatus("test123");
        //Assert
        assertEquals("test123", status.getDescription().getText());
    }

    @Test
    void getTaskStatusByDescription() {
        //Arrange
        TaskStatusStore store = new TaskStatusStore();
        //Act
        store.populateDefault();
        //Assert
        assertEquals("Planned", store.getTaskStatusByDescription("Planned").getDescription().getText());
    }

    @Test
    void taskStatus() {
        //Arranje
        Task task = new Task("test");
        TaskStatus status = new TaskStatus("test");
        task.setStatus(status);

        //Assert
        assertEquals(status,task.getStatus());
    }

    @Test
    void taskStatusHash() {
        //Arranje
        TaskStatus status = new TaskStatus("test");
        TaskStatus status2 = new TaskStatus("test_2");

        //Assert
        assertNotEquals(status.hashCode(),status2.hashCode());
        assertEquals(status.hashCode(),status.hashCode());
    }

    @Test
    void taskStatusStoreHash() {
        //Arranje
        TaskStatusStore status = new TaskStatusStore();
        status.populateDefault();
        TaskStatusStore status2 = new TaskStatusStore();
        status2.populateDefault();
        TaskStatus teste = new TaskStatus("Teste");
        status2.getTaskStatusList().set(0,teste);

        //Assert
        assertNotEquals(status.hashCode(),status2.hashCode());
        assertEquals(status.hashCode(),status.hashCode());
    }

    @Test
    void createSprintTaskTestSuccess() {
        //Arrange
        Company comp = new Company();
        TaskList test = new TaskList();
        TaskMapper map = new TaskMapper();
        CreateTaskDTO dto = new CreateTaskDTO("test", "test test test tests", 10, "Meeting", "user test");

            // Create Project
        Customer cust = new Customer("test", "test@test.pt", 123456789);
        Typology typo = new Typology("test123");
        BusinessSector busSec = new BusinessSector("t1234");
        ProjectStatus status = new ProjectStatus("test1234");
        Project proj = new Project("project", "project test", cust, typo, busSec, LocalDate.of(2022, 2, 1), status, 4, 7000);
        comp.getProjectStore().saveNewProject(proj);
        UserProfile profile = comp.getUserProfileStore().getUserProfile("Visitor");
        SystemUser user = new SystemUser("user test", "test@test.pt", "test", "encript", "encript", "photo", profile);
        Resource resource = proj.getProjectTeam().createResource(user, LocalDate.of(2022, 2, 1), LocalDate.of(2023, 2, 1), 100, 1);
        proj.getProjectTeam().saveResource(resource);

        test.createSprintTask(dto, map, proj);

        //Asserts
        assertEquals(1, test.getTasksNames().size());
        assertEquals("test", test.getTasksNames().get(0));
    }

    @Test
    void createSprintTaskTestRepeated() {
        //Arrange
        Company comp = new Company();
        TaskList test = new TaskList();
        TaskMapper map = new TaskMapper();
        CreateTaskDTO dto = new CreateTaskDTO("test", "test test test tests", 10, "Meeting", "user test");
        CreateTaskDTO dto2 = new CreateTaskDTO("test", "test test test tests", 10, "Meeting", "user test");

        // Create Project
        Customer cust = new Customer("test", "test@test.pt", 123456789);
        Typology typo = new Typology("test123");
        BusinessSector busSec = new BusinessSector("t1234");
        ProjectStatus status = new ProjectStatus("test1234");
        Project proj = new Project("project", "project test", cust, typo, busSec, LocalDate.of(2022, 2, 1), status, 4, 7000);
        comp.getProjectStore().saveNewProject(proj);
        UserProfile profile = comp.getUserProfileStore().getUserProfile("Visitor");
        SystemUser user = new SystemUser("user test", "test@test.pt", "test", "encript", "encript", "photo", profile);
        Resource resource = proj.getProjectTeam().createResource(user, LocalDate.of(2022, 2, 1), LocalDate.of(2023, 2, 1), 100, 1);
        proj.getProjectTeam().saveResource(resource);

        test.createSprintTask(dto, map, proj);
        test.createSprintTask(dto2, map, proj);

        //Asserts
        assertEquals(1, test.getTasksNames().size());
        assertEquals("test", test.getTasksNames().get(0));
    }

    @Test
    void getTaskByIdTestSuccess() {
        //Arrange
        Company comp = new Company();
        TaskList test = new TaskList();
        TaskMapper map = new TaskMapper();
        CreateTaskDTO dto = new CreateTaskDTO("test", "test test test tests", 10, "Meeting", "user test");
        CreateTaskDTO dto2 = new CreateTaskDTO("test2", "test2 test2 test2 test2", 10, "Meeting", "user test");

        // Create Project
        Customer cust = new Customer("test", "test@test.pt", 123456789);
        Typology typo = new Typology("test123");
        BusinessSector busSec = new BusinessSector("t1234");
        ProjectStatus status = new ProjectStatus("test1234");
        Project proj = new Project("project", "project test", cust, typo, busSec, LocalDate.of(2022, 2, 1), status, 4, 7000);
        comp.getProjectStore().saveNewProject(proj);
        UserProfile profile = comp.getUserProfileStore().getUserProfile("Visitor");
        SystemUser user = new SystemUser("user test", "test@test.pt", "test", "encript", "encript", "photo", profile);
        Resource resource = proj.getProjectTeam().createResource(user, LocalDate.of(2022, 2, 1), LocalDate.of(2023, 2, 1), 100, 1);
        proj.getProjectTeam().saveResource(resource);

        test.createSprintTask(dto, map, proj);
        test.createSprintTask(dto2, map, proj);

        //Asserts
        assertEquals(new Task("test", "test test test tests", 10, comp.getTaskTypeStore().getTypeByDescription("Meeting"), resource), test.getTaskById(1));
        assertEquals(new Task("test2", "test2 test2 test2 test2", 10, comp.getTaskTypeStore().getTypeByDescription("Meeting"), resource), test.getTaskById(2));
    }

    @Test
    void getTaskByDescription() {
        //Arrange
        Company comp = new Company();
        TaskList test = new TaskList();
        TaskMapper map = new TaskMapper();
        CreateTaskDTO dto = new CreateTaskDTO("test", "test test test tests", 10, "Meeting", "user test");
        CreateTaskDTO dto2 = new CreateTaskDTO("test2", "test2 test2 test2 test2", 10, "Meeting", "user test");

        // Create Project
        Customer cust = new Customer("test", "test@test.pt", 123456789);
        Typology typo = new Typology("test123");
        BusinessSector busSec = new BusinessSector("t1234");
        ProjectStatus status = new ProjectStatus("test1234");
        Project proj = new Project("project", "project test", cust, typo, busSec, LocalDate.of(2022, 2, 1), status, 4, 7000);
        comp.getProjectStore().saveNewProject(proj);
        UserProfile profile = comp.getUserProfileStore().getUserProfile("Visitor");
        SystemUser user = new SystemUser("user test", "test@test.pt", "test", "encript", "encript", "photo", profile);
        Resource resource = proj.getProjectTeam().createResource(user, LocalDate.of(2022, 2, 1), LocalDate.of(2023, 2, 1), 100, 1);
        proj.getProjectTeam().saveResource(resource);

        test.createSprintTask(dto, map, proj);
        test.createSprintTask(dto2, map, proj);

        //Asserts
        assertEquals(new Task("test", "test test test tests", 10, comp.getTaskTypeStore().getTypeByDescription("Meeting"), resource), test.getTaskByName("test"));
        assertEquals(new Task("test2", "test2 test2 test2 test2", 10, comp.getTaskTypeStore().getTypeByDescription("Meeting"), resource), test.getTaskByName("test2"));
    }
}
