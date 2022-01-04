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
        Typology cenas = new Typology("Fixe");
        List<String> businesses = new ArrayList<>();
        LocalDate date = LocalDate.now();
        Project XPTO = new Project("001", "XPTO2", "Teste fixe", "ISEP", "cenas", businesses, date, 7, 3000);

        //Expected
        String code = XPTO.getCode();
        String name = XPTO.getName();
        String description = XPTO.getDescription();
        String customer = XPTO.getCustomer();
        String typology = XPTO.getTypology();
        List<String> BSector = XPTO.getBusinessSector();
        LocalDate date1 = XPTO.getStartDate();
        int sprints = XPTO.getNumberOfSprints();
        int budget2 = XPTO.getBudget();

        //Results
        assertEquals(code, "001");
        assertEquals(name, "XPTO2");
        assertEquals(description, "Teste fixe");
        assertEquals(customer, "ISEP");
        assertEquals(typology, "cenas");
        assertEquals(BSector, businesses);
        assertEquals(date1, date);
        assertEquals(sprints,7);
        assertEquals(budget2, 3000);

    }


}