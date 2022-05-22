package switch2021.project.controller;

import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;
import switch2021.project.mapper.TaskMapperNew;
import switch2021.project.repositories.TaskRepository;
import switch2021.project.service.TaskService;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.mock;

class TaskControllerTest {

    @InjectMocks
    TaskController controller;

    @MockBean
    TaskService service;

    @MockBean
    TaskRepository repository;

    @MockBean
    TaskMapperNew mapperNew;



    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
    }
//
//    @Test
//    void createAndSaveTaskControllerTest() {
//        //Arrange
////        MockHttpServletRequest request = new MockHttpServletRequest();
////        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
//
//
//        TaskDTO inputDTO = mock(TaskDTO.class);
//        OutputTaskDTO outputDTO = mock(OutputTaskDTO.class);
//
//        //Act
////        when(inputDTO.getName()).thenReturn("Óscar");
////        when(inputDTO.getDescription()).thenReturn("Fazer tal e coiso");
////        when(inputDTO.getEffortEstimate()).thenReturn(12.0);
////        when(inputDTO.getType()).thenReturn("Design");
////        when(inputDTO.getResponsible()).thenReturn("123043");
////        when(inputDTO.getTaskContainerID()).thenReturn("123123");
////        when(outputDTO.getName()).thenReturn("Óscar");
////        when(outputDTO.getDescription()).thenReturn("Fazer tal e coiso");
////        when(outputDTO.getEffortEstimate()).thenReturn(String.valueOf(12.0));
////        when(outputDTO.getType()).thenReturn("Design");
////        when(outputDTO.getResponsible()).thenReturn("123043");
//
////        when(service.createAndSaveTask(inputDTO)).thenReturn(outputDTO);
//
//        ResponseEntity<?> response = controller.createAndSaveTask(inputDTO);
//
//        //Assert
//
//        assertEquals(response.getStatusCodeValue(), 201);
//    }
//
//    @Test
//    void createAndSaveTaskControllerTest2() {
//        //Arrange
//        TaskDTO inputDTO = mock(TaskDTO.class);
//
//        //Act
//        ResponseEntity<?> response = controller.createAndSaveTask(inputDTO);
//
//        //Assert
////        assertEquals(response.getStatusCodeValue(), 201);
//        assertThat(response.getStatusCodeValue()).isEqualTo(201);
//
//    }
//
//    @Test
//    void createAndSaveTaskControllerTest3() {
//        //Arrange
//
//        String name1 = "Paulo";
//        String description1 = "Fazer coiso e tal";
//        double effort1 = 12.0;
//        String type1 = "Design";
//        String res1 = "1234";
//        String taskC1 = "4321";
//
//        TaskDTO inputDTO = new TaskDTO();
//        inputDTO.name = name1;
//        inputDTO.description = description1;
//        inputDTO.effortEstimate = effort1;
//        inputDTO.type = type1;
//        inputDTO.responsible = res1;
//        inputDTO.taskContainerID = taskC1;
//
//        //Act
//        ResponseEntity<?> response = controller.createAndSaveTask(inputDTO);
//
//        //Assert
////        assertEquals(response.getStatusCodeValue(), 201);
//        assertThat(response.getStatusCodeValue()).isEqualTo(201);
//
//    }
}