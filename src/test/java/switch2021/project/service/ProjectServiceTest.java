package switch2021.project.service;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import switch2021.project.dto.OutputProjectDTO;
import switch2021.project.dto.ProjectDTO;
import switch2021.project.factoryInterface.ProjectFactoryInterface;
import switch2021.project.interfaces.ProjectRepositoryInterface;
import switch2021.project.mapper.ProjectMapper;
import switch2021.project.model.Project.ProjectReeng;
import switch2021.project.repositories.ProjectStoreReeng;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class ProjectServiceTest {

    @InjectMocks
    ProjectService projectService;

    @Mock
    private ProjectFactoryInterface projectFactoryInterface;
    @Mock
    private ProjectMapper projectsMapper;
    @Mock
    private ProjectStoreReeng projectStore;
    @Mock
    private ProjectReeng projectReeng;
    @Mock
    private ProjectDTO projectDTO;
    @Mock
    private OutputProjectDTO outputProjectDTO;
    @Mock
    private ProjectRepositoryInterface projectRepositoryInterface;

    @Test
    public void shouldCreateProject() {
        //Arrange
        List <ProjectReeng> list = new ArrayList<>();
        list.add(projectReeng);
        when(projectFactoryInterface.createProject(projectDTO,1)).thenReturn(projectReeng);
        when(projectStore.save(projectReeng)).thenReturn(projectReeng);
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
        when(projectStore.findById(any())).thenReturn(projectReeng);
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