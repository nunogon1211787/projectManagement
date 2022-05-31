package switch2021.project.entities.aggregates.Task;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import switch2021.project.entities.valueObjects.vos.ResourceIDReeng;
import switch2021.project.entities.valueObjects.vos.*;
import switch2021.project.entities.valueObjects.vos.enums.TaskTypeEnum;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.when;

@SpringBootTest
class TaskReengTest {

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


    @Test
    void CreateTaskWithSuccess() {
        //Arrange
        when(description.getText()).thenReturn("Fazer coiso e tal");

        //Act
        TaskReeng task = new TaskReeng(taskID, description, effort,
                TaskTypeEnum.Testing, resID);

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
        TaskReeng task = new TaskReeng(taskID, description, effort,
                TaskTypeEnum.Testing, resID);

        //Assert
        assertTrue(task.hasName("Alberto"));
        assertTrue(task.hasTaskTypeEnum("Testing"));
        assertTrue(task.hasResponsible(resID));
        assertTrue(task.hasDescription(description));
    }

    @Test
    void GetStatusTest() {
        //Arrange


        //Act


        //Assert


    }



}