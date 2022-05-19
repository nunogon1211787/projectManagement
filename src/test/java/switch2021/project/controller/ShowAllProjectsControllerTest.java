package switch2021.project.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import switch2021.project.dto.OutputProjectDTO;
import switch2021.project.service.CreateProjectService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
class ShowAllProjectsControllerTest {

    @MockBean
    CreateProjectService srv;
    @InjectMocks ProjectController ctrl;

    @BeforeEach
    void TestConfiguration(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void returnProjectsSuccess(){

        //Arrange
        OutputProjectDTO proj1 = mock(OutputProjectDTO.class);
        OutputProjectDTO proj2 = mock(OutputProjectDTO.class);
        OutputProjectDTO proj3 = mock(OutputProjectDTO.class);

        //Act
        List<OutputProjectDTO> projects = new ArrayList<>(List.of(proj1, proj2, proj3));
        when(srv.showAllProjects()).thenReturn(projects);
        ResponseEntity<Object> result = ctrl.showAllProjects();

        //Assert
        ResponseEntity<Object> expected = new ResponseEntity<>(projects, HttpStatus.OK);
        assertEquals(expected, result);

    }

    @Test
    void returnProjectsFail(){

        //Arrange
        OutputProjectDTO proj1 = mock(OutputProjectDTO.class);
        OutputProjectDTO proj2 = mock(OutputProjectDTO.class);
        OutputProjectDTO proj3 = mock(OutputProjectDTO.class);

        //Act
        List<OutputProjectDTO> projects = new ArrayList<>(List.of(proj1, proj2, proj3));
        when(srv.showAllProjects()).thenReturn(projects);
        ResponseEntity<Object> result = ctrl.showAllProjects();
        List<OutputProjectDTO> projects2 = new ArrayList<>(List.of(proj1, proj2, proj1));

        //Assert
        ResponseEntity<Object> expected = new ResponseEntity<>(projects2, HttpStatus.OK);
        assertNotEquals(expected, result);

    }

}