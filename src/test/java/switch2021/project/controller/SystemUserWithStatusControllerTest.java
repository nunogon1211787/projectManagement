package switch2021.project.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switch2021.project.dto.SystemUserWithStatusDto;
import switch2021.project.mapper.SystemUserWithStatusMapper;
import switch2021.project.model.Company;
import switch2021.project.model.SystemUser.SystemUser;
import switch2021.project.model.UserProfile.UserProfile;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SystemUserWithStatusControllerTest {

    @Test
    @DisplayName("check if the list size is correct")
    void getListSystemUserWithStatusSuccessSize() {
        //Arrange
        Company company = new Company();
        SystemUserWithStatusMapper mapper = new SystemUserWithStatusMapper();
        SystemUserWithStatusController systemUserWithStatusController = new SystemUserWithStatusController(company, mapper);

        String userName = "manueloliveira";
        String email = "manueloliveira@beaver.com";
        String password = "Qwerty_1";
        String passwordConfirmation = "Qwerty_1";
        String function = "tester";
        String photo = "photo.png";
        UserProfile profile = company.getUserProfileStore().getUserProfile("Visitor");

        SystemUser newUser = company.getSystemUserStore().createSystemUser(userName, email, function, password,
                passwordConfirmation, photo, profile);
        newUser.setActivateUser(true);
        company.getSystemUserStore().saveSystemUser(newUser);
        SystemUser newUser2 = company.getSystemUserStore().createSystemUser("Cris", "Cris@ipp.pt",
                function, password, passwordConfirmation, photo, profile);
        newUser2.setActivateUser(true);
        company.getSystemUserStore().saveSystemUser(newUser2);
        // Act
        List<SystemUserWithStatusDto> systemUserWithStatusDto = systemUserWithStatusController.getListSystemUserWithStatus();

        // Assert
        assertEquals(2, systemUserWithStatusDto.size());
    }

    @Test
    @DisplayName("check if the list is empty")
    void getListSystemUserWithStatusEmptyList() {
        //Arrange
        Company company = new Company();
        SystemUserWithStatusMapper mapper = new SystemUserWithStatusMapper();
        SystemUserWithStatusController systemUserWithStatusController = new SystemUserWithStatusController(company, mapper);

        String userName = "manueloliveira";
        String email = "manueloliveira@beaver.com";
        String password = "Qwerty_1";
        String passwordConfirmation = "Qwerty_1";
        String function = "tester";
        String photo = "photo.png";
        UserProfile profile = company.getUserProfileStore().getUserProfile("Visitor");

        SystemUser newUser = company.getSystemUserStore().createSystemUser(userName, email, function, password,
                passwordConfirmation, photo, profile);
        newUser.setActivateUser(true);
        SystemUser newUser2 = company.getSystemUserStore().createSystemUser("Cris", "Cris@ipp.pt",
                function, password, passwordConfirmation, photo, profile);
        newUser2.setActivateUser(false);
        // Act
        List<SystemUserWithStatusDto> systemUserWithStatusDto = systemUserWithStatusController.getListSystemUserWithStatus();

        // Assert
        assertEquals(0, systemUserWithStatusDto.size());
    }



    @Test
    @DisplayName("check if the created DTO list contains correct information")
    void getListSystemUserWithStatusSuccess() {
        //Arrange
        Company company = new Company();
        SystemUserWithStatusMapper mapper = new SystemUserWithStatusMapper();
        SystemUserWithStatusController systemUserWithStatusController = new SystemUserWithStatusController(company, mapper);

        String userName = "manueloliveira";
        String email = "manueloliveira@beaver.com";
        String password = "Qwerty_1";
        String passwordConfirmation = "Qwerty_1";
        String function = "tester";
        String photo = "photo.png";
        UserProfile profile = company.getUserProfileStore().getUserProfile("Visitor");

        SystemUser newUser = company.getSystemUserStore().createSystemUser(userName, email, function, password,
                passwordConfirmation, photo, profile);
        newUser.setActivateUser(true);
        company.getSystemUserStore().saveSystemUser(newUser);
        SystemUser newUser2 = company.getSystemUserStore().createSystemUser("Cris", "Cris@ipp.pt",
                function, password, passwordConfirmation, photo, profile);
        newUser2.setActivateUser(true);
        company.getSystemUserStore().saveSystemUser(newUser2);

        // Act
        List<SystemUserWithStatusDto> systemUserWithStatusDto = systemUserWithStatusController.getListSystemUserWithStatus();

        // Assert
        assertEquals(newUser.getUserName().getNameF(), systemUserWithStatusDto.get(0).getUserName());
        assertEquals(newUser.getEmail().getEmail(), systemUserWithStatusDto.get(0).getEmail());
        assertEquals(newUser.isActivateUser(), systemUserWithStatusDto.get(0).isActivateUser());
        assertEquals(newUser2.getUserName().getNameF(), systemUserWithStatusDto.get(1).getUserName());
        assertEquals(newUser2.getEmail().getEmail(), systemUserWithStatusDto.get(1).getEmail());
        assertEquals(newUser2.isActivateUser(), systemUserWithStatusDto.get(1).isActivateUser());
    }

    @Test
    @DisplayName("check if the created DTO list contains users active and users inactive")
    void getListSystemUserWithWithAnyStatus() {
        //Arrange
        Company company = new Company();
        SystemUserWithStatusMapper mapper = new SystemUserWithStatusMapper();
        SystemUserWithStatusController systemUserWithStatusController = new SystemUserWithStatusController(company, mapper);

        String userName = "manueloliveira";
        String email = "manueloliveira@beaver.com";
        String password = "Qwerty_1";
        String passwordConfirmation = "Qwerty_1";
        String function = "tester";
        String photo = "photo.png";
        UserProfile profile = company.getUserProfileStore().getUserProfile("Visitor");

        SystemUser newUser = company.getSystemUserStore().createSystemUser(userName, email, function, password,
                passwordConfirmation, photo, profile);
        newUser.setActivateUser(false);
        company.getSystemUserStore().saveSystemUser(newUser);
        SystemUser newUser2 = company.getSystemUserStore().createSystemUser("Cris", "Cris@ipp.pt",
                function, password, passwordConfirmation, photo, profile);
        newUser2.setActivateUser(true);
        company.getSystemUserStore().saveSystemUser(newUser2);

        // Act
        List<SystemUserWithStatusDto> systemUserWithStatusDto = systemUserWithStatusController.getListSystemUserWithStatus();

        // Assert
        assertEquals(2, systemUserWithStatusDto.size());
        assertEquals(newUser.isActivateUser(), systemUserWithStatusDto.get(0).isActivateUser());
        assertEquals(newUser2.isActivateUser(), systemUserWithStatusDto.get(1).isActivateUser());

    }
}
