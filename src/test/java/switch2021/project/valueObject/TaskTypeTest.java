package switch2021.project.valueObject;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


class TaskTypeTest {

    @Test
    @DisplayName("Test to verify if task type description is valid.")
    void hasDescriptionSuccess() {
        //Arrange
        TaskType type = mock(TaskType.class);
        Description description = mock(Description.class);
        when(type.hasDescription("test")).thenReturn(true);
        when(description.getText()).thenReturn("test");
        //Act
        boolean x = type.hasDescription("test");
        //Assert
        assertTrue(x);
    }

    @Test
    @DisplayName("Test to verify if task type description is valid.")
    void getDescriptionSuccess() {
        //Arrange
        TaskType type1 = mock(TaskType.class);
        Description description = mock(Description.class);
        when(type1.getDescription()).thenReturn(description);
        when(description.getText()).thenReturn("test");
        //Act
        String x = type1.getDescription().getText();
        //Assert
        assertEquals("test", x);
    }


    @Test
    @DisplayName("Test to verify if task type description is invalid.")
    void hasDescriptionFail() {
        //Arrange
        TaskType type = mock(TaskType.class);
        Description description = mock(Description.class);
        when(type.hasDescription("fail")).thenReturn(false);
        when(description.getText()).thenReturn("test");
        //Act
        boolean x = type.hasDescription("fail");
        //Assert
        assertFalse(x);
    }

    @Test
    @DisplayName("Test to verify if task type description is invalid.")
    void getDescriptionFail() {
        //Arrange
        TaskType type1 = mock(TaskType.class);
        Description description = mock(Description.class);
        when(type1.getDescription()).thenReturn(description);
        when(description.getText()).thenReturn("Test");
        //Act
        String x = type1.getDescription().getText();
        //Assert
        assertNotEquals("Test_1", x);
    }

    @Test
    @DisplayName("Test to verify if task type description has a null field.")
    void hasDescriptionNull() {
        //Arrange
        TaskType type = mock(TaskType.class);
        Description description = mock(Description.class);
        when(type.hasDescription(null)).thenReturn(false);
        when(description.getText()).thenReturn(null);
        //Act
        boolean x = type.hasDescription(null);
        //Assert
        assertFalse(x);
    }


    @Test
    @DisplayName("Test 1 to check Override Method, two different objects.")
    void overrideTest_True_1(){
        //Arrange
        TaskType taskType = new TaskType("name");
        TaskType taskType1 = taskType;
        //Act and Assert
        assertTrue(taskType.equals(taskType1));
    }

    @Test
    @DisplayName("Test 2 to check Override Method, with one null object.")
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
    @DisplayName("Test 4 to check Override Method, with one null object.")
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