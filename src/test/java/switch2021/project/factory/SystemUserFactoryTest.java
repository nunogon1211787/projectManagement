package switch2021.project.factory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import switch2021.project.dto.NewUserInfoDTO;
import switch2021.project.factoryInterface.*;
import switch2021.project.model.SystemUser.SystemUser;
import switch2021.project.model.valueObject.*;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class SystemUserFactoryTest {

    @Mock
    IEmailFactory emailFactory;
    @Mock
    ISystemUserIDFactory idFactory;
    @Mock
    INameFactory nameFactory;
    @Mock
    IFunctionFactory functionFactory;
    @Mock
    IPasswordFactory passwordFactory;
    @Mock
    IPhotoFactory photoFactory;
    @Mock
    IUserProfileIDFactory userProfileIDFactory;
    @InjectMocks
    SystemUserFactory underTest;

    @BeforeEach
    public void setUp()/* throws Exception*/ {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName(".createSystemUser(NewUserInfoDTO infoDTO)")
    void itShouldCreateASystemUser() {
        //Arrange
        NewUserInfoDTO infoDTODouble = mock(NewUserInfoDTO.class);
        infoDTODouble.userName = "manueloliveira";
        infoDTODouble.email = "manueloliveira@beaver.com";
        infoDTODouble.password = "Qwerty_1";
        infoDTODouble.passwordConfirmation = "Qwerty_1";
        infoDTODouble.function = "tester";
        infoDTODouble.photo = "photo.png";

        Name nameDouble = mock(Name.class);
        when(nameFactory.createName(infoDTODouble.userName)).thenReturn(nameDouble);

        /*Email emailDouble = mock(Email.class);
        when(emailFactory.createEmail(infoDTODouble.email)).thenReturn(emailDouble);
         */

        SystemUserID idDouble = mock(SystemUserID.class);
        when(idFactory.createSystemUserID(infoDTODouble.email)).thenReturn(idDouble);

        Password passwordDouble = mock(Password.class);
        Password passwordConfirmationDouble = mock(Password.class);
        when(passwordFactory.createPassword(infoDTODouble.password)).thenReturn(passwordDouble);
        when(passwordFactory.createPassword(infoDTODouble.passwordConfirmation)).thenReturn(passwordConfirmationDouble);
        when(passwordConfirmationDouble.getPwd()).thenReturn("Qwerty_1");

        Function functionDouble = mock(Function.class);
        when(functionFactory.createFunction(infoDTODouble.function)).thenReturn(functionDouble);

        Photo photoDouble = mock(Photo.class);
        when(photoFactory.createPhoto(infoDTODouble.photo)).thenReturn(photoDouble);

        UserProfileId userProfileIdDouble = mock(UserProfileId.class);
        when(userProfileIDFactory.createUserProfileID("Visitor")).thenReturn(userProfileIdDouble);
        //Act
        SystemUser isCreated = underTest.createSystemUser(infoDTODouble);
        //Assert
        assertNotNull(isCreated);
    }
}
