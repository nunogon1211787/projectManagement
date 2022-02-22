package switch2021.project.model;

import org.junit.jupiter.api.Test;
import switch2021.project.stores.TaskTypeStore;
import static org.junit.jupiter.api.Assertions.*;

class TaskTypeTest {

    @Test
    void hasNameSuccess() {
        //Arrange
        TaskType type = new TaskType("teste");
        String testeName = "teste";
        //Assert
        assertTrue(type.hasName(testeName));
    }

    @Test
    void hasNameFail() {
        //Arrange
        TaskType type = new TaskType("teste");
        String testeName = "fail";
        //Assert
        assertFalse(type.hasName(testeName));
    }

    @Test
    void hasNameNull() {
        //Arrange
        TaskType type = new TaskType("teste");
        //Assert
        assertFalse(type.hasName(null));
    }

    @Test
    void getType_IDSuccess() {
        //Arrange
        TaskTypeStore teste = new TaskTypeStore();
        TaskType type = new TaskType("teste");
        teste.saveTaskType(type);
        //Assert
        assertEquals(1, type.getTypeID());
    }

    @Test
    void getType_IDWithoutSave() {
        //Arrange
        TaskTypeStore teste = new TaskTypeStore();
        TaskType type = new TaskType("teste");
        //Assert
        assertEquals(0, type.getTypeID());
    }

    @Test
    void getType_IDFail() {
        //Arrange
        TaskTypeStore teste = new TaskTypeStore();
        TaskType type = new TaskType("teste");
        //Assert
        assertNotEquals(1, type.getTypeID());
    }

    @Test
    void getType_IDWith3Types() {
        //Arrange
        TaskTypeStore teste = new TaskTypeStore();
        TaskType type1= new TaskType("teste1");
        TaskType type2 = new TaskType("teste2");
        TaskType type3 = new TaskType("teste3");
        teste.saveTaskType(type1);
        teste.saveTaskType(type2);
        teste.saveTaskType(type3);
        //Assert
        assertEquals(3, type3.getTypeID());
    }

    @Test
    void getNameSuccess() {
        //Arrange
        TaskTypeStore teste = new TaskTypeStore();
        TaskType type1= new TaskType("teste1");
        TaskType type2 = new TaskType("teste2");
        TaskType type3 = new TaskType("teste3");
        teste.saveTaskType(type1);
        teste.saveTaskType(type2);
        teste.saveTaskType(type3);
        //Assert
        assertEquals("teste1", type1.getName());
        assertEquals("teste2", type2.getName());
        assertEquals("teste3", type3.getName());
    }

    @Test
    void getNameFail() {
        //Arrange
        TaskTypeStore teste = new TaskTypeStore();
        TaskType type1= new TaskType("teste1");
        TaskType type2 = new TaskType("teste2");
        TaskType type3 = new TaskType("teste3");
        teste.saveTaskType(type1);
        teste.saveTaskType(type2);
        teste.saveTaskType(type3);
        //Assert
        assertNotEquals("teste2", type1.getName());
        assertNotEquals("teste3", type2.getName());
        assertNotEquals("teste1", type3.getName());
    }

    @Test
    void setType_IDSuccess() {
        //Arrange
        TaskType type1= new TaskType("teste1");
        TaskType type2 = new TaskType("teste2");
        TaskType type3 = new TaskType("teste3");
        type1.setTypeID(1);
        type2.setTypeID(2);
        type3.setTypeID(3);
        //Assert
        assertEquals(1, type1.getTypeID());
        assertEquals(2, type2.getTypeID());
        assertEquals(3, type3.getTypeID());
    }

    @Test
    void setType_IDFail() {
        //Arrange
        TaskType type1= new TaskType("teste1");
        TaskType type2 = new TaskType("teste2");
        TaskType type3 = new TaskType("teste3");
        type1.setTypeID(1);
        type2.setTypeID(2);
        type3.setTypeID(3);
        //Assert
        assertNotEquals(1, type2.getTypeID());
        assertNotEquals(2, type3.getTypeID());
        assertNotEquals(3, type1.getTypeID());
    }

    @Test
    void checkRulesLenght() {
        //Arrange
        boolean result;
        try {
            TaskType type = new TaskType("t1");
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
            TaskType type = new TaskType("");
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
            TaskType type = new TaskType("teste");
            type.setTypeID(-1);
            result = false;
        } catch(IllegalArgumentException e){
            result = true;
        }
        //Assert
        assertTrue(result);
    }


}