package switch2021.project.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switch2021.project.dto.SystemUserDto;
import switch2021.project.model.Company;
import switch2021.project.model.SystemUser;
import switch2021.project.model.UserProfile;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SystemUserWithStatusControllerTest {

    @Test
    @DisplayName("check if the list size is correct and check if information are correct")
    void getListSystemUserWithStatusSuccess (){
        //Arrange
        Company company = new Company();


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

        SystemUserWithStatusController systemUserWithStatusController = new SystemUserWithStatusController(company);
        List <SystemUserDto> systemUserDto = systemUserWithStatusController.getListSystemUserWithStatus();

        // Assert
        assertEquals(2, systemUserDto.size());
        assertEquals(newUser.getUserName(),systemUserDto.get(0).getUserName());
        assertEquals(newUser.getEmail(),systemUserDto.get(0).getEmail());
        assertEquals(newUser.getActivateUserStatus(), systemUserDto.get(0).isActivateUser());
        assertEquals(newUser2.getUserName(),systemUserDto.get(1).getUserName());
        assertEquals(newUser2.getEmail(),systemUserDto.get(1).getEmail());
        assertEquals(newUser2.getActivateUserStatus(), systemUserDto.get(1).isActivateUser());
    }
}
