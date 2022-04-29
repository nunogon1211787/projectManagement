package switch2021.project.model.Sprint;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import switch2021.project.dto.SprintDTO;
import org.junit.jupiter.api.Test;
import switch2021.project.factoryInterface.ISprintFactory;
import switch2021.project.interfaces.SprintRepositoryInterface;
import switch2021.project.mapper.SprintMapper;
import switch2021.project.service.CreateSprintService;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class CreateSprintServiceTest {
//
//    @InjectMocks
//    CreateSprintService createSprintService;
//
//    @Mock
//    private SprintRepositoryInterface sprintRepositoryInterface;
//    @Mock
//    private SprintMapper sprintMapper;
//    @Mock
//    private ISprintFactory iSprintFactory;
//
//
//    @Test
//    public void createAndSaveUserStory() {
//
//        //Arrange
//        SprintDTO sprintDTO = new SprintDTO();
//        sprintDTO.projectID = "Project_2022_1";
//        sprintDTO.name = "Sprint Name";
//
//        OutputSprintDTO outputSprintDTO = new OutputSprintDTO();
//        outputSprintDTO.sprintID = "Project_2022_1_Sprint Name";
//
//        //Act
//        Sprint sprint = new Sprint();
//
//        when(iSprintFactory.createSprint(sprintDTO.projectID, sprintDTO.name)).thenReturn(sprint);
//
//        when(sprintRepositoryInterface.saveSprint(any(Sprint.class))).thenReturn(true);
//
//        when(sprintMapper.toDTO(sprint)).thenReturn(outputSprintDTO);
//
//        OutputSprintDTO saveSprint = createSprintService.createAndSaveSprint(sprintDTO);
//
//        //Assert
//        assertEquals(outputSprintDTO.sprintID, sprintDTO.getSprintID());
//    }
}

