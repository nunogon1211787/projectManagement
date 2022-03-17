package switch2021.project.controller;

import org.junit.jupiter.api.Test;
import switch2021.project.immutable.Date;
import switch2021.project.dto.TaskEffortDTO;
import switch2021.project.dto.TaskIdNameDTO;
import switch2021.project.dto.UserStorySprintProjectDTO;
import switch2021.project.mapper.RegisterWorkToTaskMapper;
import switch2021.project.model.*;
import switch2021.project.stores.ProjectStore;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RegisterWorkToTaskControllerTest {
    @Test
    public void getTasksTest() {
        //Arrange
        Company company = new Company();
        RegisterWorkToTaskMapper mapper = new RegisterWorkToTaskMapper();
        RegisterWorkToTaskController controller = new RegisterWorkToTaskController(company, mapper);
        ProjectStore projectStore = company.getProjectStore();
        //Project
        Typology typo = company.getTypologyStore().getTypologyByDescription("Fixed Cost");
        Customer customer = company.getCustomerStore().getCustomerByName("isep");
        BusinessSector sector = company.getBusinessSectorStore().getBusinessSectorByDescription("it");
        Project project1 = company.getProjectStore().createProject("prototype4", "proj4Prototype", customer,
                typo, sector, LocalDate.of(2022, 1, 1), 2, 4000);
        project1.setEndDate(LocalDate.of(2022, 1, 31));
        projectStore.saveNewProject(project1);
        String projectCode1 = project1.getCode(); //"Project_2022_1"
        //Resource
        UserProfile profile = company.getUserProfileStore().getUserProfile("Visitor");
        SystemUser user2 = company.getSystemUserStore().createSystemUser("manuelmartins", "manuelmartins@beaver.com", "tester", "ghi", "ghi", "photo", profile);
        LocalDate startDateMm = LocalDate.of(2022, 1, 1);
        LocalDate endDateMm = LocalDate.of(2022, 1, 31);
        Resource resource1 = project1.getProjectTeam().createResource(user2, startDateMm, endDateMm, 100, 1);
        project1.getProjectTeam().saveResource(resource1);
        //Sprint
        Sprint sprint1 = project1.getSprints().createSprint("Sprint 1", LocalDate.of(2022, 1, 1), 2);
        project1.getSprints().saveSprint(sprint1);
        int id_Sprint1 = sprint1.getIdSprint(); //1
        //UserStory
        UserStory userStory1 = project1.getProductBacklog().createUserStory("US001", 1, "description", 5);
        project1.getProductBacklog().saveUserStory(userStory1);
        int id_UserStory1 = userStory1.getIdUserStory(); //1
        sprint1.getSprintBacklog().saveUserStoryToSprintBacklog(userStory1);
        //Tasks
        String taskDescription = "must be at least 20 characters";
        TaskType taskType = company.getTaskTypeStore().getTypeByDescription("Testing");
        Task task1 = userStory1.getTasks().createTask("task1", taskDescription, 20.0, taskType, resource1);
        Task task2 = userStory1.getTasks().createTask("task2", taskDescription, 10.0, taskType, resource1);
        userStory1.getTasks().saveTask(task1);
        userStory1.getTasks().saveTask(task2);
        //userStorySprintProjectDTO
        UserStorySprintProjectDTO userStorySprintProjectDTO = new UserStorySprintProjectDTO(projectCode1, id_Sprint1, id_UserStory1);
        //Act
        List<TaskIdNameDTO> taskIdNameDTOList = controller.getTasks(userStorySprintProjectDTO);
        //Assert
        assertEquals(2, taskIdNameDTOList.size());
        assertEquals(1, taskIdNameDTOList.get(0).getTaskId());
        assertEquals(2, taskIdNameDTOList.get(1).getTaskId());
        assertEquals("task1", taskIdNameDTOList.get(0).getTaskName());
        assertEquals("task2", taskIdNameDTOList.get(1).getTaskName());
    }

    @Test
    public void getTaskTest() {
        //Arrange
        Company company = new Company();
        RegisterWorkToTaskMapper mapper = new RegisterWorkToTaskMapper();
        RegisterWorkToTaskController controller = new RegisterWorkToTaskController(company, mapper);
        ProjectStore projectStore = company.getProjectStore();
        //Project
        Typology typo = company.getTypologyStore().getTypologyByDescription("Fixed Cost");
        Customer customer = company.getCustomerStore().getCustomerByName("isep");
        BusinessSector sector = company.getBusinessSectorStore().getBusinessSectorByDescription("it");
        Project project1 = company.getProjectStore().createProject("prototype4", "proj4Prototype", customer,
                typo, sector, LocalDate.of(2022, 1, 1), 2, 4000);
        project1.setEndDate(LocalDate.of(2022, 1, 31));
        projectStore.saveNewProject(project1);
        String projectCode1 = project1.getCode(); //"Project_2022_1"
        //Resource
        UserProfile profile = company.getUserProfileStore().getUserProfile("Visitor");
        SystemUser user2 = company.getSystemUserStore().createSystemUser("manuelmartins", "manuelmartins@beaver.com", "tester", "ghi", "ghi", "photo", profile);
        LocalDate startDateMm = LocalDate.of(2022, 1, 1);
        LocalDate endDateMm = LocalDate.of(2022, 1, 31);
        Resource resource1 = project1.getProjectTeam().createResource(user2, startDateMm, endDateMm, 100, 1);
        project1.getProjectTeam().saveResource(resource1);
        //Sprint
        Sprint sprint1 = project1.getSprints().createSprint("Sprint 1", LocalDate.of(2022, 1, 1), 2);
        project1.getSprints().saveSprint(sprint1);
        int id_Sprint1 = sprint1.getIdSprint(); //1
        //UserStory
        UserStory userStory1 = project1.getProductBacklog().createUserStory("US001", 1, "description", 5);
        project1.getProductBacklog().saveUserStory(userStory1);
        int id_UserStory1 = userStory1.getIdUserStory(); //1
        sprint1.getSprintBacklog().saveUserStoryToSprintBacklog(userStory1);
        //Tasks
        String taskDescription = "must be at least 20 characters";
        TaskType taskType = company.getTaskTypeStore().getTypeByDescription("Testing");
        Task task1 = userStory1.getTasks().createTask("task1", taskDescription, 20.0, taskType, resource1);
        Task task2 = userStory1.getTasks().createTask("task2", taskDescription, 10.0, taskType, resource1);
        userStory1.getTasks().saveTask(task1);
        int id_task1 = task1.getIdTask(); //1
        userStory1.getTasks().saveTask(task2);
        int id_task2 = task2.getIdTask(); //2
        //userStorySprintProjectDTO
        UserStorySprintProjectDTO userStorySprintProjectDTO = new UserStorySprintProjectDTO(projectCode1, id_Sprint1, id_UserStory1);
        //Act
        controller.getTasks(userStorySprintProjectDTO);
        TaskIdNameDTO wantedTask1 = controller.getTask(id_task1);
        TaskIdNameDTO wantedTask2 = controller.getTask(id_task2);
        //Assert
        assertEquals("task1", wantedTask1.getTaskName());
        assertEquals("task2", wantedTask2.getTaskName());
        assertEquals(1, wantedTask1.getTaskId());
        assertEquals(2, wantedTask2.getTaskId());
    }

    @Test
    public void createTaskEffortTest() {
        //Arrange
        Company company = new Company();
        RegisterWorkToTaskMapper mapper = new RegisterWorkToTaskMapper();
        RegisterWorkToTaskController controller = new RegisterWorkToTaskController(company, mapper);
        ProjectStore projectStore = company.getProjectStore();
        //Project
        Typology typo = company.getTypologyStore().getTypologyByDescription("Fixed Cost");
        Customer customer = company.getCustomerStore().getCustomerByName("isep");
        BusinessSector sector = company.getBusinessSectorStore().getBusinessSectorByDescription("it");
        Project project1 = company.getProjectStore().createProject("prototype4", "proj4Prototype", customer,
                typo, sector, LocalDate.of(2022, 1, 1), 2, 4000);
        project1.setEndDate(LocalDate.of(2022, 1, 31));
        projectStore.saveNewProject(project1);
        String projectCode1 = project1.getCode(); //"Project_2022_1"
        //Resource
        UserProfile profile = company.getUserProfileStore().getUserProfile("Visitor");
        SystemUser user2 = company.getSystemUserStore().createSystemUser("manuelmartins", "manuelmartins@beaver.com", "tester", "ghi", "ghi", "photo", profile);
        LocalDate startDateMm = LocalDate.of(2022, 1, 1);
        LocalDate endDateMm = LocalDate.of(2022, 1, 31);
        Resource resource1 = project1.getProjectTeam().createResource(user2, startDateMm, endDateMm, 100, 1);
        project1.getProjectTeam().saveResource(resource1);
        //Sprint
        Sprint sprint1 = project1.getSprints().createSprint("Sprint 1", LocalDate.of(2022, 1, 1), 2);
        project1.getSprints().saveSprint(sprint1);
        int id_Sprint1 = sprint1.getIdSprint(); //1
        //UserStory
        UserStory userStory1 = project1.getProductBacklog().createUserStory("US001", 1, "description", 5);
        project1.getProductBacklog().saveUserStory(userStory1);
        int id_UserStory1 = userStory1.getIdUserStory(); //1
        sprint1.getSprintBacklog().saveUserStoryToSprintBacklog(userStory1);
        //Tasks
        String taskDescription = "must be at least 20 characters";
        TaskType taskType = company.getTaskTypeStore().getTypeByDescription("Testing");
        Task task1 = userStory1.getTasks().createTask("task1", taskDescription, 20.0, taskType, resource1);
        Task task2 = userStory1.getTasks().createTask("task2", taskDescription, 10.0, taskType, resource1);
        userStory1.getTasks().saveTask(task1);
        int id_task1 = task1.getIdTask(); //1
        userStory1.getTasks().saveTask(task2);
        int id_task2 = task2.getIdTask(); //2
        //userStorySprintProjectDTO
        UserStorySprintProjectDTO userStorySprintProjectDTO = new UserStorySprintProjectDTO(projectCode1, id_Sprint1, id_UserStory1);

        controller.getTasks(userStorySprintProjectDTO);
        //taskEffort
        TaskIdNameDTO taskIdNameDTO = controller.getTask(id_task2);
        assertEquals(10, task2.getEffortEstimate());
        assertEquals(0, task2.getHoursSpent());
        assertEquals(10, task2.getEffortRemaining());
        assertEquals(0, task2.getExecutionPercentage());
        assertEquals(0, userStory1.getWorkDone());
        //TaskEffortDTO
        Date effortDate = new Date(LocalDate.of(2022, 1, 10));
        TaskEffortDTO taskEffortDTO = new TaskEffortDTO( 4, 30, effortDate, "test", ".pdf");
        //Assert
        assertTrue(controller.createTaskEffort(taskEffortDTO));
        assertEquals(10, task2.getEffortEstimate());
        assertEquals(4.5, task2.getHoursSpent());
        assertEquals(5.5, task2.getEffortRemaining());
        assertEquals(0.45, task2.getExecutionPercentage());
        assertEquals(4.5, userStory1.getWorkDone());
    }
}
