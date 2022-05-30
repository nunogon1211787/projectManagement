package switch2021.project.interfaceAdapters.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import switch2021.project.dtoModel.dto.DateDTO;
import switch2021.project.dtoModel.dto.IdDTO;
import switch2021.project.dtoModel.dto.OutputResourceDTO;
import switch2021.project.applicationServices.service.ResourceService;
import switch2021.project.interfaceAdapters.controller.ResourceController;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
class ResourceControllerTest {

    @MockBean private ResourceService currentProjectTeamService;
    @InjectMocks private ResourceController ctrl;

    @BeforeEach
    void TestConfiguration(){ MockitoAnnotations.openMocks(this); }

    @Test
    void showCurrentProjectTeamSuccess(){
        //Arrange
        IdDTO id = mock(IdDTO.class);
        DateDTO date = mock(DateDTO.class);
        OutputResourceDTO output1 = mock(OutputResourceDTO.class);
        OutputResourceDTO output2 = mock(OutputResourceDTO.class);
        OutputResourceDTO output3 = mock(OutputResourceDTO.class);
        List<OutputResourceDTO> resources= new ArrayList<>(List.of(output1, output2, output3));

        //Act
        when(currentProjectTeamService.showCurrentProjectTeam(id, date)).thenReturn(resources);
        ResponseEntity<Object> result = ctrl.showCurrentProjectTeam(id, date);
        ResponseEntity<Object> expected = new ResponseEntity<>(resources, HttpStatus.OK);

        //Assert
        assertEquals(result, expected);

    }

    @Test
    void showCurrentProjectTeamFail(){
        //Arrange
        IdDTO id = mock(IdDTO.class);
        DateDTO date = mock(DateDTO.class);
        OutputResourceDTO output1 = mock(OutputResourceDTO.class);
        OutputResourceDTO output2 = mock(OutputResourceDTO.class);
        OutputResourceDTO output3 = mock(OutputResourceDTO.class);
        List<OutputResourceDTO> resources = new ArrayList<>(List.of(output1, output2, output3));
        List<OutputResourceDTO> resources2 = new ArrayList<>(List.of(output1, output2, output1));

        //Act
        when(currentProjectTeamService.showCurrentProjectTeam(id, date)).thenReturn(resources);
        ResponseEntity<Object> result = ctrl.showCurrentProjectTeam(id, date);
        ResponseEntity<Object> expected = new ResponseEntity<>(resources2, HttpStatus.OK);

        //Assert
        assertNotEquals(result, expected);

    }
}