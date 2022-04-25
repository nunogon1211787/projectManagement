package switch2021.project.model.UserStory;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switch2021.project.factory.UserStoryFactory;
import switch2021.project.model.valueObject.*;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UserStoryStoreTest {


    @Test
    @DisplayName("Create and save user story with success")
    public void createAndSaveUserStorySuccessFull() {
        // Arrange
        UserStoryFactory userStoryFactory = mock(UserStoryFactory.class);
        UserStoryStore userStoryStore = new UserStoryStore(userStoryFactory);
        UserStory newUserStory = mock(UserStory.class);
        UserStoryID userStoryId = mock(UserStoryID.class);
        UsTitle title = mock(UsTitle.class);
        UsPriority priority = mock(UsPriority.class);
        Description description = mock(Description.class);
        UsHour timeEstimate = mock(UsHour.class);
        ProjectID projectId = mock(ProjectID.class);

        when(newUserStory.getProjectID()).thenReturn(projectId);
        when(projectId.getCode()).thenReturn("Project_2022_1");
        when(newUserStory.getUserStoryId()).thenReturn(userStoryId);
        when(userStoryId.toString()).thenReturn("Project_2022_1_As a PO, i want to test this string");
        when(newUserStory.getTitle()).thenReturn(title);
        when(title.getTitleUs()).thenReturn("As a PO, i want to test this string");
        when(newUserStory.getPriority()).thenReturn(priority);
        when(priority.getPriorityUs()).thenReturn(1);
        when(newUserStory.getDescription()).thenReturn(description);
        when(description.getText()).thenReturn("Default Story");
        when(newUserStory.getTimeEstimate()).thenReturn(timeEstimate);
        when(timeEstimate.getUsHours()).thenReturn(5);

        when(userStoryFactory.createUserStory("Project_" + LocalDate.now().getYear() + "_" + 1, userStoryId.toString(), title.getTitleUs(), priority.getPriorityUs(), description.getText(), timeEstimate.getUsHours())).thenReturn(newUserStory);
        //Act
        userStoryStore.createAndSaveUserStory("Project_" + LocalDate.now().getYear() + "_" + 1, userStoryId.toString(), title.getTitleUs(), priority.getPriorityUs(), description.getText(), timeEstimate.getUsHours());
        // Assert
        assertEquals(priority.getPriorityUs(), userStoryStore.getUserStoryList().get(0).getPriority().getPriorityUs());
        assertEquals(description.getText(), userStoryStore.getUserStoryList().get(0).getDescription().getText());
        assertEquals(title.getTitleUs(), userStoryStore.getUserStoryList().get(0).getTitle().getTitleUs());
        assertEquals(userStoryId.toString(), userStoryStore.getUserStoryList().get(0).getUserStoryId().toString());
        assertEquals(timeEstimate.getUsHours(), userStoryStore.getUserStoryList().get(0).getTimeEstimate().getUsHours());
    }

    @Test
    @DisplayName("Create and save user story with success")
    public void createAndSaveUserStorySuccessSize() {
        // Arrange
        UserStoryFactory userStoryFactory = mock(UserStoryFactory.class);
        UserStoryStore userStoryStore = new UserStoryStore(userStoryFactory);
        UserStory newUserStory = mock(UserStory.class);
        UserStoryID userStoryId = mock(UserStoryID.class);
        UsTitle title = mock(UsTitle.class);
        UsPriority priority = mock(UsPriority.class);
        Description description = mock(Description.class);
        ProjectID projectId = mock(ProjectID.class);

        when(newUserStory.getProjectID()).thenReturn(projectId);
        when(projectId.getCode()).thenReturn("Project_2022_1");
        when(newUserStory.getUserStoryId()).thenReturn(userStoryId);
        when(userStoryId.toString()).thenReturn("Project_2022_1_As a PO, i want to test this string");
        when(newUserStory.getTitle()).thenReturn(title);
        when(title.getTitleUs()).thenReturn("As a PO, i want to test this string");
        when(newUserStory.getPriority()).thenReturn(priority);
        when(priority.getPriorityUs()).thenReturn(1);
        when(newUserStory.getDescription()).thenReturn(description);
        when(description.getText()).thenReturn("Default Story");

        when(userStoryFactory.createUserStory("Project_" + LocalDate.now().getYear() + "_" + 1, userStoryId.toString(), title.getTitleUs(), priority.getPriorityUs(), description.getText(), 5)).thenReturn(newUserStory);
        //Act
        userStoryStore.createAndSaveUserStory("Project_" + LocalDate.now().getYear() + "_" + 1, userStoryId.toString(), title.getTitleUs(), priority.getPriorityUs(), description.getText(), 5);
        // Assert
        assertEquals(1, userStoryStore.getUserStoryList().size());
        assertNotEquals(0, userStoryStore.getUserStoryList().size());
    }


    @Test
    @DisplayName("Create and save user story fail - empty description")
    public void createUserStoryDescriptionInvalidEmpty() {
        // Assert
        assertThrows(IllegalArgumentException.class, () -> {
            // Arrange
            UserStoryFactory userStoryFactory = mock(UserStoryFactory.class);
            UserStoryStore userStoryStore = new UserStoryStore(userStoryFactory);
            UserStory newUserStory = mock(UserStory.class);
            UserStoryID userStoryId = mock(UserStoryID.class);
            UsTitle title = mock(UsTitle.class);
            UsPriority priority = mock(UsPriority.class);
            Description description = mock(Description.class);
            ProjectID projectId = mock(ProjectID.class);

            when(newUserStory.getProjectID()).thenReturn(projectId);
            when(projectId.getCode()).thenReturn("Project_2022_1");
            when(newUserStory.getUserStoryId()).thenReturn(userStoryId);
            when(userStoryId.toString()).thenReturn("Project_2022_1_As a PO, i want to test this string");
            when(newUserStory.getTitle()).thenReturn(title);
            when(title.getTitleUs()).thenReturn("As a PO, i want to test this string");
            when(newUserStory.getPriority()).thenReturn(priority);
            when(priority.getPriorityUs()).thenReturn(1);
            when(newUserStory.getDescription()).thenReturn(description);
            when(description.getText()).thenReturn("");
            when(userStoryFactory.createUserStory("Project_" + LocalDate.now().getYear() + "_" + 1, userStoryId.toString(), title.getTitleUs(), priority.getPriorityUs(), description.getText(), 5)).thenThrow(new IllegalArgumentException("Description field requires at least " + 1 + " characters"));
            // Act
            userStoryStore.createAndSaveUserStory("Project_" + LocalDate.now().getYear() + "_" + 1, userStoryId.toString(), title.getTitleUs(), priority.getPriorityUs(), description.getText(), 5);
        });

    }

    @Test
    @DisplayName("Create and save user story success - description with one character")
    public void createUserStorySuccessFullOnLimit() {
        // Arrange
        UserStoryFactory userStoryFactory = mock(UserStoryFactory.class);
        UserStoryStore userStoryStore = new UserStoryStore(userStoryFactory);
        UserStory newUserStory = mock(UserStory.class);
        UserStoryID userStoryId = mock(UserStoryID.class);
        UsTitle title = mock(UsTitle.class);
        UsPriority priority = mock(UsPriority.class);
        Description description = mock(Description.class);
        ProjectID projectId = mock(ProjectID.class);

        when(newUserStory.getProjectID()).thenReturn(projectId);
        when(projectId.getCode()).thenReturn("Project_2022_1");
        when(newUserStory.getUserStoryId()).thenReturn(userStoryId);
        when(userStoryId.toString()).thenReturn("Project_2022_1_As a PO, i want to test this string");
        when(newUserStory.getTitle()).thenReturn(title);
        when(title.getTitleUs()).thenReturn("As a PO, i want to test this string");
        when(newUserStory.getPriority()).thenReturn(priority);
        when(priority.getPriorityUs()).thenReturn(1);
        when(newUserStory.getDescription()).thenReturn(description);
        when(description.getText()).thenReturn("D");
        when(userStoryFactory.createUserStory("Project_" + LocalDate.now().getYear() + "_" + 1, userStoryId.toString(), title.getTitleUs(), priority.getPriorityUs(), description.getText(), 5)).thenReturn(newUserStory);

        //Act
        userStoryStore.createAndSaveUserStory("Project_" + LocalDate.now().getYear() + "_" + 1, userStoryId.toString(), title.getTitleUs(), priority.getPriorityUs(), description.getText(), 5);
        // Assert
        assertEquals(priority.getPriorityUs(), userStoryStore.getUserStoryList().get(0).getPriority().getPriorityUs());
        assertEquals(description.getText(), userStoryStore.getUserStoryList().get(0).getDescription().getText());
        assertEquals(title.getTitleUs(), userStoryStore.getUserStoryList().get(0).getTitle().getTitleUs());
    }


    @Test
    @DisplayName("Create and save user story fail - invalid priority inferior")
    public void createUserStoryPriorityInvalidInf() {
        // Assert
        assertThrows(IllegalArgumentException.class, () -> {
            // Arrange
            UserStoryFactory userStoryFactory = new UserStoryFactory();
            UserStoryStore userStoryStore = new UserStoryStore(userStoryFactory);
            int priority = -1;
            String description = "Create user story";
            String userStoryId = "Project_2022_1_As a PO, i want to test this string";
            String projectId = "Project_2022_1";
            // Act
            userStoryStore.createAndSaveUserStory("Project_" + LocalDate.now().getYear() + "_" + 1, userStoryId, "US001", priority, description, 5);
        });
    }

    @Test
    @DisplayName("Create and save user story fail - invalid priority superior")
    public void createUserStoryPriorityInvalidSup() {
        // Assert
        assertThrows(IllegalArgumentException.class, () -> {
            // Arrange
            UserStoryFactory userStoryFactory = new UserStoryFactory();
            UserStoryStore userStoryStore = new UserStoryStore(userStoryFactory);
            int priority = 6;
            String description = "Create user story";
            String userStoryId = "Project_2022_1_As a PO, i want to test this string";
            String projectId = "Project_2022_1";
            // Act
            userStoryStore.createAndSaveUserStory("Project_" + LocalDate.now().getYear() + "_" + 1, userStoryId, "US001", priority, description, 5);
        });
    }


    @Test
    @DisplayName("Create and save user story fail - already exists")
    public void createUserStoryAlreadyExist() {
        // Assert
        assertThrows(IllegalArgumentException.class, () -> {
            // Arrange
            UserStoryFactory userStoryFactory = new UserStoryFactory();
            UserStoryStore userStoryStore = new UserStoryStore(userStoryFactory);
            int priority = 1;
            String description = "Create user story";
            String userStoryId = "Project_2022_1_As a PO, i want to test this string";
            ProjectID projectID = new ProjectID("Project_2022_1");
            // Act
            userStoryStore.createAndSaveUserStory("Project_" + LocalDate.now().getYear() + "_" + 1, userStoryId, "US001", priority, description, 5);
            userStoryStore.createAndSaveUserStory("Project_" + LocalDate.now().getYear() + "_" + 1, userStoryId, "US001", priority, description, 5);
        });
    }


    @Test
    @DisplayName("Create and save user story fail - existsByUserStoryId")
    public void existsByUserStoryIdFail() {
        // Assert
        assertThrows(IllegalArgumentException.class, () -> {
            // Arrange
            UserStoryFactory userStoryFactory = new UserStoryFactory();
            UserStoryStore userStoryStore = new UserStoryStore(userStoryFactory);
            int priority = 1;
            String description = "Create user story";
            String userStoryId = "Project_2022_1_As a PO, i want to test this string";
            ProjectID projectID = new ProjectID("Project_2022_1");
            userStoryStore.createAndSaveUserStory("Project_" + LocalDate.now().getYear() + "_" + 1, userStoryId, "US001", priority, description, 5);
            // Act
            userStoryStore.existsByUserStoryId(userStoryId);
        });
    }

    @Test
    @DisplayName("Create and save user story success - findAllUserStories")
    public void findAllUserStoriesSuccess() {
        // Arrange
        UserStoryFactory userStoryFactory = new UserStoryFactory();
        UserStoryStore userStoryStore = new UserStoryStore(userStoryFactory);
        int priority = 1;
        String description = "D";
        String title = "As a PO, i want to test this string";
        String userStoryId = "Project_2022_1_As a PO, i want to test this string";
        String userStoryId2 = "Project_2022_2_As a PO, i want to test this string";
        String userStoryId3 = "Project_2022_3_As a PO, i want to test this string";
        ProjectID projectID = new ProjectID("Project_2022_1");
        //Act
        userStoryStore.createAndSaveUserStory("Project_" + LocalDate.now().getYear() + "_" + 1, userStoryId, title, priority, description, 5);
        userStoryStore.createAndSaveUserStory("Project_" + LocalDate.now().getYear() + "_" + 1, userStoryId2, title, priority, description, 5);
        userStoryStore.createAndSaveUserStory("Project_" + LocalDate.now().getYear() + "_" + 1, userStoryId3, title, priority, description, 5);

        // Assert
        assertEquals(3, userStoryStore.findAllUserStories().size());
        assertEquals(userStoryId3, userStoryStore.findAllUserStories().get(2).getUserStoryId().toString());
        assertNotNull(userStoryStore.findAllUserStories());
    }

    @Test
    @DisplayName("Create and save user story success - findAllUserStoriesId")
    public void findAllUserStoriesEmptyList() {
        // Arrange & Act
        UserStoryFactory userStoryFactory = new UserStoryFactory();
        UserStoryStore userStoryStore = new UserStoryStore(userStoryFactory);
        // Assert
        assertEquals(0, userStoryStore.findAllUserStories().size());

    }

    @Test
    @DisplayName("Create and save user story success - findAllUserStories")
    public void findAllUserStoryByProjectIDSuccess() {
        // Arrange
        UserStoryFactory userStoryFactory = new UserStoryFactory();
        UserStoryStore userStoryStore = new UserStoryStore(userStoryFactory);
        int priority = 1;
        String description = "D";
        String title = "As a PO, i want to test this string";
        UserStoryID userStoryId = new UserStoryID(new ProjectID(1), new UsTitle("As a PO, i want to test this string"));
        ProjectID projectID = new ProjectID("Project_2022_1");
        //Act
        userStoryStore.createAndSaveUserStory("Project_" + LocalDate.now().getYear() + "_" + 1, userStoryId.toString(), title, priority, description, 5);


        // Assert
        assertEquals(1, userStoryStore.findAllUserStoryByProjectID(userStoryStore.getUserStoryList().get(0).getUserStoryId().getProjectID().getCode()).size());
    }


    @Test
    @DisplayName("grants a list of US that is sorted by priority. It keeps the done and/or cancelled US on the end\n")
    public void getSortedListWithSuccess() {
        // Arrange
        UserStoryFactory userStoryFactory = new UserStoryFactory();
        UserStoryStore userStoryStore = new UserStoryStore(userStoryFactory);
        UserStoryID userStoryId = new UserStoryID("Project_2022_1_As a PO, i want to test this string");
        UserStoryID userStoryID2 = new UserStoryID("Project_2022_2_As a PO, i want to test this string");
        UserStoryID userStoryID3 = new UserStoryID("Project_2022_3_As a PO, i want to test this string");
        UserStoryID userStoryID4 = new UserStoryID("Project_2022_4_As a PO, i want to test this string");
        ProjectID projectID = new ProjectID("Project_2022_1");
        userStoryStore.createAndSaveUserStory("Project_" + LocalDate.now().getYear() + "_" + 1, userStoryId.toString(), "As a PO, i want to test this string", 1, "create user story", 5);
        userStoryStore.createAndSaveUserStory("Project_" + LocalDate.now().getYear() + "_" + 1, userStoryID2.toString(), "As a PO, i want to test this string", 3, "sort user story", 5);
        userStoryStore.createAndSaveUserStory("Project_" + LocalDate.now().getYear() + "_" + 1, userStoryID3.toString(), "As a PO, i want to test this string", 2, "backlog sorted", 5);
        userStoryStore.createAndSaveUserStory("Project_" + LocalDate.now().getYear() + "_" + 1, userStoryID4.toString(), "As a PO, i want to test this string", 5, "show sorted", 5);

        // Act
        List<UserStory> userStoryList = userStoryStore.findUsSortedByPriority();

        // Assert
        assertEquals(4, userStoryList.size());
        assertEquals(1, userStoryList.get(0).getPriority().getPriorityUs());
        assertEquals(2, userStoryList.get(1).getPriority().getPriorityUs());
        assertEquals(3, userStoryList.get(2).getPriority().getPriorityUs());
        assertEquals(5, userStoryList.get(3).getPriority().getPriorityUs());
    }

    @Test
    @DisplayName("grants a list of US that is sorted by priority. It keeps the done and/or cancelled US on the end\n")
    public void getSortedListWithSuccessUsCancelledAndEndDate() {
        // Arrange
        UserStoryFactory userStoryFactory = new UserStoryFactory();
        UserStoryStore userStoryStore = new UserStoryStore(userStoryFactory);
        UserStoryID userStoryId = new UserStoryID("Project_2022_1_As a PO, i want to test this string");
        UserStoryID userStoryID2 = new UserStoryID("Project_2022_2_As a PO, i want to test this string");
        UserStoryID userStoryID3 = new UserStoryID("Project_2022_3_As a PO, i want to test this string");
        UserStoryID userStoryID4 = new UserStoryID("Project_2022_4_As a PO, i want to test this string");
        ProjectID projectID = new ProjectID("Project_2022_1");
        userStoryStore.createAndSaveUserStory("Project_" + LocalDate.now().getYear() + "_" + 1, userStoryId.toString(), "As a PO, i want to test this string", 1, "create user story", 5);
        userStoryStore.createAndSaveUserStory("Project_" + LocalDate.now().getYear() + "_" + 1, userStoryID2.toString(), "As a PO, i want to test this string", 3, "sort user story", 5);
        userStoryStore.createAndSaveUserStory("Project_" + LocalDate.now().getYear() + "_" + 1, userStoryID3.toString(), "As a PO, i want to test this string", 2, "backlog sorted", 5);
        userStoryStore.createAndSaveUserStory("Project_" + LocalDate.now().getYear() + "_" + 1, userStoryID4.toString(), "As a PO, i want to test this string", 5, "show sorted", 5);
        userStoryStore.findUserStoryById(userStoryId.toString()).setUsCancelled(LocalDate.now());
        userStoryStore.findUserStoryById(userStoryID2.toString()).setUsEndDate(LocalDate.now());

        // Act
        List<UserStory> userStoryList = userStoryStore.findUsSortedByPriority();

        // Assert
        assertEquals(4, userStoryList.size());
        assertEquals(2, userStoryList.get(0).getPriority().getPriorityUs());
        assertEquals(5, userStoryList.get(1).getPriority().getPriorityUs());
        assertEquals(1, userStoryList.get(2).getPriority().getPriorityUs());
        assertEquals(3, userStoryList.get(3).getPriority().getPriorityUs());
    }


    @Test
    @DisplayName("get exception message \"Check priority, cannot be < 0 or superior to 5.“")
    public void getSortedListFailWrongPriority() {
        // Arrange
        UserStoryFactory userStoryFactory = new UserStoryFactory();
        UserStoryStore userStoryStore = new UserStoryStore(userStoryFactory);
        UserStoryID userStoryId = new UserStoryID("Project_2022_1_As a PO, i want to test this string");
        ProjectID projectID = new ProjectID("Project_2022_1");
        // Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            userStoryStore.createAndSaveUserStory("Project_" + LocalDate.now().getYear() + "_" + 1, userStoryId.toString(), "As a PO, i want to test this string", 6, "sort user story", 5);
            userStoryStore.findUsSortedByPriority();
        });

        // Assert
        assertTrue(exception.getMessage().contains("Check priority, cannot be < 0 or superior to 5"));
    }

    @Test
    @DisplayName("Create UserStory Refine Success")
    public void createUserStoryRefineSuccess() {
        // Arrange
        UserStoryFactory userStoryFactory = new UserStoryFactory();
        UserStoryStore userStoryStore = new UserStoryStore(userStoryFactory);
        String userStoryId = "Project_2022_1_As a PO, i want to test this string";
        userStoryStore.createAndSaveUserStory("Project_" + LocalDate.now().getYear() + "_" + 1, userStoryId, "As a PO, i want to test this string", 4, "123TestTest", 5);

        // Act
        UserStory userStory = new UserStory(userStoryStore.getUserStoryList().get(0), 5, "234test234");

        // Assert
        assertEquals(userStory.getParentUserStory(), userStoryStore.getUserStoryList().get(0));
        assertEquals(5, userStory.getPriority().getPriorityUs());
        assertEquals("234test234", userStory.getDescription().getText());

    }


    @Test
    @DisplayName("Create UserStory Refine Fail - Description Empty")
    public void createUserStoryRefineDescriptionIsEmptyFail() {
        // Assert
        assertThrows(IllegalArgumentException.class, () -> {
            // Arrange
            UserStoryFactory userStoryFactory = new UserStoryFactory();
            UserStoryStore userStoryStore = new UserStoryStore(userStoryFactory);
            String userStoryId = "Project_2022_1_As a PO, i want to test this string";
            ProjectID projectID = new ProjectID("Project_2022_1");
            userStoryStore.createAndSaveUserStory("Project_" + LocalDate.now().getYear() + "_" + 1, userStoryId, "As a PO, i want to test this string", 4, "123testTest", 5);
            // Act
            new UserStory(userStoryStore.getUserStoryList().get(0), 5, "");
        });
    }

    @Test
    @DisplayName("Create UserStory Refine Fail - Description too Short")
    public void createUserStoryRefineSuccessOnLimit() {
        // Arrange
        UserStoryFactory userStoryFactory = new UserStoryFactory();
        UserStoryStore userStoryStore = new UserStoryStore(userStoryFactory);
        String userStoryId = "Project_2022_1_As a PO, i want to test this string";
        ProjectID projectID = new ProjectID("Project_2022_1");
        userStoryStore.createAndSaveUserStory("Project_" + LocalDate.now().getYear() + "_" + 1, userStoryId, "As a PO, i want to test this string", 4, "123testTest", 5);

        // Act
        UserStory userStory = new UserStory(userStoryStore.getUserStoryList().get(0), 5, "2");
        // Assert
        assertEquals(userStory.getParentUserStory(), userStoryStore.getUserStoryList().get(0));
        assertEquals(5, userStory.getPriority().getPriorityUs());
        assertEquals("2", userStory.getDescription().getText());
    }

    @Test
    @DisplayName("Create/save UserStory Refine Fail - Description already Exist")
    public void createUserStoryRefineDescriptionAlreadyExistFail() {
        UserStoryStore userStoryStore = new UserStoryStore();
        UserStoryID userStoryId = new UserStoryID("Project_2022_1_As a PO, i want to test this string");
        ProjectID projectID = new ProjectID("Project_2022_1");
        userStoryStore.createAndSaveUserStory("Project_" + LocalDate.now().getYear() + "_" + 1, userStoryId.toString(), "As a PO, i want to test this string", 4, "123testTest", 5);

        assertThrows(IllegalArgumentException.class, () -> userStoryStore.refineUserStory(userStoryStore.getUserStoryList().get(0), 4, "123testTest"));
    }

    @Test
    @DisplayName("Create/save UserStory Refine Fail - Priority Low")
    public void createUserStoryRefinePriorityLowFail() {
        UserStoryStore userStoryStore = new UserStoryStore();
        String userStoryId = "Project_2022_1_As a PO, i want to test this string";
        ProjectID projectID = new ProjectID("Project_2022_1");
        userStoryStore.createAndSaveUserStory("Project_" + LocalDate.now().getYear() + "_" + 1, userStoryId, "As a PO, i want to test this string", 4, "123testTest", 5);

        assertThrows(IllegalArgumentException.class, () -> new UserStory(userStoryStore.getUserStoryList().get(0), -1, "456testTest"));
    }

    @Test
    @DisplayName("Create/save UserStory Refine Fail - Priority High")
    public void createUserStoryRefinePriorityHighFail() {
        UserStoryStore userStoryStore = new UserStoryStore();
        String userStoryId = "Project_2022_1_As a PO, i want to test this string";
        ProjectID projectID = new ProjectID("Project_2022_1");
        userStoryStore.createAndSaveUserStory("Project_" + LocalDate.now().getYear() + "_" + 1, userStoryId, "As a PO, i want to test this string", 4, "123testTest", 5);

        assertThrows(IllegalArgumentException.class, () -> new UserStory(userStoryStore.getUserStoryList().get(0), 6, "456testTest"));
    }

    @Test
    @DisplayName("get User Story By Id Success")
    public void getUserStoryByIdSuccess() {
        UserStoryStore userStoryStore = new UserStoryStore();
        UserStoryID userStoryId = new UserStoryID("Project_2022_1_As a PO, i want to test this string");
        userStoryStore.createAndSaveUserStory("Project_" + LocalDate.now().getYear() + "_" + 1, userStoryId.toString(), "As a PO, i want to test this string", 4, "123testTest", 5);

        assertEquals("Project_2022_1_As a PO, i want to test this string", userStoryStore.getUserStoryList().get(0).getUserStoryId().toString());
    }

    @Test
    @DisplayName("get User Story By Id Fail")
    public void getUserStoryByIdFail() {
        UserStoryStore userStoryStore = new UserStoryStore();
        String userStoryId1 = "Project_2022_2_As a PO, i want to test this string";
        UserStoryID userStoryId = new UserStoryID("Project_2022_1_As a PO, i want to test this string");

        userStoryStore.createAndSaveUserStory("Project_" + LocalDate.now().getYear() + "_" + 1, userStoryId1, "As a PO, i want to test this string", 4, "123testTest", 5);

        assertNull(userStoryStore.findUserStoryById(userStoryId.toString()));
        assertNotEquals(1, userStoryStore.findUserStoryById(userStoryId.toString()));

    }

    @Test
    @DisplayName("getActiveUserStoryList_fail_not active")
    public void getActiveUserStoryListFailCompleted() {
        // Arrange
        UserStoryFactory userStoryFactory = new UserStoryFactory();
        UserStoryStore userStoryStore = new UserStoryStore(userStoryFactory);

        int priority = 1;
        String description = "Create user story";
        String userStoryId = "Project_2022_1_As a PO, i want to test this string";
        String userStoryId1 = "Project_2022_2_As a PO, i want to test this string";
        userStoryStore.createAndSaveUserStory("Project_" + LocalDate.now().getYear() + "_" + 1, userStoryId, "As a PO, i want to test this string", priority, description, 5);
        userStoryStore.createAndSaveUserStory("Project_" + LocalDate.now().getYear() + "_" + 1, userStoryId1, "As a PO, i want to test this string", priority, description, 5);
        userStoryStore.getUserStoryList().get(0).setUsEndDate(LocalDate.of(2022, 1, 25));
        userStoryStore.getUserStoryList().get(1).setUsCancelled(LocalDate.of(2022, 4, 2));

        // Act
        List<UserStory> userStoryList = userStoryStore.findActiveUserStoryList();
        // Assert
        assertEquals(0, userStoryList.size());

    }


    @Test
    @DisplayName("getActiveUserStoryList_success")
    public void getActiveUserStoryListSuccess() {
        // Arrange
        UserStoryFactory userStoryFactory = new UserStoryFactory();
        UserStoryStore userStoryStore = new UserStoryStore(userStoryFactory);

        int priority = 1;
        String description = "Create user story";
        String userStoryId = "Project_2022_1_As a PO, i want to test this string";
        String userStoryId1 = "Project_2022_2_As a PO, i want to test this string";
        ProjectID projectID = new ProjectID("Project_2022_1");
        userStoryStore.createAndSaveUserStory("Project_" + LocalDate.now().getYear() + "_" + 1, userStoryId, "As a PO, i want to test this string", priority, description, 5);
        userStoryStore.createAndSaveUserStory("Project_" + LocalDate.now().getYear() + "_" + 1, userStoryId1, "As a PO, i want to test this string", priority, description, 5);

        // Act
        List<UserStory> userStoryList = userStoryStore.findActiveUserStoryList();
        // Assert
        assertEquals(2, userStoryList.size());

    }

    @Test
    @DisplayName("set user story list")
    public void CreateAndSaveUserStorySuccess() {
        // Arrange
        UserStoryFactory userStoryFactory = mock(UserStoryFactory.class);
        UserStoryStore userStoryStore = new UserStoryStore(userStoryFactory);

        UserStory userStory = mock(UserStory.class);
        Description description1 = mock(Description.class);
        UserStoryID userStoryId = mock(UserStoryID.class);
        ProjectID projectID = mock(ProjectID.class);

        when(userStory.getDescription()).thenReturn(description1);
        when(description1.getText()).thenReturn("create user story");

        when(userStory.getUserStoryId()).thenReturn(userStoryId);
        when(userStoryId.toString()).thenReturn("Project_2022_1_As a PO, i want to test this string");

        when(userStory.getProjectID()).thenReturn(projectID);
        when(projectID.getCode()).thenReturn("Project_2022_1");


        when(userStoryFactory.createUserStory("Project_" + LocalDate.now().getYear() + "_" + 1, "Project_2022_1_As a PO, i want to test this string", "US001", 1, "create user story", 5)).thenReturn(userStory);
        userStoryStore.createAndSaveUserStory("Project_" + LocalDate.now().getYear() + "_" + 1, "Project_2022_1_As a PO, i want to test this string", "US001", 1, "create user story", 5);

        // Assert
        assertEquals(1, userStoryStore.getUserStoryList().size());


    }

    @Test
    @DisplayName("validate ID user Story")
    public void validateID() {
        // Arrange
        UserStoryFactory userStoryFactory = new UserStoryFactory();
        UserStoryStore userStoryStore = new UserStoryStore(userStoryFactory);
        UserStoryID userStoryId = new UserStoryID("Project_2022_1_As a PO, i want to test this string");
        UserStoryID userStoryID2 = new UserStoryID("Project_2022_2_As a PO, i want to test this string");
        UserStoryID userStoryID3 = new UserStoryID("Project_2022_3_As a PO, i want to test this string");
        ProjectID projectID = new ProjectID("Project_2022_1");
        // Act
        userStoryStore.createAndSaveUserStory("Project_" + LocalDate.now().getYear() + "_" + 1, userStoryId.toString(), "As a PO, i want to test this string", 1, "create user story", 5);
        userStoryStore.createAndSaveUserStory("Project_" + LocalDate.now().getYear() + "_" + 1, userStoryID2.toString(), "As a PO, i want to test this string", 3, "sort user story", 5);
        userStoryStore.createAndSaveUserStory("Project_" + LocalDate.now().getYear() + "_" + 1, userStoryID3.toString(), "As a PO, i want to test this string", 1, "backlog sorted", 5);

        // Assert
        assertEquals(3, userStoryStore.getUserStoryList().size());
        assertEquals(userStoryFactory, userStoryStore.getUserStoryFactory());
        assertEquals("Project_2022_1_As a PO, i want to test this string", userStoryStore.getUserStoryList().get(0).getUserStoryId().toString());
        assertEquals("Project_2022_2_As a PO, i want to test this string", userStoryStore.getUserStoryList().get(1).getUserStoryId().toString());
        assertEquals("Project_2022_3_As a PO, i want to test this string", userStoryStore.getUserStoryList().get(2).getUserStoryId().toString());
    }

    @Test
    @DisplayName("getUserStoryFactory - success")
    public void getUsFactory() {
        // Arrange & Act
        UserStoryFactory userStoryFactory = mock(UserStoryFactory.class);
        UserStoryStore userStoryStore = new UserStoryStore(userStoryFactory);
        // Assert
        assertEquals(userStoryFactory, userStoryStore.getUserStoryFactory());
        assertNotNull(userStoryFactory);
    }
}