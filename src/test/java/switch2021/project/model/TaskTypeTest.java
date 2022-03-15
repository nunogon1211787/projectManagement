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
    @DisplayName("Test to get, with success, ID type.")
    void getType_IDSuccess() {
        //Arrange
        TaskTypeStore test = new TaskTypeStore();
        TaskType type = new TaskType("test");
        //Act
        test.saveTaskType(type);
        int x = type.getTypeID();
        //Assert
        assertEquals(1, x);
    }

    @Test
    @DisplayName("Test to get ID type, without saving the task type.")
    void getType_IDWithoutSave() {
        //Arrange
        TaskTypeStore test = new TaskTypeStore();
        TaskType type = new TaskType("test");
        //Act
        int x = type.getTypeID();
        //Assert
        assertEquals(0, x);
    }

    @Test
    @DisplayName("Test to get, without success, ID type.")
    void getType_IDFail() {
        //Arrange
        TaskTypeStore test = new TaskTypeStore();
        TaskType type = new TaskType("test");
        //Act
        int x = type.getTypeID();
        //Assert
        assertNotEquals(1, x);
    }

    @Test
    @DisplayName("Test to get the last ID type.")
    void getType_IDWith3Types() {
        //Arrange
        TaskTypeStore test = new TaskTypeStore();
        TaskType type1= new TaskType("test1");
        TaskType type2 = new TaskType("test2");
        TaskType type3 = new TaskType("test3");
        test.saveTaskType(type1);
        test.saveTaskType(type2);
        test.saveTaskType(type3);
        //Act
        int x = type3.getTypeID();
        //Assert
        assertEquals(3, x);
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
        String x1 = type1.getDescription().getDescriptionF();
        String x2 = type2.getDescription().getDescriptionF();
        String x3 = type3.getDescription().getDescriptionF();
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
        String x1 = type1.getDescription().getDescriptionF();
        String x2 = type2.getDescription().getDescriptionF();
        String x3 = type3.getDescription().getDescriptionF();
        //Assert
        assertNotEquals("test2", x1);
        assertNotEquals("test3", x2);
        assertNotEquals("test1", x3);
    }

    @Test
    @DisplayName("Test to set all ID type, with success.")
    void setType_IDSuccess() {
        //Arrange
        TaskType type1= new TaskType("test1");
        TaskType type2 = new TaskType("test2");
        TaskType type3 = new TaskType("test3");
        type1.setTypeID(1);
        type2.setTypeID(2);
        type3.setTypeID(3);
        //Act
        int x1 = type1.getTypeID();
        int x2 = type2.getTypeID();
        int x3 = type3.getTypeID();
        //Assert
        assertEquals(1, x1);
        assertEquals(2, x2);
        assertEquals(3, x3);
    }

    @Test
    @DisplayName("Test to set all ID type, without success.")
    void setType_IDFail() {
        //Arrange
        TaskType type1= new TaskType("test1");
        TaskType type2 = new TaskType("test2");
        TaskType type3 = new TaskType("test3");
        type1.setTypeID(1);
        type2.setTypeID(2);
        type3.setTypeID(3);
        //Act
        int x1 = type1.getTypeID();
        int x2 = type2.getTypeID();
        int x3 = type3.getTypeID();
        //Assert
        assertNotEquals(1, x2);
        assertNotEquals(2, x3);
        assertNotEquals(3, x1);
    }

//    @Test
//    @DisplayName("Test to check description, with low length, rules.")
//    void checkRulesLength() {
//        //Arrange
//        boolean result;
//        try {
//            TaskType type = new TaskType("t1");
//            result = false;
//        } catch(IllegalArgumentException e){
//            result = true;
//        }
//        //Assert
//        assertTrue(result);
//    }

//    @Test
//    @DisplayName("Test to check empty description rules.")
//    void checkRulesEmpty() {
//        //Arrange
//        boolean result;
//        try {
//            TaskType type = new TaskType("");
//            result = false;
//        } catch(IllegalArgumentException e){
//            result = true;
//        }
//        //Assert
//        assertTrue(result);
//    }

    @Test
    @DisplayName("Test to check negative id type.")
    void checkRulesIdNegative() {
        //Arrange
        boolean result;
        try {
            TaskType type = new TaskType("test");
            type.setTypeID(-1);
            result = false;
        } catch(IllegalArgumentException e){
            result = true;
        }
        //Assert
        assertTrue(result);
    }

    @Test
    @DisplayName("Test 1 to check Override Method, two equal objects.")
    void overrideTest_1(){
        //Arrange
        TaskType taskType = new TaskType("name");
        TaskType taskType_equal = new TaskType("name");
        taskType.setTypeID(1);
        taskType_equal.setTypeID(1);
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
        taskType.setTypeID(1);
        taskType1.setTypeID(1);
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
        taskType.setTypeID(1);
        taskType1.setTypeID(1);
        //Act
        Object x = taskType.hashCode();
        Object y = taskType1.hashCode();
        //Assert
        assertNotEquals(x,y);
    }

}