package switch2021.project.model;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TaskTest {

    @Test

    public void taskCreatorTest() {
        Task tastkTest = new Task("test");

        String expectedTask = "test";

        assertEquals(expectedTask, tastkTest.getDescription());
    }

    @Test
    public void taskWithPrecedenceCreatorTest() {
        Company company = new Company();
        TaskType taskType = new TaskType("Coisa");
        Task tastkTest = new Task("test");
        UserProfile profile = company.getUserProfileStore().getUserProfile("Visitor");
        SystemUser user = new SystemUser("manuelbras", "manuelbras@beaver.com", "tester", "ghi", "ghi", "photo", profile);
        LocalDate startDateMb = LocalDate.of(2022, 1, 1);
        LocalDate endDateMb = LocalDate.of(2022, 1, 31);
        Resource resource = new Resource(user, startDateMb, endDateMb, 100, .5);
        Task tastkTest2 = new Task("test", "Planear fazer totil coisas de cenas", 22, taskType, resource);

        List<String> precedence = new ArrayList<>();
        precedence.add(tastkTest2.getDescription());

        Task tastkTest3 = new Task("test2", "Planear fazer totil coisas de cenas", 21, taskType, resource, precedence);

        String expectedTask = "Planear fazer totil coisas de cenas";

        assertEquals(expectedTask, tastkTest2.getDescription());
    }

    @Test
    public void createTaskSuccessWithNoTaskEffort() {
        //Arrange
        Company company = new Company();
        UserProfile profile = company.getUserProfileStore().getUserProfile("Visitor");
        SystemUser user = new SystemUser("manuelbras", "manuelbras@beaver.com", "tester", "ghi", "ghi", "photo", profile);
        LocalDate startDateMb = LocalDate.of(2022, 1, 1);
        LocalDate endDateMb = LocalDate.of(2022, 1, 31);
        Resource resource = new Resource(user, startDateMb, endDateMb, 100, .5);
        String taskDescription = "must be at least 20 characters";
        TaskType taskType = company.getTaskTypeStore().getTypeByName("Testing");
        TaskStatus taskStatus = company.getTaskStatusStore().getTaskStatusByDescription("Planned");
        //Act
        Task task = new Task("test", taskDescription, 16.00, taskType, resource);
        //Assert
        assertEquals("test", task.getName());
        assertEquals(taskDescription, task.getDescription());
        assertEquals(16.00, task.getEffortEstimate());
        assertEquals(taskType, task.getType());
        assertEquals(resource, task.getResponsible());
        assertEquals(16.00, task.getEffortRemaining());
        assertEquals(taskStatus, task.getStatus());
        assertTrue(task.getTaskEffortList().isEmpty());
        assertEquals(0, task.getHoursSpent());
        assertEquals(0, task.getExecutionPercentage());
        assertNull(task.getStartDate());
        assertNull(task.getEndDate());
    }

    @Test
    public void createTaskEffortSuccess() {
        //Arrange
        Company company = new Company();
        UserProfile profile = company.getUserProfileStore().getUserProfile("Visitor");
        SystemUser user = new SystemUser("manuelbras", "manuelbras@beaver.com", "tester", "ghi", "ghi", "photo", profile);
        LocalDate startDateMb = LocalDate.of(2022, 1, 1);
        LocalDate endDateMb = LocalDate.of(2022, 1, 31);
        Resource resource = new Resource(user, startDateMb, endDateMb, 100, .5);
        String taskDescription = "must be at least 20 characters";
        Task task = new Task("test", taskDescription, 16.00, new TaskType("User Story"), resource);

        int effortHours = 4;
        int effortMinutes = 30;
        LocalDate effortDate = LocalDate.of(2022, 1, 27);
        String comment = "test";
        String attachment = ".pdf";
        //Act
        TaskEffort taskEffort = task.createTaskEffort(effortHours, effortMinutes, effortDate, comment, attachment);
        //Assert
        assertEquals(effortHours, taskEffort.getEffortHours());
        assertEquals(effortMinutes, taskEffort.getEffortMinutes());
        assertEquals(effortDate, taskEffort.getEffortDate());
        assertEquals(comment, taskEffort.getComment());
        assertEquals(attachment, taskEffort.getAttachment());
    }

    @Test
    public void createTaskEffortFailWorkDateNotMatchResourceDates() {
        assertThrows(IllegalArgumentException.class, () -> {
            //Arrange
            Company company = new Company();
            UserProfile profile = company.getUserProfileStore().getUserProfile("Visitor");
            SystemUser user = new SystemUser("manuelbras", "manuelbras@beaver.com", "tester", "ghi", "ghi", "photo", profile);
            LocalDate startDateMb = LocalDate.of(2022, 1, 1);
            LocalDate endDateMb = LocalDate.of(2022, 1, 31);
            Resource resource = new Resource(user, startDateMb, endDateMb, 100, .5);
            String taskDescription = "must be at least 20 characters";
            Task task = new Task("test", taskDescription, 16.00, new TaskType("User Story"), resource);

            int effortHours = 4;
            int effortMinutes = 30;
            LocalDate effortDate = LocalDate.of(2021, 12, 27);
            String comment = "test";
            String attachment = ".pdf";
            //Act
            task.validateTaskEffort(task.createTaskEffort(effortHours, effortMinutes, effortDate, comment, attachment));
        });
    }

    @Test
    public void saveTaskEffortSuccess() {
        //Arrange
        Company company = new Company();
        UserProfile profile = company.getUserProfileStore().getUserProfile("Visitor");
        SystemUser user = new SystemUser("manuelbras", "manuelbras@beaver.com", "tester", "ghi", "ghi", "photo", profile);
        LocalDate startDateMb = LocalDate.of(2022, 1, 1);
        LocalDate endDateMb = LocalDate.of(2022, 1, 31);
        Resource resource = new Resource(user, startDateMb, endDateMb, 100, .5);
        String taskDescription = "must be at least 20 characters";
        TaskType taskType = company.getTaskTypeStore().getTypeByName("Testing");
        Task task = new Task("test", taskDescription, 20.00, taskType, resource);

        LocalDate effortDate = LocalDate.of(2022, 1, 20);
        TaskEffort taskEffort = task.createTaskEffort(8, 0, effortDate, "test", ".pdf");

        TaskStatus taskStatusExpected = company.getTaskStatusStore().getTaskStatusByDescription("Running");
        //Act
        task.saveTaskEffort(taskEffort);
        //Assert
        assertEquals(1, task.getTaskEffortList().size());
        assertEquals(taskStatusExpected, task.getStatus()); //change status to Running
        assertEquals(effortDate, task.getStartDate()); //set start date
        assertNull(task.getEndDate());
        assertEquals(20.00, task.getEffortEstimate()); //keep the same estimated effort
        assertEquals(12.00, task.getEffortRemaining());
        assertEquals(8, task.getHoursSpent());
        assertEquals(0.4, task.getExecutionPercentage());
    }

    @Test
    public void saveTwoTaskEffortSuccess() {
        //Arrange
        Company company = new Company();
        UserProfile profile = company.getUserProfileStore().getUserProfile("Visitor");
        SystemUser user = new SystemUser("manuelbras", "manuelbras@beaver.com", "tester", "ghi", "ghi", "photo", profile);
        LocalDate startDateMb = LocalDate.of(2022, 1, 1);
        LocalDate endDateMb = LocalDate.of(2022, 1, 31);
        Resource resource = new Resource(user, startDateMb, endDateMb, 100, .5);
        String taskDescription = "must be at least 20 characters";
        TaskType taskType = company.getTaskTypeStore().getTypeByName("Testing");
        Task task = new Task("test", taskDescription, 20.00, taskType, resource);

        LocalDate effortDate = LocalDate.of(2022, 1, 20);
        TaskEffort taskEffort = task.createTaskEffort(8, 0, effortDate, "test", ".pdf");
        LocalDate effortDate2 = LocalDate.of(2022, 1, 21);
        TaskEffort taskEffort2 = task.createTaskEffort(4, 0, effortDate2, "test2", ".pdf2");

        TaskStatus taskStatusExpected = company.getTaskStatusStore().getTaskStatusByDescription("Running");
        //Act
        task.saveTaskEffort(taskEffort);
        task.saveTaskEffort(taskEffort2);
        //Assert
        assertEquals(2, task.getTaskEffortList().size());
        assertEquals(taskStatusExpected, task.getStatus());
        assertEquals(effortDate, task.getStartDate()); //start date of the first effort
        assertNull(task.getEndDate());
        assertEquals(20.00, task.getEffortEstimate()); //keep the same estimated effort
        assertEquals(8.00, task.getEffortRemaining());
        assertEquals(12, task.getHoursSpent());
        assertEquals(0.6, task.getExecutionPercentage());
    }

    @Test
    public void saveThreeTaskEffortToCompleteTheTask() {
        //Arrange
        Company company = new Company();
        UserProfile profile = company.getUserProfileStore().getUserProfile("Visitor");
        SystemUser user = new SystemUser("manuelbras", "manuelbras@beaver.com", "tester", "ghi", "ghi", "photo", profile);
        LocalDate startDateMb = LocalDate.of(2022, 1, 1);
        LocalDate endDateMb = LocalDate.of(2022, 1, 31);
        Resource resource = new Resource(user, startDateMb, endDateMb, 100, .5);
        String taskDescription = "must be at least 20 characters";
        TaskType taskType = company.getTaskTypeStore().getTypeByName("Testing");
        Task task = new Task("test", taskDescription, 20.00, taskType, resource);

        LocalDate effortDate = LocalDate.of(2022, 1, 20);
        TaskEffort taskEffort = task.createTaskEffort(8, 0, effortDate, "test", ".pdf");
        LocalDate effortDate2 = LocalDate.of(2022, 1, 21);
        TaskEffort taskEffort2 = task.createTaskEffort(4, 0, effortDate2, "test2", ".pdf2");
        LocalDate effortDate3 = LocalDate.of(2022, 1, 22);
        TaskEffort taskEffort3 = task.createTaskEffort(8, 0, effortDate3, "test3", ".pdf3");

        TaskStatus taskStatusExpected = company.getTaskStatusStore().getTaskStatusByDescription("Finished");
        //Act
        task.saveTaskEffort(taskEffort);
        task.saveTaskEffort(taskEffort2);
        task.saveTaskEffort(taskEffort3);
        //Assert
        assertEquals(3, task.getTaskEffortList().size());
        assertEquals(taskStatusExpected, task.getStatus()); //change status to Finished
        assertEquals(effortDate, task.getStartDate());
        assertEquals(effortDate3, task.getEndDate()); //set end date
        assertEquals(20.00, task.getEffortEstimate()); //keep the same estimated effort
        assertEquals(0.00, task.getEffortRemaining());
        assertEquals(20, task.getHoursSpent());
        assertEquals(1.0, task.getExecutionPercentage());
    }

    @Test
    public void saveThreeTaskEffortToCompleteTheTaskButPassingTheEstimatedEffort() {
        //Arrange
        Company company = new Company();
        UserProfile profile = company.getUserProfileStore().getUserProfile("Visitor");
        SystemUser user = new SystemUser("manuelbras", "manuelbras@beaver.com", "tester", "ghi", "ghi", "photo", profile);
        LocalDate startDateMb = LocalDate.of(2022, 1, 1);
        LocalDate endDateMb = LocalDate.of(2022, 1, 31);
        Resource resource = new Resource(user, startDateMb, endDateMb, 100, .5);
        String taskDescription = "must be at least 20 characters";
        TaskType taskType = company.getTaskTypeStore().getTypeByName("Testing");
        Task task = new Task("test", taskDescription, 20.00, taskType, resource);

        LocalDate effortDate = LocalDate.of(2022, 1, 20);
        TaskEffort taskEffort = task.createTaskEffort(8, 0, effortDate, "test", ".pdf");
        LocalDate effortDate2 = LocalDate.of(2022, 1, 21);
        TaskEffort taskEffort2 = task.createTaskEffort(4, 0, effortDate2, "test2", ".pdf2");
        LocalDate effortDate3 = LocalDate.of(2022, 1, 22);
        TaskEffort taskEffort3 = task.createTaskEffort(10, 0, effortDate3, "test3", ".pdf3");

        TaskStatus taskStatusExpected = company.getTaskStatusStore().getTaskStatusByDescription("Finished");
        //Act
        task.saveTaskEffort(taskEffort);
        task.saveTaskEffort(taskEffort2);
        task.saveTaskEffort(taskEffort3);
        //Assert
        assertEquals(3, task.getTaskEffortList().size());
        assertEquals(taskStatusExpected, task.getStatus());
        assertEquals(effortDate, task.getStartDate());
        assertEquals(effortDate3, task.getEndDate());
        assertEquals(20.00, task.getEffortEstimate()); //keep the same estimated effort
        assertEquals(0.00, task.getEffortRemaining()); //0.0 is the lower value
        assertEquals(22, task.getHoursSpent());
        assertEquals(1.0, task.getExecutionPercentage()); //1.0 is the higher value
    }

    @Test
    public void saveTaskEffortAfterTheTaskAlreadyConsideredFinished() {
        //Arrange
        Company company = new Company();
        UserProfile profile = company.getUserProfileStore().getUserProfile("Visitor");
        SystemUser user = new SystemUser("manuelbras", "manuelbras@beaver.com", "tester", "ghi", "ghi", "photo", profile);
        LocalDate startDateMb = LocalDate.of(2022, 1, 1);
        LocalDate endDateMb = LocalDate.of(2022, 1, 31);
        Resource resource = new Resource(user, startDateMb, endDateMb, 100, .5);
        String taskDescription = "must be at least 20 characters";
        TaskType taskType = company.getTaskTypeStore().getTypeByName("Testing");
        Task task = new Task("test", taskDescription, 20.00, taskType, resource);

        LocalDate effortDate = LocalDate.of(2022, 1, 20);
        TaskEffort taskEffort = task.createTaskEffort(8, 0, effortDate, "test", ".pdf");
        LocalDate effortDate2 = LocalDate.of(2022, 1, 21);
        TaskEffort taskEffort2 = task.createTaskEffort(4, 0, effortDate2, "test2", ".pdf2");
        LocalDate effortDate3 = LocalDate.of(2022, 1, 22);
        TaskEffort taskEffort3 = task.createTaskEffort(8, 0, effortDate3, "test3", ".pdf3");
        LocalDate effortDate4 = LocalDate.of(2022, 1, 23);
        TaskEffort taskEffort4 = task.createTaskEffort(4, 0, effortDate4, "test4", ".pdf4");

        TaskStatus taskStatusExpected = company.getTaskStatusStore().getTaskStatusByDescription("Finished");
        //Act
        task.saveTaskEffort(taskEffort);
        task.saveTaskEffort(taskEffort2);
        task.saveTaskEffort(taskEffort3);
        task.saveTaskEffort(taskEffort4);
        //Assert
        assertEquals(4, task.getTaskEffortList().size());
        assertEquals(taskStatusExpected, task.getStatus());
        assertEquals(effortDate, task.getStartDate());
        assertEquals(effortDate4, task.getEndDate()); // end date is updated
        assertEquals(20.00, task.getEffortEstimate());
        assertEquals(0.00, task.getEffortRemaining());
        assertEquals(24, task.getHoursSpent());
        assertEquals(1.0, task.getExecutionPercentage());
    }

    @Test //Fail
    public void saveTwoEqualTaskEffort() {
        assertThrows(IllegalArgumentException.class, () -> {
            //Arrange
            Company company = new Company();
            UserProfile profile = company.getUserProfileStore().getUserProfile("Visitor");
            SystemUser user = new SystemUser("manuelbras", "manuelbras@beaver.com", "tester", "ghi", "ghi", "photo", profile);
            LocalDate startDateMb = LocalDate.of(2022, 1, 1);
            LocalDate endDateMb = LocalDate.of(2022, 1, 31);
            Resource resource = new Resource(user, startDateMb, endDateMb, 100, .5);
            String taskDescription = "must be at least 20 characters";
            TaskType taskType = company.getTaskTypeStore().getTypeByName("Testing");
            Task task = new Task("test", taskDescription, 20.00, taskType, resource);

            LocalDate effortDate = LocalDate.of(2022, 1, 20);
            TaskEffort taskEffort = task.createTaskEffort(8, 0, effortDate, "test", ".pdf");
            //Act
            task.saveTaskEffort(taskEffort);
            task.saveTaskEffort(taskEffort);
        });
    }

    @Test //Fail
    public void saveSameDayTaskEffort() {
        assertThrows(IllegalArgumentException.class, () -> {
            //Arrange
            Company company = new Company();
            UserProfile profile = company.getUserProfileStore().getUserProfile("Visitor");
            SystemUser user = new SystemUser("manuelbras", "manuelbras@beaver.com", "tester", "ghi", "ghi", "photo", profile);
            LocalDate startDateMb = LocalDate.of(2022, 1, 1);
            LocalDate endDateMb = LocalDate.of(2022, 1, 31);
            Resource resource = new Resource(user, startDateMb, endDateMb, 100, .5);
            String taskDescription = "must be at least 20 characters";
            TaskType taskType = company.getTaskTypeStore().getTypeByName("Testing");
            Task task = new Task("test", taskDescription, 20.00, taskType, resource);

            LocalDate effortDate = LocalDate.of(2022, 1, 20);
            TaskEffort taskEffort = task.createTaskEffort(8, 0, effortDate, "test", ".pdf");
            TaskEffort taskEffort2 = task.createTaskEffort(1, 30, effortDate, "test", ".pdf");
            //Act
            task.saveTaskEffort(taskEffort);
            task.saveTaskEffort(taskEffort2);
        });
    }

    @Test //Fail
    public void saveNullTaskEffort() {
        //Arrange
        Company company = new Company();
        UserProfile profile = company.getUserProfileStore().getUserProfile("Visitor");
        SystemUser user = new SystemUser("manuelbras", "manuelbras@beaver.com", "tester", "ghi", "ghi", "photo", profile);
        LocalDate startDateMb = LocalDate.of(2022, 1, 1);
        LocalDate endDateMb = LocalDate.of(2022, 1, 31);
        Resource resource = new Resource(user, startDateMb, endDateMb, 100, .5);
        String taskDescription = "must be at least 20 characters";
        TaskType taskType = company.getTaskTypeStore().getTypeByName("Testing");
        Task task = new Task("test", taskDescription, 20.00, taskType, resource);

        TaskEffort taskEffort = null;
        //Assert
        assertFalse(task.saveTaskEffort(taskEffort));
    }
//início (tentar acabar com os bugs)
    @Test
    public void updateHoursSpentPositivo() {
        //Arrange
        Company company = new Company();
        UserProfile profile = company.getUserProfileStore().getUserProfile("Visitor");
        SystemUser user = new SystemUser("manuelbras", "manuelbras@beaver.com", "tester", "ghi", "ghi", "photo", profile);

        LocalDate startDateMb = LocalDate.of(2022, 1, 1);
        LocalDate endDateMb = LocalDate.of(2022, 1, 31);
        Resource resource = new Resource(user, startDateMb, endDateMb, 100, .5);

        String taskDescription = "must be at least 20 characters";
        TaskType taskType = company.getTaskTypeStore().getTypeByName("Testing");
        Task task = new Task("test", taskDescription, 20.00, taskType, resource);

        LocalDate effortDate = LocalDate.of(2022, 1, 20);
        TaskEffort taskEffort = task.createTaskEffort(8, 0, effortDate, "test", ".pdf");

        TaskStatus taskStatusExpected = company.getTaskStatusStore().getTaskStatusByDescription("Running");
        task.saveTaskEffort(taskEffort);
        //Act

        //Assert
        assertEquals(12.0, task.getEffortRemaining());
        assertEquals(8.0, task.getHoursSpent());
        assertEquals(0.4, task.getExecutionPercentage());
    }

    @Test
    public void updateHoursSpentZero() {
        //Arrange
        Company company = new Company();
        UserProfile profile = company.getUserProfileStore().getUserProfile("Visitor");
        SystemUser user = new SystemUser("manuelbras", "manuelbras@beaver.com", "tester", "ghi", "ghi", "photo", profile);

        LocalDate startDateMb = LocalDate.of(2022, 1, 1);
        LocalDate endDateMb = LocalDate.of(2022, 1, 31);
        Resource resource = new Resource(user, startDateMb, endDateMb, 100, .5);

        String taskDescription = "must be at least 20 characters";
        TaskType taskType = company.getTaskTypeStore().getTypeByName("Testing");
        Task task = new Task("test", taskDescription, 8.00, taskType, resource);

        LocalDate effortDate = LocalDate.of(2022, 1, 20);
        TaskEffort taskEffort = task.createTaskEffort(8, 0, effortDate, "test", ".pdf");

        TaskStatus taskStatusExpected = company.getTaskStatusStore().getTaskStatusByDescription("Running");
        task.saveTaskEffort(taskEffort);
        //Act

        //Assert
        assertEquals(0.0, task.getEffortRemaining());
        assertEquals(8.0, task.getHoursSpent());
        assertEquals(1.0, task.getExecutionPercentage());
    }

    @Test
    public void updateHoursSpentNegative() {
        //Arrange
        Company company = new Company();
        UserProfile profile = company.getUserProfileStore().getUserProfile("Visitor");
        SystemUser user = new SystemUser("manuelbras", "manuelbras@beaver.com", "tester", "ghi", "ghi", "photo", profile);

        LocalDate startDateMb = LocalDate.of(2022, 1, 1);
        LocalDate endDateMb = LocalDate.of(2022, 1, 31);
        Resource resource = new Resource(user, startDateMb, endDateMb, 100, .5);

        String taskDescription = "must be at least 20 characters";
        TaskType taskType = company.getTaskTypeStore().getTypeByName("Testing");
        Task task = new Task("test", taskDescription, 8.00, taskType, resource);

        LocalDate effortDate = LocalDate.of(2022, 1, 20);
        TaskEffort taskEffort = task.createTaskEffort(8, 0, effortDate, "test", ".pdf");
        LocalDate effortDate2 = LocalDate.of(2022, 1, 21);
        TaskEffort taskEffort2 = task.createTaskEffort(4, 0, effortDate2, "test2", ".pdf2");
        TaskStatus taskStatusExpected = company.getTaskStatusStore().getTaskStatusByDescription("Running");
        task.saveTaskEffort(taskEffort);
        //Act
        task.saveTaskEffort(taskEffort2);
        //Assert
        assertEquals(-1.0, task.getEffortRemaining());
        assertEquals(12.0, task.getHoursSpent());
        assertEquals(1.0, task.getExecutionPercentage());
    }
    //fim (tentar acabar com os bugs)

    @Test
    void hasTypeStatusResponsibleTestSuccess() {
        //Arrange
        Company company = new Company();
        UserProfile profile = company.getUserProfileStore().getUserProfile("Visitor");
        SystemUser user = new SystemUser("manuelbras", "manuelbras@beaver.com", "tester", "ghi", "ghi", "photo", profile);
        LocalDate startDateMb = LocalDate.of(2022, 1, 1);
        LocalDate endDateMb = LocalDate.of(2022, 1, 31);
        Resource resource = new Resource(user, startDateMb, endDateMb, 100, .5);
        String taskDescription = "must be at least 20 characters";
        TaskType taskType = company.getTaskTypeStore().getTypeByName("Testing");
        Task task = new Task("test", taskDescription, 20.00, taskType, resource);
        //Assert
        assertTrue(task.hasType(taskType));
        assertTrue(task.hasStatus(company.getTaskStatusStore().getInitialStatus()));
        assertTrue(task.hasResponsible(resource));
    }

    @Test
    void constructorCheckNameEmptyTest() {
        //Arrange
        Company company = new Company();
        UserProfile profile = company.getUserProfileStore().getUserProfile("Visitor");
        SystemUser user = new SystemUser("manuelbras", "manuelbras@beaver.com", "tester", "ghi", "ghi", "photo", profile);
        LocalDate startDateMb = LocalDate.of(2022, 1, 1);
        LocalDate endDateMb = LocalDate.of(2022, 1, 31);
        Resource resource = new Resource(user, startDateMb, endDateMb, 100, .5);
        String taskDescription = "must be at least 20 characters";
        TaskType taskType = company.getTaskTypeStore().getTypeByName("Testing");

        //Asserts
        assertThrows(IllegalArgumentException.class, () -> {
            //Arrange
            new Task("", taskDescription, 20.00, taskType, resource);
        });
    }

    @Test
    void constructorCheckNameSizeTest() {
        //Arrange
        Company company = new Company();
        UserProfile profile = company.getUserProfileStore().getUserProfile("Visitor");
        SystemUser user = new SystemUser("manuelbras", "manuelbras@beaver.com", "tester", "ghi", "ghi", "photo", profile);
        LocalDate startDateMb = LocalDate.of(2022, 1, 1);
        LocalDate endDateMb = LocalDate.of(2022, 1, 31);
        Resource resource = new Resource(user, startDateMb, endDateMb, 100, .5);
        String taskDescription = "must be at least 20 characters";
        TaskType taskType = company.getTaskTypeStore().getTypeByName("Testing");

        //Asserts
        assertThrows(IllegalArgumentException.class, () -> {
            //Arrange
            new Task("t1", taskDescription, 20.00, taskType, resource);
        });
    }

    @Test
    void constructorCheckNameSizeTest2() {
        //Arrange
        Company company = new Company();
        UserProfile profile = company.getUserProfileStore().getUserProfile("Visitor");
        SystemUser user = new SystemUser("manuelbras", "manuelbras@beaver.com", "tester", "ghi", "ghi", "photo", profile);
        LocalDate startDateMb = LocalDate.of(2022, 1, 1);
        LocalDate endDateMb = LocalDate.of(2022, 1, 31);
        Resource resource = new Resource(user, startDateMb, endDateMb, 100, .5);
        String taskDescription = "must be at least 20 characters";
        TaskType taskType = company.getTaskTypeStore().getTypeByName("Testing");

        //Act
        assertThrows(IllegalArgumentException.class, () -> {
            //Assert
            new Task("1", taskDescription, 20.00, taskType, resource);
        });
    }

    @Test
    void constructorCheckNameSizeTest3Sucess() {
        //Arrange
        Company company = new Company();
        UserProfile profile = company.getUserProfileStore().getUserProfile("Visitor");
        SystemUser user = new SystemUser("manuelbras", "manuelbras@beaver.com", "tester", "ghi", "ghi", "photo", profile);
        LocalDate startDateMb = LocalDate.of(2022, 1, 1);
        LocalDate endDateMb = LocalDate.of(2022, 1, 31);
        Resource resource = new Resource(user, startDateMb, endDateMb, 100, .5);
        String taskDescription = "must be at least 20 characters";
        TaskType taskType = company.getTaskTypeStore().getTypeByName("Testing");

        //Act
        Task task = new Task("t31", taskDescription, 20.00, taskType, resource);
        //Assert
        assertEquals("t31",task.getName());

    }


    @Test
    void constructorCheckDescriptionEmptyTest() {
        //Arrange
        Company company = new Company();
        UserProfile profile = company.getUserProfileStore().getUserProfile("Visitor");
        SystemUser user = new SystemUser("manuelbras", "manuelbras@beaver.com", "tester", "ghi", "ghi", "photo", profile);
        LocalDate startDateMb = LocalDate.of(2022, 1, 1);
        LocalDate endDateMb = LocalDate.of(2022, 1, 31);
        Resource resource = new Resource(user, startDateMb, endDateMb, 100, .5);
        String taskDescription = "";
        TaskType taskType = company.getTaskTypeStore().getTypeByName("Testing");

        //Asserts
        assertThrows(IllegalArgumentException.class, () -> {
            //Arrange
            new Task("test", taskDescription, 20.00, taskType, resource);
        });
    }

    @Test
    void constructorCheckDescriptionSizeTest() {
        //Arrange
        Company company = new Company();
        UserProfile profile = company.getUserProfileStore().getUserProfile("Visitor");
        SystemUser user = new SystemUser("manuelbras", "manuelbras@beaver.com", "tester", "ghi", "ghi", "photo", profile);
        LocalDate startDateMb = LocalDate.of(2022, 1, 1);
        LocalDate endDateMb = LocalDate.of(2022, 1, 31);
        Resource resource = new Resource(user, startDateMb, endDateMb, 100, .5);
        String taskDescription = "must be at";
        TaskType taskType = company.getTaskTypeStore().getTypeByName("Testing");

        //Asserts
        assertThrows(IllegalArgumentException.class, () -> {
            //Arrange
            new Task("test", taskDescription, 20.00, taskType, resource);
        });
    }

    @Test
    void constructorCheckTypeNotNullTest() {
        //Arrange
        Company company = new Company();
        UserProfile profile = company.getUserProfileStore().getUserProfile("Visitor");
        SystemUser user = new SystemUser("manuelbras", "manuelbras@beaver.com", "tester", "ghi", "ghi", "photo", profile);
        LocalDate startDateMb = LocalDate.of(2022, 1, 1);
        LocalDate endDateMb = LocalDate.of(2022, 1, 31);
        Resource resource = new Resource(user, startDateMb, endDateMb, 100, .5);
        String taskDescription = "must be at least 20 characters";
        TaskType taskType = null;

        //Asserts
        assertThrows(IllegalArgumentException.class, () -> {
            //Arrange
            new Task("test", taskDescription, 20.00, taskType, resource);
        });
    }

    @Test
    void constructorCheckResponsibleNotNullTest() {
        //Arrange
        Company company = new Company();
        Resource resource = null;
        String taskDescription = "must be at least 20 characters";
        TaskType taskType = company.getTaskTypeStore().getTypeByName("Testing");

        //Asserts
        assertThrows(IllegalArgumentException.class, () -> {
            //Arrange
            new Task("test", taskDescription, 20.00, taskType, resource);
        });
    }

    @Test
    void checkIdRulesTest() {
        //Arrange
        Company company = new Company();
        UserProfile profile = company.getUserProfileStore().getUserProfile("Visitor");
        SystemUser user = new SystemUser("manuelbras", "manuelbras@beaver.com", "tester", "ghi", "ghi", "photo", profile);
        LocalDate startDateMb = LocalDate.of(2022, 1, 1);
        LocalDate endDateMb = LocalDate.of(2022, 1, 31);
        Resource resource = new Resource(user, startDateMb, endDateMb, 100, .5);
        String taskDescription = "must be at least 20 characters";
        TaskType taskType = company.getTaskTypeStore().getTypeByName("Testing");
        Task test = new Task("test", taskDescription, 20.00, taskType, resource);
        //Asserts
        assertThrows(IllegalArgumentException.class, () -> {
            //Arrange
            test.setIdTask(-1);
        });
    }


    @Test
    void checkEffortRulesTest() {
        //Asserts
        assertThrows(IllegalArgumentException.class, () -> {
            //Arrange
            Company company = new Company();
            UserProfile profile = company.getUserProfileStore().getUserProfile("Visitor");
            SystemUser user = new SystemUser("manuelbras", "manuelbras@beaver.com", "tester", "ghi", "ghi", "photo", profile);
            LocalDate startDateMb = LocalDate.of(2022, 1, 1);
            LocalDate endDateMb = LocalDate.of(2022, 1, 31);
            Resource resource = new Resource(user, startDateMb, endDateMb, 100, .5);
            String taskDescription = "must be at least 20 characters";
            TaskType taskType = company.getTaskTypeStore().getTypeByName("Testing");
            //Act
            new Task("test", taskDescription, 00.00, taskType, resource);
        });
    }

    @Test
    public void overrideAndHashCodeTest() {
        //Arrange
        Company company = new Company();
        UserProfile profile = company.getUserProfileStore().getUserProfile("Visitor");
        SystemUser user = new SystemUser("manuelbras", "manuelbras@beaver.com", "tester", "ghi", "ghi", "photo", profile);
        LocalDate startDateMb = LocalDate.of(2022, 1, 1);
        LocalDate endDateMb = LocalDate.of(2022, 1, 31);
        Resource resource = new Resource(user, startDateMb, endDateMb, 100, .5);
        String taskDescription = "must be at least 20 characters";
        TaskType taskType = company.getTaskTypeStore().getTypeByName("Testing");
        Task task1 = new Task("test", taskDescription, 20.00, taskType, resource);
        Task task2 = new Task("test", taskDescription, 20.00, taskType, resource);
        Task task3 = new Task("test3", taskDescription, 20.00, taskType, resource);
        //Assert
        assertNotSame(task1, task2);
        assertEquals(task1, task2);
        assertEquals(task1.hashCode(), task2.hashCode());
        assertNotEquals(task1, task3);
        assertNotEquals(task1.hashCode(), task3.hashCode());
    }

}