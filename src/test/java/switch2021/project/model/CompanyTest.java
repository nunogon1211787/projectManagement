package switch2021.project.model;

import org.junit.jupiter.api.DisplayName;
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
    @DisplayName("teste com inicialização de instancia")
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
        String expected = comp.getProj(0).getProjectName();
        String expected_2 = comp.getProj(1).getProjectName();
        String expected_3 = comp.getProj(2).getProjectName();

        //Results
        assertEquals(expected, "XPTO1");
        assertEquals(expected_2, "XPTO2");
        assertEquals(expected_3, "XPTO1");

    }

    @Test
    @DisplayName("teste sem inicialização de instancia")
    void searchProject_2() {
        //Input
        List<String> businesses = new ArrayList<>();
        LocalDate date = LocalDate.now();
        Project XPTO = new Project("001", "XPTO1", "Teste fixe", "ISEP", "cenas", businesses, date
                , 7, 3000);

        SystemUser ivan = new SystemUser("Ivan Aguiar", "1211768@isep.ipp.pt", "123456");

        //Act
        boolean comp = Company.arrayProj.add(XPTO);
        Company comp2 = new Company();
        String expected = comp2.getProj(0).getProjectName();

        boolean sysUs = Company.arraySyUser.add(ivan);
        String expected_SU = comp2.getSyUser(0).getUserName();

        //Result
        assertTrue(comp);
        assertEquals( "XPTO1",expected);
        assertTrue(sysUs);
        assertEquals("Ivan Aguiar", expected_SU);

                    /** Duvidas sobre isto, inicializa-se a instancia comp2 sem lhe adicionar
                     nada e ao buscar o index 0 diz que XPOT1 já lá está, ou seja como arrayProj
                     está static ao usar Company.arraProj.add(XPTO) adiciona o projecto.
                     o mesmo pode ser feito para SystemUser **/
    }

}