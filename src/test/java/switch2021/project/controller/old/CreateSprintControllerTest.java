package switch2021.project.controller.old;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import switch2021.project.controller.CreateSprintController;
import switch2021.project.dto.SprintDTO;
import switch2021.project.factoryInterface.ISprintFactory;
import switch2021.project.model.Sprint.Sprint;
import switch2021.project.service.CreateSprintService;
import java.util.Optional;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


@SpringBootTest
public class CreateSprintControllerTest {
//
//    @MockBean
//    CreateSprintService createSprintService;
//
//    @MockBean
//    ISprintFactory iSprintFactory;
//
//    @MockBean
//    Sprint sprint;
//
//    @InjectMocks
//    CreateSprintController createSprintController;
//
//    @BeforeEach
//    public void setUp throws Exception {
//        MockitoAnnotations.initMocks(createSprintController);
//    }
//
//
//    @Test
//    @DisplayName("Test to create a sprint")
//    public void createASprint() {
//
//        // Arrange
//        MockHttpServletRequest request = new MockHttpServletRequest();
//        RequestContextHolder.setRequestAttributes((new ServletRequestAttributes(request));
//
//        Sprint newSprint = mock(Sprint.class);
//        Optional<Sprint> optionalSprint = Optional.of(newSprint);
//        SprintDTO sprintDTO = new SprintDTO();
//        when(createSprintService.createAndSaveSprint(sprintDTO)).thenReturn();
//        ResponseEntity<Object> responseEntity = createSprintController.createAndSaveSprint(sprintDTO);
//
//        // Assert
//        assertEquals(responseEntity.getStatusCodeValue(), 200);
//    }


}
