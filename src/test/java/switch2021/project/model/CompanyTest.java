package switch2021.project.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
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


    // Teste de adicionar profile em company (Cris US013)
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
    public void UserByEmail() {

        SystemUser ana = new SystemUser("", "1211748@isep.ipp.pt", "", "", new Profile("", ""));
        Company company = new Company(); // criar uma company
        company.saveSystemUser(ana); //ana = objeto da classe SU
        //Expected
        SystemUser ana2 = company.getUserByEmail("1211748@isep.ipp.pt"); // estou a ir buscar um utilizador com o email etc
        //Result
        assertEquals("1211748@isep.ipp.pt", ana2.getEmail());
    }


    @Test
    public void verifyUpdatePassword() {

        //Input
        Profile tes = new Profile("ddd", "pro");
        SystemUser joana = new SystemUser("Joana", "1211770@isep.ipp.pt", "Aluna_10", "png_123", tes);
        //RESULT
        assertTrue(joana.updatePassword("png_123", "GOODBYE"));
        assertEquals("GOODBYE", joana.getPassword());
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

    /**
     * >>>>>> Tests from project <<<<<<
     **/

    // Test to validate if there is project code (Cris US009)
    @Test
    public void getProjValidProjectCode() {
        //arrange
        Company company = new Company();
        Project proj1 = new Project("123", "CDC", "teste", new Customer("email@domain.pt"), new Typology("description"), null, LocalDate.now(), 5, 555);
        company.add(proj1);
        //act
        Project proj2 = company.getProj("123");
        // assert information
        assertEquals(proj1, proj2);
    }

    @Test
    public void getProjInvalidProjectCode() {
        //arrange
        Company company = new Company();
        Project proj1 = new Project("123", "CDC", "teste", new Customer("email@domain.pt"), new Typology("description"), null, LocalDate.now(), 5, 555);
        company.add(proj1);
        //act
        Project proj2 = company.getProj("125");
        // assert information
        assertNull(proj2);
    }





    @Test
    void searchUsersOnlyByStateSuccess() {
        //Input
        Company co = new Company();
        Profile p1 = new Profile("111", "222");
        Profile p2 = new Profile("333", "444");
        Profile p3 = new Profile("555", "666");
        Profile p4 = new Profile("777", "888");
        Profile p5 = new Profile("999", "000");
        SystemUser usr1 = new SystemUser("aaaa", "bbbb", "cccc", "dddd", p1);
        SystemUser usr2 = new SystemUser("eeee", "ffff", "gggg", "hhhh", p2);
        SystemUser usr3 = new SystemUser("iiii", "jjjj", "kkkk", "llll", p3);
        SystemUser usr4 = new SystemUser("mmmm", "nnnn", "oooo", "pppp", p4);
        SystemUser usr5 = new SystemUser("qqqq", "rrrr", "ssss", "tttt", p5);
        co.saveSystemUser(usr1);
        co.saveSystemUser(usr2);
        co.saveSystemUser(usr3);
        co.saveSystemUser(usr4);
        co.saveSystemUser(usr5);
        //Expected
        int[] list = {};
        List<SystemUser> resultList = co.searchUsers("", "", "", 0, list);
        List<SystemUser> expectedList = Arrays.asList(usr1, usr2, usr3, usr4, usr5);
        //Result
        assertEquals(expectedList, resultList);
    }

    @Test
    void searchUsersOnlyByNameSuccess() {
        //Input
        Company co = new Company();
        Profile p1 = new Profile("111", "222");
        Profile p2 = new Profile("333", "444");
        Profile p3 = new Profile("555", "666");
        Profile p4 = new Profile("777", "888");
        Profile p5 = new Profile("999", "000");
        SystemUser usr1 = new SystemUser("aaai", "bbbb", "cccc", "dddd", p1);
        SystemUser usr2 = new SystemUser("qeee", "ffff", "gggg", "hhhh", p2);
        SystemUser usr3 = new SystemUser("iiii", "jjjj", "kkkk", "llll", p3);
        SystemUser usr4 = new SystemUser("mmam", "nnnn", "oooo", "pppp", p4);
        SystemUser usr5 = new SystemUser("qmqe", "rrrr", "ssss", "tttt", p5);
        co.saveSystemUser(usr1);
        co.saveSystemUser(usr2);
        co.saveSystemUser(usr3);
        co.saveSystemUser(usr4);
        co.saveSystemUser(usr5);
        //Expected
        int[] list = {};
        List<SystemUser> resultList = co.searchUsers("a", "", "", -1, list);
        List<SystemUser> expectedList = Arrays.asList(usr1, usr4);
        //Result
        assertEquals(expectedList, resultList);
    }

    @Test
    void searchUsersOnlyByEmailSuccess() {
        //Input
        Company co = new Company();
        Profile p1 = new Profile("111", "222");
        Profile p2 = new Profile("333", "444");
        Profile p3 = new Profile("555", "666");
        Profile p4 = new Profile("777", "888");
        Profile p5 = new Profile("999", "000");
        SystemUser usr1 = new SystemUser("aaai", "brbb", "cccc", "dddd", p1);
        SystemUser usr2 = new SystemUser("qeee", "ffnf", "gggg", "hhhh", p2);
        SystemUser usr3 = new SystemUser("iiii", "jjjb", "kkkk", "llll", p3);
        SystemUser usr4 = new SystemUser("mmam", "fnnn", "oooo", "pppp", p4);
        SystemUser usr5 = new SystemUser("qmqe", "rjrr", "ssss", "tttt", p5);
        co.saveSystemUser(usr1);
        co.saveSystemUser(usr2);
        co.saveSystemUser(usr3);
        co.saveSystemUser(usr4);
        co.saveSystemUser(usr5);
        //Expected
        int[] list = {};
        List<SystemUser> resultList = co.searchUsers("", "b", "", -1, list);
        List<SystemUser> expectedList = Arrays.asList(usr1, usr3);
        //Result
        assertEquals(expectedList, resultList);
    }

    @Test
    void searchUsersOnlyByFunctionSuccess() {
        //Input
        Company co = new Company();
        Profile p1 = new Profile("111", "222");
        Profile p2 = new Profile("333", "444");
        Profile p3 = new Profile("555", "666");
        Profile p4 = new Profile("777", "888");
        Profile p5 = new Profile("999", "000");
        SystemUser usr1 = new SystemUser("aaai", "brbb", "ckcc", "lddd", p1);
        SystemUser usr2 = new SystemUser("qeee", "ffnf", "gggo", "phhh", p2);
        SystemUser usr3 = new SystemUser("iiii", "jjjb", "kksk", "ltll", p3);
        SystemUser usr4 = new SystemUser("mmam", "fnnn", "oxoo", "ppdp", p4);
        SystemUser usr5 = new SystemUser("qmqe", "rjrr", "gsss", "ttth", p5);
        co.saveSystemUser(usr1);
        co.saveSystemUser(usr2);
        co.saveSystemUser(usr3);
        co.saveSystemUser(usr4);
        co.saveSystemUser(usr5);
        //Expected
        int[] list = {};
        List<SystemUser> resultList = co.searchUsers("", "", "g", -1, list);
        List<SystemUser> expectedList = Arrays.asList(usr2, usr5);
        //Result
        assertEquals(expectedList, resultList);
    }

    @Test
    void searchUsersOnlyByProfilesSuccess() {
        //Input
        Company co = new Company();
        Profile p1 = new Profile("111", "222");
        Profile p2 = new Profile("333", "444");
        Profile p3 = new Profile("555", "666");
        Profile p4 = new Profile("777", "888");
        Profile p5 = new Profile("999", "000");
        SystemUser usr1 = new SystemUser("aaai", "brbb", "cccc", "dddd", p1);
        SystemUser usr2 = new SystemUser("qeee", "ffnf", "gggg", "hhhh", p2);
        SystemUser usr3 = new SystemUser("iiii", "jjjb", "kkkk", "llll", p3);
        SystemUser usr4 = new SystemUser("mmam", "fnnn", "oooo", "pppp", p4);
        SystemUser usr5 = new SystemUser("qmqe", "rjrr", "ssss", "tttt", p5);
        co.saveSystemUser(usr1);
        co.saveSystemUser(usr2);
        co.saveSystemUser(usr3);
        co.saveSystemUser(usr4);
        co.saveSystemUser(usr5);
        //Expected
        int[] list = {3};
        List<SystemUser> resultList = co.searchUsers("", "", "", -1, list);
        List<SystemUser> expectedList = Arrays.asList(usr2);
        //Result
        assertEquals(expectedList, resultList);
    }

}