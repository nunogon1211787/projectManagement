package switch2021.project.entities.aggregates.Task;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import switch2021.project.entities.valueObjects.vos.ResourceID;
import switch2021.project.entities.valueObjects.vos.*;
import switch2021.project.entities.valueObjects.vos.enums.TaskTypeEnum;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.when;

@SpringBootTest
class TaskTest {

    @MockBean
    Name name;
    @MockBean
    TaskID taskID;
    @MockBean
    Description description;
    @MockBean
    EffortEstimate effort;
    @MockBean
    ResourceID resID;
    @MockBean
    UserStoryID usID;
    @MockBean
    SprintID sprintID;

    @Test
    void CreateTaskWithSuccess() {
        //Arrange
        when(description.getText()).thenReturn("Fazer coiso e tal");
        //Act
        Task task = new Task(taskID, description, effort,
                             TaskTypeEnum.TESTING, resID);
        //Assert
        assertEquals(task.getDescription().getText(), "Fazer coiso e tal");
        assertNotNull(task);
    }

    @Test
    void TestingHasMethods() {
        //Arrange
        when(taskID.getTaskName()).thenReturn(name);
        when(name.getText()).thenReturn("Alberto");
        //Act
        Task task = new Task(taskID, description, effort,
                             TaskTypeEnum.TESTING, resID);
        //Assert
        assertTrue(task.hasName("Alberto"));
        assertTrue(task.hasTaskTypeEnum("TESTING"));
        assertTrue(task.hasResponsible(resID));
        assertTrue(task.hasDescription(description));
    }

}