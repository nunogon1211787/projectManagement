package switch2021.project.model;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CompanyTest {

    /**
     * Teste do searchProject, ainda falta tudo de results
     **/

    @Test
    void searchProject() {
        //Input
        List<String> businesses = new ArrayList<>();
        LocalDate date = LocalDate.now();
        Project XPTO = new Project("001", "XPTO1", "Teste fixe", "ISEP", "cenas", businesses, date
                , 7, 3000);
        Project XPTO_2 = new Project("002", "XPTO2", "Teste fixe_2", "ISEP_2", "cenas_2", businesses, date
                , 9, 5000);

        Company comp = new Company();
        comp.add(XPTO);
        comp.add(XPTO_2);

        //Expected
        String expected = comp.getProj(0).getName();
        String expected_2 = comp.getProj(1).getName();

        //Results
        assertEquals(expected, "XPTO1");
        assertEquals(expected_2, "XPTO2");

    }

}