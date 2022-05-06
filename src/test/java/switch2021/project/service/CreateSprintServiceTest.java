package switch2021.project.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import switch2021.project.dto.NewSprintDTO;
import switch2021.project.dto.OutPutSprintDTO;
import switch2021.project.factoryInterface.ISprintFactory;
import switch2021.project.interfaces.SprintRepositoryInterface;
import switch2021.project.mapper.SprintMapper;
import switch2021.project.model.Sprint.Sprint;
import switch2021.project.model.Sprint.SprintID;
import switch2021.project.model.valueObject.Description;
import switch2021.project.model.valueObject.ProjectID;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class CreateSprintServiceTest {

    @InjectMocks
    CreateSprintService createSprintService;
    @Mock
    SprintRepositoryInterface sprintRepositoryInterface;
    @Mock
    SprintMapper sprintMapper;
    @Mock
    ISprintFactory iSprintFactory;
    @Mock
    Sprint sprint;
    @Mock
    NewSprintDTO newSprintDTO;
    @Mock
    OutPutSprintDTO outPutSprintDTO;


    @Test
    public void createAndSaveSprint_SprintName() {

        //Arrange
        when(iSprintFactory.createSprint(newSprintDTO)).thenReturn(sprint);
        when(sprintRepositoryInterface.saveSprint(sprint)).thenReturn(true);
        when(sprintMapper.toDTO(sprint)).thenReturn(outPutSprintDTO);
        //Act
        OutPutSprintDTO outPut = createSprintService.createAndSaveSprint(newSprintDTO);
        //Assert
        assertEquals(outPut.name,
                createSprintService.createAndSaveSprint(newSprintDTO).name);
    }

    @Test
    public void createAndSaveSprint_ProjectID() {

        //Arrange
        when(iSprintFactory.createSprint(newSprintDTO)).thenReturn(sprint);
        when(sprintRepositoryInterface.saveSprint(sprint)).thenReturn(true);
        when(sprintMapper.toDTO(sprint)).thenReturn(outPutSprintDTO);
        //Act
        OutPutSprintDTO outPut = createSprintService.createAndSaveSprint(newSprintDTO);
        //Assert
        assertEquals(outPut.projectID,
                createSprintService.createAndSaveSprint(newSprintDTO).projectID);
    }
}

