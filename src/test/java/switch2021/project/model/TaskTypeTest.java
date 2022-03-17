package switch2021.project.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switch2021.project.stores.TaskTypeStore;
import static org.junit.jupiter.api.Assertions.*;

class TaskTypeTest {

    @Test
    @DisplayName("Test to verify if task type description is valid.")
    void hasDescriptionSuccess() {
        //Arrange
        TaskType type = new TaskType("tes");
        String testDescription = "tes";
        //Act
        boolean x = type.hasDescription(testDescription);
        //Assert
        assertTrue(x);
    }

    @Test
    @DisplayName("Test to verify if task type description is invalid.")
    void hasDescriptionFail() {
        //Arrange
        TaskType type = new TaskType("test");
        String testDescription = "fail";
        //Act
        boolean x = type.hasDescription(testDescription);
        //Assert
        assertFalse(x);
    }

    @Test
    @DisplayName("Test to verify if task type description has a null field.")
    void hasDescriptionNull() {
        //Arrange
        TaskType type = new TaskType("test");
        //Act
        boolean x = type.hasDescription(null);
        //Assert
        assertFalse(x);
    }


    @Test
    @DisplayName("Test to get all descriptions for all the task type stored.")
    void getDescriptionSuccess() {
        //Arrange
        TaskTypeStore test = new TaskTypeStore();
        TaskType type1= new TaskType("test1");
        TaskType type2 = new TaskType("test2");
        TaskType type3 = new TaskType("test3");
        test.saveTaskType(type1);
        test.saveTaskType(type2);
        test.saveTaskType(type3);
        //Act
        String x1 = type1.getDescription().getText();
        String x2 = type2.getDescription().getText();
        String x3 = type3.getDescription().getText();
        //Assert
        assertEquals("test1", x1);
        assertEquals("test2", x2);
        assertEquals("test3", x3);
    }

    @Test
    @DisplayName("Test to get all wrong descriptions for all the task type stored.")
    void getDescriptionFail() {
        //Arrange
        TaskTypeStore test = new TaskTypeStore();
        TaskType type1= new TaskType("test1");
        TaskType type2 = new TaskType("test2");
        TaskType type3 = new TaskType("test3");
        test.saveTaskType(type1);
        test.saveTaskType(type2);
        test.saveTaskType(type3);
        //Act
        String x1 = type1.getDescription().getText();
        String x2 = type2.getDescription().getText();
        String x3 = type3.getDescription().getText();
        //Assert
        assertNotEquals("test2", x1);
        assertNotEquals("test3", x2);
        assertNotEquals("test1", x3);
    }

    @Test
    @DisplayName("Test 1 to check Override Method, two equal objects.")
    void overrideTest_1(){
        //Arrange
        TaskType taskType = new TaskType("name");
        TaskType taskType_equal = new TaskType("name");
        //Act
        Object x = taskType;
        Object y = taskType_equal;
        //Assert
        assertEquals(x,y);
    }

    @Test
    @DisplayName("Test 2 to check Override Method, two different objects.")
    void overrideTest_2(){
        //Arrange
        TaskType taskType = new TaskType("name");
        TaskType taskType1 = new TaskType("name2");
        //Act
        Object x = taskType;
        Object y = taskType1;
        //Assert
        assertNotEquals(x,y);
    }

    @Test
    @DisplayName("Test 3 to check Override Method, with one null object.")
    void overrideTest_3(){
        //Arrange
        TaskType taskType = new TaskType("taskType");
        TaskType taskType1 = null;
        //Act and Assert
        assertNotEquals(taskType,taskType1);
    }

    @Test
    @DisplayName("Test to check HashCode Method.")
    void hashCodeTest_1(){
        //Arrange
        TaskType taskType = new TaskType("name");
        TaskType taskType1 = new TaskType("name2");
        //Act
        Object x = taskType.hashCode();
        Object y = taskType1.hashCode();
        //Assert
        assertNotEquals(x,y);
    }

    @Test
    @DisplayName("Test to check HashCode Method.")
    void hashCodeTest_2(){
        //Arrange
        TaskType taskType = new TaskType("name");
        TaskType taskType1 = new TaskType("name");
        //Act
        Object x = taskType.hashCode();
        Object y = taskType1.hashCode();
        //Assert
        assertEquals(x,y);
    }

}