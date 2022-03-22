package switch2021.project.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switch2021.project.immutable.Description;
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
    @DisplayName("Test to verify if task type description is valid.")
    void getDescriptionSuccess() {
        //Arrange
        TaskType type1 = new TaskType("test1");
        //Act
        Description description = new Description("test1");
        Description x = type1.getDescription();
        //Assert
        assertEquals(description, x);
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
    @DisplayName("Test to verify if task type description is valid.")
    void getDescriptionFail() {
        //Arrange
        TaskType type1 = new TaskType("test1");
        //Act
        Description description = new Description("test2");
        Description x = type1.getDescription();
        //Assert
        assertNotEquals(description, x);
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
    @DisplayName("Test 2 to check Override Method, two different objects.")
    void overrideTest_True_1(){
        //Arrange
        TaskType taskType = new TaskType("name");
        TaskType taskType1 = taskType;
        //Act and Assert
        assertTrue(taskType.equals(taskType1));
    }

    @Test
    @DisplayName("Test 3 to check Override Method, with one null object.")
    void overrideTest_True_2(){
        //Arrange
        TaskType taskType = new TaskType("TaskType");
        TaskType taskType1 = new TaskType("TaskType");
        //Act and Assert
        assertTrue(taskType.equals(taskType1));
    }

    @Test
    @DisplayName("Test 3 to check Override Method, with one null object.")
    void overrideTest_NotEquals(){
        //Arrange
        TaskType taskType = new TaskType("taskType");
        TaskType taskType1 = null;
        //Act and Assert
        assertNotEquals(taskType,taskType1);
    }

    @Test
    @DisplayName("Test 3 to check Override Method, with one null object.")
    void overrideTest_False(){
        //Arrange
        TaskType taskType = new TaskType("TaskType");
        TaskType taskType1 = new TaskType("TypeTask");
        //Act and Assert
        assertFalse(taskType.equals(taskType1));
    }


    @Test
    @DisplayName("Test to check HashCode Method.")
    void hashCodeTest_Fail(){
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
    void hashCodeTest_Success(){
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