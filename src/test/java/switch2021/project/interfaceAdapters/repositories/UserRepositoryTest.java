package switch2021.project.interfaceAdapters.repositories;

import static org.mockito.Mockito.mock;

//@ExtendWith(SpringExtension.class)
//@SpringBootApplication
//@SpringBootTest //Useful for integration tests
//@SpringJUnitConfig
public class UserRepositoryTest {

//    @Test
//    @DisplayName(".findSystemUserByEmail(String email)")
//    void itShouldFindASystemUser() {
//        //Arrange
//        IUserRepo underTest = new UserRepository();
//
//        String email = "oliveira@beaver.com";
//        User userDouble = mock(User.class);
//        UserID idDouble = mock(UserID.class);
//        Email emailDouble = mock(Email.class);
//
//        //Stubbing behaviour of userDouble
//        when(userDouble.getUserId()).thenReturn(idDouble);
//        when(idDouble.getEmail()).thenReturn(emailDouble);
//        when(emailDouble.getEmailText()).thenReturn(email);
//        underTest.save(userDouble);
//
//        when(userDouble.isYourEmail(email)).thenReturn(true);
//        //Act
//        //User actual = underTest.findByUserID(email);
//        Optional<User> actual = underTest.findUserById(idDouble);
//        //Assert
//        assertNotNull(actual);
//    }

//    @Test
//    @DisplayName(".findSystemUserByEmail(String email)")
//    void itShouldNotFindTheSystemUser() {
//        //Arrange
//        IUserRepo underTest = new UserRepository();
//        //User1
//        String email = "oliveira@beaver.com";
//        User userDouble = mock(User.class);
//        UserID idDouble = mock(UserID.class);
//        Email emailDouble = mock(Email.class);
//        //Stubbing behaviour of User1
//        when(userDouble.getUserId()).thenReturn(idDouble);
//        when(idDouble.getEmail()).thenReturn(emailDouble);
//        when(emailDouble.getEmailText()).thenReturn(email);
//        underTest.save(userDouble);
//        when(userDouble.isYourEmail(email)).thenReturn(true);
//
//        //User2
//        String email2 = "pereira@beaver.com";
//        User user2Double = mock(User.class);
//        UserID id2Double = mock(UserID.class);
//        Email email2Double = mock(Email.class);
//        when(user2Double.getUserId()).thenReturn(id2Double);
//        when(id2Double.getEmail()).thenReturn(email2Double);
//        when(email2Double.getEmailText()).thenReturn(email2);
//        //Stubbing behaviour of User2
//        when(user2Double.isYourEmail(email2)).thenReturn(false);
//        //Act
//        //User actual = underTest.findByUserID(email2);
//        Optional<User> actual = underTest.findUserById(id2Double);
//        //Assert
//        assertTrue(actual.isEmpty());
//    }

//    @Test
//    @DisplayName(".findAllSystemUsers()")
//    void itShouldFindAnEmptyList() {
//        //Arrange
//        IUserRepo underTest = new UserRepository();
//        //Act
//        List<User> actualList = underTest.findAllSystemUsers();
//        //Assert
//        assertEquals(0, actualList.size());
//    }
//
//    @Test
//    @DisplayName(".findAllSystemUsers()")
//    void itShouldFindAList() {
//        //Arrange
//        IUserRepo underTest = new UserRepository();
//
//        String email = "oliveira@beaver.com";
//        User userDouble = mock(User.class);
//        UserID idDouble = mock(UserID.class);
//        Email emailDouble = mock(Email.class);
//
//        when(userDouble.getUserId()).thenReturn(idDouble);
//        when(idDouble.getEmail()).thenReturn(emailDouble);
//        when(emailDouble.getEmailText()).thenReturn(email);
//
//        underTest.save(userDouble);
//        //Act
//        List<User> actualList = underTest.findAllSystemUsers();
//        //Assert
//        assertEquals(1, actualList.size());
//    }

 /*   @Test
    @DisplayName(".saveSystemUser(SystemUser user)")
    void itShouldSaveSystemUser() {
        //Arrange
        IUserRepo underTest = new UserRepository();

        String email = "oliveira@beaver.com";
        User userDouble = mock(User.class);
        UserID idDouble = mock(UserID.class);
        Email emailDouble = mock(Email.class);

        when(userDouble.getUserId()).thenReturn(idDouble);
        when(idDouble.getEmail()).thenReturn(emailDouble);
        when(emailDouble.getEmailText()).thenReturn(email);
        //Act
        boolean isSaved = underTest.save(userDouble);
        //Assert
        assertTrue(isSaved);
    }

    @Test
    @DisplayName(".saveSystemUser(SystemUser user)")
    void itShouldFailSaveSystemUserPresent() {
        //Arrange
        IUserRepo underTest = new UserRepository();

        String email = "oliveira@beaver.com";
        User userDouble = mock(User.class);
        UserID idDouble = mock(UserID.class);
        Email emailDouble = mock(Email.class);

        when(userDouble.getUserId()).thenReturn(idDouble);
        when(idDouble.getEmail()).thenReturn(emailDouble);
        when(emailDouble.getEmailText()).thenReturn(email);
        underTest.save(userDouble);
        //Act
        boolean isSaved = underTest.save(userDouble);
        //Assert
        assertFalse(isSaved);
    }

    @Test
    @DisplayName(".saveSystemUser(SystemUser user)")
    void itShouldFailSaveNull() {
        //Arrange
        IUserRepo underTest = new UserRepository();

        User user = null;
        //Act
        boolean isSaved = underTest.save(user);
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
        UserID idDouble = mock(UserID.class);
        Email emailDouble = mock(Email.class);

        underTest.saveSystemUser(userDouble);

        when(userDouble.getUserId()).thenReturn(idDouble);
        when(idDouble.getEmail()).thenReturn(emailDouble);
        when(emailDouble.getEmailText()).thenReturn(email);
        //Act
        boolean exists = underTest.existsByEmail(email);
        //Assert
        assertTrue(exists);
    }

  */
}
