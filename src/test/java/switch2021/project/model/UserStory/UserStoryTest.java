//package switch2021.project.model.UserStory;
//
//import org.junit.jupiter.api.Test;
//import switch2021.project.model.Sprint.Sprint;
//import switch2021.project.model.valueObject.*;
//
//import java.time.LocalDate;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.mock;
//import static org.mockito.Mockito.when;
//
//class UserStoryTest {
//
//    @Test
//    void ValidateInfoUserStory() {
//        //Arrange & Act
//        UserStory userStory = new UserStory("Project_2022_1","Project_2022_1_As a PO, i want to test this string", "As a PO, i want to test this string", 1, "As a PO, i want to test this string - Test", 40);
//        //Assert
//        assertEquals("As a PO, i want to test this string", userStory.getTitle().getTitleUs());
//        assertEquals("As a PO, i want to test this string - Test", userStory.getDescription().getText());
//    }
//
//
//    @Test
//    void setPriorityTest() {
//        UserStory userStory = new UserStory("Project_2022_1","Project_2022_1_As a PO, i want to test this string", "As a PO, i want to test this string", 2, "Fazer tal", 5);
//        userStory.setPriority(4);
//        assertEquals(4, userStory.getPriority().getPriorityUs());
//    }
//
//
//    @Test
//    void hasCodeTest() {
//        UserStory userStory = new UserStory("Project_2022_1","Project_2022_1_As a PO, i want to test this string", "As a PO, i want to test this string", 2, "Fazer tal", 5);
//
//        boolean expected = userStory.hasCode(userStory.getUserStoryID());
//        assertTrue(expected);
//    }
//
//    @Test
//    void hasCodeTest2() {
//        UserStory userStory = new UserStory("Project_2022_1","Project_2022_1_As a PO, i want to test this string", "As a PO, i want to test this string", 2, "Fazer tal", 5);
//        UserStoryID userStoryId = new UserStoryID("Project_2022_1_As a PO, i want to test this string");
//        boolean expected = userStory.hasCode(userStoryId);
//        assertFalse(expected);
//    }
//
//    @Test
//    void setDescriptionTest() {
//        //Arrange
//        UserStory userStory = new UserStory("Project_2022_1","Project_2022_1_As a PO, i want to test this string", "As a PO, i want to test this string", 2, "Fazer tal", 5);
//        //Act
//        userStory.setDescription(new Description ("Fazer coiso"));
//        //Assert
//        assertEquals("Fazer coiso", userStory.getDescription().getText());
//    }
//
//
//    @Test
//    void setParentUserStoryTest() {
//        //Arrange
//        UserStory userStory = new UserStory("Project_2022_1","Project_2022_1_As a PO, i want to test this string", "As a PO, i want to test this string", 1, "US001 - Test", 40);
//        UserStory userStory_parent = new UserStory("Project_2022_1","Project_2022_1_As a PO, i want to test this string", "As a PO, i want to test this string", 1, "As a PO, i want to test this string - Test", 40);
//        UserStory userStory_parent2 = new UserStory("Project_2022_1","Project_2022_1_As a PO, i want to test this string", "As a PO, i want to test", 2, "make testes", 20);
//
//        //Act
//        userStory.setParentUserStory(userStory_parent);
//
//        //Assert
//        assertEquals(userStory.getParentUserStory(), userStory_parent);
//
//    }
//
//    @Test
//    void isValidUserStoryDescription() {
//        assertThrows(IllegalArgumentException.class, () -> {
//            Sprint sprint = mock(Sprint.class);
//            Description description = mock(Description.class);
//            when(sprint.getSprintName()).thenReturn(description);
//            when(description.getText()).thenReturn("Super");
//            when(sprint.getStartDate()).thenReturn(LocalDate.of(2022, 3, 1));
//
//            UserStory userStory = new UserStory("Project_2022_1","Project_2022_1_As a PO, i want to test this string", "As a PO, i want to test this string", 2, "", 5);
//            sprint.saveUsInScrumBoard(userStory);
//        });
//    }
//
//    @Test
//    void isValidUserStoryDescription2() {
//        assertThrows(IllegalArgumentException.class, () -> {
//            Sprint sprint = mock(Sprint.class);
//            Description description = mock(Description.class);
//            when(sprint.getSprintName()).thenReturn(description);
//            when(description.getText()).thenReturn("Super");
//            when(sprint.getStartDate()).thenReturn(LocalDate.of(2022, 3, 1));
//
//            UserStory userStory = new UserStory("Project_2022_1","Project_2022_1_As a PO, i want to test this string", "As a PO, i want to test this string", 2, "    ", 5);
//            sprint.saveUsInScrumBoard(userStory);
//        });
//    }
//
//
//    @Test
//    void isValidUserStoryName() {
//        assertThrows(IllegalArgumentException.class, () -> {
//            Sprint sprint = mock(Sprint.class);
//            Description description = mock(Description.class);
//            when(sprint.getSprintName()).thenReturn(description);
//            when(description.getText()).thenReturn("Super");
//            when(sprint.getStartDate()).thenReturn(LocalDate.of(2022, 3, 1));
//
//            UserStory userStory = new UserStory("Project_2022_1","Project_2022_1_As a PO, i want to test this string", "", 2, "Fazer tal", 5);
//            sprint.saveUsInScrumBoard(userStory);
//        });
//    }
//
//    @Test
//    void isValidUserStoryName2() {
//        assertThrows(IllegalArgumentException.class, () -> {
//            Sprint sprint = mock(Sprint.class);
//            Description description = mock(Description.class);
//            when(sprint.getSprintName()).thenReturn(description);
//            when(description.getText()).thenReturn("Super");
//            when(sprint.getStartDate()).thenReturn(LocalDate.of(2022, 3, 1));
//
//
//            UserStory userStory = new UserStory("Project_2022_1","Project_2022_1_As a PO, i want to test this string", "C", 2, "Fazer tal", 5);
//            sprint.saveUsInScrumBoard(userStory);
//        });
//    }
//
//    @Test
//    void isValidUserStoryName3() {
//        assertThrows(IllegalArgumentException.class, () -> {
//            Sprint sprint = mock(Sprint.class);
//            Description description = mock(Description.class);
//            when(sprint.getSprintName()).thenReturn(description);
//            when(description.getText()).thenReturn("Super");
//            when(sprint.getStartDate()).thenReturn(LocalDate.of(2022, 3, 1));
//
//            UserStory userStory = new UserStory("Project_2022_1","Project_2022_1_As a PO, i want to test this string", "   ", 2, "Fazer tal", 5);
//            sprint.saveUsInScrumBoard(userStory);
//        });
//    }
//
//    @Test
//    void isValidUserStoryName5() {
//        assertThrows(IllegalArgumentException.class, () -> {
//            Sprint sprint = mock(Sprint.class);
//            Description description = mock(Description.class);
//            when(sprint.getSprintName()).thenReturn(description);
//            when(description.getText()).thenReturn("Super");
//            when(sprint.getStartDate()).thenReturn(LocalDate.of(2022, 3, 1));
//
//
//            UserStory userStory = new UserStory("Project_2022_1","Project_2022_1_As a PO, i want to test this string", "CC", 2, "Fazer tal", 5);
//            sprint.saveUsInScrumBoard(userStory);
//        });
//    }
//
//    @Test
//    void hashCodeTest() {
//        UserStory userStory1 = new UserStory("Project_2022_2","Project_2022_1_As a PO, i want to test this string", "As a PO, i want to test this string", 1, "Fazer tal", 0);
//        UserStory userStory2 = new UserStory("Project_2022_1","Project_2022_1_As a PO, i want to test this string", "As a AO, i want to test this string", 2, "Fazer tal e coiso", 5);
//        UserStory userStory3 = new UserStory("Project_2022_1","Project_2022_1_As a PO, i want to test this string", "As a AO, i want to test this string", 2, "Fazer tal e coiso", 5);
//        UserStoryStatus status4 = mock(UserStoryStatus.class);
//        Description description = mock(Description.class);
//        when(status4.getDescription()).thenReturn(description);
//        when(description.getText()).thenReturn("teste5");
//        when(status4.isSprintAvailable()).thenReturn(true);
//
//        assertEquals(0, userStory1.getTimeEstimate().getUsHours());
//        assertEquals(userStory2.getDescription().getText(), userStory3.getDescription().getText());
//        assertEquals(userStory2.getPriority().getPriorityUs(), userStory3.getPriority().getPriorityUs());
//        assertEquals(userStory2.getTitle().getTitleUs(), userStory3.getTitle().getTitleUs());
//        assertEquals(userStory2.getTimeEstimate().getUsHours(), userStory3.getTimeEstimate().getUsHours());
//        assertEquals(userStory2.getProjectID().getCode(),userStory3.getProjectID().getCode());
//        assertNotEquals(userStory1.getProjectID().getCode(),userStory3.getProjectID().getCode());
//        assertNotEquals(userStory2.getDescription().getText(), userStory1.getDescription().getText());
//        assertNotEquals(null, userStory1);
//        assertEquals(userStory1.getClass(), userStory2.getClass());
//        assertNotEquals(null, status4);
//    }
//
//    @Test
//    void setID() {
//        UserStory userStory1 = new UserStory("Project_2022_1","Project_2022_1_As a PO, i want to test this string", "As a PO, i want to test this string", 2, "Fazer tal", 5);
//        UserStory userStory2 = new UserStory("Project_2022_1","Project_2022_2_As a PO, i want to test this string", "As a IO, i want to test this string", 2, "Fazer tal e coiso", 5);
//
//        assertEquals("Project_2022_2_As a PO, i want to test this string", userStory2.getUserStoryID().toString());
//        assertNotEquals("2", userStory1.getUserStoryID());
//    }
//
//
//    @Test
//    void setPriorityTrue() {
//        UserStory userStory1 = new UserStory("Project_2022_1","Project_2022_1_As a PO, i want to test this string", "As a PO, i want to test this string", 2, "Fazer tal", 5);
//        UserStory userStory2 = new UserStory("Project_2022_1","Project_2022_1_As a PO, i want to test this string", "As a SO, i want to test this string", 2, "Fazer tal e coiso", 5);
//        userStory2.setPriority(1);
//        userStory1.setPriority(4);
//
//        assertTrue(userStory1.setPriority(3));
//        assertEquals(3, userStory1.getPriority().getPriorityUs());
//
//    }
//
//    @Test
//    void setPriorityFalse() {
//        UserStory userStory1 = new UserStory("Project_2022_1","Project_2022_1_As a PO, i want to test this string", "As a PO, i want to test this string", 2, "Fazer tal", 5);
//        UserStory userStory2 = new UserStory("Project_2022_1","Project_2022_1_As a PO, i want to test this string", "As a AO, i want to test this string", 2, "Fazer tal e coiso", 5);
//        userStory2.setPriority(1);
//        Exception exception = assertThrows(IllegalArgumentException.class, () -> userStory1.setPriority(6));
//        assertNotEquals(6, userStory1.getPriority());
//        assertEquals("Check priority, cannot be < 0 or superior to 5", exception.getMessage());
//    }
//
//    @Test
//    public void overrideTest() {
//        //Arrange
//
//        UserStory userStory1 = new UserStory("Project_2022_1","Project_2022_1_As a PO, i want to test this string", "As coise, I want to tal", 4, "Fazer totil de cenas bueda fixes", 10);
//        UserStory userStory2 = new UserStory("Project_2022_1","Project_2022_1_As a PO, i want to test this string", "As coise, I want to tal", 4, "Fazer totil de cenas bueda fixes", 10);
//        UserStory userStory3 = new UserStory("Project_2022_1","Project_2022_1_As a PO, i want to test this string", "As coise, I want to tal12132", 4, "Fazer totil de cenas bueda fixes", 10);
//
//
//        //Assert
//        assertNotSame(userStory1, userStory2);
//        assertEquals(userStory1, userStory2);
//        assertEquals(userStory1.getDescription(), userStory2.getDescription());
//        assertEquals(userStory1.getTitle(), userStory2.getTitle());
//        assertNotSame("saffdf", userStory1.getTitle().getTitleUs());
//        assertTrue(userStory1.setPriority(2));
//
//    }
//
//    @Test
//    public void overrideStatus() {
//        //Arrange
//        UserStory userStory1 = new UserStory("Project_2022_1","Project_2022_1_As a PO, i want to test this string", "As coise, I want to tal", 4, "Fazer totil de cenas bueda fixes", 0);
//        UserStory userStory2 = new UserStory("Project_2022_1","Project_2022_1_As a PO, i want to test this string", "As coise, I want to tal", 4, "Fazer totil de cenas bueda fixes", 0);
//        UserStory userStory3 = new UserStory("Project_2022_1","Project_2022_1_As a PO, i want to test this string", "As coise, I want to tal12132", 4, "Fazer totil de cenas bueda fixes", 10);
//        UserStory userStory4 = new UserStory(userStory3, 4, "Fazer totil de cenas bueda fixes");
//        UserStory userStory5 = new UserStory(userStory3, 4, "Fazer totil de cenas bueda fixes");
//        boolean result = userStory1.equals(userStory2);
//
//        //Assert
//        assertNotSame(userStory1, userStory2);
//        assertEquals(userStory1, userStory2);
//        assertEquals(userStory1.getTitle(), userStory2.getTitle());
//        assertEquals(userStory1.getPriority(), userStory2.getPriority());
//        assertEquals(userStory1.getDescription(), userStory2.getDescription());
//        assertEquals(userStory1.getTimeEstimate(), userStory2.getTimeEstimate());
//        assertEquals(userStory1.getUserStoryID(), userStory2.getUserStoryID());
//        assertEquals(userStory4.getParentUserStory(), userStory5.getParentUserStory());
//        assertEquals(userStory1.getUsCancelled(), userStory2.getUsCancelled());
//        assertEquals(userStory1.getUsEndDate(), userStory2.getUsEndDate());
//        assertEquals(userStory1.getUsStartDate(), userStory2.getUsStartDate());
//        assertEquals(userStory1.getProjectID().getCode(),userStory2.getProjectID().getCode());
//        assertEquals(0, userStory1.getTimeEstimate().getUsHours());
//        assertTrue(result);
//    }
//
//
//}