package switch2021.project.stores;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switch2021.project.immutable.Description;
import switch2021.project.model.TaskType;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class TaskTypeStoreTest {

    @Test
    @DisplayName("Test to verify the populate method - Test size, with all descriptions.")
    void populateDefaultTestSuccess_TestSize() {
        //Arrange
        TaskTypeStore test = new TaskTypeStore();
        test.populateDefault();
        //Act
        int x = test.getTaskTypesDescription().size();
        //Assert
        assertEquals(6, x);
    }

    @Test
    @DisplayName("Test to verify the populate method - Test size - position 0.")
    void populateDefaultTestSuccess_Position0() {
        //Arrange
        TaskTypeStore test = new TaskTypeStore();
        test.populateDefault();
        //Act
        String x = test.getTaskTypesDescription().get(0);
        //Asserts
        assertEquals("Meeting", x);
    }

    @Test
    @DisplayName("Test to verify the populate method - Test size - position 1.")
    void populateDefaultTestSuccess_Position1() {
        //Arrange
        TaskTypeStore test = new TaskTypeStore();
        test.populateDefault();
        //Act
        String x = test.getTaskTypesDescription().get(1);
        //Assert
        assertEquals("Documentation", x);
    }

    @Test
    @DisplayName("Test to verify the populate method - Test size - position 2.")
    void populateDefaultTestSuccess_Position2() {
        //Arrange
        TaskTypeStore test = new TaskTypeStore();
        test.populateDefault();
        //Act
        String x = test.getTaskTypesDescription().get(2);
        //Assert
        assertEquals("Design", x);
    }

    @Test
    @DisplayName("Test to verify the populate method - Test size - position 3.")
    void populateDefaultTestSuccess_Position3() {
        //Arrange
        TaskTypeStore test = new TaskTypeStore();
        test.populateDefault();
        //Act
        String x = test.getTaskTypesDescription().get(3);
        //Assert
        assertEquals("Implementation", x);
    }

    @Test
    @DisplayName("Test to verify the populate method - Test size - position 4.")
    void populateDefaultTestSuccess_Position4() {
        //Arrange
        TaskTypeStore test = new TaskTypeStore();
        test.populateDefault();
        //Act
        String x = test.getTaskTypesDescription().get(4);
        //Assert
        assertEquals("Testing", x);
    }

    @Test
    @DisplayName("Test to verify the populate method - Test size - position 5.")
    void populateDefaultTestSuccess_Position5() {
        //Arrange
        TaskTypeStore test = new TaskTypeStore();
        test.populateDefault();
        //Act
        String x = test.getTaskTypesDescription().get(5);
        //Assert
        assertEquals("Deployment", x);
    }

    @Test
    @DisplayName("Test to verify the creation method, with success.")
    void createTaskTypeTestSuccess(){
        //Arrange
        TaskTypeStore test = new TaskTypeStore();
        test.createTaskType("test1");
        //Act
        int x = test.getTaskTypesDescription().size();
        String y = test.getTaskTypesDescription().get(0);
        //Asserts
        assertEquals(1, x);
        assertEquals("test1", y);
    }

    @Test
    @DisplayName("Test to verify the creation and save method, with success.")
    void createAndSaveTaskType_Success(){
        //Arrange
        TaskTypeStore test = new TaskTypeStore();
        boolean y = test.createTaskType("test1");
        //Act and Assert
        assertTrue(y);
    }

    @Test
    @DisplayName("Test to get all task type description - test size - empty.")
    void getTaskTypesDescriptionEmptyTest() {
        //Arrange
        TaskTypeStore test = new TaskTypeStore();
        List<String> testList = test.getTaskTypesDescription();
        //Act
        int x = testList.size();
        //Assert
        assertEquals(0, x);
    }

    @Test
    @DisplayName("Test to get task type by description, with success.")
    void getTypeByDescriptionTestSuccess() {
        //Arrange
        TaskTypeStore test = new TaskTypeStore();
        test.populateDefault();
        //Act
        Description result = test.getTypeByDescription("Meeting").getDescription();
        Description expected = new TaskType("Meeting").getDescription();
        //Assert
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Test to save the task type with success.")
    void saveTaskTypeSuccess() {
        //Arrange
        TaskTypeStore test = new TaskTypeStore();
        TaskType type1 = new TaskType("type1");
        test.saveTaskType(type1);
        //Act
        int x = test.getTaskTypesDescription().size();
        //Assert
        assertEquals(1, x);
    }

    @Test
    @DisplayName("Test with mock if the description is returned, with success.")
    void getTypeByDescriptionSuccess() {
        //Arrange
        TaskTypeStore test = new TaskTypeStore();
        TaskType type1 = mock(TaskType.class);
        when(type1.hasDescription("test1")).thenReturn(true);
        test.saveTaskType(type1);
        //Act
        TaskType x = test.getTypeByDescription("test1");
        //Assert
        assertEquals(type1, x);
    }

    @Test
    @DisplayName("Test with mock if the description is returned, with insuccess.")
    void getTypeByDescriptionInsuccess() {
        //Arrange
        TaskTypeStore test = new TaskTypeStore();
        TaskType type1 = mock(TaskType.class);
        when(type1.hasDescription("test1")).thenReturn(true);
        test.saveTaskType(type1);
        //Act
        TaskType x = test.getTypeByDescription("test2");
        //Assert
        assertNotEquals(type1, x);
    }

    @Test
    @DisplayName("Test with mock to verify if a task is saved, with success")
    void saveTaskType_Success() {
        //Arrange
        TaskTypeStore test = new TaskTypeStore();
        TaskType tasktype = mock(TaskType.class);
        Description description = mock(Description.class);
        when(tasktype.getDescription()).thenReturn(description);
        when(description.getText()).thenReturn("test1");
        //Act
        test.saveTaskType(tasktype);
        //Assert
        assertEquals("test1", tasktype.getDescription().getText());
    }


    @Test
    @DisplayName("Test to verify if is possible to save two task type with repeated description.")
    void saveTaskTypeRepeatedDescription() {
        //Arrange
        TaskTypeStore test = new TaskTypeStore();
        TaskType type1 = new TaskType("type1");
        test.saveTaskType(type1);
        TaskType type2 = new TaskType("type1");
        test.saveTaskType(type2);
        //Act
        int x = test.getTaskTypesDescription().size();
        String y = test.getTaskTypesDescription().get(0);
        //Assert;
        assertEquals(1, x);
        assertEquals("type1", y);
    }

    @Test
    @DisplayName("Test to verify if is possible to save a null task.")
    void saveTaskTypeNull() {
        //Arrange
        TaskTypeStore test = new TaskTypeStore();
        TaskType type1 = null;
        test.saveTaskType(type1);
        //Act
        int x = test.getTaskTypesDescription().size();
        //Assert
        assertEquals(0, x);
    }


    @Test
    @DisplayName("Override test - equal objects.")
    void TaskTypeStoreOverrideTest_1() {
        //Arrange
        TaskTypeStore test1 = new TaskTypeStore();
        TaskTypeStore test2 = new TaskTypeStore();
        //Assert
        assertEquals(test1, test2);
    }

    @Test
    @DisplayName("Test 2 to check Override Method, two different objects.")
    void TaskTypeStoreOverrideTest_2() {
        //Arrange
        TaskTypeStore test1 = new TaskTypeStore();
        TaskTypeStore test2 = null;
        //Assert
        assertNotEquals(test1, test2);
    }


    @Test
    @DisplayName("Hash code test - equal lists.")
    void TaskTypeStoreOverrideTest_3() {
        //Arrange
        TaskTypeStore test1 = new TaskTypeStore();
        List<TaskType> test1List = test1.getTaskTypeList();
        TaskTypeStore test2 = new TaskTypeStore();
        List<TaskType> test2List = test2.getTaskTypeList();
        //Act
        boolean x = equals(test1List);
        boolean y = equals(test2List);
        //Assert
        assertEquals(x,y);
    }

    @Test
    @DisplayName("Hash code test - equal lists.")
    void TaskTypeStoreOverride_4() {
        //Arrange
        TaskTypeStore test1 = new TaskTypeStore();
        TaskTypeStore test2 = new TaskTypeStore();
        //Act
        boolean x = equals(test1);
        boolean y = equals(test2);
        //Assert
        assertEquals(x,y);
    }

    @Test
    @DisplayName("Hash code test - equal objects.")
    void hashCodeTest_1() {
        //Arrange
        TaskTypeStore test1 = new TaskTypeStore();
        TaskTypeStore test2 = new TaskTypeStore();
        //Act
        int x = test1.hashCode();
        int y = test2.hashCode();
        //Assert
        assertEquals(x,y);
    }
    @Test
    @DisplayName("Hash code test - different objects.")
    void hashCodeTest_2() {
        //Arrange
        TaskTypeStore test1 = new TaskTypeStore();
        TaskTypeStore test2 = new TaskTypeStore();
        test1.populateDefault();
        //Act
        int x = test1.hashCode();
        int y = test2.hashCode();
        //Assert
        assertNotEquals(x,y);
    }

}