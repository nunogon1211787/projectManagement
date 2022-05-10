package switch2021.project.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import switch2021.project.dto.NewUserInfoDTO;
import switch2021.project.dto.OutputUserDTO;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class RegisterUserServiceTest {

    @Autowired
    RegisterUserService registerUserService; //for integration tests purpose

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

/*
    //for unit tests purpose:
    @MockBean
    private ISystemUserRepo systemUserRepo;
    @MockBean
    private SystemUserMapper systemUserMapper;
    @MockBean
    private ISystemUserFactory systemUserFactory;
    @InjectMocks
    RegisterUserService underTest;
 */

    //Integration Test Success
    @Test
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
    //Unit Test Success
    @Test
    void itShouldRegisterAUserMcks() throws Exception {
        MockitoAnnotations.openMocks(this);
        //Arrange
        SystemUser userDouble = mock(SystemUser.class);
        OutputUserDTO outUserDTODouble = mock(OutputUserDTO.class);
        outUserDTODouble.email="mano@beaver.com";

        when(systemUserFactory.createSystemUser(any())).thenReturn(userDouble);
        when(systemUserRepo.saveSystemUser(userDouble)).thenReturn(true);
        when(systemUserMapper.toDto(userDouble)).thenReturn(outUserDTODouble);
        //when(outUserDTODouble.email).thenReturn("mano@beaver.com");
        //Act
        OutputUserDTO outDTO = underTest.createAndSaveSystemUser(any());
        //Assert
        assertEquals("mano@beaver.com", outDTO.email);
    }

 */
}
