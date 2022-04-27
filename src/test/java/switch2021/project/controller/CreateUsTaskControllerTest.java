package switch2021.project.controller;

//TODO ver task deixou de haver store de task na US
class CreateUsTaskControllerTest {

//    @Test
//    void getTaskTypesValid() {
//        Company company = new Company();
//        TaskMapper mapper = new TaskMapper();
//        CreateUsTaskController controller = new CreateUsTaskController(company, mapper);
//        List<String> result = controller.getTaskTypes();
//        List<String> test = TaskTypeEnum.getTaskTypesDescriptionEnums();
//        //Assert
//        assertEquals(6, result.size());
//        assertEquals(result, test);
//    }
//
//    @Test
//    void getUsTasksValid() {
//        //Arrange
//        Company company = new Company();
//        TaskMapper mapper = new TaskMapper();
//        CreateUsTaskController controller = new CreateUsTaskController(company, mapper);
//
//        //Create a project
//        Typology typo = company.getTypologyStore().getTypologyByDescription("Fixed Cost");
//        Customer customer = company.getCustomerStore().getCustomerByName("Teste");
//        BusinessSector sector = company.getBusinessSectorStore().getBusinessSectorByDescription("sector");
//        Project project = company.getProjectStore().createProject("prototype", "test1234", customer,
//                typo, sector, LocalDate.now(), 7, 5000);
//        company.getProjectStore().saveNewProject(project);
//
//        //Create a sprint
//        Sprint sprint = project.getSprintList().createSprint("Sprint 1", LocalDate.of(2022, 2, 1), 2);
//        project.getSprintList().saveSprint(sprint);
//
//        //Create a UserStory
//        String userStoryId1 = "Project_2022_1_As a PO, i want to test this string";
//        UserStory userStory = new UserStory(userStoryId1,"As a PO, i want to test this string", 2, "Fazer tal", 5);
//        sprint.saveUsInScrumBoard(userStory);
//
//
//        //Create tasks
//        UserProfile profile = company.getUserProfileStore().getUserProfile("Visitor");
//        SystemUser user = new SystemUser("user test", "test@test.pt", "test", "Qwerty_1", "Qwerty_1", "photo.png", profile);
//        Resource resource = new Resource(user, LocalDate.of(2022, 2, 1), LocalDate.of(2023, 2, 1), new CostPerHour(100), new PercentageOfAllocation(1));
//        TaskTypeEnum type = TaskTypeEnum.Design;
//        Task newTask = new Task("test", "test test test tests", 10, type, resource);
//        Task newTask2 = new Task("testdois", "test2 test2 test2 test2", 10, type, resource);
////        userStory.getTasks().saveTask(newTask);
////        userStory.getTasks().saveTask(newTask2);
//
//        //Get us task list
//        List<String> tasksNames = controller.getUsTasks(project.getProjectCode().getCode(), sprint.getIdSprint());
//
//        //Asserts
//        assertEquals(2, tasksNames.size());
//        assertEquals("test", tasksNames.get(0));
//        assertEquals("testdois", tasksNames.get(1));
//    }
//
//    @Test
//    void getUsTasksEmpty() {
//        //Arrange
//        Company company = new Company();
//        TaskMapper mapper = new TaskMapper();
//        CreateUsTaskController controller = new CreateUsTaskController(company, mapper);
//
//        //Create a project
//        Typology typo = company.getTypologyStore().getTypologyByDescription("Fixed Cost");
//        Customer customer = company.getCustomerStore().getCustomerByName("Teste");
//        BusinessSector sector = company.getBusinessSectorStore().getBusinessSectorByDescription("sector");
//        Project project = company.getProjectStore().createProject("prototype", "test1234", customer,
//                typo, sector, LocalDate.now(), 7, 5000);
//        company.getProjectStore().saveNewProject(project);
//
//        //Create a sprint
//        Sprint sprint = project.getSprintList().createSprint("Sprint 1", LocalDate.of(2022, 2, 1), 2);
//        project.getSprintList().saveSprint(sprint);
//
//        //Create a UserStory
//        String userStoryId1 = "Project_2022_1_As a PO, i want to test this string";
//        UserStory userStory = new UserStory(userStoryId1,"As a PO, i want to test this string", 2, "Fazer tal", 5);
//        sprint.saveUsInScrumBoard(userStory);
//
//        //Get us task list
//        List<String> tasksNames = controller.getUsTasks(project.getProjectCode().getCode(), sprint.getIdSprint());
//
//        //Asserts
//        assertEquals(0, tasksNames.size());
//    }
//
//    @Test
//    void getCurrentProjectTeamValid() {
//
//        //Arrange
//        Company company = new Company();
//        TaskMapper mapper = new TaskMapper();
//        CreateUsTaskController controller = new CreateUsTaskController(company, mapper);
//
//        //Create a project
//        Typology typo = company.getTypologyStore().getTypologyByDescription("Fixed Cost");
//        Customer customer = company.getCustomerStore().getCustomerByName("Teste");
//        BusinessSector sector = company.getBusinessSectorStore().getBusinessSectorByDescription("sector");
//        Project project = company.getProjectStore().createProject("prototype", "test1234", customer,
//                typo, sector, LocalDate.now(), 7, 5000);
//        company.getProjectStore().saveNewProject(project);
//
//        //Create a sprint
//        Sprint sprint = project.getSprintList().createSprint("Sprint 1", LocalDate.of(2022, 2, 1), 2);
//        project.getSprintList().saveSprint(sprint);
//
//        //Create a UserStory
//        String userStoryId1 = "Project_2022_1_As a PO, i want to test this string";
//        UserStory userStory = new UserStory(userStoryId1,"As a PO, i want to test this string", 2, "Fazer tal", 5);
//        sprint.saveUsInScrumBoard(userStory);
//
//        //Create project team
//        UserProfile profile = company.getUserProfileStore().getUserProfile("Visitor");
//        SystemUser user1 = new SystemUser("user test", "test@test.pt", "test", "Qwerty_1", "Qwerty_1", "photo.png", profile);
//        Resource res1 = new Resource(user1, LocalDate.of(2022, 2, 1), LocalDate.of(2023, 2, 1), new CostPerHour(100), new PercentageOfAllocation(1));
//        SystemUser user2 = new SystemUser("user test dois", "test2@test.pt", "test", "Qwerty_1", "Qwerty_1", "photo.png", profile);
//        Resource res2 = new Resource(user2, LocalDate.of(2022, 2, 1), LocalDate.of(2023, 2, 1), new CostPerHour(100), new PercentageOfAllocation(1));
//        SystemUser user3 = new SystemUser("user test tres", "test@test.pt", "test", "Qwerty_1", "Qwerty_1", "photo.png", profile);
//        Resource res3 = new Resource(user3, LocalDate.of(2022, 2, 1), LocalDate.of(2023, 2, 1), new CostPerHour(100), new PercentageOfAllocation(1));
//        project.getProjectTeam().saveResource(res1);
//        project.getProjectTeam().saveResource(res2);
//        project.getProjectTeam().saveResource(res3);
//
//        //Get project team names
//        controller.getUsTasks(project.getProjectCode().getCode(), sprint.getIdSprint());
//        List<String> test = controller.getCurrentProjectTeam();
//
//        //Asserts
//        assertEquals(3, test.size());
//        assertEquals("user test", test.get(0));
//        assertEquals("user test dois", test.get(1));
//        assertEquals("user test tres", test.get(2));
//    }
//
//    @Test
//    void getCurrentProjectTeamEmpty() {
//
//        //Arrange
//        Company company = new Company();
//        TaskMapper mapper = new TaskMapper();
//        CreateUsTaskController controller = new CreateUsTaskController(company, mapper);
//
//        //Create a project
//        Typology typo = company.getTypologyStore().getTypologyByDescription("Fixed Cost");
//        Customer customer = company.getCustomerStore().getCustomerByName("Teste");
//        BusinessSector sector = company.getBusinessSectorStore().getBusinessSectorByDescription("sector");
//        Project project = company.getProjectStore().createProject("prototype", "test1234", customer,
//                typo, sector, LocalDate.now(), 7, 5000);
//        company.getProjectStore().saveNewProject(project);
//
//        //Create a sprint
//        Sprint sprint = project.getSprintList().createSprint("Sprint 1", LocalDate.of(2022, 2, 1), 2);
//        project.getSprintList().saveSprint(sprint);
//
//        //Create a UserStory
//        String userStoryId1 = "Project_2022_1_As a PO, i want to test this string";
//        UserStory userStory = new UserStory(userStoryId1,"As a PO, i want to test this string", 2, "Fazer tal", 5);
//        sprint.saveUsInScrumBoard(userStory);
//
//        //Get project team names
//        controller.getUsTasks(project.getProjectCode().getCode(), sprint.getIdSprint());
//        List<String> test = controller.getCurrentProjectTeam();
//
//        //Asserts
//        assertEquals(0, test.size());
//    }
//
//    @Test
//    void createUsTaskValid() {
//        //Arrange
//        Company company = new Company();
//        TaskMapper mapper = new TaskMapper();
//        CreateUsTaskController controller = new CreateUsTaskController(company, mapper);
//
//        //Create a project
//        Typology typo = company.getTypologyStore().getTypologyByDescription("Fixed Cost");
//        Customer customer = company.getCustomerStore().getCustomerByName("Teste");
//        BusinessSector sector = company.getBusinessSectorStore().getBusinessSectorByDescription("sector");
//        Project project = company.getProjectStore().createProject("prototype", "test1234", customer,
//                typo, sector, LocalDate.now(), 7, 5000);
//        company.getProjectStore().saveNewProject(project);
//
//        //Create a sprint
//        Sprint sprint = project.getSprintList().createSprint("Sprint 1", LocalDate.of(2022, 2, 1), 2);
//        project.getSprintList().saveSprint(sprint);
//
//        //Create a UserStory
//        String userStoryId1 = "Project_2022_1_As a PO, i want to test this string";
//        UserStory userStory = new UserStory(userStoryId1 ,"As a PO, i want to test this string", 2, "Fazer tal", 5);
//        sprint.saveUsInScrumBoard(userStory);
//
//        //Create project team
//        UserProfile profile = company.getUserProfileStore().getUserProfile("Visitor");
//        SystemUser user1 = new SystemUser("user test", "test@test.pt", "test", "Qwerty_1", "Qwerty_1", "photo.png", profile);
//        Resource res1 = new Resource(user1, LocalDate.of(2022, 2, 1), LocalDate.of(2023, 2, 1), new CostPerHour(100), new PercentageOfAllocation(1));
//        SystemUser user2 = new SystemUser("user test dois", "test2@test.pt", "test", "Qwerty_1", "Qwerty_1", "photo.png", profile);
//        Resource res2 = new Resource(user2, LocalDate.of(2022, 2, 1), LocalDate.of(2023, 2, 1), new CostPerHour(100), new PercentageOfAllocation(1));
//        SystemUser user3 = new SystemUser("user test tres", "test@test.pt", "test", "Qwerty_1", "Qwerty_1", "photo.png", profile);
//        Resource res3 = new Resource(user3, LocalDate.of(2022, 2, 1), LocalDate.of(2023, 2, 1), new CostPerHour(100), new PercentageOfAllocation(1));
//        project.getProjectTeam().saveResource(res1);
//        project.getProjectTeam().saveResource(res2);
//        project.getProjectTeam().saveResource(res3);
//
//        //Create a new us Task
//        controller.getUsTasks(project.getProjectCode().getCode(), sprint.getIdSprint());
//        CreateTaskDTO dto = new CreateTaskDTO("newTask", "newTask to a controller test", 10, "Design", "user test tres");
//
//
//        //Asserts
//        assertTrue(controller.createUsTask(dto));
////        assertEquals(1, userStory.getTasks().getTasksNames().size());
////        assertEquals("newTask", userStory.getTasks().getTasksNames().get(0));
//    }
//
//    @Test
//    void createUsTaskValid2() {
//        //Arrange
//        Company company = new Company();
//        TaskMapper mapper = new TaskMapper();
//        CreateUsTaskController controller = new CreateUsTaskController(company, mapper);
//
//        //Create a project
//        Typology typo = company.getTypologyStore().getTypologyByDescription("Fixed Cost");
//        Customer customer = company.getCustomerStore().getCustomerByName("Teste");
//        BusinessSector sector = company.getBusinessSectorStore().getBusinessSectorByDescription("sector");
//        Project project = company.getProjectStore().createProject("prototype", "test1234", customer,
//                typo, sector, LocalDate.now(), 7, 5000);
//        company.getProjectStore().saveNewProject(project);
//
//        //Create a sprint
//        Sprint sprint = project.getSprintList().createSprint("Sprint 1", LocalDate.of(2022, 2, 1), 2);
//        project.getSprintList().saveSprint(sprint);
//
//        //Create a UserStory
//        String userStoryId1 = "Project_2022_1_As a PO, i want to test this string";
//        UserStory userStory = new UserStory(userStoryId1,"As a PO, i want to test this string", 2, "Fazer tal", 5);
//        sprint.saveUsInScrumBoard(userStory);
//
//        //Create project team
//        UserProfile profile = company.getUserProfileStore().getUserProfile("Visitor");
//        SystemUser user1 = new SystemUser("user test", "test@test.pt", "test", "Qwerty_1", "Qwerty_1", "photo.png", profile);
//        Resource res1 = new Resource(user1, LocalDate.of(2022, 2, 1), LocalDate.of(2023, 2, 1), new CostPerHour(100), new PercentageOfAllocation(1));
//        SystemUser user2 = new SystemUser("user test dois", "test2@test.pt", "test", "Qwerty_1", "Qwerty_1", "photo.png", profile);
//        Resource res2 = new Resource(user2, LocalDate.of(2022, 2, 1), LocalDate.of(2023, 2, 1), new CostPerHour(100), new PercentageOfAllocation(1));
//        SystemUser user3 = new SystemUser("user test tres", "test@test.pt", "test", "Qwerty_1", "Qwerty_1", "photo.png", profile);
//        Resource res3 = new Resource(user3, LocalDate.of(2022, 2, 1), LocalDate.of(2023, 2, 1), new CostPerHour(100), new PercentageOfAllocation(1));
//        project.getProjectTeam().saveResource(res1);
//        project.getProjectTeam().saveResource(res2);
//        project.getProjectTeam().saveResource(res3);
//
//        //Create tasks
//        TaskTypeEnum type = TaskTypeEnum.Design;
//        Task newTask = new Task("test", "test test test tests", 10, type, res1);
//        Task newTask2 = new Task("testdois", "test2 test2 test2 test2", 10, type, res2);
////        userStory.getTasks().saveTask(newTask);
////        userStory.getTasks().saveTask(newTask2);
//
//        //Create a new us Task
////        controller.getUsTasks(project.getProjectCode().getCode(), sprint.getIdSprint(), userStory.getUserStoryId());
//        CreateTaskDTO dto = new CreateTaskDTO("newTask", "newTask to a controller test", 10, "Design", "user test tres");
//
//
//        //Asserts
//        assertTrue(controller.createUsTask(dto));
////        assertEquals(3, userStory.getTasks().getTasksNames().size());
////        assertEquals("newTask", userStory.getTasks().getTasksNames().get(2));
//    }
//
//    @Test
//    void createUsTaskValidWithPrecedence() {
//        //Arrange
//        Company company = new Company();
//        TaskMapper mapper = new TaskMapper();
//        CreateUsTaskController controller = new CreateUsTaskController(company, mapper);
//
//        //Create a project
//        Typology typo = company.getTypologyStore().getTypologyByDescription("Fixed Cost");
//        Customer customer = company.getCustomerStore().getCustomerByName("Teste");
//        BusinessSector sector = company.getBusinessSectorStore().getBusinessSectorByDescription("sector");
//        Project project = company.getProjectStore().createProject("prototype", "test1234", customer,
//                typo, sector, LocalDate.now(), 7, 5000);
//        company.getProjectStore().saveNewProject(project);
//
//        //Create a sprint
//        Sprint sprint = project.getSprintList().createSprint("Sprint 1", LocalDate.of(2022, 2, 1), 2);
//        project.getSprintList().saveSprint(sprint);
//
//        //Create a UserStory
//        String userStoryId1 = "Project_2022_1_As a PO, i want to test this string";
//        UserStory userStory = new UserStory(userStoryId1,"As a PO, i want to test this string", 2, "Fazer tal", 5);
//        sprint.saveUsInScrumBoard(userStory);
//
//        //Create project team
//        UserProfile profile = company.getUserProfileStore().getUserProfile("Visitor");
//        SystemUser user1 = new SystemUser("user test", "test@test.pt", "test", "Qwerty_1", "Qwerty_1", "photo.png", profile);
//        Resource res1 = new Resource(user1, LocalDate.of(2022, 2, 1), LocalDate.of(2023, 2, 1), new CostPerHour(100), new PercentageOfAllocation(1));
//        SystemUser user2 = new SystemUser("user test dois", "test2@test.pt", "test", "Qwerty_1", "Qwerty_1", "photo.png", profile);
//        Resource res2 = new Resource(user2, LocalDate.of(2022, 2, 1), LocalDate.of(2023, 2, 1), new CostPerHour(100), new PercentageOfAllocation(1));
//        SystemUser user3 = new SystemUser("user test tres", "test@test.pt", "test", "Qwerty_1", "Qwerty_1", "photo.png", profile);
//        Resource res3 = new Resource(user3, LocalDate.of(2022, 2, 1), LocalDate.of(2023, 2, 1), new CostPerHour(100), new PercentageOfAllocation(1));
//        project.getProjectTeam().saveResource(res1);
//        project.getProjectTeam().saveResource(res2);
//        project.getProjectTeam().saveResource(res3);
//
//        //Create tasks
//        TaskTypeEnum type = TaskTypeEnum.Design;
//        Task newTask = new Task("test", "test test test tests", 10, type, res1);
//        Task newTask2 = new Task("testdois", "test2 test2 test2 test2", 10, type, res2);
////        userStory.getTasks().saveTask(newTask);
////        userStory.getTasks().saveTask(newTask2);
//        List<String> precedenceList = new ArrayList<>();
//        precedenceList.add(newTask.getName().getNameF());
//        precedenceList.add(newTask2.getName().getNameF());
//
//        //Create a new us Task
////        controller.getUsTasks(project.getProjectCode().getCode(), sprint.getIdSprint());
//        CreateTaskDTO dto = new CreateTaskDTO("newTask", "newTask to a controller test", 10, "Design", "user test tres", precedenceList);
//
//
//        //Asserts
//        assertTrue(controller.createUsTask(dto));
////        assertEquals(2, userStory.getTasks().getTaskById(3).getPrecedenceList().size());
////        assertEquals("test", userStory.getTasks().getTaskById(3).getPrecedenceList().get(0));
//    }
//
//
}