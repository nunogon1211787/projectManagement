package switch2021.project.controller;

import org.junit.jupiter.api.Test;
import switch2021.project.model.Company;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RegisterUserControllerTest {
    /*@Test
    void createSystemUserSuccess() {
        //Arrange
        Company company = new Company();
        String userName = "manueloliveira";
        String email = "manueloliveira@beaver.com";
        String password = "ghi";
        String passwordConfirmation = "ghi";
        String function = "tester";
        String photo = "photo";
        RegisterUserController controller = new RegisterUserController();

        assertTrue(controller.createSystemUser(userName, email, function, password, passwordConfirmation, photo));
    }*/

    @Test
    void createSystemUserWithPhotoFailUserNameIsEmpty() {
        assertThrows(IllegalArgumentException.class, () -> {
            //Arrange
            Company company = new Company();
            String userName = "";
            String email = "manueloliveira@beaver.com";
            String password = "ghi";
            String passwordConfirmation = "ghi";
            String function = "tester";
            String photo = "photo";
            RegisterUserController controller = new RegisterUserController();
            controller.createSystemUser(userName, email, function, photo, password, passwordConfirmation);
        });
    }
}