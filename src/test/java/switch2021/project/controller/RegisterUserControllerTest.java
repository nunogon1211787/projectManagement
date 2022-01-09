package switch2021.project.controller;

import org.junit.jupiter.api.Test;
import switch2021.project.model.Company;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RegisterUserControllerTest {
    @Test
    void createSystemUserWithPhotoSuccess() {
        //Arrange
        Company company = new Company();
        String userName = "manueloliveira";
        String email = "manueloliveira@beaver.com";
        String password = "ghi";
        String function = "tester";
        String photo = "photo";
        RegisterUserController controller = new RegisterUserController(company);

        assertTrue(controller.createSystemUser(userName, email, function, photo, password));
    }

    @Test
    void createSystemUserWithoutPhotoSuccess() {
        //Arrange
        Company company = new Company();
        String userName = "manueloliveira";
        String email = "manueloliveira@beaver.com";
        String password = "ghi";
        String function = "tester";
        RegisterUserController controller = new RegisterUserController(company);
        //Assert
        assertTrue(controller.createSystemUser(userName, email, function, password));
    }

    @Test
    void createSystemUserWithPhotoFailUserNameIsEmpty() {
        assertThrows(IllegalArgumentException.class, () -> {
            //Arrange
            Company company = new Company();
            String userName = "";
            String email = "manueloliveira@beaver.com";
            String password = "ghi";
            String function = "tester";
            String photo = "photo";
            RegisterUserController controller = new RegisterUserController(company);
            controller.createSystemUser(userName, email, function, photo, password);
        });
    }

    @Test
    void createSystemUserWithoutPhotoFailUserNameIsEmpty() {
        assertThrows(IllegalArgumentException.class, () -> {
            //Arrange
            Company company = new Company();
            String userName = "";
            String email = "manueloliveira@beaver.com";
            String password = "ghi";
            String function = "tester";
            RegisterUserController controller = new RegisterUserController(company);
            controller.createSystemUser(userName, email, function, password);
        });
    }
    //fazer para os restantes atributos vazios
}
