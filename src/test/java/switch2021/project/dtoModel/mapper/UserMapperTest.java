package switch2021.project.dtoModel.mapper;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switch2021.project.dtoModel.dto.OutputUserDTO;
import switch2021.project.entities.aggregates.User.User;
import switch2021.project.entities.valueObjects.vos.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UserMapperTest {

    @Test
    @DisplayName("toDTO test - Email")
    public void toDTOSuccess_Email() {
        //Arrange
        UserMapper userMapper = new UserMapper();
        User user = mock(User.class);
        UserID userID = mock(UserID.class);
        Name name = mock(Name.class);
        Email email = mock(Email.class);
        Function function = mock(Function.class);
        Photo photo = mock(Photo.class);

        //Act
        when(user.getUserId()).thenReturn(userID);
        when(userID.getEmail()).thenReturn(email);
        when(email.getEmailText()).thenReturn("user@email.com");
        when(user.getUserName()).thenReturn(name);
        when(name.getText()).thenReturn("User Name");
        when(user.getFunction()).thenReturn(function);
        when(function.getText()).thenReturn("Function");
        when(user.getPhoto()).thenReturn(photo);
        when(photo.getExtension()).thenReturn("photo.png");
        when(user.isActive()).thenReturn(true);

        OutputUserDTO outputUserDTO = userMapper.toDto(user);

        //Assert
        assertEquals(user.getUserId().getEmail().getEmailText(), outputUserDTO.email);
    }

    @Test
    @DisplayName("toDTO test - Name")
    public void toDTOSuccess_Name() {
        //Arrange
        UserMapper userMapper = new UserMapper();
        User user = mock(User.class);
        UserID userID = mock(UserID.class);
        Name name = mock(Name.class);
        Email email = mock(Email.class);
        Function function = mock(Function.class);
        Photo photo = mock(Photo.class);

        //Act
        when(user.getUserId()).thenReturn(userID);
        when(userID.getEmail()).thenReturn(email);
        when(email.getEmailText()).thenReturn("user@email.com");
        when(user.getUserName()).thenReturn(name);
        when(name.getText()).thenReturn("User Name");
        when(user.getFunction()).thenReturn(function);
        when(function.getText()).thenReturn("Function");
        when(user.getPhoto()).thenReturn(photo);
        when(photo.getExtension()).thenReturn("photo.png");
        when(user.isActive()).thenReturn(true);

        OutputUserDTO outputUserDTO = userMapper.toDto(user);

        //Assert
        assertEquals(user.getUserName().getText(), outputUserDTO.userName);
    }

    @Test
    @DisplayName("toDTO test - Function")
    public void toDTOSuccess_Function() {
        //Arrange
        UserMapper userMapper = new UserMapper();
        User user = mock(User.class);
        UserID userID = mock(UserID.class);
        Name name = mock(Name.class);
        Email email = mock(Email.class);
        Function function = mock(Function.class);
        Photo photo = mock(Photo.class);

        //Act
        when(user.getUserId()).thenReturn(userID);
        when(userID.getEmail()).thenReturn(email);
        when(email.getEmailText()).thenReturn("user@email.com");
        when(user.getUserName()).thenReturn(name);
        when(name.getText()).thenReturn("User Name");
        when(user.getFunction()).thenReturn(function);
        when(function.getText()).thenReturn("Function");
        when(user.getPhoto()).thenReturn(photo);
        when(photo.getExtension()).thenReturn("photo.png");
        when(user.isActive()).thenReturn(true);

        OutputUserDTO outputUserDTO = userMapper.toDto(user);

        //Assert
        assertEquals(user.getFunction().getText(), outputUserDTO.function);
    }

    @Test
    @DisplayName("toDTO test - Photo")
    public void toDTOSuccess_Photo() {
        //Arrange
        UserMapper userMapper = new UserMapper();
        User user = mock(User.class);
        UserID userID = mock(UserID.class);
        Name name = mock(Name.class);
        Email email = mock(Email.class);
        Function function = mock(Function.class);
        Photo photo = mock(Photo.class);

        //Act
        when(user.getUserId()).thenReturn(userID);
        when(userID.getEmail()).thenReturn(email);
        when(email.getEmailText()).thenReturn("user@email.com");
        when(user.getUserName()).thenReturn(name);
        when(name.getText()).thenReturn("User Name");
        when(user.getFunction()).thenReturn(function);
        when(function.getText()).thenReturn("Function");
        when(user.getPhoto()).thenReturn(photo);
        when(photo.getExtension()).thenReturn("photo.png");
        when(user.isActive()).thenReturn(true);

        OutputUserDTO outputUserDTO = userMapper.toDto(user);

        //Assert
        assertEquals(user.getPhoto().getExtension(), outputUserDTO.photo);
    }

    @Test
    @DisplayName("toDTO test - Is Active")
    public void toDTOSuccess_IsActive() {
        //Arrange
        UserMapper userMapper = new UserMapper();
        User user = mock(User.class);
        UserID userID = mock(UserID.class);
        Name name = mock(Name.class);
        Email email = mock(Email.class);
        Function function = mock(Function.class);
        Photo photo = mock(Photo.class);

        //Act
        when(user.getUserId()).thenReturn(userID);
        when(userID.getEmail()).thenReturn(email);
        when(email.getEmailText()).thenReturn("user@email.com");
        when(user.getUserName()).thenReturn(name);
        when(name.getText()).thenReturn("User Name");
        when(user.getFunction()).thenReturn(function);
        when(function.getText()).thenReturn("Function");
        when(user.getPhoto()).thenReturn(photo);
        when(photo.getExtension()).thenReturn("photo.png");
        when(user.isActive()).thenReturn(true);

        //Assert
        assertTrue(user.isActive());
    }
}
