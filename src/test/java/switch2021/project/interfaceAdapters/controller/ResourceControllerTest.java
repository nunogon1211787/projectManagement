package switch2021.project.interfaceAdapters.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import switch2021.project.dtoModel.dto.*;
import switch2021.project.applicationServices.service.ResourceService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
class ResourceControllerTest {

    @MockBean private ResourceService Service;
    @InjectMocks private ResourceController ctrl;

    @BeforeEach
    void TestConfiguration(){ MockitoAnnotations.openMocks(this); }

    @Test
    void createResourceSuccess(){
        //Arrange
        CreateResourceDTO dto = new CreateResourceDTO("email@email.com","Project_2022_1","TeamMember",
                "2022-01-12","2023-01-12",500,0.6);
        OutputResourceDTO output = mock(OutputResourceDTO.class);
        //Act
        when(Service.createAndSaveResource(dto)).thenReturn(output);
        ResponseEntity<Object> result = ctrl.createResource(dto);
        ResponseEntity<Object> expected = new ResponseEntity<>(output, HttpStatus.CREATED);
        //Assert
        assertEquals(result, expected);
    }

    @Test
    void createResourceFail(){
        //Arrange
        CreateResourceDTO dto = new CreateResourceDTO("email@email.com","Project_2022_1","TeamMember",
                "2022-01-12","2023-01-12",500,0.6);
        Exception exception = mock(IllegalArgumentException.class);
        //Act
        when(exception.getLocalizedMessage()).thenReturn("test");
        when(Service.createAndSaveResource(dto)).thenThrow(exception);
        ResponseEntity<Object> result = ctrl.createResource(dto);
        HttpStatus expected = HttpStatus.BAD_REQUEST;
        //Assert
        assertEquals(result.getStatusCode(), expected);
    }

    @Test
    void showResourceRequestSuccess() throws Exception {
        //Arrange
        String id = "mock(ResourceID.class)";
        OutputResourceDTO output = mock(OutputResourceDTO.class);
        //Act
        when(Service.showResourceRequested(id)).thenReturn(output);
        ResponseEntity<Object> result = ctrl.showResourceRequested(id);
        ResponseEntity<Object> expected = new ResponseEntity<>(output, HttpStatus.OK);
        //Assert
        assertEquals(result, expected);
    }

    @Test
    void showResourceRequestFail() throws Exception {
        //Arrange
        String id = "mock(ResourceID.class)";
        Exception exception = mock(NullPointerException.class);
        //Act
        when(exception.getLocalizedMessage()).thenReturn("test");
        when(Service.showResourceRequested(id)).thenThrow(exception);
        ResponseEntity<Object> result = ctrl.showResourceRequested(id);
        HttpStatus expected = HttpStatus.BAD_REQUEST;
        //Assert
        assertEquals(result.getStatusCode(), expected);
    }

    @Test
    void showAllResourcesSuccess(){
        //Arrange
        OutputResourceDTO output1 = mock(OutputResourceDTO.class);
        OutputResourceDTO output2 = mock(OutputResourceDTO.class);
        OutputResourceDTO output3 = mock(OutputResourceDTO.class);
        List<OutputResourceDTO> resources= new ArrayList<>();
        resources.add(output1);
        resources.add(output2);
        resources.add(output3);
        //Act
        when(Service.showAllResources()).thenReturn(resources);
        ResponseEntity<Object> result = ctrl.showAllResources();
        ResponseEntity<Object> expected = new ResponseEntity<>(CollectionModel.of(resources), HttpStatus.OK);
        //Assert
        assertEquals(result, expected);
    }

    @Test
    void showAllResourcesFail(){
        //Arrange
        Exception exception = mock(NullPointerException.class);
        //Act
        when(exception.getLocalizedMessage()).thenReturn("test");
        when(Service.showAllResources()).thenThrow(exception);
        ResponseEntity<Object> result = ctrl.showAllResources();
        HttpStatus expected = HttpStatus.BAD_REQUEST;
        //Assert
        assertEquals(result.getStatusCode(), expected);
    }

