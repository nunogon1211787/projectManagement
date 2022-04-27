package switch2021.project.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

//TODO ver testes - estao a ir buscar dados task a US e a SPRINT
public class RegisterWorkToTaskControllerTest {
//    @Test
//    public void getTasksTest() {
//        //Arrange
//        Company company = new Company();
//        RegisterWorkToTaskMapper mapper = new RegisterWorkToTaskMapper();
//        RegisterWorkToTaskController controller = new RegisterWorkToTaskController(company, mapper);
//        ProjectStore projectStore = company.getProjectStore();
//        //Project
//        Typology typo = company.getTypologyStore().getTypologyByDescription("Fixed Cost");
//        Customer customer = company.getCustomerStore().getCustomerByName("isep");
//        BusinessSector sector = company.getBusinessSectorStore().getBusinessSectorByDescription("it");
//        Project project1 = company.getProjectStore().createProject("prototype4", "proj4Prototype", customer,
//                typo, sector, LocalDate.of(2022, 1, 1), 2, 4000);
//        project1.setEndDate(LocalDate.of(2022, 1, 31));
//        projectStore.saveNewProject(project1);
//        String projectCode1 = project1.getProjectCode().getCode(); //"Project_2022_1"
//        //Resource
//        UserProfile profile = company.getUserProfileStore().getUserProfile("Visitor");
//        SystemUser user2 = new SystemUser("manuelmartins", "manuelmartins@beaver.com", "tester", "Qwerty_1", "Qwerty_1", "photo.png", profile.getUserProfileId());
//        LocalDate startDateMm = LocalDate.of(2022, 1, 1);
//        LocalDate endDateMm = LocalDate.of(2022, 1, 31);
//        Resource resource1 = project1.getProjectTeam().createResource(user2, startDateMm, endDateMm, 100, 1);
//        project1.getProjectTeam().saveResource(resource1);
//        //Sprint
//        Sprint sprint1 = project1.getSprintList().createSprint("Sprint 1", 2);
//        project1.getSprintList().saveSprint(sprint1);
//        int id_Sprint1 = sprint1.getIdSprint(); //1
//        //UserStory
//        project1.getUserStoryStore().createAndSaveUserStory("As a PO, i want to test this string", 1, "description", 5);
//        int id_UserStory1 = project1.getUserStoryStore().getUserStoryList().get(0).getIdUserStory(); //1
//
//        sprint1.saveUsInScrumBoard(project1.getUserStoryStore().getUserStoryList().get(0));
//        //Tasks
//        String taskDescription = "must be at least 20 characters";
//        TaskTypeEnum taskType = TaskTypeEnum.Design;
//        Task task1 = project1.getUserStoryStore().getUserStoryList().get(0).getTasks().createTask("taskum", taskDescription, 20.0, taskType, resource1);
//        Task task2 = project1.getUserStoryStore().getUserStoryList().get(0).getTasks().createTask("taskdois", taskDescription, 10.0, taskType, resource1);
//        project1.getUserStoryStore().getUserStoryList().get(0).getTasks().saveTask(task1);
//        project1.getUserStoryStore().getUserStoryList().get(0).getTasks().saveTask(task2);
//        //userStorySprintProjectDTO
//        UserStorySprintProjectDTO userStorySprintProjectDTO = new UserStorySprintProjectDTO(projectCode1, id_Sprint1, id_UserStory1);
//        //Act
//        List<TaskIdNameDTO> taskIdNameDTOList = controller.getTasks(userStorySprintProjectDTO);
//        //Assert
//        assertEquals(2, taskIdNameDTOList.size());
//        assertEquals(1, taskIdNameDTOList.get(0).getTaskId());
//        assertEquals(2, taskIdNameDTOList.get(1).getTaskId());
//        assertEquals("taskum", taskIdNameDTOList.get(0).getTaskName());
//        assertEquals("taskdois", taskIdNameDTOList.get(1).getTaskName());
//    }

//    @Test
//    public void getTaskTest() {
//        //Arrange
//        Company company = new Company();
//        RegisterWorkToTaskMapper mapper = new RegisterWorkToTaskMapper();
//        RegisterWorkToTaskController controller = new RegisterWorkToTaskController(company, mapper);
//        ProjectStore projectStore = company.getProjectStore();
//        //Project
//        Typology typo = company.getTypologyStore().getTypologyByDescription("Fixed Cost");
//        Customer customer = company.getCustomerStore().getCustomerByName("isep");
//        BusinessSector sector = company.getBusinessSectorStore().getBusinessSectorByDescription("it");
//        Project project1 = company.getProjectStore().createProject("prototype4", "proj4Prototype", customer,
//                typo, sector, LocalDate.of(2022, 1, 1), 2, 4000);
//        project1.setEndDate(LocalDate.of(2022, 1, 31));
//        projectStore.saveNewProject(project1);
//        String projectCode1 = project1.getProjectCode().getCode(); //"Project_2022_1"
//        //Resource
//        UserProfile profile = company.getUserProfileStore().getUserProfile("Visitor");
//        SystemUser user2 = new SystemUser("manuelmartins", "manuelmartins@beaver.com", "tester", "Qwerty_1", "Qwerty_1", "photo.png", profile.getUserProfileId());
//        LocalDate startDateMm = LocalDate.of(2022, 1, 1);
//        LocalDate endDateMm = LocalDate.of(2022, 1, 31);
//        Resource resource1 = project1.getProjectTeam().createResource(user2, startDateMm, endDateMm, 100, 1);
//        project1.getProjectTeam().saveResource(resource1);
//        //Sprint
//        Sprint sprint1 = project1.getSprintList().createSprint("Sprint 1", 2);
//        project1.getSprintList().saveSprint(sprint1);
//        int id_Sprint1 = sprint1.getIdSprint(); //1
//        //UserStory
//        project1.getUserStoryStore().createAndSaveUserStory("As a PO, i want to test this string", 1, "description", 5);
//        int id_UserStory1 = project1.getUserStoryStore().getUserStoryList().get(0).getIdUserStory(); //1
//        sprint1.saveUsInScrumBoard(project1.getUserStoryStore().getUserStoryList().get(0));
//        //Tasks
//        String taskDescription = "must be at least 20 characters";
//        TaskTypeEnum taskType = TaskTypeEnum.Design;
//        Task task1 = project1.getUserStoryStore().getUserStoryList().get(0).getTasks().createTask("taskum", taskDescription, 20.0, taskType, resource1);
//        Task task2 = project1.getUserStoryStore().getUserStoryList().get(0).getTasks().createTask("taskdois", taskDescription, 10.0, taskType, resource1);
//        project1.getUserStoryStore().getUserStoryList().get(0).getTasks().saveTask(task1);
//        int id_task1 = task1.getIdTask(); //1
//        project1.getUserStoryStore().getUserStoryList().get(0).getTasks().saveTask(task2);
//        int id_task2 = task2.getIdTask(); //2
//        //userStorySprintProjectDTO
//        UserStorySprintProjectDTO userStorySprintProjectDTO = new UserStorySprintProjectDTO(projectCode1, id_Sprint1, id_UserStory1);
//        //Act
//        controller.getTasks(userStorySprintProjectDTO);
//        TaskIdNameDTO wantedTask1 = controller.getTask(id_task1);
//        TaskIdNameDTO wantedTask2 = controller.getTask(id_task2);
//        //Assert
//        assertEquals("taskum", wantedTask1.getTaskName());
//        assertEquals("taskdois", wantedTask2.getTaskName());
//        assertEquals(1, wantedTask1.getTaskId());
//        assertEquals(2, wantedTask2.getTaskId());
//    }

//    @Test
//    public void createTaskEffortTest() {
//        //Arrange
//        Company company = new Company();
//        RegisterWorkToTaskMapper mapper = new RegisterWorkToTaskMapper();
//        RegisterWorkToTaskController controller = new RegisterWorkToTaskController(company, mapper);
//        ProjectStore projectStore = company.getProjectStore();
//        //Project
//        Typology typo = company.getTypologyStore().getTypologyByDescription("Fixed Cost");
//        Customer customer = company.getCustomerStore().getCustomerByName("isep");
//        BusinessSector sector = company.getBusinessSectorStore().getBusinessSectorByDescription("it");
//        Project project1 = company.getProjectStore().createProject("prototype4", "proj4Prototype", customer,
//                typo, sector, LocalDate.of(2022, 1, 1), 2, 4000);
//        project1.setEndDate(LocalDate.of(2022, 1, 31));
//        projectStore.saveNewProject(project1);
//        String projectCode1 = project1.getProjectCode().getCode(); //"Project_2022_1"
//        //Resource
//        UserProfile profile = company.getUserProfileStore().getUserProfile("Visitor");
//        SystemUser user2 = new SystemUser("manuelmartins", "manuelmartins@beaver.com", "tester", "Qwerty_1", "Qwerty_1", "photo.png", profile.getUserProfileId());
//        LocalDate startDateMm = LocalDate.of(2022, 1, 1);
//        LocalDate endDateMm = LocalDate.of(2022, 1, 31);
//        Resource resource1 = project1.getProjectTeam().createResource(user2, startDateMm, endDateMm, 100, 1);
//        project1.getProjectTeam().saveResource(resource1);
//        //Sprint
//        Sprint sprint1 = project1.getSprintList().createSprint("Sprint 1", 2);
//        project1.getSprintList().saveSprint(sprint1);
//        int id_Sprint1 = sprint1.getIdSprint(); //1
//        //UserStory
//        project1.getUserStoryStore().createAndSaveUserStory("As a PO, i want to test this string", 1, "description", 5);
//
//        int id_UserStory1 = project1.getUserStoryStore().getUserStoryList().get(0).getIdUserStory(); //1
//        sprint1.saveUsInScrumBoard(project1.getUserStoryStore().getUserStoryList().get(0));
//        //Tasks
//        String taskDescription = "must be at least 20 characters";
//        TaskTypeEnum taskType = TaskTypeEnum.Design;
//        Task task1 = project1.getUserStoryStore().getUserStoryList().get(0).getTasks().createTask("taskum", taskDescription, 20.0, taskType, resource1);
//        Task task2 = project1.getUserStoryStore().getUserStoryList().get(0).getTasks().createTask("taskdois", taskDescription, 10.0, taskType, resource1);
//        project1.getUserStoryStore().getUserStoryList().get(0).getTasks().saveTask(task1);
//        project1.getUserStoryStore().getUserStoryList().get(0).getTasks().saveTask(task2);
//        //userStorySprintProjectDTO
//        UserStorySprintProjectDTO userStorySprintProjectDTO = new UserStorySprintProjectDTO(projectCode1, id_Sprint1, id_UserStory1);
//
//        controller.getTasks(userStorySprintProjectDTO);
//        //taskEffort
//        assertEquals(10, task2.getEffortEstimate());
//        assertEquals(0, task2.getHoursSpent());
//        assertEquals(10, task2.getEffortRemaining());
//        assertEquals(0, task2.getExecutionPercentage());
//
//        //TaskEffortDTO
//        Date effortDate = new Date(LocalDate.of(2022, 1, 10));
//        TaskEffortDTO taskEffortDTO = new TaskEffortDTO(4, 30, effortDate, "test", ".pdf");
//        TaskEffortDTO taskEffortDTO2 = new TaskEffortDTO(4, 30, effortDate, "", "");
//        //Act
//        controller.getTask(2);
//        //Assert
//
//        assertEquals(10, task2.getEffortEstimate());
//        assertEquals(0, task2.getHoursSpent());
//        assertEquals(10.0, task2.getEffortRemaining());
//        assertEquals(0.0, task2.getExecutionPercentage());
//
//        assertEquals("test", taskEffortDTO.getComment());
//        assertEquals(".pdf", taskEffortDTO.getAttachment());
//        assertEquals("", taskEffortDTO2.getComment());
//        assertEquals("", taskEffortDTO2.getAttachment());
//    }
}
