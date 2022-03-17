package switch2021.project.model;

import org.junit.jupiter.api.Test;
import switch2021.project.Immutables.Description;
import static org.junit.jupiter.api.Assertions.*;

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
    void getDescriptionSuccess() {
        //Arrange
        TaskStatus status1= new TaskStatus("test1");
        //Act
        Description des = new Description("test1");
        //Assert
        assertEquals(des, status1.getDescription());
    }

    @Test
    void getDescriptionFail() {
        //Arrange
        TaskStatus status1= new TaskStatus("test1");
        //Act
        Description des = new Description("test");
        //Assert
        assertNotEquals(des, status1.getDescription());
    }

    @Test
    void overrideTestTrue() {
        //Arrange
        TaskStatus status1= new TaskStatus("test1");
        TaskStatus status2 = new TaskStatus("test1");
        //Assert
        assertTrue(status1.equals(status2));
    }

    @Test
    void overrideTestTrue1() {
        //Arrange
        TaskStatus status1= new TaskStatus("test1");
        TaskStatus status2 = status1;
        //Assert
        assertTrue(status1.equals(status2));
    }

    @Test
    void overrideTestFalse() {
        //Arrenge
        TaskStatus status5= new TaskStatus("test1");
        TaskStatus status6= new TaskStatus("test2");
        //Assert
        assertFalse(status5.equals(status6));
    }

    @Test
    void overrideTestNull() {
        //Arrenge
        TaskStatus status4 = null;
        Typology typo = new Typology("test");
        //Assert
        assertFalse(typo.equals(status4));
    }

    @Test
    void overrideTestClass() {
        //Arrange
        TaskStatus status3 = new TaskStatus("test3");
        Description des = new Description("test3");
        //Assert
        assertFalse(status3.equals(des));
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