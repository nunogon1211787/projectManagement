package switch2021.project.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import switch2021.project.dto.IdDTO;
import switch2021.project.dto.OutputUserDTO;
import switch2021.project.dto.UpdateDataDTO;
import switch2021.project.interfaces.IUserRepo;
import switch2021.project.mapper.UserMapper;
import switch2021.project.model.SystemUser.User;
import switch2021.project.model.valueObject.*;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
public class UpdatePersonalDataServiceTest {

    @MockBean
    private IUserRepo iUserRepo;
    @MockBean
    private UserMapper userMapper;
    @InjectMocks
    UpdatePersonalDataService updatePersonalDataService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Update Personal Data, with Success")
    public void updatePersonalDataSuccess_1(){
        //Arrange
        User user = mock(User.class);
        UpdateDataDTO input = mock(UpdateDataDTO.class);
        OutputUserDTO out = mock(OutputUserDTO.class);

        Name name = mock(Name.class);
        SystemUserID systemUserID = mock(SystemUserID.class);
        Email email = mock(Email.class);
        Function function = mock(Function.class);
        Photo photo = mock(Photo.class);
        Description description = mock(Description.class);
        IdDTO idDTO = mock(IdDTO.class);
        UserProfileID userProfileID = mock(UserProfileID.class);


        when(user.getSystemUserId()).thenReturn(systemUserID);
        when(systemUserID.getEmail()).thenReturn(email);
        when(email.getEmailText()).thenReturn("email@email.com");
        when(user.getUserName()).thenReturn(name);
        when(name.getText()).thenReturn("User Name");
        when(user.getFunction()).thenReturn(function);
        when(function.getText()).thenReturn("Function");
        when(user.getPhoto()).thenReturn(photo);
        when(photo.getExtension()).thenReturn("photo.png");
        when(user.getEncryptedPassword()).thenReturn(description);
        when(description.getText()).thenReturn("Password");
        when(user.getAssignedIdProfiles()).thenReturn(List.of(userProfileID));
        when(userProfileID.getUserProfileName()).thenReturn(description);
        when(description.getText()).thenReturn("userProfile");

        when(iUserRepo.findByUserID(idDTO.getId())).thenReturn(user);
        when(idDTO.getId()).thenReturn("email@email.com");

        when(input.getUserName()).thenReturn("New User Name");
        when(input.getFunction()).thenReturn("New function");
        when(input.getPhoto()).thenReturn("newPhoto.png");

        when(out.getUserName()).thenReturn("New User Name");

        when(userMapper.toDto(user)).thenReturn(out);

        //Act
        OutputUserDTO result =  updatePersonalDataService.updatePersonalData(idDTO, input);

        //Assert
        assertEquals(out.getUserName(), result.getUserName());
    }

}
