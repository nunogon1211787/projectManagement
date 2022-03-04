package switch2021.project.stores;

import org.junit.jupiter.api.Test;
import switch2021.project.model.Task;
import switch2021.project.model.TaskStatus;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TaskStatusStoreTest {

    @Test
    void createTaskStatusTestSuccess() {
        //Arrange
        TaskStatusStore test = new TaskStatusStore();
        //Asserts
        assertTrue(test.createTaskStatus("test1"));
        assertEquals(1, test.getTaskStatusNames().size());
        assertEquals("test1", test.getTaskStatusByDescription("test1").getDescription());
    }

    @Test
    void populateDefaultTestSuccess() {
        //Arrange
        TaskStatusStore test = new TaskStatusStore();
        test.populateDefault();
        //Asserts
        assertEquals(4, test.getTaskStatusNames().size());
        assertEquals("Planned", test.getTaskStatusNames().get(0));
        assertEquals("Running", test.getTaskStatusNames().get(1));
        assertEquals("Finished", test.getTaskStatusNames().get(2));
        assertEquals("Blocked", test.getTaskStatusNames().get(3));
    }

    @Test
    void getTaskStatusNamesSizeTest() {
        //Arrange
        TaskStatusStore test = new TaskStatusStore();
        test.populateDefault();
        List<String> testList = test.getTaskStatusNames();
        //Asserts
        assertEquals(4, testList.size());
    }

    @Test
    void getTaskStatusNamesEmptyTest() {
        //Arrange
        TaskStatusStore test = new TaskStatusStore();
        List<String> testList = test.getTaskStatusNames();
        //Asserts
        assertEquals(0, testList.size());
    }

    @Test
    void getStatusByDescriptionTestSuccess() {
        //Arrange
        TaskStatusStore test = new TaskStatusStore();
        test.populateDefault();
        TaskStatus result = test.getTaskStatusByDescription("Planned");
        TaskStatus expected = new TaskStatus("Planned");
        //Assert
        assertEquals(expected.getDescription(), result.getDescription());
    }

    @Test
    void saveTaskStatusSuccess() {
        //Arrange
        TaskStatusStore test = new TaskStatusStore();
        TaskStatus status1 = new TaskStatus("status1");
        TaskStatus status2 = new TaskStatus("status2");
        test.saveTaskStatus(status1);
        test.saveTaskStatus(status2);
        //Assert
        assertEquals(2, test.getTaskStatusNames().size());
        assertEquals("status1", test.getTaskStatusNames().get(0));
        assertEquals("status2", test.getTaskStatusNames().get(1));
    }

    @Test
    void saveTaskStatusIDSuccess() {
        //Arrange
        TaskStatusStore test = new TaskStatusStore();
        TaskStatus status1 = new TaskStatus("status1");
        TaskStatus status2 = new TaskStatus("status2");
        test.saveTaskStatus(status1);
        test.saveTaskStatus(status2);
        //Assert
        assertEquals(1, status1.getIdTaskStatus());
        assertEquals(2, status2.getIdTaskStatus());
    }

    @Test
    void TaskStatusListSuccess() {
        //Arrange
        TaskStatusStore test = new TaskStatusStore();
        TaskStatus status1 = new TaskStatus("status1");
        TaskStatus status2 = new TaskStatus("status2");
        test.saveTaskStatus(status1);
        test.saveTaskStatus(status2);
        //Act
        List<TaskStatus> testList = new ArrayList<>();
        testList.add(status1);
        testList.add(status2);
        //Assert
        assertEquals(testList, test.getTaskStatusList());
    }

    @Test
    void TaskStatusListFail() {
        //Arrange
        TaskStatusStore test = new TaskStatusStore();
        TaskStatus status1 = new TaskStatus("status1");
        TaskStatus status2 = new TaskStatus("status2");
        test.saveTaskStatus(status1);
        test.saveTaskStatus(status2);
        //Act
        List<TaskStatus> testList = new ArrayList<>();
        //Assert
        assertNotEquals(testList, test.getTaskStatusList());
    }

    @Test
    void saveTaskStatusRepeatedName() {
        //Arrange
        TaskStatusStore test = new TaskStatusStore();
        TaskStatus status1 = new TaskStatus("status1");
        TaskStatus status2 = new TaskStatus("status1");
        test.saveTaskStatus(status1);
        test.saveTaskStatus(status2);
        //Assert
        assertEquals(1, test.getTaskStatusNames().size());
        assertEquals("status1", test.getTaskStatusNames().get(0));
    }

    @Test
    void saveTaskStatusNull() {
        //Arrange
        TaskStatusStore test = new TaskStatusStore();
        TaskStatus status1 = null;
        test.saveTaskStatus(status1);
        //Assert
        assertEquals(0, test.getTaskStatusNames().size());
    }

    @Test
    void getInitialStatus() {
        //Arrange
        TaskStatusStore test = new TaskStatusStore();
        test.populateDefault();
        TaskStatus initial = test.getInitialStatus();
        //Assert
        assertEquals(4, test.getTaskStatusNames().size());
        assertEquals(initial, test.getTaskStatusByDescription("Planned"));
    }


}