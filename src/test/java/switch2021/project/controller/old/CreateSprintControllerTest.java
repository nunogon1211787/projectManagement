package switch2021.project.controller.old;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import switch2021.project.controller.CreateSprintController;
import switch2021.project.dto.NewSprintDTO;
import switch2021.project.dto.OutPutSprintDTO;
import switch2021.project.service.CreateSprintService;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class CreateSprintControllerTest {

    @InjectMocks
    CreateSprintController createSprintController;

    @Mock
    CreateSprintService createSprintService;

    @Mock
    OutPutSprintDTO outPutSprintDTO;


    @Test
    @DisplayName("Test to create a sprint, with success")
    public void createASprint_Success() {

        // Arrange
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        NewSprintDTO newSprintDTO = mock(NewSprintDTO.class);

        //Act
        when(createSprintService.createAndSaveSprint(newSprintDTO)).thenReturn(outPutSprintDTO);
        ResponseEntity<Object> responseEntity = createSprintController.createAndSaveSprint(newSprintDTO);

        //Assert
        assertEquals(responseEntity.getStatusCodeValue(), 201);
    }


    @Test
    @DisplayName("Test to create a sprint, with failure")
    public void createASprint_Failure() {

        // Arrange
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        NewSprintDTO newSprintDTO = mock(NewSprintDTO.class);

        //Act
        when(createSprintService.createAndSaveSprint(newSprintDTO)).thenReturn(outPutSprintDTO);
        ResponseEntity<Object> responseEntity = createSprintController.createAndSaveSprint(newSprintDTO);

        //Assert
        assertNotEquals(responseEntity.getStatusCodeValue(), 500);
    }

}
