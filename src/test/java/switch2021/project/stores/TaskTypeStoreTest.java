package switch2021.project.stores;

import org.junit.jupiter.api.Test;
import switch2021.project.model.TaskType;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class TaskTypeStoreTest {

    @Test
    void populateDefaultTestSuccess() {
        //Arrange
        TaskTypeStore test = new TaskTypeStore();
        test.populateDefault();
        //Asserts
        assertEquals(6, test.getTaskTypesDescription().size());
        //assertEquals("Meeting", test.getTaskTypesDescription().get(0));
        //assertEquals("Documentation", test.getTaskTypesDescription().get(1));
        //assertEquals("Design", test.getTaskTypesDescription().get(2));
        //assertEquals("Implementation", test.getTaskTypesDescription().get(3));
        //assertEquals("Testing", test.getTaskTypesDescription().get(4));
        //assertEquals("Deployment", test.getTaskTypesDescription().get(5));
    }

//    @Test
//    void createTaskTypeTestSuccess(){
//        //Arrange
//        TaskTypeStore test = new TaskTypeStore();
//        test.createTaskType("test1");
//        //Asserts
//        assertEquals(1, test.getTaskTypesDescription().size());
//        assertEquals("test1", test.getTaskTypesDescription().get(0));
//        assertEquals(1, test.getTypeByDescription("test1").getTypeID());
//    }

    @Test
    void getTaskTypesNamesSizeTest() {
        //Arrange
        TaskTypeStore test = new TaskTypeStore();
        test.populateDefault();
        List<String> testList = test.getTaskTypesDescription();
        //Asserts
        assertEquals(6, testList.size());
    }

    @Test
    void getTaskTypesNamesEmptyTest() {
        //Arrange
        TaskTypeStore test = new TaskTypeStore();
        List<String> testList = test.getTaskTypesDescription();
        //Asserts
        assertEquals(0, testList.size());
    }

//    @Test
//    void getTypeByNameTestSuccess() {
//        //Arrange
//        TaskTypeStore test = new TaskTypeStore();
//        test.populateDefault();
//        TaskType result = test.getTypeByDescription("Meeting");
//        TaskType expected = new TaskType("Meeting");
//        //Assert
//        assertEquals(expected.getDescriptionF(), result.getDescriptionF());
//    }
//
//    @Test
//    void saveTaskTypeSuccess() {
//        //Arrange
//        TaskTypeStore test = new TaskTypeStore();
//        TaskType type1 = new TaskType("type1");
//        TaskType type2 = new TaskType("type2");
//        test.saveTaskType(type1);
//        test.saveTaskType(type2);
//        //Assert
//        assertEquals(2, test.getTaskTypesDescription().size());
//        assertEquals("type1", test.getTaskTypesDescription().get(0));
//        assertEquals("type2", test.getTaskTypesDescription().get(1));
//    }

    @Test
    void saveTaskTypeIDSuccess() {
        //Arrange
        TaskTypeStore test = new TaskTypeStore();
        TaskType type1 = new TaskType("type1");
        TaskType type2 = new TaskType("type2");
        test.saveTaskType(type1);
        test.saveTaskType(type2);
        //Assert
        assertEquals(1, type1.getTypeID());
        assertEquals(2, type2.getTypeID());
    }

//    @Test
//    void saveTaskTypeRepeatedName() {
//        //Arrange
//        TaskTypeStore test = new TaskTypeStore();
//        TaskType type1 = new TaskType("type1");
//        TaskType type2 = new TaskType("type1");
//        test.saveTaskType(type1);
//        test.saveTaskType(type2);
//        //Assert
//        assertEquals(1, test.getTaskTypesDescription().size());
//        assertEquals("type1", test.getTaskTypesDescription().get(0));
//    }

    @Test
    void saveTaskTypeNull() {
        //Arrange
        TaskTypeStore test = new TaskTypeStore();
        TaskType type1 = null;
        test.saveTaskType(type1);
        //Assert
        assertEquals(0, test.getTaskTypesDescription().size());
    }

    @Test
    void TaskTypeStoreConstructorTest() {
        //Arrange
        TaskTypeStore test1 = new TaskTypeStore();
        List<TaskType> test1List = test1.getTaskTypeList();
        TaskTypeStore test2 = new TaskTypeStore();
        List<TaskType> test2List = test2.getTaskTypeList();
        //Assert
        assertNotSame(test1, test2);
        assertEquals(test1, test2);
        assertEquals(test1.hashCode(), test2.hashCode());
        assertEquals(test1List.hashCode(),test2List.hashCode());
    }
}