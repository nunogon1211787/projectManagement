package switch2021.project.interfaceAdapters.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import switch2021.project.dtoModel.dto.NewUserInfoDTO;
import switch2021.project.interfaceAdapters.controller.UserController;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class UserControllerTest {

    @Autowired
    UserController registerUserController;

    /*@Test //Integration Test
    @DisplayName(".registerUser(NewUserInfoDTO infoDTO)")
    void itShouldRegisterAUserIntegration() {
        //Arrange
        NewUserInfoDTO dto = new NewUserInfoDTO();
        dto.userName = "maneloliveira";
        dto.email = "maneloliveira@beaver.com";
        dto.password = "Qwerty_1";
        dto.passwordConfirmation = "Qwerty_1";
        dto.function = "tester";
        dto.photo = "photo.png";
        //Act
        ResponseEntity<Object> responseEntity = registerUserController.registerUser(dto);
        //Assert
        assertEquals(201, responseEntity.getStatusCodeValue());
    }

     */

    @Test
    @DisplayName("registerUser(NewUserInfoDTO infoDTO)")
    void itShouldFailToRegisterAUserPasswordsNotMatch() {
        //Arrange
        NewUserInfoDTO dto = new NewUserInfoDTO();
        dto.userName = "manuelOliveira";
        dto.email = "manueloliveira@beaver.com";
        dto.password = "Qwerty_1";
        dto.passwordConfirmation = "Qwerty_2";
        dto.function = "tester";
        dto.photo = "photo.png";

        //Act
        ResponseEntity<Object> responseEntity = registerUserController.registerUser(dto);
        //Assert
        assertEquals(400, responseEntity.getStatusCodeValue());
    }
}