package switch2021.project.model.UserStory;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switch2021.project.factory.UserStoryFactory;
import switch2021.project.model.Company;
import switch2021.project.model.Project.Project;
import switch2021.project.model.Typology.Typology;
import switch2021.project.model.UserStory.UserStory;
import switch2021.project.model.UserStory.UserStoryStore;
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

        UserStoryStatus status = mock(UserStoryStatus.class);
        Description description = mock(Description.class);

        int priority = 1;
        String descriptionUS = "Default Story";
        String title = "As a PO, i want to test this string";
        String userStoryId = "Project_2022_1_As a PO, i want to test this string";

        UserStory newUserStory = new UserStory(userStoryId, title, priority, descriptionUS, 5);
        when(status.getDescription()).thenReturn(description);
        when(description.getText()).thenReturn("To do");

        when(userStoryFactory.createUserStory(userStoryId, title, priority, descriptionUS, 5)).thenReturn(newUserStory);
        //Act
        userStoryStore.createAndSaveUserStory(userStoryId, title, priority, descriptionUS, 5);
        // Assert
        assertEquals(priority, userStoryStore.getUserStoryList().get(0).getPriority().getPriorityUs());
        assertEquals(descriptionUS, userStoryStore.getUserStoryList().get(0).getDescription().getText());
        assertEquals(title, userStoryStore.getUserStoryList().get(0).getTitle().getTitleUs());
        assertEquals(userStoryId, userStoryStore.getUserStoryList().get(0).getUserStoryId().toString());
    }

    @Test
    @DisplayName("Create and save user story fail - empty description")
    public void createUserStorydescriptionInvalidEmpty() {
        // Assert
        assertThrows(IllegalArgumentException.class, () -> {
            // Arrange
            UserStoryFactory userStoryFactory = new UserStoryFactory();
            UserStoryStore userStoryStore = new UserStoryStore(userStoryFactory);
            int priority = 1;
            String description = "";
            String userStoryId = "Project_2022_1_As a PO, i want to test this string";
            // Act
            userStoryStore.createAndSaveUserStory(userStoryId, "US001", priority, description, 5);
        });

    }

    @Test
    @DisplayName("Create and save user story fail - empty description")
    public void createUserStorydescriptionInvalidEmptyMock() {
        // Assert
        assertThrows(IllegalArgumentException.class, () -> {
            // Arrange
            UserStoryFactory userStoryFactory = mock(UserStoryFactory.class);
            UserStoryStore userStoryStore = new UserStoryStore(userStoryFactory);
            String userStoryId = "Project_2022_1_As a PO, i want to test this string";

            String descriptionUS = "";
            int priority = 1;

            when(userStoryFactory.createUserStory(userStoryId, "As director i want create US", priority, descriptionUS, 5)).thenThrow(new IllegalArgumentException("Description field requires at least " + 1 + " characters"));
            // Act
            userStoryStore.createAndSaveUserStory(userStoryId, "As director i want create US", priority, descriptionUS, 5);
        });

    }


    @Test
    @DisplayName("Create and save user story success - description with one character")
    public void createUserStorySuccessFullOnLimit() {
        // Arrange
        UserStoryFactory userStoryFactory = new UserStoryFactory();
        UserStoryStore userStoryStore = new UserStoryStore(userStoryFactory);
        UserStoryStatus status = new UserStoryStatus("To do", true);
        int priority = 1;
        String description = "D";
        String title = "As a PO, i want to test this string";
        String userStoryId = "Project_2022_1_As a PO, i want to test this string";
        //Act
        userStoryStore.createAndSaveUserStory(userStoryId, title, priority, description, 5);
        // Assert
        assertEquals(priority, userStoryStore.getUserStoryList().get(0).getPriority().getPriorityUs());
        assertEquals(description, userStoryStore.getUserStoryList().get(0).getDescription().getText());
        assertEquals(title, userStoryStore.getUserStoryList().get(0).getTitle().getTitleUs());
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
            // Act
            userStoryStore.createAndSaveUserStory(userStoryId, "US001", priority, description, 5);
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
            // Act
            userStoryStore.createAndSaveUserStory(userStoryId, "US001", priority, description, 5);
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
            // Act
            userStoryStore.createAndSaveUserStory(userStoryId, "US001", priority, description, 5);
            userStoryStore.createAndSaveUserStory(userStoryId, "US001", priority, description, 5);
        });
    }


    @Test
    @DisplayName("grants a list of US that is sorted by priority. It keeps the done and/or cancelled US on the end\n")
    public void getSortedListWithSuccess() {
        // Arrange
        UserStoryFactory userStoryFactory = new UserStoryFactory();
        UserStoryStore userStoryStore = new UserStoryStore(userStoryFactory);
        UserStoryId userStoryId = new UserStoryId("Project_2022_1_As a PO, i want to test this string");
        UserStoryId userStoryId2 = new UserStoryId("Project_2022_2_As a PO, i want to test this string");
        UserStoryId userStoryId3 = new UserStoryId("Project_2022_3_As a PO, i want to test this string");
        UserStoryId userStoryId4 = new UserStoryId("Project_2022_4_As a PO, i want to test this string");
        userStoryStore.createAndSaveUserStory(userStoryId.toString(), "As a PO, i want to test this string", 1, "create user story", 5);
        userStoryStore.createAndSaveUserStory(userStoryId2.toString(), "As a PO, i want to test this string", 3, "sort user story", 5);
        userStoryStore.createAndSaveUserStory(userStoryId3.toString(), "As a PO, i want to test this string", 2, "backlog sorted", 5);
        userStoryStore.createAndSaveUserStory(userStoryId4.toString(), "As a PO, i want to test this string", 5, "show sorted", 5);

        // Act
        List<UserStory> userStoryList = userStoryStore.getUsSortedByPriority();

        // Assert
        assertEquals(4, userStoryList.size());
        assertEquals(1, userStoryList.get(0).getPriority().getPriorityUs());
        assertEquals(2, userStoryList.get(1).getPriority().getPriorityUs());
        assertEquals(3, userStoryList.get(2).getPriority().getPriorityUs());
        assertEquals(5, userStoryList.get(3).getPriority().getPriorityUs());

    }

    @Test
    @DisplayName("get exception message \"Check priority, cannot be < 0 or superior to 5.“")
    public void getSortedListFailWrongPriority() {
        // Arrange
        UserStoryFactory userStoryFactory = new UserStoryFactory();
        UserStoryStore userStoryStore = new UserStoryStore(userStoryFactory);
        UserStoryId userStoryId = new UserStoryId("Project_2022_1_As a PO, i want to test this string");
        // Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            userStoryStore.createAndSaveUserStory(userStoryId.toString(), "As a PO, i want to test this string", 6, "sort user story", 5);
            userStoryStore.getUsSortedByPriority();
        });

        // Assert
        assertTrue(exception.getMessage().contains("Check priority, cannot be < 0 or superior to 5"));
    }

    @Test
    @DisplayName("Create UserStory Refine Success")
    public void createUserStoryRefineSucess() {
        // Arrange
        Company company = new Company();
        UserStoryFactory userStoryFactory = new UserStoryFactory();
        UserStoryStore userStoryStore = new UserStoryStore(userStoryFactory);
        String userStoryId = "Project_2022_1_As a PO, i want to test this string";
        userStoryStore.createAndSaveUserStory(userStoryId, "As a PO, i want to test this string", 4, "123testtest", 5);


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
            Company company = new Company();
            UserStoryFactory userStoryFactory = new UserStoryFactory();
            UserStoryStore userStoryStore = new UserStoryStore(userStoryFactory);
            String userStoryId = "Project_2022_1_As a PO, i want to test this string";
            userStoryStore.createAndSaveUserStory(userStoryId, "As a PO, i want to test this string", 4, "123testtest", 5);
            // Act
            new UserStory(userStoryStore.getUserStoryList().get(0), 5, "");
        });
    }

    @Test
    @DisplayName("Create UserStory Refine Fail - Description too Short")
    public void createUserStoryRefineSucessOnLimit() {
        // Arrange
        Company company = new Company();
        UserStoryFactory userStoryFactory = new UserStoryFactory();
        UserStoryStore userStoryStore = new UserStoryStore(userStoryFactory);
        String userStoryId = "Project_2022_1_As a PO, i want to test this string";
        userStoryStore.createAndSaveUserStory(userStoryId, "As a PO, i want to test this string", 4, "123testtest", 5);

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
        Company company = new Company();
        UserStoryId userStoryId = new UserStoryId("Project_2022_1_As a PO, i want to test this string");
        company.getUserStoryStore().createAndSaveUserStory(userStoryId.toString(), "As a PO, i want to test this string", 4, "123testtest", 5);

        assertThrows(IllegalArgumentException.class, () -> {
            company.getUserStoryStore().refineUserStory(company.getUserStoryStore().getUserStoryList().get(0), 4, "123testtest");
        });
    }

    @Test
    @DisplayName("Create/save UserStory Refine Fail - Priority Low")
    public void createUserStoryRefinePriorityLowFail() {
        Company company = new Company();
        String userStoryId = "Project_2022_1_As a PO, i want to test this string";
        company.getUserStoryStore().createAndSaveUserStory(userStoryId, "As a PO, i want to test this string", 4, "123testtest", 5);

        assertThrows(IllegalArgumentException.class, () -> {
            new UserStory(company.getUserStoryStore().getUserStoryList().get(0), -1, "456testtest");
        });
    }

    @Test
    @DisplayName("Create/save UserStory Refine Fail - Priority High")
    public void createUserStoryRefinePriorityHighFail() {
        Company company = new Company();
        String userStoryId = "Project_2022_1_As a PO, i want to test this string";
        company.getUserStoryStore().createAndSaveUserStory(userStoryId, "As a PO, i want to test this string", 4, "123testtest", 5);

        assertThrows(IllegalArgumentException.class, () -> {

            new UserStory(company.getUserStoryStore().getUserStoryList().get(0), 6, "456testtest");
        });
    }

    @Test
    @DisplayName("get User Story By Id Success")
    public void getUserStoryByIdSucess() {
        Company company = new Company();
        UserStoryId userStoryId = new UserStoryId("Project_2022_1_As a PO, i want to test this string");
        company.getUserStoryStore().createAndSaveUserStory(userStoryId.toString(), "As a PO, i want to test this string", 4, "123testtest", 5);

        assertEquals("Project_2022_1_As a PO, i want to test this string", company.getUserStoryStore().getUserStoryList().get(0).getUserStoryId().toString());
    }

    @Test
    @DisplayName("get User Story By Id Fail")
    public void getUserStoryByIdFail() {
        Company company = new Company();
        String userStoryId1 = "Project_2022_2_As a PO, i want to test this string";
        UserStoryId userStoryId = new UserStoryId("Project_2022_1_As a PO, i want to test this string");
        company.getUserStoryStore().createAndSaveUserStory(userStoryId1, "As a PO, i want to test this string", 4, "123testtest", 5);

        assertNull(company.getUserStoryStore().findUserStoryById(userStoryId));
        assertNotEquals(1, company.getUserStoryStore().findUserStoryById(userStoryId));

    }

    //TODO CDC alterar teste uma vez que deixamos de ter status completed - alterar para data introduzida

