package switch2021.project.service;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import switch2021.project.dto.OutputProjectDTO;
import switch2021.project.dto.ProjectDTO;
import switch2021.project.factoryInterface.IProjectFactory;
import switch2021.project.interfaces.IProjectRepo;
import switch2021.project.mapper.ProjectMapper;
import switch2021.project.model.Project.ProjectReeng;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class CreateProjectServiceTest {

    @MockBean
    private IProjectFactory IProjectFactory;
    @MockBean
    private ProjectMapper projectsMapper;
    @MockBean
    private IProjectRepo IProjectRepo;

    @Mock
    private ProjectReeng projectReeng;
    @Mock
    private ProjectDTO projectDTO;
    @Mock
    private OutputProjectDTO outputProjectDTO;

    @InjectMocks
    CreateProjectService createProjectService;

    @BeforeEach
    void TestConfiguration(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void shouldCreateProject() {
        //Arrange
        List <ProjectReeng> list = new ArrayList<>();

        when(IProjectFactory.createProject(projectDTO,1)).thenReturn(projectReeng);
        when(IProjectRepo.save(projectReeng)).thenReturn(projectReeng);
        when(projectsMapper.model2Dto(projectReeng)).thenReturn(outputProjectDTO);

        when(IProjectRepo.findAll()).thenReturn(list);

        //Act
        OutputProjectDTO outputProjectDTO = createProjectService.createAndSaveProject(projectDTO);

        //Assert
        assertEquals(projectDTO.projectName,outputProjectDTO.projectName);
    }

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