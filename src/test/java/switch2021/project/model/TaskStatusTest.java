package switch2021.project.model;

import org.junit.jupiter.api.Test;
import switch2021.project.stores.TaskStatusStore;

import static org.junit.jupiter.api.Assertions.*;

class TaskStatusTest {

    @Test
    void hasDescriptionSuccess() {
        //Arrange
        TaskStatus status = new TaskStatus("teste");
        String testeName = "teste";
        //Assert
        assertTrue(status.hasDescription(testeName));
    }

    @Test
    void hasDescriptionFail() {
        //Arrange
        TaskStatus status = new TaskStatus("teste");
        String testeName = "fail";
        //Assert
        assertFalse(status.hasDescription(testeName));
    }

    @Test
    void hasDescriptionNull() {
        //Arrange
        TaskStatus status = new TaskStatus("teste");
        //Assert
        assertFalse(status.hasDescription(null));
    }

    @Test
    void getId_TaskStatusSuccess() {
        //Arrange
        TaskStatusStore teste = new TaskStatusStore();
        TaskStatus status = new TaskStatus("teste");
        teste.saveTaskStatus(status);
        //Assert
        assertEquals(1, status.getId_TaskStatus());
    }

    @Test
    void getId_TaskStatusWithoutSave() {
        //Arrange
        TaskStatusStore teste = new TaskStatusStore();
        TaskStatus status = new TaskStatus("teste");
        //Assert
        assertEquals(0, status.getId_TaskStatus());
    }

    @Test
    void getId_TaskStatusFail() {
        //Arrange
        TaskStatusStore teste = new TaskStatusStore();
        TaskStatus status = new TaskStatus("teste");
        //Assert
        assertNotEquals(1, status.getId_TaskStatus());
    }

    @Test
    void getId_TaskStatusWith3statuss() {
        //Arrange
        TaskStatusStore teste = new TaskStatusStore();
        TaskStatus status1= new TaskStatus("teste1");
        TaskStatus status2 = new TaskStatus("teste2");
        TaskStatus status3 = new TaskStatus("teste3");
        teste.saveTaskStatus(status1);
        teste.saveTaskStatus(status2);
        teste.saveTaskStatus(status3);
        //Assert
        assertEquals(3, status3.getId_TaskStatus());
    }

    @Test
    void getDescriptionSuccess() {
        //Arrange
        TaskStatusStore teste = new TaskStatusStore();
        TaskStatus status1= new TaskStatus("teste1");
        TaskStatus status2 = new TaskStatus("teste2");
        TaskStatus status3 = new TaskStatus("teste3");
        teste.saveTaskStatus(status1);
        teste.saveTaskStatus(status2);
        teste.saveTaskStatus(status3);
        //Assert
        assertEquals("teste1", status1.getDescription());
        assertEquals("teste2", status2.getDescription());
        assertEquals("teste3", status3.getDescription());
    }

    @Test
    void getDescriptionFail() {
        //Arrange
        TaskStatusStore teste = new TaskStatusStore();
        TaskStatus status1= new TaskStatus("teste1");
        TaskStatus status2 = new TaskStatus("teste2");
        TaskStatus status3 = new TaskStatus("teste3");
        teste.saveTaskStatus(status1);
        teste.saveTaskStatus(status2);
        teste.saveTaskStatus(status3);
        //Assert
        assertNotEquals("teste2", status1.getDescription());
        assertNotEquals("teste3", status2.getDescription());
        assertNotEquals("teste1", status3.getDescription());
    }

    @Test
    void setID_TaskStatusSuccess() {
        //Arrange
        TaskStatus status1= new TaskStatus("teste1");
        TaskStatus status2 = new TaskStatus("teste2");
        TaskStatus status3 = new TaskStatus("teste3");
        status1.setID_TaskStatus(1);
        status2.setID_TaskStatus(2);
        status3.setID_TaskStatus(3);
        //Assert
        assertEquals(1, status1.getId_TaskStatus());
        assertEquals(2, status2.getId_TaskStatus());
        assertEquals(3, status3.getId_TaskStatus());
    }

    @Test
    void setID_TaskStatusFail() {
        //Arrange
        TaskStatus status1= new TaskStatus("teste1");
        TaskStatus status2 = new TaskStatus("teste2");
        TaskStatus status3 = new TaskStatus("teste3");
        status1.setID_TaskStatus(1);
        status2.setID_TaskStatus(2);
        status3.setID_TaskStatus(3);
        //Assert
        assertNotEquals(1, status2.getId_TaskStatus());
        assertNotEquals(2, status3.getId_TaskStatus());
        assertNotEquals(3, status1.getId_TaskStatus());
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
            status.setID_TaskStatus(-1);
            result = false;
        } catch(IllegalArgumentException e){
            result = true;
        }
        //Assert
        assertTrue(result);
    }
}