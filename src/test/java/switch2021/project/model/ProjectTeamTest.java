package switch2021.project.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switch2021.project.Immutables.Description;

import java.time.LocalDate;
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
        when(manuelbras.isYourEmail("manuelbras@beaver.com")).thenReturn(true);
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
        when(manuelbras.isYourEmail("manuelbras@beaver.com")).thenReturn(false);
        when(manuelbras.isCurrent()).thenReturn(false);

        projectTeam.saveResource(manuelbras);

        //Act
        Resource testRes = projectTeam.getResource("manuelbras@beaver.com");

        //Assert
        assertNull(testRes);
    }

    @Test
    public void getResourceByUserTestSuccess() {
        //Arrange
        ProjectTeam projectTeam = new ProjectTeam();
        SystemUser user = mock(SystemUser.class);
        Resource manuelbras = mock(Resource.class);
        when(manuelbras.isYourEmail(user)).thenReturn(true);
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
        when(manuelbras.isYourEmail(user)).thenReturn(false);
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
        when(manuelbras.isYourEmail(role)).thenReturn(true);
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
        when(manuelbras.isYourEmail(role)).thenReturn(false);
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
        ProjectTeam projectTeam = new ProjectTeam();

        SystemUser user = mock(SystemUser.class);
        when(user.getUserName()).thenReturn("chico");

        Resource chico = mock(Resource.class);
        when(chico.getUser()).thenReturn(user);
        when(chico.isCurrent()).thenReturn(true);

        projectTeam.saveResource(chico);

        SystemUser user2 = mock(SystemUser.class);
        when(user2.getUserName()).thenReturn("jorgebras");

        Resource jorgebras = mock(Resource.class);
        when(jorgebras.getUser()).thenReturn(user2);
        when(jorgebras.isCurrent()).thenReturn(true);

        projectTeam.saveResource(jorgebras);

        //Act
        List<String> testRes = projectTeam.getCurrentResourcesNames();

        //Assert
        assertEquals("chico", testRes.get(0));
        assertEquals("jorgebras", testRes.get(1));
        assertEquals(2, testRes.size());
    }

    @Test
    public void getCurrentResourcesNamesTestFail() {
        //Arrange
        ProjectTeam projectTeam = new ProjectTeam();

        SystemUser user = mock(SystemUser.class);
        when(user.getUserName()).thenReturn("chico");

        Resource chico = mock(Resource.class);
        when(chico.getUser()).thenReturn(user);
        when(chico.isCurrent()).thenReturn(false);

        projectTeam.saveResource(chico);

        SystemUser user2 = mock(SystemUser.class);
        when(user2.getUserName()).thenReturn("jorgebras");

        Resource jorgebras = mock(Resource.class);
        when(jorgebras.getUser()).thenReturn(user2);
        when(jorgebras.isCurrent()).thenReturn(false);

        projectTeam.saveResource(jorgebras);

        //Act
        List<String> testRes = projectTeam.getCurrentResourcesNames();

        //Assert
        assertTrue(testRes.isEmpty());
    }

    /**
     * Create a new Resource Test
     */
    @Test  //TODO Vai passar a factory
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
        SystemUser user1 = new SystemUser("manuelbras", "manuelbras@beaver.com",
                "tester", "ghi", "ghi", "photo", profile);
        Resource manuelbras = new Resource(user1, LocalDate.now(), LocalDate.now().plusWeeks(4), 100, .5);
        //Act
        Resource test = proj1.getProjectTeam().createResource(user1, LocalDate.now(), LocalDate.now().plusWeeks(4), 100, .5);
        //Assert
        assertEquals(test, manuelbras);
    }

    @Test  //TODO Vai passar a factory
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

    @Test
    public void saveResourceTestSuccess() {
        //Arrange
        ProjectTeam projectTeam = new ProjectTeam();

        Resource chico = mock(Resource.class);

        //Assert
        assertTrue(projectTeam.saveResource(chico));
    }

    @Test
    public void saveResourceTestFailResourceNull() {
        assertThrows(NullPointerException.class, () -> {
            //Arrange
            ProjectTeam projectTeam = new ProjectTeam();

            Resource chico = null;

            //Assert
            projectTeam.saveResource(chico);
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
    public void hasResourceTestSuccess() {
        //Arrange
        ProjectTeam projectTeam = new ProjectTeam();
        Resource chico = mock(Resource.class);
        when(chico.isYourEmail("chico@beaver.com")).thenReturn(true);

        //Act
        projectTeam.saveResource(chico);

        //Assert
        assertTrue(projectTeam.hasResource(("chico@beaver.com")));
    }

    @Test
    public void hasResourceTestFail() {
        //Arrange
        ProjectTeam projectTeam = new ProjectTeam();
        Resource chico = mock(Resource.class);
        when(chico.isYourEmail("chico@beaver.com")).thenReturn(false);

        //Act
        projectTeam.saveResource(chico);

        //Assert
        assertFalse(projectTeam.hasResource(("chico@beaver.com")));
    }

    @Test
    public void hasCurrentResourceTestSuccess() {
        //Arrange
        ProjectTeam projectTeam = new ProjectTeam();
        Resource chico = mock(Resource.class);
        when(chico.isYourEmail("chico@beaver.com")).thenReturn(true);
        when(chico.isCurrent()).thenReturn(true);

        //Act
        projectTeam.saveResource(chico);

        //Assert
        assertTrue(projectTeam.hasCurrentResource(("chico@beaver.com")));
    }

    @Test
    public void hasCurrentResourceTestFail() {
        //Arrange
        ProjectTeam projectTeam = new ProjectTeam();
        Resource chico = mock(Resource.class);
        when(chico.isYourEmail("chico@beaver.com")).thenReturn(false);
        when(chico.isCurrent()).thenReturn(false);

        //Act
        projectTeam.saveResource(chico);

        //Assert
        assertFalse(projectTeam.hasCurrentResource(("chico@beaver.com")));
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
        proj1.getProjectTeam().assignProjectRole(manuelTest, LocalDate.of(2021, 11, 16), 2, company.getProjectRoleStore().getProjectRole("Scrum Master"));
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
        proj1.getProjectTeam().assignProjectRole(manuelTest, LocalDate.of(2021, 11, 16), 2, company.getProjectRoleStore().getProjectRole("Scrum Master"));
        //Assert
        assertEquals(2, proj1.getProjectTeam().getProjectTeamList().size());
    }

    @Test
    @DisplayName("Test to Validate Project Team - Success To Get PO and SM")
    public void validateProjectTeamSuccess() {
        //Arrange
        ProjectTeam projectTeam = new ProjectTeam();
        LocalDate startDateMb = LocalDate.of(2021, 11, 1);

        Resource po = mock(Resource.class);
        Resource sm = mock(Resource.class);

        when(po.isYourRole("Product Owner")).thenReturn(true);
        when(po.isAvailableToSprint(startDateMb,2)).thenReturn(true);

        when(sm.isYourRole("Scrum Master")).thenReturn(true);
        when(sm.isAvailableToSprint(startDateMb,2)).thenReturn(true);

        projectTeam.saveResource(po);
        projectTeam.saveResource(sm);

        assertTrue(projectTeam.validateProjectTeam(startDateMb,2));
    }

    @Test
    @DisplayName("Test to Validate Project Team - The team does not have a PO and a SM")
    public void validateProjectTeamGlobalFail() {
        //Arrange
        ProjectTeam projectTeam = new ProjectTeam();
        LocalDate startDateMb = LocalDate.of(2021, 11, 1);

        Resource po = mock(Resource.class);
        Resource sm = mock(Resource.class);

        when(po.isYourRole("Product Owner")).thenReturn(false);
        when(po.isAvailableToSprint(startDateMb,2)).thenReturn(false);

        when(sm.isYourRole("Scrum Master")).thenReturn(false);
        when(sm.isAvailableToSprint(startDateMb,2)).thenReturn(false);

        projectTeam.saveResource(po);
        projectTeam.saveResource(sm);

        assertFalse(projectTeam.validateProjectTeam(startDateMb,2));
    }

    @Test
    @DisplayName("Test to Validate Project Team - The Team does not have a PO but has a Scrum Master")
    public void validateProjectTeamPOFail() {
        //Arrange
        ProjectTeam projectTeam = new ProjectTeam();
        LocalDate startDateMb = LocalDate.of(2021, 11, 1);

        Resource po = mock(Resource.class);
        Resource sm = mock(Resource.class);

        when(po.isYourRole("Product Owner")).thenReturn(false);
        when(po.isAvailableToSprint(startDateMb,2)).thenReturn(false);

        when(sm.isYourRole("Scrum Master")).thenReturn(true);
        when(sm.isAvailableToSprint(startDateMb,2)).thenReturn(true);

        projectTeam.saveResource(po);
        projectTeam.saveResource(sm);

        assertFalse(projectTeam.validateProjectTeam(startDateMb,2));
    }


    @Test
    @DisplayName("Test to Validate Project Team - The Team does not have a SM but has a Product Owner")
    public void validateProjectTeamSMFail() {
        //Arrange
        ProjectTeam projectTeam = new ProjectTeam();
        LocalDate startDateMb = LocalDate.of(2021, 11, 1);

        Resource po = mock(Resource.class);
        Resource sm = mock(Resource.class);

        when(po.isYourRole("Product Owner")).thenReturn(true);
        when(po.isAvailableToSprint(startDateMb,2)).thenReturn(true);

        when(sm.isYourRole("Scrum Master")).thenReturn(false);
        when(sm.isAvailableToSprint(startDateMb,2)).thenReturn(false);

        projectTeam.saveResource(po);
        projectTeam.saveResource(sm);

        assertFalse(projectTeam.validateProjectTeam(startDateMb,2));
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
        Project proj1 = company.getProjectStore().createProject("prototype1", "proj1Prototype", customer,
                typo, sector, LocalDate.of(2022, 1, 1), 2, 3000);
        proj1.setEndDate(LocalDate.of(2022, 12, 31));
        company.getProjectStore().saveNewProject(proj1);
        UserProfile profile = company.getUserProfileStore().getUserProfile("Visitor");
        //Resource 1
        SystemUser joana1 = new SystemUser("joana1", "joana1@beaver.com", "tester", "ghi", "ghi", "photo", profile);
        LocalDate startDatej1 = LocalDate.of(2022, 1, 1);
        LocalDate endDatej1 = LocalDate.of(2022, 1, 30);
        Resource joana1R = proj1.createResource(joana1, startDatej1, endDatej1, 100, .5);
        joana1R.setRole(company.getProjectRoleStore().getProjectRole("Product Owner"));
        //Resource 2
        SystemUser joana2 = new SystemUser("joana2", "joana2@beaver.com", "tester", "ghi", "ghi", "photo", profile);
        LocalDate startDatej2 = LocalDate.of(2022, 1, 1);
        LocalDate endDatej2 = LocalDate.of(2022, 1, 30);
        Resource joana2R = proj1.createResource(joana2, startDatej2, endDatej2, 100, 1);
        joana2R.setRole(company.getProjectRoleStore().getProjectRole("Team Member"));
        //Resource 3
        SystemUser joana3 = new SystemUser("joana3", "joana3@beaver.com", "tester", "ghi", "ghi", "photo", profile);
        LocalDate startDatej3 = LocalDate.of(2022, 1, 1);
        LocalDate endDatej3 = LocalDate.of(2022, 1, 31);
        Resource joana3R = proj1.createResource(joana3, startDatej3, endDatej3, 100, .5);
        joana3R.setRole(company.getProjectRoleStore().getProjectRole("Project Manager"));
        //Resource 4
        SystemUser joana4 = new SystemUser("joana4", "joana4@beaver.com", "tester", "ghi", "ghi", "photo", profile);
        LocalDate startDatej4 = LocalDate.of(2022, 1, 1);
        LocalDate endDatej4 = LocalDate.of(2022, 1, 31);
        Resource joana4R = proj1.createResource(joana4, startDatej4, endDatej4, 100, .3333);
        joana4R.setRole(company.getProjectRoleStore().getProjectRole("Team Member"));

        ProjectRole joana4role = joana4R.getRole();

        //Resource 5
        SystemUser joana5 = new SystemUser("joana5", "joana5@beaver.com", "tester", "ghi", "ghi", "photo", profile);
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
        Project proj1 = company.getProjectStore().createProject("prototype1", "proj1Prototype", customer,
                typo, sector, LocalDate.of(2022, 1, 1), 2, 3000);
        proj1.setEndDate(LocalDate.of(2022, 12, 31));
        company.getProjectStore().saveNewProject(proj1);
        UserProfile profile = company.getUserProfileStore().getUserProfile("Visitor");
        //Resource 1
        SystemUser joana1 = new SystemUser("joana1", "joana1@beaver.com", "tester", "ghi", "ghi", "photo", profile);
        LocalDate startDatej1 = LocalDate.of(2022, 1, 1);
        LocalDate endDatej1 = LocalDate.of(2022, 1, 30);
        Resource joana1R = proj1.createResource(joana1, startDatej1, endDatej1, 100, .5);
        joana1R.setRole(company.getProjectRoleStore().getProjectRole("Product Owner"));

        ProjectRole joana1role = joana1R.getRole();

        //Resource 2
        SystemUser joana2 = new SystemUser("joana2", "joana2@beaver.com", "tester", "ghi", "ghi", "photo", profile);
        LocalDate startDatej2 = LocalDate.of(2022, 1, 1);
        LocalDate endDatej2 = LocalDate.of(2022, 1, 30);
        Resource joana2R = proj1.createResource(joana2, startDatej2, endDatej2, 100, 1);
        joana2R.setRole(company.getProjectRoleStore().getProjectRole("Team Member"));
        //Resource 3
        SystemUser joana3 = new SystemUser("joana3", "joana3@beaver.com", "tester", "ghi", "ghi", "photo", profile);
        LocalDate startDatej3 = LocalDate.of(2022, 1, 1);
        LocalDate endDatej3 = LocalDate.of(2022, 1, 31);
        Resource joana3R = proj1.createResource(joana3, startDatej3, endDatej3, 100, .5);
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
        Project proj1 = company.getProjectStore().createProject("prototype1", "proj1Prototype", customer,
                typo, sector, LocalDate.of(2022, 1, 1), 2, 3000);
        proj1.setEndDate(LocalDate.of(2022, 12, 31));
        company.getProjectStore().saveNewProject(proj1);
        UserProfile profile = company.getUserProfileStore().getUserProfile("Visitor");
        //Resource 1
        SystemUser joana1 = new SystemUser("joana1", "joana1@beaver.com", "tester", "ghi", "ghi", "photo", profile);
        LocalDate startDatej1 = LocalDate.of(2022, 1, 1);
        LocalDate endDatej1 = LocalDate.of(2022, 1, 30);
        Resource joana1R = proj1.createResource(joana1, startDatej1, endDatej1, 100, .5);
        joana1R.setRole(company.getProjectRoleStore().getProjectRole("Product Owner"));

        ProjectRole joana1role = joana1R.getRole();

        //Resource 2
        SystemUser joana2 = new SystemUser("joana2", "joana2@beaver.com", "tester", "ghi", "ghi", "photo", profile);
        LocalDate startDatej2 = LocalDate.of(2022, 1, 1);
        LocalDate endDatej2 = LocalDate.of(2022, 1, 30);
        Resource joana2R = proj1.createResource(joana2, startDatej2, endDatej2, 100, 1);
        joana2R.setRole(company.getProjectRoleStore().getProjectRole("Team Member"));
        //Resource 3
        SystemUser joana3 = new SystemUser("joana3", "joana3@beaver.com", "tester", "ghi", "ghi", "photo", profile);
        LocalDate startDatej3 = LocalDate.of(2022, 1, 1);
        LocalDate endDatej3 = LocalDate.of(2022, 1, 31);
        Resource joana3R = proj1.createResource(joana3, startDatej3, endDatej3, 100, .5);
        joana3R.setRole(company.getProjectRoleStore().getProjectRole("Project Manager"));


        //Act
        proj1.getProjectTeam().saveResource(joana1R);
        proj1.getProjectTeam().saveResource(joana2R);
        proj1.getProjectTeam().saveResource(joana3R);

        //Assert
        assertFalse(proj1.getProjectTeam().checkIfTheRoleExistAndIsCurrent(joana1role, endDatej1));
    }
}

