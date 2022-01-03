package switch2021.project.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TypologyTest {

    /**
     * Constructor´s Tests
     */

    @Test
    public void contructorTypologyTestNull() {
        //Input
        Typology typo1 = new Typology();
        //Expected
        int id = typo1.getId_Typology();
        int value_id = 0;
        String description = typo1.getDescription();
        String value_description = null;
        //Results
        assertEquals(id, value_id);
        assertEquals(description, value_description);
    }

    @Test
    public void contructorTypologyTestNotNull() {
        //Input
        Typology typo2 = new Typology(02, "TestTypology");
        //Expected
        int id = typo2.getId_Typology();
        int value_id = 02;
        String description = typo2.getDescription();
        String value_description = "TestTypology";
        //Results
        assertEquals(id, value_id);
        assertEquals(description, value_description);
    }

}
