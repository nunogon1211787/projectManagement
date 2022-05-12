package switch2021.project.factory;

import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;
import switch2021.project.interfaces.ITaskIDFactory;
import switch2021.project.model.Resource.ResourceIDReeng;
import switch2021.project.model.Task.TaskID;
import switch2021.project.model.valueObject.*;


class TaskFactoryTest {



    @MockBean
    ITaskIDFactory taskIDFactory;

    @MockBean
    Name name;

    @MockBean
    TaskID taskID;

    @MockBean
    Description description;

    @MockBean
    EffortEstimate effort;

    @MockBean
    ResourceIDReeng resID;

    @MockBean
    UserStoryID usID;

    @MockBean
    SprintID sprintID;

    @InjectMocks
    TaskFactory factory;


    @BeforeEach
    void TestConfiguration(){
        MockitoAnnotations.openMocks(this);
    }

//    @Test
//    void createTaskSuccess() {
//        //Arrange
//        TaskDTO dto = mock(TaskDTO.class);
//
//        when(dto.getDescription()).thenReturn("Fazer coiso e tal");
//
//
//        when(taskIDFactory.createTaskID(usID, dto.name)).thenReturn(taskID);
//
//
//        //Act
//        TaskReeng task = factory.createTask(dto, resID, usID);
//
//        //Assert
//        assertEquals(task.getDescription().getText(), "Fazer coiso e tal");
//
//    }
}