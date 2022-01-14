//package switch2021.project.model;
//
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//
//import java.time.LocalDate;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//class CompanyTest {
//
//    @Test
//    public void SearchUserByPartiallySwitchedEmail() {
//
//        //Arrange
//        SystemUser joana = new SystemUser("Joana Silva", "1234@isep.ipp.pt", "Aluna", "12345", new UserProfile("XXX", "AAA"));
//        Company company = new Company();
//        company.saveSystemUser(joana);
//        //Act
//        SystemUser joanasilva = company.getUserByEmail("1235@isep.ipp.pt");
//        //Assert
//        assertNotEquals(joana, joanasilva);
//    }
//
//    @Test
//    public void SearchUserByWrongEmail() {
//
//        //Arrange
//        SystemUser joana = new SystemUser("Joana Silva", "1234@isep.ipp.pt", "Aluna", "12345", new UserProfile("XXX", "AAA"));
//        Company company = new Company();
//        company.saveSystemUser(joana);
//        //Act
//        SystemUser joanasilva = company.getUserByEmail("4321@isep.ipp.pt");
//        //Assert
//        assertNotEquals(joana, joanasilva);
//    }
//
//    /**
//     * Teste do searchProject, ainda acabar
//     **/
//
////    @Test
////    @DisplayName("Teste de craição de projecto atraves de Company (Paulo)")
////    void searchProject() {
////      List<String> businesses = new ArrayList<>();
////        LocalDate date = LocalDate.now();
////        Company XPTO = new Company();
////
////        XPTO.add(XPTO.createProject("002", "XPTO2", "Teste fixe_2", "ISEP_2",
////                "cenas_2", businesses, date, 9, 5000));
////
////    }
//
////    @Test
////    @DisplayName("teste com inicialização de instancia")
////    void searchProject() {
////        //Input
////        List<String> businesses = new ArrayList<>();
////        LocalDate date = LocalDate.now();
////
////        Project XPTO = new Project("001", "XPTO1", "Teste fixe", "ISEP", "cenas", businesses, date
////                , 7, 3000);
////
////        Project XPTO_2 = new Project("002", "XPTO2", "Teste fixe_2", "ISEP_2", "cenas_2", businesses, date
////                , 9, 5000);
////
////        Company comp = new Company();
////        comp.add(XPTO);
////        comp.add(XPTO_2);
////
////        //Expected
////        String expected = comp.getProj(0).getProjectName();
////        String expected_2 = comp.getProj(1).getProjectName();
////        String expected_3 = comp.getProj(2).getProjectName();
////
////        //Results
////        assertEquals(expected, "XPTO1");
////        assertEquals(expected_2, "XPTO2");
////        assertEquals(expected_3, "XPTO1");
////
////    }
////
////    @Test
////    @DisplayName("teste sem inicialização de instancia")
////    void searchProject_2() {
////        //Input
////        List<String> businesses = new ArrayList<>();
////        LocalDate date = LocalDate.now();
////        Project XPTO = new Project("001", "XPTO1", "Teste fixe", "ISEP", "cenas", businesses, date
////                , 7, 3000);
////
////        SystemUser ivan = new SystemUser("Ivan Aguiar", "1211768@isep.ipp.pt", "123456");
////
////        //Act
////        boolean comp = Company.arrayProj.add(XPTO);
////        Company comp2 = new Company();
////        String expected = comp2.getProj(0).getProjectName();
////
////        boolean sysUs = Company.arraySyUser.add(ivan);
////        String expected_SU = comp2.getSyUser(0).getUserName();
////
////        //Result
////        assertTrue(comp);
////        assertEquals( "XPTO1",expected);
////        assertTrue(sysUs);
////        assertEquals("Ivan Aguiar", expected_SU);
////
////                    /** Duvidas sobre isto, inicializa-se a instancia comp2 sem lhe adicionar
////                     nada e ao buscar o index 0 diz que XPOT1 já lá está, ou seja como arrayProj
////                     está static ao usar Company.arraProj.add(XPTO) adiciona o projecto.
////                     o mesmo pode ser feito para SystemUser **/
////    }
///*    @Test
//    public void SearchUserByEmail() {
//
//        //Act
//        SystemUser ana = new SystemUser("Ana", "1211748@isep.ipp.pt", "Developer", "12345", new UserProfile("Visit", "System"));
//        Company company = new Company(); // criar uma company
//        company.getSystemUserStore().saveSystemUser(ana); //ana = objeto da classe SU
//        //Arrange
//        SystemUser ana2 = company.getSystemUserStore().getUserByEmail("1211748@isep.ipp.pt"); // estou a ir buscar um utilizador com o email etc
//        //Assert
//        assertEquals(ana, ana2);
//    }
//
//
//    @Test
//    public void verifyUpdatePassword() {
//
//        //Arrange
//        UserProfile tes = new UserProfile("ddd", "pro");
//        SystemUser joana = new SystemUser("Joana", "1211770@isep.ipp.pt", "Aluna_10", "png_123", tes);
//        //Act
//        assertTrue(joana.updatePassword("png_123", "GOODBYE"));
//        //Assert
//        assertEquals("GOODBYE", joana.getPassword());
//    }*/
//
//
//
//    // Teste de lista de UserProfile em company (Ivan)
//
//   /*@Test
//    public void inicializeUserProfileslistwithdefaultUserProfiles() {
//        //Input
//        Company comTest = new Company();
//
//        //Expected
//        List<UserProfile> expected = new ArrayList<>();
//        expected.add(new UserProfile("Visitor", "System UserProfile"));
//        expected.add(new UserProfile("Administrator", "System UserProfile"));
//        //Result
//        assertEquals(expected, comTest.getArrayUserProfile());
//    }*/
//
//    /**
//     * >>>>>> Tests from project <<<<<<
//     **/
//
//    // Test to validate if there is project code (Cris US009)
//    @Test
//    public void getProjValidProjectCode() {
//        //arrange
//        Company company = new Company();
//        Project proj1 = new Project("123", "CDC", "teste", new Customer("email@domain.pt"),
//                new Typology("description"), new BusinessSector("ee"), LocalDate.now(), 5, 555);
//        company.addProject(proj1);
//        //act
//        Project proj2 = company.getProject("123");
//        //assert information
//        assertEquals(proj1, proj2);
//    }
//
//    @Test
//    public void getProjInvalidProjectCode() {
//        //arrange
//        Company company = new Company();
//        Project proj1 = new Project("123", "CDC", "teste", new Customer("email@domain.pt"),
//                new Typology("description"), new BusinessSector("ee"), LocalDate.now(), 5, 555);
//        company.addProject(proj1);
//        //act
//        Project proj2 = company.getProject("125");
//        //assert information
//        assertNull(proj2);
//    }
//
//
//    @Test
//    void searchUsersOnlyByStateSuccess() {
//        //Input
//        Company co = new Company();
//        UserProfile p1 = new UserProfile("111", "222");
//        UserProfile p2 = new UserProfile("333", "444");
//        UserProfile p3 = new UserProfile("555", "666");
//        UserProfile p4 = new UserProfile("777", "888");
//        UserProfile p5 = new UserProfile("999", "000");
//        SystemUser usr1 = new SystemUser("aaaa", "bbbb", "cccc", "dddd", p1);
//        SystemUser usr2 = new SystemUser("eeee", "ffff", "gggg", "hhhh", p2);
//        SystemUser usr3 = new SystemUser("iiii", "jjjj", "kkkk", "llll", p3);
//        SystemUser usr4 = new SystemUser("mmmm", "nnnn", "oooo", "pppp", p4);
//        SystemUser usr5 = new SystemUser("qqqq", "rrrr", "ssss", "tttt", p5);
//        co.addSystemUser(usr1);
//        co.addSystemUser(usr2);
//        co.addSystemUser(usr3);
//        co.addSystemUser(usr4);
//        co.addSystemUser(usr5);
//        //Expected
//        int[] list = {};
//        List<SystemUser> resultList = co.searchUsers("", "", "", 0, list);
//        List<SystemUser> expectedList = Arrays.asList(usr1, usr2, usr3, usr4, usr5);
//        //Result
//        assertEquals(expectedList, resultList);
//    }
//
//    @Test
//    void searchUsersOnlyByNameSuccess() {
//        //Input
//        Company co = new Company();
//        UserProfile p1 = new UserProfile("111", "222");
//        UserProfile p2 = new UserProfile("333", "444");
//        UserProfile p3 = new UserProfile("555", "666");
//        UserProfile p4 = new UserProfile("777", "888");
//        UserProfile p5 = new UserProfile("999", "000");
//        SystemUser usr1 = new SystemUser("aaai", "bbbb", "cccc", "dddd", p1);
//        SystemUser usr2 = new SystemUser("qeee", "ffff", "gggg", "hhhh", p2);
//        SystemUser usr3 = new SystemUser("iiii", "jjjj", "kkkk", "llll", p3);
//        SystemUser usr4 = new SystemUser("mmam", "nnnn", "oooo", "pppp", p4);
//        SystemUser usr5 = new SystemUser("qmqe", "rrrr", "ssss", "tttt", p5);
//        co.addSystemUser(usr1);
//        co.addSystemUser(usr2);
//        co.addSystemUser(usr3);
//        co.addSystemUser(usr4);
//        co.addSystemUser(usr5);
//        //Expected
//        int[] list = {};
//        List<SystemUser> resultList = co.searchUsers("a", "", "", -1, list);
//        List<SystemUser> expectedList = Arrays.asList(usr1, usr4);
//        //Result
//        assertEquals(expectedList, resultList);
//    }
//
//    @Test
//    void searchUsersOnlyByEmailSuccess() {
//        //Input
//        Company co = new Company();
//        UserProfile p1 = new UserProfile("111", "222");
//        UserProfile p2 = new UserProfile("333", "444");
//        UserProfile p3 = new UserProfile("555", "666");
//        UserProfile p4 = new UserProfile("777", "888");
//        UserProfile p5 = new UserProfile("999", "000");
//        SystemUser usr1 = new SystemUser("aaai", "brbb", "cccc", "dddd", p1);
//        SystemUser usr2 = new SystemUser("qeee", "ffnf", "gggg", "hhhh", p2);
//        SystemUser usr3 = new SystemUser("iiii", "jjjb", "kkkk", "llll", p3);
//        SystemUser usr4 = new SystemUser("mmam", "fnnn", "oooo", "pppp", p4);
//        SystemUser usr5 = new SystemUser("qmqe", "rjrr", "ssss", "tttt", p5);
//        co.addSystemUser(usr1);
//        co.addSystemUser(usr2);
//        co.addSystemUser(usr3);
//        co.addSystemUser(usr4);
//        co.addSystemUser(usr5);
//        //Expected
//        int[] list = {};
//        List<SystemUser> resultList = co.searchUsers("", "b", "", -1, list);
//        List<SystemUser> expectedList = Arrays.asList(usr1, usr3);
//        //Result
//        assertEquals(expectedList, resultList);
//    }
//
//    @Test
//    void searchUsersOnlyByFunctionSuccess() {
//        //Input
//        Company co = new Company();
//        UserProfile p1 = new UserProfile("111", "222");
//        UserProfile p2 = new UserProfile("333", "444");
//        UserProfile p3 = new UserProfile("555", "666");
//        UserProfile p4 = new UserProfile("777", "888");
//        UserProfile p5 = new UserProfile("999", "000");
//        SystemUser usr1 = new SystemUser("aaai", "brbb", "ckcc", "lddd", p1);
//        SystemUser usr2 = new SystemUser("qeee", "ffnf", "gggo", "phhh", p2);
//        SystemUser usr3 = new SystemUser("iiii", "jjjb", "kksk", "ltll", p3);
//        SystemUser usr4 = new SystemUser("mmam", "fnnn", "oxoo", "ppdp", p4);
//        SystemUser usr5 = new SystemUser("qmqe", "rjrr", "gsss", "ttth", p5);
//        co.addSystemUser(usr1);
//        co.addSystemUser(usr2);
//        co.addSystemUser(usr3);
//        co.addSystemUser(usr4);
//        co.addSystemUser(usr5);
//        //Expected
//        int[] list = {};
//        List<SystemUser> resultList = co.searchUsers("", "", "g", -1, list);
//        List<SystemUser> expectedList = Arrays.asList(usr2, usr5);
//        //Result
//        assertEquals(expectedList, resultList);
//    }
//
//    /*@Test
//    void searchUsersOnlyByUserProfilesSuccess() {
//        //Input
//        Company co = new Company();
//        UserProfile p1 = new UserProfile("111", "222");
//        UserProfile p2 = new UserProfile("333", "444");
//        UserProfile p3 = new UserProfile("555", "666");
//        UserProfile p4 = new UserProfile("777", "888");
//        UserProfile p5 = new UserProfile("999", "000");
//        SystemUser usr1 = new SystemUser("aaai", "brbb", "cccc", "dddd", p1);
//        SystemUser usr2 = new SystemUser("qeee", "ffnf", "gggg", "hhhh", p2);
//        SystemUser usr3 = new SystemUser("iiii", "jjjb", "kkkk", "llll", p3);
//        SystemUser usr4 = new SystemUser("mmam", "fnnn", "oooo", "pppp", p4);
//        SystemUser usr5 = new SystemUser("qmqe", "rjrr", "ssss", "tttt", p5);
//        co.addSystemUser(usr1);
//        co.addSystemUser(usr2);
//        co.addSystemUser(usr3);
//        co.addSystemUser(usr4);
//        co.addSystemUser(usr5);
//        //Expected
//        int[] list = {3};
//        List<SystemUser> resultList = co.searchUsers("", "", "", -1, list);
//        List<SystemUser> expectedList = Arrays.asList(usr2);
//        //Result
//        assertEquals(expectedList, resultList);
//    }*/
//
//    //US001:
//    @Test
//    public void createSystemUserWithPhotoSuccess() {
//        //Arrange
//        String userName = "manueloliveira";
//        String email = "manueloliveira@beaver.com";
//        String password = "ghi";
//        String function = "tester";
//        String photo = "photo";
//        Company company = new Company();
//        SystemUser newUser = company.createSystemUser(userName, email, function, photo, password);
//
//        String userNameExpected = "manueloliveira";
//        String emailExpected = "manueloliveira@beaver.com";
//        String passwordExpected = "ÊËÌ";
//        String functionExpected = "tester";
//        String photoExpected = "photo";
//        UserProfile pro = new UserProfile("Visitor", "System UserProfile");
//        List<UserProfile> assignedUserProfileExpected = new ArrayList<>();
//        assignedUserProfileExpected.add(pro);
//        //Act
//        String userNameResult = newUser.getUserName();
//        String emailResult = newUser.getEmail();
//        String passwordResult = newUser.getPassword();
//        String functionResult = newUser.getFunction();
//        String photoResult = newUser.getPhoto();
//        boolean activateUserResult = newUser.getUserActivated();
//        List<UserProfile> assignedUserProfileResult = newUser.getAssignedUserProfileList();
//        //Assert
//        assertEquals(userNameExpected, userNameResult);
//        assertEquals(emailExpected, emailResult);
//        assertEquals(passwordExpected, passwordResult);
//        assertEquals(functionExpected, functionResult);
//        assertEquals(photoExpected, photoResult);
//        assertFalse(activateUserResult);
//        assertEquals(assignedUserProfileExpected, assignedUserProfileResult);
//    }
//
//    @Test
//    public void createSystemUserWithoutPhotoSuccess() {
//        //Arrange
//        String userName = "manueloliveira";
//        String email = "manueloliveira@beaver.com";
//        String password = "ghi";
//        String function = "tester";
//        Company company = new Company();
//        SystemUser newUser = company.createSystemUser(userName, email, function, password);
//
//        String userNameExpected = "manueloliveira";
//        String emailExpected = "manueloliveira@beaver.com";
//        String passwordExpected = "ÊËÌ";
//        String functionExpected = "tester";
//        UserProfile pro = new UserProfile("Visitor", "System UserProfile");
//        List<UserProfile> assignedUserProfileExpected = new ArrayList<>();
//        assignedUserProfileExpected.add(pro);
//        //Act
//        String userNameResult = newUser.getUserName();
//        String emailResult = newUser.getEmail();
//        String passwordResult = newUser.getPassword();
//        String functionResult = newUser.getFunction();
//        boolean activateUserResult = newUser.getUserActivated();
//        List<UserProfile> assignedUserProfileResult = newUser.getAssignedUserProfileList();
//        //Assert
//        assertEquals(userNameExpected, userNameResult);
//        assertEquals(emailExpected, emailResult);
//        assertEquals(passwordExpected, passwordResult);
//        assertEquals(functionExpected, functionResult);
//        assertFalse(activateUserResult);
//        assertEquals(assignedUserProfileExpected, assignedUserProfileResult);
//    }
//
//    @Test
//    public void saveSystemUserWithPhotoSuccess() {
//        //Arrange
//        String userName = "manueloliveira";
//        String email = "manueloliveira@beaver.com";
//        String password = "ghi";
//        String function = "tester";
//        String photo = "photo";
//        Company company = new Company();
//        SystemUser newUser = company.createSystemUser(userName, email, function, photo, password);
//        int initialSize = company.getArraySyUser().size();
//
//        company.saveSystemUser(newUser);
//        int expected = initialSize + 1;
//        //Act
//        int result = company.getArraySyUser().size();
//        //Assert
//        assertEquals(expected, result);
//    }
//
//    @Test
//    public void saveSystemUserWithoutPhotoSuccess() {
//        //Arrange
//        String userName = "manueloliveira";
//        String email = "manueloliveira@beaver.com";
//        String password = "ghi";
//        String function = "tester";
//        Company company = new Company();
//        SystemUser newUser = company.createSystemUser(userName, email, function, password);
//        int initialSize = company.getArraySyUser().size();
//
//        company.saveSystemUser(newUser);
//        int expected = initialSize + 1;
//        //Act
//        int result = company.getArraySyUser().size();
//        //Assert
//        assertEquals(expected, result);
//    }
//
//    @Test
//    public void saveSystemUserWithPhotoFailEmailAlreadyExists() {
//        //Arrange
//        String userName = "manueloliveira";
//        String email = "manueloliveira@beaver.com";
//        String password = "ghi";
//        String function = "tester";
//        String photo = "photo";
//        Company company = new Company();
//        SystemUser newUser = company.createSystemUser(userName, email, function, photo, password);
//        company.saveSystemUser(newUser);
//
//        String userName2 = "maneloliveira";
//        SystemUser newUser2 = company.createSystemUser(userName2, email, function, photo, password);
//        //Assert
//        assertFalse(company.saveSystemUser(newUser2));
//    }
//
//    @Test
//    public void saveSystemUserWithoutPhotoFailEmailAlreadyExists() {
//        //Arrange
//        String userName = "manueloliveira";
//        String email = "manueloliveira@beaver.com";
//        String password = "ghi";
//        String function = "tester";
//        Company company = new Company();
//        SystemUser newUser = company.createSystemUser(userName, email, function, password);
//        company.saveSystemUser(newUser);
//
//        String userName2 = "maneloliveira";
//        SystemUser newUser2 = company.createSystemUser(userName2, email, function, password);
//        //Assert
//        assertFalse(company.saveSystemUser(newUser2));
//    }
//
//    @Test
//    public void saveSystemUserWithPhotoFailUserNameAlreadyExists() {
//        //Arrange
//        String userName = "manueloliveira";
//        String email = "manueloliveira@beaver.com";
//        String password = "ghi";
//        String function = "tester";
//        String photo = "photo";
//        Company company = new Company();
//        SystemUser newUser = company.createSystemUser(userName, email, function, photo, password);
//        company.saveSystemUser(newUser);
//
//        String email2 = "maneloliveira@beaver.com";
//        SystemUser newUser2 = company.createSystemUser(userName, email2, function, photo, password);
//        //Assert
//        assertFalse(company.saveSystemUser(newUser2));
//    }
//
//    @Test
//    public void saveSystemUserWithoutPhotoFailUserNameAlreadyExists() {
//        //Arrange
//        String userName = "manueloliveira";
//        String email = "manueloliveira@beaver.com";
//        String password = "ghi";
//        String function = "tester";
//        Company company = new Company();
//        SystemUser newUser = company.createSystemUser(userName, email, function, password);
//        company.saveSystemUser(newUser);
//
//        String email2 = "maneloliveira@beaver.com";
//        SystemUser newUser2 = company.createSystemUser(userName, email2, function, password);
//        //Assert
//        assertFalse(company.saveSystemUser(newUser2));
//    }
//
//    @Test
//    public void saveSystemUserFailUserNameEmpty() {
//        //Assert
//        assertThrows(IllegalArgumentException.class, () -> {
//            //Arrange
//            String userName = "";
//            String email = "manueloliveira@beaver.com";
//            String password = "ghi";
//            String function = "tester";
//            String photo = "photo";
//            Company company = new Company();
//            company.createSystemUser(userName, email, function, photo, password);
//        });
//    }
//
//    @Test
//    public void saveSystemUser() {
//        //Arrange
//        String userName = "Joana Silva";
//        String email = "1234@isep.ipp.pt";
//        String password = "1234";
//        String function = "Aluna";
//        String photo = "123_img";
//        Company company = new Company();
//        SystemUser joana = company.createSystemUser(userName, email, function, photo, password);
//        int initialSize = company.getArraySyUser().size();
//        company.saveSystemUser(joana);
//        int expected = initialSize +1 ;
//        //Act
//        int result = company.getArraySyUser().size();
//        //Assert
//        assertEquals(expected, result);
//    }
//
//
//    @Test
//    @DisplayName("Validate Allocation True")
//    public void validateAllocationTrue() {
//        //Arrange
//        /** user **/
//        UserProfile pro = new UserProfile("mku", "sss");
//        SystemUser newUser = new SystemUser("xyz", "fase", "des", "gth", pro);
//        LocalDate startDateAllocated = LocalDate.of(2021, 12, 12);
//        LocalDate endDateAllocated = LocalDate.of(2021, 12, 24);
//
//        Resource resAllo1 = new Resource(newUser, startDateAllocated, endDateAllocated, 100, .2);
//        LocalDate startDateToAllocate = LocalDate.of(2021, 12, 13);
//        LocalDate endDateToAllocate = LocalDate.of(2021, 12, 14);
//        Resource resAllo2 = new Resource(newUser, startDateToAllocate, endDateToAllocate, 100, .2);
//
//        /** project list **/
//
//        Company comTest = new Company();
//       List<Project> testProjectList = comTest.getArrayProject();
//       LocalDate startProjectDate = LocalDate.of(2021, 02, 25);
//       Customer cust = new Customer("ght@gmail.com");
//       Typology typo = new Typology("typo1");
//       BusinessSector busSector = new BusinessSector("busSec1");
//       Project proj1 = comTest.createProject("1", "gfd", "ghj", cust,typo, busSector, startProjectDate, 30,4500);
//       Project proj2 = comTest.createProject("2", "gfd", "ghj", cust,typo, busSector, startProjectDate, 30,4500);
//       Project proj3 = comTest.createProject("3", "gfd", "ghj", cust,typo, busSector, startProjectDate, 30,4500);
//       testProjectList.add(proj1);
//       testProjectList.add(proj2);
//       testProjectList.add(proj3);
//       proj1.addResource(resAllo1);
//       proj3.addResource(resAllo1);
//
//       //Act
//       boolean result = proj2.addResource(resAllo1);
//
//        //Assert
//        assertTrue(result);
//    }
//
//    @Test
//    @DisplayName("Validate Allocation False")
//    public void validateAllocationFalse() {
//        //Arrange
//        /** user **/
//        UserProfile pro = new UserProfile("mku", "sss");
//        SystemUser newUser = new SystemUser("xyz", "fase", "des", "gth", pro);
//        SystemUser newUser2 = new SystemUser("xyz", "fase", "des", "gth", pro);
//        LocalDate startDateAllocated = LocalDate.of(2021, 12, 12);
//        LocalDate endDateAllocated = LocalDate.of(2021, 12, 24);
//
//        Resource resAllo1 = new Resource(newUser, startDateAllocated, endDateAllocated, 100, .5);
//        LocalDate startDateToAllocate = LocalDate.of(2021, 12, 13);
//        LocalDate endDateToAllocate = LocalDate.of(2021, 12, 14);
//        Resource resAllo2 = new Resource(newUser2, startDateToAllocate, endDateToAllocate, 100, .5);
//
//        /** project **/
//        Company comTest = new Company();
//        List<Project> testProjectList = comTest.getArrayProject();
//        LocalDate startProjectDate = LocalDate.of(2021, 02, 25);
//        Customer cust = new Customer("ght@gmail.com");
//        Typology typo = new Typology("typo1");
//        BusinessSector busSector = new BusinessSector("busSec1");
//        Project proj1 = comTest.createProject("1", "gfd", "ghj", cust, typo, busSector, startProjectDate, 30, 4500);
//        Project proj2 = comTest.createProject("2", "gfd", "ghj", cust, typo, busSector, startProjectDate, 30, 4500);
//        Project proj3 = comTest.createProject("3", "gfd", "ghj", cust, typo, busSector, startProjectDate, 30, 4500);
//        testProjectList.add(proj1);
//        testProjectList.add(proj2);
//        testProjectList.add(proj3);
//        proj1.addResource(resAllo1);
//        proj3.addResource(resAllo1);
//
//        //Act
//        boolean result = comTest.validateAllocation(newUser,.5, startDateToAllocate, endDateToAllocate );
//
//        //Assert
//        assertFalse(result);
//    }
//
//    // Test to validate if there is project code (Cris US009)
//
//    @Test
//    public void getProjectListWithPORightEmptyList() {
//        //Arrange
//        Company company = new Company();
//        Project project = company.createProject("TEST", "Projecto Test", "decricao",
//                new Customer("marreta@email.pt"), new Typology("description"),
//                new BusinessSector("description"), LocalDate.now(), 10, 100000);
//        // Act
//        List<Project> projectList = company.getProjectListWithPORight("email");
//        //Assert
//        assertEquals(0, projectList.size());
//
//    }
//
//    @Test
//    public void getProjectListWithPORightWithEmptyEmailAndGetEmptyList() {
//        //Arrange
//        Company company = new Company();
//        // Act
//        List<Project> projectList = company.getProjectListWithPORight("");
//        List<Project> projectList2 = company.getProjectListWithPORight(null);
//        //Assert
//        assertEquals(0, projectList.size());
//        assertEquals(0, projectList2.size());
//
//    }
//
//    @Test
//    public void getProjectListWithPORighListWithResults() {
//        //Arrange
//        Company company = new Company();
//        Project project = company.createProject("TEST", "Projecto Test", "decricao",
//                new Customer("marreta@email.pt"), new Typology("description"),
//                new BusinessSector("description"), LocalDate.now(), 10, 100000);
//        project.createUserStory(UserStoryStatus., 12, "Default Story", 6);
//        project.setProductOwner(new SystemUser("Test User", "123@isep.ipp.pt",
//                "Product Owner", "AAA", company.getUserUserProfile("Product Owne")));
//        company.addProject(project);
//        // Act
//        List<Project> projectList = company.getProjectListWithPORight("123@isep.ipp.pt");
//        //Assert
//        assertNotEquals(0, projectList.size());
//    }
//
//    @Test
//    @DisplayName("Create Resource")
//    public void createResource() {
//        //Arrange
//        /** user **/
//        UserProfile pro = new UserProfile("mku", "sss");
//        SystemUser newUser = new SystemUser("xyz", "fase", "des", "gth", pro);
//        SystemUser newUser2 = new SystemUser("xyz", "fase", "des", "gth", pro);
//        LocalDate startDateAllocated = LocalDate.of(2021, 12, 12);
//        LocalDate endDateAllocated = LocalDate.of(2021, 12, 24);
//
//        Resource resAllo1 = new Resource(newUser, startDateAllocated, endDateAllocated, 100, .5);
//        LocalDate startDateToAllocate = LocalDate.of(2021, 12, 13);
//        LocalDate endDateToAllocate = LocalDate.of(2021, 12, 14);
////        Resource resAllo2 = new Resource(newUser2, startDateToAllocate, endDateToAllocate, 100, .5);
//
//        /** project **/
//        Company comTest = new Company();
//        List<Project> testProjectList = comTest.getArrayProject();
//        LocalDate startProjectDate = LocalDate.of(2021, 02, 25);
//        Customer cust = new Customer("ght@gmail.com");
//        Typology typo = new Typology("typo1");
//        BusinessSector busSector = new BusinessSector("busSec1");
//        Project proj1 = comTest.createProject("1", "gfd", "ghj", cust, typo, busSector, startProjectDate, 30, 4500);
//        Project proj2 = comTest.createProject("2", "gfd", "ghj", cust, typo, busSector, startProjectDate, 30, 4500);
//        Project proj3 = comTest.createProject("3", "gfd", "ghj", cust, typo, busSector, startProjectDate, 30, 4500);
//        testProjectList.add(proj1);
//        testProjectList.add(proj2);
//        testProjectList.add(proj3);
//        proj1.addResource(resAllo1);
//        proj3.addResource(resAllo1);
//
//        //Act
//        Resource resultexp = proj1.createResource(newUser, startDateAllocated, endDateAllocated, 100, .5);
//
//        //Assert
//        assertEquals(resAllo1,resultexp);
//    }
//}