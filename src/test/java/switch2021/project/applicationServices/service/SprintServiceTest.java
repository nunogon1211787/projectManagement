package switch2021.project.applicationServices.service;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import switch2021.project.applicationServices.iRepositories.IProjectRepo;
import switch2021.project.applicationServices.iRepositories.ISprintRepo;
import switch2021.project.applicationServices.iRepositories.IUserStoryOfSprintRepo;
import switch2021.project.applicationServices.iRepositories.IUserStoryRepo;
import switch2021.project.dtoModel.dto.OutputSprintDTO;
import switch2021.project.dtoModel.mapper.SprintMapper;
import switch2021.project.dtoModel.mapper.UserStoryOfSprintMapper;
import switch2021.project.entities.aggregates.Project.Project;
import switch2021.project.entities.aggregates.Sprint.Sprint;
import switch2021.project.entities.factories.factoryInterfaces.ISprintFactory;
import switch2021.project.entities.valueObjects.vos.SprintDuration;

import java.util.Optional;

@SpringBootTest
@AutoConfigureMockMvc
public class SprintServiceTest {

    @InjectMocks
    SprintService sprintService;
    @Mock
    private ISprintRepo sprintRepo;
    @Mock
    private SprintMapper sprintMapper;
    @Mock
    private ISprintFactory sprintFactory;
    @Mock
    private IUserStoryRepo usRepo;
    @Mock
    private IProjectRepo projRepo;
    @Mock
    private IUserStoryOfSprintRepo userStoryOfSprintRepo;
    @Mock
    private UserStoryOfSprintMapper userStoryOfSprintMapper;
/*    @Mock
    private Sprint sprint;
    @Mock
    private Optional<Sprint> savedSprint;
    @Mock
    private Optional<Project> savedProject;
    @Mock
    private OutputSprintDTO outputSprintDTO;
    @Mock
    private Project project;
    @Mock
    private SprintDuration sprintDurationClass;*/


/*    @SneakyThrows
    @Test
    void createAndSaveSprint() {
        NewSprintDTO newSprint = mock(NewSprintDTO.class);
        String string = "Project_2022_1_1";
        String date = "2022-01-01";

        when(sprintFactory.createSprint(newSprint)).thenReturn(sprint);
        when(sprintService.validateSprintStartDate(string, date)).thenCallRealMethod();

        when(sprintRepo.save(sprint)).thenReturn(savedSprint);
        when(savedSprint.get()).thenReturn(sprint);
        when(sprintMapper.toDTO(savedSprint.get())).thenReturn(outputSprintDTO);

        OutputSprintDTO result = sprintService.createAndSaveSprint(newSprint);

        assertEquals(outputSprintDTO, result);
    }*/

}

