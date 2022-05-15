package switch2021.project.model.Project;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switch2021.project.factory.IResourceFactory;
import switch2021.project.model.Company;
import switch2021.project.model.Resource.old.Resource;
import switch2021.project.model.Typology.Typology;
import switch2021.project.repositories.ProjectTeam;
import switch2021.project.model.valueObject.*;
import switch2021.project.model.SystemUser.SystemUser;

import java.time.LocalDate;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ProjectTeamTest {

    /**
     * Constructor Test
     */
    @Test
    public void projectTeamTest() {
        //Arrange
        IResourceFactory resFac = mock(IResourceFactory.class);
        ProjectTeam test = new ProjectTeam(resFac);
        //Assert
        assertEquals(new ProjectTeam(resFac), test);
    }

    @Test
    public void projectTeamListTest() {
        //Arrange
        IResourceFactory resFac = mock(IResourceFactory.class);
        ProjectTeam test = new ProjectTeam(resFac);
        //Assert
        assertEquals(new ProjectTeam(resFac).getProjectTeamList(), test.getProjectTeamList());
    }

    @Test
    @DisplayName("Test with mock if the resource is returned")
    public void getResourceByEmailTestSuccess() {
        //Arrange
        IResourceFactory resFac = mock(IResourceFactory.class);
        ProjectTeam projectTeam = new ProjectTeam(resFac);

        Resource manuelbras = mock(Resource.class);
        when(manuelbras.isYourEmail("manuelbras@beaver.com")).thenReturn(true);
        when(manuelbras.isCurrent()).thenReturn(true);

        projectTeam.saveResource(manuelbras);

        //Act
        Resource testRes = projectTeam.getResourceByEmail("manuelbras@beaver.com");

        //Assert
        assertEquals(manuelbras, testRes);
    }

    @Test
    public void getResourceByEmailTestFail() {
        //Arrange
        IResourceFactory resFac = mock(IResourceFactory.class);
        ProjectTeam projectTeam = new ProjectTeam(resFac);

        Resource manuelbras = mock(Resource.class);
        when(manuelbras.isYourEmail("manuelbras@beaver.com")).thenReturn(false);
        when(manuelbras.isCurrent()).thenReturn(false);

        projectTeam.saveResource(manuelbras);

        //Act
        Resource testRes = projectTeam.getResourceByEmail("manuelbras@beaver.com");

        //Assert
        assertNull(testRes);
    }

    @Test
    public void getResourceByEmailTestIsYourEmailFail() {
        //Arrange
        IResourceFactory resFac = mock(IResourceFactory.class);
        ProjectTeam projectTeam = new ProjectTeam(resFac);

        Resource manuelbras = mock(Resource.class);
        when(manuelbras.isYourEmail("manuelbras@beaver.com")).thenReturn(false);
        when(manuelbras.isCurrent()).thenReturn(true);

        projectTeam.saveResource(manuelbras);

        //Act
        Resource testRes = projectTeam.getResourceByEmail("manuelbras@beaver.com");

        //Assert
        assertNull(testRes);
    }

    @Test
    public void getResourceByEmailIsCurrentFail() {
        //Arrange
        IResourceFactory resFac = mock(IResourceFactory.class);
        ProjectTeam projectTeam = new ProjectTeam(resFac);

        Resource manuelbras = mock(Resource.class);
        when(manuelbras.isYourEmail("manuelbras@beaver.com")).thenReturn(true);
        when(manuelbras.isCurrent()).thenReturn(false);

        projectTeam.saveResource(manuelbras);

        //Act
        Resource testRes = projectTeam.getResourceByEmail("manuelbras@beaver.com");

        //Assert
        assertNull(testRes);
    }

    @Test
    public void getResourceByUserTestSuccess() {
        //Arrange
        IResourceFactory resFac = mock(IResourceFactory.class);
        ProjectTeam projectTeam = new ProjectTeam(resFac);
        SystemUser user = mock(SystemUser.class);
        Resource manuelbras = mock(Resource.class);
        when(manuelbras.isYourEmail(user)).thenReturn(true);
        when(manuelbras.isCurrent()).thenReturn(true);

        projectTeam.saveResource(manuelbras);

        //Act
        Resource testRes = projectTeam.getResourceByUser(user);

        //Assert
        assertEquals(manuelbras, testRes);
    }

    @Test
    public void getResourceByUserTestFail() {
        //Arrange
        IResourceFactory resFac = mock(IResourceFactory.class);
        ProjectTeam projectTeam = new ProjectTeam(resFac);

        SystemUser user = mock(SystemUser.class);
        Resource manuelbras = mock(Resource.class);
        when(manuelbras.isYourEmail(user)).thenReturn(false);
        when(manuelbras.isCurrent()).thenReturn(false);

        projectTeam.saveResource(manuelbras);

        //Act
        Resource testRes = projectTeam.getResourceByUser(user);

        //Assert
        assertNull(testRes);
    }

    @Test
    public void getResourceByRoleTestSuccess() {
        //Arrange
        IResourceFactory resFac = mock(IResourceFactory.class);
        ProjectTeam projectTeam = new ProjectTeam(resFac);
        ProjectRole role = mock(ProjectRole.class);
        Resource manuelbras = mock(Resource.class);
        when(manuelbras.isYourEmail(role)).thenReturn(true);
        when(manuelbras.isCurrent()).thenReturn(true);

        projectTeam.saveResource(manuelbras);

        //Act
        Resource testRes = projectTeam.getResourceByRole(role);

        //Assert
        assertEquals(manuelbras, testRes);
    }

    @Test
    public void getResourceByRoleTestFail() {
        //Arrange
        IResourceFactory resFac = mock(IResourceFactory.class);
        ProjectTeam projectTeam = new ProjectTeam(resFac);
        ProjectRole role = mock(ProjectRole.class);
        Resource manuelbras = mock(Resource.class);
        when(manuelbras.isYourEmail(role)).thenReturn(false);
        when(manuelbras.isCurrent()).thenReturn(false);

        projectTeam.saveResource(manuelbras);

        //Act
        Resource testRes = projectTeam.getResourceByRole(role);

        //Assert
        assertNull(testRes);
    }

    @Test
    public void getResourceByRoleTestFailOneCondition() {
        //Arrange
        IResourceFactory resFac = mock(IResourceFactory.class);
        ProjectTeam projectTeam = new ProjectTeam(resFac);
        ProjectRole role = mock(ProjectRole.class);
        Resource manuelbras = mock(Resource.class);
        when(manuelbras.isYourEmail(role)).thenReturn(false);
        when(manuelbras.isCurrent()).thenReturn(true);

        projectTeam.saveResource(manuelbras);

        //Act
        Resource testRes = projectTeam.getResourceByRole(role);

        //Assert
        assertNull(testRes);
    }

/*    @Test
    public void getResourceByNameTestSuccess() {
        //Arrange
        ResourceFactory resFac = mock(ResourceFactory.class);
        ProjectTeam projectTeam = new ProjectTeam(resFac);

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
        ResourceFactory resFac = mock(ResourceFactory.class);
        ProjectTeam projectTeam = new ProjectTeam(resFac);

        Resource manuelbras = mock(Resource.class);
        when(manuelbras.isYourName("jorgebras")).thenReturn(false);
        when(manuelbras.isCurrent()).thenReturn(false);

        projectTeam.saveResource(manuelbras);

        //Act
        Resource testRes = projectTeam.getResourceByName("jorgebras");

        //Assert
        assertNull(testRes);
    }
 */
    /**
     * Get Current Resource List Test
     */
    @Test
    public void getCurrentResourcesNamesTestSuccess() {
        //Arrange
        IResourceFactory resourceFactory = mock(IResourceFactory.class);
        ProjectTeam projectTeam = new ProjectTeam(resourceFactory);

        SystemUser user = mock(SystemUser.class);
        Name name = mock(Name.class);

        when(name.getText()).thenReturn("chico");
        when(user.getUserName()).thenReturn(name);

        Resource chico = mock(Resource.class);
        when(chico.getUser()).thenReturn(user);
        when(chico.isCurrent()).thenReturn(true);

        projectTeam.saveResource(chico);

        SystemUser user2 = mock(SystemUser.class);
        Name name2 = mock(Name.class);

        when(name2.getText()).thenReturn("jorgebras");
        when(user2.getUserName()).thenReturn(name2);

        Resource jorgebras = mock(Resource.class);
        when(jorgebras.getUser()).thenReturn(user2);
        when(jorgebras.isCurrent()).thenReturn(true);

        projectTeam.saveResource(jorgebras);

        //Act
        List<String> testRes = projectTeam.getCurrentResourcesNames();

        //Assert
        assertEquals("chico", testRes.get(0));
        assertEquals("jorgebras", testRes.get(1));
    }

    @Test
    public void getCurrentResourcesNamesTestFail() {
        //Arrange
        IResourceFactory resFac = mock(IResourceFactory.class);
        ProjectTeam projectTeam = new ProjectTeam(resFac);

        SystemUser user = mock(SystemUser.class);
        Name name = mock(Name.class);

        when(name.getText()).thenReturn("chico");
        when(user.getUserName()).thenReturn(name);

        Resource chico = mock(Resource.class);
        when(chico.getUser()).thenReturn(user);
        when(chico.isCurrent()).thenReturn(false);

        projectTeam.saveResource(chico);

        SystemUser user2 = mock(SystemUser.class);
        Name name2 = mock(Name.class);

        when(name.getText()).thenReturn("jorgebras");
        when(user2.getUserName()).thenReturn(name2);

        Resource jorgebras = mock(Resource.class);
        when(jorgebras.getUser()).thenReturn(user2);
        when(jorgebras.isCurrent()).thenReturn(false);

        projectTeam.saveResource(jorgebras);

        //Act
        List<String> testRes = projectTeam.getCurrentResourcesNames();

        //Assert
        assertTrue(testRes.isEmpty());
    }


    @Test
    public void saveResourceTestSuccess() {
        //Arrange
        IResourceFactory resFac = mock(IResourceFactory.class);
        ProjectTeam projectTeam = new ProjectTeam(resFac);

        Resource chico = mock(Resource.class);

        //Assert
        assertTrue(projectTeam.saveResource(chico));
    }

    @Test
    public void saveResourceTestFailResourceNull() {
        //Assert
        assertThrows(NullPointerException.class, () -> {
            //Arrange
            IResourceFactory resFac = mock(IResourceFactory.class);
            ProjectTeam projectTeam = new ProjectTeam(resFac);
            //Act
            projectTeam.saveResource(null);
        });
    }

    /**
     * Assign new Project Role to a Resource
     */
/*    @Test //At this test the project role already exist in the team
    public void assignProjectRoleTestSuccessWithRoleInTheTeam() {
        //Arrange
        Company company = new Company();
        Typology typo = company.getTypologyRepository().findTypologyByDescription("Fixed Cost");
        Customer customer = company.getCustomerStore().getCustomerByName("isep");
        BusinessSector sector = company.getBusinessSectorStore().getBusinessSectorByDescription("it");
        //Project 1
        Project proj1 = company.getProjectStore().createAndSaveProject("prototype1", "proj1Prototype", customer,
                typo, sector, LocalDate.now().minusWeeks(2), 2, 3000);
        proj1.setEndDate(LocalDate.now().plusYears(1));
        UserProfile profile = company.getUserProfileStore().getUserProfile("Visitor");

        //Resource 1
        SystemUser user1 = new SystemUser("manuelbras", "manuelbras@beaver.com", "tester", "Qwerty_1", "Qwerty_1", "photo.png", profile.getUserProfileId());
        Resource manuelbras = proj1.createResource(user1, LocalDate.now().minusWeeks(2), LocalDate.now().plusWeeks(4), 100, .5);
        manuelbras.setRole(company.getProjectRoleStore().getProjectRole("Scrum Master"));
          proj1.getProjectTeam().saveResource(manuelbras);


 */
        //Resource 2
/*        SystemUser user2 = new SystemUser("manuelmartins", "manuelmartins@beaver.com", "tester", "Qwerty_1", "Qwerty_1", "photo.png", profile.getUserProfileId());
        Resource manuelmartins = proj1.createResource(user2, LocalDate.now().minusWeeks(2), LocalDate.now().plusWeeks(4), 100, 1);

        proj1.getProjectTeam().saveResource(manuelmartins);
        //Act
        proj1.getProjectTeam().assignProjectRole(manuelmartins, LocalDate.now(), 2, company.getProjectRoleStore().getProjectRole("Scrum Master"));
        //Assert
        assertEquals(proj1.getProjectTeam().getResourceByUser(user1).getRole(), company.getProjectRoleStore().getProjectRole("Team Member"));
        assertEquals(proj1.getProjectTeam().getResourceByUser(user2).getRole(), company.getProjectRoleStore().getProjectRole("Scrum Master"));
        assertEquals(4, proj1.getProjectTeam().getProjectTeamList().size());
        assertEquals(LocalDate.now(), proj1.getProjectTeam().getResourceByUser(user2).getStartDate());
    }

    @Test //At this test the project role already exist in the team
    public void assignProjectRoleTestSuccessWithOutRoleInTheTeam() {
        //Arrange
        Company company = new Company();
        Typology typo = company.getTypologyRepository().findTypologyByDescription("Fixed Cost");
        Customer customer = company.getCustomerStore().getCustomerByName("isep");
        BusinessSector sector = company.getBusinessSectorStore().getBusinessSectorByDescription("it");
        //Project 1
        Project proj1 = company.getProjectStore().createAndSaveProject("prototype1", "proj1Prototype", customer,
                typo, sector, LocalDate.now().minusWeeks(2), 2, 3000);
        proj1.setEndDate(LocalDate.now().plusYears(1));
        UserProfile profile = company.getUserProfileStore().getUserProfile("Visitor");
        //Resource 1
        SystemUser user1 = new SystemUser("manuelbras", "manuelbras@beaver.com", "tester", "Qwerty_1", "Qwerty_1", "photo.png", profile.getUserProfileId());
        Resource manuelbras = proj1.createResource(user1, LocalDate.now().minusWeeks(2), LocalDate.now().plusWeeks(4), 100, .5);
        manuelbras.setRole(company.getProjectRoleStore().getProjectRole("Team Member"));
        proj1.getProjectTeam().saveResource(manuelbras);
        //Resource 2
        SystemUser user2 = new SystemUser("manuelmartins", "manuelmartins@beaver.com", "tester", "Qwerty_1", "Qwerty_1", "photo.png", profile.getUserProfileId());
        Resource manuelmartins = proj1.createResource(user2, LocalDate.now().minusWeeks(2), LocalDate.now().plusWeeks(4), 100, 1);
        proj1.getProjectTeam().saveResource(manuelmartins);
        //Act
        proj1.getProjectTeam().assignProjectRole(manuelmartins, LocalDate.now(), 2, company.getProjectRoleStore().getProjectRole("Scrum Master"));
        //Assert
        assertEquals(proj1.getProjectTeam().getResourceByUser(user1).getRole(), company.getProjectRoleStore().getProjectRole("Team Member"));
        assertEquals(proj1.getProjectTeam().getResourceByUser(user2).getRole(), company.getProjectRoleStore().getProjectRole("Scrum Master"));
        assertEquals(3, proj1.getProjectTeam().getProjectTeamList().size());
    }

    @Test  //Assign a Role to a resource as null
    public void assignProjectRoleTestNullFail() {
        //Assert
        assertThrows(NullPointerException.class, () -> {
            //Arrange
            Company company = new Company();
            Typology typo = company.getTypologyRepository().findTypologyByDescription("Fixed Cost");
            Customer customer = company.getCustomerStore().getCustomerByName("isep");
            BusinessSector sector = company.getBusinessSectorStore().getBusinessSectorByDescription("it");
            //Project 1
            Project proj1 = company.getProjectStore().createAndSaveProject("prototype1", "proj1Prototype", customer,
                    typo, sector, LocalDate.now().minusWeeks(2), 2, 3000);
            proj1.setEndDate(LocalDate.now().plusYears(1));
            UserProfile profile = company.getUserProfileStore().getUserProfile("Visitor");
            //Resource 1
            SystemUser user1 = new SystemUser("manuelbras", "manuelbras@beaver.com", "tester", "Qwerty_1", "Qwerty_1", "photo.png", profile.getUserProfileId());
            Resource manuelbras = proj1.createResource(user1, LocalDate.now().minusWeeks(2), LocalDate.now().plusWeeks(4), 100, .5);
            manuelbras.setRole(company.getProjectRoleStore().getProjectRole("Scrum Master"));
            proj1.getProjectTeam().saveResource(manuelbras);
            //Resource 2
            SystemUser user2 = new SystemUser("manuelmartins", "manuelmartins@beaver.com", "tester", "Qwerty_1", "Qwerty_1", "photo.png", profile.getUserProfileId());
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
            Typology typo = company.getTypologyRepository().findTypologyByDescription("Fixed Cost");
            Customer customer = company.getCustomerStore().getCustomerByName("isep");
            BusinessSector sector = company.getBusinessSectorStore().getBusinessSectorByDescription("it");
            //Project 1
            Project proj1 = company.getProjectStore().createAndSaveProject("prototype1", "proj1Prototype", customer,
                    typo, sector, LocalDate.now().minusWeeks(2), 2, 3000);
            proj1.setEndDate(LocalDate.now().plusYears(1));
            UserProfile profile = company.getUserProfileStore().getUserProfile("Visitor");
            //Resource 1
            SystemUser user1 = new SystemUser("manuelbras", "manuelbras@beaver.com", "tester", "Qwerty_1", "Qwerty_1", "photo.png", profile.getUserProfileId());
            Resource manuelbras = proj1.createResource(user1, LocalDate.now().plusWeeks(1), LocalDate.now().plusWeeks(4), 100, .5);
            proj1.getProjectTeam().saveResource(manuelbras);
            //Act and Assert
            proj1.getProjectTeam().assignProjectRole(manuelbras, LocalDate.now(), 2, company.getProjectRoleStore().getProjectRole("Scrum Master"));
        });
    }

 */

    @Test
    public void hasResourceTestSuccess() {
        //Arrange
        IResourceFactory resFac = mock(IResourceFactory.class);
        ProjectTeam projectTeam = new ProjectTeam(resFac);
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
        IResourceFactory resFac = mock(IResourceFactory.class);
        ProjectTeam projectTeam = new ProjectTeam(resFac);
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
        IResourceFactory resFac = mock(IResourceFactory.class);
        ProjectTeam projectTeam = new ProjectTeam(resFac);
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
        IResourceFactory resFac = mock(IResourceFactory.class);
        ProjectTeam projectTeam = new ProjectTeam(resFac);
        Resource chico = mock(Resource.class);
        when(chico.isYourEmail("chico@beaver.com")).thenReturn(false);
        when(chico.isCurrent()).thenReturn(false);

        //Act
        projectTeam.saveResource(chico);

        //Assert
        assertFalse(projectTeam.hasCurrentResource(("chico@beaver.com")));
    }


//    @Test
//    public void hasCurrentResourceFailResourceNotCurrent() {
//        //Arrange
//        Company company = new Company();
//        Typology typo = company.getTypologyRepository().findTypologyById("Fixed Cost");
//        Customer customer = company.getCustomerStore().getCustomerByName("isep");
//        BusinessSector sector = company.getBusinessSectorStore().getBusinessSectorByDescription("it");
//        //Project 1
//        Project proj1 = company.getProjectStore().createAndSaveProject("prototype1", "proj1Prototype", customer,
//                typo, sector, LocalDate.now().minusWeeks(2), 2, 3000);
//        proj1.setEndDate(LocalDate.now().plusYears(1));
//
//        //Assert
//        assertFalse(proj1.getProjectTeam().hasCurrentResource(("manueloliveira@beaver.com")));
//    }

/*    @Test
    @DisplayName("Assign New Role for a resource")
    public void assignScrumMasterWithoutRepeatedTestSize() {
        //Arrange
        Company company = new Company();
        Typology typo = company.getTypologyRepository().findTypologyByDescription("Fixed Cost");
        Customer customer = company.getCustomerStore().getCustomerByName("isep");
        BusinessSector sector = company.getBusinessSectorStore().getBusinessSectorByDescription("it");
        //Project 1
        Project proj1 = company.getProjectStore().createAndSaveProject("prototype1", "proj1Prototype", customer,
                typo, sector, LocalDate.now().minusWeeks(2), 2, 3000);
        proj1.setEndDate(LocalDate.now().plusYears(1));
        //Resource 1
        UserProfile profile = company.getUserProfileStore().getUserProfile("Visitor");
        SystemUser user1 = new SystemUser("manuelbras", "manuelbras@beaver.com", "tester", "Qwerty_1", "Qwerty_1", "photo.png", profile.getUserProfileId());
        Resource manuelbras = new Resource(user1, LocalDate.of(2021, 11, 1), LocalDate.of(2022, 11, 15), new CostPerHour(100), new PercentageOfAllocation(.5));
        //Resource 2
        SystemUser user2 = new SystemUser("manuelmartins", "manuelmartins@beaver.com", "tester", "Qwerty_1", "Qwerty_1", "photo.png", profile.getUserProfileId());
        Resource manuelmartins = new Resource(user2, LocalDate.now().minusDays(6), LocalDate.now().plusDays(7), new CostPerHour(100), new PercentageOfAllocation(1));
        //Resource 3
        SystemUser user3 = new SystemUser("manueljose", "manueljose@beaver.com", "tester", "Qwerty_1", "Qwerty_1", "photo.png", profile.getUserProfileId());
        Resource manueljose = new Resource(user3, LocalDate.of(2021, 11, 1), LocalDate.of(2021, 11, 15), new CostPerHour(100), new PercentageOfAllocation(.5));
        //Resource 4
        SystemUser user4 = new SystemUser("manueloliveira", "manueloliveira@beaver.com", "tester", "Qwerty_1", "Qwerty_1", "photo.png", profile.getUserProfileId());
        Resource manueloliveira = new Resource(user4, LocalDate.now().minusWeeks(1), LocalDate.now().plusWeeks(3), new CostPerHour(100), new PercentageOfAllocation(.33333));
        manueloliveira.setRole(company.getProjectRoleStore().getProjectRole("Scrum Master"));
        //Resource 5
        SystemUser user5 = new SystemUser("manuelfernandes", "manuelfernandes@beaver.com", "tester", "Qwerty_1", "Qwerty_1", "photo.png", profile.getUserProfileId());
        Resource manuelfernandes = new Resource(user5, LocalDate.of(2021, 11, 16), LocalDate.of(2021, 11, 30), new CostPerHour(100), new PercentageOfAllocation(1));
        //Resource 6
        SystemUser user6 = new SystemUser("manuelgoncalves", "manuelgoncalves@beaver.com", "tester", "Qwerty_1", "Qwerty_1", "photo.png", profile.getUserProfileId());
        Resource manuelgoncalves = new Resource(user6, LocalDate.of(2021, 11, 16), LocalDate.of(2021, 11, 30), new CostPerHour(100), new PercentageOfAllocation(1));
        proj1.getProjectTeam().saveResource(manuelbras);
        proj1.getProjectTeam().saveResource(manuelgoncalves);
        proj1.getProjectTeam().saveResource(manuelfernandes);
        proj1.getProjectTeam().saveResource(manueljose);
        proj1.getProjectTeam().saveResource(manueloliveira);
        proj1.getProjectTeam().saveResource(manuelmartins);
        //Act
        Resource manuelTest = proj1.getProjectTeam().getResourceByEmail("manuelbras@beaver.com");
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
        Typology typo = company.getTypologyRepository().findTypologyByDescription("Fixed Cost");
        Customer customer = company.getCustomerStore().getCustomerByName("isep");
        BusinessSector sector = company.getBusinessSectorStore().getBusinessSectorByDescription("it");
        //Project 1
        Project proj1 = company.getProjectStore().createAndSaveProject("prototype1", "proj1Prototype", customer,
                typo, sector, LocalDate.now().minusWeeks(2), 2, 3000);
        proj1.setEndDate(LocalDate.now().plusYears(1));
        //Resource
        UserProfile profile = company.getUserProfileStore().getUserProfile("Visitor");
        SystemUser user1 = new SystemUser("manuelbrasil", "manuelbrasil@beaver.com", "tester", "Qwerty_1", "Qwerty_1", "photo.png", profile.getUserProfileId());
        LocalDate startDateMb = LocalDate.of(2021, 11, 1);
        LocalDate endDateMb = LocalDate.of(2022, 11, 15);
        Resource manuelbrasil = proj1.createResource(user1, startDateMb, endDateMb, 100, .5);
        manuelbrasil.setRole(company.getProjectRoleStore().getProjectRole("Scrum Master"));
        //Act
        proj1.getProjectTeam().saveResource(manuelbrasil);
        Resource manuelTest = proj1.getProjectTeam().getResourceByEmail("manuelbrasil@beaver.com");
        manuelTest.setRole(company.getProjectRoleStore().getProjectRole("Team Member"));
        proj1.getProjectTeam().assignProjectRole(manuelTest, LocalDate.of(2021, 11, 16), 2, company.getProjectRoleStore().getProjectRole("Scrum Master"));
        //Assert
        assertEquals(2, proj1.getProjectTeam().getProjectTeamList().size());
    }

 */

    @Test
    @DisplayName("Test to Validate Project Team - Success To Get PO and SM")
    public void validateProjectTeamSuccess() {
        //Arrange
        IResourceFactory resFac = mock(IResourceFactory.class);
        ProjectTeam projectTeam = new ProjectTeam(resFac);
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
        IResourceFactory resFac = mock(IResourceFactory.class);
        ProjectTeam projectTeam = new ProjectTeam(resFac);
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
        IResourceFactory resFac = mock(IResourceFactory.class);
        ProjectTeam projectTeam = new ProjectTeam(resFac);
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
        IResourceFactory resFac = mock(IResourceFactory.class);
        ProjectTeam projectTeam = new ProjectTeam(resFac);
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
        IResourceFactory resFac = mock(IResourceFactory.class);
        ProjectTeam projectTeam = new ProjectTeam(resFac);
        LocalDate date = LocalDate.now().plusDays(3);
        LocalDate endDate = LocalDate.now();

        //Mock results of other class mehtods
        ProjectRole role = mock(ProjectRole.class);
        Description description = mock(Description.class);
        Resource resource = mock(Resource.class);

        when(role.getName()).thenReturn(description);
        when(description.getText()).thenReturn("Team Member");
        when(resource.isYourEmail(any(ProjectRole.class))).thenReturn(false);
        when(resource.getEndDate()).thenReturn(endDate);

        projectTeam.saveResource(resource);

        //Assert expected result for this method
        assertFalse(projectTeam.checkIfTheRoleExistAndIsCurrent(role,date));
    }

    @Test
    @DisplayName("Check if role exist and is current - Product Owner")
    public void checkIfTheRoleExistAndIsCurrentTrueProductOwner() {
        //Arrange
        IResourceFactory resFac = mock(IResourceFactory.class);
        ProjectTeam projectTeam = new ProjectTeam(resFac);
        LocalDate date = LocalDate.now();
        LocalDate endDate = LocalDate.now().plusDays(3);

        //Mock results of other class mehtods
        ProjectRole role = mock(ProjectRole.class);
        Description description = mock(Description.class);
        Resource resource = mock(Resource.class);

        when(role.getName()).thenReturn(description);
        when(description.getText()).thenReturn("Product Owner");
        when(resource.isYourEmail(any(ProjectRole.class))).thenReturn(true);
        when(resource.getEndDate()).thenReturn(endDate);

        projectTeam.saveResource(resource);

        //Assert expected result for this method
        assertTrue(projectTeam.checkIfTheRoleExistAndIsCurrent(role,date));
    }

    @Test
    @DisplayName("Check if role exist and is current - Product Owner - startDate before - fail")
    public void checkIfTheRoleExistAndIsCurrentTrueProductOwnerFail() {
        //Arrange
        IResourceFactory resFac = mock(IResourceFactory.class);
        ProjectTeam projectTeam = new ProjectTeam(resFac);
        LocalDate date = LocalDate.now().plusDays(3);
        LocalDate endDate = LocalDate.now();

        //Mock results of other class mehtods
        ProjectRole role = mock(ProjectRole.class);
        Description description = mock(Description.class);
        Resource resource = mock(Resource.class);

        when(role.getName()).thenReturn(description);
        when(description.getText()).thenReturn("Product Owner");
        when(resource.isYourEmail(any(ProjectRole.class))).thenReturn(true);
        when(resource.getEndDate()).thenReturn(endDate);

        projectTeam.saveResource(resource);

        //Assert expected result for this method
        assertFalse(projectTeam.checkIfTheRoleExistAndIsCurrent(role,date));
    }

    @Test
    void overrideEqualsTestTrue() {
        //Arrange
        IResourceFactory resFac = mock(IResourceFactory.class);
        ProjectTeam pt = new ProjectTeam(resFac);
        ProjectTeam expected = new ProjectTeam(resFac);
        //Assert
        assertEquals(pt, expected);
    }

    @Test
    void overrideEqualsTestObjectTrue() {
        //Arrange
        IResourceFactory resFac = mock(IResourceFactory.class);
        ProjectTeam pt = new ProjectTeam(resFac);
        ProjectTeam expected = new ProjectTeam(resFac);
        //Assert
        assertEquals(pt, expected);
    }

    @Test
    void overrideEqualsTestFalseNull() {
        //Arrange
        IResourceFactory resFac = mock(IResourceFactory.class);
        ProjectTeam pt = new ProjectTeam(resFac);
        ProjectTeam expected = null;
        //Assert
        assertNotEquals(pt, expected);
    }

    @Test
    void overrideEqualsTestFalseInstanceOf() {
        //Arrange
        IResourceFactory resFac = mock(IResourceFactory.class);
        ProjectTeam pt = new ProjectTeam(resFac);
        Description expected = new Description("s");
        //Assert
        assertNotEquals(pt, expected);
    }

/*    @Test
    void overrideEqualsTestFalse() {
        //Arrange
        Company company = new Company();
        Typology typo = company.getTypologyRepository().findTypologyByDescription("Fixed Cost");
        Customer customer = company.getCustomerStore().getCustomerByName("isep");
        BusinessSector sector = company.getBusinessSectorStore().getBusinessSectorByDescription("it");
        //Project 1
        Project proj1 = company.getProjectStore().createAndSaveProject("prototype1", "proj1Prototype", customer,
                typo, sector, LocalDate.now(), 2, 3000);
        proj1.setEndDate(LocalDate.now().plusWeeks(10));
        UserProfile profile = company.getUserProfileStore().getUserProfile("Visitor");
        //Resource 3
        SystemUser joana3 = new SystemUser("joana", "joana3@beaver.com", "tester", "Switch_22", "Switch_22", "photo.png", profile.getUserProfileId());
        LocalDate startDatej3 = LocalDate.now();
        LocalDate endDatej3 = LocalDate.now().plusWeeks(2);
        Resource joana3R = proj1.createResource(joana3, startDatej3, endDatej3, 100, .5);
        joana3R.setRole(company.getProjectRoleStore().getProjectRole("Project Manager"));

        ResourceFactory resFac = mock(ResourceFactory.class);
        ProjectTeam pt = new ProjectTeam(resFac);
        ProjectTeam expected = new ProjectTeam(resFac);
        expected.saveResource(joana3R);
        //Assert
        assertNotEquals(pt, expected);
    }

 */


    @Test
    void overrideHashCodeTestTrue() {
        //Arrange
        IResourceFactory resFac = mock(IResourceFactory.class);
        ProjectTeam pt = new ProjectTeam(resFac);
        ProjectTeam expected = new ProjectTeam(resFac);
        //Assert
        assertEquals(pt.hashCode(), expected.hashCode());
    }

/*    @Test
    void overrideHashCodeTestFalse() {
        //Arrange
        Company company = new Company();
        Typology typo = company.getTypologyRepository().findTypologyByDescription("Fixed Cost");
        Customer customer = company.getCustomerStore().getCustomerByName("isep");
        BusinessSector sector = company.getBusinessSectorStore().getBusinessSectorByDescription("it");
        //Project 1
        Project proj1 = company.getProjectStore().createAndSaveProject("prototype1", "proj1Prototype", customer,
                typo, sector, LocalDate.now(), 2, 3000);
        proj1.setEndDate(LocalDate.now().plusWeeks(10));
        UserProfile profile = company.getUserProfileStore().getUserProfile("Visitor");
            //Resource 3
        SystemUser joana3 = new SystemUser("joana", "joana3@beaver.com", "tester", "Switch_22", "Switch_22", "photo.png", profile.getUserProfileId());
        LocalDate startDatej3 = LocalDate.now();
        LocalDate endDatej3 = LocalDate.now().plusWeeks(2);
        Resource joana3R = proj1.createResource(joana3, startDatej3, endDatej3, 100, .5);
        joana3R.setRole(company.getProjectRoleStore().getProjectRole("Project Manager"));

        ResourceFactory resFac = mock(ResourceFactory.class);
        ProjectTeam pt = new ProjectTeam(resFac);
        ProjectTeam expected = new ProjectTeam(resFac);
        expected.saveResource(joana3R);
        //Assert
        assertNotEquals(pt.hashCode(), expected.hashCode());
    }

    @Test
    public void shouldCreateAndAddProject() {
        // Arrange
        Company company = new Company();
        UserProfile profile = company.getUserProfileStore().getUserProfile("Visitor");
        SystemUser joana3 = new SystemUser("joana", "joana3@beaver.com", "tester", "Switch_22", "Switch_22", "photo.png", profile.getUserProfileId());
        LocalDate startDate = LocalDate.now().minusWeeks(1);
        LocalDate endDate = LocalDate.now().plusYears(1);
        Double costPerHour = 100.0;
        Double percAlloc = 0.5;

        Resource res = mock( Resource.class );
        SystemUser user = mock(SystemUser.class);

        ResourceFactory resFac =  mock( ResourceFactory.class );
        when(resFac.createResource(joana3, startDate, endDate, costPerHour, percAlloc)).thenReturn( res );

        ProjectTeam projTeam = new ProjectTeam( resFac );
        boolean hasCreated = projTeam.createAndAddResourceWithFac( joana3, startDate, endDate ,costPerHour,percAlloc);

        assertTrue( hasCreated );
    }

 */

    @Test
    public void shouldCreateAndAddProjectFail()
    {
        // Arrange
        Company company = new Company();
        SystemUser joana3 = null;
        LocalDate startDate = LocalDate.now().minusWeeks(1);
        LocalDate endDate = LocalDate.now().plusYears(1);
        double costPerHour = 100.0;
        double percAlloc = 0.5;

        Resource res = mock( Resource.class );
        SystemUser user = mock(SystemUser.class);

        IResourceFactory resFac =  mock( IResourceFactory.class );
        when(resFac.createResource(joana3, startDate, endDate, costPerHour, percAlloc)).thenReturn( res );

        ProjectTeam projTeam = new ProjectTeam( resFac );
        boolean hasCreated = projTeam.createAndAddResourceWithFac( joana3, startDate, endDate ,costPerHour,percAlloc);

        assertFalse( hasCreated );
    }

        @Test
    public void shouldCreateAndAddProject2()
    {
        // Arrange
        LocalDate startDate = LocalDate.now().minusWeeks(1);
        LocalDate endDate = LocalDate.now().plusYears(1);
        Double costPerHour = 100.0;
        Double percAlloc = 0.5;

        //Act
        SystemUser user = mock(SystemUser.class);
        IResourceFactory resFac =  new IResourceFactory();
        Resource res = resFac.createResource(user, startDate, endDate, costPerHour, percAlloc);

        //Assert
        assertEquals(user, res.getUser());
        assertEquals(startDate, res.getStartDate());
        assertEquals(endDate, res.getEndDate());
        assertEquals(costPerHour,res.getCostPerHour().getCost());
        assertEquals(percAlloc,res.getPercentageOfAllocation().getPercentage());
    }

}

