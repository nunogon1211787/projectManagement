package switch2021.project.model.UserStory;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switch2021.project.model.valueObject.*;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class UserStoryTest {


    @Test
    @DisplayName("Create User Story with success - using user story ID")
    void CreateUserStoryWithSuccess() {
        //Arrange
        UserStoryID usID = mock(UserStoryID.class);
        UserStory userStory = new UserStory(usID);
        ProjectID projID = mock(ProjectID.class);
        UsTitle usTitle = mock(UsTitle.class);
        //Act
        when(usID.getProjectID()).thenReturn(projID);
        when(projID.getCode()).thenReturn("Project_2022_1");
        when(usID.getUsTitle()).thenReturn(usTitle);
        when(usTitle.getTitleUs()).thenReturn("As a PO, i want to test this string");
        //Assert
        assertEquals(usID, userStory.getUserStoryID());
    }

    @Test
    @DisplayName("Create User Story refined with success - using user story parent")
    void CreateUserStoryParentWithSuccess() {
        //Arrange
        UserStoryID usID = mock(UserStoryID.class);
        UserStory userStory = new UserStory(usID);
        ProjectID projID = mock(ProjectID.class);
        UsTitle usTitle = mock(UsTitle.class);

        when(usID.getProjectID()).thenReturn(projID);
        when(projID.getCode()).thenReturn("Project_2022_1");
        when(usID.getUsTitle()).thenReturn(usTitle);
        when(usTitle.getTitleUs()).thenReturn("As a PO, i want to test this string");
        when(userStory.getUserStoryID().toString()).thenReturn("Project_2022_1_As a PO, i want to test this string");
        //Act
        UserStory userStoryParent = new UserStory(userStory);
        //Assert
        assertEquals(userStory.getUserStoryID().toString() + " - Refined", userStoryParent.getUserStoryID().toString());
    }

    @Test
    @DisplayName("test to get information about object attributes")
    public void getUserStoryAttributes() {
        //Arrange
        UserStoryID usID = mock(UserStoryID.class);
        UserStory userStory = new UserStory(usID);
        ProjectID projID = mock(ProjectID.class);
        UsTitle usTitle = mock(UsTitle.class);
        UsPriority usPriority = mock(UsPriority.class);
        Description description = mock(Description.class);
        UsHour timeEstimate = mock(UsHour.class);

        when(usID.getProjectID()).thenReturn(projID);
        when(projID.getCode()).thenReturn("Project_2022_1");
        when(usID.getUsTitle()).thenReturn(usTitle);
        when(usTitle.getTitleUs()).thenReturn("As a PO, i want to test this string");
        when(userStory.getUserStoryID().toString()).thenReturn("Project_2022_1_As a PO, i want to test this string");
        when(usPriority.getPriorityUs()).thenReturn(1);
        when(description.getText()).thenReturn("Make mock test");
        when(timeEstimate.getUsHours()).thenReturn(5.0);
        //Act
        userStory.setPriority(usPriority);
        userStory.setDescription(description);
        userStory.setTimeEstimate(timeEstimate);
        userStory.setUsCancelled(LocalDate.now());
        userStory.setUsEndDate(LocalDate.now());
        userStory.setUsStartDate(LocalDate.now());
        userStory.setParentUserStory(userStory);
        //Assert
        assertEquals(1, userStory.getPriority().getPriorityUs());
        assertEquals("Make mock test", userStory.getDescription().getText());
        assertEquals(5.0, userStory.getTimeEstimate().getUsHours());
        assertEquals(LocalDate.now(), userStory.getUsCancelled());
        assertEquals(LocalDate.now(), userStory.getUsEndDate());
        assertEquals(LocalDate.now(), userStory.getUsStartDate());
        assertEquals(userStory, userStory.getParentUserStory());


    }

    @Test
    @DisplayName("Test hasCode conditions for coverage purposes")
    void hasCodeTest() {
        //Arrange
        UserStoryID usID = mock(UserStoryID.class);
        UserStory userStory = new UserStory(usID);
        ProjectID projID = mock(ProjectID.class);
        UsTitle usTitle = mock(UsTitle.class);

        when(usID.getProjectID()).thenReturn(projID);
        when(projID.getCode()).thenReturn("Project_2022_1");
        when(usID.getUsTitle()).thenReturn(usTitle);
        when(usTitle.getTitleUs()).thenReturn("As a PO, i want to test this string");
        when(userStory.getUserStoryID().toString()).thenReturn("Project_2022_1_As a PO, i want to test this string");

        UserStoryID usID2 = mock(UserStoryID.class);
        UserStory userStory2 = new UserStory(usID2);
        ProjectID projID2 = mock(ProjectID.class);
        UsTitle usTitle2 = mock(UsTitle.class);

        when(usID2.getProjectID()).thenReturn(projID2);
        when(projID2.getCode()).thenReturn("Project_2022_1");
        when(usID2.getUsTitle()).thenReturn(usTitle2);
        when(usTitle2.getTitleUs()).thenReturn("As a PO, i want to test this string");
        when(userStory2.getUserStoryID().toString()).thenReturn("Project_2022_1_As a PO, i want to test this string");

        UserStoryID usID3 = mock(UserStoryID.class);
        UserStory userStory3 = new UserStory(usID3);
        ProjectID projID3 = mock(ProjectID.class);
        UsTitle usTitle3 = mock(UsTitle.class);

        when(usID3.getProjectID()).thenReturn(projID3);
        when(projID3.getCode()).thenReturn("Project_2024_1");
        when(usID3.getUsTitle()).thenReturn(usTitle3);
        when(usTitle3.getTitleUs()).thenReturn("As a PO, i want to test this override method");
        when(userStory3.getUserStoryID().toString()).thenReturn("Project_2024_1_As a PO, i want to test this override" +
                " method");

        //Act
        boolean expected = userStory.hasCode(userStory.getUserStoryID());
        boolean expectedF = userStory.hasCode(userStory3.getUserStoryID());
        //Assert
        assertTrue(expected);
        assertFalse(expectedF);
        assertEquals(userStory.hasCode(usID), userStory2.hasCode(usID2));

    }

    @Test
    @DisplayName("Test same identity conditions for coverage purposes")
    public void sameIdentityAs() {
        //Arrange
        UserStoryID usID = mock(UserStoryID.class);
        UserStory userStory = new UserStory(usID);
        ProjectID projID = mock(ProjectID.class);
        UsTitle usTitle = mock(UsTitle.class);

        when(usID.getProjectID()).thenReturn(projID);
        when(projID.getCode()).thenReturn("Project_2022_1");
        when(usID.getUsTitle()).thenReturn(usTitle);
        when(usTitle.getTitleUs()).thenReturn("As a PO, i want to test this string");
        when(userStory.getUserStoryID().toString()).thenReturn("Project_2022_1_As a PO, i want to test this string");

        UserStoryID usID2 = mock(UserStoryID.class);
        UserStory userStory2 = new UserStory(usID2);
        ProjectID projID2 = mock(ProjectID.class);
        UsTitle usTitle2 = mock(UsTitle.class);

        when(usID2.getProjectID()).thenReturn(projID2);
        when(projID2.getCode()).thenReturn("Project_2022_1");
        when(usID2.getUsTitle()).thenReturn(usTitle2);
        when(usTitle2.getTitleUs()).thenReturn("As a PO, i want to test this string");
        when(userStory2.getUserStoryID().toString()).thenReturn("Project_2022_1_As a PO, i want to test this string");

        assertFalse(userStory.sameIdentityAs(userStory2));
    }

    @Test
    @DisplayName("Test override conditions for coverage purposes")
    public void overrideTest() {
        //Arrange & Act
        UserStoryID usID = mock(UserStoryID.class);
        UserStory userStory = new UserStory(usID);
        ProjectID projID = mock(ProjectID.class);
        UsTitle usTitle = mock(UsTitle.class);

        when(usID.getProjectID()).thenReturn(projID);
        when(projID.getCode()).thenReturn("Project_2022_1");
        when(usID.getUsTitle()).thenReturn(usTitle);
        when(usTitle.getTitleUs()).thenReturn("As a PO, i want to test this string");
        when(userStory.getUserStoryID().toString()).thenReturn("Project_2022_1_As a PO, i want to test this string");

        UserStory userStory2 = new UserStory(usID);

        UserStoryID usID3 = mock(UserStoryID.class);
        UserStory userStory3 = new UserStory(usID3);
        ProjectID projID3 = mock(ProjectID.class);
        UsTitle usTitle3 = mock(UsTitle.class);

        when(usID3.getProjectID()).thenReturn(projID3);
        when(projID3.getCode()).thenReturn("Project_2024_1");
        when(usID3.getUsTitle()).thenReturn(usTitle3);
        when(usTitle3.getTitleUs()).thenReturn("As a PO, i want to test this override method");
        when(userStory3.getUserStoryID().toString()).thenReturn("Project_2024_1_As a PO, i want to test this override" +
                " method");

        //Assert
        assertNotSame(userStory, userStory3);
        assertEquals(userStory.getUserStoryID(), userStory2.getUserStoryID());
        assertNotEquals(userStory.getUserStoryID(), userStory3.getUserStoryID());
        assertNotEquals(userStory, userStory2);
    }


    @Test
    @DisplayName("Test hashcode conditions for coverage purposes")
    public void hashCodeTest() {
        //Arrange
        UserStoryID usID = mock(UserStoryID.class);
        UserStory userStory = new UserStory(usID);
        ProjectID projID = mock(ProjectID.class);
        UsTitle usTitle = mock(UsTitle.class);

        when(usID.getProjectID()).thenReturn(projID);
        when(projID.getCode()).thenReturn("Project_2022_1");
        when(usID.getUsTitle()).thenReturn(usTitle);
        when(usTitle.getTitleUs()).thenReturn("As a PO, i want to test this string");
        when(userStory.getUserStoryID().toString()).thenReturn("Project_2022_1_As a PO, i want to test this string");

        UserStoryID usID2 = mock(UserStoryID.class);
        UserStory userStory2 = new UserStory(usID);
        ProjectID projID2 = mock(ProjectID.class);
        UsTitle usTitle2 = mock(UsTitle.class);

        when(usID2.getProjectID()).thenReturn(projID2);
        when(projID2.getCode()).thenReturn("Project_2022_1");
        when(usID2.getUsTitle()).thenReturn(usTitle2);
        when(usTitle2.getTitleUs()).thenReturn("As a PO, i want to test this string");
        when(userStory2.getUserStoryID().toString()).thenReturn("Project_2022_1_As a PO, i want to test this string");

        UserStoryID usID3 = mock(UserStoryID.class);
        UserStory userStory3 = new UserStory(usID);
        ProjectID projID3 = mock(ProjectID.class);
        UsTitle usTitle3 = mock(UsTitle.class);

        when(usID3.getProjectID()).thenReturn(projID2);
        when(projID3.getCode()).thenReturn("Project_2024_1");
        when(usID3.getUsTitle()).thenReturn(usTitle3);
        when(usTitle3.getTitleUs()).thenReturn("As a PO, i want to test this override method");
        when(userStory3.getUserStoryID().toString()).thenReturn("Project_2024_1_As a PO, i want to test this override" +
                " method");
       //Act
        boolean result = userStory.getUserStoryID().equals(userStory2.getUserStoryID());
        boolean result2 = userStory.equals(userStory3);

        //Assert
        assertEquals(userStory.hashCode(), userStory2.hashCode());
        assertEquals(userStory, userStory);
        assertNotEquals(userStory3, userStory2);
        assertTrue(result);
        assertFalse(result2);
    }


//    @Test
//    void setParentUserStoryTest() {
//        //Arrange
//        UserStory userStory = new UserStory("Project_2022_1","Project_2022_1_As a PO, i want to test this string",
//        "As a PO, i want to test this string", 1, "US001 - Test", 40);
//        UserStory userStory_parent = new UserStory("Project_2022_1","Project_2022_1_As a PO, i want to test this
//        string", "As a PO, i want to test this string", 1, "As a PO, i want to test this string - Test", 40);
//        UserStory userStory_parent2 = new UserStory("Project_2022_1","Project_2022_1_As a PO, i want to test this
//        string", "As a PO, i want to test", 2, "make testes", 20);
//
//        //Act
//        userStory.setParentUserStory(userStory_parent);
//
//        //Assert
//        assertEquals(userStory.getParentUserStory(), userStory_parent);
//
//    }


//    @Test
//    void ValidateInfoUserStory() {
//        //Arrange & Act
//        UserStory userStory = new UserStory("Project_2022_1","Project_2022_1_As a PO, i want to test this string",
//        "As a PO, i want to test this string", 1, "As a PO, i want to test this string - Test", 40);
//        //Assert
//        assertEquals("As a PO, i want to test this string", userStory.getTitle().getTitleUs());
//        assertEquals("As a PO, i want to test this string - Test", userStory.getDescription().getText());
//    }
//

//    @Test
//    void setPriorityTest() {
//        UserStory userStory = new UserStory("Project_2022_1","Project_2022_1_As a PO, i want to test this string",
//        "As a PO, i want to test this string", 2, "Fazer tal", 5);
//        userStory.setPriority(4);
//        assertEquals(4, userStory.getPriority().getPriorityUs());
//    }
//
//
//
//    @Test
//    void hasCodeTest2() {
//        UserStory userStory = new UserStory("Project_2022_1","Project_2022_1_As a PO, i want to test this string",
//        "As a PO, i want to test this string", 2, "Fazer tal", 5);
//        UserStoryID userStoryId = new UserStoryID("Project_2022_1_As a PO, i want to test this string");
//        boolean expected = userStory.hasCode(userStoryId);
//        assertFalse(expected);
//    }
//
//    @Test
//    void setDescriptionTest() {
//        //Arrange
//        UserStory userStory = new UserStory("Project_2022_1","Project_2022_1_As a PO, i want to test this string",
//        "As a PO, i want to test this string", 2, "Fazer tal", 5);
//        //Act
//        userStory.setDescription(new Description ("Fazer coiso"));
//        //Assert
//        assertEquals("Fazer coiso", userStory.getDescription().getText());
//    }
//
//
//
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
//            UserStory userStory = new UserStory("Project_2022_1","Project_2022_1_As a PO, i want to test this
//            string", "As a PO, i want to test this string", 2, "", 5);
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
//            UserStory userStory = new UserStory("Project_2022_1","Project_2022_1_As a PO, i want to test this
//            string", "As a PO, i want to test this string", 2, "    ", 5);
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
//            UserStory userStory = new UserStory("Project_2022_1","Project_2022_1_As a PO, i want to test this
//            string", "", 2, "Fazer tal", 5);
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
//            UserStory userStory = new UserStory("Project_2022_1","Project_2022_1_As a PO, i want to test this
//            string", "C", 2, "Fazer tal", 5);
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
//            UserStory userStory = new UserStory("Project_2022_1","Project_2022_1_As a PO, i want to test this
//            string", "   ", 2, "Fazer tal", 5);
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
//            UserStory userStory = new UserStory("Project_2022_1","Project_2022_1_As a PO, i want to test this
//            string", "CC", 2, "Fazer tal", 5);
//            sprint.saveUsInScrumBoard(userStory);
//        });
//    }
//
//    @Test
//    void hashCodeTest() {
//        UserStory userStory1 = new UserStory("Project_2022_2","Project_2022_1_As a PO, i want to test this string",
//        "As a PO, i want to test this string", 1, "Fazer tal", 0);
//        UserStory userStory2 = new UserStory("Project_2022_1","Project_2022_1_As a PO, i want to test this string",
//        "As a AO, i want to test this string", 2, "Fazer tal e coiso", 5);
//        UserStory userStory3 = new UserStory("Project_2022_1","Project_2022_1_As a PO, i want to test this string",
//        "As a AO, i want to test this string", 2, "Fazer tal e coiso", 5);
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
//        UserStory userStory1 = new UserStory("Project_2022_1","Project_2022_1_As a PO, i want to test this string",
//        "As a PO, i want to test this string", 2, "Fazer tal", 5);
//        UserStory userStory2 = new UserStory("Project_2022_1","Project_2022_2_As a PO, i want to test this string",
//        "As a IO, i want to test this string", 2, "Fazer tal e coiso", 5);
//
//        assertEquals("Project_2022_2_As a PO, i want to test this string", userStory2.getUserStoryID().toString());
//        assertNotEquals("2", userStory1.getUserStoryID());
//    }
//
//
//    @Test
//    void setPriorityTrue() {
//        UserStory userStory1 = new UserStory("Project_2022_1","Project_2022_1_As a PO, i want to test this string",
//        "As a PO, i want to test this string", 2, "Fazer tal", 5);
//        UserStory userStory2 = new UserStory("Project_2022_1","Project_2022_1_As a PO, i want to test this string",
//        "As a SO, i want to test this string", 2, "Fazer tal e coiso", 5);
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
//        UserStory userStory1 = new UserStory("Project_2022_1","Project_2022_1_As a PO, i want to test this string",
//        "As a PO, i want to test this string", 2, "Fazer tal", 5);
//        UserStory userStory2 = new UserStory("Project_2022_1","Project_2022_1_As a PO, i want to test this string",
//        "As a AO, i want to test this string", 2, "Fazer tal e coiso", 5);
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
//        UserStory userStory1 = new UserStory("Project_2022_1","Project_2022_1_As a PO, i want to test this string",
//        "As coise, I want to tal", 4, "Fazer totil de cenas bueda fixes", 10);
//        UserStory userStory2 = new UserStory("Project_2022_1","Project_2022_1_As a PO, i want to test this string",
//        "As coise, I want to tal", 4, "Fazer totil de cenas bueda fixes", 10);
//        UserStory userStory3 = new UserStory("Project_2022_1","Project_2022_1_As a PO, i want to test this string",
//        "As coise, I want to tal12132", 4, "Fazer totil de cenas bueda fixes", 10);
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
//        UserStory userStory1 = new UserStory("Project_2022_1","Project_2022_1_As a PO, i want to test this string",
//        "As coise, I want to tal", 4, "Fazer totil de cenas bueda fixes", 0);
//        UserStory userStory2 = new UserStory("Project_2022_1","Project_2022_1_As a PO, i want to test this string",
//        "As coise, I want to tal", 4, "Fazer totil de cenas bueda fixes", 0);
//        UserStory userStory3 = new UserStory("Project_2022_1","Project_2022_1_As a PO, i want to test this string",
//        "As coise, I want to tal12132", 4, "Fazer totil de cenas bueda fixes", 10);
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


}