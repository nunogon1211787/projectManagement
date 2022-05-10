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
import switch2021.project.factoryInterface.ProjectFactoryInterface;
import switch2021.project.interfaces.ProjectRepositoryInterface;
import switch2021.project.mapper.ProjectMapper;
import switch2021.project.model.Project.ProjectReeng;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class ProjectServiceTest {

    @MockBean
    private ProjectFactoryInterface projectFactoryInterface;
    @MockBean
    private ProjectMapper projectsMapper;
    @MockBean
    private ProjectRepositoryInterface projectRepositoryInterface;

    @Mock
    private ProjectReeng projectReeng;
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

    @Test
    public void shouldCreateProject() {
        //Arrange
        List <ProjectReeng> list = new ArrayList<>();

        when(projectFactoryInterface.createProject(projectDTO,1)).thenReturn(projectReeng);
        when(projectRepositoryInterface.save(projectReeng)).thenReturn(projectReeng);
        when(projectsMapper.model2Dto(projectReeng)).thenReturn(outputProjectDTO);

        when(projectRepositoryInterface.findAll()).thenReturn(list);

        //Act
        OutputProjectDTO outputProjectDTO = projectService.createAndSaveProject(projectDTO);

        //Assert
        assertEquals(projectDTO.projectName,outputProjectDTO.projectName);
    }

    @Test
    public void shouldEditProject() {
        //Arrange
        when(projectRepositoryInterface.findById(any())).thenReturn(projectReeng);
        when(projectsMapper.model2Dto(any())).thenReturn(outputProjectDTO);
        when(projectRepositoryInterface.findById(any())).thenReturn(projectReeng);


        ProjectDTO projectDTO = new ProjectDTO();
        projectDTO.code = "Project_2022_1";
        projectDTO.projectName = "Project";
        projectDTO.description = "Description";
        projectDTO.typology = "";
        projectDTO.businessSector = "BusinessSector";
        projectDTO.startDate = "2025-12-05";
        projectDTO.numberOfSprints = "12";
        projectDTO.budget = "1200";
        projectDTO.projectStatus = "PLANNED";
        projectDTO.sprintDuration = "15";

        //Act
        OutputProjectDTO outputProjectDTO = projectService.editProject(projectDTO);
        OutputProjectDTO expected = projectsMapper.model2Dto(projectReeng);

        //Arrange
        assertEquals(expected, outputProjectDTO);
    }
}