package switch2021.project.factory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;
import switch2021.project.dto.TaskDTO;
import switch2021.project.interfaces.ITaskIDFactory;
import switch2021.project.model.Resource.ResourceIDReeng;
import switch2021.project.model.Sprint.SprintID;
import switch2021.project.model.Task.TaskID;
import switch2021.project.model.Task.TaskReeng;
import switch2021.project.model.Task.TaskTypeEnum;
import switch2021.project.model.valueObject.Description;
import switch2021.project.model.valueObject.EffortEstimate;
import switch2021.project.model.valueObject.Name;
import switch2021.project.model.valueObject.UserStoryID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

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