package switch2021.project.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import switch2021.project.dto.OutputUserDTO;
import switch2021.project.factoryInterface.IUserFactory;
import switch2021.project.interfaces.IUserRepo;
import switch2021.project.mapper.UserMapper;
import switch2021.project.model.SystemUser.User;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
public class RegisterUserServiceTest {

    //@Autowired
    //RegisterUserService registerUserService; //for integration tests purpose

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
    private IUserRepo systemUserRepo;
    @MockBean
    private UserMapper userMapper;
    @MockBean
    private IUserFactory systemUserFactory;
    @InjectMocks
    RegisterUserService underTest;

    @BeforeEach
    void setUp()/* throws Exception*/ {
        MockitoAnnotations.openMocks(this);
    }

    //Unit Test Success
    @Test
    void itShouldRegisterAUser() {
        //S.U.T. {RegisterUserService}
        //Arrange
        User userDouble = mock(User.class);
        OutputUserDTO outUserDTODouble = mock(OutputUserDTO.class);
        outUserDTODouble.email = "mano@beaver.com";

        when(systemUserFactory.createUser(any())).thenReturn(userDouble);
        when(systemUserRepo.save(userDouble)).thenReturn(true);
        when(userMapper.toDto(userDouble)).thenReturn(outUserDTODouble);
        //Act
        OutputUserDTO outDTO = underTest.createAndSaveUser(any());
        //Assert
        assertEquals("mano@beaver.com", outDTO.email);
    }

    @Test
    void itShouldNotRegisterAUser() {
        assertThrows(IllegalArgumentException.class, () -> {
            //S.U.T. {RegisterUserService}
            //Arrange
            User userDouble = mock(User.class);

            when(systemUserFactory.createUser(any())).thenReturn(userDouble);
            when(systemUserRepo.save(userDouble)).thenReturn(false);
            //Act
            underTest.createAndSaveUser(any());
        });
    }

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
