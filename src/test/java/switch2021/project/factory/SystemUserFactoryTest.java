package switch2021.project.factory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import switch2021.project.dto.NewUserInfoDTO;
import switch2021.project.factoryInterface.*;
import switch2021.project.model.SystemUser.SystemUser;
import switch2021.project.model.valueObject.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
public class SystemUserFactoryTest {

    @MockBean
    IEmailFactory emailFactory;
    @MockBean
    ISystemUserIDFactory idFactory;
    @MockBean
    INameFactory nameFactory;
    @MockBean
    IFunctionFactory functionFactory;
    @MockBean
    IPasswordFactory passwordFactory;
    @MockBean
    IPhotoFactory photoFactory;
    @MockBean
    IUserProfileIDFactory userProfileIDFactory;
    @InjectMocks
    SystemUserFactory underTest;

    @BeforeEach
    public void setUp()/* throws Exception*/ {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void itShouldCreateASystemUser() {
        //S.U.T. {SystemUserFactory, SystemUser}
        //Arrange
        NewUserInfoDTO infoDTODouble = mock(NewUserInfoDTO.class);

        Name nameDouble = mock(Name.class);
        SystemUserID idDouble = mock(SystemUserID.class);
        Password passwordDouble = mock(Password.class);
        Function functionDouble = mock(Function.class);
        Photo photoDouble = mock(Photo.class);
        UserProfileId userProfileIdDouble = mock(UserProfileId.class);
        Description descriptionDouble = mock(Description.class);

        when(nameFactory.createName(any())).thenReturn(nameDouble);
        when(idFactory.createSystemUserID(any())).thenReturn(idDouble);
        when(functionFactory.createFunction(any())).thenReturn(functionDouble);
        when(photoFactory.createPhoto(any())).thenReturn(photoDouble);

        when(passwordFactory.createPassword(any())).thenReturn(passwordDouble);
        when(passwordDouble.getPwd()).thenReturn("Qwerty_1");

        when(userProfileIDFactory.createUserProfileID(any())).thenReturn(userProfileIdDouble);
        when(userProfileIdDouble.getUserProfileName()).thenReturn(descriptionDouble);
        when(descriptionDouble.getText()).thenReturn("Visitor");
        //Act
        SystemUser isCreated = underTest.createSystemUser(infoDTODouble);
        //Assert
        assertNotNull(isCreated);
        assertEquals("Visitor",isCreated.getAssignedProfiles().get(0).getUserProfileName().getText());
    }
}
