package switch2021.project.controller.old;

import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import switch2021.project.dto.NewUserInfoDTO;
import switch2021.project.factory.SystemUserFactory;
import switch2021.project.mapper.SystemUserMapper;
import switch2021.project.repositories.UserProfileRepository;
import switch2021.project.service.RegisterUserService;
import switch2021.project.stores.SystemUserStore;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RegisterUserControllerTest {

/*    @MockBean
    RegisterUserService systemUserService;

    @MockBean
    ISystemUserFactory systemUserFactory;

    @MockBean
    UserProfileRepository userProfileStore;

    @InjectMocks
    RegisterUserController controller;
*/
    @Test
        //create and save a new user
    void createSystemUserSuccess() {
        //Arrange
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        UserProfileRepository userProfileStore = new UserProfileRepository();

        SystemUserStore systemUserStore = new SystemUserStore();
        SystemUserMapper mapper = new SystemUserMapper();
        SystemUserFactory factory = new SystemUserFactory();
        RegisterUserService service = new RegisterUserService(systemUserStore,userProfileStore,mapper,factory);
        //RegisterUserService service = new RegisterUserService();

        userProfileStore.populateDefault();
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
        assertEquals(responseEntity.getStatusCodeValue(), 201);
    }

 /*
    @ExtendWith(SpringExtension.class)
public class CreateUserStoryServiceTest {

    @InjectMocks
    CreateUserStoryService createUserStoryService;

    @Mock
    private IRepoUserStory iRepoUserStory;
    @Mock
    private UserStoryMapper userStoryMapper;
    @Mock
    private IUserStoryFactory userStoryFactory;


    @Test
    public void test() {

        //Arrange
        UserStoryDTO userStoryDTO = new UserStoryDTO();
        userStoryDTO.userStoryID = "Project_2022_1_As a PO, i want to test this string";
        userStoryDTO.projectID = "Project_2022_1";
        userStoryDTO.title = "As a PO, i want to test this string";
        userStoryDTO.priority = 1;
        userStoryDTO.description = "Default Story";
        userStoryDTO.timeEstimate = 5.0;


        OutputUsDTO outputUserStoryDTO = new OutputUsDTO();
        outputUserStoryDTO.userStoryID = "Project_2022_1_As a PO, i want to test this string";
        outputUserStoryDTO.projectID = "Project_2022_1";
        outputUserStoryDTO.title = "As a PO, i want to test this string";

        //Act
        UserStory us = new UserStory(userStoryDTO.projectID, userStoryDTO.userStoryID,
                userStoryDTO.title, userStoryDTO.priority, userStoryDTO.description, userStoryDTO.timeEstimate);

        when(userStoryFactory.createUserStory(userStoryDTO.projectID, userStoryDTO.userStoryID,
                userStoryDTO.title, userStoryDTO.priority, userStoryDTO.description, userStoryDTO.timeEstimate)).thenReturn(us);

        when(iRepoUserStory.save(any(UserStory.class))).thenReturn(true);

        when(userStoryMapper.toDto(us)).thenReturn(outputUserStoryDTO);


        OutputUsDTO andSaveUserStory = createUserStoryService.createAndSaveUserStory(userStoryDTO);

        //Assert
        assertEquals(outputUserStoryDTO.userStoryID, andSaveUserStory.getUserStoryID());
        assertEquals(outputUserStoryDTO.projectID, andSaveUserStory.getProjectID());
        assertEquals(outputUserStoryDTO.title, andSaveUserStory.getTitle());
    }
}

 */


/*    @Test
        //check fail username input value is empty
    void createSystemUserFailUserNameIsEmpty() {
        assertThrows(IllegalArgumentException.class, () -> {
            //Arrange
            Company company = new Company();
            SystemUserStore systemUserStore = company.getSystemUserStore();
            UserProfileRepository userProfileStore = company.getUserProfileStore();
            RegisterUserService systemUserService = new RegisterUserService(systemUserStore, userProfileStore);
            RegisterUserController controller = new RegisterUserController(company, systemUserService);
            String userName = "";
            String email = "manueloliveira@beaver.com";
            String password = "ghi";
            String passwordConfirmation = "ghi";
            String function = "tester";
            String photo = "photo";

            //controller.createSystemUser(userName, email, function, photo, password, passwordConfirmation);
            controller.registerUser(userName, email, function, photo, password, passwordConfirmation);
        });
    }

 */
}