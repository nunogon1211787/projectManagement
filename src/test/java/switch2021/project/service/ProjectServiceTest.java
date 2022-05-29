package switch2021.project.service;


import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import switch2021.project.dto.OutputProjectDTO;
import switch2021.project.dto.ProjectDTO;
import switch2021.project.factoryInterface.IProjectFactory;
import switch2021.project.repositories.jpa.ProjectJpaRepository;
import switch2021.project.mapper.ProjectMapper;
import switch2021.project.model.Project.Project;

@SpringBootTest
class ProjectServiceTest {

    @MockBean
    private IProjectFactory IProjectFactory;
    @MockBean
    private ProjectMapper projectsMapper;
    @MockBean
    private ProjectJpaRepository ProjectJpaRepository;

    @Mock
    private Project project;
    @Mock
    private ProjectDTO projectDTO;
    @Mock
    private OutputProjectDTO outputProjectDTO;

    @InjectMocks
    ProjectService projectService;

    @BeforeEach
    void TestConfiguration(){
        MockitoAnnotations.openMocks(this);
    }

/*    @Test
    public void shouldCreateProject() {
        //Arrange
        List <ProjectReeng> list = new ArrayList<>();

        when(IProjectFactory.createProject(projectDTO,1)).thenReturn(projectReeng);
        when(ProjectJpaRepository.saveProject(projectReeng)).thenReturn(projectReeng);
        when(projectsMapper.model2Dto(projectReeng)).thenReturn(outputProjectDTO);

        when(ProjectJpaRepository.findAll()).thenReturn(list);

        //Act
        OutputProjectDTO outputProjectDTO = createProjectService.createAndSaveProject(projectDTO);

        //Assert
        assertEquals(projectDTO.projectName,outputProjectDTO.projectName);
    }*/

//    @Test
//    public void shouldEditProject() {
//        //Arrange
//        when(projectRepositoryInterface.findById(any())).thenReturn(projectReeng);
//        when(projectsMapper.model2Dto(any())).thenReturn(outputProjectDTO);
//        when(projectRepositoryInterface.findById(any())).thenReturn(projectReeng);
//
//
//        String code = "Project_2022_1";
//       String projectName = "Project";
//        String description = "Description";
//        String typology = "";
//        String businessSector = "BusinessSector";
//       String startDate = "2025-12-05";
//        String numberOfSprints = "12";
//        String budget = "1200";
//       String projectStatus = "PLANNED";
//       String sprintDuration = "15";
//        ProjectDTO projectDTO = new ProjectDTO(code, projectName,description,businessSector,startDate,numberOfSprints,budget,projectStatus);
//
//        //Act
//        OutputProjectDTO outputProjectDTO = createProjectService.editProject(projectDTO);
//        OutputProjectDTO expected = projectsMapper.model2Dto(projectReeng);
//
//        //Arrange
//        assertEquals(expected, outputProjectDTO);
//    }
}