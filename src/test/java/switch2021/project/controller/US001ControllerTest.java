package switch2021.project.controller;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class US001ControllerTest {
    @Test
    void createSystemUserWithPhotoSuccess() {
        //Arrange
        String userName = "manueloliveira";
        String email = "manueloliveira@beaver.com";
        String password = "ghi";
        String function = "tester";
        String photo = "photo";
        US001Controller controller = new US001Controller();

        boolean isNewUservalid = controller.createSystemUser(userName, email, function, photo, password);

        assertTrue(isNewUservalid);
    }

    @Test
    void createSystemUserWithoutPhotoSuccess() {
        //Arrange
        String userName = "manueloliveira";
        String email = "manueloliveira@beaver.com";
        String password = "ghi";
        String function = "tester";
        US001Controller controller = new US001Controller();

        boolean isNewUservalid = controller.createSystemUser(userName, email, function, password);

        assertTrue(isNewUservalid);
    }
}
