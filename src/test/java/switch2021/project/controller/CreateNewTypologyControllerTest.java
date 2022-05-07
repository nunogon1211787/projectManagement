package switch2021.project.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import switch2021.project.dto.TypologyDTO;
import switch2021.project.service.CreateTypologyService;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;

@SpringBootTest
public class CreateNewTypologyControllerTest {

    @InjectMocks
    CreateTypologyController controller;
    @MockBean
    CreateTypologyService service;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    public void createNewTypologyControllerTest() {
        //Arrange
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        TypologyDTO dto = mock(TypologyDTO.class);
        //Act
        when(service.createAndSaveTypology(dto)).thenReturn(dto);
        ResponseEntity<?> response = controller.createTypology(dto);
        //Assert
        assertThat(response.getStatusCodeValue()).isEqualTo(201);
    }

    @Test //Review test without body, msut have resquest, but the methods without values is passing....
    public void createNewTypologyControllerNullDescriptionTest() {
        //Arrange
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        //Act
        when(service.createAndSaveTypology(null)).thenReturn(null);
        ResponseEntity<?> response = controller.createTypology(null);
        //Assert
        assertThat(response.getStatusCodeValue()).isEqualTo(201);
    }
}
