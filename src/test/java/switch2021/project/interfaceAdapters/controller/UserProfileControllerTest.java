package switch2021.project.interfaceAdapters.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import switch2021.project.applicationServices.service.UserProfileService;
import switch2021.project.dtoModel.dto.UserProfileDTO;
import switch2021.project.entities.aggregates.UserProfile.UserProfile;
import switch2021.project.interfaceAdapters.controller.UserProfileController;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class UserProfileControllerTest {

    @MockBean
    UserProfileService userProfileService;

    @MockBean
    UserProfile userProfile;

    @InjectMocks
    UserProfileController userProfileController;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
    }


//    @Autowired
//    UserProfileController userProfileController;

    @Test
    void shouldCreateAUserProfile() {
        // Arrange
        UserProfileDTO dto = new UserProfileDTO("regular");
        // Act
        ResponseEntity<Object> responseEntity = userProfileController.createUserProfile(dto);
        // Assert
        assertEquals(201, responseEntity.getStatusCodeValue());
    }
}
