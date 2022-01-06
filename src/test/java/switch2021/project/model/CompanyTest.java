package switch2021.project.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CompanyTest {

    /**
     * Teste do searchProject, ainda acabar
     **/

//    @Test
//    @DisplayName("Teste de craição de projecto atraves de Company (Paulo)")
//    void searchProject() {
//      List<String> businesses = new ArrayList<>();
//        LocalDate date = LocalDate.now();
//        Company XPTO = new Company();
//
//        XPTO.add(XPTO.createProject("002", "XPTO2", "Teste fixe_2", "ISEP_2",
//                "cenas_2", businesses, date, 9, 5000));
//
//    }

//    @Test
//    @DisplayName("teste com inicialização de instancia")
//    void searchProject() {
//        //Input
//        List<String> businesses = new ArrayList<>();
//        LocalDate date = LocalDate.now();
//
//        Project XPTO = new Project("001", "XPTO1", "Teste fixe", "ISEP", "cenas", businesses, date
//                , 7, 3000);
//
//        Project XPTO_2 = new Project("002", "XPTO2", "Teste fixe_2", "ISEP_2", "cenas_2", businesses, date
//                , 9, 5000);
//
//        Company comp = new Company();
//        comp.add(XPTO);
//        comp.add(XPTO_2);
//
//        //Expected
//        String expected = comp.getProj(0).getProjectName();
//        String expected_2 = comp.getProj(1).getProjectName();
//        String expected_3 = comp.getProj(2).getProjectName();
//
//        //Results
//        assertEquals(expected, "XPTO1");
//        assertEquals(expected_2, "XPTO2");
//        assertEquals(expected_3, "XPTO1");
//
//    }
//
//    @Test
//    @DisplayName("teste sem inicialização de instancia")
//    void searchProject_2() {
//        //Input
//        List<String> businesses = new ArrayList<>();
//        LocalDate date = LocalDate.now();
//        Project XPTO = new Project("001", "XPTO1", "Teste fixe", "ISEP", "cenas", businesses, date
//                , 7, 3000);
//
//        SystemUser ivan = new SystemUser("Ivan Aguiar", "1211768@isep.ipp.pt", "123456");
//
//        //Act
//        boolean comp = Company.arrayProj.add(XPTO);
//        Company comp2 = new Company();
//        String expected = comp2.getProj(0).getProjectName();
//
//        boolean sysUs = Company.arraySyUser.add(ivan);
//        String expected_SU = comp2.getSyUser(0).getUserName();
//
//        //Result
//        assertTrue(comp);
//        assertEquals( "XPTO1",expected);
//        assertTrue(sysUs);
//        assertEquals("Ivan Aguiar", expected_SU);
//
//                    /** Duvidas sobre isto, inicializa-se a instancia comp2 sem lhe adicionar
//                     nada e ao buscar o index 0 diz que XPOT1 já lá está, ou seja como arrayProj
//                     está static ao usar Company.arraProj.add(XPTO) adiciona o projecto.
//                     o mesmo pode ser feito para SystemUser **/
//    }

    /**
     * >>>>>> Testes de profile <<<<<<
     **/


    // Teste de adicionar profile em company (Cris)
    @Test
    public void addNewProfileWithFailNameEmpty() {
        //Input
        Company company = new Company();
        Profile up = company.createProfile("", "System Profile");
        //Expected
        boolean result = company.add(up);
        //Result
        assertFalse(result);
    }

    @Test
    public void addNewProfileWithFailTypeEmpty() {
        //Input
        Company company = new Company();
        Profile up = company.createProfile("Visitor", "");
        //Expected
        boolean expected = false;
        boolean result = company.add(up);
//Result
        assertFalse(result);
    }

    @Test
    public void addNewProfileWithFailNameAndProfileEmpty() {
//Input
        Company company = new Company();
        Profile up = company.createProfile("", "");
//Expected
        boolean expected = false;
        boolean result = company.add(up);
        //Result
        assertFalse(result);
    }

    @Test
    public void addNewProfileWithInvalidProfileType() {
//Input
        Company company = new Company();
        Profile up = company.createProfile("Visitor", "Other Type Profile");
//Expected
        boolean expected = false;
        boolean result = company.add(up);
//Result
        assertFalse(result);
    }

    @Test
    public void addNewProfileWithFailProfileAlreadyExist() {
//Input
        Company company = new Company();
        Profile up = company.createProfile("Visitor", "System Profile");
//Expected
        boolean expected = false;
        boolean result = company.add(up);
//Result
        assertFalse(result);
    }

    @Test
    public void addNewProfileWithSuccess() {
//Input
        Company company = new Company();
//Expected
        int inicialSize = company.getArrayProfile().size();
        Profile up = company.createProfile("Cris", "System Profile");
        company.add(up);
//Result
        assertEquals(company.getArrayProfile().size(), inicialSize + 1);
    }

    @Test
    public void addNewProfileWithSuccess2() {
//Input
        Company company = new Company();
// Expected
        int inicialSize = company.getArrayProfile().size();
        Profile up1 = company.createProfile("Cris", "System Profile");
        Profile up2 = company.createProfile("Cris_Dani", "System Profile");
        company.add(up1);
        company.add(up2);
//Result
        assertEquals(company.getArrayProfile().size(), inicialSize + 2);
    }

    // Teste de lista de profile em company (Ivan)

    @Test
    public void inicializeprofileslistwithdefaultprofiles() {
        //Input
        Company comTest = new Company();
        List<Profile> testProfileList = comTest.getArrayProfile();
        testProfileList.add(new Profile("Visitor", "System Profile"));
        testProfileList.add(new Profile("Administrator", "System Profile"));
        testProfileList.add(new Profile("Director", "System Profile"));
        testProfileList.add(new Profile("Project Manager", "Special Profile"));
        testProfileList.add(new Profile("Product Owner", "Special Profile"));
        testProfileList.add(new Profile("Scrum Master", "Special Profile"));
        testProfileList.add(new Profile("Project Team", "Special Profile"));
        //Expected
        List<Profile> expected = new ArrayList<>();
        expected.add(new Profile("Visitor", "System Profile"));
        expected.add(new Profile("Administrator", "System Profile"));
        expected.add(new Profile("Director", "System Profile"));
        expected.add(new Profile("Project Manager", "Special Profile"));
        expected.add(new Profile("Product Owner", "Special Profile"));
        expected.add(new Profile("Scrum Master", "Special Profile"));
        expected.add(new Profile("Project Team", "Special Profile"));
        //Result
        assertEquals(expected, testProfileList);
    }
}