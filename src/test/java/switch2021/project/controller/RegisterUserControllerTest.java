package switch2021.project.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import switch2021.project.controller.RegisterUserController;
import switch2021.project.dto.NewUserInfoDTO;
import switch2021.project.factory.*;
import switch2021.project.mapper.SystemUserMapper;
import switch2021.project.repositories.SystemUserRepository;
import switch2021.project.service.RegisterUserService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class RegisterUserControllerTest {
    @Mock
    RegisterUserService registerUserService;

    @InjectMocks
    RegisterUserController underTest;

    @BeforeEach
    public void setUp()/* throws Exception*/ {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("registerUser(NewUserInfoDTO infoDTO)")
    void itShouldRegisterAUser() {
        //Arrange
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        NewUserInfoDTO dto = new NewUserInfoDTO();
        dto.userName = "manueloliveira";
        dto.email = "manueloliveira@beaver.com";
        dto.password = "Qwerty_1";
        dto.passwordConfirmation = "Qwerty_1";
        dto.function = "tester";
        dto.photo = "photo.png";
        //Act
        ResponseEntity<Object> responseEntity = underTest.registerUser(dto);
        //Assert
        assertEquals(201, responseEntity.getStatusCodeValue());
    }

    //Integration Tests
    @Test
    @DisplayName("registerUser(NewUserInfoDTO infoDTO)")
    void itShouldRegisterAUserIntegration() {
        //Arrange
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        EmailFactory emailFactory = new EmailFactory();
        SystemUserIDFactory idFactory = new SystemUserIDFactory(emailFactory);
        NameFactory nameFactory = new NameFactory();
        FunctionFactory functionFactory = new FunctionFactory();
        PasswordFactory passwordFactory = new PasswordFactory();
        PhotoFactory photoFactory = new PhotoFactory();
        DescriptionFactory descriptionFactory = new DescriptionFactory();
        UserProfileIDFactory userProfileIDFactory = new UserProfileIDFactory(descriptionFactory);
        SystemUserFactory factory = new SystemUserFactory(idFactory, nameFactory, functionFactory, passwordFactory,
                photoFactory,userProfileIDFactory);
        SystemUserRepository systemUserRepository = new SystemUserRepository();
        SystemUserMapper mapper = new SystemUserMapper();


        RegisterUserService service = new RegisterUserService(systemUserRepository, mapper, factory);

        RegisterUserController controller = new RegisterUserController(service);

        NewUserInfoDTO dto = new NewUserInfoDTO();
        dto.userName = "manueloliveira";
        dto.email = "manueloliveira@beaver.com";
        dto.password = "Qwerty_1";
        dto.passwordConfirmation = "Qwerty_1";
        dto.function = "tester";
        dto.photo = "photo.png";
        //Act
        ResponseEntity<Object> responseEntity = controller.registerUser(dto);
        //Assert
        assertEquals(201, responseEntity.getStatusCodeValue());
    }

/*    @Test
    @DisplayName("registerUser(NewUserInfoDTO infoDTO)")
    void itShouldFailToRegisterAUserPasswordsNotMatch() {
        assertThrows(IllegalArgumentException.class, () -> {
            //Arrange
            MockHttpServletRequest request = new MockHttpServletRequest();
            RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

            EmailFactory emailFactory = new EmailFactory();
            SystemUserIDFactory idFactory = new SystemUserIDFactory(emailFactory);
            NameFactory nameFactory = new NameFactory();
            FunctionFactory functionFactory = new FunctionFactory();
            PasswordFactory passwordFactory = new PasswordFactory();
            PhotoFactory photoFactory = new PhotoFactory();
            SystemUserFactory factory = new SystemUserFactory(idFactory, nameFactory, functionFactory, passwordFactory,
                    photoFactory);
            SystemUserRepository systemUserRepository = new SystemUserRepository();
            SystemUserMapper mapper = new SystemUserMapper();
            RegisterUserService service = new RegisterUserService(systemUserRepository, mapper, factory);
            RegisterUserController controller = new RegisterUserController(service);

            NewUserInfoDTO dto = new NewUserInfoDTO();
            dto.userName = "manueloliveira";
            dto.email = "manueloliveira@beaver.com";
            dto.password = "Qwerty_1";
            dto.passwordConfirmation = "Qwerty_2";
            dto.function = "tester";
            dto.photo = "photo.png";
            //Act
            controller.registerUser(dto);
        });
    }

 */
}