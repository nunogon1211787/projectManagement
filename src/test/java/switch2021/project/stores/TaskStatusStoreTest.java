package switch2021.project.stores;

import org.junit.jupiter.api.Test;
import switch2021.project.factory.TaskStatusFactory;
import switch2021.project.model.Task.TaskStatusStore;
import switch2021.project.model.valueObject.Description;
import switch2021.project.model.Task.TaskStatus;

import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class TaskStatusStoreTest {

    @Test
    void constructorFactory() {
        //Arrange
        TaskStatusFactory tsf = new TaskStatusFactory();
        //Act
        TaskStatusStore tss = new TaskStatusStore(tsf);
        //Assert
        assertEquals(tsf,tss.getTaskStatusFactoryInterface());
    }

    @Test
    void createTaskStatusTestSuccess() {
        //Arrange
        TaskStatusFactory tsf = mock(TaskStatusFactory.class);
        TaskStatusStore test = new TaskStatusStore(tsf);
        TaskStatus status = mock(TaskStatus.class);
        //Act
        when(tsf.createTaskStatus("test1")).thenReturn(status);
        //Assert
        assertTrue(test.createAndAddTaskStatus("test1"));
    }

    @Test
    void createTaskStatusTestFail() {
        //Arrange
        TaskStatusFactory tsf = mock(TaskStatusFactory.class);
        TaskStatusStore test = new TaskStatusStore(tsf);
        test.getTaskStatusList().add(new TaskStatus("test1"));
        TaskStatus status = mock(TaskStatus.class);
        Description des = mock(Description.class);
        //Act
        when(status.getDescription()).thenReturn(des);
        when(des.getText()).thenReturn("test1");
        when(tsf.createTaskStatus("test1")).thenReturn(status);
        //Assert
        assertFalse(test.createAndAddTaskStatus("test1"));
    }

    @Test
    void populateDefaultTestSuccess() {
        //Arrange
        TaskStatusFactory tsf = mock(TaskStatusFactory.class);
        TaskStatusStore test = new TaskStatusStore(tsf);
        TaskStatus status = mock(TaskStatus.class);
        //Act
        when(tsf.createTaskStatus("Planned")).thenReturn(status);
        when(tsf.createTaskStatus("Running")).thenReturn(status);
        when(tsf.createTaskStatus("Finished")).thenReturn(status);
        when(tsf.createTaskStatus("Blocked")).thenReturn(status);
        test.populateDefault();
        //Assert
        assertEquals(4, test.getTaskStatusList().size());
    }

    @Test
    void populateDefaultTestFail() {
        //Assert
        assertThrows(IllegalArgumentException.class, () -> {
            // Arrange
            TaskStatusFactory tsf = mock(TaskStatusFactory.class);
            TaskStatusStore test = new TaskStatusStore(tsf);
            test.getTaskStatusList().add(new TaskStatus("Planned"));
            test.getTaskStatusList().add(new TaskStatus("Running"));
            test.getTaskStatusList().add(new TaskStatus("Finished"));
            test.getTaskStatusList().add(new TaskStatus("Blocked"));
            //Act
            test.populateDefault();
        });
    }

    @Test
    void getStatusByDescriptionTestSuccess() {
        //Arrange
        TaskStatusFactory tsf = mock(TaskStatusFactory.class);
        TaskStatusStore test = new TaskStatusStore(tsf);
        test.getTaskStatusList().add(new TaskStatus("Planned"));
        TaskStatus status = mock(TaskStatus.class);
        Description des = mock(Description.class);
        //Act
        when(status.hasDescription("Planned")).thenReturn(true);
        when(status.getDescription()).thenReturn(des);
        when(des.getText()).thenReturn("Planned");
        //Assert
        assertEquals("Planned", test.getTaskStatusByDescription("Planned").getDescription().getText());
    }

    @Test
    void getInitialStatus() {
        //Arrange
        TaskStatusFactory tsf = mock(TaskStatusFactory.class);
        TaskStatusStore test = new TaskStatusStore(tsf);
        test.getTaskStatusList().add(new TaskStatus("Planned"));
        test.getTaskStatusList().add(new TaskStatus("Running"));
        test.getTaskStatusList().add(new TaskStatus("Finished"));
        test.getTaskStatusList().add(new TaskStatus("Blocked"));
        TaskStatus initial = new TaskStatus("Planned");
        TaskStatus status = mock(TaskStatus.class);
        Description des = mock(Description.class);
        //Act
        when(status.hasDescription("Planned")).thenReturn(true);
        when(status.getDescription()).thenReturn(des);
        when(des.getText()).thenReturn("Planned");
        //Assert
        assertEquals(initial, test.getTaskStatusByDescription("Planned"));
    }

    @Test
    void getInitialStatusNull() {
        //Arrange
        TaskStatusFactory tsf = mock(TaskStatusFactory.class);
        TaskStatusStore test = new TaskStatusStore(tsf);
        test.getTaskStatusList().add(new TaskStatus("Running"));
        test.getTaskStatusList().add(new TaskStatus("Finished"));
        TaskStatus statusRun = mock(TaskStatus.class);
        TaskStatus statusFin = mock(TaskStatus.class);
        //Act
        when(statusRun.hasDescription("Planned")).thenReturn(false);
        when(statusFin.hasDescription("Planned")).thenReturn(false);
        //Assert
        assertNull(test.getInitialStatus());
    }

    @Test
    void getTaskStatusNamesSizeTest() {
        //Arrange
        TaskStatusFactory tsf = mock(TaskStatusFactory.class);
        TaskStatusStore test = new TaskStatusStore(tsf);
        TaskStatus status = mock(TaskStatus.class);
        Description des = mock(Description.class);
        //Act
        when(tsf.createTaskStatus("Planned")).thenReturn(status);
        when(tsf.createTaskStatus("Running")).thenReturn(status);
        when(tsf.createTaskStatus("Finished")).thenReturn(status);
        when(tsf.createTaskStatus("Blocked")).thenReturn(status);
        test.populateDefault();
        when(status.getDescription()).thenReturn(des);
        when(des.getText()).thenReturn("");
        List<String> testList = test.getTaskStatusDescriptions();
        //Assert
        assertEquals(4, testList.size());
    }

    @Test
    void getTaskStatusNamesEmptyTest() {
        //Arrange
        TaskStatusFactory tsf = mock(TaskStatusFactory.class);
        TaskStatusStore test = new TaskStatusStore(tsf);
        TaskStatus status = mock(TaskStatus.class);
        Description des = mock(Description.class);
        //Act
        when(status.getDescription()).thenReturn(des);
        when(des.getText()).thenReturn("");
        List<String> testList = test.getTaskStatusDescriptions();
        //Assert
        assertEquals(0, testList.size());
    }
}