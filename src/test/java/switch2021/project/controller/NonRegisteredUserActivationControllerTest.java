package switch2021.project.controller;

import org.junit.jupiter.api.Test;
import switch2021.project.model.Company;
import switch2021.project.model.SystemUser;
import static org.junit.jupiter.api.Assertions.assertTrue;


class NonRegisteredUserActivationControllerTest {

    @Test
    public void Mytest () {
        //Arrange
        Company company = new Company();
        SystemUser user = new SystemUser("Ana", "1211748@isep.ipp.pt",
                "User_12", "111", company.getUserProfile("visitor"));
        NonRegisteredUserActivationController userActivation = new NonRegisteredUserActivationController(company);
        //Assert
        assertTrue(userActivation.getUserbyEmail("1211748@isep.ipp.pt"));
    }
}
