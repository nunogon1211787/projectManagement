package switch2021.project.stores;

import org.junit.jupiter.api.Test;
import switch2021.project.model.Task;

import static org.junit.jupiter.api.Assertions.*;

public class TaskListTest {


    @Test
    void testIfCreatedListStartsEmpty() {

        //Arrange
        TaskList test = new TaskList();
        //Assert
        int listSizeResult = test.getTaskList().size();
        assertEquals(listSizeResult, 0);
        assertTrue(test.getTaskList().isEmpty());
    }

    @Test
    void addTaskToTheList() {

        //Arrange
        TaskList test = new TaskList();
        Task newTask = new Task();
        test.addTaskToTheList(newTask);
        //Assert
        assertEquals(1,test.getTaskList().size());
        assertTrue(test.getTaskList().contains(newTask));
    }

    @Test
    void removeTaskFromTheList() {

        //Arrange
        TaskList test = new TaskList();
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
        TaskList test = new TaskList();
        Task newTask = new Task();
        //Assert
        assertTrue(test.saveTask(newTask));
        assertFalse(test.saveTask(newTask));
    }
}
