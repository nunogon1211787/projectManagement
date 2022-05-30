package switch2021.project.applicationServices.service;

import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import switch2021.project.entities.factories.factoryInterfaces.IUserFactory;
import switch2021.project.applicationServices.iRepositories.IUserRepo;
import switch2021.project.dtoModel.mapper.UserMapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;

@SpringBootTest
public class UserServiceTest {

    //@Autowired
    //UserService registerUserService; //for integration tests purpose

    //@Autowired
    //private final CacheManager cacheManager = new SimpleCacheManager();
    /*@BeforeEach
    public void evictAllCaches() {
        for (String name : cacheManager.getCacheNames()) {
            Objects.requireNonNull(cacheManager.getCache(name)).clear();
        }
    }*/
    /*@Bean
    public CacheManager cacheManager() {
        return new NoOpCacheManager();
    }
    //@AfterEach
    public void clear(){
        clear();
    }*/

    //for unit tests purpose:
    @MockBean
    private IUserRepo userRepoDouble;
    @MockBean
    private UserMapper userMapperDouble;
    @MockBean
    private IUserFactory userFactoryDouble;
    @InjectMocks
    UserService underTest;

    @BeforeEach
    void setUp()/* throws Exception*/ {
        MockitoAnnotations.openMocks(this);
    }

//    //Unit Test Success
//    @Test
//    void itShouldRegisterAUser() {
//        //S.U.T. {UserService}
//        //Arrange
//        User userDouble = mock(User.class);
//        OutputUserDTO outUserDTODouble = mock(OutputUserDTO.class);
//        NewUserInfoDTO newUserInfoDTO = mock(NewUserInfoDTO.class);
//        outUserDTODouble.email = "mano@beaver.com";
//
//        when(userFactoryDouble.createUser(any())).thenReturn(userDouble);
//        when(userRepoDouble.save(userDouble)).thenReturn(true);
//        when(userMapperDouble.toDto(userDouble)).thenReturn(outUserDTODouble);
//        //Act
//        OutputUserDTO outDTO = underTest.createAndSaveUser(newUserInfoDTO);
//        //Assert
//        assertEquals("mano@beaver.com", outDTO.email);
//    }

//    @Test
//    void itShouldNotRegisterAUser() {
//        assertThrows(IllegalArgumentException.class, () -> {
//            //S.U.T. {UserService}
//            //Arrange
//            User userDouble = mock(User.class);
//
//            when(userFactoryDouble.createUser(any())).thenReturn(userDouble);
//            when(userRepoDouble.save(userDouble)).thenReturn(false);
//            //Act
//            underTest.createAndSaveUser(any());
//        });
//    }

/*    @Test
    @DisplayName("Update Personal Data, with Success")
    public void updatePersonalDataSuccess_1(){
        //Arrange
        User user = mock(User.class);
        UpdateDataDTO input = mock(UpdateDataDTO.class);
        OutputUserDTO out = mock(OutputUserDTO.class);

        Name name = mock(Name.class);
        UserID systemUserID = mock(UserID.class);
        Email email = mock(Email.class);
        Function function = mock(Function.class);
        Photo photo = mock(Photo.class);
        Description description = mock(Description.class);
        IdDTO idDTO = mock(IdDTO.class);
        UserProfileID userProfileID = mock(UserProfileID.class);


        when(user.getUserId()).thenReturn(systemUserID);
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

        when(userRepoDouble.findByUserID(idDTO.getId())).thenReturn(user);
        when(idDTO.getId()).thenReturn("email@email.com");

        when(input.getUserName()).thenReturn("New User Name");
        when(input.getFunction()).thenReturn("New function");
        when(input.getPhoto()).thenReturn("newPhoto.png");

        when(out.getUserName()).thenReturn("New User Name");

        when(userMapperDouble.toDto(user)).thenReturn(out);

        //Act
        OutputUserDTO result =  underTest.updatePersonalData(idDTO, input);

        //Assert
        assertEquals(out.getUserName(), result.getUserName());
    }

 */

    //Integration Test Success
/*    @Test
    void itShouldRegisterAUser(){
        //Arrange
        NewUserInfoDTO dto = new NewUserInfoDTO();
        dto.userName = "manuel";
        dto.email = "manuel@beaver.com";
        dto.password = "Qwerty_1";
        dto.passwordConfirmation = "Qwerty_1";
        dto.function = "tester";
        dto.photo = "photo.png";
        //Act
        OutputUserDTO outDTO = registerUserService.createAndSaveSystemUser(dto);
        //Assert
        assertEquals("manuel@beaver.com", outDTO.email);
        assertFalse(outDTO.isActive);
    }

    //Integration Test Fail
    @Test
    void itShouldNotRegisterUserEmailAlreadyExists() {
        assertThrows(IllegalArgumentException.class, () -> {
            //Arrange
            NewUserInfoDTO dto = new NewUserInfoDTO();
            dto.userName = "Oliveira";
            dto.email = "oliveira@beaver.com";
            dto.password = "Qwerty_1";
            dto.passwordConfirmation = "Qwerty_1";
            dto.function = "tester";
            dto.photo = "photo.png";
            registerUserService.createAndSaveSystemUser(dto);
            //Act
            registerUserService.createAndSaveSystemUser(dto);
        });
    }
/*

 */
}
