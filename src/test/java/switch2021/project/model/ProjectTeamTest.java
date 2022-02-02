package switch2021.project.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switch2021.project.utils.App;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class ProjectTeamTest {

    /**
     * Attribute
     **/
    private Company company;
    private Project proj1;
    private Project proj2;
    private Project proj3;
    private Project currentProject;

    @BeforeEach
    public void init() {
        Company company = new Company();
        Typology typo = company.getTypologyStore().getTypologyByDescription("Fixed Cost");
        Customer customer = company.getCustomerStore().getCustomerByName("isep");
        BusinessSector sector = company.getBusinessSectorStore().getBusinessSectorByDescription("it");

        proj1 = company.getProjectStore().createProject( "prototype1", "proj1Prototype", customer,
                typo, sector, LocalDate.of(2021, 11, 1), 2, 3000);
        proj1.setEndDate(LocalDate.of(2021, 11, 30));

        proj2 = company.getProjectStore().createProject( "prototype2", "proj2Prototype", customer,
                typo, sector, LocalDate.of(2021, 11, 1), 2, 2000);
        proj2.setEndDate(LocalDate.of(2021, 11, 30));

        proj3 = company.getProjectStore().createProject( "prototype3", "proj3Prototype", customer,
                typo, sector, LocalDate.of(2021, 11, 1), 2, 2000);
        proj3.setEndDate(LocalDate.of(2021, 11, 30));

        currentProject = company.getProjectStore().createProject( "prototype4", "proj4Prototype", customer,
                typo, sector, LocalDate.now().minusDays(7), 2, 4000);
        currentProject.setEndDate(LocalDate.now().plusDays(7));

        UserProfile profile = company.getUserProfileStore().getUserProfile("Visitor");
        SystemUser user1 = new SystemUser("manuelbras", "manuelbras@beaver.com", "tester", "ghi", "ghi", "photo", profile);
        LocalDate startDateMb = LocalDate.of(2021, 11, 1);
        LocalDate endDateMb = LocalDate.of(2022, 11, 15);
        Resource manuelbras = new Resource(user1, startDateMb, endDateMb, 100, .5);

        SystemUser user2 = new SystemUser("manuelmartins", "manuelmartins@beaver.com", "tester", "ghi", "ghi", "photo", profile);
        LocalDate startDateMm = LocalDate.now().minusDays(7);
        LocalDate endDateMm = LocalDate.now().plusDays(7);
        Resource manuelmartins = new Resource(user2, startDateMm, endDateMm, 100, 1);

        SystemUser user3 = new SystemUser("manueljose", "manueljose@beaver.com", "tester", "ghi", "ghi", "photo", profile);
        LocalDate startDateMj = LocalDate.of(2021, 11, 1);
        LocalDate endDateMj = LocalDate.of(2021, 11, 15);
        Resource manueljose = new Resource(user3, startDateMj, endDateMj, 100, .5);

        SystemUser user4 = new SystemUser("manueloliveira", "manueloliveira@beaver.com", "tester", "ghi", "ghi", "photo", profile);
        LocalDate startDateMo = LocalDate.now().minusWeeks(1);
        LocalDate endDateMo = LocalDate.now().plusWeeks(3);
        Resource manueloliveira = new Resource(user4, startDateMo, endDateMo, 100, .3333);

        SystemUser user5 = new SystemUser("manuelfernandes", "manuelfernandes@beaver.com", "tester", "ghi", "ghi", "photo", profile);
        LocalDate startDateMf = LocalDate.of(2021, 11, 16);
        LocalDate endDateMf = LocalDate.of(2021, 11, 30);
        Resource manuelfernandes = new Resource(user5, startDateMf, endDateMf, 100, 1);

        SystemUser user6 = new SystemUser("manuelgoncalves", "manuelgoncalves@beaver.com", "tester", "ghi", "ghi", "photo", profile);
        LocalDate startDateMg = LocalDate.of(2021, 11, 16);
        LocalDate endDateMg = LocalDate.of(2021, 11, 30);
        Resource manuelgoncalves = new Resource(user6, startDateMg, endDateMg, 100, 1);

        proj1.getProjectTeam().addResourceToTeam(manuelbras);
        proj1.getProjectTeam().addResourceToTeam(manueljose);
        proj1.getProjectTeam().addResourceToTeam(manueloliveira);
        proj1.getProjectTeam().addResourceToTeam(manuelfernandes);
        proj2.getProjectTeam().addResourceToTeam(manuelbras);
        proj2.getProjectTeam().addResourceToTeam(manueloliveira);
        proj2.getProjectTeam().addResourceToTeam(manuelgoncalves);
        proj3.getProjectTeam().addResourceToTeam(manueljose);
        proj3.getProjectTeam().addResourceToTeam(manueloliveira);
        currentProject.getProjectTeam().addResourceToTeam(manuelmartins);
    }

    @Test
    public void hasCurrentResourceSuccess() {
        assertTrue(this.currentProject.getProjectTeam().hasCurrentResource(("manuelmartins@beaver.com")));
    }

    @Test
    public void hasCurrentResourceFailResourceNotPresent() {
        assertFalse(this.currentProject.getProjectTeam().hasCurrentResource(("manuelbras@beaver.com")));
    }

    @Test
    public void hasCurrentResourceFailResourceNotCurrent() {
        assertFalse(this.currentProject.getProjectTeam().hasCurrentResource(("manueloliveira@beaver.com")));
    }

    @Test
    @DisplayName("Get current resource by email")
    public void getCurrentResource() {
        //Arrange
        UserProfile profile = App.getInstance().getCompany().getUserProfileStore().getUserProfile("Visitor");
        SystemUser user4 = new SystemUser("manueloliveira", "manueloliveira@beaver.com", "tester", "ghi", "ghi", "photo", profile);
        LocalDate startDateMo = LocalDate.now().minusWeeks(1);
        LocalDate endDateMo = LocalDate.now().plusWeeks(3);
        Resource manueloliveira = new Resource(user4, startDateMo, endDateMo, 100, .3333);
        //Act
        Resource teste = proj1.getProjectTeam().getResource("manueloliveira@beaver.com");
        //Assert
        assertEquals(teste, manueloliveira);
    }

    @Test
    @DisplayName("Get current resource by email")
    public void getCurrentResourceNull() {
        //Assert
        assertNull(proj1.getProjectTeam().getResource("manuelbraga@beaver.com"));
    }


    @Test
    @DisplayName("Assign New Role for a resource")
    public void assignScrumMasterWithoutRepeatedTestSize() {
        //Arrange
        Company company = new Company();
        //Act
        Resource manuelTest = proj1.getProjectTeam().getResource("manuelbras@beaver.com");
        manuelTest.setRole(company.getProjectRoleStore().getProjectRole("Team Member"));
        proj1.getProjectTeam().assignProjectRole(manuelTest, LocalDate.of(2021,11,16), 2, company.getProjectRoleStore().getProjectRole("Scrum Master"));
        //Assert
        assertEquals(5, proj1.getProjectTeam().getProjectTeamList().size());
    }

    @Test
    @DisplayName("Assign New Role for a resource")
    public void assignScrumMasterWithRepeatedTestSize() {
        //Arrange
        Company company = new Company();
        UserProfile profile = company.getUserProfileStore().getUserProfile("Visitor");
        SystemUser user1 = new SystemUser("manuelbrasil", "manuelbrasil@beaver.com", "tester", "ghi", "ghi", "photo", profile);
        LocalDate startDateMb = LocalDate.of(2021, 11, 1);
        LocalDate endDateMb = LocalDate.of(2022, 11, 15);
        Resource manuelbrasil = new Resource(user1, startDateMb, endDateMb, 100, .5);
        manuelbrasil.setRole(company.getProjectRoleStore().getProjectRole("Scrum Master"));
        //Act
        proj1.getProjectTeam().addResourceToTeam(manuelbrasil);
        Resource manuelTest = proj1.getProjectTeam().getResource("manuelbras@beaver.com");
        manuelTest.setRole(company.getProjectRoleStore().getProjectRole("Team Member"));
        proj1.getProjectTeam().assignProjectRole(manuelTest, LocalDate.of(2021,11,16), 2, company.getProjectRoleStore().getProjectRole("Scrum Master"));
        //Assert
        assertEquals(7, proj1.getProjectTeam().getProjectTeamList().size());
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

//    @Test
//    @DisplayName("Test to Validate Project Team")
//    public void validateProjectTeamSuccess() {
//        //Arrange
//        //User1
//        Company company = new Company();
//        UserProfile profile = company.getUserProfileStore().getUserProfile("Visitor");
//        SystemUser joana = new SystemUser("joana", "joana@beaver.com", "developer", "ghi",
//                "ghi", "photo", profile);
//        LocalDate startDate = LocalDate.of(2022, 1, 1);
//        LocalDate endDate = LocalDate.of(2022, 1, 15);
//        Resource joanaResource = new Resource(joana, startDate, endDate, 100, .5);
//        boolean joanaRole = joanaResource.isYour("Scrum Master");
//        //User2
//        SystemUser isabel = new SystemUser("isabel", "isabel@beaver.com", "tester", "ghi",
//                "ghi", "photo1", profile);
//        LocalDate startDateIsa = LocalDate.of(2022, 1, 1);
//        LocalDate endDateIsa = LocalDate.of(2022, 1, 15);
//        Resource isabelResource = new Resource(isabel, startDateIsa, endDateIsa, 100, .5);
//        //Act
//        proj1.getProjectTeam().addResourceToTeam(joanaResource);
//        proj1.getProjectTeam().addResourceToTeam(isabelResource);
//        int joanaSize = company.getProjectStore().getCurrentProjectsByUserEmail("joana@beaver.com").size();
//        int isabelSize = company.getProjectStore().getCurrentProjectsByUserEmail("isabel@beaver.com").size();
//        //proj1.getProjectTeam().assignProjectRole(manuelTest, LocalDate.of(2021,11,16), 2, company.getProjectRoleStore().getProjectRole("Scrum Master"));
//        //Assert
//        assertEquals(joanaSize, isabelSize);
//        assertTrue(proj1.getProjectTeam().validateProjectTeam(startDate, 1));
//
//        //assertTrue(proj1.getProjectTeam().validateProjectTeam(startDateIsa, 1));
//
//    }
}

