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
    void ValidateInfoUserStory() {
        //Arrange
        Company company = new Company();
        //User Story
        ProductBacklog productBacklog = new ProductBacklog();
        UserStory userStory = new UserStory("US001", 1, "US001 - Test", 40);
        productBacklog.saveUserStory(userStory);
        assertEquals("US001", userStory.getName());
        assertEquals("US001 - Test", userStory.getDescription().getDescriptionF());
    }


    @Test
    void setPriorityTest() {
        Sprint sprint = new Sprint("Super", LocalDate.of(2022, 3, 1));
        userStory = new UserStory("US001", 2, "Fazer tal", 5);
        sprint.getSprintBacklog().saveUserStoryToSprintBacklog(userStory);
        userStory.setPriority(4);
        assertEquals(4, userStory.getPriority());
    }

    @Test
    void setPriorityTestInvalid() {
        Sprint sprint = new Sprint("Super", LocalDate.of(2022, 3, 1));
        userStory = new UserStory("US001", 2, "Fazer tal", 5);
        sprint.getSprintBacklog().saveUserStoryToSprintBacklog(userStory);
        userStory.setPriority(6);
        assertEquals(2, userStory.getPriority());
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
        assertEquals("Fazer coiso", userStory.getDescription().getDescriptionF());
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

        assertEquals("Almost finished", userStory.getUserStoryStatus().getDescription());

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
        TaskType taskType = company.getTaskTypeStore().getTypeByDescription("Testing");
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

    @Test
    void isValidUserStoryName5() {
        assertThrows(IllegalArgumentException.class, () -> {
            Sprint sprint = new Sprint("Super", LocalDate.of(2022, 3, 1));
            userStory = new UserStory("CC", 2, "Fazer tal", 5);
            sprint.getSprintBacklog().saveUserStoryToSprintBacklog(userStory);
        });
    }

    @Test
    void hashCodeTest() {
        UserStory userStory1 = new UserStory("CCC", 1, "Fazer tal", 5);
        UserStory userStory2 = new UserStory("AAA", 2, "Fazer tal e coiso", 5);
        UserStory userStory3 = new UserStory("AAA", 2, "Fazer tal e coiso", 5);
        UserStoryStatus status4 = new UserStoryStatus("teste5");
        boolean result = status4.equals(null);

        assertNotEquals(userStory1.hashCode(), userStory2.hashCode());
        assertEquals(userStory2.getDescription().getDescriptionF(), userStory3.getDescription().getDescriptionF());
        assertEquals(userStory2.getPriority(), userStory3.getPriority());
        assertEquals(userStory2.getName(), userStory3.getName());
        assertEquals(userStory2.getTimeEstimate(), userStory3.getTimeEstimate());
        assertNotEquals(userStory2.getDescription().getDescriptionF(), userStory1.getDescription().getDescriptionF());
        assertNotEquals(null, userStory1);
        assertEquals(userStory1.getClass(), userStory2.getClass());
        assertNotEquals(userStory1.hashCode(), userStory3.hashCode());
        assertEquals(0, userStory1.getIdUserStory());
        assertFalse(result);
    }

    @Test
    void setID() {
        UserStory userStory1 = new UserStory("CCC", 2, "Fazer tal", 5);
        UserStory userStory2 = new UserStory("AAA", 2, "Fazer tal e coiso", 5);
        userStory2.setIdUserStory(2);

        assertEquals(2, userStory2.getIdUserStory());
        assertNotEquals(2, userStory1.getIdUserStory());
    }

    @Test
    void setPriorityTrue() {
        UserStory userStory1 = new UserStory("US001", 2, "Fazer tal", 5);
        UserStory userStory2 = new UserStory("US002", 2, "Fazer tal e coiso", 5);
        userStory2.setPriority(1);
        userStory1.setPriority(4);

        assertTrue(userStory1.setPriority(3));
        assertEquals(3, userStory1.getPriority());

    }
    @Test
    void setPriorityFalse() {
        UserStory userStory1 = new UserStory("US001", 2, "Fazer tal", 5);
        UserStory userStory2 = new UserStory("US002", 2, "Fazer tal e coiso", 5);
        userStory2.setPriority(1);
        userStory1.setPriority(6);

        assertFalse(userStory1.setPriority(6));
        assertNotEquals(6, userStory1.getPriority());

    }
}