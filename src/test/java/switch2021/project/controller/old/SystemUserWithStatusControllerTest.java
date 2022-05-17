package switch2021.project.controller.old;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SystemUserWithStatusControllerTest {

/*    @Test
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

        SystemUser newUser = new SystemUser(userName, email, function, password,
                passwordConfirmation, photo, profile.getUserProfileId());
        newUser.setActive(true);
        company.getSystemUserStore().saveSystemUser(newUser);
        SystemUser newUser2 = new SystemUser("Cris", "Cris@ipp.pt",
                function, password, passwordConfirmation, photo, profile.getUserProfileId());
        newUser2.setActive(true);
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

        SystemUser newUser = new SystemUser(userName, email, function, password,
                passwordConfirmation, photo, profile.getUserProfileId());
        newUser.setActive(true);
        SystemUser newUser2 = new SystemUser("Cris", "Cris@ipp.pt",
                function, password, passwordConfirmation, photo, profile.getUserProfileId());
        newUser2.setActive(false);
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

        SystemUser newUser = new SystemUser(userName, email, function, password,
                passwordConfirmation, photo, profile.getUserProfileId());
        newUser.setActive(true);
        company.getSystemUserStore().saveSystemUser(newUser);
        SystemUser newUser2 = new SystemUser("Cris", "Cris@ipp.pt",
                function, password, passwordConfirmation, photo, profile.getUserProfileId());
        newUser2.setActive(true);
        company.getSystemUserStore().saveSystemUser(newUser2);

        // Act
        List<SystemUserWithStatusDto> systemUserWithStatusDto = systemUserWithStatusController.getListSystemUserWithStatus();

        // Assert
        assertEquals(newUser.getUserName().getText(), systemUserWithStatusDto.get(0).getUserName());
        assertEquals(newUser.getSystemUserId().getEmail().getEmailText(), systemUserWithStatusDto.get(0).getEmail());
        assertEquals(newUser.isActive(), systemUserWithStatusDto.get(0).isActivateUser());
        assertEquals(newUser2.getUserName().getText(), systemUserWithStatusDto.get(1).getUserName());
        assertEquals(newUser2.getSystemUserId().getEmail().getEmailText(), systemUserWithStatusDto.get(1).getEmail());
        assertEquals(newUser2.isActive(), systemUserWithStatusDto.get(1).isActivateUser());
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

        SystemUser newUser = new SystemUser(userName, email, function, password,
                passwordConfirmation, photo, profile.getUserProfileId());
        newUser.setActive(false);
        company.getSystemUserStore().saveSystemUser(newUser);
        SystemUser newUser2 = new SystemUser("Cris", "Cris@ipp.pt",
                function, password, passwordConfirmation, photo, profile.getUserProfileId());
        newUser2.setActive(true);
        company.getSystemUserStore().saveSystemUser(newUser2);

        // Act
        List<SystemUserWithStatusDto> systemUserWithStatusDto = systemUserWithStatusController.getListSystemUserWithStatus();

        // Assert
        assertEquals(2, systemUserWithStatusDto.size());
        assertEquals(newUser.isActive(), systemUserWithStatusDto.get(0).isActivateUser());
        assertEquals(newUser2.isActive(), systemUserWithStatusDto.get(1).isActivateUser());

    }

 */
}
