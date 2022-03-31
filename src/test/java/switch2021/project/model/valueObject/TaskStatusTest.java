package switch2021.project.model.valueObject;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TaskStatusTest {


    @Test
    void hasDescriptionSuccess() {
        //Arrange
        TaskStatus status = new TaskStatus("tes");
        String testName = "tes";
        //Assert
        assertTrue(status.hasDescription(testName));
    }

    @Test
    void hasDescriptionFail() {
        //Arrange
        TaskStatus status = new TaskStatus("test");
        String testName = "fail";
        //Assert
        assertFalse(status.hasDescription(testName));
    }

    @Test
    void hasDescriptionNull() {
        //Arrange
        TaskStatus status = new TaskStatus("test");
        //Assert
        assertFalse(status.hasDescription(null));
    }

    @Test
    void getDescriptionTest() {
        //Arrange
        TaskStatus status1 = new TaskStatus("test1");
        Description des = mock(Description.class);
        when(des.getText()).thenReturn("test1");
        //Act
        Description desSt1 = status1.getDescription();
        //Assert
        assertNotEquals(desSt1, null);
    }

    @Test
    void overrideTestTrue() {
        //Arrange
        TaskStatus status1 = new TaskStatus("test1");
        TaskStatus status2 = new TaskStatus("test1");
        //Assert
        assertEquals(status1, status2);
    }

    @Test
    void overrideTestFalse() {
        //Arrange
        TaskStatus status5 = new TaskStatus("test1");
        TaskStatus status6 = new TaskStatus("test2");
        //Assert
        assertNotEquals(status5, status6);
    }

    @Test
    void overrideTestNull() {
        //Arrange
        TaskStatus status4 = new TaskStatus("test1");
        //Assert
        assertNotEquals(status4, null);
    }

    @Test
    void overrideTestClass() {
        //Arrange
        TaskStatus status3 = new TaskStatus("test3");
        Description des = mock(Description.class);
        //Assert
        assertNotEquals(status3, des);
    }

    @Test
    void hashCodeSuccess() {
        //Arrange
        TaskStatus status3 = new TaskStatus("test");
        TaskStatus status = new TaskStatus("test");
        //Assert
        assertEquals(status3.hashCode(), status.hashCode());
    }

    @Test
    void hashCodeSuccessFail() {
        //Arrange
        TaskStatus status3 = new TaskStatus("test");
        TaskStatus status = new TaskStatus("test1");
        //Assert
        assertNotEquals(status3.hashCode(), status.hashCode());
    }
}