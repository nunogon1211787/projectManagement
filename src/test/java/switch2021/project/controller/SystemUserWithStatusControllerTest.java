package switch2021.project.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switch2021.project.dto.SystemUserWithStatusDto;
import switch2021.project.mapper.SystemUserWithStatusMapper;
import switch2021.project.model.Company;
import switch2021.project.model.SystemUser;
import switch2021.project.model.UserProfile;

import java.util.ArrayList;
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
        String password = "ghi";
        String passwordConfirmation = "ghi";
        String function = "tester";
        String photo = "photo";
        UserProfile profile = company.getUserProfileStore().getUserProfile("Visitor");

        List<UserProfile> assignedProfileExpected = new ArrayList<>();
        assignedProfileExpected.add(profile);

        SystemUser newUser = company.getSystemUserStore().createSystemUser(userName, email, function, password,
                passwordConfirmation, photo, profile);
        newUser.setActivateUser(true);
        company.getSystemUserStore().saveSystemUser(newUser);
        SystemUser newUser2 = company.getSystemUserStore().createSystemUser("Cris", "Cris@ipp.pt",
                function, password, passwordConfirmation, photo, profile);
        company.getSystemUserStore().saveSystemUser(newUser2);
        // Act
        List<SystemUserWithStatusDto> systemUserWithStatusDto = systemUserWithStatusController.getListSystemUserWithStatus();

        // Assert
        assertEquals(2, systemUserWithStatusDto.size());
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
        String password = "ghi";
        String passwordConfirmation = "ghi";
        String function = "tester";
        String photo = "photo";
        UserProfile profile = company.getUserProfileStore().getUserProfile("Visitor");

        List<UserProfile> assignedProfileExpected = new ArrayList<>();
        assignedProfileExpected.add(profile);

        SystemUser newUser = company.getSystemUserStore().createSystemUser(userName, email, function, password,
                passwordConfirmation, photo, profile);
        newUser.setActivateUser();
        company.getSystemUserStore().saveSystemUser(newUser);
        SystemUser newUser2 = company.getSystemUserStore().createSystemUser("Cris", "Cris@ipp.pt",
                function, password, passwordConfirmation, photo, profile);
        company.getSystemUserStore().saveSystemUser(newUser2);

        // Act
        List<SystemUserWithStatusDto> systemUserWithStatusDto = systemUserWithStatusController.getListSystemUserWithStatus();

        // Assert
        assertEquals(newUser.getUserName(), systemUserWithStatusDto.get(0).getUserName());
        assertEquals(newUser.getEmail(), systemUserWithStatusDto.get(0).getEmail());
        assertEquals(newUser.getActivateUserStatus(), systemUserWithStatusDto.get(0).isActivateUser());
        assertEquals(newUser2.getUserName(), systemUserWithStatusDto.get(1).getUserName());
        assertEquals(newUser2.getEmail(), systemUserWithStatusDto.get(1).getEmail());
        assertEquals(newUser2.getActivateUserStatus(), systemUserWithStatusDto.get(1).isActivateUser());
    }
}
