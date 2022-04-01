package switch2021.project.model.UserStory;

import org.junit.jupiter.api.Test;
import switch2021.project.model.Sprint.Sprint;
import switch2021.project.model.Task.Task;
import switch2021.project.model.valueObject.*;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class UserStoryTest {

    @Test
    void ValidateInfoUserStory() {
        //Arrange
        //Act
        UserStory userStory = new UserStory("As a PO, i want to test this string", 1, "As a PO, i want to test this string - Test", 40);
        //Assert
        assertEquals("As a PO, i want to test this string", userStory.getTitle().getTitleUs());
        assertEquals("As a PO, i want to test this string - Test", userStory.getDescription().getText());
    }


    @Test
    void setPriorityTest() {
        UserStory userStory = new UserStory("As a PO, i want to test this string", 2, "Fazer tal", 5);
        userStory.setPriority(4);
        assertEquals(4, userStory.getPriority().getPriorityUs());
    }


    @Test
    void hasCodeTest() {
        UserStory userStory = new UserStory("As a PO, i want to test this string", 2, "Fazer tal", 5);

        boolean expected = userStory.hasCode(userStory.getIdUserStory());
        assertTrue(expected);
    }

    @Test
    void hasCodeTest2() {
        UserStory userStory = new UserStory("As a PO, i want to test this string", 2, "Fazer tal", 5);

        boolean expected = userStory.hasCode(4);
        assertFalse(expected);
    }

    @Test
    void setDescriptionTest() {
        UserStory userStory = new UserStory("As a PO, i want to test this string", 2, "Fazer tal", 5);

        userStory.setDescription("Fazer coiso");
        assertEquals("Fazer coiso", userStory.getDescription().getText());
    }


    @Test
    void setParentUserStoryTest() {
        //Arrange
        UserStory userStory = new UserStory("As a PO, i want to test this string", 1, "US001 - Test", 40);
        UserStory userStory_parent = new UserStory("As a PO, i want to test this string", 1, "As a PO, i want to test this string - Test", 40);

        //Act
        userStory.setParentUserStory(userStory_parent);

        //Assert
        assertEquals(userStory.getParentUserStory(), userStory_parent);
    }

    @Test
    void setUserStoryStatusBooleanTest() {
        UserStory userStory = new UserStory("As a PO, i want to test this string", 2, "Fazer tal", 5);

        UserStoryStatus userStoryStatus = mock(UserStoryStatus.class);
        userStory.setUserStoryStatusBoolean(userStoryStatus);

        assertEquals(userStoryStatus, userStory.getUserStoryStatus());
    }

    @Test
    void setUpdateUserStoryWorkDoneTest() {
        //Arrange
        UserStory userStory = new UserStory("As a PO, i want to test this string", 1, "US001 - Test", 40);
        Task task = mock(Task.class);
        when(task.getHoursSpent()).thenReturn(12.0);

        //Act
        userStory.updateWorkDone(task);

        //Assert
        assertEquals(12, userStory.getWorkDone());
    }


    @Test
    void isValidUserStoryDescription() {
        assertThrows(IllegalArgumentException.class, () -> {
            Sprint sprint = new Sprint("Super", LocalDate.of(2022, 3, 1));
            UserStory userStory = new UserStory("As a PO, i want to test this string", 2, "", 5);
            sprint.saveUsInSprintBacklog(userStory);
        });
    }

    @Test
    void isValidUserStoryDescription2() {
        assertThrows(IllegalArgumentException.class, () -> {
            Sprint sprint = new Sprint("Super", LocalDate.of(2022, 3, 1));
            UserStory userStory = new UserStory("As a PO, i want to test this string", 2, "    ", 5);
            sprint.saveUsInSprintBacklog(userStory);
        });
    }


    @Test
    void isValidUserStoryName() {
        assertThrows(IllegalArgumentException.class, () -> {
            Sprint sprint = new Sprint("Super", LocalDate.of(2022, 3, 1));
            UserStory userStory = new UserStory("", 2, "Fazer tal", 5);
            sprint.saveUsInSprintBacklog(userStory);
        });
    }

    @Test
    void isValidUserStoryName2() {
        assertThrows(IllegalArgumentException.class, () -> {
            Sprint sprint = new Sprint("Super", LocalDate.of(2022, 3, 1));
            UserStory userStory = new UserStory("C", 2, "Fazer tal", 5);
            sprint.saveUsInSprintBacklog(userStory);
        });
    }

    @Test
    void isValidUserStoryName3() {
        assertThrows(IllegalArgumentException.class, () -> {
            Sprint sprint = new Sprint("Super", LocalDate.of(2022, 3, 1));
            UserStory userStory = new UserStory("   ", 2, "Fazer tal", 5);
            sprint.saveUsInSprintBacklog(userStory);
        });
    }

    @Test
    void isValidUserStoryName5() {
        assertThrows(IllegalArgumentException.class, () -> {
            Sprint sprint = new Sprint("Super", LocalDate.of(2022, 3, 1));
            UserStory userStory = new UserStory("CC", 2, "Fazer tal", 5);
            sprint.saveUsInSprintBacklog(userStory);
        });
    }

    @Test
    void hashCodeTest() {
        UserStory userStory1 = new UserStory("As a PO, i want to test this string", 1, "Fazer tal", 0);
        UserStory userStory2 = new UserStory("As a AO, i want to test this string", 2, "Fazer tal e coiso", 5);
        UserStory userStory3 = new UserStory("As a AO, i want to test this string", 2, "Fazer tal e coiso", 5);
        UserStoryStatus status4 = new UserStoryStatus("teste5", true);

        assertNotEquals(userStory1.hashCode(), userStory2.hashCode());
        assertEquals(0, userStory1.getTimeEstimate());
        assertEquals(userStory2.getDescription().getText(), userStory3.getDescription().getText());
        assertEquals(userStory2.getPriority().getPriorityUs(), userStory3.getPriority().getPriorityUs());
        assertEquals(userStory2.getTitle().getTitleUs(), userStory3.getTitle().getTitleUs());
        assertEquals(userStory2.getTimeEstimate(), userStory3.getTimeEstimate());
        assertNotEquals(userStory2.getDescription().getText(), userStory1.getDescription().getText());
        assertNotEquals(null, userStory1);
        assertEquals(userStory1.getClass(), userStory2.getClass());
        assertNotEquals(userStory1.hashCode(), userStory3.hashCode());
        assertEquals(0, userStory1.getIdUserStory());
        assertNotEquals(null, status4);
    }

    @Test
    void setID() {
        UserStory userStory1 = new UserStory("As a PO, i want to test this string", 2, "Fazer tal", 5);
        UserStory userStory2 = new UserStory("As a IO, i want to test this string", 2, "Fazer tal e coiso", 5);
        userStory2.setIdUserStory(2);

        assertEquals(2, userStory2.getIdUserStory());
        assertNotEquals(2, userStory1.getIdUserStory());
    }

    @Test
    void setPriorityTrue() {
        UserStory userStory1 = new UserStory("As a PO, i want to test this string", 2, "Fazer tal", 5);
        UserStory userStory2 = new UserStory("As a SO, i want to test this string", 2, "Fazer tal e coiso", 5);
        userStory2.setPriority(1);
        userStory1.setPriority(4);

        assertTrue(userStory1.setPriority(3));
        assertEquals(3, userStory1.getPriority().getPriorityUs());

    }

    @Test
    void setPriorityFalse() {
        UserStory userStory1 = new UserStory("As a PO, i want to test this string", 2, "Fazer tal", 5);
        UserStory userStory2 = new UserStory("As a AO, i want to test this string", 2, "Fazer tal e coiso", 5);
        userStory2.setPriority(1);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            userStory1.setPriority(6);
        });
        assertNotEquals(6, userStory1.getPriority());
        assertTrue(exception.getMessage().equals("Check priority, cannot be < 0 or superior to 5"));
    }

    @Test
    public void overrideTest() {
        //Arrange

        UserStory userStory1 = new UserStory("As coise, I want to tal", 4, "Fazer totil de cenas bueda fixes", 10);
        UserStory userStory2 = new UserStory("As coise, I want to tal", 4, "Fazer totil de cenas bueda fixes", 10);
        UserStory userStory3 = new UserStory("As coise, I want to tal12132", 4, "Fazer totil de cenas bueda fixes", 10);


        //Assert
        assertNotSame(userStory1, userStory2);
        assertEquals(userStory1, userStory2);
        assertEquals(userStory1.getTitle(), userStory2.getTitle());
        assertNotEquals(userStory1, userStory3);
        assertNotSame("saffdf", userStory1.getTitle().getTitleUs());
        assertFalse(userStory1.hasCode(213));
        assertTrue(userStory1.setPriority(2));

    }

}