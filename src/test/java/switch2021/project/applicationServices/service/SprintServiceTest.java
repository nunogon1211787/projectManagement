package switch2021.project.applicationServices.service;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
public class SprintServiceTest {
/*
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
    OutputSprintDTO outPutSprintDTO;


    @Test
    @DisplayName("Test to create and save sprint - get SprintName")
    public void createAndSaveSprint_SprintName() {

        //Arrange
        when(iSprintFactory.createSprint(newSprintDTO)).thenReturn(sprint);
        when(iSprintRepo.save(sprint)).thenReturn(true);
        when(sprintMapper.toDTO(sprint)).thenReturn(outPutSprintDTO);
        //Act
        OutputSprintDTO outPut = createSprintService.createAndSaveSprint(newSprintDTO);
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
        OutputSprintDTO outPut = createSprintService.createAndSaveSprint(newSprintDTO);
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

 */
}

