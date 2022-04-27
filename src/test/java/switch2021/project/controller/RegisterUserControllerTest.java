package switch2021.project.controller;

import org.junit.jupiter.api.Test;
import switch2021.project.dto.RegisterUserDTO;
import switch2021.project.factory.SystemUserFactory;
import switch2021.project.factoryInterface.SystemUserFactoryInterface;
import switch2021.project.mapper.SystemUserMapper;
import switch2021.project.model.Company;
import switch2021.project.model.SystemUser.SystemUserService;
import switch2021.project.stores.SystemUserStore;
import switch2021.project.repositories.UserProfileRepository;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RegisterUserControllerTest {

/*    @Test //create and save a new user
    void createSystemUserSuccess() {
        //Arrange
        Company company = new Company();
        SystemUserStore systemUserStore = company.getSystemUserStore();
        UserProfileRepository userProfileStore = company.getUserProfileStore();
        SystemUserMapper systemUserMapper = new SystemUserMapper();
        SystemUserFactory systemUserFactory = new SystemUserFactory();

        SystemUserService systemUserService = new SystemUserService(systemUserStore,userProfileStore,systemUserMapper,systemUserFactory);
        RegisterUserController controller = new RegisterUserController(company,systemUserService);

        RegisterUserDTO dto = new RegisterUserDTO();
        dto.userName = "manueloliveira";
        dto.email = "manueloliveira@beaver.com";
        dto.password = "Qwerty_1";
        dto.passwordConfirmation = "Qwerty_1";
        dto.function = "tester";
        dto.photo = "photo.png";

        //assertTrue(controller.createSystemUser(userName, email, function, password, passwordConfirmation, photo));
        assertTrue(controller.registerUser(dto));
    }

    @Test //check fail username input value is empty
    void createSystemUserFailUserNameIsEmpty() {
        assertThrows(IllegalArgumentException.class, () -> {
            //Arrange
            Company company = new Company();
            SystemUserStore systemUserStore = company.getSystemUserStore();
            UserProfileRepository userProfileStore = company.getUserProfileStore();
            SystemUserService systemUserService = new SystemUserService(systemUserStore,userProfileStore);
            RegisterUserController controller = new RegisterUserController(company,systemUserService);
            String userName = "";
            String email = "manueloliveira@beaver.com";
            String password = "ghi";
            String passwordConfirmation = "ghi";
            String function = "tester";
            String photo = "photo";

            //controller.createSystemUser(userName, email, function, photo, password, passwordConfirmation);
            controller.registerUser(userName, email, function, photo, password, passwordConfirmation);
        });
    }*/
}