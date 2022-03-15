package switch2021.project.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switch2021.project.Immutables.Description;
import switch2021.project.stores.TaskStatusStore;
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
    void getId_TaskStatusSuccess() {
        //Arrange
        TaskStatusStore test = new TaskStatusStore();
        TaskStatus status = new TaskStatus("test");
        test.saveTaskStatus(status);
        //Assert
        assertEquals(1, status.getIdTaskStatus());
    }

    @Test
    void getId_TaskStatusWithoutSave() {
        //Arrange
        TaskStatus status = new TaskStatus("test");
        //Assert
        assertEquals(0, status.getIdTaskStatus());
    }

    @Test
    void getId_TaskStatusFail() {
        //Arrange
        TaskStatus status = new TaskStatus("test");
        //Assert
        assertNotEquals(1, status.getIdTaskStatus());
    }

    @Test
    void setId_TaskStatus() {
        //Arrange
        TaskStatus status = new TaskStatus("test");
        status.setIDTaskStatus(1);
        //Assert
        assertEquals(1, status.getIdTaskStatus());
    }

    @Test
    void setDescription_TaskStatus() {
        //Arrange
        TaskStatus status = new TaskStatus("test");
        status.setDescription("Success");
        //Assert
        assertEquals("Success", status.getDescription());
    }

    @Test
    void getId_TaskStatusWith3status() {
        //Arrange
        TaskStatusStore test = new TaskStatusStore();
        TaskStatus status1= new TaskStatus("test1");
        TaskStatus status2 = new TaskStatus("test2");
        TaskStatus status3 = new TaskStatus("test3");
        test.saveTaskStatus(status1);
        test.saveTaskStatus(status2);
        test.saveTaskStatus(status3);
        //Assert
        assertEquals(3, status3.getIdTaskStatus());
    }

    @Test
    void getDescriptionSuccess() {
        //Arrange
        TaskStatusStore test = new TaskStatusStore();
        TaskStatus status1= new TaskStatus("test1");
        TaskStatus status2 = new TaskStatus("test2");
        TaskStatus status3 = new TaskStatus("test3");
        test.saveTaskStatus(status1);
        test.saveTaskStatus(status2);
        test.saveTaskStatus(status3);
        //Assert
        assertEquals("test1", status1.getDescription());
        assertEquals("test2", status2.getDescription());
        assertEquals("test3", status3.getDescription());
    }

    @Test
    void getDescriptionFail() {
        //Arrange
        TaskStatusStore test = new TaskStatusStore();
        TaskStatus status1= new TaskStatus("test1");
        TaskStatus status2 = new TaskStatus("test2");
        TaskStatus status3 = new TaskStatus("test3");
        test.saveTaskStatus(status1);
        test.saveTaskStatus(status2);
        test.saveTaskStatus(status3);
        //Assert
        assertNotEquals("test2", status1.getDescription());
        assertNotEquals("test3", status2.getDescription());
        assertNotEquals("test1", status3.getDescription());
    }

    @Test
    void setID_TaskStatusSuccess() {
        //Arrange
        TaskStatus status1= new TaskStatus("test1");
        TaskStatus status2 = new TaskStatus("test2");
        TaskStatus status3 = new TaskStatus("test3");
        status1.setIDTaskStatus(1);
        status2.setIDTaskStatus(2);
        status3.setIDTaskStatus(3);
        //Assert
        assertEquals(1, status1.getIdTaskStatus());
        assertEquals(2, status2.getIdTaskStatus());
        assertEquals(3, status3.getIdTaskStatus());
    }

    @Test
    void setID_TaskStatusFail() {
        //Arrange
        TaskStatus status1= new TaskStatus("test1");
        TaskStatus status2 = new TaskStatus("test2");
        TaskStatus status3 = new TaskStatus("test3");
        status1.setIDTaskStatus(1);
        status2.setIDTaskStatus(2);
        status3.setIDTaskStatus(3);
        //Assert
        assertNotEquals(1, status2.getIdTaskStatus());
        assertNotEquals(2, status3.getIdTaskStatus());
        assertNotEquals(3, status1.getIdTaskStatus());
    }

    @Test
    void checkRulesLenght() {
        //Arrange
        boolean result;
        try {
            TaskStatus status = new TaskStatus("t1");
            result = false;
        } catch(IllegalArgumentException e){

            result = true;
        }
        //Assert
        assertTrue(result);
    }

    @Test
    void checkRulesEmpty() {
        //Arrange
        boolean result;
        try {
            TaskStatus status = new TaskStatus("");
            result = false;
        } catch(IllegalArgumentException e){
            result = true;
        }
        //Assert
        assertTrue(result);
    }

    @Test
    void checkRulesIdNegative() {
        //Arrange
        boolean result;
        try {
            TaskStatus status = new TaskStatus("teste");
            status.setIDTaskStatus(-1);
            result = false;
        } catch(IllegalArgumentException e){
            result = true;
        }
        //Assert
        assertTrue(result);
    }

    @Test
    void overrideTests() {
        //Arrange
        TaskStatus status1= new TaskStatus("test1");
        TaskStatus status2 = new TaskStatus("test2");
        TaskStatus status3 = new TaskStatus("test3");
        TaskStatus status4 = null;
        TaskStatus status5= new TaskStatus("test1");
        TaskStatus status6= new TaskStatus("test1");
        Typology typo = new Typology(new Description("test"));
        status1.setIDTaskStatus(1);
        status2.setIDTaskStatus(2);
        status3.setIDTaskStatus(3);
        status5.setIDTaskStatus(55);
        status6.setIDTaskStatus(1);
        //Assert
        assertNotEquals(1, status2.getIdTaskStatus());
        assertNotEquals(2, status3.getIdTaskStatus());
        assertNotEquals(3, status1.getIdTaskStatus());
        assertEquals(status1.getClass(),status2.getClass());
        assertEquals(status1,status6);
        assertNull(status4);
        assertEquals(status5,status1);
        assertNotEquals(typo,status1);
    }

}