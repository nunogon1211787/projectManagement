package switch2021.project.model.Task;

import org.junit.jupiter.api.Test;
import switch2021.project.model.Company;
import switch2021.project.model.valueObject.*;
import switch2021.project.model.SystemUser.SystemUser;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BooleanSupplier;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class TaskTest {

    @Test

    public void createTaskOnlyWithDescription() {
        //Arrange
        Task tastkTest = new Task("test");
        //Act
        String expectedTask = "test";
        //Assert
        assertEquals(expectedTask, tastkTest.getDescription().getText());
    }

    @Test
    public void taskWithPrecedenceCreatorTest() {
        //Arrange
        Company company = new Company();
        TaskType taskType = new TaskType("Coisa");
        UserProfile profile = company.getUserProfileStore().getUserProfile("Visitor");
        SystemUser user = new SystemUser("manuelbras", "manuelbras@beaver.com", "tester", "Qwerty_1", "Qwerty_1", "photo.png", profile);
        LocalDate startDateMb = LocalDate.of(2022, 1, 1);
        LocalDate endDateMb = LocalDate.of(2022, 1, 31);
        Resource resource = new Resource(user, startDateMb, endDateMb, new CostPerHour(100), new PercentageOfAllocation(.5));
        Task tastkTest2 = new Task("test", "Planear fazer totil coisas de cenas", 22, taskType, resource);
        //Act
        List<String> precedence = new ArrayList<>();
        precedence.add(tastkTest2.getDescription().getText());
        new Task("testdois", "Planear fazer totil coisas de cenas", 21, taskType, resource, precedence);
        String expectedTask = "Planear fazer totil coisas de cenas";
        //Assert
        assertEquals(expectedTask, tastkTest2.getDescription().getText());
    }

    @Test
    public void createTaskEffortSuccessMock() {
        //Arrange
        //UserProfile
        UserProfile profile = mock(UserProfile.class);
        Description description = mock(Description.class);
        when(profile.getUserProfileName()).thenReturn(description);
        when(description.getText()).thenReturn("Visitor");
        //SystemUser
        SystemUser user = mock(SystemUser.class);
        Name name = mock(Name.class);
        when(user.getUserName()).thenReturn(name);
        when(name.getNameF()).thenReturn("manuelbras");
        Email email = mock(Email.class);
        when(user.getEmail()).thenReturn(email);
        when(email.getEmail()).thenReturn("manuelbras@beaver.com");
        Function function = mock(Function.class);
        when(user.getFunction()).thenReturn(function);
        when(function.getText()).thenReturn("tester");
        Password password = mock(Password.class);
        when(user.getPassword()).thenReturn(password);
        when(password.getPwd()).thenReturn("Qwerty_1");
        Photo photo = mock(Photo.class);
        when(user.getPhoto()).thenReturn(photo);
        when(user.getPhoto().getExtension()).thenReturn("photo.png");
        //Resource
        Resource resource = mock(Resource.class);
        when(resource.getUser()).thenReturn(user);
        LocalDate startDateMb = LocalDate.of(2022, 1, 1);
        LocalDate endDateMb = LocalDate.of(2022, 1, 31);
        when(resource.getStartDate()).thenReturn(startDateMb);
        when(resource.getEndDate()).thenReturn(endDateMb);
        CostPerHour costPerHour = mock(CostPerHour.class);
        when(resource.getCostPerHour()).thenReturn(costPerHour);
        when(costPerHour.getCost()).thenReturn(100.00);
        PercentageOfAllocation percentageOfAllocation = mock(PercentageOfAllocation.class);
        when(resource.getPercentageOfAllocation()).thenReturn(percentageOfAllocation);
        when(percentageOfAllocation.getPercentage()).thenReturn(0.5);
        //TaskType
        TaskType taskType = mock(TaskType.class);
        Description descType = mock(Description.class);
        when(taskType.getDescription()).thenReturn(descType);
        when(descType.getText()).thenReturn("Testing");
        //TaskStatus
        TaskStatus taskStatus = mock(TaskStatus.class);
        Description descTaskStatys = mock(Description.class);
        when(taskStatus.getDescription()).thenReturn(descTaskStatys);
        when(descTaskStatys.getText()).thenReturn("Running");
        String taskDescription = "must be at least 20 characters";
        //Task
        Task task = new Task("test", taskDescription, 16.00, taskType, resource);
        int effortHours = 4;
        int effortMinutes = 30;
        Date effortDate = new Date(LocalDate.of(2022, 1, 27));
        String comment = "test";
        String attachment = ".pdf";
        //Act
        task.createAndSaveTaskEffort(effortHours, effortMinutes, effortDate, comment, attachment);
        //Assert
        assertEquals(effortHours, task.getTaskEffortList().get(0).getEffortHours().getEffortHours());
        assertEquals(effortMinutes, task.getTaskEffortList().get(0).getEffortMinutes().getEffortMinutes());
        assertEquals(effortDate, task.getTaskEffortList().get(0).getEffortDate());
        assertEquals(comment, task.getTaskEffortList().get(0).getComment().getText());
        assertEquals(attachment, task.getTaskEffortList().get(0).getAttachment().getExtension());
        assertEquals(taskType, task.getType());
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
            Resource resource = new Resource(user, startDateMb, endDateMb, new CostPerHour(100), new PercentageOfAllocation(.5));
            String taskDescription = "must be at least 20 characters";
            Task task = new Task("test", taskDescription, 16.00, new TaskType("User Story"), resource);

            int effortHours = 4;
            int effortMinutes = 30;
            Date effortDate = new Date(LocalDate.of(2021, 12, 27));
            String comment = "test";
            String attachment = ".pdf";
            //Act
            task.validateTaskEffort(new TaskEffort(effortHours, effortMinutes, effortDate, comment, attachment));
        });
    }


    @Test
    public void createAndSaveTaskEffortSuccessMock() {
        //Arrange

        //UserProfile
        UserProfile profile = mock(UserProfile.class);
        Description description = mock(Description.class);
        when(profile.getUserProfileName()).thenReturn(description);
        when(description.getText()).thenReturn("Visitor");
        //SystemUser
        SystemUser user = mock(SystemUser.class);
        Name name = mock(Name.class);
        when(user.getUserName()).thenReturn(name);
        when(name.getNameF()).thenReturn("manuelbras");
        Email email = mock(Email.class);
        when(user.getEmail()).thenReturn(email);
        when(email.getEmail()).thenReturn("manuelbras@beaver.com");
        Function function = mock(Function.class);
        when(user.getFunction()).thenReturn(function);
        when(function.getText()).thenReturn("tester");
        Password password = mock(Password.class);
        when(user.getPassword()).thenReturn(password);
        when(password.getPwd()).thenReturn("Qwerty_1");
        Photo photo = mock(Photo.class);
        when(user.getPhoto()).thenReturn(photo);
        when(user.getPhoto().getExtension()).thenReturn("photo.png");
        //Resource
        Resource resource = mock(Resource.class);
        when(resource.getUser()).thenReturn(user);
        LocalDate startDateMb = LocalDate.of(2022, 1, 1);
        LocalDate endDateMb = LocalDate.of(2022, 1, 31);
        when(resource.getStartDate()).thenReturn(startDateMb);
        when(resource.getEndDate()).thenReturn(endDateMb);
        CostPerHour costPerHour = mock(CostPerHour.class);
        when(resource.getCostPerHour()).thenReturn(costPerHour);
        when(costPerHour.getCost()).thenReturn(100.00);
        PercentageOfAllocation percentageOfAllocation = mock(PercentageOfAllocation.class);
        when(resource.getPercentageOfAllocation()).thenReturn(percentageOfAllocation);
        when(percentageOfAllocation.getPercentage()).thenReturn(0.5);
        //TaskType
        TaskType taskType = mock(TaskType.class);
        Description descType = mock(Description.class);
        when(taskType.getDescription()).thenReturn(descType);
        when(descType.getText()).thenReturn("Testing");
        //TaskStatus
        TaskStatus taskStatus = mock(TaskStatus.class);
        Description descTaskStatys = mock(Description.class);
        when(taskStatus.getDescription()).thenReturn(descTaskStatys);
        when(descTaskStatys.getText()).thenReturn("Running");
        String taskDescription = "must be at least 20 characters";
        //TaskStatus
        Task task = new Task("test", taskDescription, 20.00, taskType, resource); // same class don't mock?
        Date effortDate = new Date(LocalDate.of(2022, 1, 27));

        //Act
        task.createAndSaveTaskEffort(8, 0, effortDate, "test", ".pdf");

        //Assert
        assertEquals(1, task.getTaskEffortList().size());
        assertEquals("Running", task.getStatus().getDescription().getText()); //change status to Running
        assertEquals(effortDate.getEffortDate(), task.getStartDate()); //set start date
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
        SystemUser user = new SystemUser("manuelbras", "manuelbras@beaver.com", "tester", "Qwerty_1", "Qwerty_1", "photo.png", profile);
        LocalDate startDateMb = LocalDate.of(2022, 1, 1);
        LocalDate endDateMb = LocalDate.of(2022, 1, 31);
        Resource resource = new Resource(user, startDateMb, endDateMb, new CostPerHour(100), new PercentageOfAllocation(.5));
        String taskDescription = "must be at least 20 characters";
        TaskType taskType = company.getTaskTypeStore().getTypeByDescription("Testing");
        Task task = new Task("test", taskDescription, 20.00, taskType, resource);


        TaskStatus taskStatusExpected = company.getTaskStatusStore().getTaskStatusByDescription("Running");
        //Act
        Date effortDate = new Date(LocalDate.of(2022, 1, 20));
        task.createAndSaveTaskEffort(8, 0, effortDate, "test", ".pdf");
        Date effortDate2 = new Date(LocalDate.of(2022, 1, 21));
        task.createAndSaveTaskEffort(4, 0, effortDate2, "test2", ".xls");

        //Assert
        assertEquals(2, task.getTaskEffortList().size());
        assertEquals(taskStatusExpected.getDescription().getText(), task.getStatus().getDescription().getText());
        assertEquals(effortDate.getEffortDate(), task.getStartDate()); //start date of the first effort
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
        SystemUser user = new SystemUser("manuelbras", "manuelbras@beaver.com", "tester", "Qwerty_1", "Qwerty_1", "photo.png", profile);
        LocalDate startDateMb = LocalDate.of(2022, 1, 1);
        LocalDate endDateMb = LocalDate.of(2022, 1, 31);
        Resource resource = new Resource(user, startDateMb, endDateMb, new CostPerHour(100), new PercentageOfAllocation(.5));
        String taskDescription = "must be at least 20 characters";
        TaskType taskType = company.getTaskTypeStore().getTypeByDescription("Testing");
        Task task = new Task("test", taskDescription, 20.00, taskType, resource);

        //Act
        Date effortDate = new Date(LocalDate.of(2022, 1, 20));
        task.createAndSaveTaskEffort(8, 0, effortDate, "test", ".pdf");
        Date effortDate2 = new Date(LocalDate.of(2022, 1, 21));
        task.createAndSaveTaskEffort(4, 0, effortDate2, "test2", ".doc");
        Date effortDate3 = new Date(LocalDate.of(2022, 1, 22));
        task.createAndSaveTaskEffort(8, 0, effortDate3, "test3", ".xls");

        TaskStatus taskStatusExpected = company.getTaskStatusStore().getTaskStatusByDescription("Finished");

        //Assert
        assertEquals(3, task.getTaskEffortList().size());
        assertEquals(taskStatusExpected.getDescription().getText(), task.getStatus().getDescription().getText()); //change status to Finished
        assertEquals(effortDate.getEffortDate(), task.getStartDate());
        assertEquals(effortDate3.getEffortDate(), task.getEndDate()); //set end date
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
        SystemUser user = new SystemUser("manuelbras", "manuelbras@beaver.com", "tester", "Qwerty_1", "Qwerty_1", "photo.png", profile);
        LocalDate startDateMb = LocalDate.of(2022, 1, 1);
        LocalDate endDateMb = LocalDate.of(2022, 1, 31);
        Resource resource = new Resource(user, startDateMb, endDateMb, new CostPerHour(100), new PercentageOfAllocation(.5));
        String taskDescription = "must be at least 20 characters";
        TaskType taskType = company.getTaskTypeStore().getTypeByDescription("Testing");
        Task task = new Task("test", taskDescription, 20.00, taskType, resource);

        Date effortDate = new Date(LocalDate.of(2022, 1, 20));
        Date effortDate2 = new Date(LocalDate.of(2022, 1, 21));
        Date effortDate3 = new Date(LocalDate.of(2022, 1, 22));

        TaskStatus taskStatusExpected = company.getTaskStatusStore().getTaskStatusByDescription("Finished");
        //Act
        task.createAndSaveTaskEffort(8, 0, effortDate, "test", ".pdf");
        task.createAndSaveTaskEffort(4, 0, effortDate2, "test2", ".doc");
        task.createAndSaveTaskEffort(10, 0, effortDate3, "test3", ".pdf");

        //Assert
        assertEquals(3, task.getTaskEffortList().size());
        assertEquals(taskStatusExpected.getDescription().getText(), task.getStatus().getDescription().getText());
        assertEquals(effortDate.getEffortDate(), task.getStartDate());
        assertEquals(effortDate3.getEffortDate(), task.getEndDate());
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
        SystemUser user = new SystemUser("manuelbras", "manuelbras@beaver.com", "tester", "Qwerty_1", "Qwerty_1", "photo.png", profile);
        LocalDate startDateMb = LocalDate.of(2022, 1, 1);
        LocalDate endDateMb = LocalDate.of(2022, 1, 31);
        Resource resource = new Resource(user, startDateMb, endDateMb, new CostPerHour(100), new PercentageOfAllocation(.5));
        String taskDescription = "must be at least 20 characters";
        TaskType taskType = company.getTaskTypeStore().getTypeByDescription("Testing");
        Task task = new Task("test", taskDescription, 20.00, taskType, resource);

        Date effortDate = new Date(LocalDate.of(2022, 1, 20));
        Date effortDate2 = new Date(LocalDate.of(2022, 1, 21));
        Date effortDate3 = new Date(LocalDate.of(2022, 1, 22));
        Date effortDate4 = new Date(LocalDate.of(2022, 1, 23));

        TaskStatus taskStatusExpected = company.getTaskStatusStore().getTaskStatusByDescription("Finished");
        //Act
        task.createAndSaveTaskEffort(8, 0, effortDate, "test", ".pdf");
        task.createAndSaveTaskEffort(4, 0, effortDate2, "test2", ".pdf");
        task.createAndSaveTaskEffort(8, 0, effortDate3, "test3", ".doc");
        task.createAndSaveTaskEffort(4, 0, effortDate4, "test4", ".txt");

        //Assert
        assertEquals(4, task.getTaskEffortList().size());
        assertEquals(taskStatusExpected.getDescription().getText(), task.getStatus().getDescription().getText());
        assertEquals(effortDate.getEffortDate(), task.getStartDate());
        assertEquals(effortDate4.getEffortDate(), task.getEndDate()); // end date is updated
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
            Resource resource = new Resource(user, startDateMb, endDateMb, new CostPerHour(100), new PercentageOfAllocation(.5));
            String taskDescription = "must be at least 20 characters";
            TaskType taskType = company.getTaskTypeStore().getTypeByDescription("Testing");
            Task task = new Task("test", taskDescription, 20.00, taskType, resource);

            Date effortDate = new Date(LocalDate.of(2022, 1, 20));
            //Act
            task.createAndSaveTaskEffort(8, 0, effortDate, "test", ".pdf");
            task.createAndSaveTaskEffort(8, 0, effortDate, "test", ".pdf");

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
            Resource resource = new Resource(user, startDateMb, endDateMb, new CostPerHour(100), new PercentageOfAllocation(.5));
            String taskDescription = "must be at least 20 characters";
            TaskType taskType = company.getTaskTypeStore().getTypeByDescription("Testing");
            Task task = new Task("test", taskDescription, 20.00, taskType, resource);

            Date effortDate = new Date(LocalDate.of(2022, 1, 20));
            //Act
            task.createAndSaveTaskEffort(8, 0, effortDate, "test", ".pdf");
            task.createAndSaveTaskEffort(1, 30, effortDate, "test", ".pdf");
        });
    }


    @Test
    public void updateHoursSpentPositivo() {
        //Arrange
        Company company = new Company();
        UserProfile profile = company.getUserProfileStore().getUserProfile("Visitor");
        SystemUser user = new SystemUser("manuelbras", "manuelbras@beaver.com", "tester", "Qwerty_1", "Qwerty_1", "photo.png", profile);
        LocalDate startDateMb = LocalDate.of(2022, 1, 1);
        LocalDate endDateMb = LocalDate.of(2022, 1, 31);
        Resource resource = new Resource(user, startDateMb, endDateMb, new CostPerHour(100), new PercentageOfAllocation(.5));
        String taskDescription = "must be at least 20 characters";
        TaskType taskType = company.getTaskTypeStore().getTypeByDescription("Testing");
        Task task = new Task("test", taskDescription, 20.00, taskType, resource);

        Date effortDate = new Date(LocalDate.of(2022, 1, 20));
        //Act
        task.createAndSaveTaskEffort(19, 0, effortDate, "test", ".pdf");
        //Assert
        assertEquals(1.0, task.getEffortRemaining());
        assertEquals(19.0, task.getHoursSpent());
        assertEquals(0.95, task.getExecutionPercentage());
    }

    @Test
    public void updateHoursSpentZero() {
        //Arrange
        Company company = new Company();
        UserProfile profile = company.getUserProfileStore().getUserProfile("Visitor");
        SystemUser user = new SystemUser("manuelbras", "manuelbras@beaver.com", "tester", "Qwerty_1", "Qwerty_1", "photo.png", profile);

        LocalDate startDateMb = LocalDate.of(2022, 1, 1);
        LocalDate endDateMb = LocalDate.of(2022, 1, 31);
        Resource resource = new Resource(user, startDateMb, endDateMb, new CostPerHour(100), new PercentageOfAllocation(.5));

        String taskDescription = "must be at least 20 characters";
        TaskType taskType = company.getTaskTypeStore().getTypeByDescription("Testing");
        Task task = new Task("test", taskDescription, 8.00, taskType, resource);

        Date effortDate = new Date(LocalDate.of(2022, 1, 20));

        //Act
        task.createAndSaveTaskEffort(8, 0, effortDate, "test", ".pdf");

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
        SystemUser user = new SystemUser("manuelbras", "manuelbras@beaver.com", "tester", "Qwerty_1", "Qwerty_1", "photo.png", profile);

        LocalDate startDateMb = LocalDate.of(2022, 1, 1);
        LocalDate endDateMb = LocalDate.of(2022, 1, 31);
        Resource resource = new Resource(user, startDateMb, endDateMb, new CostPerHour(100), new PercentageOfAllocation(.5));

        String taskDescription = "must be at least 20 characters";
        TaskType taskType = company.getTaskTypeStore().getTypeByDescription("Testing");
        Task task = new Task("test", taskDescription, 8.00, taskType, resource);

        Date effortDate = new Date(LocalDate.of(2022, 1, 20));
        Date effortDate2 = new Date(LocalDate.of(2022, 1, 21));
        //Act
        task.createAndSaveTaskEffort(6, 0, effortDate, "test", ".pdf");
        task.createAndSaveTaskEffort(4, 0, effortDate2, "test2", ".doc");
        //Assert
        assertEquals(0.0, task.getEffortRemaining());
        assertEquals(10.0, task.getHoursSpent());
        assertEquals(1.0, task.getExecutionPercentage());
    }

    @Test
    public void updateHoursSpentNegative2() {
        //Arrange
        Company company = new Company();
        UserProfile profile = company.getUserProfileStore().getUserProfile("Visitor");
        SystemUser user = new SystemUser("manuelbras", "manuelbras@beaver.com", "tester", "Qwerty_1", "Qwerty_1", "photo.png", profile);

        LocalDate startDateMb = LocalDate.of(2022, 1, 1);
        LocalDate endDateMb = LocalDate.of(2022, 1, 31);
        Resource resource = new Resource(user, startDateMb, endDateMb, new CostPerHour(100), new PercentageOfAllocation(.5));

        String taskDescription = "must be at least 20 characters";
        TaskType taskType = company.getTaskTypeStore().getTypeByDescription("Testing");
        Task task = new Task("test", taskDescription, 8.00, taskType, resource);

        Date effortDate = new Date(LocalDate.of(2022, 1, 20));
        //Act
        task.createAndSaveTaskEffort(9, 0, effortDate, "test", ".pdf");
        //Assert
        assertEquals(0.0, task.getEffortRemaining());
        assertEquals(9.0, task.getHoursSpent());
        assertEquals(1.0, task.getExecutionPercentage());
    }

    @Test
    void hasTypeStatusResponsibleTestSuccess() {
        //Arrange
        Company company = new Company();
        UserProfile profile = company.getUserProfileStore().getUserProfile("Visitor");
        SystemUser user = new SystemUser("manuelbras", "manuelbras@beaver.com", "tester", "Qwerty_1", "Qwerty_1", "photo.png", profile);
        LocalDate startDateMb = LocalDate.of(2022, 1, 1);
        LocalDate endDateMb = LocalDate.of(2022, 1, 31);
        Resource resource = new Resource(user, startDateMb, endDateMb, new CostPerHour(100), new PercentageOfAllocation(.5));
        String taskDescription = "must be at least 20 characters";
        TaskType taskType = company.getTaskTypeStore().getTypeByDescription("Testing");
        Task task = new Task("test", taskDescription, 20.00, taskType, resource);
        //Assert
        assertTrue(task.hasType(taskType));
        //Act
        assertTrue(task.hasStatus(company.getTaskStatusStore().getInitialStatus()));
        assertTrue(task.hasResponsible(resource));
        assertTrue(task.hasName("test"));
    }

    @Test
    void constructorCheckNameEmptyTest() {
        //Arrange
        Company company = new Company();
        UserProfile profile = company.getUserProfileStore().getUserProfile("Visitor");
        SystemUser user = new SystemUser("manuelbras", "manuelbras@beaver.com", "tester", "Qwerty_1", "Qwerty_1", "photo.png", profile);
        LocalDate startDateMb = LocalDate.of(2022, 1, 1);
        LocalDate endDateMb = LocalDate.of(2022, 1, 31);
        Resource resource = new Resource(user, startDateMb, endDateMb, new CostPerHour(100), new PercentageOfAllocation(.5));
        String taskDescription = "must be at least 20 characters";
        TaskType taskType = company.getTaskTypeStore().getTypeByDescription("Testing");

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
        SystemUser user = new SystemUser("manuelbras", "manuelbras@beaver.com", "tester", "Qwerty_1", "Qwerty_1", "photo.png", profile);
        LocalDate startDateMb = LocalDate.of(2022, 1, 1);
        LocalDate endDateMb = LocalDate.of(2022, 1, 31);
        Resource resource = new Resource(user, startDateMb, endDateMb, new CostPerHour(100), new PercentageOfAllocation(.5));
        String taskDescription = "must be at least 20 characters";
        TaskType taskType = company.getTaskTypeStore().getTypeByDescription("Testing");

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
        SystemUser user = new SystemUser("manuelbras", "manuelbras@beaver.com", "tester", "Qwerty_1", "Qwerty_1", "photo.png", profile);
        LocalDate startDateMb = LocalDate.of(2022, 1, 1);
        LocalDate endDateMb = LocalDate.of(2022, 1, 31);
        Resource resource = new Resource(user, startDateMb, endDateMb, new CostPerHour(100), new PercentageOfAllocation(.5));
        String taskDescription = "must be at least 20 characters";
        TaskType taskType = company.getTaskTypeStore().getTypeByDescription("Testing");

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
        SystemUser user = new SystemUser("manuelbras", "manuelbras@beaver.com", "tester", "Qwerty_1", "Qwerty_1", "photo.png", profile);
        LocalDate startDateMb = LocalDate.of(2022, 1, 1);
        LocalDate endDateMb = LocalDate.of(2022, 1, 31);
        Resource resource = new Resource(user, startDateMb, endDateMb, new CostPerHour(100), new PercentageOfAllocation(.5));
        String taskDescription = "must be at least 20 characters";
        TaskType taskType = company.getTaskTypeStore().getTypeByDescription("Testing");
        //Act
        Task task = new Task("tttr", taskDescription, 20.00, taskType, resource);
        //Assert
        assertEquals("tttr", task.getName().getNameF());

    }


    @Test
    void constructorCheckDescriptionEmptyTest() {
        //Arrange

        //UserProfile
        UserProfile profile = mock(UserProfile.class);
        Description description = mock(Description.class);
        when(profile.getUserProfileName()).thenReturn(description);
        when(description.getText()).thenReturn("Visitor");
        //SystemUser
        SystemUser user = mock(SystemUser.class);
        Name name = mock(Name.class);
        when(user.getUserName()).thenReturn(name);
        when(name.getNameF()).thenReturn("manuelbras");
        Email email = mock(Email.class);
        when(user.getEmail()).thenReturn(email);
        when(email.getEmail()).thenReturn("manuelbras@beaver.com");
        Function function = mock(Function.class);
        when(user.getFunction()).thenReturn(function);
        when(function.getText()).thenReturn("tester");
        Password password = mock(Password.class);
        when(user.getPassword()).thenReturn(password);
        when(password.getPwd()).thenReturn("Qwerty_1");
        Photo photo = mock(Photo.class);
        when(user.getPhoto()).thenReturn(photo);
        when(user.getPhoto().getExtension()).thenReturn("photo.png");
        //Resource
        Resource resource = mock(Resource.class);
        when(resource.getUser()).thenReturn(user);
        LocalDate startDateMb = LocalDate.of(2022, 1, 1);
        LocalDate endDateMb = LocalDate.of(2022, 1, 31);
        when(resource.getStartDate()).thenReturn(startDateMb);
        when(resource.getEndDate()).thenReturn(endDateMb);
        CostPerHour costPerHour = mock(CostPerHour.class);
        when(resource.getCostPerHour()).thenReturn(costPerHour);
        when(costPerHour.getCost()).thenReturn(100.00);
        PercentageOfAllocation percentageOfAllocation = mock(PercentageOfAllocation.class);
        when(resource.getPercentageOfAllocation()).thenReturn(percentageOfAllocation);
        when(percentageOfAllocation.getPercentage()).thenReturn(0.5);
        //TaskType
        TaskType taskType = mock(TaskType.class);
        Description descType = mock(Description.class);
        when(taskType.getDescription()).thenReturn(descType);
        when(descType.getText()).thenReturn("Testing");
        //TaskStatus
        TaskStatus taskStatus = mock(TaskStatus.class);
        Description descTaskStatys = mock(Description.class);
        when(taskStatus.getDescription()).thenReturn(descTaskStatys);
        when(descTaskStatys.getText()).thenReturn("Running");
        String taskDescription = "";

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
        SystemUser user = new SystemUser("manuelbras", "manuelbras@beaver.com", "tester", "Qwerty_1", "Qwerty_1", "photo.png", profile);
        LocalDate startDateMb = LocalDate.of(2022, 1, 1);
        LocalDate endDateMb = LocalDate.of(2022, 1, 31);
        Resource resource = new Resource(user, startDateMb, endDateMb, new CostPerHour(100), new PercentageOfAllocation(.5));
        String taskDescription = "";
        TaskType taskType = company.getTaskTypeStore().getTypeByDescription("Testing");

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
        SystemUser user = new SystemUser("manuelbras", "manuelbras@beaver.com", "tester", "Qwerty_1", "Qwerty_1", "photo.png", profile);
        LocalDate startDateMb = LocalDate.of(2022, 1, 1);
        LocalDate endDateMb = LocalDate.of(2022, 1, 31);
        Resource resource = new Resource(user, startDateMb, endDateMb, new CostPerHour(100), new PercentageOfAllocation(.5));
        String taskDescription = "must be at least 20 characters";
        TaskType taskType = company.getTaskTypeStore().getTypeByDescription("Testing");
        Task test = new Task("test", taskDescription, 20.00, taskType, resource);
        //Asserts
        assertThrows(IllegalArgumentException.class, () -> {
            //Arrange
            test.setIdTask(-1);
        });
    }


    @Test
    void checkEffortRulesEqual0() {
        //Asserts
        assertThrows(IllegalArgumentException.class, () -> {
            //Arrange

            //UserProfile
            UserProfile profile = mock(UserProfile.class);
            Description description = mock(Description.class);
            when(profile.getUserProfileName()).thenReturn(description);
            when(description.getText()).thenReturn("Visitor");
            //SystemUser
            SystemUser user = mock(SystemUser.class);
            Name name = mock(Name.class);
            when(user.getUserName()).thenReturn(name);
            when(name.getNameF()).thenReturn("manuelbras");
            Email email = mock(Email.class);
            when(user.getEmail()).thenReturn(email);
            when(email.getEmail()).thenReturn("manuelbras@beaver.com");
            Function function = mock(Function.class);
            when(user.getFunction()).thenReturn(function);
            when(function.getText()).thenReturn("tester");
            Password password = mock(Password.class);
            when(user.getPassword()).thenReturn(password);
            when(password.getPwd()).thenReturn("Qwerty_1");
            Photo photo = mock(Photo.class);
            when(user.getPhoto()).thenReturn(photo);
            when(user.getPhoto().getExtension()).thenReturn("photo.png");
            //Resource
            Resource resource = mock(Resource.class);
            when(resource.getUser()).thenReturn(user);
            LocalDate startDateMb = LocalDate.of(2022, 1, 1);
            LocalDate endDateMb = LocalDate.of(2022, 1, 31);
            when(resource.getStartDate()).thenReturn(startDateMb);
            when(resource.getEndDate()).thenReturn(endDateMb);
            CostPerHour costPerHour = mock(CostPerHour.class);
            when(resource.getCostPerHour()).thenReturn(costPerHour);
            when(costPerHour.getCost()).thenReturn(100.00);
            PercentageOfAllocation percentageOfAllocation = mock(PercentageOfAllocation.class);
            when(resource.getPercentageOfAllocation()).thenReturn(percentageOfAllocation);
            when(percentageOfAllocation.getPercentage()).thenReturn(0.5);
            //TaskType
            TaskType taskType = mock(TaskType.class);
            Description descType = mock(Description.class);
            when(taskType.getDescription()).thenReturn(descType);
            when(descType.getText()).thenReturn("Testing");
            //TaskStatus
            TaskStatus taskStatus = mock(TaskStatus.class);
            Description descTaskStatys = mock(Description.class);
            when(taskStatus.getDescription()).thenReturn(descTaskStatys);
            when(descTaskStatys.getText()).thenReturn("Running");
            String taskDescription = "must be at least 20 characters";
            //Act
            new Task("test", taskDescription, 0.00, taskType, resource);
        });
    }

    @Test
    void checkEffortRulesLessthan0() {
        //Arrange

        //UserProfile
        UserProfile profile = mock(UserProfile.class);
        Description description = mock(Description.class);
        when(profile.getUserProfileName()).thenReturn(description);
        when(description.getText()).thenReturn("Visitor");
        //SystemUser
        SystemUser user = mock(SystemUser.class);
        Name name = mock(Name.class);
        when(user.getUserName()).thenReturn(name);
        when(name.getNameF()).thenReturn("manuelbras");
        Email email = mock(Email.class);
        when(user.getEmail()).thenReturn(email);
        when(email.getEmail()).thenReturn("manuelbras@beaver.com");
        Function function = mock(Function.class);
        when(user.getFunction()).thenReturn(function);
        when(function.getText()).thenReturn("tester");
        Password password = mock(Password.class);
        when(user.getPassword()).thenReturn(password);
        when(password.getPwd()).thenReturn("Qwerty_1");
        Photo photo = mock(Photo.class);
        when(user.getPhoto()).thenReturn(photo);
        when(user.getPhoto().getExtension()).thenReturn("photo.png");
        //Resource
        Resource resource = mock(Resource.class);
        when(resource.getUser()).thenReturn(user);
        LocalDate startDateMb = LocalDate.of(2022, 1, 1);
        LocalDate endDateMb = LocalDate.of(2022, 1, 31);
        when(resource.getStartDate()).thenReturn(startDateMb);
        when(resource.getEndDate()).thenReturn(endDateMb);
        CostPerHour costPerHour = mock(CostPerHour.class);
        when(resource.getCostPerHour()).thenReturn(costPerHour);
        when(costPerHour.getCost()).thenReturn(100.00);
        PercentageOfAllocation percentageOfAllocation = mock(PercentageOfAllocation.class);
        when(resource.getPercentageOfAllocation()).thenReturn(percentageOfAllocation);
        when(percentageOfAllocation.getPercentage()).thenReturn(0.5);
        //TaskType
        TaskType taskType = mock(TaskType.class);
        Description descType = mock(Description.class);
        when(taskType.getDescription()).thenReturn(descType);
        when(descType.getText()).thenReturn("Testing");
        //TaskStatus
        TaskStatus taskStatus = mock(TaskStatus.class);
        Description descTaskStatys = mock(Description.class);
        when(taskStatus.getDescription()).thenReturn(descTaskStatys);
        when(descTaskStatys.getText()).thenReturn("Running");
        String taskDescription = "must be at least 20 characters";

        //Asserts
        assertThrows(IllegalArgumentException.class, () -> {
            //Arrange
            new Task("test", taskDescription, -1.00, taskType, resource);
        });
    }

    @Test
    void checkEffortRulesGreaterThan0() {
        //Arrange

        //UserProfile
        UserProfile profile = mock(UserProfile.class);
        Description description = mock(Description.class);
        when(profile.getUserProfileName()).thenReturn(description);
        when(description.getText()).thenReturn("Visitor");
        //SystemUser
        SystemUser user = mock(SystemUser.class);
        Name name = mock(Name.class);
        when(user.getUserName()).thenReturn(name);
        when(name.getNameF()).thenReturn("manuelbras");
        Email email = mock(Email.class);
        when(user.getEmail()).thenReturn(email);
        when(email.getEmail()).thenReturn("manuelbras@beaver.com");
        Function function = mock(Function.class);
        when(user.getFunction()).thenReturn(function);
        when(function.getText()).thenReturn("tester");
        Password password = mock(Password.class);
        when(user.getPassword()).thenReturn(password);
        when(password.getPwd()).thenReturn("Qwerty_1");
        Photo photo = mock(Photo.class);
        when(user.getPhoto()).thenReturn(photo);
        when(user.getPhoto().getExtension()).thenReturn("photo.png");
        //Resource
        Resource resource = mock(Resource.class);
        when(resource.getUser()).thenReturn(user);
        LocalDate startDateMb = LocalDate.of(2022, 1, 1);
        LocalDate endDateMb = LocalDate.of(2022, 1, 31);
        when(resource.getStartDate()).thenReturn(startDateMb);
        when(resource.getEndDate()).thenReturn(endDateMb);
        CostPerHour costPerHour = mock(CostPerHour.class);
        when(resource.getCostPerHour()).thenReturn(costPerHour);
        when(costPerHour.getCost()).thenReturn(100.00);
        PercentageOfAllocation percentageOfAllocation = mock(PercentageOfAllocation.class);
        when(resource.getPercentageOfAllocation()).thenReturn(percentageOfAllocation);
        when(percentageOfAllocation.getPercentage()).thenReturn(0.5);
        //TaskType
        TaskType taskType = mock(TaskType.class);
        Description descType = mock(Description.class);
        when(taskType.getDescription()).thenReturn(descType);
        when(descType.getText()).thenReturn("Testing");
        //TaskStatus
        TaskStatus taskStatus = mock(TaskStatus.class);
        Description descTaskStatys = mock(Description.class);
        when(taskStatus.getDescription()).thenReturn(descTaskStatys);
        when(descTaskStatys.getText()).thenReturn("Running");
        String taskDescription = "must be at least 20 characters";

        //Act
        Task task = new Task("Test", taskDescription, 1.00, taskType, resource);
        //Assert
        assertEquals("Test", task.getName().getNameF());

    }

    @Test
    public void overrideAndHashCodeTest() {
        //Arrange
        Company company = new Company();
        UserProfile profile = company.getUserProfileStore().getUserProfile("Visitor");
        SystemUser user = new SystemUser("manuelbras", "manuelbras@beaver.com", "tester", "Qwerty_1", "Qwerty_1", "photo.png", profile);
        SystemUser user2 = new SystemUser("Cris", "manuelbras@beaver.com", "tester", "Qwerty_1", "Qwerty_1", "photo.png", profile);
        LocalDate startDateMb = LocalDate.of(2022, 1, 1);
        LocalDate endDateMb = LocalDate.of(2022, 1, 31);
        Resource resource = new Resource(user, startDateMb, endDateMb, new CostPerHour(100), new PercentageOfAllocation(.5));
        Resource resource2 = new Resource(user2, startDateMb, endDateMb, new CostPerHour(100), new PercentageOfAllocation(.5));
        String taskDescription = "must be at least 20 characters";
        TaskType taskType = company.getTaskTypeStore().getTypeByDescription("Testing");
        Task task1 = new Task("test", taskDescription, 20.00, taskType, resource);
        Task task2 = new Task("test", taskDescription, 20.00, taskType, resource);
        Task task3 = new Task("testtres", taskDescription, 20.00, taskType, resource);
        TaskStatus taskStatus = new TaskStatus("Cris");
        TaskType taskType1 = new TaskType("Cris");
        Resource resource1 = new Resource(resource2);


        //Assert
        assertNotSame(task1, task2);
        assertEquals(task1, task2);
        assertEquals(task1.hashCode(), task2.hashCode());
        assertEquals(task1.hasName("test"), task2.hasName("test"));
        assertNotEquals(task1, task3);
        assertNotEquals(task1.hashCode(), task3.hashCode());
        assertFalse(task1.hasName("cris"));
        assertFalse(task1.hasStatus(taskStatus));
        assertFalse(task1.hasType(taskType1));
        assertFalse(task1.hasResponsible(resource1));
    }

    @Test
    public void updateHoursSpent() {
        //Arrange
        Company company = new Company();
        UserProfile profile = company.getUserProfileStore().getUserProfile("Visitor");
        SystemUser user = new SystemUser("manuelbras", "manuelbras@beaver.com", "tester", "Qwerty_1", "Qwerty_1", "photo.png", profile);

        LocalDate startDateMb = LocalDate.of(2022, 1, 1);
        LocalDate endDateMb = LocalDate.of(2022, 1, 31);
        Resource resource = new Resource(user, startDateMb, endDateMb, new CostPerHour(100), new PercentageOfAllocation(.5));

        String taskDescription = "must be at least 20 characters";
        TaskType taskType = company.getTaskTypeStore().getTypeByDescription("Testing");
        Task task = new Task("test", taskDescription, 8.00, taskType, resource);
        Task task2 = new Task("test", taskDescription, 8.00, taskType, resource);

        Date effortDate = new Date(LocalDate.of(2022, 1, 20));
        task.createAndSaveTaskEffort(8, 0, effortDate, "test", ".pdf");

        company.getTaskStatusStore().getTaskStatusByDescription("Running");
        task.updateHoursSpent(task.getTaskEffortList().get(0));
        task2.updateEffortRemaining(task.getTaskEffortList().get(0));
        //Act

        //Assert
        assertEquals(0.0, task.getEffortRemaining());
        assertEquals(16.0, task.getHoursSpent());
        assertEquals(1.0, task.getExecutionPercentage());
        assertEquals(24.0, task.updateHoursSpent(task.getTaskEffortList().get(0)));
        assertEquals(0.0, task2.updateEffortRemaining(task.getTaskEffortList().get(0)));
    }

    @Test
    public void validateTaskEffortNull() {
        //Arrange & act
        TaskEffort taskEffort = null;
        //Assert
        assertNull(taskEffort);
    }

}