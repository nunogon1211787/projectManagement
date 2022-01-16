package switch2021.project.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switch2021.project.model.Company;
import switch2021.project.model.SystemUser;

import static org.junit.jupiter.api.Assertions.*;

public class ChangePasswordControllerTest {

    @Test
    public void changePassword() {
        //Arrange
        Company company = new Company();
        SystemUser user = new SystemUser("Joana", "123@isep.ipp.pt",
                "Aluna", "AAA", "AAA", "img_123", company.getUserProfileStore().getUserProfile("visitor"));
        //Act
        company.getSystemUserStore().addSystemUser(user);
        //Assert
        assertTrue(user.updatePassword("AAA", "BBB"));
    }

    @Test
    @DisplayName("Using the wrong old password")
    public void changePasswordFail_Test1() {
        //Arrange
        Company company = new Company();
        SystemUser user = new SystemUser("Joana", "123@isep.ipp.pt",
                "Aluna", "AAA", "AAA", "", company.getUserProfileStore().getUserProfile("visitor"));
        company.getSystemUserStore().addSystemUser(user);
        //Assert
        assertFalse(user.updatePassword("BBB", "CCC"));
    }

}
