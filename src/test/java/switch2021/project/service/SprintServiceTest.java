package switch2021.project.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import switch2021.project.dto.NewSprintDTO;
import switch2021.project.dto.OutPutSprintDTO;
import switch2021.project.factoryInterface.ISprintFactory;
import switch2021.project.interfaces.ISprintRepo;
import switch2021.project.mapper.SprintMapper;
import switch2021.project.model.Sprint.Sprint;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class SprintServiceTest {

    @InjectMocks
    SprintService createSprintService;
    @Mock
    ISprintRepo iSprintRepo;
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
    @DisplayName("Test to create and save sprint - get SprintName")
    public void createAndSaveSprint_SprintName() {

        //Arrange
        when(iSprintFactory.createSprint(newSprintDTO)).thenReturn(sprint);
        when(iSprintRepo.save(sprint)).thenReturn(true);
        when(sprintMapper.toDTO(sprint)).thenReturn(outPutSprintDTO);
        //Act
        OutPutSprintDTO outPut = createSprintService.createAndSaveSprint(newSprintDTO);
        //Assert
        assertEquals(outPut.name,
                createSprintService.createAndSaveSprint(newSprintDTO).name);
    }

    @Test
    @DisplayName("Test to create and save sprint - get ProjectID")
    public void createAndSaveSprint_ProjectID() {

        //Arrange
        when(iSprintFactory.createSprint(newSprintDTO)).thenReturn(sprint);
        when(iSprintRepo.save(sprint)).thenReturn(true);
        when(sprintMapper.toDTO(sprint)).thenReturn(outPutSprintDTO);
        //Act
        OutPutSprintDTO outPut = createSprintService.createAndSaveSprint(newSprintDTO);
        //Assert
        assertEquals(outPut.projectID,
                createSprintService.createAndSaveSprint(newSprintDTO).projectID);
    }

    @Test
    @DisplayName("Test to create a repeated sprint")
    public void createAndSaveRepeatedSprint() {
        //Assert
        assertThrows(IllegalArgumentException.class, () -> {
            //Arrange
            when(iSprintFactory.createSprint(newSprintDTO)).thenReturn(sprint);
            when(iSprintRepo.save(sprint)).thenReturn(false);
            when(sprintMapper.toDTO(sprint)).thenReturn(outPutSprintDTO);
            //Act
            createSprintService.createAndSaveSprint(newSprintDTO);
        });
    }
}

