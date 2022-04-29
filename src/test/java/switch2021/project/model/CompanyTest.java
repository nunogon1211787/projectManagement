package switch2021.project.model;

import org.junit.jupiter.api.Test;
import switch2021.project.model.SystemUser.SystemUser;
import switch2021.project.repositories.ProjectStore;
import switch2021.project.model.UserProfile.UserProfile;

import static org.junit.jupiter.api.Assertions.*;

class CompanyTest {

    @Test
    public void companyConstructor() {
        //Arrange
        Company company = new Company();
        //Assert
        assertEquals(4, company.getUserProfileStore().getUserProfileList().size());
        assertEquals(0, company.getTypologyRepository().getTypologyList().size());
        assertEquals(0, company.getCustomerStore().getCustomerList().size());
        assertEquals(0, company.getBusinessSectorStore().getBusinessSectorList().size());
        assertEquals(3, company.getUserStoryStatusStore().getUserStoryStatusList().size());
        //assertEquals(0, company.getRequestStore().getRequestProfileList().size());
    }

/*    @Test
    public void SearchUserByPartiallySwitchedEmail() {

        //Arrange
        Company company = new Company();
        UserProfile profile = company.getUserProfileStore().getUserProfile("Visitor");
        SystemUser joana = new SystemUser("Joana Silva", "1234@isep.ipp.pt", "Aluna", "Qwerty_1", "Qwerty_1", ".jpg", profile.getUserProfileId());
        company.getSystemUserStore().saveSystemUser(joana);
        //Act
        SystemUser joanasilva = company.getSystemUserStore().findSystemUserByEmail("1235@isep.ipp.pt");
        //Assert
        assertNotEquals(joana, joanasilva);
    }

    @Test
    public void SearchUserByWrongEmail() {

        //Arrange
        Company company = new Company();
        UserProfile profile = company.getUserProfileStore().getUserProfile("Visitor");
        SystemUser joana = new SystemUser("Joana Silva", "1234@isep.ipp.pt", "Aluna", "Qwerty_1", "Qwerty_1", ".png", profile.getUserProfileId());
        company.getSystemUserStore().saveSystemUser(joana);
        //Act
        SystemUser joanasilva = company.getSystemUserStore().findSystemUserByEmail("4321@ipp.pt");
        //Assert
        assertNotEquals(joana, joanasilva);
    }

 */

    @Test
    public void getProjectStoreIsInstanceOfProjectStore() {
        //Arrange
        Company c = new Company();
        ProjectStore p = new ProjectStore();
        // Assert
        assertInstanceOf(p.getClass(), c.getProjectStore());
        assertNotSame(p, c.getProjectStore());
        assertNotNull(c.getProjectStore());
    }

    @Test
    public void getProjectStoreHasList() {
        //Arrange
        Company c = new Company();
        // Assert
        assertTrue(c.getProjectStore().findAll().isEmpty());
    }
}