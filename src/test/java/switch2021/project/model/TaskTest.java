package switch2021.project.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TaskTest {

    @Test

    public void taskCreatorTest(){
        Task tastkTest = new Task("test");

        String expectedTask = "test";

        assertEquals(expectedTask,tastkTest.getDescription());
    }

}