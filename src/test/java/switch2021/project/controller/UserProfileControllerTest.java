//package switch2021.project.controller;
//
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.http.ResponseEntity;
//import org.springframework.mock.web.MockHttpServletRequest;
//import org.springframework.web.context.request.RequestContextHolder;
//import org.springframework.web.context.request.ServletRequestAttributes;
//import switch2021.project.dto.UserProfileDTO;
//import switch2021.project.service.CreateUserProfileService;
//
//import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
//import static org.mockito.Mockito.mock;
//import static org.mockito.Mockito.when;
//
//@ExtendWith(MockitoExtension.class)
//public class UserProfileControllerTest {
//
//    @InjectMocks
//    UserProfileController controller;
//    @Mock
//    CreateUserProfileService service;
//
//    @Test
//    public void createUserProfileControllerTest() {
//        //Arrange
//        MockHttpServletRequest request = new MockHttpServletRequest();
//        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
//        UserProfileDTO dto = mock(UserProfileDTO.class);
//        //Act
//        when(service.createAndSaveUserProfile(dto)).thenReturn(dto);
//        ResponseEntity<Object> response = controller.createUserProfile(dto);
//        //Assert
//        assertThat(response.getStatusCodeValue()).isEqualTo(201);
//    }
//}