//    @Test
//    @DisplayName("getActiveUserStoryList_fail_not active")
//    public void getActiveUserStoryListFailCompleted() {
//        // Arrange
//        UserStoryFactory userStoryFactory = new UserStoryFactory();
//        ProductBacklog productBacklog = new ProductBacklog(userStoryFactory);
//        int priority = 1;
//        String description = "Create user story";
//
//        productBacklog.createAndSaveUserStory(
//                "As a PO, i want to test this string", priority, description, 5);
//        productBacklog.getUserStoryList().get(0).setUserStoryStatus(new UserStoryStatus("Completed", true));
//
//        // Act
//        List<UserStory> userStoryList = productBacklog.getActiveUserStoryList();
//        // Assert
//        assertEquals(0, userStoryList.size());
//
//    }

    @Test
    @DisplayName("set user story list")
    public void CreateAndSaveUserStorySuccess() {
        // Arrange
        Company company = mock(Company.class);
        UserStoryFactory userStoryFactory = mock(UserStoryFactory.class);
        UserStoryStore userStoryStore = new UserStoryStore(userStoryFactory);
        when(company.getUserStoryStore()).thenReturn(userStoryStore);
        UserStory userStory = mock(UserStory.class);
        Description description1 = mock(Description.class);
        UserStoryId userStoryId = mock(UserStoryId.class);


        when(userStory.getDescription()).thenReturn(description1);
        when(description1.getText()).thenReturn("create user story");

        when(userStory.getUserStoryId()).thenReturn(userStoryId);
        when(userStoryId.toString()).thenReturn("Project_2022_1_As a PO, i want to test this string");


        when(userStoryFactory.createUserStory("Project_2022_1_As a PO, i want to test this string", "US001", 1, "create user story", 5)).thenReturn(userStory);
        userStoryStore.createAndSaveUserStory("Project_2022_1_As a PO, i want to test this string", "US001", 1, "create user story", 5);

        // Assert
        assertEquals(1, userStoryStore.getUserStoryList().size());


    }

    @Test
    @DisplayName("validate ID user Story")
    public void validateID() {
        // Arrange
        UserStoryFactory userStoryFactory = new UserStoryFactory();
        UserStoryStore userStoryStore = new UserStoryStore(userStoryFactory);
        UserStoryId userStoryId = new UserStoryId("Project_2022_1_As a PO, i want to test this string");
        UserStoryId userStoryId2 = new UserStoryId("Project_2022_2_As a PO, i want to test this string");
        UserStoryId userStoryId3 = new UserStoryId("Project_2022_3_As a PO, i want to test this string");

        // Act
        userStoryStore.createAndSaveUserStory(userStoryId.toString(), "As a PO, i want to test this string", 1, "create user story", 5);
        userStoryStore.createAndSaveUserStory(userStoryId2.toString(), "As a PO, i want to test this string", 3, "sort user story", 5);
        userStoryStore.createAndSaveUserStory(userStoryId3.toString(), "As a PO, i want to test this string", 1, "backlog sorted", 5);

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