package switch2021.project.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;


public class ProjectTeamTest {
    /**
     * Attribute
     **/
    private Company company;
    private Project project1;

    public Project getProject1() {
        init();
        return project1;
    }

    @BeforeEach
    public void init() {
        Company company = new Company();
        Typology typo = company.getTypologyStore().getTypology("Fixed Cost");
        Customer customer = company.getCustomerStore().getCustomerByName("Teste");
        BusinessSector sector = company.getBusinessSectorStore().getBusinessSectorByDescription("sector");
        project1 = company.getProjectStore().createProject("123testcode", "prototype", "test56", customer,
                typo, sector, LocalDate.now(), 7, 5000);

        UserProfile profile1 = company.getUserProfileStore().getUserProfile("Visitor");
        SystemUser user1 = new SystemUser("manuelbras", "manuelbras@beaver.com", "tester", "ghi", "ghi", "photo", profile1);
        LocalDate startDate1 = LocalDate.of(2021, 12, 31);
        LocalDate endDate1 = LocalDate.of(2022, 1, 5);
        Resource resource1 = new Resource(user1, startDate1, endDate1, 100, .2);

        UserProfile profile2 = company.getUserProfileStore().getUserProfile("Visitor");
        SystemUser user2 = new SystemUser("manuelmartins", "manuelmartins@beaver.com", "tester", "ghi", "ghi", "photo", profile2);
        LocalDate startDate2 = LocalDate.of(2022, 1, 6);
        LocalDate endDate2 = LocalDate.of(2022, 1, 11);
        Resource resource2 = new Resource(user2, startDate2, endDate2, 100, .2);

        UserProfile profile3 = company.getUserProfileStore().getUserProfile("Visitor");
        SystemUser user3 = new SystemUser("manueljose", "manueljose@beaver.com", "tester", "ghi", "ghi", "photo", profile3);
        LocalDate startDate3 = LocalDate.of(2022, 1, 12);
        LocalDate endDate3 = LocalDate.of(2022, 1, 17);
        Resource resource3 = new Resource(user3, startDate3, endDate3, 100, .2);

        UserProfile profile4 = company.getUserProfileStore().getUserProfile("Visitor");
        SystemUser user4 = new SystemUser("manueloliveira", "manueloliveira@beaver.com", "tester", "ghi", "ghi", "photo", profile4);
        LocalDate startDate4 = LocalDate.of(2022, 1, 18);
        LocalDate endDate4 = LocalDate.of(2022, 1, 23);
        Resource resource4 = new Resource(user4, startDate4, endDate4, 100, .2);

        UserProfile profile5 = company.getUserProfileStore().getUserProfile("Visitor");
        SystemUser user5 = new SystemUser("manuelgoncalves", "manuelgoncalves@beaver.com", "tester", "ghi", "ghi", "photo", profile5);
        LocalDate startDate5 = LocalDate.of(2022, 1, 24);
        LocalDate endDate5 = LocalDate.of(2022, 1, 29);
        Resource resource5 = new Resource(user5, startDate5, endDate5, 100, .2);

        project1.getProjectTeam().addResourceToTeam(resource1);
        project1.getProjectTeam().addResourceToTeam(resource2);
        project1.getProjectTeam().addResourceToTeam(resource3);
        project1.getProjectTeam().addResourceToTeam(resource4);
        project1.getProjectTeam().addResourceToTeam(resource5);

    }

    @Test
    public void hasCurrentResourceSuccess() {
        assertTrue(project1.getProjectTeam().hasCurrentResource(("manueloliveira@beaver.com")));
    }

    @Test
    public void hasCurrentResourceFailResourceNotPresent() {
        assertFalse(project1.getProjectTeam().hasCurrentResource(("manuelalexandre@beaver.com")));
    }

    @Test
    public void hasCurrentResourceFailResourceNotCurrent() {
        assertFalse(project1.getProjectTeam().hasCurrentResource(("manueljose@beaver.com")));
    }
}
