package switch2021.project.controller;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;


@ExtendWith(SpringExtension.class)
public class SprintControllerTest {

//    @InjectMocks
//    CreateSprintController createSprintController;
//
//    @Mock
//    SprintService createSprintService;
//    @Mock
//    NewSprintDTO newSprintDTO;
//    @Mock
//    OutPutSprintDTO outPutSprintDTO;
//    @Mock
//    MockHttpServletRequest mockHttpServletRequest;
//
//
//    @Test
//    @DisplayName("Test to create a sprint, with success")
//    public void createSprint_Success() {
//
//        // Arrange
//        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(mockHttpServletRequest));
//        when(createSprintService.createAndSaveSprint(newSprintDTO)).thenReturn(outPutSprintDTO);
//
//        //Act
//        ResponseEntity<Object> responseEntity = createSprintController.createAndSaveSprint(newSprintDTO);
//
//        //Assert
//        assertEquals(responseEntity.getStatusCodeValue(), 201);
//    }

//    @Test
//    @DisplayName("Create Sprint IT")
//    public void createSprint(){
//        //Arrange
//        String project = "Project_2022_1";
//        String name = "Sprint Name";
//        NewSprintDTO dto = new NewSprintDTO(project, name);
//        //Act
//        ResponseEntity<Object> responseEntity = createSprintController.createAndSaveSprint(dto);
//        //Assert
//        assertEquals(201, responseEntity.getStatusCodeValue());
//    }

}
