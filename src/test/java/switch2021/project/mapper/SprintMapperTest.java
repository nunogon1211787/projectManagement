package switch2021.project.mapper;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switch2021.project.dto.OutPutSprintDTO;
import switch2021.project.model.Sprint.Sprint;
import switch2021.project.model.valueObject.SprintID;
import switch2021.project.model.valueObject.Description;
import switch2021.project.model.valueObject.ProjectID;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SprintMapperTest {

    @Test
    @DisplayName("toDTO test")
    public void toDTOSuccess() {
        //Arrange
        SprintMapper sprintMapper = new SprintMapper();
        Sprint sprint = mock(Sprint.class);
        SprintID sprintID = mock(SprintID.class);
        Description description = mock(Description.class);
        ProjectID projectID = mock(ProjectID.class);
        //Act
        when(sprint.getSprintID()).thenReturn(sprintID);
        when(sprintID.getSprintName()).thenReturn(description);
        when(description.getText()).thenReturn("Sprint Name");
        when(sprintID.getProjectID()).thenReturn(projectID);
        when(projectID.getCode()).thenReturn("Project_2022_1");

        OutPutSprintDTO outPutSprintDTO = sprintMapper.toDTO(sprint);

        //Assert
        assertEquals(sprint.getSprintID().toString(), outPutSprintDTO.sprintID);
    }

    @Test
    @DisplayName("toDTO test, SprintID")
    public void toDTOSuccess_SprintID() {
        //Arrange
        SprintMapper sprintMapper = new SprintMapper();
        Sprint sprint = mock(Sprint.class);
        SprintID sprintID = mock(SprintID.class);
        Description description = mock(Description.class);
        ProjectID projectID = mock(ProjectID.class);
        //Act
        when(sprint.getSprintID()).thenReturn(sprintID);
        when(sprintID.getSprintName()).thenReturn(description);
        when(description.getText()).thenReturn("Sprint Name");
        when(sprintID.getProjectID()).thenReturn(projectID);
        when(projectID.getCode()).thenReturn("Project_2022_1");
        OutPutSprintDTO outPutSprintDTO = sprintMapper.toDTO(sprint);
        //Assert
        assertEquals(sprintID.toString(), outPutSprintDTO.sprintID);
    }

    @Test
    @DisplayName("toDTO test, get Sprint Name")
    public void toDTOSuccess_SprintName() {
        //Arrange
        SprintMapper sprintMapper = new SprintMapper();
        Sprint sprint = mock(Sprint.class);
        SprintID sprintID = mock(SprintID.class);
        Description description = mock(Description.class);
        ProjectID projectID = mock(ProjectID.class);
        //Act
        when(sprint.getSprintID()).thenReturn(sprintID);
        when(sprintID.getSprintName()).thenReturn(description);
        when(description.getText()).thenReturn("Sprint Name");
        when(sprintID.getProjectID()).thenReturn(projectID);
        when(projectID.getCode()).thenReturn("Project_2022_1");
        OutPutSprintDTO outPutSprintDTO = sprintMapper.toDTO(sprint);
        //Assert
        assertEquals(description.getText(), outPutSprintDTO.name);
    }

    @Test
    @DisplayName("toDTO test, get ProjectID")
    public void toDTOSuccess_ProjectID() {
        //Arrange
        SprintMapper sprintMapper = new SprintMapper();
        Sprint sprint = mock(Sprint.class);
        SprintID sprintID = mock(SprintID.class);
        Description description = mock(Description.class);
        ProjectID projectID = mock(ProjectID.class);
        //Act
        when(sprint.getSprintID()).thenReturn(sprintID);
        when(sprintID.getSprintName()).thenReturn(description);
        when(description.getText()).thenReturn("Sprint Name");
        when(sprintID.getProjectID()).thenReturn(projectID);
        when(projectID.getCode()).thenReturn("Project_2022_1");
        OutPutSprintDTO outPutSprintDTO = sprintMapper.toDTO(sprint);
        //Assert
        assertEquals(projectID.getCode(), outPutSprintDTO.projectID);
    }

    @Test
    @DisplayName("Test OutPutSprintDTO - SprintID")
    public void outPutSprintDTO_SprintID() {
        //Arrange
        SprintMapper sprintMapper = new SprintMapper();
        Sprint sprint = mock(Sprint.class);
        SprintID sprintID = mock(SprintID.class);
        Description description = mock(Description.class);
        ProjectID projectID = mock(ProjectID.class);

        //Act
        when(sprint.getSprintID()).thenReturn(sprintID);
        when(sprintID.getProjectID()).thenReturn(projectID);
        when(projectID.getCode()).thenReturn("Project_2022_1");
        when(sprintID.getSprintName()).thenReturn(description);
        when(description.getText()).thenReturn("Sprint Name");

        OutPutSprintDTO outPutSprintDTO = sprintMapper.toDTO(sprint);

        //Assert
        assertEquals(outPutSprintDTO.getSprintID(), sprintID.toString());
    }

    @Test
    @DisplayName("Test OutPutSprintDTO - ProjectID")
    public void outPutSprintDTO_ProjectID() {
        //Arrange
        SprintMapper sprintMapper = new SprintMapper();
        Sprint sprint = mock(Sprint.class);
        SprintID sprintID = mock(SprintID.class);
        Description description = mock(Description.class);
        ProjectID projectID = mock(ProjectID.class);

        //Act
        when(sprint.getSprintID()).thenReturn(sprintID);
        when(sprintID.getProjectID()).thenReturn(projectID);
        when(projectID.getCode()).thenReturn("Project_2022_1");
        when(sprintID.getSprintName()).thenReturn(description);
        when(description.getText()).thenReturn("Sprint Name");

        OutPutSprintDTO outPutSprintDTO = sprintMapper.toDTO(sprint);

        //Assert
        assertEquals(outPutSprintDTO.getProjectID(), "Project_2022_1");
    }

    @Test
    @DisplayName("Test OutPutSprintDTO - Sprint Name")
    public void outPutSprintDTO_SprintName() {
        //Arrange
        SprintMapper sprintMapper = new SprintMapper();
        Sprint sprint = mock(Sprint.class);
        SprintID sprintID = mock(SprintID.class);
        Description description = mock(Description.class);
        ProjectID projectID = mock(ProjectID.class);

        //Act
        when(sprint.getSprintID()).thenReturn(sprintID);
        when(sprintID.getProjectID()).thenReturn(projectID);
        when(projectID.getCode()).thenReturn("Project_2022_1");
        when(sprintID.getSprintName()).thenReturn(description);
        when(description.getText()).thenReturn("Sprint Name");

        OutPutSprintDTO outPutSprintDTO = sprintMapper.toDTO(sprint);

        //Assert
        assertEquals(outPutSprintDTO.getName(), "Sprint Name");
    }
}
