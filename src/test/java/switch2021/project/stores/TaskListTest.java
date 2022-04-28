package switch2021.project.stores;

import org.junit.jupiter.api.Test;
import switch2021.project.model.Project.*;
import switch2021.project.dto.old.CreateTaskDTO;
import switch2021.project.mapper.old.TaskMapper;
import switch2021.project.model.*;
import switch2021.project.model.Resource.old.Resource;
import switch2021.project.model.SystemUser.SystemUser;
import switch2021.project.model.Task.*;
import switch2021.project.model.Typology.Typology;
import switch2021.project.model.UserProfile.UserProfile;
import switch2021.project.model.valueObject.*;

import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

public class TaskListTest {


    @Test
    void testIfCreatedListStartsEmpty() {

        //Arrange
        TaskStore test = new TaskStore();
        //Assert
        int listSizeResult = test.getTaskList().size();
        assertEquals( 0, listSizeResult);
        assertTrue(test.getTaskList().isEmpty());
    }

    @Test
    void addTaskToTheList() {

        //Arrange
        TaskStore test = new TaskStore();
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
        TaskStore test = new TaskStore();
        UserProfile profile = comp.getUserProfileStore().getUserProfile("Visitor");
        SystemUser user = new SystemUser("user test", "test@test.pt", "test", "Qwerty_1", "Qwerty_1", "photo.png", profile.getUserProfileId());
        Resource resource = new Resource(user, LocalDate.of(2022, 2, 1), LocalDate.of(2023, 2, 1), new CostPerHour(100), new PercentageOfAllocation(1));
        TaskTypeEnum type = TaskTypeEnum.Design;
        Task newTask = new Task("test", "test test test tests", 10, type, resource);
        Task newTask2 = new Task("testdois", "test2 test2 test2 test2", 10, type, resource);
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
        TaskStore test = new TaskStore();
        UserProfile profile = comp.getUserProfileStore().getUserProfile("Visitor");
        SystemUser user = new SystemUser("user test", "test@test.pt", "test", "Qwerty_1", "Qwerty_1", "photo.png", profile.getUserProfileId());
        Resource resource = new Resource(user, LocalDate.of(2022, 2, 1), LocalDate.of(2023, 2, 1), new CostPerHour(100), new PercentageOfAllocation(1));
        TaskTypeEnum type = TaskTypeEnum.Design;
        Task newTask = new Task("test", "test test test tests", 10, type, resource);
        //Assert
        assertTrue(test.saveTask(newTask));
        assertEquals(1, test.getTasksNames().size());
    }


    @Test
    void createSprintTaskTestSuccess() {
        //Arrange
        Company comp = new Company();
        TaskStore test = new TaskStore();
        TaskMapper map = new TaskMapper();
        CreateTaskDTO dto = new CreateTaskDTO("test", "test test test tests", 10, "Meeting", "user test");

            // Create Project
        Customer cust = new Customer("test", "test@test.pt", 123456789);
        Typology typo = new Typology("test123");
        BusinessSector busSec = new BusinessSector("t1234");
        Project proj = new Project("project", "project test", cust, typo, busSec, LocalDate.of(2022, 2, 1), 4, 7000);
        UserProfile profile = comp.getUserProfileStore().getUserProfile("Visitor");
        SystemUser user = new SystemUser("user test", "test@test.pt", "test", "Qwerty_1", "Qwerty_1", "photo.png", profile.getUserProfileId());
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
        TaskStore test = new TaskStore();
        TaskMapper map = new TaskMapper();
        CreateTaskDTO dto = new CreateTaskDTO("test", "test test test tests", 10, "Meeting", "user test");
        CreateTaskDTO dto2 = new CreateTaskDTO("test", "test test test tests", 10, "Meeting", "user test");

        // Create Project
        Customer cust = new Customer("test", "test@test.pt", 123456789);
        Typology typo = new Typology("test123");
        BusinessSector busSec = new BusinessSector("t1234");
        Project proj = new Project("project", "project test", cust, typo, busSec, LocalDate.of(2022, 2, 1), 4, 7000);
        UserProfile profile = comp.getUserProfileStore().getUserProfile("Visitor");
        SystemUser user = new SystemUser("user test", "test@test.pt", "test", "Qwerty_1", "Qwerty_1", "photo.png", profile.getUserProfileId());
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
        TaskStore test = new TaskStore();
        TaskMapper map = new TaskMapper();
        CreateTaskDTO dto = new CreateTaskDTO("test", "test test test tests", 10, "Meeting", "user test");
        CreateTaskDTO dto2 = new CreateTaskDTO("testdois", "test2 test2 test2 test2", 10, "Meeting", "user test");

        // Create Project
        Customer cust = new Customer("test", "test@test.pt", 123456789);
        Typology typo = new Typology("test123");
        BusinessSector busSec = new BusinessSector("t1234");
        Project proj = new Project("project", "project test", cust, typo, busSec, LocalDate.of(2022, 2, 1), 4, 7000);
        UserProfile profile = comp.getUserProfileStore().getUserProfile("Visitor");
        SystemUser user = new SystemUser("user test", "test@test.pt", "test", "Qwerty_1", "Qwerty_1", "photo.png", profile.getUserProfileId());
        Resource resource = proj.getProjectTeam().createResource(user, LocalDate.of(2022, 2, 1), LocalDate.of(2023, 2, 1), 100, 1);
        proj.getProjectTeam().saveResource(resource);

        test.createSprintTask(dto, map, proj);
        test.createSprintTask(dto2, map, proj);

        //Asserts
        assertEquals(new Task("test", "test test test tests", 10, TaskTypeEnum.valueOf("Meeting"), resource), test.getTaskById(1));
        assertEquals(new Task("testdois", "test2 test2 test2 test2", 10, TaskTypeEnum.valueOf("Meeting"), resource), test.getTaskById(2));
    }

    @Test
    void getTaskByDescription() {
        //Arrange
        Company comp = new Company();
        TaskStore test = new TaskStore();
        TaskMapper map = new TaskMapper();
        CreateTaskDTO dto = new CreateTaskDTO("test", "test test test tests", 10, "Meeting", "user test");
        CreateTaskDTO dto2 = new CreateTaskDTO("testdois", "test2 test2 test2 test2", 10, "Meeting", "user test");

        // Create Project
        Customer cust = new Customer("test", "test@test.pt", 123456789);
        Typology typo = new Typology("test123");
        BusinessSector busSec = new BusinessSector("t1234");
        Project proj = new Project("project", "project test", cust, typo, busSec, LocalDate.of(2022, 2, 1), 4, 7000);
        UserProfile profile = comp.getUserProfileStore().getUserProfile("Visitor");
        SystemUser user = new SystemUser("user test", "test@test.pt", "test", "Qwerty_1", "Qwerty_1", "photo.png", profile.getUserProfileId());
        Resource resource = proj.getProjectTeam().createResource(user, LocalDate.of(2022, 2, 1), LocalDate.of(2023, 2, 1), 100, 1);
        proj.getProjectTeam().saveResource(resource);

        test.createSprintTask(dto, map, proj);
        test.createSprintTask(dto2, map, proj);

        //Asserts
        assertEquals(new Task("test", "test test test tests", 10, TaskTypeEnum.valueOf("Meeting"), resource), test.getTaskByName("test"));
        assertEquals(new Task("testdois", "test2 test2 test2 test2", 10, TaskTypeEnum.valueOf("Meeting"), resource), test.getTaskByName("testdois"));
    }
}
