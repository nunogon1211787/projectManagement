package switch2021.project.controller.old;

import static org.mockito.Mockito.mock;

public class UserStoryStoreSortControllerTest {


    //TODO -----> Validar testes CDC

//    @Test
//    @DisplayName("grants a list of US that is sorted by priority. It keeps the done and/or cancelled US on the end\n")
//    public void getSortedListWithSuccess() {
//        Company company = new Company();
//        ProjectsMapper mapper = new ProjectsMapper();
//        ProductBacklogMapper mapperPB = new ProductBacklogMapper();
//        Typology typo = company.getTypologyRepository().findTypologyByDescription("Fixed Cost");
//        Customer customer = company.getCustomerStore().getCustomerByName("Teste");
//        BusinessSector sector = company.getBusinessSectorStore().getBusinessSectorByDescription("sector");
//        Project project = company.getProjectStore().createAndSaveProject("prototype", "test56", customer,
//                typo, sector, LocalDate.now(), 7, 5000);
//        UserProfile userProfile = company.getUserProfileStore().getUserProfile("Visitor");
//        SystemUser newUser = new SystemUser("xyz", "cris@ipp.pt", "des", "Qwerty_1", "Qwerty_1", "photo.png", userProfile.getUserProfileId());
//        LocalDate startDate = LocalDate.of(2021, 12, 31);
//        LocalDate endDate = LocalDate.of(2022, 1, 5);
//        Resource input = new Resource(newUser, startDate, endDate, new CostPerHour(100), new PercentageOfAllocation(.5));
//        ResourceFactory resFac = mock(ResourceFactory.class);
//        ProjectTeam projectTeam = new ProjectTeam(resFac);// Arrange
//        ProjectID projectID = new ProjectID("Project_2022_1");
//
//        project.getUserStoryFactory().createUserStory("Project_" + LocalDate.now().getYear() + "_" + 1,"Project_2022_1_As a PO, i want to test this string","As a PO, i want to test this string", 2, "create user story", 5);
//        project.getUserStoryFactory().createUserStory("Project_" + LocalDate.now().getYear() + "_" + 1,"Project_2022_2_As a PO, i want to test this string","As a PO, i want to test this string", 1, "sort user story", 5);
//        project.getUserStoryFactory().createUserStory("Project_" + LocalDate.now().getYear() + "_" + 1,"Project_2022_3_As a PO, i want to test this string","As a PO, i want to test this string", 3, "backlog sorted", 5);
//        project.getUserStoryFactory().createUserStory("Project_" + LocalDate.now().getYear() + "_" + 1,"Project_2022_4_As a PO, i want to test this string","As a PO, i want to test this string", 5, "show sorted", 5);
//
//        projectTeam.saveResource(input);
//        project.setProjectTeam(projectTeam);
//        // Act
//        ProductBacklogSortController productBacklogSortController = new ProductBacklogSortController(company, mapper, mapperPB);
//        productBacklogSortController.getProjectListByUserEmail("cris@ipp.pt");
//        List<UserStoryDTO> userStoryListDtoList = productBacklogSortController.getUsSortedByPriority("Project_2022_1");
//        // Assert
//        assertEquals(4, userStoryListDtoList.size());
//        assertEquals(1, userStoryListDtoList.get(0).getPriority());
//        assertEquals(2, userStoryListDtoList.get(1).getPriority());
//        assertEquals(3, userStoryListDtoList.get(2).getPriority());
//        assertEquals(5, userStoryListDtoList.get(3).getPriority());
//    }
//
//    @Test
//    @DisplayName("get exception message \"Check priority, cannot be < 0 or superior to 5.“")
//
//    public void getSortedListFailWrongPriority() {
//        // Arrange
//        Company company = new Company();
//        ProjectsMapper mapper = new ProjectsMapper();
//        ProductBacklogMapper mapperPB = new ProductBacklogMapper();
//        Typology typo = company.getTypologyRepository().findTypologyByDescription("Fixed Cost");
//        Customer customer = company.getCustomerStore().getCustomerByName("Teste");
//        BusinessSector sector = company.getBusinessSectorStore().getBusinessSectorByDescription("sector");
//        Project project = company.getProjectStore().createAndSaveProject("prototype", "test56", customer,
//                typo, sector, LocalDate.now(), 7, 5000);
//        UserProfile userProfile = company.getUserProfileStore().getUserProfile("Visitor");
//        SystemUser newUser = new SystemUser("xyz", "cris@ipp.pt", "des", "Qwerty_1", "Qwerty_1", "photo.png", userProfile.getUserProfileId());
//        LocalDate startDate = LocalDate.of(2021, 12, 31);
//        LocalDate endDate = LocalDate.of(2022, 1, 5);
//        Resource input = new Resource(newUser, startDate, endDate, new CostPerHour(100), new PercentageOfAllocation(.5));
//        ResourceFactory resFac = mock(ResourceFactory.class);
//        ProjectTeam projectTeam = new ProjectTeam(resFac);
//        ProjectID projectID = new ProjectID("Project_2022_1");
//        project.getUserStoryFactory().createUserStory("Project_" + LocalDate.now().getYear() + "_" + 1,"Project_2022_1_As a PO, i want to test this string","As a PO, i want to test this string", 2, "create user story", 5);
//        project.getUserStoryFactory().createUserStory("Project_" + LocalDate.now().getYear() + "_" + 1,"Project_2022_2_As a PO, i want to test this string","As a PO, i want to test this string", 1, "sort user story", 5);
//        project.getUserStoryFactory().createUserStory("Project_" + LocalDate.now().getYear() + "_" + 1,"Project_2022_3_As a PO, i want to test this string","As a PO, i want to test this string", 3, "backlog sorted", 5);
//
//        projectTeam.saveResource(input);
//        project.setProjectTeam(projectTeam);
//
//        // Act
//        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
//            ProductBacklogSortController productBacklogSortController = new ProductBacklogSortController(company, mapper, mapperPB);
//            productBacklogSortController.getProjectListByUserEmail("cris@ipp.pt");
//            project.getUserStoryFactory().createUserStory("Project_" + LocalDate.now().getYear() + "_" + 1,"Project_2022_1_As a PO, i want to test this string","As a PO, i want to test this string", 6, "show sorted", 5);
//            productBacklogSortController.getUsSortedByPriority("123testcode");
//        });
//        // Assert
//        assertTrue(exception.getMessage().contains("Check priority, cannot be < 0 or superior to 5"));
//    }

//    @Test
//    @DisplayName("grants a list DTO of US that is sorted by priority. It keeps the done and/or cancelled US on the end\n")
//    public void getSortedListWithSuccessCorrectDTOInfo() {
//        Company company = new Company();
//        ProjectsMapper mapper = new ProjectsMapper();
//        ProductBacklogMapper mapperPB = new ProductBacklogMapper();
//        Typology typo = company.getTypologyRepository().findTypologyByDescription("Fixed Cost");
//        Customer customer = company.getCustomerStore().getCustomerByName("Teste");
//        BusinessSector sector = company.getBusinessSectorStore().getBusinessSectorByDescription("sector");
//        Project project = company.getProjectStore().createAndSaveProject("prototype", "test56", customer,
//                typo, sector, LocalDate.now(), 7, 5000);
//        UserProfile userProfile = company.getUserProfileStore().getUserProfile("Visitor");
//        SystemUser newUser = new SystemUser("xyz", "cris@ipp.pt", "des", "Qwerty_1", "Qwerty_1", "photo.png", userProfile.getUserProfileId());
//        LocalDate startDate = LocalDate.of(2021, 12, 31);
//        LocalDate endDate = LocalDate.of(2022, 1, 5);
//        Resource input = new Resource(newUser, startDate, endDate, new CostPerHour(100), new PercentageOfAllocation(.5));
//        ResourceFactory resFac = mock(ResourceFactory.class);
//        ProjectTeam projectTeam = new ProjectTeam(resFac);// Arrange
//        ProjectID projectID = new ProjectID("Project_2022_1");
//
//       UserStory us= project.getUserStoryFactory().createUserStory("Project_" + LocalDate.now().getYear() + "_" + 1,"Project_2022_1_As a PO, i want to test this string","As a PO, i want to test this string", 2, "create user story", 5);
//        project.getUserStoryFactory().createUserStory("Project_" + LocalDate.now().getYear() + "_" + 1,"Project_2022_2_As a PO, i want to test this string","As a PO, i want to test this string", 1, "sort user story", 5);
//        project.getUserStoryFactory().createUserStory("Project_" + LocalDate.now().getYear() + "_" + 1,"Project_2022_3_As a PO, i want to test this string","As a PO, i want to test this string", 3, "backlog sorted", 5);
//        project.getUserStoryFactory().createUserStory("Project_" + LocalDate.now().getYear() + "_" + 1,"Project_2022_4_As a PO, i want to test this string","As a PO, i want to test this string", 5, "show sorted", 5);
//        projectTeam.saveResource(input);
//        project.setProjectTeam(projectTeam);
//        project.getUserStoryStore().save(us);
//        // Act
//        ProductBacklogSortController productBacklogSortController = new ProductBacklogSortController(company, mapper, mapperPB);
//        productBacklogSortController.getProjectListByUserEmail("cris@ipp.pt");
//        List<UserStoryDTO> userStoryListDtoList = productBacklogSortController.getUsSortedByPriority("Project_2022_1");
//        // Assert
//        assertEquals(project.getUserStoryStore().getUserStoryList().get(0).getTitle().getTitleUs(), userStoryListDtoList.get(0).getTitle());
//        assertEquals(project.getUserStoryStore().getUserStoryList().get(0).getPriority().getPriorityUs(), userStoryListDtoList.get(0).getPriority());
//        assertEquals(project.getUserStoryStore().getUserStoryList().get(0).getDescription().getText(), userStoryListDtoList.get(0).getDescription());
//        assertEquals(project.getUserStoryStore().getUserStoryList().get(1).getTitle().getTitleUs(), userStoryListDtoList.get(1).getTitle());
//        assertEquals(project.getUserStoryStore().getUserStoryList().get(1).getPriority().getPriorityUs(), userStoryListDtoList.get(1).getPriority());
//        assertEquals(project.getUserStoryStore().getUserStoryList().get(1).getDescription().getText(), userStoryListDtoList.get(1).getDescription());
//        assertEquals(project.getUserStoryStore().getUserStoryList().get(2).getTitle().getTitleUs(), userStoryListDtoList.get(2).getTitle());
//        assertEquals(project.getUserStoryStore().getUserStoryList().get(2).getPriority().getPriorityUs(), userStoryListDtoList.get(2).getPriority());
//        assertEquals(project.getUserStoryStore().getUserStoryList().get(2).getDescription().getText(), userStoryListDtoList.get(2).getDescription());
//        assertEquals(project.getUserStoryStore().getUserStoryList().get(3).getTitle().getTitleUs(), userStoryListDtoList.get(3).getTitle());
//        assertEquals(project.getUserStoryStore().getUserStoryList().get(3).getPriority().getPriorityUs(), userStoryListDtoList.get(3).getPriority());
//        assertEquals(project.getUserStoryStore().getUserStoryList().get(3).getDescription().getText(), userStoryListDtoList.get(3).getDescription());
//    }
}