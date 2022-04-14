package switch2021.project.controller;

import org.junit.jupiter.api.Test;
import switch2021.project.model.Company;
import switch2021.project.model.SystemUser.SystemUser;
import switch2021.project.model.valueObject.Request;
import switch2021.project.model.UserProfile.UserProfile;
import switch2021.project.model.valueObject.SystemUserId;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class ProfileRequestControllerTest {

    @Test
    public void createRequestSuccess() {
        //Arrange
        Company company = new Company();
        ProfileRequestController controller = new ProfileRequestController(company);
        UserProfile profile = company.getUserProfileStore().getUserProfile("Visitor");
        SystemUser testUser = new SystemUser("joaquim", "xxxx@isep.ipp.pt",
                "tester", "Qwerty_1", "Qwerty_1", "photo.png", profile.getUserProfileId());
        company.getSystemUserStore().saveSystemUser(testUser);
        //Act + Assert
        assertTrue(controller.createProfileRequest("xxxx@isep.ipp.pt","User"));
    }

    @Test
    public void createRequestSuccessRequestAThirdProfile() {
        //Arrange
        Company company = new Company();
        ProfileRequestController controller = new ProfileRequestController(company);
        UserProfile profile = company.getUserProfileStore().getUserProfile("Visitor");
        SystemUser testUser = new SystemUser("joaquim", "xxxx@isep.ipp.pt",
                "tester", "Qwerty_1", "Qwerty_1", "photo.png", profile.getUserProfileId());
        company.getSystemUserStore().saveSystemUser(testUser);
        controller.createProfileRequest("xxxx@isep.ipp.pt","User");
        //Act + Assert
        assertTrue(controller.createProfileRequest("xxxx@isep.ipp.pt","Director"));
    }

    @Test
    public void createRequestFailSameProfile() {
        //Arrange
        Company company = new Company();
        ProfileRequestController controller = new ProfileRequestController(company);
        UserProfile profile = company.getUserProfileStore().getUserProfile("Visitor");
        SystemUser user = new SystemUser("joaquim", "xxxx@isep.ipp.pt",
                "tester", "Qwerty_1", "Qwerty_1", "photo.png", profile.getUserProfileId());
        company.getSystemUserStore().saveSystemUser(user);
        //Act + Assert
        assertFalse(controller.createProfileRequest("xxxx@isep.ipp.pt","Visitor"));
    }

    @Test
    public void createRequestFailSameRequestSameDay() {
        //Arrange
        Company company = new Company();
        ProfileRequestController controller = new ProfileRequestController(company);
        UserProfile profile = company.getUserProfileStore().getUserProfile("Visitor");
        SystemUser user = new SystemUser("joaquim", "xxxx@isep.ipp.pt",
                "tester", "Qwerty_1", "Qwerty_1", "photo.png", profile.getUserProfileId());
        company.getSystemUserStore().saveSystemUser(user);
        controller.createProfileRequest("xxxx@isep.ipp.pt","User");
        //Act + Assert
        assertFalse(controller.createProfileRequest("xxxx@isep.ipp.pt","User"));
    }



}