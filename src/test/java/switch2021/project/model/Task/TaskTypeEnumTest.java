package switch2021.project.model.Task;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TaskTypeEnumTest {

    @Test
    void getTaskTypesDescriptionEnums() {
        List<String> listaTypes = TaskTypeEnum.getTaskTypesDescriptionEnums();
        List<String> listaTypes2 = List.of(new String[]{"Meeting", "Documentation", "Design", "Implementation", "Testing", "Deployment"});

        assertEquals(listaTypes2, listaTypes );
    }

    @Test
    void values() {
        TaskTypeEnum[] valoresType = TaskTypeEnum.values();
        TaskTypeEnum x = valoresType[1];

        assertEquals(x, TaskTypeEnum.Documentation);
        assertNotEquals(x, TaskTypeEnum.Design);
        assertEquals(x.toString(), "Documentation");
    }

    @Test
    void valueOf() {
        TaskTypeEnum x = TaskTypeEnum.valueOf("Documentation");

        assertEquals(x, TaskTypeEnum.Documentation);
    }
}