package switch2021.project.model;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TaskEffortTest {

    @Test
    public void createTaskEffortSuccess() {
        //Arrange
        Company company = new Company();
        UserProfile profile = company.getUserProfileStore().getUserProfile("Visitor");
        SystemUser user = new SystemUser("manueloliveira", "manueloliveira@beaver.com", "developer", "ghi", "ghi", "photo", profile);

        int workHours = 4;
        int workMinutes = 30;
        LocalDate workDate = LocalDate.of(2022, 1, 27);
        String comment = "design";
        String attachment = "photo";
        //Act
        TaskEffort taskEffort = new TaskEffort(workHours, workMinutes, workDate, comment, attachment, user);
        //Assert
        assertEquals(workHours, taskEffort.getWorkHours());
        assertEquals(workMinutes, taskEffort.getWorkMinutes());
        assertEquals(workDate, taskEffort.getWorkDate());
        assertEquals(comment, taskEffort.getComment());
        assertEquals(attachment, taskEffort.getAttachment());
        assertEquals(user, taskEffort.getUser());
    }

    @Test //create effort with no comments or attachments
    public void createTaskEffortSuccessOptionalValuesEmpty() {
        //Arrange
        Company company = new Company();
        UserProfile profile = company.getUserProfileStore().getUserProfile("Visitor");
        SystemUser user = new SystemUser("manueloliveira", "manueloliveira@beaver.com", "developer", "ghi", "ghi", "photo", profile);

        int workHours = 4;
        int workMinutes = 30;
        LocalDate workDate = LocalDate.of(2022, 1, 27);
        String comment = "";
        String attachment = "";
        //Act
        TaskEffort taskEffort = new TaskEffort(workHours, workMinutes, workDate, comment, attachment, user);
        //Assert
        assertEquals(workHours, taskEffort.getWorkHours());
        assertEquals(workMinutes, taskEffort.getWorkMinutes());
        assertEquals(workDate, taskEffort.getWorkDate());
        assertEquals(comment, taskEffort.getComment());
        assertEquals(attachment, taskEffort.getAttachment());
        assertEquals(user, taskEffort.getUser());
    }

    @Test //no date entered -> assigns the date of today
    public void createTaskEffortSuccessWorkDateInputValueEmpty() {
        //Arrange
        Company company = new Company();
        UserProfile profile = company.getUserProfileStore().getUserProfile("Visitor");
        SystemUser user = new SystemUser("manueloliveira", "manueloliveira@beaver.com", "developer", "ghi", "ghi", "photo", profile);

        int workHours = 4;
        int workMinutes = 30;
        LocalDate workDate = null;
        String comment = "design";
        String attachment = "";
        //Act
        TaskEffort taskEffort = new TaskEffort(workHours, workMinutes, workDate, comment, attachment, user);
        //Assert
        assertEquals(workHours, taskEffort.getWorkHours());
        assertEquals(workMinutes, taskEffort.getWorkMinutes());
        assertEquals(LocalDate.now(), taskEffort.getWorkDate());
        assertEquals(comment, taskEffort.getComment());
        assertEquals(attachment, taskEffort.getAttachment());
        assertEquals(user, taskEffort.getUser());
    }

    @Test
    public void createTaskEffortSuccessWithOnlyMinutes() {
        //Arrange
        Company company = new Company();
        UserProfile profile = company.getUserProfileStore().getUserProfile("Visitor");
        SystemUser user = new SystemUser("manueloliveira", "manueloliveira@beaver.com", "developer", "ghi", "ghi", "photo", profile);

        int workHours = 0;
        int workMinutes = 30;
        LocalDate workDate = LocalDate.of(2022, 1, 27);
        String comment = "design";
        String attachment = "photo";
        //Act
        TaskEffort taskEffort = new TaskEffort(workHours, workMinutes, workDate, comment, attachment, user);
        //Assert
        assertEquals(workHours, taskEffort.getWorkHours());
        assertEquals(workMinutes, taskEffort.getWorkMinutes());
        assertEquals(workDate, taskEffort.getWorkDate());
        assertEquals(comment, taskEffort.getComment());
        assertEquals(attachment, taskEffort.getAttachment());
        assertEquals(user, taskEffort.getUser());
    }

    @Test
    public void createTaskEffortSuccessWithOnlyHours() {
        //Arrange
        Company company = new Company();
        UserProfile profile = company.getUserProfileStore().getUserProfile("Visitor");
        SystemUser user = new SystemUser("manueloliveira", "manueloliveira@beaver.com", "developer", "ghi", "ghi", "photo", profile);

        int workHours = 4;
        int workMinutes = 0;
        LocalDate workDate = LocalDate.of(2022, 1, 27);
        String comment = "design";
        String attachment = "photo";
        //Act
        TaskEffort taskEffort = new TaskEffort(workHours, workMinutes, workDate, comment, attachment, user);
        //Assert
        assertEquals(workHours, taskEffort.getWorkHours());
        assertEquals(workMinutes, taskEffort.getWorkMinutes());
        assertEquals(workDate, taskEffort.getWorkDate());
        assertEquals(comment, taskEffort.getComment());
        assertEquals(attachment, taskEffort.getAttachment());
        assertEquals(user, taskEffort.getUser());
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

            int workHours = -4;
            int workMinutes = 30;
            LocalDate workDate = LocalDate.of(2022, 1, 27);
            String comment = "design";
            String attachment = "";
            //Act
            TaskEffort taskEffort = new TaskEffort(workHours, workMinutes, workDate, comment, attachment, user);
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

            int workHours = 4;
            int workMinutes = -30;
            LocalDate workDate = LocalDate.of(2022, 1, 27);
            String comment = "design";
            String attachment = "";
            //Act
            TaskEffort taskEffort = new TaskEffort(workHours, workMinutes, workDate, comment, attachment, user);
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

            int workHours = 0;
            int workMinutes = 0;
            LocalDate workDate = LocalDate.of(2022, 1, 27);
            String comment = "design";
            String attachment = "";
            //Act
            TaskEffort taskEffort = new TaskEffort(workHours, workMinutes, workDate, comment, attachment, user);
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

            int workHours = 25;
            int workMinutes = 10;
            LocalDate workDate = LocalDate.of(2022, 1, 27);
            String comment = "design";
            String attachment = "";
            //Act
            TaskEffort taskEffort = new TaskEffort(workHours, workMinutes, workDate, comment, attachment, user);
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

            int workHours = 0;
            int workMinutes = 61;
            LocalDate workDate = LocalDate.of(2022, 1, 27);
            String comment = "design";
            String attachment = "";
            //Act
            TaskEffort taskEffort = new TaskEffort(workHours, workMinutes, workDate, comment, attachment, user);
        });
    }

    @Test //effort is in the future
    public void createTaskEffortFailWorkDateAfterToday() {
        //Assert
        assertThrows(IllegalArgumentException.class, () -> {
            //Arrange
            Company company = new Company();
            UserProfile profile = company.getUserProfileStore().getUserProfile("Visitor");
            SystemUser user = new SystemUser("manueloliveira", "manueloliveira@beaver.com", "developer", "ghi", "ghi", "photo", profile);

            int workHours = 4;
            int workMinutes = 30;
            LocalDate workDate = LocalDate.now().plusDays(1);
            String comment = "design";
            String attachment = "";
            //Act
            TaskEffort taskEffort = new TaskEffort(workHours, workMinutes, workDate, comment, attachment, user);
        });
    }

}
