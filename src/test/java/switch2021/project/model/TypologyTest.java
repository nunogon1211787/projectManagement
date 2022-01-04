package switch2021.project.model;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

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
        int value_id = 000;
        String description = typo1.getDescription();
        String value_description = null;
        //Results
        assertEquals(id, value_id);
        assertEquals(description, value_description);
    }

    @Test
    public void contructorTypologyTestNotNull() {
        //Input
        Typology typo2 = new Typology("TestTypology");
        //Expected
        int id = typo2.getId_Typology();
        int value_id = 001;
        String description = typo2.getDescription();
        String value_description = "TestTypology";
        //Results
        assertEquals(id, value_id);
        assertEquals(description, value_description);
    }

    @Test
    public void contructorTypologyTestNotNull_ID() {
        //Input
        Typology typo3 = new Typology("TestTypology3");
        Typology typo4 = new Typology("TestTypology4");
        Typology typo5 = new Typology("TestTypology5");
        Typology typo6 = new Typology("TestTypology6");
        Typology typo7 = new Typology("TestTypology7");
        ArrayList<Typology> typologyArrayList = new ArrayList<Typology>();
        typologyArrayList.add(typo3);
        typologyArrayList.add(typo4);
        typologyArrayList.add(typo5);
        typologyArrayList.add(typo6);
        typologyArrayList.add(typo7);
        //Expected
        int id = typo6.getId_Typology();
        int value_id = 004;//Inicialmente começa ao 01...
        String description = typo6.getDescription();
        String value_description = "TestTypology6";
        //Results
        assertEquals(id, value_id);
        assertEquals(description, value_description);
    }


}
