package switch2021.project.entities.valueObjects.vos;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TaskIDTest {


    @Test
    @DisplayName("Test override conditions for coverage purposes")
    public void hashCode1() {
        //Arrange
        TaskID taskID = new TaskID();
        TaskID taskID1 = new TaskID();
        //Assert
        assertEquals(taskID.hashCode(), taskID1.hashCode());
    }
}
