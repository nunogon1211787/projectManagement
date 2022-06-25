package switch2021.project.entities.valueObjects.enums;

import org.junit.jupiter.api.Test;
import switch2021.project.entities.valueObjects.vos.enums.TaskTypeEnum;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TaskTypeEnumTest {

    @Test
    void getTaskTypesDescriptionEnums() {
        List<String> listaTypes = TaskTypeEnum.getTaskTypesDescriptionEnums();
        List<String> listaTypes2 = List.of(new String[]{"MEETING", "DOCUMENTATION",
                "DESIGN", "IMPLEMENTATION", "TESTING", "DEPLOYMENT"});

        assertEquals(listaTypes2, listaTypes );
    }

    @Test
    void values() {
        TaskTypeEnum[] valoresType = TaskTypeEnum.values();
        TaskTypeEnum x = valoresType[1];

        assertEquals(TaskTypeEnum.DOCUMENTATION, x);
        assertNotEquals(TaskTypeEnum.DESIGN, x);
        assertEquals("DOCUMENTATION", x.toString());
    }

    @Test
    void valueOf() {
        TaskTypeEnum x = TaskTypeEnum.valueOf("DOCUMENTATION");

        assertEquals(TaskTypeEnum.DOCUMENTATION, x);
    }
}