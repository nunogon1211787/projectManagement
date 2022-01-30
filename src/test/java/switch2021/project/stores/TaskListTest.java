package switch2021.project.stores;

import org.junit.jupiter.api.Test;
import switch2021.project.model.Task;
import switch2021.project.model.TaskStatus;

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
        Task newTask = new Task("teste");
        test.addTaskToTheList(newTask);
        //Assert
        assertEquals(1,test.getTaskList().size());
        assertTrue(test.getTaskList().contains(newTask));
    }

    @Test
    void removeTaskFromTheList() {

        //Arrange
        TaskList test = new TaskList();
        Task newTask = new Task("teste");
        Task newTask2 = new Task("teste2");
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
        Task newTask = new Task("teste");
        //Assert
        assertTrue(test.saveTask(newTask));
        assertFalse(test.saveTask(newTask));
    }

    @Test
    void createTaskStatus() {
        //Arranje
        TaskStatus status = new TaskStatus("teste123");

        //Assert
        assertEquals("teste123", status.getDescription());
    }

    @Test
    void getTaskStatusByDescription() {
        //Arranje
        TaskStatusStore store = new TaskStatusStore();
        store.populateDefault();

        //Assert
        assertEquals("Planned", store.getTaskStatusByDescription("Planned"));
    }

    @Test
    void taskOverride() {
        //Arranje
        TaskStatusStore store = new TaskStatusStore();
        store.populateDefault();

        TaskStatusStore store2 = new TaskStatusStore();
        store2.populateDefault();

        TaskStatusStore store3 = new TaskStatusStore();
        store3.populateDefault();
        store3.getTaskList().set(0,"Teste");

        //Assert
        assertEquals(store,store2);
        assertNotEquals(store,store3);
    }

    @Test
    void taskStatus() {
        //Arranje
        Task task = new Task("test");
        TaskStatus status = new TaskStatus("test");
        task.setStatus(status);

        //Assert
        assertEquals(status,task.getStatus());
    }

    @Test
    void taskStatusHash() {
        //Arranje
        TaskStatus status = new TaskStatus("test");
        TaskStatus status2 = new TaskStatus("test_2");

        //Assert
        assertNotEquals(status.hashCode(),status2.hashCode());
        assertEquals(status.hashCode(),status.hashCode());
    }

    @Test
    void taskStatusStoreHash() {
        //Arranje
        TaskStatusStore status = new TaskStatusStore();
        status.populateDefault();
        TaskStatusStore status2 = new TaskStatusStore();
        status2.populateDefault();
        status2.getTaskList().set(0,"teste");

        //Assert
        assertNotEquals(status.hashCode(),status2.hashCode());
        assertEquals(status.hashCode(),status.hashCode());
    }
}
