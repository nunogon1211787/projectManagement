package switch2021.project.model.UserStory;

import switch2021.project.dto.OutputUsDTO;
import switch2021.project.dto.UserStoryDTO;
import switch2021.project.factory.IUserStoryFactory;
import org.junit.jupiter.api.Test;
import switch2021.project.interfaces.UserStoryRepositoryInterface;
import switch2021.project.mapper.UserStoryMapper;
import switch2021.project.model.valueObject.Description;
import switch2021.project.model.valueObject.ProjectID;
import switch2021.project.service.UserStoryService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class UserStoryServiceTest {

    @Test
    void CreateAndSaveUserStory() {
        // Arrange
        UserStory userStory = mock(UserStory.class);
        UserStoryID userStoryID = mock(UserStoryID.class);
        when(userStory.getUserStoryID()).thenReturn(userStoryID);
        when(userStoryID.toString()).thenReturn("Project_2022_1_As a PO, i want to test this string");
        ProjectID projectID = mock(ProjectID.class);
        when(userStory.getProjectID()).thenReturn(projectID);
        when(projectID.getCode()).thenReturn("Project_2022_1");
        UsTitle usTitle = mock(UsTitle.class);
        when(userStory.getTitle()).thenReturn(usTitle);
        when(usTitle.getTitleUs()).thenReturn("As a PO, i want to test this string");
        UsPriority usPriority = mock(UsPriority.class);
        when(userStory.getPriority()).thenReturn(usPriority);
        when(usPriority.getPriorityUs()).thenReturn(1);
        Description description = mock(Description.class);
        when(userStory.getDescription()).thenReturn(description);
        when(description.getText()).thenReturn("Default Story");
        UsHour usHour = mock(UsHour.class);
        when(userStory.getTimeEstimate()).thenReturn(usHour);
        when(usHour.getUsHours()).thenReturn(5.0);

        UserStoryDTO userStoryDTO = mock(UserStoryDTO.class);
        when(userStoryDTO.getUserStoryId()).thenReturn("Project_2022_1_As a PO, i want to test this string");
        when(userStoryDTO.getProjectId()).thenReturn("Project_2022_1");
        when(userStoryDTO.getTitle()).thenReturn("As a PO, i want to test this string");
        when(userStoryDTO.getPriority()).thenReturn(1);
        when(userStoryDTO.getDescription()).thenReturn("Default Story");
        when(userStoryDTO.getTimeEstimate()).thenReturn(5.0);

        IUserStoryFactory userStoryFactory = mock(IUserStoryFactory.class);
        UserStoryStore userStoryStore = mock(UserStoryStore.class);
        UserStoryMapper userStoryMapper = mock(UserStoryMapper.class);
        UserStoryRepositoryInterface userStoryRepositoryInterface = mock(UserStoryRepositoryInterface.class);
        UserStoryService userStoryService = new UserStoryService(userStoryRepositoryInterface, userStoryMapper,
                userStoryFactory);
        OutputUsDTO outputUsDTO = mock(OutputUsDTO.class);
        when(userStoryMapper.toDto(userStory)).thenReturn(outputUsDTO);
        when(outputUsDTO.getProjectID()).thenReturn("Project_2022_1");
        when(outputUsDTO.getUserStoryID()).thenReturn("Project_2022_1_As a PO, i want to test this string");
        when(outputUsDTO.getTitle()).thenReturn("As a PO, i want to test this string");

        when(userStoryFactory.createUserStory("Project_2022_1", "Project_2022_1_As a PO, i want to test this string",
                "As a PO, i want to test this string", 1, "Default Story", 5)).thenReturn(userStory);

        when(userStoryStore.save(userStory)).thenReturn(true);

        // Act
        userStoryService.createAndSaveUserStory(userStoryDTO);

        // Assert
        assertEquals(userStoryDTO.getTitle(), outputUsDTO.getTitle());
        assertEquals(userStoryDTO.getUserStoryId(), outputUsDTO.getUserStoryID());
        assertEquals(userStoryDTO.getProjectId(), outputUsDTO.getProjectID());
        assertEquals("Default Story", userStoryDTO.getDescription());
        assertEquals(1, userStoryDTO.getPriority());
        assertEquals(5, userStoryDTO.getTimeEstimate());

    }
}
