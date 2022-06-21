package switch2021.project.interfaceAdapters.controller;

import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;
import switch2021.project.applicationServices.service.UserProfileService;
import switch2021.project.dtoModel.dto.UserProfileDTO;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class UserProfileControllerTest {

    @Autowired
    UserProfileController ctrl;

    @MockBean
    UserProfileService service;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void getAllProfilesSuccess() {
        //Arrange
        UserProfileDTO test = mock(UserProfileDTO.class);
        UserProfileDTO test2 = mock(UserProfileDTO.class);
        UserProfileDTO test3 = mock(UserProfileDTO.class);
        when(service.showAllProfiles()).thenReturn(CollectionModel.of
                (List.of(new UserProfileDTO[]{test, test2, test3})));
        //Act
        ResponseEntity<?> response = ctrl.showAllProfiles();
        //Assert
        assertThat(response.getStatusCodeValue()).isEqualTo(200);
    }

    @Test
    void getAllProfilesCatchException() {
        //Arrange
        //Act
        when(service.showAllProfiles()).thenThrow();
        ResponseEntity<?> response = ctrl.showAllProfiles();
        //Assert
        assertThat(response.getStatusCodeValue()).isEqualTo(400);
    }

    @SneakyThrows
    @Test
    void getProfileRequestedSuccess() {
        //Arrange
        UserProfileDTO test = mock(UserProfileDTO.class);
        String x = "Fixe";
        test.setDescription(x);
        when(service.showUserProfileRequested(x)).thenReturn(test);
        //Act
        ResponseEntity<?> response = ctrl.showUserProfileRequested(x);
        //Assert
        assertThat(response.getStatusCodeValue()).isEqualTo(200);
    }

    @SneakyThrows
    @Test
    void getProfileRequestedCatchException() {
        //Arrange
        when(service.showUserProfileRequested(anyString())).thenThrow();
        //Act
        ResponseEntity<?> response = ctrl.showUserProfileRequested("1");
        //Assert
        assertThat(response.getStatusCodeValue()).isEqualTo(404);
    }




//    @Test
//    void shouldCreateAUserProfile() {
//        // Arrange
//        UserProfileDTO dto = new UserProfileDTO("regular");
//        // Act
//        ResponseEntity<Object> responseEntity = ctrl.createUserProfile(dto);
//        // Assert
//        assertEquals(201, responseEntity.getStatusCodeValue());
//    }
}
