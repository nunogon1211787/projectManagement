package switch2021.project.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class UserStoryTest {
    Company company;
    private UserStory userStory;
    private UserStory userStory2;
    private Sprint sprint;
    private UserStory userStoryToRefine;
    ProductBacklog productBacklog;
    private Project proj;

    @BeforeEach
    public void ini() {
        company = new Company();
        LocalDate date = LocalDate.of(2021, 12, 12);
        company.getBusinessSectorStore().addBusinessSector(company.getBusinessSectorStore().createBusinessSector("sector"));
        company.getCustomerStore().saveNewCustomer(company.getCustomerStore().createCustomer("Teste", "Teste"));
        Typology typo = company.getTypologyStore().getTypology("Fixed Cost");
        Customer customer = company.getCustomerStore().getCustomerByName("Teste");
        BusinessSector sector = company.getBusinessSectorStore().getBusinessSectorByDescription("sector");
        proj = company.getProjectStore().createProject("prototype", "test56", customer,
                typo, sector, date, 7, 5000);
        company.getProjectStore().saveNewProject(proj);
        productBacklog = proj.getProductBacklog();
        userStoryToRefine = productBacklog.createUserStory("US001", 4, "123testtest", 5);
        productBacklog.saveUserStory(userStoryToRefine);
    }


    @Test
    void setPriorityTest() {
        Sprint sprint = new Sprint("Super", LocalDate.of(2022, 3, 1));
        userStory = new UserStory("US001", 2, "Fazer tal", 5);
        sprint.getSprintBacklog().saveUserStoryToSprintBacklog(userStory);
        userStory.setPriority(4);
        assertEquals(userStory.getPriority(), 4);
    }

    @Test
    void setPriorityTestInvalid() {
        Sprint sprint = new Sprint("Super", LocalDate.of(2022, 3, 1));
        userStory = new UserStory("US001", 2, "Fazer tal", 5);
        sprint.getSprintBacklog().saveUserStoryToSprintBacklog(userStory);
        userStory.setPriority(6);
        assertEquals(userStory.getPriority(), 2);
    }

    @Test
    void hasCodeTest() {

        ProductBacklog productBacklog = new ProductBacklog();
        Sprint sprint = new Sprint("Super", LocalDate.of(2022, 3, 1));
        userStory = new UserStory("US001", 2, "Fazer tal", 5);
        productBacklog.saveUserStory(userStory);

        boolean expected = userStory.hasCode(userStory.getIdUserStory());
        assertTrue(expected);
    }

    @Test
    void hasCodeTest2() {

        ProductBacklog productBacklog = new ProductBacklog();
        Sprint sprint = new Sprint("Super", LocalDate.of(2022, 3, 1));
        userStory = new UserStory("US001", 2, "Fazer tal", 5);
        productBacklog.saveUserStory(userStory);

        boolean expected = userStory.hasCode(4);
        assertFalse(expected);
    }

    @Test
    void setDescriptionTest() {
        Sprint sprint = new Sprint("Super", LocalDate.of(2022, 3, 1));
        userStory = new UserStory("US001", 2, "Fazer tal", 5);
        sprint.getSprintBacklog().saveUserStoryToSprintBacklog(userStory);
        userStory.setDescription("Fazer coiso");
        assertEquals(userStory.getDescription(), "Fazer coiso");
    }

    @Test
    void setParentUserStoryTest() {
        Sprint sprint = new Sprint("Super", LocalDate.of(2022, 3, 1));
        userStory = new UserStory("US001", 2, "Fazer tal", 5);
        sprint.getSprintBacklog().saveUserStoryToSprintBacklog(userStory);
        userStory.setParentUserStory(userStoryToRefine);
        assertEquals(userStoryToRefine, sprint.getSprintBacklog().getUserStory(userStory.getIdUserStory()).getParentUserStory());
    }


    @Test
    void setUserStoryStatusBooleanTest() {

        Sprint sprint = new Sprint("Super", LocalDate.of(2022, 3, 1));
        userStory = new UserStory("US001", 2, "Fazer tal", 5);
        sprint.getSprintBacklog().saveUserStoryToSprintBacklog(userStory);
        UserStoryStatus userStoryStatus = new UserStoryStatus("Almost finished");
        userStory.setUserStoryStatusBoolean(userStoryStatus);

        assertEquals(userStory.getUserStoryStatus().getDescription(), "Almost finished");

    }

    @Test
    void setUpdateUserStoryWorkDoneTest() {
        //Arrange
        Company company = new Company();
        //User Story
        ProductBacklog productBacklog = new ProductBacklog();
        UserStory userStory = new UserStory("US001", 1, "US001 - Test", 40);
        productBacklog.saveUserStory(userStory);
        //Task
        UserProfile profile = company.getUserProfileStore().getUserProfile("Visitor");
        SystemUser user = new SystemUser("manuelbras", "manuelbras@beaver.com", "tester", "ghi", "ghi", "photo", profile);
        LocalDate startDateMb = LocalDate.of(2022, 1, 1);
        LocalDate endDateMb = LocalDate.of(2022, 1, 31);
        Resource resource = new Resource(user, startDateMb, endDateMb, 100, .5);
        String taskDescription = "must be at least 20 characters";
        TaskType taskType = company.getTaskTypeStore().getTypeByName("Testing");
        Task task = new Task("test", taskDescription, 20.00, taskType, resource);
        userStory.getTasks().saveTask(task);
        //TaskEffort
        LocalDate effortDate = LocalDate.of(2022, 1, 20);
        TaskEffort taskEffort = task.createTaskEffort(8, 0, effortDate, "test", ".pdf");
        LocalDate effortDate2 = LocalDate.of(2022, 1, 21);
        TaskEffort taskEffort2 = task.createTaskEffort(4, 0, effortDate2, "test2", ".pdf2");
        task.saveTaskEffort(taskEffort);
        task.saveTaskEffort(taskEffort2);
        //Act
        userStory.updateWorkDone(task);
        //Assert
        assertEquals(12, userStory.getWorkDone());
    }

    @Test
    void validatePriorityTest() {
        Sprint sprint = new Sprint("Super", LocalDate.of(2022, 3, 1));
        userStory = new UserStory("US001", 2, "Fazer tal", 5);
        sprint.getSprintBacklog().saveUserStoryToSprintBacklog(userStory);
        boolean expected = userStory.validatePriority(6);
        assertFalse(expected);
    }

    @Test
    void validatePriorityTest2() {
        Sprint sprint = new Sprint("Super", LocalDate.of(2022, 3, 1));
        userStory = new UserStory("US001", 2, "Fazer tal", 5);
        sprint.getSprintBacklog().saveUserStoryToSprintBacklog(userStory);
        boolean expected = userStory.validatePriority(1);
        assertTrue(expected);
    }

    @Test
    void validatePriorityTest3() {
        Sprint sprint = new Sprint("Super", LocalDate.of(2022, 3, 1));
        userStory = new UserStory("US001", 2, "Fazer tal", 5);
        sprint.getSprintBacklog().saveUserStoryToSprintBacklog(userStory);
        boolean expected = userStory.validatePriority(-1);
        assertFalse(expected);
    }

    @Test
    void validatePriorityTest4() {
        Sprint sprint = new Sprint("Super", LocalDate.of(2022, 3, 1));
        userStory = new UserStory("US001", 2, "Fazer tal", 5);
        sprint.getSprintBacklog().saveUserStoryToSprintBacklog(userStory);
        boolean expected = userStory.validatePriority(5);
        assertTrue(expected);
    }

    @Test
    void validatePriorityTest5() {
        Sprint sprint = new Sprint("Super", LocalDate.of(2022, 3, 1));
        userStory = new UserStory("US001", 2, "Fazer tal", 5);
        sprint.getSprintBacklog().saveUserStoryToSprintBacklog(userStory);
        boolean expected = userStory.validatePriority(0);
        assertTrue(expected);
    }

    @Test
    void isValidUserStoryDescription() {
        assertThrows(IllegalArgumentException.class, () -> {
            Sprint sprint = new Sprint("Super", LocalDate.of(2022, 3, 1));
            userStory = new UserStory("US001", 2, "", 5);
            sprint.getSprintBacklog().saveUserStoryToSprintBacklog(userStory);
        });
    }

    @Test
    void isValidUserStoryDescription2() {
        assertThrows(IllegalArgumentException.class, () -> {
            Sprint sprint = new Sprint("Super", LocalDate.of(2022, 3, 1));
            userStory = new UserStory("US001", 2, "    ", 5);
            sprint.getSprintBacklog().saveUserStoryToSprintBacklog(userStory);
        });
    }

    @Test
    void isValidUserStoryDescription3() {
        assertThrows(IllegalArgumentException.class, () -> {
            Sprint sprint = new Sprint("Super", LocalDate.of(2022, 3, 1));
            userStory = new UserStory("US001", 2, "Co", 5);
            sprint.getSprintBacklog().saveUserStoryToSprintBacklog(userStory);
        });
    }

    @Test
    void isValidUserStoryDescription4() {
        assertThrows(IllegalArgumentException.class, () -> {
            Sprint sprint = new Sprint("Super", LocalDate.of(2022, 3, 1));
            userStory = new UserStory("US001", 2, null, 5);
            sprint.getSprintBacklog().saveUserStoryToSprintBacklog(userStory);
        });
    }

    @Test
    void isValidUserStoryName() {
        assertThrows(IllegalArgumentException.class, () -> {
            Sprint sprint = new Sprint("Super", LocalDate.of(2022, 3, 1));
            userStory = new UserStory("", 2, "Fazer tal", 5);
            sprint.getSprintBacklog().saveUserStoryToSprintBacklog(userStory);
        });
    }

    @Test
    void isValidUserStoryName2() {
        assertThrows(IllegalArgumentException.class, () -> {
            Sprint sprint = new Sprint("Super", LocalDate.of(2022, 3, 1));
            userStory = new UserStory("C", 2, "Fazer tal", 5);
            sprint.getSprintBacklog().saveUserStoryToSprintBacklog(userStory);
        });
    }

    @Test
    void isValidUserStoryName3() {
        assertThrows(IllegalArgumentException.class, () -> {
            Sprint sprint = new Sprint("Super", LocalDate.of(2022, 3, 1));
            userStory = new UserStory("   ", 2, "Fazer tal", 5);
            sprint.getSprintBacklog().saveUserStoryToSprintBacklog(userStory);
        });
    }

    @Test
    void isValidUserStoryName4() {
        assertThrows(IllegalArgumentException.class, () -> {
            Sprint sprint = new Sprint("Super", LocalDate.of(2022, 3, 1));
            userStory = new UserStory(null, 2, "Fazer tal", 5);
            sprint.getSprintBacklog().saveUserStoryToSprintBacklog(userStory);
        });
    }
}