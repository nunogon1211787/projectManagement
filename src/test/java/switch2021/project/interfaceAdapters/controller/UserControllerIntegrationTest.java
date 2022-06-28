package switch2021.project.interfaceAdapters.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import switch2021.project.applicationServices.service.UserProfileService;
import switch2021.project.applicationServices.service.UserService;
import switch2021.project.dtoModel.dto.NewUserInfoDTO;
import switch2021.project.dtoModel.dto.RequestDTO;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerIntegrationTest {

    public static final String BASE_URL = "https://localhost:8443";

    @Autowired
    UserService userService;
    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper mapper;
    @Autowired
    UserProfileService profileService;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void registerUser() throws Exception {
        //Arrange
        String userName = "Ana";
        String email = "email@beaver.com";
        String function = "PM";
        String password = "HelloWorld2021!";
        String photo = "ana.png";
        NewUserInfoDTO inputDTO = new NewUserInfoDTO(userName, email, function, password, password, photo);
        //Act
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post(BASE_URL+"/users")
                        .contentType("application/json")
                        .content(mapper.writeValueAsString(inputDTO))
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn();
        //Assert
        assertEquals(201, result.getResponse().getStatus());
    }

    @Test
    void getUser() throws Exception {
        //Arrange
        RequestDTO dto = new RequestDTO("Director");
        String userName = "Ana";
        String email = "ana123@beaver.com";
        String function = "PM";
        String password = "HelloWorld2021!";
        String photo = "ana.png";
        NewUserInfoDTO inputDTO = new NewUserInfoDTO(userName, email, function, password, password, photo);
        userService.createAndSaveUser(inputDTO);
        //Act
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.patch(BASE_URL + "/users/ana123@beaver.com")
                        .contentType("application/json")
                        .content(mapper.writeValueAsString(dto))
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn();
        //Assert
        assertEquals(200, result.getResponse().getStatus());
    }


    @Test
    void showAllUsers() {
    }

    @Test
    void searchUsersByTypedParams() {
    }

    @Test
    void updatePersonalData() {
    }

    @Test
    void assignProfile() {
    }

    @Test
    void removeProfile() {
    }

    @Test
    void activateUser() {
    }

    @Test
    void inactivateUser() {
    }

   @Test
    void requestUserProfile() throws Exception {
        //Arrange
        RequestDTO dto = new RequestDTO("Director");
        String userName = "Ana";
        String email = "email123@beaver.com";
        String function = "PM";
        String password = "HelloWorld2021!";
        String photo = "ana.png";
        NewUserInfoDTO inputDTO = new NewUserInfoDTO(userName, email, function, password, password, photo);
        userService.createAndSaveUser(inputDTO);
        //Act
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.patch(BASE_URL + "/users/email123@beaver.com/requests")
                        .contentType("application/json")
                        .content(mapper.writeValueAsString(dto))
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn();
        //Assert
        assertEquals(200, result.getResponse().getStatus());
    }


    @Test
    void deleteUser() {
    }

}
