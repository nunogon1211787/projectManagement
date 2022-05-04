package switch2021.project.repositories;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switch2021.project.interfaces.ISystemUserRepo;
import switch2021.project.model.SystemUser.SystemUser;
import switch2021.project.model.valueObject.Email;
import switch2021.project.model.valueObject.SystemUserID;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


//@ExtendWith(SpringExtension.class)
//@SpringBootApplication
//@SpringBootTest //Useful for integration tests
//@SpringJUnitConfig
public class SystemUserRepositoryTest {

    /*@MockBean
    private ISystemUserRepo underTest;*/

    @Test
    @DisplayName(".findSystemUserByEmail(String email)")
    void itShouldFindASystemUser() {
        //Arrange
        //MockitoAnnotations.openMocks(this);
        ISystemUserRepo underTest = new SystemUserRepository(); //until spring annotations resolution

        String email = "oliveira@beaver.com";
        SystemUser userDouble = mock(SystemUser.class);
        SystemUserID idDouble = mock(SystemUserID.class);
        Email emailDouble = mock(Email.class);

        //Stubbing behaviour of userDouble
        when(userDouble.getSystemUserId()).thenReturn(idDouble);
        when(idDouble.getEmail()).thenReturn(emailDouble);
        when(emailDouble.getEmailText()).thenReturn(email);
        underTest.saveSystemUser(userDouble);

        when(userDouble.isYourEmail(email)).thenReturn(true);
        //Act
        SystemUser actual = underTest.findSystemUserByEmail(email);
        //Assert
        assertNotNull(actual);
        //assertEquals(email, actual.getSystemUserId().getEmail().getEmailText());
    }

    @Test
    @DisplayName(".findSystemUserByEmail(String email)")
    void itShouldNotFindTheSystemUser() {
        //Arrange
        //MockitoAnnotations.openMocks(this);
        ISystemUserRepo underTest = new SystemUserRepository(); //until spring annotations resolution
        //User1
        String email = "oliveira@beaver.com";
        SystemUser userDouble = mock(SystemUser.class);
        SystemUserID idDouble = mock(SystemUserID.class);
        Email emailDouble = mock(Email.class);
        //Stubbing behaviour of User1
        when(userDouble.getSystemUserId()).thenReturn(idDouble);
        when(idDouble.getEmail()).thenReturn(emailDouble);
        when(emailDouble.getEmailText()).thenReturn(email);
        underTest.saveSystemUser(userDouble);
        when(userDouble.isYourEmail(email)).thenReturn(true);

        //User2
        String email2 = "pereira@beaver.com";
        SystemUser user2Double = mock(SystemUser.class);
        //Stubbing behaviour of User2
        when(user2Double.isYourEmail(email2)).thenReturn(false);
        //Act
        SystemUser actual = underTest.findSystemUserByEmail(email2);
        //Assert
        assertNull(actual);
    }

    @Test
    @DisplayName(".findAllSystemUsers()")
    void itShouldFindAnEmptyList() {
        //Arrange
        //MockitoAnnotations.openMocks(this);
        ISystemUserRepo underTest = new SystemUserRepository(); //until spring annotations resolution
        //Act
        List<SystemUser> actualList = underTest.findAllSystemUsers();
        //Assert
        assertEquals(0, actualList.size());
    }

    @Test
    @DisplayName(".findAllSystemUsers()")
    void itShouldFindAList() {
        //Arrange
        //MockitoAnnotations.openMocks(this);
        ISystemUserRepo underTest = new SystemUserRepository(); //until spring annotations resolution

        String email = "oliveira@beaver.com";
        SystemUser userDouble = mock(SystemUser.class);
        SystemUserID idDouble = mock(SystemUserID.class);
        Email emailDouble = mock(Email.class);

        when(userDouble.getSystemUserId()).thenReturn(idDouble);
        when(idDouble.getEmail()).thenReturn(emailDouble);
        when(emailDouble.getEmailText()).thenReturn(email);

        underTest.saveSystemUser(userDouble);
        //Act
        List<SystemUser> actualList = underTest.findAllSystemUsers();
        //Assert
        assertEquals(1, actualList.size());
    }

    @Test
    @DisplayName(".saveSystemUser(SystemUser user)")
    void itShouldSaveSystemUser() {
        //Arrange
        //MockitoAnnotations.openMocks(this);
        ISystemUserRepo underTest = new SystemUserRepository(); //until spring annotations resolution

        String email = "oliveira@beaver.com";
        SystemUser userDouble = mock(SystemUser.class);
        SystemUserID idDouble = mock(SystemUserID.class);
        Email emailDouble = mock(Email.class);

        when(userDouble.getSystemUserId()).thenReturn(idDouble);
        when(idDouble.getEmail()).thenReturn(emailDouble);
        when(emailDouble.getEmailText()).thenReturn(email);
        //Act
        boolean isSaved = underTest.saveSystemUser(userDouble);
        //Assert
        assertTrue(isSaved);
    }

    @Test
    @DisplayName(".saveSystemUser(SystemUser user)")
    void itShouldFailSaveSystemUserPresent() {
        //Arrange
        //MockitoAnnotations.openMocks(this);
        ISystemUserRepo underTest = new SystemUserRepository(); //until spring annotations resolution

        String email = "oliveira@beaver.com";
        SystemUser userDouble = mock(SystemUser.class);
        SystemUserID idDouble = mock(SystemUserID.class);
        Email emailDouble = mock(Email.class);

        when(userDouble.getSystemUserId()).thenReturn(idDouble);
        when(idDouble.getEmail()).thenReturn(emailDouble);
        when(emailDouble.getEmailText()).thenReturn(email);
        underTest.saveSystemUser(userDouble);
        //Act
        boolean isSaved = underTest.saveSystemUser(userDouble);
        //Assert
        assertFalse(isSaved);
    }

    @Test
    @DisplayName(".saveSystemUser(SystemUser user)")
    void itShouldFailSaveNull() {
        //Arrange
        //MockitoAnnotations.openMocks(this);
        ISystemUserRepo underTest = new SystemUserRepository(); //until spring annotations resolution

        SystemUser user = null;
        //Act
        boolean isSaved = underTest.saveSystemUser(user);
        //Assert
        assertFalse(isSaved);
    }
 /*
    @Test
    void itShouldCheckIfSystemUserExistsByEmail() {
        //Arrange
        //MockitoAnnotations.openMocks(this);
        ISystemUserRepo underTest = new SystemUserRepository(); //until spring annotations resolution

        String email = "oliveira@beaver.com";
        SystemUser userDouble = mock(SystemUser.class);
        SystemUserID idDouble = mock(SystemUserID.class);
        Email emailDouble = mock(Email.class);

        underTest.saveSystemUser(userDouble);

        when(userDouble.getSystemUserId()).thenReturn(idDouble);
        when(idDouble.getEmail()).thenReturn(emailDouble);
        when(emailDouble.getEmailText()).thenReturn(email);
        //Act
        boolean exists = underTest.existsByEmail(email);
        //Assert
        assertTrue(exists);
    }

  */
}
