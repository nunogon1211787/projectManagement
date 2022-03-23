package switch2021.project.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import switch2021.project.immutable.Date;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class UserStoryTest {

    @Test
    void ValidateInfoUserStory() {
        //Arrange
        Company company = new Company();
        //Act
        UserStory userStory = new UserStory("US001", 1, "US001 - Test", 40);
        //Assert
        assertEquals("US001", userStory.getTitle());
        assertEquals("US001 - Test", userStory.getDescription().getText());
    }


    @Test
    void setPriorityTest() {
        UserStory userStory = new UserStory("US001", 2, "Fazer tal", 5);
        userStory.setPriority(4);
        assertEquals(4, userStory.getPriority());
    }


    @Test
    void hasCodeTest() {
       UserStory userStory = new UserStory("US001", 2, "Fazer tal", 5);

        boolean expected = userStory.hasCode(userStory.getIdUserStory());
        assertTrue(expected);
    }

    @Test
    void hasCodeTest2() {
       UserStory userStory = new UserStory("US001", 2, "Fazer tal", 5);

        boolean expected = userStory.hasCode(4);
        assertFalse(expected);
    }

    @Test
    void setDescriptionTest() {
       UserStory userStory = new UserStory("US001", 2, "Fazer tal", 5);

        userStory.setDescription("Fazer coiso");
        assertEquals("Fazer coiso", userStory.getDescription().getText());
    }


    @Test
    void setParentUserStoryTest() {
        //Arrange
        UserStory userStory = new UserStory("US001", 1, "US001 - Test", 40);
        UserStory userStory_parent = new UserStory("US002", 1, "US002 - Test", 40);

        //Act
        userStory.setParentUserStory(userStory_parent);

        //Assert
        assertEquals(userStory.getParentUserStory(), userStory_parent);
    }

    @Test
    void setUserStoryStatusBooleanTest() {
        UserStory userStory = new UserStory("US001", 2, "Fazer tal", 5);

        UserStoryStatus userStoryStatus = mock(UserStoryStatus.class);
        userStory.setUserStoryStatusBoolean(userStoryStatus);

        assertEquals(userStoryStatus, userStory.getUserStoryStatus());
    }

    @Test
    void setUpdateUserStoryWorkDoneTest() {
        //Arrange
        UserStory userStory = new UserStory("US001", 1, "US001 - Test", 40);
        Task task = mock(Task.class);
        when(task.getHoursSpent()).thenReturn(12.0);

        //Act
        userStory.updateWorkDone(task);

        //Assert
        assertEquals(12, userStory.getWorkDone());
    }

    @Test
    void validatePriorityTestFail() {
      UserStory  userStory = new UserStory("US001", 2, "Fazer tal", 5);

        boolean expected = userStory.validatePriority(6);
        assertFalse(expected);
    }

    @Test
    void validatePriorityTestSuccess() {
      UserStory  userStory = new UserStory("US001", 2, "Fazer tal", 5);

        boolean expected = userStory.validatePriority(1);
        assertTrue(expected);
    }

    @Test
    void validatePriorityTestFailNegative() {
     UserStory   userStory = new UserStory("US001", 2, "Fazer tal", 5);

        boolean expected = userStory.validatePriority(-1);
        assertFalse(expected);
    }

    @Test
    void validatePriorityTestSuccessHighLimit() {
     UserStory   userStory = new UserStory("US001", 2, "Fazer tal", 5);

        boolean expected = userStory.validatePriority(5);
        assertTrue(expected);
    }

    @Test
    void validatePriorityTestSuccessLowLimit() {
      UserStory  userStory = new UserStory("US001", 2, "Fazer tal", 5);

        boolean expected = userStory.validatePriority(0);
        assertTrue(expected);
    }

    @Test
    void isValidUserStoryDescription() {
        assertThrows(IllegalArgumentException.class, () -> {
            Sprint sprint = new Sprint("Super", LocalDate.of(2022, 3, 1));
        UserStory    userStory = new UserStory("US001", 2, "", 5);
            sprint.getSprintBacklog().saveUserStoryToSprintBacklog(userStory);
        });
    }

    @Test
    void isValidUserStoryDescription2() {
        assertThrows(IllegalArgumentException.class, () -> {
            Sprint sprint = new Sprint("Super", LocalDate.of(2022, 3, 1));
          UserStory  userStory = new UserStory("US001", 2, "    ", 5);
            sprint.getSprintBacklog().saveUserStoryToSprintBacklog(userStory);
        });
    }


    @Test
    void isValidUserStoryName() {
        assertThrows(IllegalArgumentException.class, () -> {
            Sprint sprint = new Sprint("Super", LocalDate.of(2022, 3, 1));
       UserStory     userStory = new UserStory("", 2, "Fazer tal", 5);
            sprint.getSprintBacklog().saveUserStoryToSprintBacklog(userStory);
        });
    }

    @Test
    void isValidUserStoryName2() {
        assertThrows(IllegalArgumentException.class, () -> {
            Sprint sprint = new Sprint("Super", LocalDate.of(2022, 3, 1));
      UserStory      userStory = new UserStory("C", 2, "Fazer tal", 5);
            sprint.getSprintBacklog().saveUserStoryToSprintBacklog(userStory);
        });
    }

    @Test
    void isValidUserStoryName3() {
        assertThrows(IllegalArgumentException.class, () -> {
            Sprint sprint = new Sprint("Super", LocalDate.of(2022, 3, 1));
       UserStory     userStory = new UserStory("   ", 2, "Fazer tal", 5);
            sprint.getSprintBacklog().saveUserStoryToSprintBacklog(userStory);
        });
    }

    @Test
    void isValidUserStoryName4() {
        assertThrows(IllegalArgumentException.class, () -> {
            Sprint sprint = new Sprint("Super", LocalDate.of(2022, 3, 1));
         UserStory   userStory = new UserStory(null, 2, "Fazer tal", 5);
            sprint.getSprintBacklog().saveUserStoryToSprintBacklog(userStory);
        });
    }

    @Test
    void isValidUserStoryName5() {
        assertThrows(IllegalArgumentException.class, () -> {
            Sprint sprint = new Sprint("Super", LocalDate.of(2022, 3, 1));
       UserStory     userStory = new UserStory("CC", 2, "Fazer tal", 5);
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
        assertEquals(userStory2.getDescription().getText(), userStory3.getDescription().getText());
        assertEquals(userStory2.getPriority(), userStory3.getPriority());
        assertEquals(userStory2.getTitle(), userStory3.getTitle());
        assertEquals(userStory2.getTimeEstimate(), userStory3.getTimeEstimate());
        assertNotEquals(userStory2.getDescription().getText(), userStory1.getDescription().getText());
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