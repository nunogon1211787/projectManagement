package switch2021.project.model.Sprint;

import org.junit.jupiter.api.Test;
import switch2021.project.dto.OutputSprintDTO;
import switch2021.project.dto.SprintDTO;
import switch2021.project.factory.SprintFactory;
import switch2021.project.interfaces.SprintRepositoryInterface;
import switch2021.project.mapper.SprintMapper;
import switch2021.project.model.valueObject.Description;
import switch2021.project.model.valueObject.ProjectID;
import switch2021.project.service.CreateSprintService;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CreateSprintServiceTest {

    @Test
    void CreateAndSaveSprint() {
        // Arrange
        Sprint sprint = mock(Sprint.class);
        SprintID sprintID = mock(SprintID.class);
        when(sprint.getSprintID()).thenReturn(sprintID);
        when(sprintID.toString()).thenReturn("Project_2022_1_Sprint Name");
        ProjectID projectID = mock(ProjectID.class);
        when(sprint.getProjectID()).thenReturn(projectID);
        when(projectID.getCode()).thenReturn("Project_2022_1");
        Description description = mock(Description.class);
        when(sprint.getSprintName()).thenReturn(description);
        when(description.getText()).thenReturn("Sprint Name");

        SprintDTO sprintDTO = mock(SprintDTO.class);
        when(sprintDTO.getProjectID()).thenReturn("Project_2022_1");
        when(sprintDTO.getSprintID()).thenReturn("Project_2022_1_Sprint Name");
        when(sprintDTO.getName()).thenReturn("Sprint Name");

        SprintFactory sprintFactory = mock(SprintFactory.class);
        SprintStore sprintStore = mock(SprintStore.class);
        SprintMapper sprintMapper = mock(SprintMapper.class);
        SprintRepositoryInterface sprintRepositoryInterface = mock(SprintRepositoryInterface.class);
        CreateSprintService createSprintService = new CreateSprintService(sprintRepositoryInterface, sprintMapper,
                sprintFactory);
        OutputSprintDTO outputSprintDTO = mock(OutputSprintDTO.class);
        when(sprintMapper.toDTO(sprint)).thenReturn(outputSprintDTO);
        //when(outputSprintDTO.getProjectID()).thenReturn("Project_2022_1");
        when(outputSprintDTO.getSprintID()).thenReturn("Project_2022_1_Sprint Name");
        //when(outputSprintDTO.getName()).thenReturn("Sprint Name");

        when(sprintFactory.createSprint("Project_2022_1", "Project_2022_1_Sprint Name",
                "Sprint Name")).thenReturn(sprint);

        when(sprintStore.saveSprint(sprint)).thenReturn(true);

        // Act
        createSprintService.createAndSaveSprint(sprintDTO);

        // Assert
        assertEquals(sprintDTO.getSprintID(), outputSprintDTO.getSprintID());
        assertEquals("Project_2022_1",projectID.getCode());
        assertEquals("Sprint Name", sprintDTO.getName());
    }
}
