package switch2021.project.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ProjectTeamTest {


    /**
     * Constructor Test
     */
    @Test
    public void projectTeamTest() {
        //Arrange
        ProjectTeam test = new ProjectTeam();
        //Assert
        assertEquals(new ProjectTeam(), test);
    }

    @Test
    public void projectTeamListTest() {
        //Arrange
        ProjectTeam test = new ProjectTeam();
        //Assert
        assertEquals(new ProjectTeam().getProjectTeamList(), test.getProjectTeamList());
    }

    @Test
    @DisplayName("Test with mock if the resource is returned ")
    public void getResourceByEmailTestSuccess() {
        //Arrange
        ProjectTeam projectTeam = new ProjectTeam();

        Resource manuelbras = mock(Resource.class);
        when(manuelbras.isYour("manuelbras@beaver.com")).thenReturn(true);
        when(manuelbras.isCurrent()).thenReturn(true);

        projectTeam.saveResource(manuelbras);

        //Act
        Resource testRes = projectTeam.getResource("manuelbras@beaver.com");

        //Assert
        assertEquals(manuelbras, testRes);
    }

    @Test
    public void getResourceByEmailTestFail() {
        //Arrange
        ProjectTeam projectTeam = new ProjectTeam();

        Resource manuelbras = mock(Resource.class);
        when(manuelbras.isYour("manuelbras@beaver.com")).thenReturn(false);
        when(manuelbras.isCurrent()).thenReturn(false);

        projectTeam.saveResource(manuelbras);

        //Act
        Resource testRes = projectTeam.getResource("manuelbras@beaver.com");

        //Assert
        assertNull( testRes);
    }

    @Test
    public void getResourceByUserTestSuccess() {
        //Arrange
        ProjectTeam projectTeam = new ProjectTeam();
        SystemUser user = mock(SystemUser.class);
        Resource manuelbras = mock(Resource.class);
        when(manuelbras.isYour(user)).thenReturn(true);
        when(manuelbras.isCurrent()).thenReturn(true);

        projectTeam.saveResource(manuelbras);

        //Act
        Resource testRes = projectTeam.getResource(user);

        //Assert
        assertEquals(manuelbras, testRes);
    }

    @Test
    public void getResourceByUserTestFail() {
        //Arrange
        ProjectTeam projectTeam = new ProjectTeam();
        SystemUser user = mock(SystemUser.class);
        Resource manuelbras = mock(Resource.class);
        when(manuelbras.isYour(user)).thenReturn(false);
        when(manuelbras.isCurrent()).thenReturn(false);

        projectTeam.saveResource(manuelbras);

        //Act
        Resource testRes = projectTeam.getResource(user);

        //Assert
        assertNull(testRes);
    }

    @Test
    public void getResourceByRoleTestSuccess() {
        //Arrange
        ProjectTeam projectTeam = new ProjectTeam();
        ProjectRole role = mock(ProjectRole.class);
        Resource manuelbras = mock(Resource.class);
        when(manuelbras.isYour(role)).thenReturn(true);
        when(manuelbras.isCurrent()).thenReturn(true);

        projectTeam.saveResource(manuelbras);

        //Act
        Resource testRes = projectTeam.getResource(role);

        //Assert
        assertEquals(manuelbras, testRes);
    }

    @Test
    public void getResourceByRoleTestFail() {
        //Arrange
        ProjectTeam projectTeam = new ProjectTeam();
        ProjectRole role = mock(ProjectRole.class);
        Resource manuelbras = mock(Resource.class);
        when(manuelbras.isYour(role)).thenReturn(false);
        when(manuelbras.isCurrent()).thenReturn(false);

        projectTeam.saveResource(manuelbras);

        //Act
        Resource testRes = projectTeam.getResource(role);

        //Assert
        assertNull(testRes);
    }

    @Test
    public void getResourceByNameTestSuccess() {
        //Arrange
        ProjectTeam projectTeam = new ProjectTeam();

        Resource manuelbras = mock(Resource.class);
        when(manuelbras.isYourName("jorgebras")).thenReturn(true);
        when(manuelbras.isCurrent()).thenReturn(true);

        projectTeam.saveResource(manuelbras);

        //Act
        Resource testRes = projectTeam.getResourceByName("jorgebras");

        //Assert
        assertEquals(manuelbras, testRes);
    }

    @Test
    public void getResourceByNameTestFail() {
        //Arrange
        ProjectTeam projectTeam = new ProjectTeam();

        Resource manuelbras = mock(Resource.class);
        when(manuelbras.isYourName("jorgebras")).thenReturn(false);
        when(manuelbras.isCurrent()).thenReturn(false);

        projectTeam.saveResource(manuelbras);

        //Act
        Resource testRes = projectTeam.getResourceByName("jorgebras");

        //Assert
        assertNull(testRes);
    }

    /**
     * Get Current Resource List Test
     */
    @Test
    public void getCurrentResourcesNamesTestSuccess() {
        //Arrange
        Company company = new Company();
        Typology typo = company.getTypologyStore().getTypology("Fixed Cost");
        Customer customer = company.getCustomerStore().getCustomerByName("isep");
        BusinessSector sector = company.getBusinessSectorStore().getBusinessSectorByDescription("it");
        //Project 1
        Project proj1 = company.getProjectStore().createProject("prototype1", "proj1Prototype", customer,
                typo, sector, LocalDate.now().minusWeeks(2), 2, 3000);
        proj1.setEndDate(LocalDate.now().plusYears(1));
        company.getProjectStore().saveNewProject(proj1);
        UserProfile profile = company.getUserProfileStore().getUserProfile("Visitor");
        SystemUser user1 = new SystemUser("manuelbras", "manuelbras@beaver.com", "tester", "ghi", "ghi", "photo", profile);
        Resource manuelbras = new Resource(user1, LocalDate.of(2021, 11, 1), LocalDate.of(2022, 11, 15), 100, .5);
        SystemUser user2 = new SystemUser("manuelmartins", "manuelmartins@beaver.com", "tester", "ghi", "ghi", "photo", profile);
        Resource manuelmartins = new Resource(user2, LocalDate.now().minusDays(6), LocalDate.now().plusDays(7), 100, 1);
        proj1.getProjectTeam().saveResource(manuelbras);
        proj1.getProjectTeam().saveResource(manuelmartins);
        //Act
        List<String> testRes = new ArrayList<>();
        testRes.add("manuelbras");
        testRes.add("manuelmartins");
        //Assert
        assertEquals(proj1.getProjectTeam().getCurrentResourcesNames(), testRes);
    }

    @Test
    public void getCurrentResourcesNamesTestFail() {
        //Arrange
        Company company = new Company();
        Typology typo = company.getTypologyStore().getTypology("Fixed Cost");
        Customer customer = company.getCustomerStore().getCustomerByName("isep");
        BusinessSector sector = company.getBusinessSectorStore().getBusinessSectorByDescription("it");
        //Project 1
        Project proj1 = company.getProjectStore().createProject("prototype1", "proj1Prototype", customer,
                typo, sector, LocalDate.now().minusWeeks(2), 2, 3000);
        proj1.setEndDate(LocalDate.now().plusYears(1));
        company.getProjectStore().saveNewProject(proj1);
        UserProfile profile = company.getUserProfileStore().getUserProfile("Visitor");
        SystemUser user1 = new SystemUser("manuelbras", "manuelbras@beaver.com", "tester", "ghi", "ghi", "photo", profile);
        Resource manuelbras = proj1.createResource(user1, LocalDate.of(2021, 11, 1), LocalDate.of(2022, 11, 15), 100, .5);
        SystemUser user2 = new SystemUser("manuelmartins", "manuelmartins@beaver.com", "tester", "ghi", "ghi", "photo", profile);
        proj1.createResource(user2, LocalDate.now().minusDays(6), LocalDate.now().plusDays(7), 100, 1);
        proj1.getProjectTeam().saveResource(manuelbras);
        //Act
        List<String> testRes = new ArrayList<>();
        testRes.add("manuelbras");
        testRes.add("manuelmartins");
        //Assert
        assertNotEquals(proj1.getProjectTeam().getCurrentResourcesNames(), testRes);
    }

    /**
     * Create a new Resource Test
     */
    @Test
    public void createResourceTestSuccess() {
        //Arrange
        Company company = new Company();
        Typology typo = company.getTypologyStore().getTypology("Fixed Cost");
        Customer customer = company.getCustomerStore().getCustomerByName("isep");
        BusinessSector sector = company.getBusinessSectorStore().getBusinessSectorByDescription("it");
        //Project 1
        Project proj1 = company.getProjectStore().createProject("prototype1", "proj1Prototype", customer,
                typo, sector, LocalDate.now().minusWeeks(2), 2, 3000);
        proj1.setEndDate(LocalDate.now().plusYears(1));
        company.getProjectStore().saveNewProject(proj1);
        UserProfile profile = company.getUserProfileStore().getUserProfile("Visitor");
        SystemUser user1 = new SystemUser("manuelbras", "manuelbras@beaver.com", "tester", "ghi", "ghi", "photo", profile);
        Resource manuelbras = new Resource(user1, LocalDate.now(), LocalDate.now().plusWeeks(4), 100, .5);
        //Act
        Resource test = proj1.getProjectTeam().createResource(user1, LocalDate.now(), LocalDate.now().plusWeeks(4), 100, .5);
        //Assert
        assertEquals(test, manuelbras);
    }

    @Test
    public void createResourceTestFail() {
        //Assert
        assertThrows(NullPointerException.class, () -> {
            //Arrange
            Company company = new Company();
            Typology typo = company.getTypologyStore().getTypology("Fixed Cost");
            Customer customer = company.getCustomerStore().getCustomerByName("isep");
            BusinessSector sector = company.getBusinessSectorStore().getBusinessSectorByDescription("it");
            //Project 1
            Project proj1 = company.getProjectStore().createProject("prototype1", "proj1Prototype", customer,
                    typo, sector, LocalDate.now().minusWeeks(2), 2, 3000);
            proj1.setEndDate(LocalDate.now().plusYears(1));
            company.getProjectStore().saveNewProject(proj1);
            //Act
            proj1.getProjectTeam().createResource(null, LocalDate.now(), LocalDate.now().plusWeeks(4), 100, .5);
        });
    }


    /**
     * Save Test
     */
    @Test
    public void saveResourceTestSuccess() {
        //Arrange
        Company company = new Company();
        Typology typo = company.getTypologyStore().getTypology("Fixed Cost");
        Customer customer = company.getCustomerStore().getCustomerByName("isep");
        BusinessSector sector = company.getBusinessSectorStore().getBusinessSectorByDescription("it");
        //Project 1
        Project proj1 = company.getProjectStore().createProject("prototype1", "proj1Prototype", customer,
                typo, sector, LocalDate.now().minusWeeks(2), 2, 3000);
        proj1.setEndDate(LocalDate.now().plusYears(1));
        company.getProjectStore().saveNewProject(proj1);
        UserProfile profile = company.getUserProfileStore().getUserProfile("Visitor");
        SystemUser user1 = new SystemUser("manuelbras", "manuelbras@beaver.com", "tester", "ghi", "ghi", "photo", profile);
        Resource manuelbras = proj1.createResource(user1, LocalDate.now(), LocalDate.now().plusWeeks(4), 100, .5);
        //Act
        assertTrue(proj1.getProjectTeam().saveResource(manuelbras));
    }

    @Test
    public void saveResourceTestFail() {
        //Assert
        assertThrows(NullPointerException.class, () -> {
            //Arrange
            Company company = new Company();
            Typology typo = company.getTypologyStore().getTypology("Fixed Cost");
            Customer customer = company.getCustomerStore().getCustomerByName("isep");
            BusinessSector sector = company.getBusinessSectorStore().getBusinessSectorByDescription("it");
            //Project 1
            Project proj1 = company.getProjectStore().createProject("prototype1", "proj1Prototype", customer,
                    typo, sector, LocalDate.now().minusWeeks(2), 2, 3000);
            proj1.setEndDate(LocalDate.now().plusYears(1));
            company.getProjectStore().saveNewProject(proj1);
            UserProfile profile = company.getUserProfileStore().getUserProfile("Visitor");
            SystemUser user1 = new SystemUser("manuelbras", "manuelbras@beaver.com", "tester", "ghi", "ghi", "photo", profile);
            proj1.createResource(user1, LocalDate.now(), LocalDate.now().plusWeeks(4), 100, .5);
            //Act
            assertFalse(proj1.getProjectTeam().saveResource(null));
        });
    }

    /**
     * Assign new Project Role to a Resource
     */
    @Test //At this test the project role already exist in the team
    public void assignProjectRoleTestSuccessWithRoleInTheTeam() {
        //Arrange
        Company company = new Company();
        Typology typo = company.getTypologyStore().getTypology("Fixed Cost");
        Customer customer = company.getCustomerStore().getCustomerByName("isep");
        BusinessSector sector = company.getBusinessSectorStore().getBusinessSectorByDescription("it");
        //Project 1
        Project proj1 = company.getProjectStore().createProject("prototype1", "proj1Prototype", customer,
                typo, sector, LocalDate.now().minusWeeks(2), 2, 3000);
        proj1.setEndDate(LocalDate.now().plusYears(1));
        company.getProjectStore().saveNewProject(proj1);
        UserProfile profile = company.getUserProfileStore().getUserProfile("Visitor");
        //Resource 1
        SystemUser user1 = new SystemUser("manuelbras", "manuelbras@beaver.com", "tester", "ghi", "ghi", "photo", profile);
        Resource manuelbras = proj1.createResource(user1, LocalDate.now().minusWeeks(2), LocalDate.now().plusWeeks(4), 100, .5);
        manuelbras.setRole(company.getProjectRoleStore().getProjectRole("Scrum Master"));
        proj1.getProjectTeam().saveResource(manuelbras);
        //Resource 2
        SystemUser user2 = new SystemUser("manuelmartins", "manuelmartins@beaver.com", "tester", "ghi", "ghi", "photo", profile);
        Resource manuelmartins = proj1.createResource(user2, LocalDate.now().minusWeeks(2), LocalDate.now().plusWeeks(4), 100, 1);
        proj1.getProjectTeam().saveResource(manuelmartins);
        //Act
        proj1.getProjectTeam().assignProjectRole(manuelmartins, LocalDate.now(), 2, company.getProjectRoleStore().getProjectRole("Scrum Master"));
        //Assert
        assertEquals(proj1.getProjectTeam().getResource(user1).getRole(), company.getProjectRoleStore().getProjectRole("Team Member"));
        assertEquals(proj1.getProjectTeam().getResource(user2).getRole(), company.getProjectRoleStore().getProjectRole("Scrum Master"));
        assertEquals(4, proj1.getProjectTeam().getProjectTeamList().size());
        assertEquals(LocalDate.now(), proj1.getProjectTeam().getResource(user2).getStartDate());
    }

    @Test //At this test the project role already exist in the team
    public void assignProjectRoleTestSuccessWithOutRoleInTheTeam() {
        //Arrange
        Company company = new Company();
        Typology typo = company.getTypologyStore().getTypology("Fixed Cost");
        Customer customer = company.getCustomerStore().getCustomerByName("isep");
        BusinessSector sector = company.getBusinessSectorStore().getBusinessSectorByDescription("it");
        //Project 1
        Project proj1 = company.getProjectStore().createProject("prototype1", "proj1Prototype", customer,
                typo, sector, LocalDate.now().minusWeeks(2), 2, 3000);
        proj1.setEndDate(LocalDate.now().plusYears(1));
        company.getProjectStore().saveNewProject(proj1);
        UserProfile profile = company.getUserProfileStore().getUserProfile("Visitor");
        //Resource 1
        SystemUser user1 = new SystemUser("manuelbras", "manuelbras@beaver.com", "tester", "ghi", "ghi", "photo", profile);
        Resource manuelbras = proj1.createResource(user1, LocalDate.now().minusWeeks(2), LocalDate.now().plusWeeks(4), 100, .5);
        manuelbras.setRole(company.getProjectRoleStore().getProjectRole("Team Member"));
        proj1.getProjectTeam().saveResource(manuelbras);
        //Resource 2
        SystemUser user2 = new SystemUser("manuelmartins", "manuelmartins@beaver.com", "tester", "ghi", "ghi", "photo", profile);
        Resource manuelmartins = proj1.createResource(user2, LocalDate.now().minusWeeks(2), LocalDate.now().plusWeeks(4), 100, 1);
        proj1.getProjectTeam().saveResource(manuelmartins);
        //Act
        proj1.getProjectTeam().assignProjectRole(manuelmartins, LocalDate.now(), 2, company.getProjectRoleStore().getProjectRole("Scrum Master"));
        //Assert
        assertEquals(proj1.getProjectTeam().getResource(user1).getRole(), company.getProjectRoleStore().getProjectRole("Team Member"));
        assertEquals(proj1.getProjectTeam().getResource(user2).getRole(), company.getProjectRoleStore().getProjectRole("Scrum Master"));
        assertEquals(3, proj1.getProjectTeam().getProjectTeamList().size());
    }

    @Test  //Assign a Role to a resource as null
    public void assignProjectRoleTestNullFail() {
        //Assert
        assertThrows(NullPointerException.class, () -> {
            //Arrange
            Company company = new Company();
            Typology typo = company.getTypologyStore().getTypology("Fixed Cost");
            Customer customer = company.getCustomerStore().getCustomerByName("isep");
            BusinessSector sector = company.getBusinessSectorStore().getBusinessSectorByDescription("it");
            //Project 1
            Project proj1 = company.getProjectStore().createProject("prototype1", "proj1Prototype", customer,
                    typo, sector, LocalDate.now().minusWeeks(2), 2, 3000);
            proj1.setEndDate(LocalDate.now().plusYears(1));
            company.getProjectStore().saveNewProject(proj1);
            UserProfile profile = company.getUserProfileStore().getUserProfile("Visitor");
            //Resource 1
            SystemUser user1 = new SystemUser("manuelbras", "manuelbras@beaver.com", "tester", "ghi", "ghi", "photo", profile);
            Resource manuelbras = proj1.createResource(user1, LocalDate.now().minusWeeks(2), LocalDate.now().plusWeeks(4), 100, .5);
            manuelbras.setRole(company.getProjectRoleStore().getProjectRole("Scrum Master"));
            proj1.getProjectTeam().saveResource(manuelbras);
            //Resource 2
            SystemUser user2 = new SystemUser("manuelmartins", "manuelmartins@beaver.com", "tester", "ghi", "ghi", "photo", profile);
            Resource manuelmartins = proj1.createResource(user2, LocalDate.now().minusWeeks(2), LocalDate.now().plusWeeks(4), 100, 1);
            proj1.getProjectTeam().saveResource(manuelmartins);
            //Act
            proj1.getProjectTeam().assignProjectRole(null, LocalDate.now(), 2, company.getProjectRoleStore().getProjectRole("Scrum Master"));
        });
    }

    @Test  //Resource is not current and active
    public void assignProjectRoleTestFail() {
        //Assert
        assertThrows(NullPointerException.class, () -> {
            //Arrange
            Company company = new Company();
            Typology typo = company.getTypologyStore().getTypology("Fixed Cost");
            Customer customer = company.getCustomerStore().getCustomerByName("isep");
            BusinessSector sector = company.getBusinessSectorStore().getBusinessSectorByDescription("it");
            //Project 1
            Project proj1 = company.getProjectStore().createProject("prototype1", "proj1Prototype", customer,
                    typo, sector, LocalDate.now().minusWeeks(2), 2, 3000);
            proj1.setEndDate(LocalDate.now().plusYears(1));
            company.getProjectStore().saveNewProject(proj1);
            UserProfile profile = company.getUserProfileStore().getUserProfile("Visitor");
            //Resource 1
            SystemUser user1 = new SystemUser("manuelbras", "manuelbras@beaver.com", "tester", "ghi", "ghi", "photo", profile);
            Resource manuelbras = proj1.createResource(user1, LocalDate.now().plusWeeks(1), LocalDate.now().plusWeeks(4), 100, .5);
            proj1.getProjectTeam().saveResource(manuelbras);
            //Act and Assert
            proj1.getProjectTeam().assignProjectRole(manuelbras, LocalDate.now(), 2, company.getProjectRoleStore().getProjectRole("Scrum Master"));
        });
    }

    @Test
    public void hasCurrentResourceTestSuccess() {
        //Arrange
        Company company = new Company();
        Typology typo = company.getTypologyStore().getTypology("Fixed Cost");
        Customer customer = company.getCustomerStore().getCustomerByName("isep");
        BusinessSector sector = company.getBusinessSectorStore().getBusinessSectorByDescription("it");
        //Project 1
        Project proj1 = company.getProjectStore().createProject("prototype1", "proj1Prototype", customer,
                typo, sector, LocalDate.now().minusWeeks(2), 2, 3000);
        proj1.setEndDate(LocalDate.now().plusYears(1));
        //Project 2
        Project proj2 = company.getProjectStore().createProject("prototype2", "proj2Prototype", customer,
                typo, sector, LocalDate.of(2021, 11, 1), 2, 2000);
        proj2.setEndDate(LocalDate.of(2021, 11, 30));
        //Project 3
        Project proj3 = company.getProjectStore().createProject("prototype3", "proj3Prototype", customer,
                typo, sector, LocalDate.of(2021, 11, 1), 2, 2000);
        proj3.setEndDate(LocalDate.of(2021, 11, 30));
        //Project 4
        Project currentProject = company.getProjectStore().createProject("prototype4", "proj4Prototype", customer,
                typo, sector, LocalDate.now().minusDays(7), 2, 4000);
        currentProject.setEndDate(LocalDate.now().plusDays(7));
        //Resource 1
        UserProfile profile = company.getUserProfileStore().getUserProfile("Visitor");
        SystemUser user1 = new SystemUser("manuelbras", "manuelbras@beaver.com", "tester", "ghi", "ghi", "photo", profile);
        Resource manuelbras = new Resource(user1, LocalDate.of(2021, 11, 1), LocalDate.of(2022, 11, 15), 100, .5);
        //Resource 2
        SystemUser user2 = new SystemUser("manuelmartins", "manuelmartins@beaver.com", "tester", "ghi", "ghi", "photo", profile);
        Resource manuelmartins = new Resource(user2, LocalDate.now().minusDays(6), LocalDate.now().plusDays(7), 100, 1);
        //Resource 3
        SystemUser user3 = new SystemUser("manueljose", "manueljose@beaver.com", "tester", "ghi", "ghi", "photo", profile);
        Resource manueljose = new Resource(user3, LocalDate.of(2021, 11, 1), LocalDate.of(2021, 11, 15), 100, .5);
        //Resource 4
        SystemUser user4 = new SystemUser("manueloliveira", "manueloliveira@beaver.com", "tester", "ghi", "ghi", "photo", profile);
        Resource manueloliveira = new Resource(user4, LocalDate.now().minusWeeks(1), LocalDate.now().plusWeeks(3), 100, .3333);
        //Resource 5
        SystemUser user5 = new SystemUser("manuelfernandes", "manuelfernandes@beaver.com", "tester", "ghi", "ghi", "photo", profile);
        Resource manuelfernandes = new Resource(user5, LocalDate.of(2021, 11, 16), LocalDate.of(2021, 11, 30), 100, 1);
        //Resource 6
        SystemUser user6 = new SystemUser("manuelgoncalves", "manuelgoncalves@beaver.com", "tester", "ghi", "ghi", "photo", profile);
        Resource manuelgoncalves = new Resource(user6, LocalDate.of(2021, 11, 16), LocalDate.of(2021, 11, 30), 100, 1);
        //Add resources
        proj1.getProjectTeam().saveResource(manuelbras);
        proj1.getProjectTeam().saveResource(manueljose);
        proj1.getProjectTeam().saveResource(manueloliveira);
        proj1.getProjectTeam().saveResource(manuelfernandes);
        proj2.getProjectTeam().saveResource(manuelbras);
        proj2.getProjectTeam().saveResource(manueloliveira);
        proj2.getProjectTeam().saveResource(manuelgoncalves);
        proj3.getProjectTeam().saveResource(manueljose);
        proj3.getProjectTeam().saveResource(manueloliveira);
        currentProject.getProjectTeam().saveResource(manuelmartins);
        //Act and Assert
        assertTrue(currentProject.getProjectTeam().hasCurrentResource(("manuelmartins@beaver.com")));
    }

    @Test
    void getResource() {
        //Arrange
        Company company = new Company();
        ProjectTeam test = new ProjectTeam();
        UserProfile profile = company.getUserProfileStore().getUserProfile("Visitor");
        SystemUser user1 = new SystemUser("manuelbrasil", "manuelbrasil@beaver.com", "tester", "ghi", "ghi", "photo", profile);
        SystemUser user2 = new SystemUser("manueltest", "manueltest@beaver.com", "tester", "ghi", "ghi", "photo", profile);
        LocalDate startDateMb = LocalDate.of(2021, 11, 1);
        LocalDate endDateMb = LocalDate.of(2022, 11, 15);
        Resource manuelbrasil = new Resource(user1, startDateMb, endDateMb, 100, .5);
        Resource manueltest = new Resource(user2, LocalDate.of(2022, 2, 1), LocalDate.of(2023, 2, 1), 100, .5);
        test.saveResource(manueltest);
        test.saveResource(manuelbrasil);
        //Assert
        assertEquals(manueltest, test.getResource(user2));
        assertEquals(manuelbrasil, test.getResource(user1));
    }

    @Test
    void getCurrentResourcesNames() {
        Company company = new Company();
        ProjectTeam test = new ProjectTeam();
        UserProfile profile = company.getUserProfileStore().getUserProfile("Visitor");
        SystemUser user1 = new SystemUser("manuelbrasil", "manuelbrasil@beaver.com", "tester", "ghi", "ghi", "photo", profile);
        SystemUser user2 = new SystemUser("manueltest", "manueltest@beaver.com", "tester", "ghi", "ghi", "photo", profile);
        LocalDate startDateMb = LocalDate.of(2021, 11, 1);
        LocalDate endDateMb = LocalDate.of(2022, 11, 15);
        Resource manuelbrasil = new Resource(user1, startDateMb, endDateMb, 100, .5);
        Resource manueltest = new Resource(user2, LocalDate.of(2022, 2, 1), LocalDate.of(2023, 2, 1), 100, .5);
        test.saveResource(manueltest);
        test.saveResource(manuelbrasil);
        List<String> testList = new ArrayList<>();
        testList.add("manueltest");
        testList.add("manuelbrasil");
        //Assert
        assertEquals(testList, test.getCurrentResourcesNames());
    }

    @Test
    public void hasCurrentResourceFailResourceNotPresent() {
        //Arrange
        Company company = new Company();
        Typology typo = company.getTypologyStore().getTypology("Fixed Cost");
        Customer customer = company.getCustomerStore().getCustomerByName("isep");
        BusinessSector sector = company.getBusinessSectorStore().getBusinessSectorByDescription("it");
        Project currentProject = company.getProjectStore().createProject("prototype4", "proj4Prototype", customer,
                typo, sector, LocalDate.now().minusDays(7), 2, 4000);
        currentProject.setEndDate(LocalDate.now().plusDays(7));
        //Resource 1
        UserProfile profile = company.getUserProfileStore().getUserProfile("Visitor");
        SystemUser user1 = new SystemUser("manuelbras", "manuelbras@beaver.com", "tester", "ghi", "ghi", "photo", profile);
        Resource manuelbras = new Resource(user1, LocalDate.of(2021, 11, 1), LocalDate.of(2022, 11, 15), 100, .5);
        //Resource 2
        SystemUser user2 = new SystemUser("manuelmartins", "manuelmartins@beaver.com", "tester", "ghi", "ghi", "photo", profile);
        Resource manuelmartins = new Resource(user2, LocalDate.now().minusDays(6), LocalDate.now().plusDays(7), 100, 1);
        //Resource 3
        SystemUser user3 = new SystemUser("manueljose", "manueljose@beaver.com", "tester", "ghi", "ghi", "photo", profile);
        Resource manueljose = new Resource(user3, LocalDate.of(2021, 11, 1), LocalDate.of(2021, 11, 15), 100, .5);
        //Resource 4
        SystemUser user4 = new SystemUser("manueloliveira", "manueloliveira@beaver.com", "tester", "ghi", "ghi", "photo", profile);
        Resource manueloliveira = new Resource(user4, LocalDate.now().minusWeeks(1), LocalDate.now().plusWeeks(3), 100, .3333);
        //Resource 5
        SystemUser user5 = new SystemUser("manuelfernandes", "manuelfernandes@beaver.com", "tester", "ghi", "ghi", "photo", profile);
        Resource manuelfernandes = new Resource(user5, LocalDate.of(2021, 11, 16), LocalDate.of(2021, 11, 30), 100, 1);
        //Resource 6
        SystemUser user6 = new SystemUser("manuelgoncalves", "manuelgoncalves@beaver.com", "tester", "ghi", "ghi", "photo", profile);
        Resource manuelgoncalves = new Resource(user6, LocalDate.of(2021, 11, 16), LocalDate.of(2021, 11, 30), 100, 1);
        //Act
        currentProject.getProjectTeam().saveResource(manuelbras);
        currentProject.getProjectTeam().saveResource(manuelfernandes);
        currentProject.getProjectTeam().saveResource(manuelgoncalves);
        currentProject.getProjectTeam().saveResource(manueljose);
        currentProject.getProjectTeam().saveResource(manueloliveira);
        currentProject.getProjectTeam().saveResource(manuelmartins);
        //Assert
        assertFalse(currentProject.getProjectTeam().hasCurrentResource(("manuelsilva@beaver.com")));
    }


        @Test
    public void hasCurrentResourceFailResourceNotCurrent() {
            //Arrange
            Company company = new Company();
            Typology typo = company.getTypologyStore().getTypology("Fixed Cost");
            Customer customer = company.getCustomerStore().getCustomerByName("isep");
            BusinessSector sector = company.getBusinessSectorStore().getBusinessSectorByDescription("it");
                //Project 1
            Project proj1 = company.getProjectStore().createProject("prototype1", "proj1Prototype", customer,
                    typo, sector, LocalDate.now().minusWeeks(2), 2, 3000);
            proj1.setEndDate(LocalDate.now().plusYears(1));
            //Act
            company.getProjectStore().saveNewProject(proj1);
            //Assert
            assertFalse(proj1.getProjectTeam().hasCurrentResource(("manueloliveira@beaver.com")));
    }

    @Test
    @DisplayName("Get current resource by email")
    public void getCurrentResource() {
        //Arrange
        Company company = new Company();
        Typology typo = company.getTypologyStore().getTypology("Fixed Cost");
        Customer customer = company.getCustomerStore().getCustomerByName("isep");
        BusinessSector sector = company.getBusinessSectorStore().getBusinessSectorByDescription("it");
        //Project 1
        Project proj1 = company.getProjectStore().createProject("prototype1", "proj1Prototype", customer,
                typo, sector, LocalDate.now().minusWeeks(2), 2, 3000);
        proj1.setEndDate(LocalDate.now().plusYears(1));
        company.getProjectStore().saveNewProject(proj1);

        UserProfile profile = company.getUserProfileStore().getUserProfile("Visitor");
        SystemUser user4 = new SystemUser("manueloliveira", "manueloliveira@beaver.com", "tester", "ghi", "ghi", "photo", profile);
        LocalDate startDateMo = LocalDate.now().minusWeeks(1);
        LocalDate endDateMo = LocalDate.now().plusWeeks(3);
        Resource manueloliveira = proj1.createResource(user4, startDateMo, endDateMo, 100, .3333);
        proj1.getProjectTeam().saveResource(manueloliveira);
        //Act
        Resource teste = proj1.getProjectTeam().getResource("manueloliveira@beaver.com");
        //Assert
        assertEquals(teste, manueloliveira);
    }

    @Test
    @DisplayName("Get current resource by email")
    public void getCurrentResourceNull() {
        //Arrange
        Company company = new Company();
        Typology typo = company.getTypologyStore().getTypology("Fixed Cost");
        Customer customer = company.getCustomerStore().getCustomerByName("isep");
        BusinessSector sector = company.getBusinessSectorStore().getBusinessSectorByDescription("it");
            //Project 1
        Project proj1 = company.getProjectStore().createProject("prototype1", "proj1Prototype", customer,
                typo, sector, LocalDate.now().minusWeeks(2), 2, 3000);
        proj1.setEndDate(LocalDate.now().plusYears(1));
        company.getProjectStore().saveNewProject(proj1);
        //Assert
        assertNull(proj1.getProjectTeam().getResource("manuelbraga@beaver.com"));
    }


    @Test
    @DisplayName("Assign New Role for a resource")
    public void assignScrumMasterWithoutRepeatedTestSize() {
        //Arrange
        Company company = new Company();
        Typology typo = company.getTypologyStore().getTypology("Fixed Cost");
        Customer customer = company.getCustomerStore().getCustomerByName("isep");
        BusinessSector sector = company.getBusinessSectorStore().getBusinessSectorByDescription("it");
        //Project 1
        Project proj1 = company.getProjectStore().createProject("prototype1", "proj1Prototype", customer,
                typo, sector, LocalDate.now().minusWeeks(2), 2, 3000);
        proj1.setEndDate(LocalDate.now().plusYears(1));
        company.getProjectStore().saveNewProject(proj1);
            //Resource 1
        UserProfile profile = company.getUserProfileStore().getUserProfile("Visitor");
        SystemUser user1 = new SystemUser("manuelbras", "manuelbras@beaver.com", "tester", "ghi", "ghi", "photo", profile);
        Resource manuelbras = new Resource(user1, LocalDate.of(2021, 11, 1), LocalDate.of(2022, 11, 15), 100, .5);
            //Resource 2
        SystemUser user2 = new SystemUser("manuelmartins", "manuelmartins@beaver.com", "tester", "ghi", "ghi", "photo", profile);
        Resource manuelmartins = new Resource(user2, LocalDate.now().minusDays(6), LocalDate.now().plusDays(7), 100, 1);
            //Resource 3
        SystemUser user3 = new SystemUser("manueljose", "manueljose@beaver.com", "tester", "ghi", "ghi", "photo", profile);
        Resource manueljose = new Resource(user3, LocalDate.of(2021, 11, 1), LocalDate.of(2021, 11, 15), 100, .5);
            //Resource 4
        SystemUser user4 = new SystemUser("manueloliveira", "manueloliveira@beaver.com", "tester", "ghi", "ghi", "photo", profile);
        Resource manueloliveira = new Resource(user4, LocalDate.now().minusWeeks(1), LocalDate.now().plusWeeks(3), 100, .3333);
        manueloliveira.setRole(company.getProjectRoleStore().getProjectRole("Scrum Master"));
            //Resource 5
        SystemUser user5 = new SystemUser("manuelfernandes", "manuelfernandes@beaver.com", "tester", "ghi", "ghi", "photo", profile);
        Resource manuelfernandes = new Resource(user5, LocalDate.of(2021, 11, 16), LocalDate.of(2021, 11, 30), 100, 1);
            //Resource 6
        SystemUser user6 = new SystemUser("manuelgoncalves", "manuelgoncalves@beaver.com", "tester", "ghi", "ghi", "photo", profile);
        Resource manuelgoncalves = new Resource(user6, LocalDate.of(2021, 11, 16), LocalDate.of(2021, 11, 30), 100, 1);
        proj1.getProjectTeam().saveResource(manuelbras);
        proj1.getProjectTeam().saveResource(manuelgoncalves);
        proj1.getProjectTeam().saveResource(manuelfernandes);
        proj1.getProjectTeam().saveResource(manueljose);
        proj1.getProjectTeam().saveResource(manueloliveira);
        proj1.getProjectTeam().saveResource(manuelmartins);
        //Act
        Resource manuelTest = proj1.getProjectTeam().getResource("manuelbras@beaver.com");
        manuelTest.setRole(company.getProjectRoleStore().getProjectRole("Team Member"));
        proj1.getProjectTeam().assignProjectRole(manuelTest, LocalDate.of(2021,11,16), 2, company.getProjectRoleStore().getProjectRole("Scrum Master"));
        //Assert
        assertEquals(8, proj1.getProjectTeam().getProjectTeamList().size());
    }

    @Test
    @DisplayName("Assign New Role for a resource")
    public void assignScrumMasterWithRepeatedTestSize() {
        //Arrange
        Company company = new Company();
        Typology typo = company.getTypologyStore().getTypology("Fixed Cost");
        Customer customer = company.getCustomerStore().getCustomerByName("isep");
        BusinessSector sector = company.getBusinessSectorStore().getBusinessSectorByDescription("it");
        //Project 1
        Project proj1 = company.getProjectStore().createProject("prototype1", "proj1Prototype", customer,
                typo, sector, LocalDate.now().minusWeeks(2), 2, 3000);
        proj1.setEndDate(LocalDate.now().plusYears(1));
        company.getProjectStore().saveNewProject(proj1);
            //Resource
        UserProfile profile = company.getUserProfileStore().getUserProfile("Visitor");
        SystemUser user1 = new SystemUser("manuelbrasil", "manuelbrasil@beaver.com", "tester", "ghi", "ghi", "photo", profile);
        LocalDate startDateMb = LocalDate.of(2021, 11, 1);
        LocalDate endDateMb = LocalDate.of(2022, 11, 15);
        Resource manuelbrasil = proj1.createResource(user1, startDateMb, endDateMb, 100, .5);
        manuelbrasil.setRole(company.getProjectRoleStore().getProjectRole("Scrum Master"));
        //Act
        proj1.getProjectTeam().saveResource(manuelbrasil);
        Resource manuelTest = proj1.getProjectTeam().getResource("manuelbrasil@beaver.com");
        manuelTest.setRole(company.getProjectRoleStore().getProjectRole("Team Member"));
        proj1.getProjectTeam().assignProjectRole(manuelTest, LocalDate.of(2021,11,16), 2, company.getProjectRoleStore().getProjectRole("Scrum Master"));
        //Assert
        assertEquals(2, proj1.getProjectTeam().getProjectTeamList().size());
    }

    @Test
    @DisplayName("Test to Validate Project Team - Success To Get PO and SM")
    public void validateProjectTeamSuccess() {
        //Arrange
        Company company = new Company();
        Typology typo = company.getTypologyStore().getTypology("Fixed Cost");
        Customer customer = company.getCustomerStore().getCustomerByName("isep");
        BusinessSector sector = company.getBusinessSectorStore().getBusinessSectorByDescription("it");
            //Project 1
        Project proj1 = company.getProjectStore().createProject( "prototype1", "proj1Prototype", customer,
                typo, sector, LocalDate.of(2022, 1, 1), 2, 3000);
        proj1.setEndDate(LocalDate.of(2022, 12, 31));
        company.getProjectStore().saveNewProject(proj1);
        UserProfile profile = company.getUserProfileStore().getUserProfile("Visitor");
            //Resource 1
        SystemUser joana1 = new SystemUser("joanaum", "joana1@beaver.com", "tester", "ghi", "ghi", "photo", profile);
        LocalDate startDatej1 = LocalDate.of(2022, 1, 1);
        LocalDate endDatej1 = LocalDate.of(2022, 1, 30);
        Resource joana1R = proj1.createResource(joana1, startDatej1, endDatej1, 100, .5);
        joana1R.setRole(company.getProjectRoleStore().getProjectRole("Scrum Master"));
            //Resource 2
        SystemUser joana2 = new SystemUser("joanadois", "joana2@beaver.com", "tester", "ghi", "ghi", "photo", profile);
        LocalDate startDatej2 = LocalDate.of(2022, 1, 1);
        LocalDate endDatej2 = LocalDate.of(2022, 1, 30);
        Resource joana2R = proj1.createResource(joana2, startDatej2, endDatej2, 100, 1);
        joana2R.setRole(company.getProjectRoleStore().getProjectRole("Product Owner"));
            //Resource 3
        SystemUser joana3 = new SystemUser("joanatres", "joana3@beaver.com", "tester", "ghi", "ghi", "photo", profile);
        LocalDate startDatej3 = LocalDate.of(2022, 1, 1);
        LocalDate endDatej3 = LocalDate.of(2022, 12, 31);
        Resource joana3R= proj1.createResource(joana3, startDatej3, endDatej3, 100, .5);
        joana3R.setRole(company.getProjectRoleStore().getProjectRole("Project Manager"));
            //Resource 4
        SystemUser joana4 = new SystemUser("joanaquatro", "joana4@beaver.com", "tester", "ghi", "ghi", "photo", profile);
        LocalDate startDatej4 = LocalDate.of(2022, 1, 1);
        LocalDate endDatej4 = LocalDate.of(2022, 12, 31);
        Resource joana4R = proj1.createResource(joana4, startDatej4, endDatej4, 100, .3333);
        joana4R.setRole(company.getProjectRoleStore().getProjectRole("Team Member"));
        //Act
        proj1.getProjectTeam().saveResource(joana1R);
        proj1.getProjectTeam().saveResource(joana2R);
        proj1.getProjectTeam().saveResource(joana3R);
        proj1.getProjectTeam().saveResource(joana4R);
        //Assert
        assertTrue(joana1R.isYour(company.getProjectRoleStore().getProjectRole("Scrum Master")));
        assertTrue(joana2R.isYour(company.getProjectRoleStore().getProjectRole("Product Owner")));
        assertTrue(joana3R.isYour(company.getProjectRoleStore().getProjectRole("Project Manager")));
        assertTrue(joana4R.isYour(company.getProjectRoleStore().getProjectRole("Team Member")));
        assertTrue(proj1.getProjectTeam().validateProjectTeam(startDatej2, 2));
        assertTrue(proj1.getProjectTeam().validateProjectTeam(startDatej1, 1));
    }

    @Test
    @DisplayName("Test to Validate Project Team - The team does not have a PO and a SM")
    public void validateProjectTeamGlobalFail() {
        //Arrange
        Company company = new Company();
        Typology typo = company.getTypologyStore().getTypology("Fixed Cost");
        Customer customer = company.getCustomerStore().getCustomerByName("isep");
        BusinessSector sector = company.getBusinessSectorStore().getBusinessSectorByDescription("it");
        //Project 1
        Project proj1 = company.getProjectStore().createProject( "prototype1", "proj1Prototype", customer,
                typo, sector, LocalDate.of(2022, 1, 1), 2, 3000);
        proj1.setEndDate(LocalDate.of(2022, 12, 31));
        company.getProjectStore().saveNewProject(proj1);
        UserProfile profile = company.getUserProfileStore().getUserProfile("Visitor");
        //Resource 1
        SystemUser joana1 = new SystemUser("joanaum", "joana1@beaver.com", "tester", "ghi", "ghi", "photo", profile);
        LocalDate startDatej1 = LocalDate.of(2022, 1, 1);
        LocalDate endDatej1 = LocalDate.of(2022, 1, 30);
        Resource joana1R = proj1.createResource(joana1, startDatej1, endDatej1, 100, .5);
        joana1R.setRole(company.getProjectRoleStore().getProjectRole("Project Manager"));
        //Resource 2
        SystemUser joana2 = new SystemUser("joanadois", "joana2@beaver.com", "tester", "ghi", "ghi", "photo", profile);
        LocalDate startDatej2 = LocalDate.of(2022, 1, 1);
        LocalDate endDatej2 = LocalDate.of(2022, 1, 30);
        Resource joana2R = proj1.createResource(joana2, startDatej2, endDatej2, 100, 1);
        joana2R.setRole(company.getProjectRoleStore().getProjectRole("Team Member"));
        //Resource 3
        SystemUser joana3 = new SystemUser("joanatres", "joana3@beaver.com", "tester", "ghi", "ghi", "photo", profile);
        LocalDate startDatej3 = LocalDate.of(2022, 1, 1);
        LocalDate endDatej3 = LocalDate.of(2022, 12, 31);
        Resource joana3R= proj1.createResource(joana3, startDatej3, endDatej3, 100, .5);
        joana3R.setRole(company.getProjectRoleStore().getProjectRole("Team Member"));
        //Resource 4
        SystemUser joana4 = new SystemUser("joanaquatro", "joana4@beaver.com", "tester", "ghi", "ghi", "photo", profile);
        LocalDate startDatej4 = LocalDate.of(2022, 1, 1);
        LocalDate endDatej4 = LocalDate.of(2022, 12, 31);
        Resource joana4R = proj1.createResource(joana4, startDatej4, endDatej4, 100, .3333);
        joana4R.setRole(company.getProjectRoleStore().getProjectRole("Team Member"));
        //Act
        proj1.getProjectTeam().saveResource(joana1R);
        proj1.getProjectTeam().saveResource(joana2R);
        proj1.getProjectTeam().saveResource(joana3R);
        proj1.getProjectTeam().saveResource(joana4R);
        //Assert
        assertFalse(proj1.getProjectTeam().validateProjectTeam(startDatej1, 2));
        assertFalse(proj1.getProjectTeam().validateProjectTeam(startDatej2, 2));
        assertFalse(proj1.getProjectTeam().validateProjectTeam(startDatej3, 2));
        assertFalse(proj1.getProjectTeam().validateProjectTeam(startDatej4, 2));
    }

    @Test
    @DisplayName("Test to Validate Project Team - The Team does not have a PO but has a Scrum Master")
    public void validateProjectTeamPOFail() {
        //Arrange
        Company company = new Company();
        Typology typo = company.getTypologyStore().getTypology("Fixed Cost");
        Customer customer = company.getCustomerStore().getCustomerByName("isep");
        BusinessSector sector = company.getBusinessSectorStore().getBusinessSectorByDescription("it");
        //Project 1
        Project proj1 = company.getProjectStore().createProject( "prototype1", "proj1Prototype", customer,
                typo, sector, LocalDate.of(2022, 1, 1), 2, 3000);
        proj1.setEndDate(LocalDate.of(2022, 12, 31));
        company.getProjectStore().saveNewProject(proj1);
        UserProfile profile = company.getUserProfileStore().getUserProfile("Visitor");
        //Resource 1
        SystemUser joana1 = new SystemUser("joanaum", "joana1@beaver.com", "tester", "ghi", "ghi", "photo", profile);
        LocalDate startDatej1 = LocalDate.of(2022, 1, 1);
        LocalDate endDatej1 = LocalDate.of(2022, 1, 30);
        Resource joana1R = proj1.createResource(joana1, startDatej1, endDatej1, 100, .5);
        joana1R.setRole(company.getProjectRoleStore().getProjectRole("Scrum Master"));
        //Resource 2
        SystemUser joana2 = new SystemUser("joanadois", "joana2@beaver.com", "tester", "ghi", "ghi", "photo", profile);
        LocalDate startDatej2 = LocalDate.of(2022, 1, 1);
        LocalDate endDatej2 = LocalDate.of(2022, 1, 30);
        Resource joana2R = proj1.createResource(joana2, startDatej2, endDatej2, 100, 1);
        joana2R.setRole(company.getProjectRoleStore().getProjectRole("Team Member"));
        //Resource 3
        SystemUser joana3 = new SystemUser("joanatres", "joana3@beaver.com", "tester", "ghi", "ghi", "photo", profile);
        LocalDate startDatej3 = LocalDate.of(2022, 1, 1);
        LocalDate endDatej3 = LocalDate.of(2022, 1, 31);
        Resource joana3R= proj1.createResource(joana3, startDatej3, endDatej3, 100, .5);
        joana3R.setRole(company.getProjectRoleStore().getProjectRole("Project Manager"));
        //Resource 4
        SystemUser joana4 = new SystemUser("joanaquatro", "joana4@beaver.com", "tester", "ghi", "ghi", "photo", profile);
        LocalDate startDatej4 = LocalDate.of(2022, 1, 1);
        LocalDate endDatej4 = LocalDate.of(2022, 1, 31);
        Resource joana4R = proj1.createResource(joana4, startDatej4, endDatej4, 100, .3333);
        joana4R.setRole(company.getProjectRoleStore().getProjectRole("Team Member"));
        //Act
        proj1.getProjectTeam().saveResource(joana1R);
        proj1.getProjectTeam().saveResource(joana2R);
        proj1.getProjectTeam().saveResource(joana3R);
        proj1.getProjectTeam().saveResource(joana4R);
        //Assert
        assertFalse(proj1.getProjectTeam().validateProjectTeam(startDatej1, 2));
        assertFalse(proj1.getProjectTeam().validateProjectTeam(startDatej2, 2));
        assertFalse(proj1.getProjectTeam().validateProjectTeam(startDatej3, 2));
        assertFalse(proj1.getProjectTeam().validateProjectTeam(startDatej4, 2));
    }

    @Test
    @DisplayName("Test to Validate Project Team - The Team does not have a SM but has a Product Owner")
    public void validateProjectTeamSMFail() {
        //Arrange
        Company company = new Company();
        Typology typo = company.getTypologyStore().getTypology("Fixed Cost");
        Customer customer = company.getCustomerStore().getCustomerByName("isep");
        BusinessSector sector = company.getBusinessSectorStore().getBusinessSectorByDescription("it");
        //Project 1
        Project proj1 = company.getProjectStore().createProject( "prototype1", "proj1Prototype", customer,
                typo, sector, LocalDate.of(2022, 1, 1), 2, 3000);
        proj1.setEndDate(LocalDate.of(2022, 12, 31));
        company.getProjectStore().saveNewProject(proj1);
        UserProfile profile = company.getUserProfileStore().getUserProfile("Visitor");
        //Resource 1
        SystemUser joana1 = new SystemUser("joanaum", "joana1@beaver.com", "tester", "ghi", "ghi", "photo", profile);
        LocalDate startDatej1 = LocalDate.of(2022, 1, 1);
        LocalDate endDatej1 = LocalDate.of(2022, 1, 30);
        Resource joana1R = proj1.createResource(joana1, startDatej1, endDatej1, 100, .5);
        joana1R.setRole(company.getProjectRoleStore().getProjectRole("Product Owner"));
        //Resource 2
        SystemUser joana2 = new SystemUser("joanadois", "joana2@beaver.com", "tester", "ghi", "ghi", "photo", profile);
        LocalDate startDatej2 = LocalDate.of(2022, 1, 1);
        LocalDate endDatej2 = LocalDate.of(2022, 1, 30);
        Resource joana2R = proj1.createResource(joana2, startDatej2, endDatej2, 100, 1);
        joana2R.setRole(company.getProjectRoleStore().getProjectRole("Team Member"));
        //Resource 3
        SystemUser joana3 = new SystemUser("joanatres", "joana3@beaver.com", "tester", "ghi", "ghi", "photo", profile);
        LocalDate startDatej3 = LocalDate.of(2022, 1, 1);
        LocalDate endDatej3 = LocalDate.of(2022, 1, 31);
        Resource joana3R= proj1.createResource(joana3, startDatej3, endDatej3, 100, .5);
        joana3R.setRole(company.getProjectRoleStore().getProjectRole("Project Manager"));
        //Resource 4
        SystemUser joana4 = new SystemUser("joanaquatro", "joana4@beaver.com", "tester", "ghi", "ghi", "photo", profile);
        LocalDate startDatej4 = LocalDate.of(2022, 1, 1);
        LocalDate endDatej4 = LocalDate.of(2022, 1, 31);
        Resource joana4R = proj1.createResource(joana4, startDatej4, endDatej4, 100, .3333);
        joana4R.setRole(company.getProjectRoleStore().getProjectRole("Team Member"));
        //Act
        proj1.getProjectTeam().saveResource(joana1R);
        proj1.getProjectTeam().saveResource(joana2R);
        proj1.getProjectTeam().saveResource(joana3R);
        proj1.getProjectTeam().saveResource(joana4R);
        //Assert
        assertFalse(proj1.getProjectTeam().validateProjectTeam(startDatej1, 2));
        assertFalse(proj1.getProjectTeam().validateProjectTeam(startDatej2, 2));
        assertFalse(proj1.getProjectTeam().validateProjectTeam(startDatej3, 2));
        assertFalse(proj1.getProjectTeam().validateProjectTeam(startDatej4, 2));
    }
    @Test
    @DisplayName("Check if role exist and is current - null and team Member")
    public void checkIfTheRoleExistAndIsCurrentFalseNullandTeamMember() {
        //Arrange
        Company company = new Company();
        Typology typo = company.getTypologyStore().getTypology("Fixed Cost");
        Customer customer = company.getCustomerStore().getCustomerByName("isep");
        BusinessSector sector = company.getBusinessSectorStore().getBusinessSectorByDescription("it");
        //Project 1
        Project proj1 = company.getProjectStore().createProject( "prototype1", "proj1Prototype", customer,
                typo, sector, LocalDate.of(2022, 1, 1), 2, 3000);
        proj1.setEndDate(LocalDate.of(2022, 12, 31));
        company.getProjectStore().saveNewProject(proj1);
        UserProfile profile = company.getUserProfileStore().getUserProfile("Visitor");
        //Resource 1
        SystemUser joana1 = new SystemUser("joanaum", "joana1@beaver.com", "tester", "ghi", "ghi", "photo", profile);
        LocalDate startDatej1 = LocalDate.of(2022, 1, 1);
        LocalDate endDatej1 = LocalDate.of(2022, 1, 30);
        Resource joana1R = proj1.createResource(joana1, startDatej1, endDatej1, 100, .5);
        joana1R.setRole(company.getProjectRoleStore().getProjectRole("Product Owner"));
        //Resource 2
        SystemUser joana2 = new SystemUser("joanadois", "joana2@beaver.com", "tester", "ghi", "ghi", "photo", profile);
        LocalDate startDatej2 = LocalDate.of(2022, 1, 1);
        LocalDate endDatej2 = LocalDate.of(2022, 1, 30);
        Resource joana2R = proj1.createResource(joana2, startDatej2, endDatej2, 100, 1);
        joana2R.setRole(company.getProjectRoleStore().getProjectRole("Team Member"));
        //Resource 3
        SystemUser joana3 = new SystemUser("joanatres", "joana3@beaver.com", "tester", "ghi", "ghi", "photo", profile);
        LocalDate startDatej3 = LocalDate.of(2022, 1, 1);
        LocalDate endDatej3 = LocalDate.of(2022, 1, 31);
        Resource joana3R= proj1.createResource(joana3, startDatej3, endDatej3, 100, .5);
        joana3R.setRole(company.getProjectRoleStore().getProjectRole("Project Manager"));
        //Resource 4
        SystemUser joana4 = new SystemUser("joanaquatro", "joana4@beaver.com", "tester", "ghi", "ghi", "photo", profile);
        LocalDate startDatej4 = LocalDate.of(2022, 1, 1);
        LocalDate endDatej4 = LocalDate.of(2022, 1, 31);
        Resource joana4R = proj1.createResource(joana4, startDatej4, endDatej4, 100, .3333);
        joana4R.setRole(company.getProjectRoleStore().getProjectRole("Team Member"));

        ProjectRole joana4role = joana4R.getRole();

        //Resource 5
        SystemUser joana5 = new SystemUser("joanacinco", "joana5@beaver.com", "tester", "ghi", "ghi", "photo", profile);
        LocalDate startDatej5 = LocalDate.of(2022, 1, 1);
        LocalDate endDatej5 = LocalDate.of(2022, 1, 31);
        Resource joana5R = proj1.createResource(joana4, startDatej4, endDatej4, 100, .3333);
//        joana4R.setRole(company.getProjectRoleStore().getProjectRole("Team Member"));

        //Act
        proj1.getProjectTeam().saveResource(joana1R);
        proj1.getProjectTeam().saveResource(joana2R);
        proj1.getProjectTeam().saveResource(joana3R);
        proj1.getProjectTeam().saveResource(joana4R);
        proj1.getProjectTeam().saveResource(joana5R);
        //Assert
        assertFalse(proj1.getProjectTeam().checkIfTheRoleExistAndIsCurrent(joana4role, startDatej4));
        assertFalse(proj1.getProjectTeam().checkIfTheRoleExistAndIsCurrent(null, startDatej5));
    }

    @Test
    @DisplayName("Check if role exist and is current - Product Owner")
    public void checkIfTheRoleExistAndIsCurrentTrueProductOwner() {
        //Arrange
        Company company = new Company();
        Typology typo = company.getTypologyStore().getTypology("Fixed Cost");
        Customer customer = company.getCustomerStore().getCustomerByName("isep");
        BusinessSector sector = company.getBusinessSectorStore().getBusinessSectorByDescription("it");
        //Project 1
        Project proj1 = company.getProjectStore().createProject( "prototype1", "proj1Prototype", customer,
                typo, sector, LocalDate.of(2022, 1, 1), 2, 3000);
        proj1.setEndDate(LocalDate.of(2022, 12, 31));
        company.getProjectStore().saveNewProject(proj1);
        UserProfile profile = company.getUserProfileStore().getUserProfile("Visitor");
        //Resource 1
        SystemUser joana1 = new SystemUser("joanaum", "joana1@beaver.com", "tester", "ghi", "ghi", "photo", profile);
        LocalDate startDatej1 = LocalDate.of(2022, 1, 1);
        LocalDate endDatej1 = LocalDate.of(2022, 1, 30);
        Resource joana1R = proj1.createResource(joana1, startDatej1, endDatej1, 100, .5);
        joana1R.setRole(company.getProjectRoleStore().getProjectRole("Product Owner"));

        ProjectRole joana1role = joana1R.getRole();

        //Resource 2
        SystemUser joana2 = new SystemUser("joanadois", "joana2@beaver.com", "tester", "ghi", "ghi", "photo", profile);
        LocalDate startDatej2 = LocalDate.of(2022, 1, 1);
        LocalDate endDatej2 = LocalDate.of(2022, 1, 30);
        Resource joana2R = proj1.createResource(joana2, startDatej2, endDatej2, 100, 1);
        joana2R.setRole(company.getProjectRoleStore().getProjectRole("Team Member"));
        //Resource 3
        SystemUser joana3 = new SystemUser("joanatres", "joana3@beaver.com", "tester", "ghi", "ghi", "photo", profile);
        LocalDate startDatej3 = LocalDate.of(2022, 1, 1);
        LocalDate endDatej3 = LocalDate.of(2022, 1, 31);
        Resource joana3R= proj1.createResource(joana3, startDatej3, endDatej3, 100, .5);
        joana3R.setRole(company.getProjectRoleStore().getProjectRole("Project Manager"));

        //Act
        proj1.getProjectTeam().saveResource(joana1R);
        proj1.getProjectTeam().saveResource(joana2R);
        proj1.getProjectTeam().saveResource(joana3R);

        //Assert
        assertTrue(proj1.getProjectTeam().checkIfTheRoleExistAndIsCurrent(joana1role, startDatej1));
    }

    @Test
    @DisplayName("Check if role exist and is current - Product Owner - startDate before - fail")
    public void checkIfTheRoleExistAndIsCurrentTrueProductOwnerFail() {
        //Arrange
        Company company = new Company();
        Typology typo = company.getTypologyStore().getTypology("Fixed Cost");
        Customer customer = company.getCustomerStore().getCustomerByName("isep");
        BusinessSector sector = company.getBusinessSectorStore().getBusinessSectorByDescription("it");
        //Project 1
        Project proj1 = company.getProjectStore().createProject( "prototype1", "proj1Prototype", customer,
                typo, sector, LocalDate.of(2022, 1, 1), 2, 3000);
        proj1.setEndDate(LocalDate.of(2022, 12, 31));
        company.getProjectStore().saveNewProject(proj1);
        UserProfile profile = company.getUserProfileStore().getUserProfile("Visitor");
        //Resource 1
        SystemUser joana1 = new SystemUser("joanaum", "joana1@beaver.com", "tester", "ghi", "ghi", "photo", profile);
        LocalDate startDatej1 = LocalDate.of(2022, 1, 1);
        LocalDate endDatej1 = LocalDate.of(2022, 1, 30);
        Resource joana1R = proj1.createResource(joana1, startDatej1, endDatej1, 100, .5);
        joana1R.setRole(company.getProjectRoleStore().getProjectRole("Product Owner"));

        ProjectRole joana1role = joana1R.getRole();

        //Resource 2
        SystemUser joana2 = new SystemUser("joanadois", "joana2@beaver.com", "tester", "ghi", "ghi", "photo", profile);
        LocalDate startDatej2 = LocalDate.of(2022, 1, 1);
        LocalDate endDatej2 = LocalDate.of(2022, 1, 30);
        Resource joana2R = proj1.createResource(joana2, startDatej2, endDatej2, 100, 1);
        joana2R.setRole(company.getProjectRoleStore().getProjectRole("Team Member"));
        //Resource 3
        SystemUser joana3 = new SystemUser("joanatres", "joana3@beaver.com", "tester", "ghi", "ghi", "photo", profile);
        LocalDate startDatej3 = LocalDate.of(2022, 1, 1);
        LocalDate endDatej3 = LocalDate.of(2022, 1, 31);
        Resource joana3R= proj1.createResource(joana3, startDatej3, endDatej3, 100, .5);
        joana3R.setRole(company.getProjectRoleStore().getProjectRole("Project Manager"));


        //Act
        proj1.getProjectTeam().saveResource(joana1R);
        proj1.getProjectTeam().saveResource(joana2R);
        proj1.getProjectTeam().saveResource(joana3R);

        //Assert
        assertFalse(proj1.getProjectTeam().checkIfTheRoleExistAndIsCurrent(joana1role, endDatej1));
    }
    }

