package switch2021.project.controller;

import org.junit.jupiter.api.Test;
import switch2021.project.model.Company;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RegisterUserControllerTest {

    @Test //create and save a new user
    void createSystemUserSuccess() {
        //Arrange
        Company company = new Company();
        RegisterUserController controller = new RegisterUserController(company);
        String userName = "manueloliveira";
        String email = "manueloliveira@beaver.com";
        String password = "ghi";
        String passwordConfirmation = "ghi";
        String function = "tester";
        String photo = "photo";

        assertTrue(controller.createSystemUser(userName, email, function, password, passwordConfirmation, photo));
    }

    @Test //check fail username input value is empty
    void createSystemUserFailUserNameIsEmpty() {
        assertThrows(IllegalArgumentException.class, () -> {
            //Arrange
            Company company = new Company();
            RegisterUserController controller = new RegisterUserController(company);
            String userName = "";
            String email = "manueloliveira@beaver.com";
            String password = "ghi";
            String passwordConfirmation = "ghi";
            String function = "tester";
            String photo = "photo";

            controller.createSystemUser(userName, email, function, photo, password, passwordConfirmation);
        });
    }
}