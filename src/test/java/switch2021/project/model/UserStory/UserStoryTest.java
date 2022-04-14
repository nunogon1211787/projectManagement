package switch2021.project.model.UserStory;

import org.junit.jupiter.api.Test;
import switch2021.project.model.Sprint.Sprint;
import switch2021.project.model.valueObject.*;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class UserStoryTest {

    @Test
    void ValidateInfoUserStory() {
        //Arrange & Act
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
        //Arrange
        UserStory userStory = new UserStory("As a PO, i want to test this string", 2, "Fazer tal", 5);
        //Act
        userStory.setDescription("Fazer coiso");
        //Assert
        assertEquals("Fazer coiso", userStory.getDescription().getText());
    }


    @Test
    void setParentUserStoryTest() {
        //Arrange
        UserStory userStory = new UserStory("As a PO, i want to test this string", 1, "US001 - Test", 40);
        UserStory userStory_parent = new UserStory("As a PO, i want to test this string", 1, "As a PO, i want to test this string - Test", 40);
        UserStory userStory_parent2 = new UserStory("As a PO, i want to test", 2, "make testes", 20);

        //Act
        userStory.setParentUserStory(userStory_parent);

        //Assert
        assertEquals(userStory.getParentUserStory(), userStory_parent);
        assertNotEquals(userStory.getParentUserStory(), userStory_parent2);
    }

    @Test
    void isValidUserStoryDescription() {
        assertThrows(IllegalArgumentException.class, () -> {
            Sprint sprint = mock(Sprint.class);
            Description description = mock(Description.class);
            when(sprint.getSprintName()).thenReturn(description);
            when(description.getText()).thenReturn("Super");
            when(sprint.getStartDate()).thenReturn(LocalDate.of(2022, 3, 1));

            UserStory userStory = new UserStory("As a PO, i want to test this string", 2, "", 5);
            sprint.saveUsInScrumBoard(userStory);
        });
    }

    @Test
    void isValidUserStoryDescription2() {
        assertThrows(IllegalArgumentException.class, () -> {
            Sprint sprint = mock(Sprint.class);
            Description description = mock(Description.class);
            when(sprint.getSprintName()).thenReturn(description);
            when(description.getText()).thenReturn("Super");
            when(sprint.getStartDate()).thenReturn(LocalDate.of(2022, 3, 1));

            UserStory userStory = new UserStory("As a PO, i want to test this string", 2, "    ", 5);
            sprint.saveUsInScrumBoard(userStory);
        });
    }


    @Test
    void isValidUserStoryName() {
        assertThrows(IllegalArgumentException.class, () -> {
            Sprint sprint = mock(Sprint.class);
            Description description = mock(Description.class);
            when(sprint.getSprintName()).thenReturn(description);
            when(description.getText()).thenReturn("Super");
            when(sprint.getStartDate()).thenReturn(LocalDate.of(2022, 3, 1));

            UserStory userStory = new UserStory("", 2, "Fazer tal", 5);
            sprint.saveUsInScrumBoard(userStory);
        });
    }

    @Test
    void isValidUserStoryName2() {
        assertThrows(IllegalArgumentException.class, () -> {
            Sprint sprint = mock(Sprint.class);
            Description description = mock(Description.class);
            when(sprint.getSprintName()).thenReturn(description);
            when(description.getText()).thenReturn("Super");
            when(sprint.getStartDate()).thenReturn(LocalDate.of(2022, 3, 1));


            UserStory userStory = new UserStory("C", 2, "Fazer tal", 5);
            sprint.saveUsInScrumBoard(userStory);
        });
    }

    @Test
    void isValidUserStoryName3() {
        assertThrows(IllegalArgumentException.class, () -> {
            Sprint sprint = mock(Sprint.class);
            Description description = mock(Description.class);
            when(sprint.getSprintName()).thenReturn(description);
            when(description.getText()).thenReturn("Super");
            when(sprint.getStartDate()).thenReturn(LocalDate.of(2022, 3, 1));

            UserStory userStory = new UserStory("   ", 2, "Fazer tal", 5);
            sprint.saveUsInScrumBoard(userStory);
        });
    }

    @Test
    void isValidUserStoryName5() {
        assertThrows(IllegalArgumentException.class, () -> {
            Sprint sprint = mock(Sprint.class);
            Description description = mock(Description.class);
            when(sprint.getSprintName()).thenReturn(description);
            when(description.getText()).thenReturn("Super");
            when(sprint.getStartDate()).thenReturn(LocalDate.of(2022, 3, 1));


            UserStory userStory = new UserStory("CC", 2, "Fazer tal", 5);
            sprint.saveUsInScrumBoard(userStory);
        });
    }

    @Test
    void hashCodeTest() {
        UserStory userStory1 = new UserStory("As a PO, i want to test this string", 1, "Fazer tal", 0);
        UserStory userStory2 = new UserStory("As a AO, i want to test this string", 2, "Fazer tal e coiso", 5);
        UserStory userStory3 = new UserStory("As a AO, i want to test this string", 2, "Fazer tal e coiso", 5);
       UserStoryStatus status4 = mock(UserStoryStatus.class);
       Description description = mock(Description.class);
       when(status4.getDescription()).thenReturn(description);
       when(description.getText()).thenReturn("teste5");
       when(status4.isSprintAvailable()).thenReturn(true);

        assertNotEquals(userStory1.hashCode(), userStory2.hashCode());
        assertEquals(0, userStory1.getTimeEstimate().getUsHours());
        assertEquals(userStory2.getDescription().getText(), userStory3.getDescription().getText());
        assertEquals(userStory2.getPriority().getPriorityUs(), userStory3.getPriority().getPriorityUs());
        assertEquals(userStory2.getTitle().getTitleUs(), userStory3.getTitle().getTitleUs());
        assertEquals(userStory2.getTimeEstimate().getUsHours(), userStory3.getTimeEstimate().getUsHours());
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
    void getTimeEstimate() {
        UserStory userStory1 = new UserStory("As a PO, i want to test this string", 2, "Fazer tal", 5);
        UserStory userStory2 = new UserStory("As a IO, i want to test this string", 2, "Fazer tal e coiso", 5);
        userStory2.setTimeEstimate(new UsHour(0));

        assertEquals(0, userStory2.getTimeEstimate().getUsHours());
        assertNotEquals(0, userStory1.getTimeEstimate().getUsHours());
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
        assertEquals(userStory1.getDescription(), userStory2.getDescription());
        assertEquals(userStory1.getTitle(), userStory2.getTitle());
        assertNotEquals(userStory1, userStory3);
        assertNotSame("saffdf", userStory1.getTitle().getTitleUs());
        assertFalse(userStory1.hasCode(213));
        assertTrue(userStory1.setPriority(2));

    }

    @Test
    public void overrideStatus() {
        //Arrange

        UserStory userStory1 = new UserStory("As coise, I want to tal", 4, "Fazer totil de cenas bueda fixes", 0);
        UserStory userStory2 = new UserStory("As coise, I want to tal", 4, "Fazer totil de cenas bueda fixes", 0);
        UserStory userStory3 = new UserStory("As coise, I want to tal12132", 4, "Fazer totil de cenas bueda fixes", 10);
        UserStory userStory4 = new UserStory(userStory3, 4, "Fazer totil de cenas bueda fixes");
        UserStory userStory5 = new UserStory(userStory3,  4, "Fazer totil de cenas bueda fixes");
        boolean result = userStory1.equals(userStory2);

        //Assert
        assertNotSame(userStory1, userStory2);
        assertEquals(userStory1, userStory2);
        assertEquals(userStory1.getTitle(), userStory2.getTitle());
        assertEquals(userStory1.getPriority(), userStory2.getPriority());
        assertEquals(userStory1.getTimeEstimate(), userStory2.getTimeEstimate());
        assertEquals(userStory1.getIdUserStory(), userStory2.getIdUserStory());
        assertEquals(userStory4.getParentUserStory(), userStory5.getParentUserStory());
        assertEquals(userStory1.getTasks(), userStory2.getTasks());
        assertNotEquals(userStory1, userStory3);
        assertNotSame("saffdf", userStory1.getTitle().getTitleUs());
        assertFalse(userStory1.hasCode(213));
        assertTrue(userStory1.setPriority(2));
        assertEquals(0, userStory1.getTimeEstimate().getUsHours());
        assertTrue(result);

    }

}