    @Test
    void showRegisterOfResourcesSuccess(){
        //Arrange
        String id = "Project_2022_1";
        OutputResourceDTO output1 = mock(OutputResourceDTO.class);
        OutputResourceDTO output2 = mock(OutputResourceDTO.class);
        OutputResourceDTO output3 = mock(OutputResourceDTO.class);
        List<OutputResourceDTO> resources= new ArrayList<>();
        resources.add(output1);
        resources.add(output2);
        resources.add(output3);
        //Act
        when(Service.showProjectTeam(id)).thenReturn(resources);
        ResponseEntity<Object> result = ctrl.showRegisterOfResourcesInAProject(id);
        ResponseEntity<Object> expected = new ResponseEntity<>(resources, HttpStatus.OK);
        //Assert
        assertEquals(result, expected);
    }

    @Test
    void showRegisterOfResourcesFail(){
        //Arrange
        String id = "Project_2022_1";
        Exception exception = mock(NullPointerException.class);
        //Act
        when(exception.getLocalizedMessage()).thenReturn("test");
        when(Service.showProjectTeam(id)).thenThrow(exception);
        ResponseEntity<Object> result = ctrl.showRegisterOfResourcesInAProject(id);
        HttpStatus expected = HttpStatus.BAD_REQUEST;
        //Assert
        assertEquals(result.getStatusCode(), expected);
    }



    @Test
    void showCurrentProjectTeamSuccess(){
        //Arrange
        String id = "Project_2022_1";
        OutputResourceDTO output1 = mock(OutputResourceDTO.class);
        OutputResourceDTO output2 = mock(OutputResourceDTO.class);
        OutputResourceDTO output3 = mock(OutputResourceDTO.class);
        List<OutputResourceDTO> resources= new ArrayList<>();
        resources.add(output1);
        resources.add(output2);
        resources.add(output3);
        //Act
        when(Service.showCurrentProjectTeam(id)).thenReturn(CollectionModel.of(resources));
        ResponseEntity<Object> result = ctrl.showCurrentProjectTeam(id);
        ResponseEntity<Object> expected = new ResponseEntity<>(CollectionModel.of(resources), HttpStatus.OK);
        //Assert
        assertEquals(result, expected);
    }

    @Test
    void showCurrentProjectTeamFail(){
        //Arrange
        String id = "Project_2022_1";
        Exception exception = mock(NullPointerException.class);
        //Act
        when(exception.getLocalizedMessage()).thenReturn("test");
        when(Service.showCurrentProjectTeam(id)).thenThrow(exception);
        ResponseEntity<Object> result = ctrl.showCurrentProjectTeam(id);
        HttpStatus expected = HttpStatus.BAD_REQUEST;
        //Assert
        assertEquals(result.getStatusCode(), expected);
    }

    @Test
    void defineProjectRoleOfAResourceSuccess() {
        //Arrange
        String id = "mock(ResourceID.class)";
        DefineRoleOfResourceDTO dto = mock(DefineRoleOfResourceDTO.class);
        OutputResourceDTO output = mock(OutputResourceDTO.class);
        //Act
        when(Service.defineProjectRole(id, dto)).thenReturn(output);
        ResponseEntity<Object> result = ctrl.defineProjectRoleOfAResource(id, dto);
        ResponseEntity<Object> expected = new ResponseEntity<>(output, HttpStatus.OK);
        //Assert
        assertEquals(result, expected);
    }

    @Test
    void defineProjectRoleOfAResourceFail() {
        //Arrange
        String id = "mock(ResourceID.class)";
        DefineRoleOfResourceDTO dto = mock(DefineRoleOfResourceDTO.class);
        Exception exception = mock(NullPointerException.class);
        //Act
        when(exception.getLocalizedMessage()).thenReturn("test");
        when(Service.defineProjectRole(id, dto)).thenThrow(exception);
        ResponseEntity<Object> result = ctrl.defineProjectRoleOfAResource(id, dto);
        HttpStatus expected = HttpStatus.BAD_REQUEST;
        //Assert
        assertEquals(result.getStatusCode(), expected);
    }

    @Test
    void deleteAResourceSuccess() {
        //Arrange
        String id = "mock(ResourceID.class)";
        //Act
        ResponseEntity<Object> result = ctrl.deleteAResource(id);
        HttpStatus expected = HttpStatus.OK;
        //Assert
        assertEquals(result.getStatusCode(), expected);
    }
}