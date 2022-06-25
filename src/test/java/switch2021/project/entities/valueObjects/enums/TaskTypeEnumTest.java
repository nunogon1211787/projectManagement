package switch2021.project.entities.valueObjects.enums;

import org.junit.jupiter.api.Test;
import switch2021.project.entities.valueObjects.vos.enums.TaskTypeEnum;

import static org.junit.jupiter.api.Assertions.*;

class TaskTypeEnumTest {
    @Test
    void values() {
        TaskTypeEnum[] valoresType = TaskTypeEnum.values();
        TaskTypeEnum x = valoresType[1];

        assertEquals(x, TaskTypeEnum.DOCUMENTATION);
        assertNotEquals(x, TaskTypeEnum.DESIGN);
        assertEquals(x.toString(), "DOCUMENTATION");
    }

    @Test
    void valueOf() {
        TaskTypeEnum x = TaskTypeEnum.valueOf("DOCUMENTATION");

        assertEquals(x, TaskTypeEnum.DOCUMENTATION);
    }
}