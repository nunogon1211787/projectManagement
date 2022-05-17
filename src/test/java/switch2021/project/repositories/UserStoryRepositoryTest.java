package switch2021.project.repositories;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import switch2021.project.model.UserStory.UserStory;
import switch2021.project.model.valueObject.*;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class UserStoryRepositoryTest {

    @Mock
    UserStory userStory;
    @Mock
    UserStoryID userStoryID;
    @Mock
    UsPriority priority;
    @Mock
    Description description;
    @Mock
    UsHour timeEstimate;
    @InjectMocks
    UserStoryRepository userStoryRepo;

    @Test
    @DisplayName("validateUserStory(UserStory newUserStory)")
    void validateUserStorySuccess() {
        //Arrange
        UserStory userStory1 = mock(UserStory.class);
        UserStory userStory2 = mock(UserStory.class);
        //Act
        when(userStory1.getDescription()).thenReturn(description);
        when(userStory2.getDescription()).thenReturn(description);
        //Assert
        assertEquals(userStory1.getDescription().getText(), userStory2.getDescription().getText());
    }

    @Test
    @DisplayName("validateUserStory(UserStory newUserStory)")
    void validateUserStoryFail() {
        //Arrange
        UserStory userStory1 = mock(UserStory.class);
        UserStory userStory2 = mock(UserStory.class);
        Description description1 = mock(Description.class);
        //Act
        when(userStory1.getDescription()).thenReturn(description);
        when(description.getText()).thenReturn("Make test");
        when(userStory2.getDescription()).thenReturn(description1);
        when(description1.getText()).thenReturn("Make test unit");
        //Assert
        assertNotEquals(userStory1.getDescription().getText(), userStory2.getDescription().getText());
    }

    @Test
    @DisplayName("save(UserStory newUserStory) - True Result")
    void SaveUserStoryWithSuccess() {
        //Arrange
        when(userStory.getUserStoryID()).thenReturn(userStoryID);
        when(userStory.getPriority()).thenReturn(priority);
        when(userStory.getDescription()).thenReturn(description);
        when(userStory.getTimeEstimate()).thenReturn(timeEstimate);
        //Act
        boolean expected = userStoryRepo.save(userStory);
        //Assert
        assertTrue(expected);
    }

    @Test
    @DisplayName("save(UserStory newUserStory) - False Result")
    void SaveUserStoryWithFail() {
        //Assert
        assertThrows(IllegalArgumentException.class, () -> {
            //Arrange
            when(userStory.getUserStoryID()).thenReturn(userStoryID);
            when(userStory.getPriority()).thenReturn(priority);
            when(userStory.getDescription()).thenReturn(description);
            when(userStory.getTimeEstimate()).thenReturn(timeEstimate);
            userStoryRepo.save(userStory);
            //Act
            userStoryRepo.save(userStory);
        });
    }

    @Test
    @DisplayName("grants a list of US that is sorted by priority. It keeps the done and/or cancelled US on the end\n")
    void FindUsSortedByPriority() {
        //Arrange
        when(userStory.getUserStoryID()).thenReturn(userStoryID);
        when(userStory.getDescription()).thenReturn(description);
        when(userStory.getTimeEstimate()).thenReturn(timeEstimate);
        UsPriority usPriority = mock(UsPriority.class);
        when(userStory.getPriority()).thenReturn(usPriority);
        when(usPriority.getPriorityUs()).thenReturn(3);
        userStoryRepo.save(userStory);
        UserStory userStory1 = mock(UserStory.class);
        UserStoryID userStoryID1 = mock(UserStoryID.class);
        ProjectID projectID1 = mock(ProjectID.class);
        UsTitle usTitle1 = mock(UsTitle.class);
        when(userStoryID1.getProjectID()).thenReturn(projectID1);
        when(projectID1.getCode()).thenReturn("Project_2021_1");
        when(userStoryID1.getUsTitle()).thenReturn(usTitle1);
        when(usTitle1.getTitleUs()).thenReturn("As a PO, i want to test this string");
        when(userStory1.getUserStoryID()).thenReturn(userStoryID1);
        when(userStory1.getDescription()).thenReturn(description);
        when(userStory1.getTimeEstimate()).thenReturn(timeEstimate);
        UsPriority usPriority1 = mock(UsPriority.class);
        when(userStory1.getPriority()).thenReturn(usPriority1);
        when(usPriority1.getPriorityUs()).thenReturn(1);
        userStoryRepo.save(userStory1);

        //Act
        List<UserStory> userStoryList = userStoryRepo.findUsSortedByPriority();
        //Assert
        assertEquals(2, userStoryRepo.getUserStoryList().size());
        assertEquals(1, userStoryList.get(0).getPriority().getPriorityUs());
        assertEquals(3, userStoryList.get(1).getPriority().getPriorityUs());
    }


    @Test
    @DisplayName("grants a list of US that is sorted by priority. It keeps the done and/or cancelled US on the end\n")
    void getSortedListWithSuccessUsCancelledAndEndDate() {
        //Arrange
        when(userStory.getUserStoryID()).thenReturn(userStoryID);
        when(userStory.getDescription()).thenReturn(description);
        when(userStory.getTimeEstimate()).thenReturn(timeEstimate);
        UsPriority usPriority = mock(UsPriority.class);
        when(userStory.getPriority()).thenReturn(usPriority);
        when(usPriority.getPriorityUs()).thenReturn(3);
        userStoryRepo.save(userStory);

        UserStory userStory1 = mock(UserStory.class);
        UserStoryID userStoryID1 = mock(UserStoryID.class);
        ProjectID projectID1 = mock(ProjectID.class);
        UsTitle usTitle1 = mock(UsTitle.class);
        when(userStoryID1.getProjectID()).thenReturn(projectID1);
        when(projectID1.getCode()).thenReturn("Project_2021_1");
        when(userStoryID1.getUsTitle()).thenReturn(usTitle1);
        when(usTitle1.getTitleUs()).thenReturn("As a PO, i want to test this string");
        when(userStory1.getUserStoryID()).thenReturn(userStoryID1);
        when(userStory1.getDescription()).thenReturn(description);
        when(userStory1.getTimeEstimate()).thenReturn(timeEstimate);
        UsPriority usPriority1 = mock(UsPriority.class);
        when(userStory1.getPriority()).thenReturn(usPriority1);
        when(usPriority1.getPriorityUs()).thenReturn(1);
        userStoryRepo.save(userStory1);

        UserStory userStory2 = mock(UserStory.class);
        UserStoryID userStoryID2 = mock(UserStoryID.class);
        ProjectID projectID2 = mock(ProjectID.class);
        UsTitle usTitle2 = mock(UsTitle.class);
        when(userStoryID2.getProjectID()).thenReturn(projectID2);
        when(projectID2.getCode()).thenReturn("Project_2022_1");
        when(userStoryID2.getUsTitle()).thenReturn(usTitle2);
        when(usTitle2.getTitleUs()).thenReturn("As a PO, i want to test this string");
        when(userStory2.getUserStoryID()).thenReturn(userStoryID2);
        when(userStory2.getDescription()).thenReturn(description);
        when(userStory2.getTimeEstimate()).thenReturn(timeEstimate);
        UsPriority usPriority2 = mock(UsPriority.class);
        when(userStory2.getPriority()).thenReturn(usPriority2);
        when(usPriority2.getPriorityUs()).thenReturn(1);
        when(userStory2.getUsCancelled()).thenReturn(LocalDate.now());
        userStoryRepo.save(userStory2);

        UserStory userStory3 = mock(UserStory.class);
        UserStoryID userStoryID3 = mock(UserStoryID.class);
        ProjectID projectID3 = mock(ProjectID.class);
        UsTitle usTitle3 = mock(UsTitle.class);
        when(userStoryID3.getProjectID()).thenReturn(projectID3);
        when(projectID3.getCode()).thenReturn("Project_2020_1");
        when(userStoryID3.getUsTitle()).thenReturn(usTitle3);
        when(usTitle3.getTitleUs()).thenReturn("As a PO, i want to test this string");
        when(userStory3.getUserStoryID()).thenReturn(userStoryID3);
        when(userStory3.getDescription()).thenReturn(description);
        when(userStory3.getTimeEstimate()).thenReturn(timeEstimate);
        UsPriority usPriority3 = mock(UsPriority.class);
        when(userStory3.getPriority()).thenReturn(usPriority3);
        when(usPriority3.getPriorityUs()).thenReturn(2);
        when(userStory3.getUsEndDate()).thenReturn(LocalDate.now());
        userStoryRepo.save(userStory3);

        //Act
        userStoryRepo.findByUserStoryId(userStoryID2.toString()).setUsCancelled(LocalDate.now());
        userStoryRepo.findByUserStoryId(userStoryID3.toString()).setUsEndDate(LocalDate.now());
        List<UserStory> userStoryList = userStoryRepo.findUsSortedByPriority();

        //Assert
        assertEquals(4, userStoryRepo.getUserStoryList().size());
        assertEquals(1, userStoryList.get(0).getPriority().getPriorityUs());
        assertEquals(3, userStoryList.get(1).getPriority().getPriorityUs());
        assertEquals(1, userStoryList.get(2).getPriority().getPriorityUs());
        assertEquals(2, userStoryList.get(3).getPriority().getPriorityUs());


    }

    @Test
    @DisplayName("findActiveUserStoryList() - with one US active and another one with end date defined (not active)")
    public void findActiveUserStoryListSuccessWithOneActiveAndTheOtherNot() {
        //Arrange
        when(userStory.getUserStoryID()).thenReturn(userStoryID);
        when(userStory.getDescription()).thenReturn(description);
        when(userStory.getTimeEstimate()).thenReturn(timeEstimate);
        UsPriority usPriority = mock(UsPriority.class);
        when(userStory.getPriority()).thenReturn(usPriority);
        when(usPriority.getPriorityUs()).thenReturn(3);
        userStoryRepo.save(userStory);

        UserStory userStory3 = mock(UserStory.class);
        UserStoryID userStoryID3 = mock(UserStoryID.class);
        ProjectID projectID3 = mock(ProjectID.class);
        UsTitle usTitle3 = mock(UsTitle.class);
        when(userStoryID3.getProjectID()).thenReturn(projectID3);
        when(projectID3.getCode()).thenReturn("Project_2020_1");
        when(userStoryID3.getUsTitle()).thenReturn(usTitle3);
        when(usTitle3.getTitleUs()).thenReturn("As a PO, i want to test this string");
        when(userStory3.getUserStoryID()).thenReturn(userStoryID3);
        when(userStory3.getDescription()).thenReturn(description);
        when(userStory3.getTimeEstimate()).thenReturn(timeEstimate);
        UsPriority usPriority3 = mock(UsPriority.class);
        when(userStory3.getPriority()).thenReturn(usPriority3);
        when(usPriority3.getPriorityUs()).thenReturn(2);
        when(userStory3.getUsEndDate()).thenReturn(LocalDate.now());
        userStoryRepo.save(userStory3);
        userStoryRepo.findByUserStoryId(userStoryID3.toString()).setUsEndDate(LocalDate.now());

        // Act
        List<UserStory> userStoryList = userStoryRepo.findActiveUserStoryList();
        // Assert
        assertEquals(1, userStoryList.size());
    }

    @Test
    @DisplayName("findAllUserStoryByProjectID(String projectID) - with success")
    public void findAllUserStoryByProjectIDSuccess() {
        //Arrange
        UserStory userStory3 = mock(UserStory.class);
        UserStoryID userStoryID3 = mock(UserStoryID.class);
        ProjectID projectID3 = mock(ProjectID.class);
        UsTitle usTitle3 = mock(UsTitle.class);
        when(userStoryID3.getProjectID()).thenReturn(projectID3);
        when(projectID3.getCode()).thenReturn("Project_2020_1");
        when(userStoryID3.getUsTitle()).thenReturn(usTitle3);
        when(usTitle3.getTitleUs()).thenReturn("As a PO, i want to test this string");
        when(userStory3.getUserStoryID()).thenReturn(userStoryID3);
        when(userStory3.getDescription()).thenReturn(description);
        when(userStory3.getTimeEstimate()).thenReturn(timeEstimate);
        UsPriority usPriority3 = mock(UsPriority.class);
        when(userStory3.getPriority()).thenReturn(usPriority3);
        when(usPriority3.getPriorityUs()).thenReturn(2);
        when(userStory3.getUsEndDate()).thenReturn(LocalDate.now());
        userStoryRepo.save(userStory3);

        //Act
        List<UserStory> userStoryList = userStoryRepo.findAllByProjectID(projectID3);
        //Assert
        assertEquals(1, userStoryList.size());
    }

    @Test
    @DisplayName("findAllUserStoryByProjectID(String projectID) - ProjectID don't exist")
    public void findAllUserStoryByProjectIDFail() {
        //Arrange
        UserStory userStory3 = mock(UserStory.class);
        UserStoryID userStoryID3 = mock(UserStoryID.class);
        ProjectID projectID3 = mock(ProjectID.class);
        ProjectID projectID4 = mock(ProjectID.class);
        UsTitle usTitle3 = mock(UsTitle.class);
        when(userStoryID3.getProjectID()).thenReturn(projectID3);
        when(projectID3.getCode()).thenReturn("Project_2020_1");
        when(projectID4.getCode()).thenReturn("Project_2020_2");
        when(userStoryID3.getUsTitle()).thenReturn(usTitle3);
        when(usTitle3.getTitleUs()).thenReturn("As a PO, i want to test this string");
        when(userStory3.getUserStoryID()).thenReturn(userStoryID3);
        when(userStory3.getDescription()).thenReturn(description);
        when(userStory3.getTimeEstimate()).thenReturn(timeEstimate);
        UsPriority usPriority3 = mock(UsPriority.class);
        when(userStory3.getPriority()).thenReturn(usPriority3);
        when(usPriority3.getPriorityUs()).thenReturn(2);
        when(userStory3.getUsEndDate()).thenReturn(LocalDate.now());
        userStoryRepo.save(userStory3);

        //Act
        List<UserStory> userStoryList = userStoryRepo.findAllByProjectID(projectID4);
        //Assert
        assertEquals(0, userStoryList.size());
    }

    @Test
    @DisplayName("findAllUserStories()")
    public void findAllUserStories() {
        //Arrange
        when(userStory.getUserStoryID()).thenReturn(userStoryID);
        when(userStory.getDescription()).thenReturn(description);
        when(userStory.getTimeEstimate()).thenReturn(timeEstimate);
        UsPriority usPriority = mock(UsPriority.class);
        when(userStory.getPriority()).thenReturn(usPriority);
        when(usPriority.getPriorityUs()).thenReturn(3);
        userStoryRepo.save(userStory);

        UserStory userStory3 = mock(UserStory.class);
        UserStoryID userStoryID3 = mock(UserStoryID.class);
        ProjectID projectID3 = mock(ProjectID.class);
        UsTitle usTitle3 = mock(UsTitle.class);
        when(userStoryID3.getProjectID()).thenReturn(projectID3);
        when(projectID3.getCode()).thenReturn("Project_2020_1");
        when(userStoryID3.getUsTitle()).thenReturn(usTitle3);
        when(usTitle3.getTitleUs()).thenReturn("As a PO, i want to test this string");
        when(userStory3.getUserStoryID()).thenReturn(userStoryID3);
        when(userStory3.getDescription()).thenReturn(description);
        when(userStory3.getTimeEstimate()).thenReturn(timeEstimate);
        UsPriority usPriority3 = mock(UsPriority.class);
        when(userStory3.getPriority()).thenReturn(usPriority3);
        when(usPriority3.getPriorityUs()).thenReturn(2);
        when(userStory3.getUsEndDate()).thenReturn(LocalDate.now());
        userStoryRepo.save(userStory3);

        //Act
        List<UserStory> userStoryList = userStoryRepo.findAllUserStories();
        //Assert
        assertEquals(2, userStoryList.size());
    }

    @Test
    @DisplayName("Test override conditions for coverage purposes")
    public void overrideTest() {
        //Arrange & Act
        UserStoryRepository userStoryRepository = new UserStoryRepository();
        UserStoryRepository userStoryRepository2 = new UserStoryRepository();

        //Assert
        assertEquals(userStoryRepository, userStoryRepository2);
        assertNotEquals(userStory, userStoryRepository);
        assertNotEquals(userStory, userStoryRepository);
    }


    @Test
    @DisplayName("Test hashcode conditions for coverage purposes")
    public void hashCodeSuccess() {
        //Arrange & Act
        UserStoryRepository userStoryRepository = new UserStoryRepository();
        UserStoryRepository userStoryRepository2 = new UserStoryRepository();
        //Assert
        assertEquals(userStoryRepository.hashCode(), userStoryRepository2.hashCode());
    }

    @Test
    @DisplayName("Test hashcode conditions for coverage purposes")
    public void hashCodeTestFail() {
        //Arrange & Act
        UserStoryRepository userStoryRepository = new UserStoryRepository();
        //Assert
        assertNotEquals(userStoryRepository.hashCode(), userStory.hashCode());
    }
}
