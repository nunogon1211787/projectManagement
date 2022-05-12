package switch2021.project.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import switch2021.project.controller.CreateSprintController;
import switch2021.project.dto.NewSprintDTO;
import switch2021.project.service.CreateSprintService;
import static org.junit.jupiter.api.Assertions.assertEquals;


@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
public class CreateSprintControllerTest {

    @InjectMocks
    //@Autowired
    CreateSprintController createSprintController;
    @Mock
    CreateSprintService createSprintService;
    //@Mock
    //OutPutSprintDTO outPutSprintDTO;


//    @Test
//    @DisplayName("Test to create a sprint, with success")
//    public void createSprint_Success() {
//
//        // Arrange
//        MockHttpServletRequest request = new MockHttpServletRequest();
//        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
//        NewSprintDTO newSprintDTO = mock(NewSprintDTO.class);
//
//        //Act
//        when(createSprintService.createAndSaveSprint(newSprintDTO)).thenReturn(outPutSprintDTO);
//        ResponseEntity<Object> responseEntity = createSprintController.createAndSaveSprint(newSprintDTO);
//
//        //Assert
//        assertEquals(responseEntity.getStatusCodeValue(), 201);
//    }

    @Test
    @DisplayName("Create Sprint IT")
    public void createSprint(){
        //Arrange
        String project = "Project_2022_1";
        String name = "Sprint Name";
        NewSprintDTO dto = new NewSprintDTO(project, name);
        //Act
        ResponseEntity<Object> responseEntity = createSprintController.createAndSaveSprint(dto);
        //Assert
        assertEquals(201, responseEntity.getStatusCodeValue());
    }

}
