package switch2021.project.stores;

import org.junit.jupiter.api.Test;
import switch2021.project.Immutables.TaskStatus;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class TaskStatusStoreTest {

    @Test
    void createTaskStatusTestSuccess() {
        //Arrange
        TaskStatusStore test = new TaskStatusStore();
        //Act
        test.createAndSaveTaskStatus("test1");
        //Asserts
        assertEquals(1, test.getTaskStatusList().size());
    }

    @Test
    void populateDefaultTestSuccess() {
        //Arrange
        TaskStatusStore test = new TaskStatusStore();
        test.populateDefault();
        //Asserts
        assertEquals(4, test.getTaskStatusDescriptions().size());
        assertEquals("Planned", test.getTaskStatusDescriptions().get(0));
        assertEquals("Running", test.getTaskStatusDescriptions().get(1));
        assertEquals("Finished", test.getTaskStatusDescriptions().get(2));
        assertEquals("Blocked", test.getTaskStatusDescriptions().get(3));
    }

    @Test
    void getTaskStatusNamesSizeTest() {
        //Arrange
        TaskStatusStore test = new TaskStatusStore();
        test.populateDefault();
        List<String> testList = test.getTaskStatusDescriptions();
        //Asserts
        assertEquals(4, testList.size());
    }

    @Test
    void getTaskStatusNamesEmptyTest() {
        //Arrange
        TaskStatusStore test = new TaskStatusStore();
        List<String> testList = test.getTaskStatusDescriptions();
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

//    @Test
//    void saveTaskStatusSuccess() {
//        //Arrange
//        TaskStatusStore test = new TaskStatusStore();
//        TaskStatus status1 = new TaskStatus("status1");
//        TaskStatus status2 = new TaskStatus("status2");
//        test.saveTaskStatus(status1);
//        test.saveTaskStatus(status2);
//        //Assert
//        assertEquals(2, test.getTaskStatusDescriptions().size());
//        assertEquals("status1", test.getTaskStatusDescriptions().get(0));
//        assertEquals("status2", test.getTaskStatusDescriptions().get(1));
//    }

//    @Test
//    void saveTaskStatusIDSuccess() {
//        //Arrange
//        TaskStatusStore test = new TaskStatusStore();
//        TaskStatus status1 = new TaskStatus("status1");
//        TaskStatus status2 = new TaskStatus("status2");
//
//        ID sta1 = new ID(0);
//        ID sta2 = new ID(1);
//
//        test.saveTaskStatus(status1);
//        test.saveTaskStatus(status2);
//        //Assert
//        assertEquals(sta1.getId(), status1.getIdTaskStatus().getId());
//        assertEquals(sta2.getId(), status2.getIdTaskStatus().getId());
//    }
//
//    @Test
//    void TaskStatusListSuccess() {
//        //Arrange
//        TaskStatusStore test = new TaskStatusStore();
//        //Act
//        test.createAndSaveTaskStatus("status1");
//        test.createAndSaveTaskStatus("status2");
//        //Assert
//        assertEquals(testList, test.getTaskStatusList());
//    }

//    @Test
//    void TaskStatusListFail() {
//        //Arrange
//        TaskStatusStore test = new TaskStatusStore();
//        TaskStatus status1 = new TaskStatus("status1");
//        TaskStatus status2 = new TaskStatus("status2");
//        test.saveTaskStatus(status1);
//        test.saveTaskStatus(status2);
//        //Act
//        List<TaskStatus> testList = new ArrayList<>();
//        //Assert
//        assertNotEquals(testList, test.getTaskStatusList());
//    }

    @Test
    void createTaskStatusRepeatedName() {
        //Assert
        assertThrows(IllegalArgumentException.class, () -> {
            //Arrange
            TaskStatusStore test = new TaskStatusStore();
            test.createAndSaveTaskStatus("status1");
            test.createAndSaveTaskStatus("status1");
        });
    }

    @Test
    void createTaskStatusNull() {
        //Assert
        assertThrows(NullPointerException.class, () -> {
            //Arrange
            TaskStatusStore test = new TaskStatusStore();
            test.createAndSaveTaskStatus(null);
        });
    }

    @Test
    void getInitialStatus() {
        //Arrange
        TaskStatusStore test = new TaskStatusStore();
        test.populateDefault();
        //Act
        TaskStatus initial = test.getInitialStatus();
        //Assert
        assertEquals(initial, test.getTaskStatusByDescription("Planned"));
    }
}