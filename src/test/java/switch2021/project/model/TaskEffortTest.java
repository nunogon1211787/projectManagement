package switch2021.project.model;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class TaskEffortTest {

    @Test
    public void createTaskEffortSuccess() {
        //Arrange
        Company company = new Company();
        UserProfile profile = company.getUserProfileStore().getUserProfile("Visitor");
        SystemUser user = new SystemUser("manueloliveira", "manueloliveira@beaver.com", "developer", "ghi", "ghi", "photo", profile);
        LocalDate startDateMb = LocalDate.of(2022, 1, 1);
        LocalDate endDateMb = LocalDate.of(2022, 1, 31);
        Resource resource = new Resource(user, startDateMb, endDateMb, 100, .5);

        int effortHours = 4;
        int effortMinutes = 30;
        LocalDate effortDate = LocalDate.of(2022, 1, 27);
        String comment = "design";
        String attachment = "photo";
        //Act
        TaskEffort taskEffort = new TaskEffort(effortHours, effortMinutes, effortDate, comment, attachment);
        //Assert
        assertEquals(effortHours, taskEffort.getEffortHours());
        assertEquals(effortMinutes, taskEffort.getEffortMinutes());
        assertEquals(effortDate, taskEffort.getEffortDate());
        assertEquals(comment, taskEffort.getComment());
        assertEquals(attachment, taskEffort.getAttachment());
    }

    @Test //create effort with no comments or attachments
    public void createTaskEffortSuccessOptionalValuesEmpty() {
        //Arrange
        Company company = new Company();
        UserProfile profile = company.getUserProfileStore().getUserProfile("Visitor");
        SystemUser user = new SystemUser("manueloliveira", "manueloliveira@beaver.com", "developer", "ghi", "ghi", "photo", profile);
        LocalDate startDateMb = LocalDate.of(2022, 1, 1);
        LocalDate endDateMb = LocalDate.of(2022, 1, 31);
        Resource resource = new Resource(user, startDateMb, endDateMb, 100, .5);

        int effortHours = 4;
        int effortMinutes = 30;
        LocalDate effortDate = LocalDate.of(2022, 1, 27);
        String comment = "";
        String attachment = "";
        //Act
        TaskEffort taskEffort = new TaskEffort(effortHours, effortMinutes, effortDate, comment, attachment);
        //Assert
        assertEquals(effortHours, taskEffort.getEffortHours());
        assertEquals(effortMinutes, taskEffort.getEffortMinutes());
        assertEquals(effortDate, taskEffort.getEffortDate());
        assertEquals(comment, taskEffort.getComment());
        assertEquals(attachment, taskEffort.getAttachment());
    }

    @Test //no date entered -> assigns the date of today
    public void createTaskEffortSuccessWorkDateInputValueEmpty() {
        //Arrange
        Company company = new Company();
        UserProfile profile = company.getUserProfileStore().getUserProfile("Visitor");
        SystemUser user = new SystemUser("manueloliveira", "manueloliveira@beaver.com", "developer", "ghi", "ghi", "photo", profile);
        LocalDate startDateMb = LocalDate.of(2022, 1, 1);
        LocalDate endDateMb = LocalDate.of(2022, 1, 31);
        Resource resource = new Resource(user, startDateMb, endDateMb, 100, .5);

        int effortHours = 4;
        int effortMinutes = 30;
        LocalDate effortDate = null;
        String comment = "design";
        String attachment = "";
        //Act
        TaskEffort taskEffort = new TaskEffort(effortHours, effortMinutes, effortDate, comment, attachment);
        //Assert
        assertEquals(effortHours, taskEffort.getEffortHours());
        assertEquals(effortMinutes, taskEffort.getEffortMinutes());
        assertEquals(LocalDate.now(), taskEffort.getEffortDate());
        assertEquals(comment, taskEffort.getComment());
        assertEquals(attachment, taskEffort.getAttachment());
    }

    @Test
    public void createTaskEffortSuccessWithOnlyMinutes() {
        //Arrange
        Company company = new Company();
        UserProfile profile = company.getUserProfileStore().getUserProfile("Visitor");
        SystemUser user = new SystemUser("manueloliveira", "manueloliveira@beaver.com", "developer", "ghi", "ghi", "photo", profile);
        LocalDate startDateMb = LocalDate.of(2022, 1, 1);
        LocalDate endDateMb = LocalDate.of(2022, 1, 31);
        Resource resource = new Resource(user, startDateMb, endDateMb, 100, .5);

        int effortHours = 0;
        int effortMinutes = 30;
        LocalDate effortDate = LocalDate.of(2022, 1, 27);
        String comment = "design";
        String attachment = "photo";
        //Act
        TaskEffort taskEffort = new TaskEffort(effortHours, effortMinutes, effortDate, comment, attachment);
        //Assert
        assertEquals(effortHours, taskEffort.getEffortHours());
        assertEquals(effortMinutes, taskEffort.getEffortMinutes());
        assertEquals(effortDate, taskEffort.getEffortDate());
        assertEquals(comment, taskEffort.getComment());
        assertEquals(attachment, taskEffort.getAttachment());
    }

    @Test
    public void createTaskEffortSuccessWithOnlyHours() {
        //Arrange
        Company company = new Company();
        UserProfile profile = company.getUserProfileStore().getUserProfile("Visitor");
        SystemUser user = new SystemUser("manueloliveira", "manueloliveira@beaver.com", "developer", "ghi", "ghi", "photo", profile);
        LocalDate startDateMb = LocalDate.of(2022, 1, 1);
        LocalDate endDateMb = LocalDate.of(2022, 1, 31);
        Resource resource = new Resource(user, startDateMb, endDateMb, 100, .5);

        int effortHours = 4;
        int effortMinutes = 0;
        LocalDate effortDate = LocalDate.of(2022, 1, 27);
        String comment = "design";
        String attachment = "photo";
        //Act
        TaskEffort taskEffort = new TaskEffort(effortHours, effortMinutes, effortDate, comment, attachment);
        //Assert
        assertEquals(effortHours, taskEffort.getEffortHours());
        assertEquals(effortMinutes, taskEffort.getEffortMinutes());
        assertEquals(effortDate, taskEffort.getEffortDate());
        assertEquals(comment, taskEffort.getComment());
        assertEquals(attachment, taskEffort.getAttachment());
    }

    //Fails
    @Test
    public void createTaskEffortFailWorkHoursNegative() {
        //Assert
        assertThrows(IllegalArgumentException.class, () -> {
            //Arrange
            Company company = new Company();
            UserProfile profile = company.getUserProfileStore().getUserProfile("Visitor");
            SystemUser user = new SystemUser("manueloliveira", "manueloliveira@beaver.com", "developer", "ghi", "ghi", "photo", profile);
            LocalDate startDateMb = LocalDate.of(2022, 1, 1);
            LocalDate endDateMb = LocalDate.of(2022, 1, 31);
            Resource resource = new Resource(user, startDateMb, endDateMb, 100, .5);

            int effortHours = -4;
            int effortMinutes = 30;
            LocalDate effortDate = LocalDate.of(2022, 1, 27);
            String comment = "design";
            String attachment = "";
            //Act
            TaskEffort taskEffort = new TaskEffort(effortHours, effortMinutes, effortDate, comment, attachment);
        });
    }

    @Test
    public void createTaskEffortFailWorkMinutesNegative() {
        //Assert
        assertThrows(IllegalArgumentException.class, () -> {
            //Arrange
            Company company = new Company();
            UserProfile profile = company.getUserProfileStore().getUserProfile("Visitor");
            SystemUser user = new SystemUser("manueloliveira", "manueloliveira@beaver.com", "developer", "ghi", "ghi", "photo", profile);
            LocalDate startDateMb = LocalDate.of(2022, 1, 1);
            LocalDate endDateMb = LocalDate.of(2022, 1, 31);
            Resource resource = new Resource(user, startDateMb, endDateMb, 100, .5);

            int effortHours = 4;
            int effortMinutes = -30;
            LocalDate effortDate = LocalDate.of(2022, 1, 27);
            String comment = "design";
            String attachment = "";
            //Act
            TaskEffort taskEffort = new TaskEffort(effortHours, effortMinutes, effortDate, comment, attachment);
        });
    }

    @Test
    public void createTaskEffortFailNoWorkingTime() {
        //Assert
        assertThrows(IllegalArgumentException.class, () -> {
            //Arrange
            Company company = new Company();
            UserProfile profile = company.getUserProfileStore().getUserProfile("Visitor");
            SystemUser user = new SystemUser("manueloliveira", "manueloliveira@beaver.com", "developer", "ghi", "ghi", "photo", profile);
            LocalDate startDateMb = LocalDate.of(2022, 1, 1);
            LocalDate endDateMb = LocalDate.of(2022, 1, 31);
            Resource resource = new Resource(user, startDateMb, endDateMb, 100, .5);

            int effortHours = 0;
            int effortMinutes = 0;
            LocalDate effortDate = LocalDate.of(2022, 1, 27);
            String comment = "design";
            String attachment = "";
            //Act
            TaskEffort taskEffort = new TaskEffort(effortHours, effortMinutes, effortDate, comment, attachment);
        });
    }

    @Test
    public void createTaskEffortFailWorkingHoursMoreThanADay() {
        //Assert
        assertThrows(IllegalArgumentException.class, () -> {
            //Arrange
            Company company = new Company();
            UserProfile profile = company.getUserProfileStore().getUserProfile("Visitor");
            SystemUser user = new SystemUser("manueloliveira", "manueloliveira@beaver.com", "developer", "ghi", "ghi", "photo", profile);
            LocalDate startDateMb = LocalDate.of(2022, 1, 1);
            LocalDate endDateMb = LocalDate.of(2022, 1, 31);
            Resource resource = new Resource(user, startDateMb, endDateMb, 100, .5);

            int effortHours = 25;
            int effortMinutes = 10;
            LocalDate effortDate = LocalDate.of(2022, 1, 27);
            String comment = "design";
            String attachment = "";
            //Act
            TaskEffort taskEffort = new TaskEffort(effortHours, effortMinutes, effortDate, comment, attachment);
        });
    }

    @Test
    public void createTaskEffortFailWorkingMinutesMoreThanAHour() {
        //Assert
        assertThrows(IllegalArgumentException.class, () -> {
            //Arrange
            Company company = new Company();
            UserProfile profile = company.getUserProfileStore().getUserProfile("Visitor");
            SystemUser user = new SystemUser("manueloliveira", "manueloliveira@beaver.com", "developer", "ghi", "ghi", "photo", profile);
            LocalDate startDateMb = LocalDate.of(2022, 1, 1);
            LocalDate endDateMb = LocalDate.of(2022, 1, 31);
            Resource resource = new Resource(user, startDateMb, endDateMb, 100, .5);

            int effortHours = 0;
            int effortMinutes = 61;
            LocalDate effortDate = LocalDate.of(2022, 1, 27);
            String comment = "design";
            String attachment = "";
            //Act
            TaskEffort taskEffort = new TaskEffort(effortHours, effortMinutes, effortDate, comment, attachment);
        });
    }

    @Test //register effort is in the future
    public void createTaskEffortFailWorkDateAfterToday() {
        //Assert
        assertThrows(IllegalArgumentException.class, () -> {
            //Arrange
            Company company = new Company();
            UserProfile profile = company.getUserProfileStore().getUserProfile("Visitor");
            SystemUser user = new SystemUser("manueloliveira", "manueloliveira@beaver.com", "developer", "ghi", "ghi", "photo", profile);
            LocalDate startDateMb = LocalDate.of(2022, 1, 1);
            LocalDate endDateMb = LocalDate.of(2022, 1, 31);
            Resource resource = new Resource(user, startDateMb, endDateMb, 100, .5);

            int effortHours = 4;
            int effortMinutes = 30;
            LocalDate effortDate = LocalDate.now().plusDays(1);
            String comment = "design";
            String attachment = "";
            //Act
            TaskEffort taskEffort = new TaskEffort(effortHours, effortMinutes, effortDate, comment, attachment);
        });
    }

}
