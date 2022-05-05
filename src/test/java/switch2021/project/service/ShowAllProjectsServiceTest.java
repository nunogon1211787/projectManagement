package switch2021.project.service;

import org.junit.jupiter.api.Test;
import switch2021.project.dto.OutputProjectDTO;
import switch2021.project.interfaces.ProjectRepositoryInterface;
import switch2021.project.mapper.ProjectMapper;
import switch2021.project.model.Project.ProjectReeng;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ShowAllProjectsServiceTest {

    @Test
    void returnAllProjectsSuccess(){

        //Arrange
        ProjectMapper map = mock(ProjectMapper.class);
        ProjectRepositoryInterface repo = mock(ProjectRepositoryInterface.class);
        ShowAllProjectsService srv = new ShowAllProjectsService(repo, map);
        ProjectReeng proj1 = mock(ProjectReeng.class);
        ProjectReeng proj2 = mock(ProjectReeng.class);
        ProjectReeng proj3 = mock(ProjectReeng.class);
        OutputProjectDTO dto = mock(OutputProjectDTO.class);

        //Act
        List<ProjectReeng> projects = new ArrayList<>(List.of(proj1, proj2, proj3));
        when(repo.findAll()).thenReturn(projects);
        when(map.model2Dto(proj1)).thenReturn(dto);
        when(map.model2Dto(proj2)).thenReturn(dto);
        when(map.model2Dto(proj3)).thenReturn(dto);
        List<OutputProjectDTO> result = srv.showAllProjects();

        //Assert
        List<OutputProjectDTO> expected = new ArrayList<>(List.of(dto, dto, dto));
        assertEquals(expected, result);

    }

    @Test
    void returnAllProjectsFail(){

        //Arrange
        ProjectMapper map = mock(ProjectMapper.class);
        ProjectRepositoryInterface repo = mock(ProjectRepositoryInterface.class);
        ShowAllProjectsService srv = new ShowAllProjectsService(repo, map);
        ProjectReeng proj1 = mock(ProjectReeng.class);
        ProjectReeng proj2 = mock(ProjectReeng.class);
        ProjectReeng proj3 = mock(ProjectReeng.class);
        OutputProjectDTO dto = mock(OutputProjectDTO.class);
        OutputProjectDTO dto2 = mock(OutputProjectDTO.class);

        //Act
        List<ProjectReeng> projects = new ArrayList<>(List.of(proj1, proj2, proj3));
        when(repo.findAll()).thenReturn(projects);
        when(map.model2Dto(proj1)).thenReturn(dto);
        when(map.model2Dto(proj2)).thenReturn(dto);
        when(map.model2Dto(proj3)).thenReturn(dto);
        List<OutputProjectDTO> result = srv.showAllProjects();

        //Assert
        List<OutputProjectDTO> expected = new ArrayList<>(List.of(dto, dto, dto2));
        assertNotEquals(expected, result);

    }

    @Test
    void returnEmpty(){

        //Arrange
        ProjectMapper map = mock(ProjectMapper.class);
        ProjectRepositoryInterface repo = mock(ProjectRepositoryInterface.class);
        ShowAllProjectsService srv = new ShowAllProjectsService(repo, map);

        //Act
        List<ProjectReeng> projects = new ArrayList<>();
        when(repo.findAll()).thenReturn(projects);
        List<OutputProjectDTO> result = srv.showAllProjects();

        //Assert
        List<OutputProjectDTO> expected = new ArrayList<>();
        assertEquals(expected, result);

    }

}