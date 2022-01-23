package switch2021.project.stores;

import org.junit.jupiter.api.Test;
import switch2021.project.model.Task;
//import sun.security.mscapi.CPublicKey;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TaskStoreTest {

    @Test
    void testIfCreatedListStartsEmpty() {

        //Arrange
        TaskStore test = new TaskStore();
        //Assert
        int listSizeResult = test.getTaskList().size();
        assertEquals(listSizeResult, 0);
        assertTrue(test.getTaskList().isEmpty());
    }

    @Test
    void addTaskToTheList() {

        //Arrange
        TaskStore test = new TaskStore();
        Task newTask = new Task();
        test.addTaskToTheList(newTask);
        //Assert
        assertEquals(1,test.getTaskList().size());
        assertTrue(test.getTaskList().contains(newTask));
    }

    @Test
    void removeTaskFromTheList() {

        //Arrange
        TaskStore test = new TaskStore();
        Task newTask = new Task();
        Task newTask2 = new Task();
        //Assert
        test.saveTask(newTask);
        test.saveTask(newTask2);
        assertTrue(test.removeTaskFromTheList(newTask));
        assertEquals(1,test.getTaskList().size());
    }

    @Test
    void validateIfTaskDontExistOnList() {

        //Arrange
        TaskStore test = new TaskStore();
        Task newTask = new Task();
        //Assert
        assertTrue(test.saveTask(newTask));
        assertFalse(test.saveTask(newTask));
    }




}