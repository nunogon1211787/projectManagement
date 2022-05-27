package switch2021.project.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import switch2021.project.dto.UserProfileDTO;

import static org.junit.jupiter.api.Assertions.assertEquals;

//@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class UserProfileControllerTest {
/*
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
 */

    @Autowired
    UserProfileController userProfileController;

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
